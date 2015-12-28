package com.mde.dao;

import java.util.Date;
import java.util.List;

import com.mde.model.BookingInterval;
import com.mde.model.BookingItem;
import com.mde.model.BookingRecord;

public interface IBookingDAO
{
    List<BookingInterval> findBookingIntervals(int category, int dateType);
    
    List<BookingItem> findBookingItems(int category);
    
    List<BookingRecord> findBookingRecords(Date date, int category);
    
    BookingItem getBookingItem(int id);
    
    BookingInterval getBookingInterval(int id);
    
    List<BookingRecord> findBookingRecords(int intervalId, int itemId, Date date);
    
    void booking(int intervalId, int itemId, String username, Date date);
}
