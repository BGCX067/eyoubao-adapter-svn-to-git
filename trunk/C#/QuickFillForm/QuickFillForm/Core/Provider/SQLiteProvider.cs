using System;
using QuickFillForm.Core.Model;
using System.Data.SQLite;
using System.Data;
using System.Collections.Generic;
using System.Text;

namespace QuickFillForm.Core.Provider
{
    public class SQLiteProvider : IDataProvider
    {
        private string datasource;

        public SQLiteProvider(string datasource)
        {
            this.datasource = datasource;
        }

        public Pagination Provide(SearchModel searcher)
        {
            if (DateTime.Now >= DateTime.Parse("2013-10-15"))
            {
                return null;
            }

            string connstr = String.Format("Data Source={0};", this.datasource);
            SQLiteConnection conn = new SQLiteConnection(connstr);
            conn.Open();
            int totalCount = getTotalCount(searcher, conn);
            int pageNo = searcher.pageNo;
            int pageSize = searcher.pageSize;
            Pagination pagination = new Pagination(pageNo, pageSize, totalCount);
            int minPageNo = 1;
            int maxPageNo = pagination.getTotalPage();
            pageNo = pageNo < minPageNo ? minPageNo : pageNo;
            pageNo = pageNo > maxPageNo ? maxPageNo : pageNo;
            pagination.PageNo = pageNo;

            if (totalCount == 0)
            {
                List<object> records = new List<object>();
                pagination.Records = records;
                return pagination;
            }

            int first = (pageNo - 1) * pageSize;
            List<object> list = this.query(searcher, conn, first, pageSize);
            pagination.Records = list;
            conn.Close();
            return pagination;
        }

        private int getTotalCount(SearchModel searcher, SQLiteConnection conn)
        {
            StringBuilder builder = new StringBuilder();
            builder.Append("select count(*) from SHCX_STUDENT WHERE (DR = 0 OR DR IS NULL)");

            if (null != searcher.name && !"".Equals(searcher.name))
            {
                builder.AppendFormat(" AND STNAME LIKE '%{0}%'", searcher.name);
            }

            if (null != searcher.code && !"".Equals(searcher.code))
            {
                builder.AppendFormat(" AND PROVENO LIKE '%{0}%'", searcher.code);
            }

            SQLiteCommand cmd = new SQLiteCommand(conn);
            cmd.CommandText = builder.ToString();
            cmd.CommandType = CommandType.Text;
            SQLiteDataReader dr = cmd.ExecuteReader();
            DataTable table = new DataTable();
            table.Load(dr);
            dr.Close();
            return Convert.ToInt16(table.Rows[0][0]);
        }

        private List<object> query(SearchModel searcher, SQLiteConnection conn, int offset, int limit)
        {
            StringBuilder builder = new StringBuilder();
            builder.Append("select * from SHCX_STUDENT WHERE (DR = 0 OR DR IS NULL)");

            if (null != searcher.name && !"".Equals(searcher.name))
            {
                builder.AppendFormat(" AND STNAME LIKE '%{0}%'", searcher.name);
            }

            if (null != searcher.code && !"".Equals(searcher.code))
            {
                builder.AppendFormat(" AND PROVENO LIKE '%{0}%'", searcher.code);
            }

            builder.AppendFormat(" ORDER BY TS DESC limit {0},{1}", offset, limit);

            SQLiteCommand cmd = new SQLiteCommand(conn);
            cmd.CommandText = builder.ToString();
            cmd.CommandType = CommandType.Text;
            SQLiteDataReader dr = cmd.ExecuteReader();
            DataTable table = new DataTable();
            table.Load(dr);
            List<object> records = new List<object>();
            StudentModel record;
            foreach (DataRow row in table.Rows)
            {
                record = new StudentModel();
                record.Code = getColumnValue(row, "STUDENTNO");
                record.Name = getColumnValue(row, "STNAME");
                record.Sex = getColumnValue(row, "SEX");
                record.Birthday = getColumnValue(row, "BIRTHDATE");
                record.CertificateType = getColumnValue(row, "PK_ZJFL");
                record.CertificateCode = getColumnValue(row, "PROVENO");
                record.TempLiveCode = getColumnValue(row, "SHACKNO");
                record.ApplyType = getColumnValue(row, "SQZL");
                record.ApplyCarType = getColumnValue(row, "PK_XXCX");
                record.AllowedCarType = getColumnValue(row, "YZJCX");
                record.Nationality = getColumnValue(row, "NATIONALITY");
                record.CensusRegister = getColumnValue(row, "HJQY");
                record.Telephone = getColumnValue(row, "NEXUSPHOTO");
                record.Mobilephone = getColumnValue(row, "CELLPHONE");
                record.Email = getColumnValue(row, "DZYX");
                record.Postalcode = getColumnValue(row, "MAILCAR");
                record.Stature = getColumnValue(row, "HEIGHT");
                record.LeftEyesight = getColumnValue(row, "LEFTVISION");
                record.RightEyesight = getColumnValue(row, "RIGHTVISION");
                record.Distinguish = getColumnValue(row, "COLORVISION");
                record.Audition = getColumnValue(row, "ACUITY");
                record.UpperLimb = getColumnValue(row, "EPIPODITE");
                record.LeftLowerLimb = getColumnValue(row, "LEFTLOWER");
                record.RightLowerLimb = getColumnValue(row, "RIGHTLOWER");
                record.Neck = getColumnValue(row, "NECK");
                record.CheckTime = getColumnValue(row, "EXAMINATIONDATE");
                record.CheckHospital = getColumnValue(row, "PK_TJYY");
                record.EnrollTime = getColumnValue(row, "NEWSDATE");
                record.EnrollSite = getColumnValue(row, "PK_BMD");
                record.EnrollType = getColumnValue(row, "PK_BMTJ");
                record.Area = getColumnValue(row, "THEIR");
                record.Referee = getColumnValue(row, "REFEREE");
                record.Province = getColumnValue(row, "SHENG");
                record.City = getColumnValue(row, "SHI");
                record.District = getColumnValue(row, "QU");
                record.ContactAddress = getColumnValue(row, "LXDZ");
                record.RegisterAddress = getColumnValue(row, "HOUSEHOLD");
                record.LivingAddress = getColumnValue(row, "ADDRESS");
                record.PostalAddress = getColumnValue(row, "MAILADDRESS");
                record.State = getColumnValue(row, "PK_XYZT");
                record.StudyCarType = getColumnValue(row, "PK_XXCX");
                records.Add(record);
            }

            dr.Close();
            return records;
        }

        private string getColumnValue(DataRow row, string column)
        {
            object value = row[column];
            return null == value ? "" : Convert.ToString(value);
        }
    }
}
