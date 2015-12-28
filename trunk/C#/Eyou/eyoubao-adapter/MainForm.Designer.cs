namespace EYouBaoAdapter
{
    partial class mainForm
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.mainMenu = new System.Windows.Forms.MenuStrip();
            this.shipMenuGroup = new System.Windows.Forms.ToolStripMenuItem();
            this.buildOrderMenu = new System.Windows.Forms.ToolStripMenuItem();
            this.body = new System.Windows.Forms.Panel();
            this.statusStrip = new System.Windows.Forms.StatusStrip();
            this.statusLabel = new System.Windows.Forms.ToolStripStatusLabel();
            this.shipOrderView1 = new EYouBaoAdapter.UI.ShipOrderView();
            this.mainMenu.SuspendLayout();
            this.body.SuspendLayout();
            this.statusStrip.SuspendLayout();
            this.SuspendLayout();
            // 
            // mainMenu
            // 
            this.mainMenu.BackColor = System.Drawing.SystemColors.MenuBar;
            this.mainMenu.Items.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.shipMenuGroup});
            this.mainMenu.Location = new System.Drawing.Point(0, 0);
            this.mainMenu.Name = "mainMenu";
            this.mainMenu.Size = new System.Drawing.Size(784, 25);
            this.mainMenu.TabIndex = 0;
            this.mainMenu.Text = "主菜单";
            // 
            // shipMenuGroup
            // 
            this.shipMenuGroup.DropDownItems.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.buildOrderMenu});
            this.shipMenuGroup.Name = "shipMenuGroup";
            this.shipMenuGroup.Size = new System.Drawing.Size(44, 21);
            this.shipMenuGroup.Text = "发运";
            // 
            // buildOrderMenu
            // 
            this.buildOrderMenu.Name = "buildOrderMenu";
            this.buildOrderMenu.Size = new System.Drawing.Size(124, 22);
            this.buildOrderMenu.Text = "生成运单";
            this.buildOrderMenu.Click += new System.EventHandler(this.BuildOrderMenu_Click);
            // 
            // body
            // 
            this.body.Controls.Add(this.shipOrderView1);
            this.body.Dock = System.Windows.Forms.DockStyle.Top;
            this.body.Location = new System.Drawing.Point(0, 25);
            this.body.Name = "body";
            this.body.Size = new System.Drawing.Size(784, 512);
            this.body.TabIndex = 1;
            // 
            // statusStrip
            // 
            this.statusStrip.AutoSize = false;
            this.statusStrip.Items.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.statusLabel});
            this.statusStrip.Location = new System.Drawing.Point(0, 537);
            this.statusStrip.Name = "statusStrip";
            this.statusStrip.Size = new System.Drawing.Size(784, 25);
            this.statusStrip.TabIndex = 2;
            this.statusStrip.Text = "状态栏";
            // 
            // statusLabel
            // 
            this.statusLabel.Name = "statusLabel";
            this.statusLabel.Size = new System.Drawing.Size(0, 20);
            // 
            // shipOrderView1
            // 
            this.shipOrderView1.Dock = System.Windows.Forms.DockStyle.Fill;
            this.shipOrderView1.Location = new System.Drawing.Point(0, 0);
            this.shipOrderView1.Name = "shipOrderView1";
            this.shipOrderView1.Padding = new System.Windows.Forms.Padding(5, 0, 5, 0);
            this.shipOrderView1.Size = new System.Drawing.Size(784, 512);
            this.shipOrderView1.TabIndex = 0;
            // 
            // mainForm
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 12F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(784, 562);
            this.Controls.Add(this.statusStrip);
            this.Controls.Add(this.body);
            this.Controls.Add(this.mainMenu);
            this.MainMenuStrip = this.mainMenu;
            this.MaximizeBox = false;
            this.MaximumSize = new System.Drawing.Size(800, 600);
            this.MinimumSize = new System.Drawing.Size(800, 600);
            this.Name = "mainForm";
            this.StartPosition = System.Windows.Forms.FormStartPosition.CenterScreen;
            this.Text = "E邮宝接入系统";
            this.Load += new System.EventHandler(this.mainForm_Load);
            this.mainMenu.ResumeLayout(false);
            this.mainMenu.PerformLayout();
            this.body.ResumeLayout(false);
            this.statusStrip.ResumeLayout(false);
            this.statusStrip.PerformLayout();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.MenuStrip mainMenu;
        private System.Windows.Forms.ToolStripMenuItem shipMenuGroup;
        private System.Windows.Forms.ToolStripMenuItem buildOrderMenu;
        private System.Windows.Forms.Panel body;
        private System.Windows.Forms.StatusStrip statusStrip;
        private System.Windows.Forms.ToolStripStatusLabel statusLabel;
        private UI.ShipOrderView shipOrderView1;
    }
}

