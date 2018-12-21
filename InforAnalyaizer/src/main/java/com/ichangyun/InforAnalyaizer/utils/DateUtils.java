/**
 * Copyright 2018 ���� http://www.ichangyun.cn
 * <p>
 * �����鱨����ϵͳ
 */
package com.ichangyun.InforAnalyaizer.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * DateUtils�����ڴ���
 *
 * @author yunx
 * @date 2018��11��9��
 */
public class DateUtils {

    /** ʱ���ʽ(yyyy-MM-dd) */
    public final static String DATE_PATTERN = "yyyy/MM/dd";

    /** ʱ���ʽ(yyyy-MM-dd HH:mm:ss) */
    public final static String DATE_TIME_PATTERN = "yyyy/MM/dd HH:mm:ss";

    /**
     * DateUtils��ϵͳʱ��ȡ��
     * @return ϵͳʱ���ʽ(yyyy-MM-dd HH:mm:ss)
     */
    public static String getCurrentDateTime() {
        Date dateTemp = new Date();
        SimpleDateFormat sdfTemp = new SimpleDateFormat(DATE_TIME_PATTERN);
        return sdfTemp.format(dateTemp);
    }

    /**
     * DateUtils:���ڸ�ʽ��(��ʽΪ��yyyy-MM-dd)
     * @param date  ����
     * @return yyyy-MM-dd��ʽ����
     */
    public static String format(Date date) {
        return format(date, DATE_PATTERN);
    }

    /**
     * DateUtils:ָ����ʽ�����ڸ�ʽ��
     * @param date  ����
     * @param pattern ��ʽ���磺DateUtils.DATE_TIME_PATTERN
     * @return ָ����ʽ������
     */
    public static String format(Date date, String pattern) {
        if(date != null){
            SimpleDateFormat df = new SimpleDateFormat(pattern);
            return df.format(date);
        }
        return null;
    }

    /**
     * DateUtil:�����ڵġ��롿���м�/��
     *
     * @param date ����
     * @param seconds ����������Ϊ��
     * @return ��/������������
     */
    public static Date addDateSeconds(Date date, int seconds) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);  // ������ʱ��
        cal.add(Calendar.SECOND, seconds); // ��/����
        return cal.getTime();
    }

    /**
     * DateUtil:�����ڵġ����ӡ����м�/��
     *
     * @param date ����
     * @param minutes ������������Ϊ��
     * @return ��/�������Ӻ������
     */
    public static Date addDateMinutes(Date date, int minutes) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);  // ������ʱ��
        cal.add(Calendar.MINUTE, minutes); // ��/��Сʱ
        return cal.getTime();
    }

    /**
     * DateUtil:�����ڵġ�Сʱ�����м�/��
     *
     * @param date ����
     * @param hours Сʱ��������Ϊ��
     * @return ��/����Сʱ�������
     */
    public static Date addDateHours(Date date, int hours) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);  // ������ʱ��
        cal.add(Calendar.HOUR, hours); // ��/��Сʱ
        return cal.getTime();
    }

    /**
     * DateUtil:�����ڵġ��졿���м�/��
     *
     * @param date ����
     * @param days ����������Ϊ��
     * @return ��/������������
     */
    public static Date addDateDays(Date date, int days) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);  // ������ʱ��
        cal.add(Calendar.DATE, days); // ��
        return cal.getTime();
    }

    /**
     * DateUtil:�����ڵġ��ܡ����м�/��
     *
     * @param date ����
     * @param weeks ����������Ϊ��
     * @return ��/�����ܺ������
     */
    public static Date addDateWeeks(Date date, int weeks) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);  // ������ʱ��
        cal.add(Calendar.WEDNESDAY, weeks); // ��/����
        return cal.getTime();
    }

    /**
     * DateUtil:�����ڵġ��¡����м�/��
     *
     * @param date ����
     * @param months ����������Ϊ��
     * @return ��/�����º������
     */
    public static Date addDateMonths(Date date, int months) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);  // ������ʱ��
        cal.add(Calendar.MONTH, months); // ��/����
        return cal.getTime();
    }

    /**
     * DateUtil:�����ڵġ��꡿���м�/��
     *
     * @param date ����
     * @param years ����������Ϊ��
     * @return ��/������������
     */
    public static Date addDateYears(Date date, int years) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);// ������ʱ��
        cal.add(Calendar.YEAR, years); // ��/����
        return cal.getTime();
    }
    
    /**
	 * ��ʽ������ʱ��
	 * @param montime
	 * @return ��ʽ��֮���ʱ��
	 */
	public static String[] dealMontime(String montime) {
		
		String[] str = new String[2];
		
		if(montime.equals("0")) {  //����
			String today = format(new Date(),DATE_PATTERN);
			str[0] = today+" 00:00:00";
			str[1] = today+" 23:59:59";
		}else if(montime.equals("-1")) {  //24Сʱ��
			Date cur_date = new Date();
			Date pre_date = addDateHours(cur_date,-24);
			str[0] = format(pre_date,DATE_TIME_PATTERN);
			str[1] = format(cur_date,DATE_TIME_PATTERN);
		}else if(montime.equals("-2")||montime.equals("-3")||montime.equals("-7")||montime.equals("-10")) {  //2 3 7 10 ����
			Date cur_date = new Date();
			Date pre_date = addDateDays(cur_date, Integer.parseInt(montime));
			str[0] = format(pre_date,DATE_PATTERN+" 00:00:00");
			str[1] = format(cur_date,DATE_TIME_PATTERN);
		}else{ //�Զ���
			String[] ss  = montime.split("-");
			if(ss.length==6) {
				str[1] = ss[3]+"/"+ss[4]+"/"+ss[5]+" 23:59:59";
				str[0] = ss[0]+"/"+ss[1]+"/"+ss[2]+" 00:00:00";
			}else {
				String today = format(new Date(),DATE_PATTERN);
				str[0] = today+" 00:00:00";
				str[1] = today+" 23:59:59";
			}
		}
		return str;
	}

	
	/**
	 * ����montime���ͻ��ʱ��μ���
	 * 1���� ÿ��СʱΪ�ָ�   �õ������  00:00,02:00,04:00......
	 * ����1�������Ϊ��λ�ָ�    �õ������  2018/12/17,2018/12/18......    
	 * @param montime
	 * @return
	 */
	public static String[] dealMontimeTimeDatas(String montime) {
		String[] sss = new String[2];
		StringBuilder res = new StringBuilder("");
		if(montime.equals("0")) {  //����
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
			
		}else if(montime.equals("-1")) {  //24Сʱ��
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
			
		}else if(montime.equals("-2")||montime.equals("-3")||montime.equals("-7")||montime.equals("-10")) {  //2 3 7 10 ����
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
			
		}else{ //�Զ���
			
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
				
				if(cz==0) {  //����
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
				}else if(cz==1) { //1����
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
     * date2��date1�������
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
        if(year1 != year2)   //ͬһ��
        {
            int timeDistance = 0 ;
            for(int i = year1 ; i < year2 ; i ++)
            {
                if(i%4==0 && i%100!=0 || i%400==0)    //����            
                {
                    timeDistance += 366;
                }
                else    //��������
                {
                    timeDistance += 365;
                }
            }
            
            return timeDistance + (day2-day1) ;
        }
        else    //��ͬ��
        {
            return day2-day1;
        }
    }

	/**
     * ͨ��ʱ����������ж�����ʱ��ļ��
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
		if(i<24) {  //����
			return format(lastDate,DATE_PATTERN)+" "+dealHour(i)+":00";
		}else {  //����
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