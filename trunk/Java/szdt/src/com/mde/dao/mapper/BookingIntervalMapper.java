package com.mde.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.mde.model.BookingInterval;

public class BookingIntervalMapper implements RowMapper<BookingInterval>
{
    @Override
    public BookingInterval mapRow(ResultSet rs, int index)
        throws SQLException
    {
        BookingInterval interval = new BookingInterval();
        interval.setId(rs.getInt("ID"));
        interval.setStart(rs.getString("START_TIME"));
        interval.setEnd(rs.getString("END_TIME"));
        return interval;
    }
    
}
