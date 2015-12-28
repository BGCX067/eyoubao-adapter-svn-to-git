
using System;
namespace QuickFillForm.Core.Handler
{
    public class DummyHandler : IDocumentHandler
    {
        private IDocumentHandler previous;

        public DummyHandler(IDocumentHandler previous)
        {
            this.previous = previous;
        }

        public void initialize()
        {
            try
            {
                if (null != previous)
                {
                    this.previous.destroy();
                }
            }
            catch (Exception e)
            {
                System.Windows.Forms.MessageBox.Show(e.Message);
            }
        }

        public void destroy()
        {
            //
        }
    }
}
