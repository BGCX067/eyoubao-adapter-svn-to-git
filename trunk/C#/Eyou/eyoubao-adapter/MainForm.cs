using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Text;
using System.Windows.Forms;
using EYouBaoAdapter.Core;
using EYouBaoAdapter.Model;
using EYouBaoAdapter.UI;
using EYouBaoAdapter.Util;

namespace EYouBaoAdapter
{
    public partial class mainForm : Form
    {
        public mainForm()
        {
            InitializeComponent();
        }

        private void mainForm_Load(object sender, EventArgs e)
        {
            //
        }

        private void BuildOrderMenu_Click(object sender, EventArgs e)
        {
            body.Controls.Clear();
            ShipOrderView view = new ShipOrderView();
            body.Controls.Add(view);
        }
    }
}
