package com.mde.wbms.ui.renderer;

import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class AlignmentCellRenderer extends DefaultTableCellRenderer 
{
    private static final long serialVersionUID = -229519474330682944L;

    private int alignment;
    
    public AlignmentCellRenderer(int alignment) 
    {
        this.alignment = alignment;
    }
    
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) 
    {
        JLabel label = (JLabel)super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        label.setHorizontalAlignment(alignment);
        return label;
    }
}
