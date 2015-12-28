using System;
using System.Collections.Generic;
using System.Text;
using EYouBaoAdapter.Util;
using System.Data;
using System.Windows.Forms;
using EYouBaoAdapter.Model;
using System.Net;
using System.IO;
using System.Xml.XPath;
using EYouBaoAdapter.Exception;
using System.Data.SqlClient;
using System.Configuration;
using System.ComponentModel;

namespace EYouBaoAdapter.Core
{
    public class CoreServiceImpl : CoreService
    {
        private const string COL_SORDER_TYPE = "TD001";

        private const string COL_SORDER_NO = "TD002";

        private const string COL_PORDER_NO = "TD202";

        private const string COL_PORDER_CUSTOMERCODE = "TD203";

        private const string COL_RECEIVER_NAME = "TD207";

        private const string COL_RECEIVER_POSTCODE = "TD208";

        private const string COL_RECEIVER_PHONE = "TD209";

        private const string COL_RECEIVER_MOBILE = "TD210";

        private const string COL_RECEIVER_COUNTRY = "TD211";

        private const string COL_RECEIVER_RPOVINCE = "TD212";

        private const string COL_RECEIVER_CITY = "TD213";

        private const string COL_RECEIVER_COUNTY = "TD214";

        private const string COL_RECEIVER_COMPANY = "TD216";

        private const string COL_RECEIVER_STREET = "TD217";

        private const string COL_RECEIVER_EMAIL = "TD218";

        private const string COL_ITEM_ENNAME = "TD005";

        private const string COL_ITEM_COUNT = "TD008";

        private const string COL_ITEM_UNIT = "TD010";

        private const string COL_ITEM_WEIGHT = "TD201";

        private const string COL_ITEM_DELCAREVALUE = "TD234";

        private const string COL_ITEM_ORIGIN = "TD219";

        private const string COL_ITEM_DESCRIPTION = "TD006";

        private const string COL_MAIL_NUM = "UDF04";

        public Pagination FindSystemOrderList(int pageNo, int pageSize)
        {
            DatabaseHandler handler = Utils.DatabaseHandler();
            int totalCount = GetSystemOrderCount(handler);
            Pagination pagination = new Pagination(pageNo, pageSize, totalCount);
            int minPageNo = 1;
            int maxPageNo = pagination.getTotalPage();
            pageNo = pageNo < minPageNo ? minPageNo : pageNo;
            pageNo = pageNo > maxPageNo ? maxPageNo : pageNo;
            pagination.PageNo = pageNo;

            if (totalCount == 0)
            {
                pagination.Records = new List<object>();
                return pagination;
            }

            int firstRecord = (pageNo - 1) * pageSize;
            string sql = String.Format("SELECT TOP {0} TC001, TC002 FROM COPTC LEFT JOIN CMSMQ ON TC001=MQ001 WHERE TC027='Y' AND CMSMQ.UDF01='Y' AND (TC001 + '$' + TC002 NOT IN (SELECT TOP {1} TC001 + '$' + TC002 FROM COPTC ORDER BY TC001, TC002)) AND EXISTS (SELECT UDF04 FROM COPTD WHERE TC001 = TD001 AND TC002 = TD002 AND TD016='N' AND TD008<>0 AND (UDF04 IS NULL OR UDF04 = '')) ORDER BY TC001, TC002", pageSize, firstRecord);
            DataSet dataSet = handler.Find(sql);
            DataTable table = dataSet.Tables[0];

            SystemOrder order;
            List<object> systemOrderList = new List<object>();

            foreach (DataRow row in table.Rows)
            {
                order = new SystemOrder();
                order.OrderType = Convert.ToString(row[0]);
                order.OrderNo = Convert.ToString(row[1]);
                systemOrderList.Add(order);
            }

            pagination.Records = systemOrderList;
            return pagination;
        }

        public List<Model.PlatformOrder> FindPlatformOrderList(string systemOrderType, string systemOrderNo)
        {
            DatabaseHandler handler = Utils.DatabaseHandler();
            string sql = String.Format("SELECT * FROM COPTD WHERE TD001 = '{0}' AND TD002 = '{1}' AND TD016='N' AND TD008<>0 AND  (UDF04 IS NULL OR UDF04 = '')  ORDER BY TD202", systemOrderType, systemOrderNo);
            DataSet dataSet = handler.Find(sql);
            DataTable table = dataSet.Tables[0];
            
            PlatformOrder order = null;
            string rowOrderNo;
            string currentOrderNo = null;
            List<PlatformOrder> platformOrderList = new List<PlatformOrder>();

            foreach (DataRow row in table.Rows)
            {
                rowOrderNo = Convert.ToString(row[COL_PORDER_NO]);

                // 正处理的订单为空或者正处理的订单单号不等于已读行的订单单号，说明新增加了一个订单
                if (null == currentOrderNo || !currentOrderNo.Equals(rowOrderNo))
                {
                    currentOrderNo = rowOrderNo;
                    order = ObtainDefaultPlatformOrder();
                    order.OrderCode = rowOrderNo;

                    if (null != row[COL_PORDER_CUSTOMERCODE])
                    {
                        order.CustomerCode = Convert.ToString(row[COL_PORDER_CUSTOMERCODE]);
                    }
                    
                    FillReceiver(order,row);

                    if (order.items == null)
                    {
                        order.items = new List<Item>();
                    }

                    platformOrderList.Add(order);
                }

                FillItem(order, row);
            }

            return platformOrderList;
        }

