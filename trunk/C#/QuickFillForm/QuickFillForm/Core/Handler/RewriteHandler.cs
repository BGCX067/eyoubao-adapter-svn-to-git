using System;
using mshtml;

namespace QuickFillForm.Core.Handler
{
    public class RewriteHandler : IDocumentHandler
    {
        private HTMLDocument document;

        public RewriteHandler(HTMLDocument document)
        {
            this.document = document;
        }

        public void initialize()
        {
            try
            {
                try
                {
                    document.parentWindow.execScript("window.showModalDialog=function(url){window.open(url);}");
                }
                catch (Exception ex)
                {
                    // do nothing
                }
            }
            catch (Exception e)
            {
                System.Windows.Forms.MessageBox.Show(e.Message);
            }
        }

        public void destroy()
        {
            // do nothing
        }
    }
}
