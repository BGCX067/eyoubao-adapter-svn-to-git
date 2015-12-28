using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Drawing;
using System.Data;
using System.Text;
using System.Windows.Forms;
using EYouBaoAdapter.Core;
using EYouBaoAdapter.Model;
using System.Xml;
using EYouBaoAdapter.Util;
using EYouBaoAdapter.Exception;
using System.Text.RegularExpressions;
using System.Threading;

namespace EYouBaoAdapter.UI
{
    public partial class ShipOrderView : UserControl
    {
        private CoreService coreService = new CoreServiceImpl();

        private BackgroundWorker worker = new BackgroundWorker();

        private ProgressForm progressForm = new ProgressForm();

        public ShipOrderView()
        {
            worker.DoWork += new DoWorkEventHandler(worker_DoWork);
            worker.ProgressChanged += new ProgressChangedEventHandler(worker_ProgressChanged);
            worker.RunWorkerCompleted += new RunWorkerCompletedEventHandler(worker_RunWorkerCompleted);
            InitializeComponent();
        }

        void worker_DoWork(object sender, DoWorkEventArgs e)
        {
            try
            {
                SystemOrder order = (SystemOrder)OrderTable.CurrentRow.DataBoundItem;
                coreService.ShipSystemOrder(order, worker, e);
            }
            catch (InvalidOrderException ex)
            {
                e.Result = ex.Message;
            }
            catch (EYouBaoAccessException ex)
            {
                e.Result = ex.Message;
            }
        }

        void worker_ProgressChanged(object sender, ProgressChangedEventArgs e)
        {
            progressForm.ProgressValue = e.ProgressPercentage;
        }

        void worker_RunWorkerCompleted(object sender, RunWorkerCompletedEventArgs e)
        {
            progressForm.Close();
            Pagination pagination = (Pagination)PagingPanel.Tag;
            RefreshView(pagination.PageNo, pagination.PageSize);
            MessageBox.Show(e.Result.ToString(),"提示");
        }

        private void ShipOrderView_Load(object sender, EventArgs e)
        {
            RefreshView(1, 15);
        }

        private void RefreshView(int pageNo, int pageSize)
        {
            Pagination pagination = coreService.FindSystemOrderList(pageNo, pageSize);

            OrderBindingSource.Clear();
            for (int i = 0; i < pagination.Records.Count; i++)
            {
                OrderBindingSource.Add(pagination.Records[i]);
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

            if (pageNo >= pagination.getTotalPage())
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
            OrderTable.ClearSelection();
        }

        private void ForwardFirstLabel_LinkClicked(object sender, LinkLabelLinkClickedEventArgs e)
        {
            Pagination pagination = (Pagination)PagingPanel.Tag;
            RefreshView(1, pagination.PageSize);
        }

        private void ForwardPrevLabel_LinkClicked(object sender, LinkLabelLinkClickedEventArgs e)
        {
            Pagination pagination = (Pagination)PagingPanel.Tag;
            RefreshView(pagination.PageNo - 1, pagination.PageSize);
        }

        private void ForwardNextLabel_LinkClicked(object sender, LinkLabelLinkClickedEventArgs e)
        {
            Pagination pagination = (Pagination)PagingPanel.Tag;
            RefreshView(pagination.PageNo + 1, pagination.PageSize);
        }

        private void ForwardLastLabel_LinkClicked(object sender, LinkLabelLinkClickedEventArgs e)
        {
            Pagination pagination = (Pagination)PagingPanel.Tag;
            RefreshView(pagination.getTotalPage(), pagination.PageSize);
        }

        private void ShipButton_Click(object sender, EventArgs e)
        {
            if (OrderTable.SelectedRows.Count <= 0)
            {
                MessageBox.Show("请选择需要发运的系统订单", "提示");
                return;
            }

            progressForm.ProgressValue = 0;
            worker.WorkerReportsProgress = true;            
            worker.RunWorkerAsync();
            progressForm.ShowDialog();
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
            RefreshView(pageNo, pagination.PageSize);
        }
    }
}
