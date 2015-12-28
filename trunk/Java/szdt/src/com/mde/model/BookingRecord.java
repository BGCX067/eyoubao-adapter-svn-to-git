/*
 * 文件名称：BookingRecord.java  下午3:32:32 2013-3-12
 * 版权说明：js.todaysoft Technologies Co., Ltd. Copyright 2010-2017, All rights reserved.
 */
package com.mde.model;

/**
 * 预约记录
 *
 * @author  xuxin
 * @version 1.0, 2013-3-12
 */
public class BookingRecord
{
    private Integer intervalId;
    
    private Integer itemId;
    
    private String username;
    
    public String getUsername()
    {
        return username;
    }
    
    public void setUsername(String username)
    {
        this.username = username;
    }
    
    public Integer getIntervalId()
    {
        return intervalId;
    }
    
    public void setIntervalId(Integer intervalId)
    {
        this.intervalId = intervalId;
    }
    
    public Integer getItemId()
    {
        return itemId;
    }
    
    public void setItemId(Integer itemId)
    {
        this.itemId = itemId;
    }
}
