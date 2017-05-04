package com.app.hexuegang.wsnmonitor.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import java.util.regex.Pattern;

/**
 * Created by hexuegang on 2017/1/12.
 */

public class StringUtils {

    private static final Pattern emailer = Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");
    private static final Pattern phone = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
    private static final ThreadLocal<SimpleDateFormat> dateFormater = new ThreadLocal() {
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }
    };
    private static final ThreadLocal<SimpleDateFormat> dateFormater2 = new ThreadLocal() {
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd");
        }
    };

    public StringUtils() {
    }

    public static boolean isEmpty(CharSequence input) {
        if(input != null && !"".equals(input)) {
            for(int i = 0; i < input.length(); ++i) {
                char c = input.charAt(i);
                if(c != 32 && c != 9 && c != 13 && c != 10) {
                    return false;
                }
            }

            return true;
        } else {
            return true;
        }
    }

    public static boolean isEmpty(CharSequence... strs) {
        CharSequence[] var1 = strs;
        int var2 = strs.length;

        for(int var3 = 0; var3 < var2; ++var3) {
            CharSequence str = var1[var3];
            if(isEmpty(str)) {
                return true;
            }
        }

        return false;
    }

    public static boolean isEmail(CharSequence email) {
        return isEmpty(email)?false:emailer.matcher(email).matches();
    }

    public static boolean isPhone(CharSequence phoneNum) {
        return isEmpty(phoneNum)?false:phone.matcher(phoneNum).matches();
    }

    public static String getDataTime(String format) {
        SimpleDateFormat df = new SimpleDateFormat(format);
        return df.format(new Date());
    }

    public static int toInt(String str, int defValue) {
        try {
            return Integer.parseInt(str);
        } catch (Exception var3) {
            return defValue;
        }
    }

    public static int toInt(Object obj) {
        return obj == null?0:toInt(obj.toString(), 0);
    }

    public static long toLong(String obj) {
        try {
            return Long.parseLong(obj);
        } catch (Exception var2) {
            return 0L;
        }
    }

    public static double toDouble(String obj) {
        try {
            return Double.parseDouble(obj);
        } catch (Exception var2) {
            return 0.0D;
        }
    }

    public static boolean toBool(String b) {
        try {
            return Boolean.parseBoolean(b);
        } catch (Exception var2) {
            return false;
        }
    }

    public static boolean isNumber(CharSequence str) {
        try {
            Integer.parseInt(str.toString());
            return true;
        } catch (Exception var2) {
            return false;
        }
    }

    public static final String byteArrayToHexString(byte[] data) {
        StringBuilder sb = new StringBuilder(data.length * 2);
        byte[] var2 = data;
        int var3 = data.length;

        for(int var4 = 0; var4 < var3; ++var4) {
            byte b = var2[var4];
            int v = b & 255;
            if(v < 16) {
                sb.append('0');
            }

            sb.append(Integer.toHexString(v));
        }

        return sb.toString().toUpperCase(Locale.getDefault());
    }

    public static byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] d = new byte[len / 2];

        for(int i = 0; i < len; i += 2) {
            d[i / 2] = (byte)((Character.digit(s.charAt(i), 16) << 4) + Character.digit(s.charAt(i + 1), 16));
        }

        return d;
    }

    public static String friendlyTime(String sdate) {
        Date time = null;
        if(isInEasternEightZones()) {
            time = toDate(sdate);
        } else {
            time = transformTime(toDate(sdate), TimeZone.getTimeZone("GMT+08"), TimeZone.getDefault());
        }

        if(time == null) {
            return "Unknown";
        } else {
            String ftime = "";
            Calendar cal = Calendar.getInstance();
            String curDate = ((SimpleDateFormat)dateFormater2.get()).format(cal.getTime());
            String paramDate = ((SimpleDateFormat)dateFormater2.get()).format(time);
            if(curDate.equals(paramDate)) {
                int lt1 = (int)((cal.getTimeInMillis() - time.getTime()) / 3600000L);
                if(lt1 == 0) {
                    ftime = Math.max((cal.getTimeInMillis() - time.getTime()) / 60000L, 1L) + "分钟前";
                } else {
                    ftime = lt1 + "小时前";
                }

                return ftime;
            } else {
                long lt = time.getTime() / 86400000L;
                long ct = cal.getTimeInMillis() / 86400000L;
                int days = (int)(ct - lt);
                if(days == 0) {
                    int hour = (int)((cal.getTimeInMillis() - time.getTime()) / 3600000L);
                    if(hour == 0) {
                        ftime = Math.max((cal.getTimeInMillis() - time.getTime()) / 60000L, 1L) + "分钟前";
                    } else {
                        ftime = hour + "小时前";
                    }
                } else if(days == 1) {
                    ftime = "昨天";
                } else if(days == 2) {
                    ftime = "前天 ";
                } else if(days > 2 && days < 31) {
                    ftime = days + "天前";
                } else if(days >= 31 && days <= 62) {
                    ftime = "一个月前";
                } else if(days > 62 && days <= 93) {
                    ftime = "2个月前";
                } else if(days > 93 && days <= 124) {
                    ftime = "3个月前";
                } else {
                    ftime = ((SimpleDateFormat)dateFormater2.get()).format(time);
                }

                return ftime;
            }
        }
    }

    public static Date toDate(String sdate) {
        return toDate(sdate, (SimpleDateFormat)dateFormater.get());
    }

    public static Date toDate(String sdate, SimpleDateFormat dateFormater) {
        try {
            return dateFormater.parse(sdate);
        } catch (ParseException var3) {
            return null;
        }
    }

    public static boolean isInEasternEightZones() {
        boolean defaultVaule = true;
        if(TimeZone.getDefault() == TimeZone.getTimeZone("GMT+08")) {
            defaultVaule = true;
        } else {
            defaultVaule = false;
        }

        return defaultVaule;
    }

    public static Date transformTime(Date date, TimeZone oldZone, TimeZone newZone) {
        Date finalDate = null;
        if(date != null) {
            int timeOffset = oldZone.getOffset(date.getTime()) - newZone.getOffset(date.getTime());
            finalDate = new Date(date.getTime() - (long)timeOffset);
        }

        return finalDate;
    }

}
