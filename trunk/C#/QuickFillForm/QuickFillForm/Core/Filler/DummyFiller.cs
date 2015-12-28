using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using mshtml;

namespace QuickFillForm.Core.Filler
{
    public class DummyFiller : IFiller
    {
        private HTMLDocument document;

        public DummyFiller(HTMLDocument document)
        {
            this.document = document;
        }

        public void Fill(object data)
        {
            // do nothing
        }

        public HTMLDocument GetDocument()
        {
            return this.document;
        }
    }
}
