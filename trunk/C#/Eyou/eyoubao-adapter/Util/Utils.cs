
using System;
using System.Collections.Generic;
using System.Text;
using EYouBaoAdapter.Model;
using System.Xml;
using System.Data;
using EYouBaoAdapter.Exception;
using EYouBaoAdapter.Core;
using System.Configuration;

namespace EYouBaoAdapter.Util
{
    public class Utils
    {
        private static DatabaseHandler dbhandler;

        private static EYouBaoMetadata metadata;

        static Utils()
        {
            dbhandler = new DatabaseHandler(ConfigurationManager.AppSettings["db"]);
            metadata = new EYouBaoMetadata();
            metadata.Server = ConfigurationManager.AppSettings["ems_server"];
            metadata.Port = ConfigurationManager.AppSettings["ems_port"];
            metadata.Version = ConfigurationManager.AppSettings["ems_version"];
            metadata.Authenticate = ConfigurationManager.AppSettings["ems_authenticate"];
            metadata.ShipURI = ConfigurationManager.AppSettings["ems_ship_uri"];
        }

        public static DatabaseHandler DatabaseHandler()
        {
            return dbhandler;
        }

        public static EYouBaoMetadata EYouBaoMetadata()
        {
            return metadata;
        }

        public static string GetXmlString(PlatformOrder order)
        {
            XmlDocument document = new XmlDocument();
            document.AppendChild(document.CreateXmlDeclaration("1.0", "UTF-8", null));
            XmlElement ordersElement = document.CreateElement("orders");
            XmlAttribute attr = document.CreateAttribute("xmlns:xsi");
            attr.Value = "http://www.w3.org/2001/XMLSchema-instance";
            ordersElement.Attributes.Append(attr);
            document.AppendChild(ordersElement);
            ordersElement.AppendChild(ToXmlElement(document,order));
            XmlWriterSettings settings = new XmlWriterSettings();
            settings.Indent = true;
            StringBuilder output = new StringBuilder();
            XmlWriter writer = XmlWriter.Create(output, settings);
            document.WriteContentTo(writer);
            writer.Flush();
            writer.Close();
            return output.ToString();
        }

