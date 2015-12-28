using System;
using mshtml;
using QuickFillForm.Core.Model;
using QuickFillForm.Core.Converter;
using QuickFillForm.Core.Util;

namespace QuickFillForm.Core.Filler
{
    public class PMSFiller : IFiller
    {
        private HTMLDocument document;

        public PMSFiller(HTMLDocument document)
        {
            this.document = document;
        }

        public void Fill(object data)
        {
            StudentModel model = (StudentModel)data;
            PMSConverter converter = PMSConverter.GetInstance();
            FillUtil.FillTextByName(GetDocument(), "XYJBXXB/XM", converter.Convert("NAME", model.Name));
            FillUtil.FillSelect(GetDocument(), "xb", model.Sex, converter.Convert("SEX", model.Sex));
            FillUtil.FillText(GetDocument(), "birthday", converter.Convert("BIRTHDAY", model.Birthday));
            FillUtil.FillSelect(GetDocument(), "zjmc", model.CertificateType, converter.Convert("CERTIFICATE_TYPE", model.CertificateType));
            FillUtil.FillText(GetDocument(), "sfzmhm", converter.Convert("CERTIFICATE_CODE", model.CertificateCode));
            FillUtil.FillText(GetDocument(), "XYJBXXB/ZZZM", converter.Convert("TEMP_LIVE_CODE", model.TempLiveCode));
            FillUtil.FillSelectByName(GetDocument(), "XYJBXXB/XXLX", model.ApplyType, converter.Convert("APPLY_TYPE", model.ApplyType));
            FillUtil.FillSelect(GetDocument(), "xxcx", model.ApplyCarType, converter.Convert("APPLY_CAR_TYPE", model.ApplyCarType));
            FillUtil.FillSelect(GetDocument(), "xzjcxdh", model.AllowedCarType, converter.Convert("ALLOWED_CAR_TYPE", model.AllowedCarType));
            FillUtil.FillSelectByName(GetDocument(), "XYJBXXB/GJ", model.Nationality, converter.Convert("NATIONNALITY", model.Nationality));
            FillUtil.FillSelectByName(GetDocument(), "XYJBXXB/XYLY", model.CensusRegister, converter.Convert("CENSUS_REGISTER", model.CensusRegister));
            FillUtil.FillTextByName(GetDocument(), "XYJBXXB/LXDH2", converter.Convert("TELEPHONE", model.Telephone));
            FillUtil.FillTextByName(GetDocument(), "XYJBXXB/LXDH1", converter.Convert("MOBILEPHONE", model.Mobilephone));
            FillUtil.FillText(GetDocument(), "dzyx", converter.Convert("EMAIL", model.Email));
            FillUtil.FillText(GetDocument(), "yzbm", converter.Convert("POSTALCODE", model.Postalcode));
            FillUtil.FillText(GetDocument(), "sg", converter.Convert("STATURE", model.Stature));
            FillUtil.FillText(GetDocument(), "zsl", converter.Convert("LEFT_EYESIGHT", model.LeftEyesight));
            FillUtil.FillText(GetDocument(), "ysl", converter.Convert("RIGHT_EYESIGHT", model.RightEyesight));
            FillUtil.FillSelect(GetDocument(), "bsl", model.Distinguish, converter.Convert("DISTINGUISH", model.Distinguish));
            FillUtil.FillSelect(GetDocument(), "tl", model.Audition, converter.Convert("AUDITION", model.Audition));
            FillUtil.FillSelect(GetDocument(), "sz", model.UpperLimb, converter.Convert("UPPER_LIMB", model.UpperLimb));
            FillUtil.FillSelect(GetDocument(), "zxz", model.LeftLowerLimb, converter.Convert("LEFT_LOWER_LIMB", model.LeftLowerLimb));
            FillUtil.FillSelect(GetDocument(), "yxz", model.RightLowerLimb, converter.Convert("RIGHT_LOWER_LIMB", model.RightLowerLimb));
            FillUtil.FillSelect(GetDocument(), "qgjb", model.Neck, converter.Convert("NECK", model.Neck));
            FillUtil.FillText(GetDocument(), "tjrq", converter.Convert("CHECK_TIME", model.CheckTime));
            FillUtil.FillSelect(GetDocument(), "tjyy", model.CheckHospital, converter.Convert("CHECK_HOSPITAL", model.CheckHospital));
            FillUtil.FillTextByName(GetDocument(), "XYJBXXB/BMRQ", converter.Convert("ENROLL_TIME", model.EnrollTime));
            FillUtil.FillSelect(GetDocument(), "ssxq", model.Area, converter.Convert("AREA", model.Area));
            FillUtil.FillSelect(GetDocument(), "aaa", model.Area, converter.Convert("CONTACT_AREA", model.Area));
            FillUtil.FillSelectByName(GetDocument(), "XYJBXXB/BMTJ", model.EnrollType, converter.Convert("ENROLL_TYPE", model.EnrollType));
            FillUtil.FillSelect(GetDocument(), "DJZSXZQH", model.Province, converter.Convert("PROVINCE", model.Province), true);
            FillUtil.FillSelect(GetDocument(), "DJZSXZQH2", model.City, converter.Convert("CITY", model.City), true);
            FillUtil.FillSelect(GetDocument(), "DJZSXZQH3", model.District, converter.Convert("DISTRICT", model.District));
            FillUtil.FillText(GetDocument(), "djzsdz", converter.Convert("REGISTER_ADDRESS", model.RegisterAddress));
            FillUtil.FillText(GetDocument(), "lxdz", converter.Convert("CONTACT_ADDRESS", model.ContactAddress));
            FillUtil.FillTextByName(GetDocument(), "XYJBXXB/TJR", converter.Convert("REFEREE", model.Referee));
        }

        public HTMLDocument GetDocument()
        {
            return this.document;
        }
    }
}
