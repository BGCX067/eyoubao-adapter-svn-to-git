/*
 * 文件名称：CoreServiceImpl.java  下午4:47:04 2013-3-8
 * 版权说明：js.todaysoft Technologies Co., Ltd. Copyright 2010-2017, All rights reserved.
 */
package com.mde.wbms.service.impl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;

import com.mde.wbms.config.SystemConfig;
import com.mde.wbms.dao.mapper.ConfigMapper;
import com.mde.wbms.model.Config;
import com.mde.wbms.service.IConfigService;

public class ConfigServiceImpl implements IConfigService
{
    private JdbcTemplate jdbc = SystemConfig.getInstance().getJdbcTemplate();
    
    @Override
    public Config getConfig()
    {
        List<Config> records = jdbc.query("SELECT * FROM WBMS_CONFIG", new ConfigMapper());
        return records.isEmpty() ? null : records.get(0);
    }
    
    @Override
    public void saveConfig(Config config)
    {
        Config c = getConfig();
        
        if (null == c)
        {
            insertConfig(config);
        }
        else
        {
            updateConfig(config);
        }
    }
    
    private void insertConfig(final Config config)
    {
        jdbc.execute("INSERT INTO WBMS_CONFIG (ID, WAYBILL_PATH, WORK_PATH, MERGE_PATH) VALUES (?, ?, ?, ?)",
            new PreparedStatementCallback<Object>()
            {
                
                @Override
                public Object doInPreparedStatement(PreparedStatement ps)
                    throws SQLException, DataAccessException
                {
                    int index = 1;
                    ps.setString(index++, UUID.randomUUID().toString());
                    ps.setString(index++, config.getWaybillPath());
                    ps.setString(index++, config.getWorkPath());
                    ps.setString(index++, config.getMergePath());
                    return ps.execute();
                }
            });
    }
    
    private void updateConfig(final Config config)
    {
        jdbc.execute("UPDATE WBMS_CONFIG SET WAYBILL_PATH = ?, WORK_PATH = ?, MERGE_PATH = ?",
            new PreparedStatementCallback<Object>()
            {
                
                @Override
                public Object doInPreparedStatement(PreparedStatement ps)
                    throws SQLException, DataAccessException
                {
                    int index = 1;
                    ps.setString(index++, config.getWaybillPath());
                    ps.setString(index++, config.getWorkPath());
                    ps.setString(index++, config.getMergePath());
                    return ps.execute();
                }
            });
    }
}
