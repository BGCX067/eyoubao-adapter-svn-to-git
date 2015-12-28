/*
 * 文件名称：IBookingService.java  下午2:56:41 2013-3-12
 * 版权说明：js.todaysoft Technologies Co., Ltd. Copyright 2010-2017, All rights reserved.
 */
package com.mde.service;

import java.util.Date;

import com.mde.model.BookingData;
import com.mde.model.BookingEntry;

/**
 * 预约管理服务接口
 *
 * @author  xuxin
 * @version 1.0, 2013-3-12
 */
public interface IBookingService
{
    int BOOKING_APPARATUS = 0;
    
    int BOOKING_APPARATUS_SUB = 1;
    
    int BOOKING_PINGPONG = 2;
    
    int BOOKING_BILLIARDS_US = 3;
    
    int BOOKING_BILLIARDS_EN = 4;
    
    int BOOKING_YOGA = 5;
    
    int WORKING_DAY = 0;
    
    int REST_DAY = 1;
    
    /**
     * 查询某一天某个项目的预约数据
     */
    BookingData getBookingData(String username, Date date, int category);
    
    BookingData getBookingData(String username, int interval, int cateogyr);
    
    BookingEntry booking(String username, Date date, String key);
}
