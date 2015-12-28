/*
 * 文件名称：BookingObject.java  下午3:25:50 2013-3-12
 * 版权说明：js.todaysoft Technologies Co., Ltd. Copyright 2010-2017, All rights reserved.
 */
package com.mde.model;

import java.util.ArrayList;
import java.util.List;

/**
 * 预约条目，具体到某个预约项的某个时间段
 *
 * @author  xuxin
 * @version 1.0, 2013-3-12
 */
public class BookingEntry
{
    private BookingItem item;
    
    private BookingInterval interval;
    
    private List<BookingRecord> records;
    
    private String key;
    
    private boolean bookingable;
    
    public BookingEntry(BookingInterval interval, BookingItem item, List<BookingRecord> records)
    {
        this.interval = interval;
        this.item = item;
        this.records = records;
        this.initilize();
    }
    
    private void initilize()
    {
        if (null == this.records)
        {
            this.records = new ArrayList<BookingRecord>();
        }
        
        this.key = interval.getId() + "-" + item.getId();
        this.bookingable = item.getCount() > records.size();
    }
    
    public BookingItem getItem()
    {
        return item;
    }
    
    public BookingInterval getInterval()
    {
        return interval;
    }
    
    public List<BookingRecord> getRecords()
    {
        return records;
    }
    
    public String getKey()
    {
        return key;
    }
    
    public boolean isBookingable()
    {
        return bookingable;
    }
}
