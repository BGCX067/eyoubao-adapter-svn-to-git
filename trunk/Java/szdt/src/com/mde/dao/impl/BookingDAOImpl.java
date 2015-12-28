package com.mde.dao.impl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mde.dao.IBookingDAO;
import com.mde.dao.mapper.BookingIntervalMapper;
import com.mde.dao.mapper.BookingItemMapper;
import com.mde.dao.mapper.BookingRecordMapper;
import com.mde.model.BookingInterval;
import com.mde.model.BookingItem;
import com.mde.model.BookingRecord;
import com.mde.util.DateUtil;

@Repository
public class BookingDAOImpl implements IBookingDAO
{
    @Autowired
    private JdbcTemplate jdbc;
    
    @Override
    public List<BookingInterval> findBookingIntervals(final int category, final int dateType)
    {
        return jdbc.query("SELECT * FROM SZDT_INTERVAL WHERE CATEGORY = ? AND DATE_TYPE = ?",
            new PreparedStatementSetter()
            {
                @Override
                public void setValues(PreparedStatement ps)
                    throws SQLException
                {
                    ps.setInt(1, category);
                    ps.setInt(2, dateType);
                }
            },
            new BookingIntervalMapper());
    }
    
    @Override
    public List<BookingItem> findBookingItems(final int category)
    {
        return jdbc.query("SELECT * FROM SZDT_ITEM WHERE CATEGORY = ?", new PreparedStatementSetter()
        {
            @Override
            public void setValues(PreparedStatement ps)
                throws SQLException
            {
                ps.setInt(1, category);
            }
        }, new BookingItemMapper());
    }
    
    @Override
    public List<BookingRecord> findBookingRecords(final Date date, final int type)
    {
        return jdbc.query("SELECT * FROM SZDT_BOOKING b WHERE EXISTS (SELECT ID FROM SZDT_ITEM i WHERE b.ITEM_ID = i.id AND i.CATEGORY = ?) AND b.BOOKING_DATE = ?",
            new PreparedStatementSetter()
            {
                @Override
                public void setValues(PreparedStatement ps)
                    throws SQLException
                {
                    int index = 1;
                    ps.setInt(index++, type);
                    ps.setDate(index++, new java.sql.Date(DateUtil.toStartDate(date).getTime()));
                }
            },
            new BookingRecordMapper());
    }
    
    @Override
    @Transactional
    public void booking(final int intervalId, final int itemId, final String username, final Date date)
    {
        jdbc.execute("INSERT INTO SZDT_BOOKING (INTERVAL_ID, ITEM_ID, BOOKING_DATE, USERNAME) VALUES (?, ?, ?, ?)",
            new PreparedStatementCallback<Object>()
            {
                @Override
                public Object doInPreparedStatement(PreparedStatement ps)
                    throws SQLException, DataAccessException
                {
                    int index = 1;
                    ps.setInt(index++, intervalId);
                    ps.setInt(index++, itemId);
                    ps.setDate(index++, new java.sql.Date(DateUtil.toStartDate(date).getTime()));
                    ps.setString(index++, username);
                    return ps.execute();
                }
            });
    }
    
    @Override
    public BookingInterval getBookingInterval(int id)
    {
        return jdbc.queryForObject("SELECT * FROM SZDT_INTERVAL WHERE ID = ?", new BookingIntervalMapper(), id);
    }
    
    @Override
    public List<BookingRecord> findBookingRecords(final int intervalId, final int itemId, final Date date)
    {
        return jdbc.query("SELECT * FROM SZDT_BOOKING b WHERE b.INTERVAL_ID = ? AND b.ITEM_ID = ? AND b.BOOKING_DATE = ?",
            new PreparedStatementSetter()
            {
                @Override
                public void setValues(PreparedStatement ps)
                    throws SQLException
                {
                    int index = 1;
                    ps.setInt(index++, intervalId);
                    ps.setInt(index++, itemId);
                    ps.setDate(index++, new java.sql.Date(DateUtil.toStartDate(date).getTime()));
                }
            },
            new BookingRecordMapper());
    }
    
    @Override
    public BookingItem getBookingItem(int id)
    {
        return jdbc.queryForObject("SELECT * FROM SZDT_ITEM WHERE ID = ?", new BookingItemMapper(), id);
    }
}
