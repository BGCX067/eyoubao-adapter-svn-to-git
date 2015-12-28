namespace EYouBaoAdapter.UI
{
    partial class ShipOrderView
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

        #region Component Designer generated code

        /// <summary> 
        /// Required method for Designer support - do not modify 
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.components = new System.ComponentModel.Container();
            System.Windows.Forms.Label L1;
            System.Windows.Forms.Label L2;
            this.ActionPanel = new System.Windows.Forms.Panel();
            this.ShipButton = new System.Windows.Forms.Button();
            this.OrderTable = new System.Windows.Forms.DataGridView();
            this.OrderBindingSource = new System.Windows.Forms.BindingSource(this.components);
            this.PagingPanel = new System.Windows.Forms.Panel();
            this.ForwardButton = new System.Windows.Forms.Button();
            this.ForwardPageText = new System.Windows.Forms.TextBox();
            this.ForwardLastLabel = new System.Windows.Forms.LinkLabel();
            this.ForwardNextLabel = new System.Windows.Forms.LinkLabel();
            this.ForwardPrevLabel = new System.Windows.Forms.LinkLabel();
            this.ForwardFirstLabel = new System.Windows.Forms.LinkLabel();
            this.StatisticLabel = new System.Windows.Forms.Label();
            this.SystemOrderTypeColumn = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.SystemOrderNoColumn = new System.Windows.Forms.DataGridViewTextBoxColumn();
            L1 = new System.Windows.Forms.Label();
            L2 = new System.Windows.Forms.Label();
            this.ActionPanel.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.OrderTable)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.OrderBindingSource)).BeginInit();
            this.PagingPanel.SuspendLayout();
            this.SuspendLayout();
            // 
            // L1
            // 
            L1.AutoSize = true;
            L1.Location = new System.Drawing.Point(454, 15);
            L1.Name = "L1";
            L1.Size = new System.Drawing.Size(29, 12);
            L1.TabIndex = 5;
            L1.Text = "转向";
            // 
            // L2
            // 
            L2.AutoSize = true;
            L2.Location = new System.Drawing.Point(524, 15);
            L2.Name = "L2";
            L2.Size = new System.Drawing.Size(17, 12);
            L2.TabIndex = 7;
            L2.Text = "页";
            // 
            // ActionPanel
            // 
            this.ActionPanel.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.ActionPanel.Controls.Add(this.ShipButton);
            this.ActionPanel.Dock = System.Windows.Forms.DockStyle.Top;
            this.ActionPanel.Location = new System.Drawing.Point(0, 0);
            this.ActionPanel.Name = "ActionPanel";
            this.ActionPanel.Size = new System.Drawing.Size(780, 50);
            this.ActionPanel.TabIndex = 0;
            // 
            // ShipButton
            // 
            this.ShipButton.Location = new System.Drawing.Point(15, 10);
            this.ShipButton.Name = "ShipButton";
            this.ShipButton.Size = new System.Drawing.Size(75, 30);
            this.ShipButton.TabIndex = 0;
            this.ShipButton.Text = "生成运单";
            this.ShipButton.UseVisualStyleBackColor = true;
            this.ShipButton.Click += new System.EventHandler(this.ShipButton_Click);
            // 
            // OrderTable
            // 
            this.OrderTable.AllowUserToAddRows = false;
            this.OrderTable.AllowUserToDeleteRows = false;
            this.OrderTable.AllowUserToResizeRows = false;
            this.OrderTable.AutoGenerateColumns = false;
            this.OrderTable.AutoSizeColumnsMode = System.Windows.Forms.DataGridViewAutoSizeColumnsMode.Fill;
            this.OrderTable.BackgroundColor = System.Drawing.SystemColors.Control;
            this.OrderTable.BorderStyle = System.Windows.Forms.BorderStyle.Fixed3D;
            this.OrderTable.ColumnHeadersHeight = 25;
            this.OrderTable.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.DisableResizing;
            this.OrderTable.Columns.AddRange(new System.Windows.Forms.DataGridViewColumn[] {
            this.SystemOrderTypeColumn,
            this.SystemOrderNoColumn});
            this.OrderTable.DataSource = this.OrderBindingSource;
            this.OrderTable.Dock = System.Windows.Forms.DockStyle.Top;
            this.OrderTable.Location = new System.Drawing.Point(0, 50);
            this.OrderTable.MultiSelect = false;
            this.OrderTable.Name = "OrderTable";
            this.OrderTable.ReadOnly = true;
            this.OrderTable.RowHeadersVisible = false;
            this.OrderTable.RowTemplate.Height = 25;
            this.OrderTable.SelectionMode = System.Windows.Forms.DataGridViewSelectionMode.FullRowSelect;
            this.OrderTable.Size = new System.Drawing.Size(780, 410);
            this.OrderTable.TabIndex = 1;
            // 
            // PagingPanel
            // 
            this.PagingPanel.Controls.Add(this.ForwardButton);
            this.PagingPanel.Controls.Add(L2);
            this.PagingPanel.Controls.Add(this.ForwardPageText);
            this.PagingPanel.Controls.Add(L1);
            this.PagingPanel.Controls.Add(this.ForwardLastLabel);
            this.PagingPanel.Controls.Add(this.ForwardNextLabel);
            this.PagingPanel.Controls.Add(this.ForwardPrevLabel);
            this.PagingPanel.Controls.Add(this.ForwardFirstLabel);
            this.PagingPanel.Controls.Add(this.StatisticLabel);
            this.PagingPanel.Dock = System.Windows.Forms.DockStyle.Fill;
            this.PagingPanel.Location = new System.Drawing.Point(0, 460);
            this.PagingPanel.Name = "PagingPanel";
            this.PagingPanel.Padding = new System.Windows.Forms.Padding(200, 10, 0, 10);
            this.PagingPanel.Size = new System.Drawing.Size(780, 40);
            this.PagingPanel.TabIndex = 2;
            // 
            // ForwardButton
            // 
            this.ForwardButton.AutoSize = true;
            this.ForwardButton.Location = new System.Drawing.Point(547, 10);
            this.ForwardButton.Name = "ForwardButton";
            this.ForwardButton.Size = new System.Drawing.Size(39, 22);
            this.ForwardButton.TabIndex = 8;
            this.ForwardButton.Text = "跳转";
            this.ForwardButton.UseVisualStyleBackColor = true;
            this.ForwardButton.Click += new System.EventHandler(this.ForwardButton_Click);
            // 
            // ForwardPageText
            // 
            this.ForwardPageText.Location = new System.Drawing.Point(487, 12);
            this.ForwardPageText.Name = "ForwardPageText";
            this.ForwardPageText.Size = new System.Drawing.Size(31, 21);
            this.ForwardPageText.TabIndex = 6;
            this.ForwardPageText.Text = "1";
            this.ForwardPageText.TextAlign = System.Windows.Forms.HorizontalAlignment.Center;
            // 
            // ForwardLastLabel
            // 
            this.ForwardLastLabel.AutoSize = true;
            this.ForwardLastLabel.Location = new System.Drawing.Point(407, 15);
            this.ForwardLastLabel.Name = "ForwardLastLabel";
            this.ForwardLastLabel.Size = new System.Drawing.Size(41, 12);
            this.ForwardLastLabel.TabIndex = 4;
            this.ForwardLastLabel.TabStop = true;
            this.ForwardLastLabel.Text = "最后页";
            this.ForwardLastLabel.LinkClicked += new System.Windows.Forms.LinkLabelLinkClickedEventHandler(this.ForwardLastLabel_LinkClicked);
            // 
            // ForwardNextLabel
            // 
            this.ForwardNextLabel.AutoSize = true;
            this.ForwardNextLabel.Location = new System.Drawing.Point(364, 15);
            this.ForwardNextLabel.Name = "ForwardNextLabel";
            this.ForwardNextLabel.Size = new System.Drawing.Size(41, 12);
            this.ForwardNextLabel.TabIndex = 3;
            this.ForwardNextLabel.TabStop = true;
            this.ForwardNextLabel.Text = "下一页";
            this.ForwardNextLabel.LinkClicked += new System.Windows.Forms.LinkLabelLinkClickedEventHandler(this.ForwardNextLabel_LinkClicked);
            // 
            // ForwardPrevLabel
            // 
            this.ForwardPrevLabel.AutoSize = true;
            this.ForwardPrevLabel.Location = new System.Drawing.Point(320, 15);
            this.ForwardPrevLabel.Name = "ForwardPrevLabel";
            this.ForwardPrevLabel.Size = new System.Drawing.Size(41, 12);
            this.ForwardPrevLabel.TabIndex = 2;
            this.ForwardPrevLabel.TabStop = true;
            this.ForwardPrevLabel.Text = "上一页";
            this.ForwardPrevLabel.LinkClicked += new System.Windows.Forms.LinkLabelLinkClickedEventHandler(this.ForwardPrevLabel_LinkClicked);
            // 
            // ForwardFirstLabel
            // 
            this.ForwardFirstLabel.AutoSize = true;
            this.ForwardFirstLabel.Location = new System.Drawing.Point(280, 15);
            this.ForwardFirstLabel.Name = "ForwardFirstLabel";
            this.ForwardFirstLabel.Size = new System.Drawing.Size(41, 12);
            this.ForwardFirstLabel.TabIndex = 1;
            this.ForwardFirstLabel.TabStop = true;
            this.ForwardFirstLabel.Text = "第一页";
            this.ForwardFirstLabel.LinkClicked += new System.Windows.Forms.LinkLabelLinkClickedEventHandler(this.ForwardFirstLabel_LinkClicked);
            // 
            // StatisticLabel
            // 
            this.StatisticLabel.Location = new System.Drawing.Point(150, 15);
            this.StatisticLabel.Name = "StatisticLabel";
            this.StatisticLabel.Size = new System.Drawing.Size(125, 12);
            this.StatisticLabel.TabIndex = 0;
            this.StatisticLabel.Text = "页次： 1/1 共0条记录";
            // 
            // SystemOrderTypeColumn
            // 
            this.SystemOrderTypeColumn.DataPropertyName = "OrderType";
            this.SystemOrderTypeColumn.HeaderText = "系统订单单别";
            this.SystemOrderTypeColumn.Name = "SystemOrderTypeColumn";
            this.SystemOrderTypeColumn.ReadOnly = true;
            // 
            // SystemOrderNoColumn
            // 
            this.SystemOrderNoColumn.DataPropertyName = "OrderNo";
            this.SystemOrderNoColumn.HeaderText = "系统订单单号";
            this.SystemOrderNoColumn.Name = "SystemOrderNoColumn";
            this.SystemOrderNoColumn.ReadOnly = true;
            // 
            // ShipOrderView
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 12F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.Controls.Add(this.PagingPanel);
            this.Controls.Add(this.OrderTable);
            this.Controls.Add(this.ActionPanel);
            this.Name = "ShipOrderView";
            this.Size = new System.Drawing.Size(780, 500);
            this.Load += new System.EventHandler(this.ShipOrderView_Load);
            this.ActionPanel.ResumeLayout(false);
            ((System.ComponentModel.ISupportInitialize)(this.OrderTable)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.OrderBindingSource)).EndInit();
            this.PagingPanel.ResumeLayout(false);
            this.PagingPanel.PerformLayout();
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.Panel ActionPanel;
        private System.Windows.Forms.DataGridView OrderTable;
        private System.Windows.Forms.Panel PagingPanel;
        private System.Windows.Forms.Label StatisticLabel;
        private System.Windows.Forms.LinkLabel ForwardFirstLabel;
        private System.Windows.Forms.LinkLabel ForwardPrevLabel;
        private System.Windows.Forms.LinkLabel ForwardNextLabel;
        private System.Windows.Forms.LinkLabel ForwardLastLabel;
        private System.Windows.Forms.TextBox ForwardPageText;
        private System.Windows.Forms.Button ForwardButton;
        private System.Windows.Forms.Button ShipButton;
        private System.Windows.Forms.BindingSource OrderBindingSource;
        private System.Windows.Forms.DataGridViewTextBoxColumn SystemOrderTypeColumn;
        private System.Windows.Forms.DataGridViewTextBoxColumn SystemOrderNoColumn;

    }
}
