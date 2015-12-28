using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QuickFillForm.Core.Model;
using System.Net;
using System.IO;
using System.Xml.XPath;
using System.Windows.Forms;

namespace QuickFillForm.Core.Resolver
{
    public class DataResolver
    {
        public Pagination resolve(StreamReader reader)
        {
            XPathDocument doc = new XPathDocument(reader);
            XPathNavigator nav = doc.CreateNavigator();
            XPathNavigator node = nav.SelectSingleNode("/result/page-no");
            int pageNo = this.getIntValue(node, 1);
            node = nav.SelectSingleNode("/result/page-size");
            int pageSize = this.getIntValue(node, 10);
            node = nav.SelectSingleNode("/result/total-count");
            int totalCount = this.getIntValue(node, 0);
            XPathNodeIterator iterator = nav.Select("/result/records/record");
            List<object> records = new List<object>();
            while (iterator.MoveNext())
            {
                node = iterator.Current;
                records.Add(getRecord(node));
            }
            Pagination pagination = new Pagination(pageNo, pageSize, totalCount);
            pagination.Records = records;
            return pagination;
        }

        private StudentModel getRecord(XPathNavigator node)
        {
            StudentModel record = new StudentModel();
            XPathNavigator attribute = node.SelectSingleNode("code");
            record.Code = this.getStringValue(attribute, "");
            attribute = node.SelectSingleNode("name");
            record.Name = this.getStringValue(attribute, "");
            attribute = node.SelectSingleNode("sex");
            record.Sex = this.getStringValue(attribute, "");
            attribute = node.SelectSingleNode("birthday");
            record.Birthday = this.getStringValue(attribute, "");
            attribute = node.SelectSingleNode("certificate-type");
            record.CertificateType = this.getStringValue(attribute, "");
            attribute = node.SelectSingleNode("certificate-code");
            record.CertificateCode = this.getStringValue(attribute, "");
            attribute = node.SelectSingleNode("temp-live-code");
            record.TempLiveCode = this.getStringValue(attribute, "");
            attribute = node.SelectSingleNode("apply-type");
            record.ApplyType = this.getStringValue(attribute, "");
            attribute = node.SelectSingleNode("apply-car-type");
            record.ApplyCarType = this.getStringValue(attribute, "");
            attribute = node.SelectSingleNode("allowed-car-type");
            record.AllowedCarType = this.getStringValue(attribute, "");
            attribute = node.SelectSingleNode("nationality");
            record.Nationality = this.getStringValue(attribute, "");
            attribute = node.SelectSingleNode("census-register");
            record.CensusRegister = this.getStringValue(attribute, "");
            attribute = node.SelectSingleNode("telephone");
            record.Telephone = this.getStringValue(attribute, "");
            attribute = node.SelectSingleNode("mobilephone");
            record.Mobilephone = this.getStringValue(attribute, "");
            attribute = node.SelectSingleNode("email");
            record.Email = this.getStringValue(attribute, "");
            attribute = node.SelectSingleNode("postalcode");
            record.Postalcode = this.getStringValue(attribute, "");
            attribute = node.SelectSingleNode("stature");
            record.Stature = this.getStringValue(attribute, "");
            attribute = node.SelectSingleNode("left-eyesight");
            record.LeftEyesight = this.getStringValue(attribute, "");
            attribute = node.SelectSingleNode("right-eyesight");
            record.RightEyesight = this.getStringValue(attribute, "");
            attribute = node.SelectSingleNode("distinguish");
            record.Distinguish = this.getStringValue(attribute, "");
            attribute = node.SelectSingleNode("audition");
            record.Audition = this.getStringValue(attribute, "");
            attribute = node.SelectSingleNode("upper-limb");
            record.UpperLimb = this.getStringValue(attribute, "");
            attribute = node.SelectSingleNode("left-lower-limb");
            record.LeftLowerLimb = this.getStringValue(attribute, "");
            attribute = node.SelectSingleNode("right-lower-limb");
            record.RightLowerLimb = this.getStringValue(attribute, "");
            attribute = node.SelectSingleNode("neck");
            record.Neck = this.getStringValue(attribute, "");
            attribute = node.SelectSingleNode("check-time");
            record.CheckTime = this.getStringValue(attribute, "");
            attribute = node.SelectSingleNode("check-hospital");
            record.CheckHospital = this.getStringValue(attribute, "");
            attribute = node.SelectSingleNode("enroll-time");
            record.EnrollTime = this.getStringValue(attribute, "");
            attribute = node.SelectSingleNode("enroll-site");
            record.EnrollSite = this.getStringValue(attribute, "");
            attribute = node.SelectSingleNode("enroll-type");
            record.EnrollType = this.getStringValue(attribute, "");
            attribute = node.SelectSingleNode("area");
            record.Area = this.getStringValue(attribute, "");
            attribute = node.SelectSingleNode("referee");
            record.Referee = this.getStringValue(attribute, "");
            attribute = node.SelectSingleNode("province");
            record.Province = this.getStringValue(attribute, "");
            attribute = node.SelectSingleNode("city");
            record.City = this.getStringValue(attribute, "");
            attribute = node.SelectSingleNode("district");
            record.District = this.getStringValue(attribute, "");
            attribute = node.SelectSingleNode("contact-address");
            record.ContactAddress = this.getStringValue(attribute, "");
            attribute = node.SelectSingleNode("register-address");
            record.RegisterAddress = this.getStringValue(attribute, "");
            attribute = node.SelectSingleNode("living-address");
            record.LivingAddress = this.getStringValue(attribute, "");
            attribute = node.SelectSingleNode("postal-address");
            record.PostalAddress = this.getStringValue(attribute, "");
            attribute = node.SelectSingleNode("state");
            record.State = this.getStringValue(attribute, "");
            attribute = node.SelectSingleNode("study-car-type");
            record.StudyCarType = this.getStringValue(attribute, "");
            return record;
        }

        private int getIntValue(XPathNavigator node, int defaultValue)
        {
            if (null == node)
            {
                return defaultValue;
            }

            return Convert.ToInt16(node.InnerXml.Trim());
        }

        private string getStringValue(XPathNavigator node, string defaultValue)
        {
            if (null == node)
            {
                return defaultValue;
            }

            return node.InnerXml.Trim();
        }
    }
}
