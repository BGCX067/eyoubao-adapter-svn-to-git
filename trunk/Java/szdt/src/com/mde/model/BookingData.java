/*
 * 文件名称：BookingProject.java  下午2:58:52 2013-3-12
 * 版权说明：js.todaysoft Technologies Co., Ltd. Copyright 2010-2017, All rights reserved.
 */
package com.mde.model;

import java.util.List;
import java.util.Map;

/**
 * 预约项目
 *
 * @author  xuxin
 * @version 1.0, 2013-3-12
 */
public class BookingData
{
    private List<BookingInterval> intervals;
    
    private List<BookingItem> items;
    
    private Map<String, BookingEntry> entries;
    
    public List<BookingInterval> getIntervals()
    {
        return intervals;
    }
    
    public void setIntervals(List<BookingInterval> intervals)
    {
        this.intervals = intervals;
    }
    
    public List<BookingItem> getItems()
    {
        return items;
    }
    
    public void setItems(List<BookingItem> items)
    {
        this.items = items;
    }
    
    public Map<String, BookingEntry> getEntries()
    {
        return entries;
    }
    
    public void setEntries(Map<String, BookingEntry> entries)
    {
        this.entries = entries;
    }
}
