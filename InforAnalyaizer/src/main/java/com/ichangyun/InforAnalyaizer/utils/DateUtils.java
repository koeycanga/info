/**
 * Copyright 2018 畅云 http://www.ichangyun.cn
 * <p>
 * 竞争情报分析系统
 */
package com.ichangyun.InforAnalyaizer.utils;

import java.text.ParseException;
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
        Date now_date = new Date();
        if(montime.equals("0")) {  //今天
            String today = format(new Date(),DATE_PATTERN);
            str[0] = today;
            str[1] = today;
        }else if(montime.equals("-1")) {  //24小时内
            //Date cur_date = new Date();
            Date pre_date = addDateHours(now_date,-24);
            str[0] = format(pre_date,DATE_TIME_PATTERN);
            str[1] = format(now_date,DATE_TIME_PATTERN);
        }else if(montime.equals("-2")) {  //2 天内
            //  Date cur_date = addDateDays(now_date,-1);
            Date pre_date = addDateDays(now_date, Integer.parseInt(montime));
            str[0] = format(pre_date,DATE_PATTERN);
            str[1] = format(now_date,DATE_PATTERN);
        }else if(montime.equals("-3")) {  // 3 天内
            //  Date cur_date = addDateDays(now_date,-2);
            Date pre_date = addDateDays(now_date, Integer.parseInt(montime));
            str[0] = format(pre_date,DATE_PATTERN);
            str[1] = format(now_date,DATE_PATTERN);
        }else if(montime.equals("-7")) {  //7 天内
            //  Date cur_date = addDateDays(now_date,-3);
            Date pre_date = addDateDays(now_date, Integer.parseInt(montime));
            str[0] = format(pre_date,DATE_PATTERN);
            str[1] = format(now_date,DATE_PATTERN);
        }else if(montime.equals("-10")) {  //10 天内
            //  Date cur_date = addDateDays(now_date,-7);
            Date pre_date = addDateDays(now_date, Integer.parseInt(montime));
            str[0] = format(pre_date,DATE_PATTERN);
            str[1] = format(now_date,DATE_PATTERN);
        }else{ //自定义
            String[] ss  = montime.split("-");
            if(ss.length==3&&montime.endsWith("-")) {
            	str[0] = ss[0]+"/"+ss[1]+"/"+ss[2];
            	str[1] = ""; 
            }else if(ss.length==3&&montime.startsWith("-")) {
            	str[1] = ss[0]+"/"+ss[1]+"/"+ss[2];
            	str[0] = ""; 
            }else if(ss.length==6) {
                str[1] = ss[3]+"/"+ss[4]+"/"+ss[5];
                str[0] = ss[0]+"/"+ss[1]+"/"+ss[2];
            }else {
                str[0] = "";
                str[1] = "";
            }
        }
        return str;
    }


    /**
     * 根据montime类型获得时间段集合
     * 1天内 每两小时为分隔   得到结果如  00:00,02:00,04:00......
     * 超过1天的以天为单位分隔    得到结果如  2018/12/17,2018/12/18......
     * @param montime
     * @return
     */
    public static String[] dealMontimeTimeDatas(String montime) {
        String[] sss = new String[2];
        StringBuilder res = new StringBuilder("");
        if(montime.equals("0")) {  //今天
            sss[0] = "hour";
            String Hour = format(new Date(),"HH");
            int flag = Integer.parseInt(Hour);
            if(flag%2!=0) {
                flag++;
                Hour = dealHour(flag);
            }
            for(int i=0;i<=flag;i+=2) {
                if(i==0) {
                    res.append(""+dealHour(i)+":00");
                }else {
                    res.append(","+dealHour(i)+":00");
                }
            }

        }else if(montime.equals("-1")) {  //24小时内
            sss[0] = "hour";
            Date date = new Date();
            Date lastDate = addDateHours(date,-24);
            int start = Integer.parseInt(format(lastDate,"HH"));
            if(start%2!=0) {
                start++;
            }
            int end = Integer.parseInt(format(date,"HH"))+24;
            if(end%2!=0) {
                end++;
            }
            for(int i=start;i<=end;i+=2) {
                if(i==start) {
                    res.append(dealHour24(i,date,lastDate));
                }else {
                    res.append(","+dealHour24(i,date,lastDate));
                }
            }

        }else if(montime.equals("-2")||montime.equals("-3")||montime.equals("-7")||montime.equals("-10")) {  //2 3 7 10 天内
            sss[0] = "day";
            Date date = new Date();

            for(int i=Integer.parseInt(montime);i<=0;i++) {
                Date pre_date = addDateDays(date, i);
                if(i==Integer.parseInt(montime)) {
                    res.append(format(pre_date,DATE_PATTERN));
                }else {
                    res.append(","+format(pre_date,DATE_PATTERN));
                }
            }

        }else{ //自定义

            String[] ss  = montime.split("-");
            String[] str = new String[2];
            if(ss.length==6) {
                str[1] = ss[3]+"/"+ss[4]+"/"+ss[5]+" 23:59:59";
                str[0] = ss[0]+"/"+ss[1]+"/"+ss[2]+" 00:00:00";
            }else {
                String today = format(new Date(),DATE_PATTERN);
                str[0] = today+" 00:00:00";
                str[1] = today+" 23:59:59";
            }

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

            try {
                Date startDate = sdf.parse(str[0]);
                Date endDate = sdf.parse(str[1]);

                int cz = differentDaysByMillisecond(startDate,endDate);

                if(cz==0) {  //今天
                    sss[0] = "hour";
                    String Hour = format(new Date(),"HH");
                    int flag = Integer.parseInt(Hour);
                    if(flag%2!=0) {
                        flag++;
                        Hour = dealHour(flag);
                    }
                    for(int i=0;i<=flag;i+=2) {
                        if(i==0) {
                            res.append(""+dealHour(i)+":00");
                        }else {
                            res.append(","+dealHour(i)+":00");
                        }
                    }
                }else if(cz==1) { //1天内
                    sss[0] = "hour";
                    SimpleDateFormat sdff = new SimpleDateFormat("yyyy/MM/dd");
                    Date date = new Date();
                    Date lastDate = sdff.parse(format(addDateHours(date,-24),DATE_PATTERN)+" 00:00:01");
                    int start = Integer.parseInt(format(lastDate,"HH"));
                    int end = Integer.parseInt(format(date,"HH"))+24;

                    for(int i=start;i<=end;i+=2) {
                        if(i==start) {
                            res.append(dealHour24(i,date,lastDate));
                        }else {
                            res.append(","+dealHour24(i,date,lastDate));
                        }
                    }
                }else {
                    sss[0] = "day";
                    for(int i=-cz;i<=0;i++) {
                        Date pre_date = addDateDays(endDate, i);
                        if(i==-cz) {
                            res.append(format(pre_date,DATE_PATTERN));
                        }else {
                            res.append(","+format(pre_date,DATE_PATTERN));
                        }
                    }
                }

            } catch (ParseException e) {
                e.printStackTrace();
            }


        }

        sss[1] = res.toString();
        return sss;//res.toString();
    }

    /**
     * date2比date1多的天数
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
            return day2-day1;
        }
    }

    /**
     * 通过时间秒毫秒数判断两个时间的间隔
     * @param date1
     * @param date2
     * @return
     */
    public static int differentDaysByMillisecond(Date date1,Date date2)
    {
        int days = (int) ((date2.getTime() - date1.getTime()) / (1000*3600*24));
        return days;
    }

    private static String dealHour24(int i, Date date, Date lastDate) {
        if(i<24) {  //昨天
            return format(lastDate,DATE_PATTERN)+" "+dealHour(i)+":00";
        }else {  //今天
            return format(date,DATE_PATTERN)+" "+dealHour(i-24)+":00";
        }
    }

    private static String dealHour(int i) {
        if(i<10) {
            return "0"+i;
        }else {
            return ""+i;
        }
    }

}