using System;
using System.Windows.Forms;
using QuickFillForm.Core.Filler;
using QuickFillForm.Core.Model;

namespace QuickFillForm.Core.UI
{
    public partial class ControlWindow : Form
    {
        private IFiller filler;

        public ControlWindow(IFiller filler)
        {
            InitializeComponent();
            this.filler = filler;
        }

        private void button1_Click(object sender, EventArgs e)
        {
            try
            {
                DataSelector provider = new DataSelector();
                StudentModel data = provider.obtainData();

                if (null != data)
                {
                    this.filler.Fill(data);
                }
            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.Message);
            }
        }
    }
}
