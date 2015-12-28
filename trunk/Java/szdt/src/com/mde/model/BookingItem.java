/*
 * 文件名称：BookingItem.java  下午2:53:39 2013-3-12
 * 版权说明：js.todaysoft Technologies Co., Ltd. Copyright 2010-2017, All rights reserved.
 */
package com.mde.model;

/**
 * 预约项目
 *
 * @author  xuxin
 * @version 1.0, 2013-3-12
 */
public class BookingItem
{
    private Integer id;
    
    private String name;
    
    // 该项目支持预约的人数
    private int count;
    
    public Integer getId()
    {
        return id;
    }
    
    public void setId(Integer id)
    {
        this.id = id;
    }
    
    public String getName()
    {
        return name;
    }
    
    public void setName(String name)
    {
        this.name = name;
    }
    
    public int getCount()
    {
        return count;
    }
    
    public void setCount(int count)
    {
        this.count = count;
    }
}