        private static XmlElement ToXmlElement(XmlDocument document, PlatformOrder order)
        {
            ValidateOrderState(order);

            XmlElement orderElement = document.CreateElement("order");
            XmlElement orderIdElement = document.CreateElement("orderid");
            orderIdElement.InnerText = order.OrderCode;
            orderElement.AppendChild(orderIdElement);

            // 客户编号非必填项
            if (order.CustomerCode != null && !"".Equals(order.CustomerCode))
            {
                XmlElement customerCodeElement = document.CreateElement("customercode");
                customerCodeElement.InnerText = order.CustomerCode;
                orderElement.AppendChild(customerCodeElement);
            }
            
            XmlElement clctTypeElement = document.CreateElement("clcttype");
            clctTypeElement.InnerText = order.ClctType;
            orderElement.AppendChild(clctTypeElement);
            XmlElement podElement = document.CreateElement("pod");
            podElement.InnerText = order.Pod;
            orderElement.AppendChild(podElement);
            XmlElement untreadElement = document.CreateElement("untread");
            untreadElement.InnerText = order.Untread;
            orderElement.AppendChild(untreadElement);

            // 体积重量是非必填项
            if (null != order.VolWeight && !"".Equals(order.VolWeight))
            {
                XmlElement volWeightElement = document.CreateElement("volweight");
                volWeightElement.InnerText = order.VolWeight;
                orderElement.AppendChild(volWeightElement);
            }

            // 起始预约时间是非必填项
            if (null != order.StartDate && !"".Equals(order.StartDate))
            {
                XmlElement startDateElement = document.CreateElement("startdate");
                startDateElement.InnerText = order.StartDate;
                orderElement.AppendChild(startDateElement);
            }

            // 终止预约时间是非必填项
            if (null != order.EndDate && !"".Equals(order.EndDate))
            {
                XmlElement endDateElement = document.CreateElement("enddate");
                endDateElement.InnerText = order.EndDate;
                orderElement.AppendChild(endDateElement);
            }

            // 备注是非必填项
            if (null != order.Remark && !"".Equals(order.Remark))
            {
                XmlElement remarkElement = document.CreateElement("remark");
                remarkElement.InnerText = order.Remark;
                orderElement.AppendChild(remarkElement);
            }

            // 客户自定义信息1是非必填项
            if (null != order.Sku1 && !"".Equals(order.Sku1))
            {
                XmlElement sku1Element = document.CreateElement("sku1");
                sku1Element.InnerText = order.Sku1;
                orderElement.AppendChild(sku1Element);
            }

            // 客户自定义信息2是非必填项
            if (null != order.Sku2 && !"".Equals(order.Sku2))
            {
                XmlElement sku2Element = document.CreateElement("sku2");
                sku2Element.InnerText = order.Sku2;
                orderElement.AppendChild(sku2Element);
            }

            // 订单条码是非必填项
            if (null != order.BarCode && !"".Equals(order.BarCode))
            {
                XmlElement barCodeElement = document.CreateElement("barcode");
                barCodeElement.InnerText = order.BarCode;
                orderElement.AppendChild(barCodeElement);
            }

            // 打印格式是非必填项
            if (null != order.PrintCode && !"".Equals(order.PrintCode))
            {
                XmlElement printCodeElement = document.CreateElement("printcode");
                printCodeElement.InnerText = order.PrintCode;
                orderElement.AppendChild(printCodeElement);
            }

            orderElement.AppendChild(ToSenderXmlElement(document,order.Sender));
            orderElement.AppendChild(ToReceiverXmlElement(document,order.Receiver));
            orderElement.AppendChild(ToCollectXmlElement(document,order.Collect));
            orderElement.AppendChild(ToItemsElement(document, order.items));
            return orderElement;
        }

        private static XmlElement ToSenderXmlElement(XmlDocument document, NameAddress nameAddress)
        {
            XmlElement senderElement = document.CreateElement("sender");
            XmlElement nameElement = document.CreateElement("name");
            nameElement.InnerText = nameAddress.Name;
            senderElement.AppendChild(nameElement);
            XmlElement postCodeElement = document.CreateElement("postcode");
            postCodeElement.InnerText = nameAddress.PostCode;
            senderElement.AppendChild(postCodeElement);

            if (null != nameAddress.Phone && !"".Equals(nameAddress.Phone))
            {
                XmlElement phoneElement = document.CreateElement("phone");
                phoneElement.InnerText = nameAddress.Phone;
                senderElement.AppendChild(phoneElement);
            }

            if (null != nameAddress.Mobile && !"".Equals(nameAddress.Mobile))
            {
                XmlElement mobileElement = document.CreateElement("mobile");
                mobileElement.InnerText = nameAddress.Mobile;
                senderElement.AppendChild(mobileElement);
            }

            XmlElement countryElement = document.CreateElement("country");
            countryElement.InnerText = nameAddress.Country;
            senderElement.AppendChild(countryElement);

            XmlElement provinceElement = document.CreateElement("province");
            provinceElement.InnerText = nameAddress.Province;
            senderElement.AppendChild(provinceElement);


            XmlElement cityElement = document.CreateElement("city");
            cityElement.InnerText = nameAddress.City;
            senderElement.AppendChild(cityElement);

            XmlElement countyElement = document.CreateElement("county");
            countyElement.InnerText = nameAddress.County;
            senderElement.AppendChild(countyElement);

            if (null != nameAddress.Company && !"".Equals(nameAddress.Company))
            {
                XmlElement companyElement = document.CreateElement("company");
                companyElement.InnerText = nameAddress.Company;
                senderElement.AppendChild(companyElement);
            }

            XmlElement streetElement = document.CreateElement("street");
            streetElement.InnerText = nameAddress.Street;
            senderElement.AppendChild(streetElement);

            if (null != nameAddress.Email && !"".Equals(nameAddress.Email))
            {
                XmlElement emailElement = document.CreateElement("email");
                emailElement.InnerText = nameAddress.Email;
                senderElement.AppendChild(emailElement);
            }

            return senderElement;
        }

