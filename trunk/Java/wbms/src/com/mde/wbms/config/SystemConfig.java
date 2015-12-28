package com.mde.wbms.config;

import java.beans.PropertyVetoException;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.mde.wbms.app.Startup;

public class SystemConfig
{
    private static Logger log = LoggerFactory.getLogger(SystemConfig.class);
    
    private static final SystemConfig instance = new SystemConfig();
    
    private String executePath;
    
    private JdbcTemplate jdbc;
    
    private SystemConfig()
    {
        this.initilize();
    }
    
    public static SystemConfig getInstance()
    {
        return instance;
    }
    
    private void initilize()
    {
        try
        {
            String path =
                URLDecoder.decode(Startup.class.getProtectionDomain().getCodeSource().getLocation().getFile(), "UTF-8");
            executePath = new File(path).getParentFile().getAbsolutePath();
            ComboPooledDataSource cpds = new ComboPooledDataSource();
            cpds.setDriverClass("org.sqlite.JDBC");
            cpds.setJdbcUrl(String.format("jdbc:sqlite:%1$s/wbms.db", getExecutePath()));
            jdbc = new JdbcTemplate(cpds);
        }
        catch (UnsupportedEncodingException e)
        {
            log.error(e.getMessage(), e);
        }
        catch (PropertyVetoException e)
        {
            log.error(e.getMessage(), e);
        }
    }
    
    public String getExecutePath()
    {
        return executePath;
    }
    
    public JdbcTemplate getJdbcTemplate()
    {
        return jdbc;
    }
}