        public void ShipSystemOrder(SystemOrder systemOrder, BackgroundWorker worker, DoWorkEventArgs e)
        {
            List<PlatformOrder> platformOrderList = this.FindPlatformOrderList(systemOrder.OrderType, systemOrder.OrderNo);

            if (platformOrderList.Count == 0)
            {
                MessageBox.Show("该系统订单下不存在平台订单。");
                return;
            }

            int successCount = 0;

            for (int i = 0; i < platformOrderList.Count; i++)
            {
                PlatformOrder platformOrder = platformOrderList[i];

                try
                {
                    ShipPlatformOrder(platformOrder, systemOrder);
                    successCount++;
                    worker.ReportProgress((100 * i) / platformOrderList.Count);
                }
                catch (System.Exception ex)
                {
                    HandleShipException(systemOrder, platformOrder, ex);
                }
            }

            if (successCount == platformOrderList.Count)
            {
                e.Result = "订单发运成功。";
            }
            else
            {
                e.Result = String.Format("订单发运失败，平台订单共{0}个，发运成功{1}个，失败{2}个", platformOrderList.Count, successCount, platformOrderList.Count - successCount);
            }
        }

        private void ShipPlatformOrder(PlatformOrder order, SystemOrder systemOrder)
        {
            EYouBaoMetadata metadata = Utils.EYouBaoMetadata();
            string requestURL = String.Format("http://{0}:{1}{2}", metadata.Server, metadata.Port, metadata.ShipURI);
            HttpWebRequest request = (HttpWebRequest)WebRequest.Create(requestURL);
            request.Headers.Set("version", metadata.Version);
            request.Headers.Set("authenticate", metadata.Authenticate);
            request.Method = "POST";
            request.ContentType = "application/xml;charset=UTF-8";
            Encoding encoding = Encoding.GetEncoding("utf-8");
            byte[] postdata = encoding.GetBytes(Utils.GetXmlString(order));
            request.ContentLength = postdata.Length;
            Stream requestStream = request.GetRequestStream();
            requestStream.Write(postdata, 0, postdata.Length);
            requestStream.Close();
            HttpWebResponse response = (HttpWebResponse)request.GetResponse();
            StreamReader reader = new StreamReader(response.GetResponseStream(), Encoding.UTF8);
            XPathDocument doc = new XPathDocument(reader);
            XPathNavigator nav = doc.CreateNavigator();
            XPathNavigator node = nav.SelectSingleNode("/order/mailnum");

            if (null != node)
            {
                string mailnum = node.InnerXml;
                try
                {
                    DatabaseHandler handler = Utils.DatabaseHandler();
                    string sql = String.Format("UPDATE COPTD SET {0} = '{1}' WHERE {2} = '{3}' AND {4} = '{5}' AND {6} = '{7}' AND TD016='N' AND TD008<>0 AND  (UDF04 IS NULL OR UDF04 = '') ", COL_MAIL_NUM, mailnum, COL_SORDER_TYPE, systemOrder.OrderType, COL_SORDER_NO, systemOrder.OrderNo, COL_PORDER_NO, order.OrderCode);
                    int count = handler.Execute(sql);
                }
                catch (SqlException e)
                {
                    MessageBox.Show(String.Format("运单号入库失败，单别：{0}，单号：{1}，平台单号：{2}，生成的运单号：{3}，具体错误信息：\r\n{4}", systemOrder.OrderType, systemOrder.OrderNo, order.OrderCode, mailnum, e.Message), "错误");
                }
            }
            else
            {
                // 生成订单失败，数据错误
                node = nav.SelectSingleNode("/response/description");
                string error = node == null ? "生成运单失败。" : node.InnerXml;
                throw new EYouBaoAccessException(error);
            }
        }

        private void HandleShipException(SystemOrder systemOrder, PlatformOrder platformOrder, System.Exception e)
        {
            MessageBox.Show(String.Format("发运失败，单别：{0}，单号：{1}，平台单号：{2}，具体错误信息：\r\n{3}", systemOrder.OrderType, systemOrder.OrderNo, platformOrder.OrderCode, e.Message), "错误");
        }


        /** 查询系统订单总数量 */
        private int GetSystemOrderCount(DatabaseHandler handler)
        {
            DataSet dataSet = handler.Find("SELECT COUNT(TC001) FROM COPTC LEFT JOIN CMSMQ ON TC001=MQ001 WHERE TC027='Y' AND CMSMQ.UDF01='Y' AND EXISTS (SELECT UDF04 FROM COPTD WHERE TC001 = TD001 AND TC002 = TD002 AND TD016='N' AND TD008<>0 AND (UDF04 IS NULL OR UDF04 = ''))");
            DataTable table = dataSet.Tables[0];
            return Convert.ToInt32(table.Rows[0][0]);
        }

