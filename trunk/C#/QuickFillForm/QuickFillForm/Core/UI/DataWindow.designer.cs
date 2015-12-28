namespace QuickFillForm.Core.UI
{
    partial class DataWindow
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
            this.components = new System.ComponentModel.Container();
            System.Windows.Forms.Label L2;
            System.Windows.Forms.Label L1;
            this.panel1 = new System.Windows.Forms.Panel();
            this.FillButton = new System.Windows.Forms.Button();
            this.StudentTable = new System.Windows.Forms.DataGridView();
            this.CodeColumn = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.NameColumn = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.SexColumn = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.CertificateType = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.CertificateCode = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.ApplyType = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.ApplyCarType = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.AllowedCarType = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.ModelBindingSource = new System.Windows.Forms.BindingSource(this.components);
            this.PagingPanel = new System.Windows.Forms.Panel();
            this.ForwardButton = new System.Windows.Forms.Button();
            this.ForwardPageText = new System.Windows.Forms.TextBox();
            this.ForwardLastLabel = new System.Windows.Forms.LinkLabel();
            this.ForwardNextLabel = new System.Windows.Forms.LinkLabel();
            this.ForwardPrevLabel = new System.Windows.Forms.LinkLabel();
            this.ForwardFirstLabel = new System.Windows.Forms.LinkLabel();
            this.StatisticLabel = new System.Windows.Forms.Label();
            this.nameLabel = new System.Windows.Forms.Label();
            this.nameText = new System.Windows.Forms.TextBox();
            this.codeLable = new System.Windows.Forms.Label();
            this.codeText = new System.Windows.Forms.TextBox();
            this.queryButton = new System.Windows.Forms.Button();
            L2 = new System.Windows.Forms.Label();
            L1 = new System.Windows.Forms.Label();
            this.panel1.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.StudentTable)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.ModelBindingSource)).BeginInit();
            this.PagingPanel.SuspendLayout();
            this.SuspendLayout();
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
            // L1
            // 
            L1.AutoSize = true;
            L1.Location = new System.Drawing.Point(454, 15);
            L1.Name = "L1";
            L1.Size = new System.Drawing.Size(29, 12);
            L1.TabIndex = 5;
            L1.Text = "转向";
            // 
            // panel1
            // 
            this.panel1.Controls.Add(this.queryButton);
            this.panel1.Controls.Add(this.codeText);
            this.panel1.Controls.Add(this.codeLable);
            this.panel1.Controls.Add(this.nameText);
            this.panel1.Controls.Add(this.nameLabel);
            this.panel1.Controls.Add(this.FillButton);
            this.panel1.Dock = System.Windows.Forms.DockStyle.Top;
            this.panel1.Location = new System.Drawing.Point(0, 0);
            this.panel1.Name = "panel1";
            this.panel1.Size = new System.Drawing.Size(774, 47);
            this.panel1.TabIndex = 0;
            // 
            // FillButton
            // 
            this.FillButton.Location = new System.Drawing.Point(639, 12);
            this.FillButton.Name = "FillButton";
            this.FillButton.Size = new System.Drawing.Size(90, 24);
            this.FillButton.TabIndex = 0;
            this.FillButton.Text = "填充";
            this.FillButton.UseVisualStyleBackColor = true;
            this.FillButton.Click += new System.EventHandler(this.FillButton_Click);
            // 
            // StudentTable
            // 
            this.StudentTable.AllowUserToAddRows = false;
            this.StudentTable.AllowUserToDeleteRows = false;
            this.StudentTable.AllowUserToResizeRows = false;
            this.StudentTable.AutoGenerateColumns = false;
            this.StudentTable.AutoSizeColumnsMode = System.Windows.Forms.DataGridViewAutoSizeColumnsMode.Fill;
            this.StudentTable.BackgroundColor = System.Drawing.SystemColors.Control;
            this.StudentTable.BorderStyle = System.Windows.Forms.BorderStyle.Fixed3D;
            this.StudentTable.ColumnHeadersHeight = 25;
            this.StudentTable.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.DisableResizing;
            this.StudentTable.Columns.AddRange(new System.Windows.Forms.DataGridViewColumn[] {
            this.CodeColumn,
            this.NameColumn,
            this.SexColumn,
            this.CertificateType,
            this.CertificateCode,
            this.ApplyType,
            this.ApplyCarType,
            this.AllowedCarType});
            this.StudentTable.DataSource = this.ModelBindingSource;
            this.StudentTable.Dock = System.Windows.Forms.DockStyle.Top;
            this.StudentTable.Location = new System.Drawing.Point(0, 47);
            this.StudentTable.MultiSelect = false;
            this.StudentTable.Name = "StudentTable";
            this.StudentTable.ReadOnly = true;
            this.StudentTable.RowHeadersVisible = false;
            this.StudentTable.RowTemplate.Height = 25;
            this.StudentTable.SelectionMode = System.Windows.Forms.DataGridViewSelectionMode.FullRowSelect;
            this.StudentTable.Size = new System.Drawing.Size(774, 410);
            this.StudentTable.TabIndex = 2;
            this.StudentTable.CellDoubleClick += new System.Windows.Forms.DataGridViewCellEventHandler(this.StudentTable_CellDoubleClick);
            // 
            // CodeColumn
            // 
            this.CodeColumn.DataPropertyName = "Code";
            this.CodeColumn.HeaderText = "编号";
            this.CodeColumn.Name = "CodeColumn";
            this.CodeColumn.ReadOnly = true;
            // 
            // NameColumn
            // 
            this.NameColumn.DataPropertyName = "Name";
            this.NameColumn.HeaderText = "姓名";
            this.NameColumn.Name = "NameColumn";
            this.NameColumn.ReadOnly = true;
            // 
            // SexColumn
            // 
            this.SexColumn.DataPropertyName = "Sex";
            this.SexColumn.HeaderText = "性别";
            this.SexColumn.Name = "SexColumn";
            this.SexColumn.ReadOnly = true;
            // 
            // CertificateType
            // 
            this.CertificateType.DataPropertyName = "CertificateType";
            this.CertificateType.HeaderText = "证件类型";
            this.CertificateType.Name = "CertificateType";
            this.CertificateType.ReadOnly = true;
            // 
            // CertificateCode
            // 
            this.CertificateCode.DataPropertyName = "CertificateCode";
            this.CertificateCode.HeaderText = "证件号码";
            this.CertificateCode.Name = "CertificateCode";
            this.CertificateCode.ReadOnly = true;
            // 
            // ApplyType
            // 
            this.ApplyType.DataPropertyName = "ApplyType";
            this.ApplyType.HeaderText = "申请种类";
            this.ApplyType.Name = "ApplyType";
            this.ApplyType.ReadOnly = true;
            // 
            // ApplyCarType
            // 
            this.ApplyCarType.DataPropertyName = "ApplyCarType";
            this.ApplyCarType.HeaderText = "申请车型";
            this.ApplyCarType.Name = "ApplyCarType";
            this.ApplyCarType.ReadOnly = true;
            // 
            // AllowedCarType
            // 
            this.AllowedCarType.DataPropertyName = "AllowedCarType";
            this.AllowedCarType.HeaderText = "原准驾车型";
            this.AllowedCarType.Name = "AllowedCarType";
            this.AllowedCarType.ReadOnly = true;
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
            this.PagingPanel.Location = new System.Drawing.Point(0, 457);
            this.PagingPanel.Name = "PagingPanel";
            this.PagingPanel.Padding = new System.Windows.Forms.Padding(200, 10, 0, 10);
            this.PagingPanel.Size = new System.Drawing.Size(774, 48);
            this.PagingPanel.TabIndex = 3;
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
            // nameLabel
            // 
            this.nameLabel.AutoSize = true;
            this.nameLabel.Location = new System.Drawing.Point(12, 16);
            this.nameLabel.Name = "nameLabel";
            this.nameLabel.Size = new System.Drawing.Size(41, 12);
            this.nameLabel.TabIndex = 1;
            this.nameLabel.Text = "姓名：";
            // 
            // nameText
            // 
            this.nameText.Location = new System.Drawing.Point(59, 13);
            this.nameText.Name = "nameText";
            this.nameText.Size = new System.Drawing.Size(142, 21);
            this.nameText.TabIndex = 2;
            // 
            // codeLable
            // 
            this.codeLable.AutoSize = true;
            this.codeLable.Location = new System.Drawing.Point(222, 17);
            this.codeLable.Name = "codeLable";
            this.codeLable.Size = new System.Drawing.Size(53, 12);
            this.codeLable.TabIndex = 3;
            this.codeLable.Text = "身份证：";
            // 
            // codeText
            // 
            this.codeText.Location = new System.Drawing.Point(282, 13);
            this.codeText.Name = "codeText";
            this.codeText.Size = new System.Drawing.Size(219, 21);
            this.codeText.TabIndex = 4;
            // 
            // queryButton
            // 
            this.queryButton.Location = new System.Drawing.Point(526, 12);
            this.queryButton.Name = "queryButton";
            this.queryButton.Size = new System.Drawing.Size(90, 24);
            this.queryButton.TabIndex = 5;
            this.queryButton.Text = "查询";
            this.queryButton.UseVisualStyleBackColor = true;
            this.queryButton.Click += new System.EventHandler(this.queryButton_Click);
            // 
            // DataWindow
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 12F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(774, 505);
            this.Controls.Add(this.PagingPanel);
            this.Controls.Add(this.StudentTable);
            this.Controls.Add(this.panel1);
            this.Name = "DataWindow";
            this.Text = "数据列表";
            this.Load += new System.EventHandler(this.DataWindow_Load);
            this.panel1.ResumeLayout(false);
            this.panel1.PerformLayout();
            ((System.ComponentModel.ISupportInitialize)(this.StudentTable)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.ModelBindingSource)).EndInit();
            this.PagingPanel.ResumeLayout(false);
            this.PagingPanel.PerformLayout();
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.Panel panel1;
        private System.Windows.Forms.DataGridView StudentTable;
        private System.Windows.Forms.Panel PagingPanel;
        private System.Windows.Forms.Button ForwardButton;
        private System.Windows.Forms.TextBox ForwardPageText;
        private System.Windows.Forms.LinkLabel ForwardLastLabel;
        private System.Windows.Forms.LinkLabel ForwardNextLabel;
        private System.Windows.Forms.LinkLabel ForwardPrevLabel;
        private System.Windows.Forms.LinkLabel ForwardFirstLabel;
        private System.Windows.Forms.Label StatisticLabel;
        private System.Windows.Forms.Button FillButton;
        private System.Windows.Forms.BindingSource ModelBindingSource;
        private System.Windows.Forms.DataGridViewTextBoxColumn CodeColumn;
        private System.Windows.Forms.DataGridViewTextBoxColumn NameColumn;
        private System.Windows.Forms.DataGridViewTextBoxColumn SexColumn;
        private System.Windows.Forms.DataGridViewTextBoxColumn CertificateType;
        private System.Windows.Forms.DataGridViewTextBoxColumn CertificateCode;
        private System.Windows.Forms.DataGridViewTextBoxColumn ApplyType;
        private System.Windows.Forms.DataGridViewTextBoxColumn ApplyCarType;
        private System.Windows.Forms.DataGridViewTextBoxColumn AllowedCarType;
        private System.Windows.Forms.Label nameLabel;
        private System.Windows.Forms.Button queryButton;
        private System.Windows.Forms.TextBox codeText;
        private System.Windows.Forms.Label codeLable;
        private System.Windows.Forms.TextBox nameText;

    }
}