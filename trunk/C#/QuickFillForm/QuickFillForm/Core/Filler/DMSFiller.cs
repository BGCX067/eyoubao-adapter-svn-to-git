using mshtml;
using QuickFillForm.Core.Model;
using QuickFillForm.Core.Converter;
using QuickFillForm.Core.Util;

namespace QuickFillForm.Core.Filler
{
    public class DMSFiller : IFiller
    {
        private HTMLDocument document;

        public DMSFiller(HTMLDocument document)
        {
            this.document = document;
        }

        public void Fill(object data)
        {
            StudentModel model = (StudentModel)data;
            DMSConverter converter = DMSConverter.GetInstance();
            string city = model.City;

            string value = "320826";

            if ("苏州市".Equals(city))
            {
                value = converter.Convert("DISTRICT", model.District);
            }

            FillUtil.FillSelect(GetDocument(), "djzsxzqh", model.District, value);
            FillUtil.FillText(GetDocument(), "djzsxxdz", converter.Convert("REGISTER_ADDRESS", model.RegisterAddress));
            FillUtil.FillSelect(GetDocument(), "lxzsxzqh", model.Area, converter.Convert("CONTACT_AREA", model.Area));
            FillUtil.FillText(GetDocument(), "lxzsxxdz", converter.Convert("CONTACT_ADDRESS", model.ContactAddress));
            FillUtil.FillText(GetDocument(), "lxdh", converter.Convert("TELEPHONE", model.Telephone));
            FillUtil.FillText(GetDocument(), "sjhm", converter.Convert("MOBILEPHONE", model.Mobilephone));
            FillUtil.FillText(GetDocument(), "tjr", converter.Convert("REFEREE", model.Referee));
        }

        public HTMLDocument GetDocument()
        {
            return this.document;
        }
    }
}
