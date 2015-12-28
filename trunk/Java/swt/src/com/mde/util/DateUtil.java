package com.mde.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public abstract class DateUtil
{
    /**
      * <p>格式化日期对象</p>
      * @param date 日期对象
      * @param pattern 格式化的格式
      * @return 格式化之后的值
     */
    public static String format(Date date, String pattern)
    {
        return new SimpleDateFormat(pattern).format(date);
    }
    
    /**
      * <p>将日期转换为当天的开始时间。即时分秒设置为00:00:00</p>
      * @param date 日期对象
      * @return 当天的开始时间
     */
    public static Date toStartDate(Date date)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }
    
    /**
      * <p>将日期转换为当天的结束时间。即时分秒设置为23:59:59</p>
      * @param date 日期对象
      * @return 当天的结束时间
     */
    public static Date toEndDate(Date date)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        return calendar.getTime();
    }
    
    /**
     * <p>返回指定日期经过相应属性的间隔之后的日期对象</p>
     * @param base 基准时间
     * @param field Calendar中相应的属性
     * @param interval 间隔
     * @return 指定间隔后的日期
    */
    public static int getInterval(Date base, Date date)
    {
        base = toStartDate(base);
        date = toStartDate(date);
        GregorianCalendar baseCalendar = new GregorianCalendar();
        GregorianCalendar calendar = new GregorianCalendar();
        baseCalendar.setTime(base);
        calendar.setTime(date);
        return (int)((calendar.getTimeInMillis() - baseCalendar.getTimeInMillis()) / (1000 * 3600 * 24));
    }
}
