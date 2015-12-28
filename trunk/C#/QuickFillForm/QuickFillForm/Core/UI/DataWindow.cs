using System;
using System.Windows.Forms;
using QuickFillForm.Core.Model;
using System.Text.RegularExpressions;
using QuickFillForm.Core.Resolver;

namespace QuickFillForm.Core.UI
{
    public partial class DataWindow : Form
    {
        public StudentModel SelectedModel { get; set; }

        public DataWindow()
        {
            InitializeComponent();
        }

        private void FillButton_Click(object sender, EventArgs e)
        {
            if (StudentTable.SelectedRows.Count <= 0)
            {
                MessageBox.Show("请选择需要填充的数据", "提示");
                return;
            }

            SelectedModel = (StudentModel)StudentTable.CurrentRow.DataBoundItem;
            this.DialogResult = DialogResult.OK;
            this.Close();
        }

        private void DataWindow_Load(object sender, EventArgs e)
        {
            SearchModel searcher = new SearchModel();
            searcher.pageNo = 1;
            searcher.pageSize = 15;
            update(searcher);
        }

        private void update(SearchModel searcher)
        {
            searcher.name = nameText.Text.Trim();
            searcher.code = codeText.Text.Trim();
            Pagination pagination = ConfigResolver.GetInstance().GetDataProvider().Provide(searcher);

            ModelBindingSource.Clear();
            for (int i = 0; i < pagination.Records.Count; i++)
            {
                ModelBindingSource.Add(pagination.Records[i]);
            }

            if (pagination.PageNo <= 1)
            {
                ForwardFirstLabel.Enabled = false;
                ForwardPrevLabel.Enabled = false;
            }
            else
            {
                ForwardFirstLabel.Enabled = true;
                ForwardPrevLabel.Enabled = true;
            }

            if (searcher.pageNo >= pagination.getTotalPage())
            {
                ForwardNextLabel.Enabled = false;
                ForwardLastLabel.Enabled = false;
            }
            else
            {
                ForwardNextLabel.Enabled = true;
                ForwardLastLabel.Enabled = true;
            }

            StatisticLabel.Text = String.Format("页次：{0}/{1} 共{2}条数据", pagination.PageNo, pagination.getTotalPage(), pagination.TotalCount);
            PagingPanel.Tag = pagination;
            ForwardPageText.Text = pagination.PageNo.ToString();
            StudentTable.ClearSelection();
        }

        private void ForwardFirstLabel_LinkClicked(object sender, LinkLabelLinkClickedEventArgs e)
        {
            Pagination pagination = (Pagination)PagingPanel.Tag;
            SearchModel searcher = new SearchModel();
            searcher.pageNo = 1;
            searcher.pageSize = pagination.PageSize;
            update(searcher);
        }

        private void ForwardPrevLabel_LinkClicked(object sender, LinkLabelLinkClickedEventArgs e)
        {
            Pagination pagination = (Pagination)PagingPanel.Tag;
            SearchModel searcher = new SearchModel();
            searcher.pageNo = pagination.PageNo - 1;
            searcher.pageSize = pagination.PageSize;
            update(searcher);
        }

        private void ForwardNextLabel_LinkClicked(object sender, LinkLabelLinkClickedEventArgs e)
        {
            Pagination pagination = (Pagination)PagingPanel.Tag;
            SearchModel searcher = new SearchModel();
            searcher.pageNo = pagination.PageNo + 1;
            searcher.pageSize = pagination.PageSize;
            update(searcher);
        }

        private void ForwardLastLabel_LinkClicked(object sender, LinkLabelLinkClickedEventArgs e)
        {
            Pagination pagination = (Pagination)PagingPanel.Tag;
            SearchModel searcher = new SearchModel();
            searcher.pageNo = pagination.getTotalPage();
            searcher.pageSize = pagination.PageSize;
            update(searcher);
        }

        private void ForwardButton_Click(object sender, EventArgs e)
        {
            int pageNo = 1;

            if (null != ForwardPageText.Text && Regex.IsMatch(ForwardPageText.Text, @"^\d+$"))
            {
                pageNo = int.Parse(ForwardPageText.Text);

                if (pageNo <= 1)
                {
                    pageNo = 1;
                }
            }
            Pagination pagination = (Pagination)PagingPanel.Tag;
            SearchModel searcher = new SearchModel();
            searcher.pageNo = pageNo;
            searcher.pageSize = pagination.PageSize;
            update(searcher);
        }

        private void StudentTable_CellDoubleClick(object sender, DataGridViewCellEventArgs e)
        {
            if (e.RowIndex == -1)
            {
                return;
            }

            SelectedModel = (StudentModel)StudentTable.CurrentRow.DataBoundItem;
            this.DialogResult = DialogResult.OK;
            this.Close();
        }

        private void queryButton_Click(object sender, EventArgs e)
        {
            SearchModel searcher = new SearchModel();
            searcher.pageNo = 1;
            searcher.pageSize = 15;
            update(searcher);
        }
    }
}
