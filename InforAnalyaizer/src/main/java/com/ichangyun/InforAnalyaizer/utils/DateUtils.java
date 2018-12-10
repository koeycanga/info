/**
 * Copyright 2018 畅云 http://www.ichangyun.cn
 * <p>
 * 竞争情报分析系统
 */
package com.ichangyun.InforAnalyaizer.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * DateUtils：日期处理
 *
 * @author yunx
 * @date 2018年11月9日
 */
public class DateUtils {

    /** 时间格式(yyyy-MM-dd) */
    public final static String DATE_PATTERN = "yyyy/MM/dd";

    /** 时间格式(yyyy-MM-dd HH:mm:ss) */
    public final static String DATE_TIME_PATTERN = "yyyy/MM/dd HH:mm:ss";

    /**
     * DateUtils：系统时间取得
     * @return 系统时间格式(yyyy-MM-dd HH:mm:ss)
     */
    public static String getCurrentDateTime() {
        Date dateTemp = new Date();
        SimpleDateFormat sdfTemp = new SimpleDateFormat(DATE_TIME_PATTERN);
        return sdfTemp.format(dateTemp);
    }

    /**
     * DateUtils:日期格式化(格式为：yyyy-MM-dd)
     * @param date  日期
     * @return yyyy-MM-dd格式日期
     */
    public static String format(Date date) {
        return format(date, DATE_PATTERN);
    }

    /**
     * DateUtils:指定格式的日期格式化
     * @param date  日期
     * @param pattern 格式，如：DateUtils.DATE_TIME_PATTERN
     * @return 指定格式的日期
     */
    public static String format(Date date, String pattern) {
        if(date != null){
            SimpleDateFormat df = new SimpleDateFormat(pattern);
            return df.format(date);
        }
        return null;
    }

    /**
     * DateUtil:对日期的【秒】进行加/减
     *
     * @param date 日期
     * @param seconds 秒数，负数为减
     * @return 加/减几秒后的日期
     */
    public static Date addDateSeconds(Date date, int seconds) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);  // 设置起时间
        cal.add(Calendar.SECOND, seconds); // 加/减秒
        return cal.getTime();
    }

    /**
     * DateUtil:对日期的【分钟】进行加/减
     *
     * @param date 日期
     * @param minutes 分钟数，负数为减
     * @return 加/减几分钟后的日期
     */
    public static Date addDateMinutes(Date date, int minutes) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);  // 设置起时间
        cal.add(Calendar.MINUTE, minutes); // 加/减小时
        return cal.getTime();
    }

    /**
     * DateUtil:对日期的【小时】进行加/减
     *
     * @param date 日期
     * @param hours 小时数，负数为减
     * @return 加/减几小时后的日期
     */
    public static Date addDateHours(Date date, int hours) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);  // 设置起时间
        cal.add(Calendar.HOUR, hours); // 加/减小时
        return cal.getTime();
    }

    /**
     * DateUtil:对日期的【天】进行加/减
     *
     * @param date 日期
     * @param days 天数，负数为减
     * @return 加/减几天后的日期
     */
    public static Date addDateDays(Date date, int days) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);  // 设置起时间
        cal.add(Calendar.DATE, days); // 天
        return cal.getTime();
    }

    /**
     * DateUtil:对日期的【周】进行加/减
     *
     * @param date 日期
     * @param weeks 周数，负数为减
     * @return 加/减几周后的日期
     */
    public static Date addDateWeeks(Date date, int weeks) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);  // 设置起时间
        cal.add(Calendar.WEDNESDAY, weeks); // 加/减周
        return cal.getTime();
    }

    /**
     * DateUtil:对日期的【月】进行加/减
     *
     * @param date 日期
     * @param months 月数，负数为减
     * @return 加/减几月后的日期
     */
    public static Date addDateMonths(Date date, int months) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);  // 设置起时间
        cal.add(Calendar.MONTH, months); // 加/减月
        return cal.getTime();
    }

    /**
     * DateUtil:对日期的【年】进行加/减
     *
     * @param date 日期
     * @param years 年数，负数为减
     * @return 加/减几年后的日期
     */
    public static Date addDateYears(Date date, int years) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);// 设置起时间
        cal.add(Calendar.YEAR, years); // 加/减年
        return cal.getTime();
    }
    
    /**
	 * 格式化请求时间
	 * @param montime
	 * @return 格式化之后的时间
	 */
	public static String[] dealMontime(String montime) {
		
		String[] str = new String[2];
		
		if(montime.equals("0")) {  //今天
			String today = format(new Date(),DATE_PATTERN);
			str[0] = today+" 00:00:00";
			str[1] = today+" 23:59:59";
		}else if(montime.equals("-1")) {  //24小时内
			Date cur_date = new Date();
			Date pre_date = addDateHours(cur_date,-24);
			str[0] = format(pre_date,DATE_TIME_PATTERN);
			str[1] = format(cur_date,DATE_TIME_PATTERN);
		}else if(montime.equals("-2")||montime.equals("-3")||montime.equals("-7")||montime.equals("-10")) {  //2 3 7 10 天内
			Date cur_date = new Date();
			Date pre_date = addDateDays(cur_date, Integer.parseInt(montime));
			str[0] = format(pre_date,DATE_PATTERN+" 00:00:00");
			str[1] = format(cur_date,DATE_TIME_PATTERN);
		}else{ //自定义
			String[] ss  = montime.split("-");
			if(ss.length==6) {
				str[1] = ss[3]+"-"+ss[4]+"-"+ss[5]+" 23:59:59";
				str[0] = ss[0]+"-"+ss[1]+"-"+ss[2]+" 00:00:00";
			}else {
				String today = format(new Date(),DATE_PATTERN);
				str[0] = today+" 00:00:00";
				str[1] = today+" 23:59:59";
			}
		}
		return str;
	}
    
}