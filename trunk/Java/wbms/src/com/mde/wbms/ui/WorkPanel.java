package com.mde.wbms.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.mde.wbms.model.BillDetail;
import com.mde.wbms.model.BillRecordModel;
import com.mde.wbms.model.IPagerTablePanelModel;
import com.mde.wbms.model.Pagination;
import com.mde.wbms.service.IConfigService;
import com.mde.wbms.service.impl.ConfigServiceImpl;

public class WorkPanel extends JPanel
{
    private static final long serialVersionUID = 8706341854377629012L;
    
    public WorkPanel()
    {
        this.initilize();
    }
    
    private void initilize()
    {
        this.setLayout(new BorderLayout());
        JPanel operates = new JPanel();
        operates.setLayout(new FlowLayout(FlowLayout.RIGHT));
        operates.add(new JButton("处理"));
        operates.add(new JButton("导出"));
        this.add(operates, BorderLayout.NORTH);
        PagerTablePanel pagerTablePanel = new PagerTablePanel(new BillRecordModel());
        pagerTablePanel.setTableRowHeight(25);
        pagerTablePanel.setTableHeaderReorderingAllowed(false);
        pagerTablePanel.setTableColumnWidth(0, 100, 100, 150);
        pagerTablePanel.setTableColumnAlignment(0, JLabel.CENTER);
        this.add(pagerTablePanel,BorderLayout.CENTER);
    }
}
