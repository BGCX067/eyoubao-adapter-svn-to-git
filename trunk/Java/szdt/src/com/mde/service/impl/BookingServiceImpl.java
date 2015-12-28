/*
 * 文件名称：BookingServiceImpl.java  下午2:57:17 2013-3-12
 * 版权说明：js.todaysoft Technologies Co., Ltd. Copyright 2010-2017, All rights reserved.
 */
package com.mde.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mde.dao.IBookingDAO;
import com.mde.model.BookingData;
import com.mde.model.BookingEntry;
import com.mde.model.BookingInterval;
import com.mde.model.BookingItem;
import com.mde.model.BookingRecord;
import com.mde.service.IBookingService;
import com.mde.util.DateUtil;

/**
 * 预约管理实现类
 *
 * @author  xuxin
 * @version 1.0, 2013-3-12
 */
@Service
public class BookingServiceImpl implements IBookingService
{
    @Autowired
    private IBookingDAO bookingDAO;
    
    @Override
    public BookingData getBookingData(String username, Date date, int category)
    {
        List<BookingInterval> intervals = bookingDAO.findBookingIntervals(category, getDateType(date));
        List<BookingItem> items = bookingDAO.findBookingItems(category);
        List<BookingRecord> records = bookingDAO.findBookingRecords(date, category);
        
        Map<String, List<BookingRecord>> context = new HashMap<String, List<BookingRecord>>();
        
        for (BookingRecord record : records)
        {
            String key = record.getIntervalId() + "-" + record.getItemId();
            List<BookingRecord> list = context.get(key);
            
            if (null == list)
            {
                list = new ArrayList<BookingRecord>();
                context.put(key, list);
            }
            
            list.add(record);
        }
        
        BookingEntry entry;
        Map<String, BookingEntry> entries = new HashMap<String, BookingEntry>();
        
        for (BookingInterval interval : intervals)
        {
            for (BookingItem item : items)
            {
                entry = new BookingEntry(interval, item, context.get(interval.getId() + "-" + item.getId()));
                entries.put(entry.getKey(), entry);
            }
        }
        
        BookingData data = new BookingData();
        data.setIntervals(intervals);
        data.setItems(items);
        data.setEntries(entries);
        return data;
    }
    
    @Override
    public BookingData getBookingData(String username, int interval, int cateogyr)
    {
        Date today = new Date();
        return getBookingData(username, DateUtil.getDate(today, Calendar.DAY_OF_MONTH, interval), cateogyr);
    }
    
    @Override
    @Transactional
    public BookingEntry booking(String username, Date date, String key)
    {
        String[] array = key.split("-");
        Integer intervalId = Integer.valueOf(array[0]);
        Integer itemId = Integer.valueOf(array[1]);
        Integer dateInterval = Integer.valueOf(array[2]);
        Date bookingDate = DateUtil.getDate(date, Calendar.DAY_OF_MONTH, dateInterval);
        bookingDAO.booking(intervalId, itemId, username, bookingDate);
        BookingInterval interval = bookingDAO.getBookingInterval(intervalId);
        BookingItem item = bookingDAO.getBookingItem(itemId);
        List<BookingRecord> records = bookingDAO.findBookingRecords(intervalId, itemId, bookingDate);
        return new BookingEntry(interval, item, records);
    }
    
    private int getDateType(Date date)
    {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
        
        if (Calendar.SATURDAY == dayOfWeek || Calendar.SUNDAY == dayOfWeek)
        {
            return REST_DAY;
        }
        else
        {
            return WORKING_DAY;
        }
    }
}
