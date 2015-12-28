/*
 * 文件名称：BookingInterval.java  下午2:52:02 2013-3-12
 * 版权说明：js.todaysoft Technologies Co., Ltd. Copyright 2010-2017, All rights reserved.
 */
package com.mde.model;

/**
 * 预约时段
 *
 * @author  xuxin
 * @version 1.0, 2013-3-12
 */
public class BookingInterval
{
    private Integer id;
    
    private String start;
    
    private String end;
    
    public Integer getId()
    {
        return id;
    }
    
    public void setId(Integer id)
    {
        this.id = id;
    }
    
    public String getStart()
    {
        return start;
    }
    
    public void setStart(String start)
    {
        this.start = start;
    }
    
    public String getEnd()
    {
        return end;
    }
    
    public void setEnd(String end)
    {
        this.end = end;
    }
}
