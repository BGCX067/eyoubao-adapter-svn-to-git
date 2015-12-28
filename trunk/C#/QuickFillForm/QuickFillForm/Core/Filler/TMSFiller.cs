using System;
using mshtml;
using QuickFillForm.Core.Model;
using QuickFillForm.Core.Util;
using QuickFillForm.Core.Converter;
using System.Windows.Forms;
using System.Threading;
using System.Timers;

namespace QuickFillForm.Core.Filler
{
    public class TMSFiller : IFiller
    {
        private HTMLDocument document;

        public TMSFiller(HTMLDocument document)
        {
            this.document = document;
        }

        public void Fill(object data)
        {
            StudentModel model = (StudentModel)data;
            TMSConverter converter = TMSConverter.GetInstance();
            FillUtil.FillText(GetDocument(), "txtXueyh", converter.Convert("CODE", model.Code));
            FillUtil.FillText(GetDocument(), "txtXingming", converter.Convert("NAME", model.Name));
            FillUtil.FillSelect(GetDocument(), "drpXingbie", model.Sex, converter.Convert("SEX", model.Sex));
            FillUtil.FillText(GetDocument(), "txtChusrq", converter.Convert("BIRTHDAY", model.Birthday));
            FillUtil.FillSelect(GetDocument(), "drpZhengjzl", model.CertificateType, converter.Convert("CERTIFICATE_TYPE", model.CertificateType));
            FillUtil.FillText(GetDocument(), "txtShenfhm", converter.Convert("CERTIFICATE_CODE", model.CertificateCode));
            FillUtil.FillText(GetDocument(), "txtZanzzhm", converter.Convert("TEMP_LIVE_CODE", model.TempLiveCode));
            FillUtil.FillSelect(GetDocument(), "drpShenqzl", model.ApplyType, converter.Convert("APPLY_TYPE", model.ApplyType));
            FillUtil.FillSelect(GetDocument(), "drpShenqcx", model.ApplyCarType, converter.Convert("APPLY_CAR_TYPE", model.ApplyCarType));
            FillUtil.FillSelect(GetDocument(), "ddl_ycx", model.AllowedCarType, converter.Convert("ALLOWED_CAR_TYPE", model.AllowedCarType));
            FillUtil.FillSelect(GetDocument(), "drpguoj", model.Nationality, converter.Convert("NATIONNALITY", model.Nationality));
            FillUtil.FillSelect(GetDocument(), "drphuji", model.CensusRegister, converter.Convert("CENSUS_REGISTER", model.CensusRegister));
            FillUtil.FillText(GetDocument(), "txtGuddh", converter.Convert("TELEPHONE", model.Telephone));
            FillUtil.FillText(GetDocument(), "txtDianh", converter.Convert("MOBILEPHONE", model.Mobilephone));
            FillUtil.FillText(GetDocument(), "Textbox1", converter.Convert("EMAIL", model.Email));
            FillUtil.FillText(GetDocument(), "txtYouzbm", converter.Convert("POSTALCODE", model.Postalcode));
            FillUtil.FillText(GetDocument(), "txtshengao", converter.Convert("STATURE", model.Stature));
            FillUtil.FillText(GetDocument(), "txtzuoshil", converter.Convert("LEFT_EYESIGHT", model.LeftEyesight));
            FillUtil.FillText(GetDocument(), "txtyoushil", converter.Convert("RIGHT_EYESIGHT", model.RightEyesight));
            FillUtil.FillSelect(GetDocument(), "drpbiansl", model.Distinguish, converter.Convert("DISTINGUISH", model.Distinguish));
            FillUtil.FillSelect(GetDocument(), "drptingl", model.Audition, converter.Convert("AUDITION", model.Audition));
            FillUtil.FillSelect(GetDocument(), "drpshangz", model.UpperLimb, converter.Convert("UPPER_LIMB", model.UpperLimb));
            FillUtil.FillSelect(GetDocument(), "drpzuoxz", model.LeftLowerLimb, converter.Convert("LEFT_LOWER_LIMB", model.LeftLowerLimb));
            FillUtil.FillSelect(GetDocument(), "drpyouxz", model.RightLowerLimb, converter.Convert("RIGHT_LOWER_LIMB", model.RightLowerLimb));
            FillUtil.FillSelect(GetDocument(), "drpqugjb", model.Neck, converter.Convert("NECK", model.Neck));
            FillUtil.FillText(GetDocument(), "txtriqi1", converter.Convert("CHECK_TIME", model.CheckTime));
            FillUtil.FillSelect(GetDocument(), "drpyiyuan", model.CheckHospital, converter.Convert("CHECK_HOSPITAL", model.CheckHospital));
            FillUtil.FillText(GetDocument(), "txtRiqi", converter.Convert("ENROLL_TIME", model.EnrollTime));
            FillUtil.FillSelect(GetDocument(), "drpbaomd", model.EnrollSite, converter.Convert("ENROLL_SITE", model.EnrollSite));
            FillUtil.FillSelect(GetDocument(), "drpDiqu", model.Area, converter.Convert("AREA", model.Area));
            FillUtil.FillSelect(GetDocument(), "DropDownList4", model.Province, converter.Convert("PROVINCE", model.Province), true);
            CityFiller filler = new CityFiller(GetDocument(), model);
            filler.Fill();
            FillUtil.FillText(GetDocument(), "txtLianxdz", converter.Convert("CONTACT_ADDRESS", model.ContactAddress));
            FillUtil.FillText(GetDocument(), "txtjiesr", converter.Convert("REFEREE", model.Referee));
        }

