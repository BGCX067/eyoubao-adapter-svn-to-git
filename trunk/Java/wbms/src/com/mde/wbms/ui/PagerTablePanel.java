package com.mde.wbms.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableColumn;

import com.mde.wbms.model.AnnnotationTableModel;
import com.mde.wbms.model.BillDetail;
import com.mde.wbms.model.IPagerTablePanelModel;
import com.mde.wbms.model.Pagination;
import com.mde.wbms.ui.renderer.AlignmentCellRenderer;

public class PagerTablePanel extends JPanel
{
    private static final long serialVersionUID = 2203637438869536491L;
    
    private IPagerTablePanelModel model;
    
    private int pageSize = 1;
    
    private JTable table;
    
    private AnnnotationTableModel tableModel;
    
    private JLabel statisticLabel;
    
    private JButton firstBtn;
    
    private JButton prevBtn;
    
    private JButton nextBtn;
    
    private JButton lastBtn;
    
    public PagerTablePanel(IPagerTablePanelModel model)
    {
        this.model = model;
        this.initilize();
    }
    
    private void initilize()
    {
        setLayout(new BorderLayout());
        Pagination<?> p = model.getCurrentPagination();
        tableModel = new AnnnotationTableModel(BillDetail.class, p.getRecords());
        table = new JTable(tableModel);
        table.setPreferredScrollableViewportSize(getPreferredSize());
        add(new JScrollPane(table), BorderLayout.CENTER);
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 10));
        statisticLabel = new JLabel();
        panel.add(statisticLabel);
        
        firstBtn = new JButton("第一页");
        firstBtn.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                model.reset(1, pageSize);
                Pagination<?> pagination = model.getCurrentPagination();
                tableModel.setData(pagination.getRecords());
                refreshUI();
            }
        });
        panel.add(firstBtn);
        
        prevBtn = new JButton("上一页");
        prevBtn.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                Pagination<?> pagination = model.getCurrentPagination();
                model.reset(pagination.getPrePage(), pageSize);
                pagination = model.getCurrentPagination();
                tableModel.setData(pagination.getRecords());
                refreshUI();
            }
        });
        panel.add(prevBtn);
        
        nextBtn = new JButton("下一页");
        nextBtn.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                Pagination<?> pagination = model.getCurrentPagination();
                model.reset(pagination.getNextPage(), pageSize);
                pagination = model.getCurrentPagination();
                tableModel.setData(pagination.getRecords());
                refreshUI();
            }
        });
        panel.add(nextBtn);
        
        lastBtn = new JButton("最后页");
        lastBtn.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                Pagination<?> pagination = model.getCurrentPagination();
                model.reset(pagination.getTotalPage(), pageSize);
                pagination = model.getCurrentPagination();
                tableModel.setData(pagination.getRecords());
                refreshUI();
            }
        });
        panel.add(lastBtn);
        add(panel, BorderLayout.SOUTH);
        refreshUI();
    }
    
    public void setTableRowHeight(int height)
    {
        table.setRowHeight(height);
    }
    
    public void setTableColumnWidth(int index, int preferredWidth, int minWidth, int maxWidth)
    {
        TableColumn column = table.getColumnModel().getColumn(index);
        column.setMinWidth(minWidth);
        column.setMaxWidth(maxWidth);
        column.setPreferredWidth(preferredWidth);
    }
    
    public void setTableColumnAlignment(int index, int alignment)
    {
        TableColumn column = table.getColumnModel().getColumn(index);
        column.setCellRenderer(new AlignmentCellRenderer(alignment));
    }
    
    public void setTableHeaderReorderingAllowed(boolean reorderingAllowed)
    {
        table.getTableHeader().setReorderingAllowed(reorderingAllowed);
    }
    
    public void setTableHeaderResizingAllowed(boolean resizingAllowed)
    {
        table.getTableHeader().setResizingAllowed(resizingAllowed);
    }
    
    private void refreshUI()
    {
        Pagination<?> pagination = model.getCurrentPagination();
        statisticLabel.setText(String.format("页次：%1$d/%2$d 共%3$d条数据",
            pagination.getPageNo(),
            pagination.getTotalPage(),
            pagination.getTotalCount()));
        
        if (!pagination.isFirstPage())
        {
            firstBtn.setEnabled(true);
            prevBtn.setEnabled(true);
        }
        else
        {
            firstBtn.setEnabled(false);
            prevBtn.setEnabled(false);
        }
        
        if (!pagination.isLastPage())
        {
            lastBtn.setEnabled(true);
            nextBtn.setEnabled(true);
        }
        else
        {
            lastBtn.setEnabled(false);
            nextBtn.setEnabled(false);
        }
    }
}
