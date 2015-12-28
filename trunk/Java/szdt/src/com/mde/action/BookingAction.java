/*
 * 文件名称：BookAction.java  下午2:33:39 2013-3-12
 * 版权说明：js.todaysoft Technologies Co., Ltd. Copyright 2010-2017, All rights reserved.
 */
package com.mde.action;

import java.util.Date;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.JsonObject;
import com.mde.model.BookingData;
import com.mde.model.BookingEntry;
import com.mde.service.IBookingService;
import com.mde.util.ResponseUtil;

/**
 * 处理预约相关请求
 *
 * @author  xuxin
 * @version 1.0, 2013-3-12
 */
@Controller
@RequestMapping("/booking")
public class BookingAction extends BaseAction
{
    @Autowired
    private IBookingService bookingService;
    
    @RequestMapping("/pingpong.do")
    public String pingpong(ModelMap model, HttpSession session)
    {
        BookingData data0 =
            bookingService.getBookingData(getCurrentUsername(session), 0, IBookingService.BOOKING_PINGPONG);
        BookingData data1 =
            bookingService.getBookingData(getCurrentUsername(session), 1, IBookingService.BOOKING_PINGPONG);
        BookingData data2 =
            bookingService.getBookingData(getCurrentUsername(session), 2, IBookingService.BOOKING_PINGPONG);
        model.addAttribute("data_0", data0);
        model.addAttribute("data_1", data1);
        model.addAttribute("data_2", data2);
        return "pingpong";
    }
    
    @RequestMapping("/apparatus.do")
    public String apparatus(ModelMap model, HttpSession session)
    {
        BookingData data0 =
            bookingService.getBookingData(getCurrentUsername(session), 0, IBookingService.BOOKING_APPARATUS);
        BookingData data1 =
            bookingService.getBookingData(getCurrentUsername(session), 1, IBookingService.BOOKING_APPARATUS);
        BookingData data2 =
            bookingService.getBookingData(getCurrentUsername(session), 2, IBookingService.BOOKING_APPARATUS);
        model.addAttribute("data_0", data0);
        model.addAttribute("data_1", data1);
        model.addAttribute("data_2", data2);
        return "apparatus";
    }
    
    @RequestMapping("/treadmill.do")
    public String treadmill(ModelMap model, HttpSession session)
    {
        BookingData data0 =
            bookingService.getBookingData(getCurrentUsername(session), 0, IBookingService.BOOKING_APPARATUS_SUB);
        BookingData data1 =
            bookingService.getBookingData(getCurrentUsername(session), 1, IBookingService.BOOKING_APPARATUS_SUB);
        BookingData data2 =
            bookingService.getBookingData(getCurrentUsername(session), 2, IBookingService.BOOKING_APPARATUS_SUB);
        model.addAttribute("data_0", data0);
        model.addAttribute("data_1", data1);
        model.addAttribute("data_2", data2);
        return "paobuji";
    }
    
    @RequestMapping("/billiards.do")
    public String billiards(ModelMap model, HttpSession session)
    {
        BookingData usdata0 =
            bookingService.getBookingData(getCurrentUsername(session), 0, IBookingService.BOOKING_BILLIARDS_US);
        BookingData usdata1 =
            bookingService.getBookingData(getCurrentUsername(session), 1, IBookingService.BOOKING_BILLIARDS_US);
        BookingData usdata2 =
            bookingService.getBookingData(getCurrentUsername(session), 2, IBookingService.BOOKING_BILLIARDS_US);
        model.addAttribute("us_data_0", usdata0);
        model.addAttribute("us_data_1", usdata1);
        model.addAttribute("us_data_2", usdata2);
        BookingData endata0 =
            bookingService.getBookingData(getCurrentUsername(session), 0, IBookingService.BOOKING_BILLIARDS_EN);
        BookingData endata1 =
            bookingService.getBookingData(getCurrentUsername(session), 1, IBookingService.BOOKING_BILLIARDS_EN);
        BookingData endata2 =
            bookingService.getBookingData(getCurrentUsername(session), 2, IBookingService.BOOKING_BILLIARDS_EN);
        model.addAttribute("en_data_0", endata0);
        model.addAttribute("en_data_1", endata1);
        model.addAttribute("en_data_2", endata2);
        return "billiards";
    }
    
    @RequestMapping("/yoga.do")
    public String yoga(ModelMap model, HttpSession session)
    {
        BookingData data0 = bookingService.getBookingData(getCurrentUsername(session), 0, IBookingService.BOOKING_YOGA);
        BookingData data1 = bookingService.getBookingData(getCurrentUsername(session), 1, IBookingService.BOOKING_YOGA);
        BookingData data2 = bookingService.getBookingData(getCurrentUsername(session), 2, IBookingService.BOOKING_YOGA);
        model.addAttribute("data_0", data0);
        model.addAttribute("data_1", data1);
        model.addAttribute("data_2", data2);
        return "yoga";
    }
    
    @RequestMapping("/booking_list")
    public String list()
    {
        return "booking_list";
    }
    
    @RequestMapping("/booking.do")
    public void booking(String key, HttpServletResponse response, HttpSession session)
    {
        JsonObject jsonObject = new JsonObject();
        
        try
        {
            BookingEntry entry = bookingService.booking(getCurrentUsername(session), new Date(), key);
            jsonObject.addProperty("success", true);
            jsonObject.addProperty("count", entry.getItem().getCount());
            jsonObject.addProperty("bookingable", entry.isBookingable());
            jsonObject.addProperty("records", entry.getRecords().size());
        }
        catch (Exception e)
        {
            jsonObject.addProperty("success", false);
            jsonObject.addProperty("message", e.getMessage());
        }
        
        ResponseUtil.renderJson(response, jsonObject.toString());
    }
}
