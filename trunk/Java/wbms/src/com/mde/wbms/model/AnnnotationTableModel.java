package com.mde.wbms.model;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.table.AbstractTableModel;

import com.mde.wbms.ui.Resolvable;

public class AnnnotationTableModel extends AbstractTableModel
{
    private static final long serialVersionUID = -7081690566557822868L;
    
    private Class<?> clazz;
    
    private List<?> records;
    
    private List<Resolvable> columns = new ArrayList<Resolvable>();
    
    private Map<Resolvable, Field> mapping = new HashMap<Resolvable, Field>();
    
    public AnnnotationTableModel(Class<?> clazz, List<?> records)
    {
        this.clazz = clazz;
        this.records = records;
        this.resolve();
    }
    
    public void setData(List<?> records)
    {
        this.records = records;
        this.fireTableDataChanged();
    }
    
    @Override
    public int getRowCount()
    {
        return records.size();
    }
    
    @Override
    public int getColumnCount()
    {
        return columns.size();
    }
    
    @Override
    public String getColumnName(int column)
    {
        return columns.get(column).name();
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex)
    {
        Object record = records.get(rowIndex);
        Resolvable column = columns.get(columnIndex);
        Field field = mapping.get(column);
        try
        {
            field.setAccessible(true);
            return field.get(record);
        }
        catch (IllegalArgumentException e)
        {
        }
        catch (IllegalAccessException e)
        {
        }
        return "";
    }
    
    private void resolve()
    {
        Field[] fields = clazz.getDeclaredFields();
        
        Resolvable column;
        
        for (Field field : fields)
        {
            column = field.getAnnotation(Resolvable.class);
            
            if (null != column)
            {
                columns.add(column);
                mapping.put(column, field);
            }
        }
        
        Collections.sort(columns, new Comparator<Resolvable>()
        {
            
            @Override
            public int compare(Resolvable o1, Resolvable o2)
            {
                return o1.sort() - o2.sort();
            }
        });
    }
    
}
