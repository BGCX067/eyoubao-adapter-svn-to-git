using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Text;
using System.Windows.Forms;

namespace EYouBaoAdapter.UI
{
    public partial class ProgressForm : Form
    {
        public ProgressForm()
        {
            InitializeComponent();
        }

        public int ProgressValue 
        {
            get { return this.progressBar1.Value; }
            set { progressBar1.Value = value; }
        }

    }
}
