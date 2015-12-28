using System.Windows.Forms;
using QuickFillForm.Core.Model;
using QuickFillForm.Core.UI;
using QuickFillForm.Core.Resolver;

namespace QuickFillForm.Core
{
    public class DataSelector
    {
        public StudentModel obtainData()
        {
            DataWindow window = new DataWindow();
            DialogResult result = window.ShowDialog();

            if (DialogResult.OK == result)
            {
                StudentModel model = window.SelectedModel;

                if (ConfigResolver.GetInstance().IsShowDetail())
                {
                    MessageBox.Show(model.ToString());
                }
                
                return model;
            }

            return null;
        }
    }
}