        private static XmlElement ToReceiverXmlElement(XmlDocument document, NameAddress nameAddress)
        {
            XmlElement receiverElement = document.CreateElement("receiver");
            XmlElement nameElement = document.CreateElement("name");
            nameElement.InnerText = nameAddress.Name;
            receiverElement.AppendChild(nameElement);
            XmlElement postCodeElement = document.CreateElement("postcode");
            postCodeElement.InnerText = nameAddress.PostCode;
            receiverElement.AppendChild(postCodeElement);

            if (null != nameAddress.Phone && !"".Equals(nameAddress.Phone))
            {
                XmlElement phoneElement = document.CreateElement("phone");
                phoneElement.InnerText = nameAddress.Phone;
                receiverElement.AppendChild(phoneElement);
            }

            if (null != nameAddress.Mobile && !"".Equals(nameAddress.Mobile))
            {
                XmlElement mobileElement = document.CreateElement("mobile");
                mobileElement.InnerText = nameAddress.Mobile;
                receiverElement.AppendChild(mobileElement);
            }

            XmlElement countryElement = document.CreateElement("country");
            countryElement.InnerText = nameAddress.Country;
            receiverElement.AppendChild(countryElement);

            XmlElement provinceElement = document.CreateElement("province");
            provinceElement.InnerText = nameAddress.Province;
            receiverElement.AppendChild(provinceElement);


            XmlElement cityElement = document.CreateElement("city");
            cityElement.InnerText = nameAddress.City;
            receiverElement.AppendChild(cityElement);

            if (null != nameAddress.County && !"".Equals(nameAddress.County))
            {
                XmlElement countyElement = document.CreateElement("county");
                countyElement.InnerText = nameAddress.County;
                receiverElement.AppendChild(countyElement);
            }

            if (null != nameAddress.Company && !"".Equals(nameAddress.Company))
            {
                XmlElement companyElement = document.CreateElement("company");
                companyElement.InnerText = nameAddress.Company;
                receiverElement.AppendChild(companyElement);
            }

            XmlElement streetElement = document.CreateElement("street");
            streetElement.InnerText = nameAddress.Street;
            receiverElement.AppendChild(streetElement);

            if (null != nameAddress.Email && !"".Equals(nameAddress.Email))
            {
                XmlElement emailElement = document.CreateElement("email");
                emailElement.InnerText = nameAddress.Email;
                receiverElement.AppendChild(emailElement);
            }

            return receiverElement;
        }

        private static XmlElement ToCollectXmlElement(XmlDocument document, NameAddress nameAddress)
        {
            XmlElement collectElement = document.CreateElement("collect");
            XmlElement nameElement = document.CreateElement("name");
            nameElement.InnerText = nameAddress.Name;
            collectElement.AppendChild(nameElement);
            XmlElement postCodeElement = document.CreateElement("postcode");
            postCodeElement.InnerText = nameAddress.PostCode;
            collectElement.AppendChild(postCodeElement);

            if (null != nameAddress.Phone && !"".Equals(nameAddress.Phone))
            {
                XmlElement phoneElement = document.CreateElement("phone");
                phoneElement.InnerText = nameAddress.Phone;
                collectElement.AppendChild(phoneElement);
            }

            if (null != nameAddress.Mobile && !"".Equals(nameAddress.Mobile))
            {
                XmlElement mobileElement = document.CreateElement("mobile");
                mobileElement.InnerText = nameAddress.Mobile;
                collectElement.AppendChild(mobileElement);
            }

            XmlElement countryElement = document.CreateElement("country");
            countryElement.InnerText = nameAddress.Country;
            collectElement.AppendChild(countryElement);

            XmlElement provinceElement = document.CreateElement("province");
            provinceElement.InnerText = nameAddress.Province;
            collectElement.AppendChild(provinceElement);


            XmlElement cityElement = document.CreateElement("city");
            cityElement.InnerText = nameAddress.City;
            collectElement.AppendChild(cityElement);

            XmlElement countyElement = document.CreateElement("county");
            countyElement.InnerText = nameAddress.County;
            collectElement.AppendChild(countyElement);

            if (null != nameAddress.Company && !"".Equals(nameAddress.Company))
            {
                XmlElement companyElement = document.CreateElement("company");
                companyElement.InnerText = nameAddress.Company;
                collectElement.AppendChild(companyElement);
            }

            XmlElement streetElement = document.CreateElement("street");
            streetElement.InnerText = nameAddress.Street;
            collectElement.AppendChild(streetElement);

            if (null != nameAddress.Email && !"".Equals(nameAddress.Email))
            {
                XmlElement emailElement = document.CreateElement("email");
                emailElement.InnerText = nameAddress.Email;
                collectElement.AppendChild(emailElement);
            }

            return collectElement;
        }