        public HTMLDocument GetDocument()
        {
            return this.document;
        }

        protected class CityFiller
        {
            private HTMLDocument document;

            private StudentModel model;

            public CityFiller(HTMLDocument document, StudentModel model)
            {
                this.document = document;
                this.model = model;
            }

            public void Fill()
            {
                System.Timers.Timer timer = new System.Timers.Timer();
                timer.Interval = 100;
                timer.AutoReset = false;
                timer.Enabled = true;
                timer.Elapsed += new ElapsedEventHandler(execute);
            }

            private void execute(object source, ElapsedEventArgs e)
            {
                TMSConverter converter = TMSConverter.GetInstance();
                FillUtil.FillSelect(this.document, "DropDownList5", model.City, converter.Convert("CITY", model.City), true);
                DistrictFiller filler = new DistrictFiller(this.document, this.model);
                filler.Fill();
            }
        }

        protected class DistrictFiller
        {
            private HTMLDocument document;

            private StudentModel model;

            public DistrictFiller(HTMLDocument document, StudentModel model)
            {
                this.document = document;
                this.model = model;
            }

            public void Fill()
            {
                System.Timers.Timer timer = new System.Timers.Timer();
                timer.Interval = 100;
                timer.AutoReset = false;
                timer.Enabled = true;
                timer.Elapsed += new ElapsedEventHandler(execute);
            }

            private void execute(object source, ElapsedEventArgs e)
            {
                TMSConverter converter = TMSConverter.GetInstance();
                FillUtil.FillSelect(this.document, "DropDownList6", model.District, converter.Convert("DISTRICT", model.District));
                RegisterAddressFiller filler = new RegisterAddressFiller(this.document, this.model);
                filler.Fill();
            }
        }

        private class RegisterAddressFiller
        { 
            private HTMLDocument document;

            private StudentModel model;

            public RegisterAddressFiller(HTMLDocument document, StudentModel model)
            {
                this.document = document;
                this.model = model;
            }

            public void Fill()
            {
                System.Timers.Timer timer = new System.Timers.Timer();
                timer.Interval = 100;
                timer.AutoReset = false;
                timer.Enabled = true;
                timer.Elapsed += new ElapsedEventHandler(execute);
            }

            private void execute(object source, ElapsedEventArgs e)
            {
                TMSConverter converter = TMSConverter.GetInstance();
                FillUtil.FillText(this.document, "txtZhusdz", converter.Convert("REGISTER_ADDRESS", model.RegisterAddress));
            }
        }
    }
}
