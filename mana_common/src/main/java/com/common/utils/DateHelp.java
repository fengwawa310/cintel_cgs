package com.common.utils;

/**
 * 时间和日期的工具类
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateHelp {

    public static final String YYYYMMDD = "yyyyMMdd";
    public static final String HHMMSS = "HHmmss";
    public static final String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";
    // 默认显示日期的格式
    public static final String DATAFORMAT_STR = "yyyy-MM-dd";
    // 默认显示日期的格式
    public static final String YYYY_MM_DATAFORMAT_STR = "yyyy-MM";
    // 默认显示日期时间的格式
    public static final String DATATIMEF_STR = "yyyy-MM-dd HH:mm:ss";
    // 默认显示简体中文日期的格式
    public static final String ZHCN_DATA_FORMAT = "yyyy年MM月dd日";
    // 默认显示简体中文日期时间的格式
    public static final String ZHCN_DATATIME_FORMAT = "yyyy年MM月dd日HH时mm分ss秒";
    // 默认显示简体中文日期时间的格式
    public static final String ZHCN_DATATIME_FORMAT_4yMMddHHmm = "yyyy年MM月dd日HH时mm分";
    // 默认显示简体中文星期格式
    public static final String ZHCN_EEEE = "EEEE";
    private static Logger logger = LoggerFactory.getLogger(DateHelp.class);
    // 默认日期格式
    private static String datePattern = DATAFORMAT_STR;
    // 默认时间格式
    private static String timePattern = DATATIMEF_STR;

    /**
     * 获取当前时间（yyyy-MM-dd HH:mm:ss）
     */
    public static String getNowTime() {
        return date2Str(new Date(), timePattern);
    }

    /**
     * 获取当前时间（yyyy-MM-dd HH:mm:ss）
     */
    public static String getNowTime(String pattern) {
        return date2Str(new Date(), pattern);
    }

    /**
     * 字符串转字符串（指定格式）
     */
    public static final String str2Str(String strDate, String oldPattern, String newPattern) {
        return date2Str(str2Date(strDate,oldPattern), newPattern);
    }
    /**
     * 时间转字符串（指定格式）
     */
    public static final String date2Str(Date date, String pattern) {
        if (date == null) {
            return "";
        }
        return new SimpleDateFormat(pattern).format(date);
    }

    /**
     * 字符串转日期（指定格式）
     */
    public static final Date str2Date(String strDate, String pattern) {
        try {
            return new SimpleDateFormat(pattern).parse(strDate);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }

    /**
     * date2比date1多的天数 年月日计算
     * @param date1
     * @param date2
     * @return
     */
    public static int differentDays(Date date1,Date date2)
    {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);

        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);
        int day1= cal1.get(Calendar.DAY_OF_YEAR);
        int day2 = cal2.get(Calendar.DAY_OF_YEAR);

        int year1 = cal1.get(Calendar.YEAR);
        int year2 = cal2.get(Calendar.YEAR);
        if(year1 != year2)   //同一年
        {
            int timeDistance = 0 ;
            for(int i = year1 ; i < year2 ; i ++)
            {
                if(i%4==0 && i%100!=0 || i%400==0)    //闰年
                {
                    timeDistance += 366;
                }
                else    //不是闰年
                {
                    timeDistance += 365;
                }
            }

            return timeDistance + (day2-day1) ;
        }
        else    //不同年
        {
            System.out.println("判断day2 - day1 : " + (day2-day1));
            return day2-day1;
        }
    }

    /**
     * @Author: sky
     * @Description:将2018-03-21T16:00:00.000Z转为date类型
     * @Date: 下午1:33 2018/4/3
     * @param: strDate
    pattern
     */
    public static final Date comStr2Date(String strDate, String pattern){
        String date = strDate.replace("Z", "UTC");
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        Date d = null;
        try {
            d = format.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return d;
    }

    /**
     * 获取距离今天days天的日期
     * @param days 距离今天的天数，如：-1代表昨天，1代表明天
     * @return
     */
    public static Date getThatDay(int days) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DAY_OF_MONTH, days);
        return calendar.getTime();
    }

    /**
     * 获取距离今天days天的日期
     * @param days 距离今天的天数，如：-1代表昨天，1代表明天
     * @return
     */
    public static String getThatDay(int days, String pattern) {
        return date2Str(getThatDay(days), pattern);
    }

    /**
     * 获取距离某天days天的日期
     * @param days 距离某天的天数，如：-1代表某天的昨天，1代表某天的明天
     * @return
     */
    public static Date getThatDay(Date date, int days) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, days);
        return calendar.getTime();
    }

    /**
     * 获取距离某天days天的日期
     * @param days 距离某天的天数，如：-1代表某天的昨天，1代表某天的明天
     * @return
     */
    public static String getThatDay(Date date, int days, String thatPattern) {
        return date2Str(getThatDay(date, days), thatPattern);
    }

    /**
     * 获取距离某天days天的日期
     * @param days 距离某天的天数，如：-1代表某天的昨天，1代表某天的明天
     * @return
     */
    public static Date getThatDay(String strDate, String pattern, int days) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(str2Date(strDate, pattern));
        calendar.add(Calendar.DAY_OF_MONTH, days);
        return calendar.getTime();
    }

    /**
     * 获取距离某天days天的日期
     * @param days 距离某天的天数，如：-1代表某天的昨天，1代表某天的明天
     * @return
     */
    public static String getThatDay(String strDate, String pattern, int days, String thatPattern) {
        return date2Str(getThatDay(strDate, pattern, days), thatPattern);
    }

    /**
     * date1 在 date2 前返回 1
     * date1 在 date2 后返回 -1
     * date1 等于 date2 返回 0
     * @param date1
     * @param date2
     * @return
     */
    public static int compareDate(Date date1, Date date2) {
        if (date1.getTime() > date2.getTime()) {
            return -1;
        } else if (date1.getTime() < date2.getTime()) {
            return 1;
        } else {
            return 0;
        }
    }
    public static int compareDate(String date1, String date1Pattern, String date2, String date2Pattern) {
        Date date11 = str2Date(date1, date1Pattern);
        Date date22 = str2Date(date2, date2Pattern);
        return compareDate(date11, date22);
    }

    // 将字符串转为时间戳


    public static String getTime(String user_time) {
        String re_time = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date d;
        try {
            d = sdf.parse(user_time);
            long l = d.getTime();
            String str = String.valueOf(l);
            re_time = str.substring(0, 10);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return re_time;
    }
}
