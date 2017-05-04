package com.app.hexuegang.wsnmonitor.util;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Created by HEXG on 2017/4/9.
 */

public class DateUtil {

    public static final String ZHUHU_API_BEGIN_DATESTR = "20160101";
    public static final String DEFAULT_DATETIME_PATTERN = "yyyy-MM-dd hh:mm:ss";

    /**
     * 获取当前系统时间， 24小时制---"HH"
     *
     * @return 当前系统时间
     */
    public static String getCurrentTimeStr() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
        Date date = new Date(System.currentTimeMillis());

        return simpleDateFormat.format(date);
    }

    /**
     * 获取当前系统时间， 24小时制---"HH"
     *
     * @return 当前系统时间
     */
    public static long getCurrentTimeMills() {
        return System.currentTimeMillis();
    }

    /**
     * @param datePattenStr 日期格式
     * @return 日期字符串
     */
    public static String getCurrentTimeStr(String datePattenStr) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(datePattenStr, Locale.CHINA);
        Date date = new Date(System.currentTimeMillis());

        return simpleDateFormat.format(date);
    }


    /**
     * 获取随机日期
     *
     * @param beginDate 起始日期，格式为：yyyyMMdd
     * @param endDate   结束日期，格式为：yyyyMMdd
     * @return Date
     */
    public static Date randomDate(String beginDate, String endDate) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
            Date start = format.parse(beginDate);// 构造开始日期
            Date end = format.parse(endDate);// 构造结束日期

            // getTime()表示返回自 1970 年 1 月 1 日 00:00:00 GMT 以来此 Date 对象表示的毫秒数。
            if (start.getTime() >= end.getTime()) {
                return null;
            }

            long date = random(start.getTime(), end.getTime());

            return new Date(date);
        } catch (Exception e) {
            Log.i("LOG", e.getMessage());
        }

        return null;
    }

    public static long random(long begin, long end) {
        long rtn = begin + (long) (Math.random() * (end - begin));

        // 如果返回的是开始时间和结束时间，则递归调用本函数查找随机值
        if (rtn == begin || rtn == end) {
            return random(begin, end);
        }

        return rtn;
    }

    /**
     * 获取随机日期字符串，格式：yyyyMMdd
     *
     * @return String
     */
    public static String randomDateStr() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd", Locale.CHINA);
        Date date = randomDate(ZHUHU_API_BEGIN_DATESTR, getCurrentTimeStr("yyyyMMdd"));

        return simpleDateFormat.format(date);
    }

    /**
     * 日期类型字符串转Date日期类
     *
     * @param timeStr 日期类型字符串: 2016-04-26 15:50:23
     * @return Date
     */
    public static Date string2Date(String timeStr) {
        SimpleDateFormat simpleDateFormat =
                new SimpleDateFormat(DEFAULT_DATETIME_PATTERN);
        Date date = null;
        try {
            date = simpleDateFormat.parse(timeStr);
        } catch (ParseException e) {
            Log.i("LOG", e.getMessage());
        }

        return date;
    }

    /**
     * 判断是否为夜晚
     *
     * @return boolean
     */
    public static boolean isNight() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH");
        String hour = sdf.format(new Date());
        int k = Integer.parseInt(hour);
        if ((k >= 0 && k < 6) || (k >= 18 && k < 24)) {
            return true;
        } else {
            return false;
        }
    }

    public static String getCurrentWeekStr() {
        String[] weekDays = {"周日", "周一", "周二", "周三", "周四", "周五", "周六"};
        Date nowDate = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(nowDate);

        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;

        return weekDays[w];
    }

    public static String utc2LocalTime(String timeStr) throws ParseException {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        df.setTimeZone(TimeZone.getTimeZone("GMT"));

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
        Date date = new Date(df.parse(timeStr).toString());

        return sdf.format(date);
    }

}
