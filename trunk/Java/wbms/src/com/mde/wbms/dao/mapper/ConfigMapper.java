package com.mde.wbms.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.mde.wbms.model.Config;

public class ConfigMapper implements RowMapper<Config>
{
    
    @Override
    public Config mapRow(ResultSet rs, int index)
        throws SQLException
    {
        Config config = new Config();
        config.setWaybillPath(rs.getString("WAYBILL_PATH"));
        config.setWorkPath(rs.getString("WORK_PATH"));
        config.setMergePath(rs.getString("MERGE_PATH"));
        return config;
    }
    
}