        /** 生成默认的平台订单，主要是接入到E邮宝时相关默认字段的填充 */
        private PlatformOrder ObtainDefaultPlatformOrder()
        {
            PlatformOrder order = new PlatformOrder();
            order.ClctType = ConfigurationManager.AppSettings["clct_type"];
            order.Pod = ConfigurationManager.AppSettings["pod"];
            order.Untread = ConfigurationManager.AppSettings["untread"];

            NameAddress sender = new NameAddress();
            sender.Name = ConfigurationManager.AppSettings["sender_name"];
            sender.PostCode = ConfigurationManager.AppSettings["sender_postcode"];
            sender.Phone = ConfigurationManager.AppSettings["sender_phone"];
            sender.Mobile = ConfigurationManager.AppSettings["sender_mobile"];
            sender.Country = ConfigurationManager.AppSettings["sender_country"];
            sender.Province = ConfigurationManager.AppSettings["sender_province"];
            sender.City = ConfigurationManager.AppSettings["sender_city"];
            sender.County = ConfigurationManager.AppSettings["sender_county"];
            sender.Company = ConfigurationManager.AppSettings["sender_company"];
            sender.Street = ConfigurationManager.AppSettings["sender_street"];
            sender.Email = ConfigurationManager.AppSettings["sender_email"];
            order.Sender = sender;

            NameAddress collect = new NameAddress();
            collect.Name = ConfigurationManager.AppSettings["collect_name"];
            collect.PostCode = ConfigurationManager.AppSettings["collect_postcode"];
            collect.Phone = ConfigurationManager.AppSettings["collect_phone"];
            collect.Mobile = ConfigurationManager.AppSettings["collect_mobile"];
            collect.Country = ConfigurationManager.AppSettings["collect_country"];
            collect.Province = ConfigurationManager.AppSettings["collect_province"];
            collect.City = ConfigurationManager.AppSettings["collect_city"];
            collect.County = ConfigurationManager.AppSettings["collect_county"];
            collect.Company = ConfigurationManager.AppSettings["collect_company"];
            collect.Street = ConfigurationManager.AppSettings["collect_street"];
            collect.Email = ConfigurationManager.AppSettings["collect_email"];
            order.Collect = collect;
            return order;
        }

        /** 根据数据库中查询出来的记录填充平台订单的收件人名址信息 */
        private void FillReceiver(PlatformOrder order, DataRow row)
        {
            NameAddress receiver = new NameAddress();
            receiver.Name = Convert.ToString(row[COL_RECEIVER_NAME]);
            receiver.PostCode = Convert.ToString(row[COL_RECEIVER_POSTCODE]);
            receiver.Country = Convert.ToString(row[COL_RECEIVER_COUNTRY]);
            receiver.Province = Convert.ToString(row[COL_RECEIVER_RPOVINCE]);
            receiver.City = Convert.ToString(row[COL_RECEIVER_CITY]);
            receiver.Street = Convert.ToString(row[COL_RECEIVER_STREET]);

            if (null != row[COL_RECEIVER_PHONE])
            {
                receiver.Phone = Convert.ToString(row[COL_RECEIVER_PHONE]);
            }

            if (null != row[COL_RECEIVER_MOBILE])
            {
                receiver.Mobile = Convert.ToString(row[COL_RECEIVER_MOBILE]);
            }

            if (null != row[COL_RECEIVER_COUNTY])
            {
                receiver.County = Convert.ToString(row[COL_RECEIVER_COUNTY]);
            }

            if (null != row[COL_RECEIVER_COMPANY])
            {
                receiver.Company = Convert.ToString(row[COL_RECEIVER_COMPANY]);
            }

            if (null != row[COL_RECEIVER_EMAIL])
            {
                receiver.Email = Convert.ToString(row[COL_RECEIVER_EMAIL]);
            }

            order.Receiver = receiver;
        }

        /** 根据数据库中查询出来的记录填充平台订单的货品信息 */
        private void FillItem(PlatformOrder order, DataRow row)
        {
            Item item = new Item();
            item.ENName = Convert.ToString(row[COL_ITEM_ENNAME]);
            item.Count = Convert.ToString(Convert.ToInt16(row[COL_ITEM_COUNT]));
            item.Weight = Convert.ToString(row[COL_ITEM_WEIGHT]);
            item.DelcareValue = Convert.ToString(row[COL_ITEM_DELCAREVALUE]);
            item.Origin = Convert.ToString(row[COL_ITEM_ORIGIN]);

            if (null != row[COL_ITEM_UNIT])
            {
                item.Unit = Convert.ToString(row[COL_ITEM_UNIT]);
            }

            if (null != row[COL_ITEM_DESCRIPTION])
            {
                item.Description = Convert.ToString(row[COL_ITEM_DESCRIPTION]);
            }

            order.items.Add(item);
        }
    }
}
