using System;
using System.Windows.Forms;
using QuickFillForm.Core.Filler;
using QuickFillForm.Core.UI;

namespace QuickFillForm.Core.Handler
{
    public class PopupHandler : IDocumentHandler
    {
        private IFiller filler;
        private ControlWindow window;

        public PopupHandler(IFiller filler)
        {
            this.filler = filler;
        }

        public void initialize()
        {
            try
            {
                this.destroy();
                this.window = new ControlWindow(this.filler);
                this.window.Show();
            }
            catch (Exception e)
            {
                MessageBox.Show(e.Message);
            }
        }

        public void destroy()
        {
            if (this.window != null)
            {
                this.window.Close();
                this.window = null;
            }
        }
    }
}
