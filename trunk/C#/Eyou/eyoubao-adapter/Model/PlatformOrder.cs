using System;
using System.Collections.Generic;
using System.Text;

namespace EYouBaoAdapter.Model
{
    public class PlatformOrder
    {
        public string OrderCode { get; set; }

        public string CustomerCode { get; set; }

        public string VipCode { get; set; }

        public string ClctType { get; set; }

        public string Pod { get; set; }

        public string Untread { get; set; }

        public string VolWeight { get; set; }

        public string StartDate { get; set; }

        public string EndDate { get; set; }

        public string Remark { get; set; }

        public string Sku1 { get; set; }

        public string Sku2 { get; set; }

        public string BarCode { get; set; }

        public string PrintCode { get; set; }

        public NameAddress Sender { get; set; }

        public NameAddress Receiver { get; set; }

        public NameAddress Collect { get; set; }

        public List<Item> items { get; set; }
    }
}