        private static XmlElement ToItemsElement(XmlDocument document, List<Item> items)
        {
            XmlElement itemsElement = document.CreateElement("items");

            Item item;

            for (int i = 0; i < items.Count; i++)
            {
                item = items[i];

                XmlElement itemElement = document.CreateElement("item");
                itemsElement.AppendChild(itemElement);

                if (null != item.CNName && !"".Equals(item.CNName))
                {
                    XmlElement cnnameElement = document.CreateElement("cnname");
                    cnnameElement.InnerText = item.CNName;
                    itemElement.AppendChild(cnnameElement);
                }

                if (null != item.ENName && !"".Equals(item.ENName))
                {
                    XmlElement ennameElement = document.CreateElement("enname");
                    ennameElement.InnerText = item.ENName;
                    itemElement.AppendChild(ennameElement);
                }
                else
                {
                    throw new InvalidOrderException("货品英文名称不能为空");
                }

                if (null != item.Count && !"".Equals(item.Count))
                {
                    XmlElement countElement = document.CreateElement("count");
                    countElement.InnerText = item.Count;
                    itemElement.AppendChild(countElement);
                }
                else
                {
                    throw new InvalidOrderException("货品数量不能为空");
                }

                if (null != item.Unit && !"".Equals(item.Unit))
                {
                    XmlElement unitElement = document.CreateElement("unit");
                    unitElement.InnerText = item.Unit;
                    itemElement.AppendChild(unitElement);
                }

                if (null != item.Weight && !"".Equals(item.Weight))
                {
                    XmlElement weightElement = document.CreateElement("weight");
                    weightElement.InnerText = item.Weight;
                    itemElement.AppendChild(weightElement);
                }
                else
                {
                    throw new InvalidOrderException("货品重量不能为空");
                }

                if (null != item.DelcareValue && !"".Equals(item.DelcareValue))
                {
                    XmlElement delcareValueElement = document.CreateElement("delcarevalue");
                    delcareValueElement.InnerText = item.DelcareValue;
                    itemElement.AppendChild(delcareValueElement);
                }
                else
                {
                    throw new InvalidOrderException("货品报关价格不能为空");
                }

                if (null != item.Origin && !"".Equals(item.Origin))
                {
                    XmlElement originElement = document.CreateElement("origin");
                    originElement.InnerText = item.Origin;
                    itemElement.AppendChild(originElement);
                }
                else
                {
                    throw new InvalidOrderException("货品原寄地不能为空");
                }

                if (null != item.Description && !"".Equals(item.Description))
                {
                    XmlElement descriptionElement = document.CreateElement("description");
                    descriptionElement.InnerText = item.Description;
                    itemElement.AppendChild(descriptionElement);
                }
            }

            return itemsElement;
        }

