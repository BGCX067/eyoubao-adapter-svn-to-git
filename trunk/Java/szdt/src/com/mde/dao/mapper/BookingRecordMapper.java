package com.mde.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.mde.model.BookingRecord;

public class BookingRecordMapper implements RowMapper<BookingRecord>
{
    @Override
    public BookingRecord mapRow(ResultSet rs, int index)
        throws SQLException
    {
        BookingRecord record = new BookingRecord();
        record.setIntervalId(rs.getInt("INTERVAL_ID"));
        record.setItemId(rs.getInt("ITEM_ID"));
        record.setUsername(rs.getString("USERNAME"));
        return record;
    }
}
