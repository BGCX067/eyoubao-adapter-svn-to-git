using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace QuickFillForm.Core.Model
{
    public class SearchModel
    {
        public int pageNo { get; set; }

        public int pageSize { get; set; }

        public string name { get; set; }

        public string code { get; set; }
    }
}