        private static void ValidateOrderState(PlatformOrder order)
        {
            if (order.OrderCode == null || "".Equals(order.OrderCode))
            {
                throw new InvalidOrderException("订单单号字段不能为空。");
            }

            if (order.ClctType == null || "".Equals(order.ClctType))
            {
                throw new InvalidOrderException("揽收类型字段不能为空。");
            }

            if (order.Untread == null || "".Equals(order.Untread))
            {
                throw new InvalidOrderException("退回类型字段不能为空。");
            }

            if (order.Pod == null || "".Equals(order.Pod))
            {
                throw new InvalidOrderException("是否电子签收字段不能为空。");
            }

            if (order.Sender == null)
            {
                throw new InvalidOrderException("寄件人名址不能为空。");
            }

            if (order.Sender.Name == null || "".Equals(order.Sender.Name))
            {
                throw new InvalidOrderException("寄件人姓名字段不能为空。");
            }

            if (order.Sender.PostCode == null || "".Equals(order.Sender.PostCode))
            {
                throw new InvalidOrderException("寄件人邮编字段不能为空。");
            }

            if (order.Sender.Country == null || "".Equals(order.Sender.Country))
            {
                throw new InvalidOrderException("寄件人国家字段不能为空");
            }

            if (order.Sender.Province == null || "".Equals(order.Sender.Province))
            {
                throw new InvalidOrderException("寄件人省份字段不能为空");
            }

            if (order.Sender.City == null || "".Equals(order.Sender.City))
            {
                throw new InvalidOrderException("寄件人城市字段不能为空");
            }

            if (order.Sender.County == null || "".Equals(order.Sender.County))
            {
                throw new InvalidOrderException("寄件人区县字段不能为空");
            }

            if (order.Sender.Street == null || "".Equals(order.Sender.Street))
            {
                throw new InvalidOrderException("寄件人街道字段不能为空");
            }

            if (order.Receiver == null)
            { 
                throw new InvalidOrderException("收件人名址不能为空。");
            }

            if (order.Receiver.Name == null || "".Equals(order.Receiver.Name))
            {
                throw new InvalidOrderException("收件人姓名字段不能为空。");
            }

            if (order.Receiver.PostCode == null || "".Equals(order.Receiver.PostCode))
            {
                throw new InvalidOrderException("收件人邮编字段不能为空。");
            }

            if (order.Receiver.Country == null || "".Equals(order.Receiver.Country))
            {
                throw new InvalidOrderException("收件人国家字段不能为空");
            }

            if (order.Receiver.Province == null || "".Equals(order.Receiver.Province))
            {
                throw new InvalidOrderException("收件人省份字段不能为空");
            }

            if (order.Receiver.City == null || "".Equals(order.Receiver.City))
            {
                throw new InvalidOrderException("收件人城市字段不能为空");
            }

            if (order.Receiver.Street == null || "".Equals(order.Receiver.Street))
            {
                throw new InvalidOrderException("收件人街道字段不能为空");
            }

            if (null == order.Collect)
            {
                throw new InvalidOrderException("Collet名址不能为空。");
            }

            if (order.Collect.Name == null || "".Equals(order.Collect.Name))
            {
                throw new InvalidOrderException("Collet姓名字段不能为空。");
            }

            if (order.Collect.PostCode == null || "".Equals(order.Collect.PostCode))
            {
                throw new InvalidOrderException("Collet邮编字段不能为空。");
            }

            if (order.Collect.Country == null || "".Equals(order.Collect.Country))
            {
                throw new InvalidOrderException("Collet国家字段不能为空");
            }

            if (order.Collect.Province == null || "".Equals(order.Collect.Province))
            {
                throw new InvalidOrderException("Collet省份字段不能为空");
            }

            if (order.Collect.City == null || "".Equals(order.Collect.City))
            {
                throw new InvalidOrderException("Collet城市字段不能为空");
            }

            if (order.Collect.County == null || "".Equals(order.Collect.County))
            {
                throw new InvalidOrderException("Collet区县字段不能为空");
            }

            if (order.Collect.Street == null || "".Equals(order.Collect.Street))
            {
                throw new InvalidOrderException("Collet街道字段不能为空");
            }

            if (order.items == null || order.items.Count == 0)
            {
                throw new InvalidOrderException("订单货品不能为空");
            }
        }
    }
}
