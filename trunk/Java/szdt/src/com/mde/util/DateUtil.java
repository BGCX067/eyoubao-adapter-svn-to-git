package com.mde.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public abstract class DateUtil
{
    private static final SimpleDateFormat DB_FORMAT = new SimpleDateFormat("yyyyMMddHHmmss");
    
    public static Long format(Date date)
    {
        return Long.parseLong(DB_FORMAT.format(date));
    }
    
    public static Date parse(Long date)
    {
        try
        {
            return DB_FORMAT.parse(String.valueOf(date));
        }
        catch (ParseException e)
        {
            return null;
        }
    }
    
    public static String format(Long date, String pattern)
    {
        try
        {
            Date d = DB_FORMAT.parse(String.valueOf(date));
            return format(d, pattern);
        }
        catch (ParseException e)
        {
            return "";
        }
    }
    
    public static String format(Date date, String pattern)
    {
        return new SimpleDateFormat(pattern).format(date);
    }
    
    public static String formatDate(Date date)
    {
        return format(date, "yyyy-MM-dd");
    }
    
    public static Date toStartDate(Date date)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }
    
    public static Date toEndDate(Date date)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        return calendar.getTime();
    }
    
    public static int getInterval(Date base, Date date)
    {
        GregorianCalendar baseCalendar = new GregorianCalendar();
        GregorianCalendar calendar = new GregorianCalendar();
        baseCalendar.setTime(base);
        calendar.setTime(date);
        return (int)((calendar.getTimeInMillis() - baseCalendar.getTimeInMillis()) / (1000 * 3600 * 24));
    }
    
    public static Date getDate(Date base, int field, int interval)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(base);
        calendar.set(field, calendar.get(field) + interval);
        return calendar.getTime();
    }
}
