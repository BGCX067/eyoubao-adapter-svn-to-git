package com.mde.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.mde.model.BookingItem;

public class BookingItemMapper implements RowMapper<BookingItem>
{
    @Override
    public BookingItem mapRow(ResultSet rs, int index)
        throws SQLException
    {
        BookingItem item = new BookingItem();
        item.setId(rs.getInt("ID"));
        item.setName(rs.getString("NAME"));
        item.setCount(rs.getInt("COUNT"));
        return item;
    }
    
}
