package com.property.manage.base.model.utils;


import com.property.manage.base.model.enums.DateUnit;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.commons.lang3.time.FastDateFormat;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AppDateUtils {

    private static Logger logger = LoggerFactory.getLogger(AppDateUtils.class);

    public static final long MILLIS_OF_MINUTE = 60 * 1000;
    public static final long MILLIS_OF_HOUR = 1000 * 3600;
    public static final long MILLIS_OF_DAY = 1000 * 3600 * 24;
    public static final String HHmmss = "HH:mm:ss";
    public static final String yyyyMMdd2 = "yyyy-MM-dd";
    public static final String yyyyMMdd3 = "yyyy/MM/dd";
    public static final String yyyyMMdd4 = "yyyy年MM月dd日";
    public static final String yyyyMMdd = "yyyyMMdd";
    public static final String MMdd = "MM-dd";
    public static final String yyyy_MM_dd_HH_mm_ss2 = "yyyy-MM-dd HH:mm:ss";
    public static final String yyyy_MM_dd_HH_mm_ss3 = "yyyy/MM/dd HH:mm:ss";
    public static final String yyyy_MM_dd_HH_mm_ss4 = "yyyy年MM月dd日 hh时mm分ss秒";
    public static final String yyyy_MM_dd_HH_mm_ss_SSS2 = "yyyy-MM-dd HH:mm:ss.SSS";
    public static final String yyyy_MM_dd_HH_mm_ss_SSS3 = "yyyy/MM/dd HH:mm:ss.SSS";
    public static final FastDateFormat ymd = FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss");
    public static final String[] DATE_FORMATS = {yyyyMMdd2, yyyyMMdd3, yyyyMMdd4, yyyyMMdd, MMdd, yyyy_MM_dd_HH_mm_ss2, yyyy_MM_dd_HH_mm_ss3, yyyy_MM_dd_HH_mm_ss4, yyyy_MM_dd_HH_mm_ss_SSS2, yyyy_MM_dd_HH_mm_ss_SSS3};
    // 最小日期组合4位 yyyy | mmdd
    public static final int MIN_DATE_LENGTH = 4;
    // 最大日期组合26位 yyyy年MM月dd日 hh时mm分ss秒SSS毫秒
    public static final int MAX_DATE_LENGTH = 26;

    public static Date parseDate(String value) {
        // 异常处理
        if (StringUtils.isBlank(value)) {
            // 中断流程
            return null;
        }
        // 如果字符串长度异常
        if (value.length() < MIN_DATE_LENGTH || value.length() > MAX_DATE_LENGTH) {
            // 中断流程
            return null;
        }
        // 循环处理
        for (String format : DATE_FORMATS) {
            // 格式化
            Date date = parseDate(value, format);
            // 取得数据
            if (null != date) {
                // 返回数据
                return date;
            }
        }
        // 异常处理
        return null;
    }

    public static Date parseDate(String value, String format) {
        try {
            // 异常处理
            if (StringUtils.isBlank(value)) {
                // 中断流程
                return null;
            }
            // 如果字符串长度异常
            if (value.length() < MIN_DATE_LENGTH || value.length() > MAX_DATE_LENGTH) {
                // 中断流程
                return null;
            }
            // 转换日期格式
            Date date = DateUtils.parseDate(value, format);
            // 异常处理
            if (null == date) {
                // 中断流程
                return null;
            }
            // 获取日期
            return date;
        } catch (Exception e) {
            //logger.error(e.getMessage());
        }
        // 中断流程
        return null;
    }

    /**
     * 计算过期时间
     *
     * @param theDate
     * @param number
     * @param unit
     * @return
     */
    public static Date addDate(Date theDate, Integer number, Integer unit) {
        // 异常处理
        if (null == theDate || null == number || null == unit) {
            // 中断流程
            return null;
        }
        // 取得单位
        DateUnit dateUnit = DateUnit.find(unit);
        // 异常处理
        if (null == dateUnit) {
            // 中断流程
            return null;
        }
        // 判断单位
        switch (dateUnit) {
            case MINUTE:
                //累加分钟
                return DateUtils.addMinutes(theDate, number);
            case HOUR:
                // 累加小时
                return DateUtils.addHours(theDate, number);
            case DAY:
                // 累加天数
                return DateUtils.addDays(theDate, number);
            case MONTH:
                // 累加月数
                return DateUtils.addMonths(theDate, number);
            default:
                break;
        }
        // 中断流程
        return null;
    }

    /**
     * n days ago
     */
    public static Date daysAgo(Date date, int n) {
        return new Date(date.getTime() - n * MILLIS_OF_DAY);
    }

    /**
     * n days ago
     */
    public static Date hourAgo(Date date, int n) {
        return new Date(date.getTime() - n * MILLIS_OF_HOUR);
    }

    /**
     * n days ago
     */
    public static Date minAgo(Date date, int n) {
        return new Date(date.getTime() - n * MILLIS_OF_MINUTE);
    }

    public static int diffYear(Date d1, Date d2) {
        int y1 = Integer.parseInt(DateFormatUtils.format(d1, "yyyy"));
        int y2 = Integer.parseInt(DateFormatUtils.format(d2, "yyyy"));
        return Math.abs(y1 - y2);
    }

    /**
     * 计算两个月之间的月份差
     *
     * @param d1
     * @param d2
     * @return
     */
    public static int diffMonths(Date d1, Date d2) {
        int m1 = Integer.parseInt(DateFormatUtils.format(d1, "MM"));
        int m2 = Integer.parseInt(DateFormatUtils.format(d2, "MM"));
        int y1 = Integer.parseInt(DateFormatUtils.format(d1, "yyyy"));
        int y2 = Integer.parseInt(DateFormatUtils.format(d2, "yyyy"));
        return Math.abs((y1 - y2) * 12 + (m1 - m2));
    }

    /**
     * 计算两个日期之间的天数差
     *
     * @param d1
     * @param d2
     * @return
     */
    public static int diffDays(Date d1, Date d2) {
        d1 = DateUtils.truncate(d1, Calendar.HOUR_OF_DAY);
        d2 = DateUtils.truncate(d2, Calendar.HOUR_OF_DAY);
        long day_one_Time = d1.getTime();
        long day_sec_Time = d2.getTime();
        long millisDiff = 0;
        if (day_one_Time >= day_sec_Time) {
            millisDiff = day_one_Time - day_sec_Time;
        } else {
            millisDiff = day_sec_Time - day_one_Time;
        }
        long daysDiff = millisDiff / 24 / 3600 / 1000;
        return Math.abs((int) daysDiff);
    }

    /**
     * 计算小时差
     *
     * @param d1
     * @param d2
     * @return
     */
    public static int diffHours(Date d1, Date d2) {
        d1 = DateUtils.truncate(d1, Calendar.HOUR_OF_DAY);
        d2 = DateUtils.truncate(d2, Calendar.HOUR_OF_DAY);
        long millisDiff = d1.getTime() - d2.getTime();
        long hoursDiff = millisDiff / 3600 / 1000;
        return Math.abs((int) hoursDiff);
    }

    /**
     * 计算分钟差
     *
     * @param d1
     * @param d2
     * @return
     */
    public static int diffMinutes(Date d1, Date d2) {
        d1 = DateUtils.truncate(d1, Calendar.MINUTE);
        d2 = DateUtils.truncate(d2, Calendar.MINUTE);
        long millisDiff = d1.getTime() - d2.getTime();
        long minutesDiff = millisDiff / 60 / 1000;
        return Math.abs((int) minutesDiff);
    }

    /**
     * 获取本周开始时间
     *
     * @return
     */
    public static Date startOfWeek() {
        Calendar cal = Calendar.getInstance();
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        return cal.getTime();
    }

    /**
     * 获取本周结束时间
     *
     * @return
     */
    public static Date endOfWeek() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(startOfWeek());
        cal.add(Calendar.DAY_OF_WEEK, 7);
        return cal.getTime();
    }

    /**
     * 获取本月开始时间
     *
     * @return
     */
    public static Date startOfMonth() {
        Calendar cal = Calendar.getInstance();
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
        return cal.getTime();
    }

    /**
     * 取得月初时间
     *
     * @param date
     * @return
     */
    public static Date startOfMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        //cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
        return cal.getTime();
    }

    /**
     * 获取本月结束时间
     *
     * @return
     */
    public static Date endOfMonth() {
        Calendar cal = Calendar.getInstance();
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        cal.set(Calendar.HOUR_OF_DAY, 24);
        return cal.getTime();
    }

    /**
     * 取得月末时间
     *
     * @param date
     * @return
     */
    public static Date endOfMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        //cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        return cal.getTime();
    }

    /**
     * 获取指定日期的前n天起始时间
     *
     * @param date
     * @param n
     * @return
     */
    public static Date startOfNDaysBefore(Date date, int n) {
        DateTime dateTime = new DateTime(date);
        return dateTime.minusDays(n).withTimeAtStartOfDay().toDate();
    }

    /**
     * 获取周数
     *
     * @param date
     * @return
     */
    public static String getWeek(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE");
        return sdf.format(date);
    }

    /**
     * 获取日期当天起始时间
     *
     * @param date
     * @return
     */
    public static Date getDateStartInDay(Date date) {
        //非空校验
        if (date == null) {
            return null;
        }
        //获取日历对象
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        //设置该日期当天的起始时间
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);

        //返回日期
        return cal.getTime();
    }

    /**
     * 获取日期当天结束时间
     *
     * @param date
     * @return
     */
    public static Date getDateEndInDay(Date date) {
        //非空校验
        if (date == null) {
            return null;
        }
        //获取日历对象
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        //设置该日期当天的结束
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 999);

        //返回日期
        return cal.getTime();
    }

    /**
     * 将两个时间从小到大的改变引用
     *
     * @param dateFrom
     * @param dateTo
     */
    public static void sortDate(Date dateFrom, Date dateTo) {
        //非空校验
        if (dateFrom == null || dateTo == null) {
            return;
        }
        //判断
        if (dateTo.before(dateFrom)) {
            Date temp = dateFrom;
            dateFrom = dateTo;
            dateTo = temp;
        }
    }

    public static void main(String[] args) {

    }
}
