package com.pm.core.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

/**
 * 时间处理类
 * 
 * @author FYL
 */
public class DateUtil {
    private static Logger LOGGER = Logger.getLogger(DateUtil.class);

    /**
     * Format String : yyyy-MM-dd HH:mm:ss
     */
    public static final String DateFormat1 = "yyyy-MM-dd HH:mm:ss";

    /**
     * Format String : yyyy-MM-dd
     */
    public static final String DateFormat2 = "yyyy-MM-dd";

    /**
     * Format String : yyyyMMdd
     */
    public static final String DateFormat3 = "yyyyMMdd";

    /**
     * Format String : yyyyMMdd HHmmss
     */
    public static final String DateFormat4 = "yyyyMMdd HHmmss";

    /**
     * Format String : yyyy-MM-dd HH:mm
     */
    public static final String DateFormat5 = "yyyy-MM-dd HH:mm";

    /**
     * Format String : yyyyMMdd HH:mm
     */
    public static final String DateFormat6 = "yyyyMMdd HH:mm";

    
    /**
     * Format String : yyyy年MM月dd日
     */
    public static final String DateFormat7 = "yyyy年MM月dd日";
    
    /**
     * 获取当前时间
     * 
     * @return Date对象
     */
    public static Date getDate() {
        Calendar calendar = Calendar.getInstance();
        return calendar.getTime();
    }
    /**
     * 根据某年某月获取最后一天
     * @param year
     * @param month
     * @return
     */
    public static String getLastDate(Integer year,Integer month){
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        int  day = calendar.getActualMaximum(Calendar.DATE);
        calendar.set(Calendar.DAY_OF_MONTH, day);
        return new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());
    }

    /**
     * 返回当前时间
     * 
     * @param format 时间格式
     * @return string 当前时间指定格式字符串
     */
    public static String getDate(String format) {
        return getStringDate(getDate(), format);
    }

    /**
     * 按照固定格式化
     * 
     * @param date Date
     * @param method 时间格式
     * @return 制定的时间格式
     */
    public static String getStringDate(Date date, String method) {
        SimpleDateFormat sdf = new SimpleDateFormat(method);
        String ret = null;
        try {
            ret = sdf.format(date);
        } catch (Exception ex) {
        }
        return ret;
    }

    /**
     * 获取前几天或者后天时间
     * 
     * @param date Date
     * @param days 天数
     * @return Date时间
     */
    public static Date getDate(Date date, int days) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_YEAR, days);
        return calendar.getTime();
    }

    /**
     * 获取前几天或者后天时间
     * 
     * @param dateStr 'yyyyMMdd'
     * @param days 天数
     * @return Date时间
     */
    public static Date getDate(String dateStr, int days) {
        return getDate(getDate(dateStr, DateFormat3), days);
    }
    
    public static Date formatDate(String stringDate, String method){
        SimpleDateFormat sdf=new SimpleDateFormat(method);
        Date ret = null;
        try {
            ret=sdf.parse(stringDate);
        } catch (Exception e) {
            LOGGER.error(e, e);
        }
        return ret;
    }
    
    /**
     * 传入String类型时间返回Date
     * 
     * @param stringDate 时间
     * @param method 格式
     * @return 返回Date
     */
    public static Date getDate(String stringDate, String method) {
        SimpleDateFormat sdf = new SimpleDateFormat(method);
        Date ret = null;
        try {
            String integerDate = stringDate.replaceAll("-", "").replaceAll("/", "").replaceAll("年", "").replaceAll("月", "").replaceAll("日", "").replaceAll("：", ":");
            ret = sdf.parse(integerDate);
        } catch (Exception ex) {
            LOGGER.error(ex, ex);
        }
        return ret;
    }

    /**
     * 获取两时间差的天数
     * 
     * @param beginDate 开始日期
     * @param endDate 结束日期
     * @return 天数
     */
    public static int getDayCount(Date beginDate, Date endDate) {
        int count = 0;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(beginDate);
        while (calendar.getTime().before(endDate)) {
            count++;
            calendar.add(Calendar.DAY_OF_YEAR, 1);
        }
        return count;
    }
   
    /**
     * 当前日期的前n个月
     * 
     * @param data
     * @return
     */
    public static String getLastMonth(Date data,int num) {
        Calendar calendar = Calendar.getInstance();
        Date date = new Date(System.currentTimeMillis());
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, num);
        date = calendar.getTime();
        return DateUtil.getStringDate(date, DateUtil.DateFormat2);
    }

    /**
     * 当前日期的后一个月
     * 
     * @param data
     * @return
     */
    public static String getNextMonth(Date data) {
        Calendar calendar = Calendar.getInstance();
        Date date = new Date(System.currentTimeMillis());
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, 1);
        date = calendar.getTime();
        return DateUtil.getStringDate(date, DateUtil.DateFormat2);
    }
    
    /**
     * 传入LONG 返回 时分秒
     * @param diff
     * @return
     */
    public static String LongToString(long diff)
    {
        String showtime = "";
        long oneSecond = 1000;
        long oneMinute = oneSecond * 60;
        long oneHour = oneMinute * 60;
        long hours = diff / oneHour;
        diff -= hours * oneHour;
        long minutes = diff / oneMinute;
        diff -= minutes * oneMinute;
        long seconds = diff / oneSecond;
        if (hours > 0) showtime += hours + "时";
        if (minutes > 0) showtime += minutes + "分";
        if (seconds > 0) showtime += seconds + "秒";
        return showtime;
    }
    
    /**
     * 根据开始时间和结束时间返回时间段内的时间集合
     * @param beginDate
     * @param endDate
     * @return List<Date>
     */
    public static List<String> getDatesBetweenTwoDate(Date beginDate, Date endDate) {    
        List<String> strList = new ArrayList<String>();
        List<Date> lDate =null;
        try {
            lDate = new ArrayList<Date>();
            lDate.add(beginDate);//把开始时间加入集合
            Calendar cal = Calendar.getInstance();
            //使用给定的 Date 设置此 Calendar 的时间
            cal.setTime(beginDate);
            boolean bContinue = true;
            while (bContinue) {
                //根据日历的规则，为给定的日历字段添加或减去指定的时间量
                cal.add(Calendar.DAY_OF_MONTH, 1);
                // 测试此日期是否在指定日期之后
                if (endDate.after(cal.getTime())) {
                    lDate.add(cal.getTime());
                } else {
                    break;
                }
            }
            lDate.add(endDate);
        } catch (Exception e) {
        }
       
        for (Date date : lDate) {
           strList.add("'"+getStringDate(date, "yyyy-MM-dd")+"'"); 
        }
        
        return strList;
    }
   
}
