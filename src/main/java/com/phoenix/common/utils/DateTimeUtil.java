package com.phoenix.common.utils;

import com.phoenix.core.utils.DateUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

/**
 * 日期处理
 */
public class DateTimeUtil {
    /**
     * 时间格式(yyyy-MM-dd)
     */
    public final static String DATE_PATTERN = "yyyy-MM-dd";
    /**
     * 时间格式(yyyy-MM-dd HH:mm:ss)
     */
    public final static String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
    
    public final static String[] weekDays = { "sunday", "monday", "tuesday", "wednesday", "thursday", "friday", "saturday" };
    
    public final static String[] weekDaysCN = { "周日", "周一", "周二", "周三", "周四", "周五", "周六" };

    public static String format(Date date) {
        return format(date, DATE_PATTERN);
    }

    public static String format(Date date, String pattern) {
        if (date != null) {
            SimpleDateFormat df = new SimpleDateFormat(pattern);
            return df.format(date);
        }
        return null;
    }

    /**
     * 计算距离现在多久，非精确
     *
     * @param date
     * @return
     */
    public static String getTimeBefore(Date date) {
        Date now = new Date();
        long l = now.getTime() - date.getTime();
        long day = l / (24 * 60 * 60 * 1000);
        long hour = (l / (60 * 60 * 1000) - day * 24);
        long min = ((l / (60 * 1000)) - day * 24 * 60 - hour * 60);
        long s = (l / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
        String r = "";
        if (day > 0) {
            r += day + "天";
        } else if (hour > 0) {
            r += hour + "小时";
        } else if (min > 0) {
            r += min + "分";
        } else if (s > 0) {
            r += s + "秒";
        }
        r += "前";
        return r;
    }

    /**
     * 计算距离现在多久，精确
     *
     * @param date
     * @return
     */
    public static String getTimeBeforeAccurate(Date date) {
        Date now = new Date();
        long l = now.getTime() - date.getTime();
        long day = l / (24 * 60 * 60 * 1000);
        long hour = (l / (60 * 60 * 1000) - day * 24);
        long min = ((l / (60 * 1000)) - day * 24 * 60 - hour * 60);
        long s = (l / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
        String r = "";
        if (day > 0) {
            r += day + "天";
        }
        if (hour > 0) {
            r += hour + "小时";
        }
        if (min > 0) {
            r += min + "分";
        }
        if (s > 0) {
            r += s + "秒";
        }
        r += "前";
        return r;
    }
    
    /** 
     * 获取过去或者未来 任意天内的日期数组 
     * @param intervals      intervals天内 
     * @return              日期数组 
     */  
    public static Map<String,List<String>> getDateList(int intervals) {  
    	Map<String,List<String>> resultMap = new HashMap<>();
        List<String> pastDaysList = new ArrayList<>();  
        List<String> fetureDaysList = new ArrayList<>();  
        for (int i = 0; i <intervals; i++) {  
            pastDaysList.add(getPastDate(i,null));  
            fetureDaysList.add(getFetureDate(i,null));  
        }
        resultMap.put("past", pastDaysList);
        resultMap.put("feture", fetureDaysList);
        return resultMap;  
    }  
   
    /** 
     * 获取过去第几天的日期 
     * 
     * @param past 
     * @return 
     */  
    public static String getPastDate(int past,Date date) {  
        Calendar calendar = Calendar.getInstance();  
        if (null != date) {
        	calendar.setTime(date);
		}
        calendar.add(Calendar.DAY_OF_YEAR, past*-1);  
        String result = DateUtils.formatDate(calendar.getTime(), DATE_PATTERN);  
        return result;  
    }  
   
    /** 
     * 获取未来 第 past 天的日期 
     * @param past 
     * @return 
     */  
    public static String getFetureDate(int past,Date date) {  
        Calendar calendar = Calendar.getInstance();  
        if (null != date) {
        	calendar.setTime(date);
		} 
        calendar.add(Calendar.DAY_OF_YEAR, past);  
        String result = DateUtils.formatDate(calendar.getTime(), DATE_PATTERN);  
        return result;  
    } 
    
    public static List<Date> getBetweenDates(String start, String end) {
    	Date startDate = DateUtils.parseDate(start, DateUtils.FORMAT_yyyy_MM_dd);
    	Date endDate = DateUtils.parseDate(end, DateUtils.FORMAT_yyyy_MM_dd);
        return getBetweenDates(startDate,endDate);
    }
    
    public static List<Date> getBetweenDates(Date start, Date end) {
        List<Date> result = new ArrayList<Date>();
        Calendar tempStart = Calendar.getInstance();
        tempStart.setTime(start);
        Calendar tempEnd = Calendar.getInstance();
        tempEnd.setTime(end);
        while (tempStart.before(tempEnd)) {
            result.add(tempStart.getTime());
            tempStart.add(Calendar.DAY_OF_YEAR, 1);
        }
        result.add(end);
        return result;
    }
    
    public static String dateToWeek(String date) {
    	return dateToWeek(DateUtils.parseDate(date, DATE_PATTERN));
    }
    
    public static String dateToWeek(Date date) {
        Calendar cal = Calendar.getInstance(); // 获得一个日历
        cal.setTime(date);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1; // 指示一个星期中的某天。
        if (w < 0) {
            w = 0;
        }
        return weekDays[w];
    }
    
    public static String dateToWeekCN(String date) {
    	return dateToWeekCN(DateUtils.parseDate(date, DATE_PATTERN));
    }
    
    public static String dateToWeekCN(Date date) {
        Calendar cal = Calendar.getInstance(); // 获得一个日历
        cal.setTime(date);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1; // 指示一个星期中的某天。
        if (w < 0) {
            w = 0;
        }
        return weekDaysCN[w];
    }
    
    /**
	 * 两个日期间的毫秒数
	 * @param startDate,endDate
	 * @return
	 */
	public static long calculateMillisecond(Date startDate,Date endDate) {
		long a = endDate.getTime();
		long b = startDate.getTime();
		return a - b;
	}
	
	/**
	 * 两个日期间的秒数
	 * @param startDate,endDate
	 * @return
	 */
	public static long calculateSecond(Date startDate,Date endDate) {
		return calculateMillisecond(startDate,endDate)/1000;
	}
	
	/** 
     * 获取过去或者未来 任意的日期数组 
     * @param intervals   偏移量
     * @param type:DAY_OF_YEAR、MONTH、YEAR
     * @return              日期数组 
     */  
    public static Map<String,List<String>> getDateList(int intervals,int type,Date date,String format) {  
    	Map<String,List<String>> resultMap = new HashMap<>();
        List<String> pastDaysList = new ArrayList<>();  
        List<String> fetureDaysList = new ArrayList<>();  
        for (int i = 0; i <intervals; i++) {  
            pastDaysList.add(getDate(i*-1,type,date,format));  
            fetureDaysList.add(getDate(i,type,date,format));  
        }
        resultMap.put("past", pastDaysList);
        resultMap.put("feture", fetureDaysList);
        return resultMap;  
    } 
    
    public static Map<String,List<String>> getDateList(int intervals,int type,String dateStr,String format) { 
    	Date date = new Date();
    	if (StringUtils.isNotBlank(dateStr)) {
    		date = DateUtils.parseDate(dateStr, format);
    	}
    	Map<String,List<String>> resultMap = new HashMap<>();
        List<String> pastDaysList = new ArrayList<>();  
        List<String> fetureDaysList = new ArrayList<>();  
        for (int i = 0; i <intervals; i++) {  
            pastDaysList.add(getDate(i*-1,type,date,format));  
            fetureDaysList.add(getDate(i,type,date,format));  
        }
        resultMap.put("past", pastDaysList);
        resultMap.put("feture", fetureDaysList);
        return resultMap;  
    }
    
    /** 
     * 根据偏移量、日期类别、格式获取日期
     * 
     * @return 
     */  
    public static Date getDate(int intervals,int type,Date date) {  
        Calendar calendar = Calendar.getInstance();  
        if (null != date) {
        	calendar.setTime(date);
		}
        calendar.add(type, intervals);  
        return calendar.getTime();  
    }
    
    public static String getDate(int intervals,int type,Date date,String format) {  
        String result = DateUtils.formatDate(getDate(intervals,type,date), format);  
        return result;  
    }
    
    public static String getDate(int intervals,int type,String date,String format) {  
        String result = DateUtils.formatDate(getDate(intervals,type,DateUtils.parseDate(date, format)), format);  
        return result;  
    }
    
    /** 
     * 获取固定间隔时刻集合 
     * @param start 开始时间 
     * @param end 结束时间 
     * @param interval 时间间隔(单位：分钟) 
     * @return 
     */  
    public static Map<String,List<String>> getIntervalTimeList(String start,String end,int interval){ 
    	Date startDate = DateUtils.parseDate(start, "HH:mm");  
        Date endDate = DateUtils.parseDate(end, "HH:mm");    
        List<String> list = new ArrayList<>();  
        List<String> listRange = new ArrayList<>(); 
        while(startDate.getTime() <= endDate.getTime()){
        	String time = DateUtils.formatDate(startDate, "HH:mm");
        	list.add(time);  
            Calendar calendar = Calendar.getInstance();  
            calendar.setTime(startDate);  
            calendar.add(Calendar.MINUTE,interval);  
            if(calendar.getTime().getTime() >= endDate.getTime()){  
//            	list.add(DateUtils.formatDate(endDate, "HH:mm")); 
            	time += " - " + DateUtils.formatDate(endDate, "HH:mm");
            	listRange.add(time);
                break;
            }else{  
                startDate = calendar.getTime(); 
                time += " - " + DateUtils.formatDate(startDate, "HH:mm");
                listRange.add(time);
            } 
            
        } 
        Map<String,List<String>> map = new HashMap<>();
        map.put("time", list);
        map.put("timeRange", listRange);
        return map;  
    } 
    
    /**
	 * 获取当年的第一天
	 * @param year
	 * @return
	 */
	public static Date getCurrYearFirst(){
		Calendar currCal=Calendar.getInstance();  
		int currentYear = currCal.get(Calendar.YEAR);
		return getYearFirst(currentYear);
	}
	
	/**
	 * 获取当年的最后一天
	 * @param year
	 * @return
	 */
	public static Date getCurrYearLast(){
		Calendar currCal=Calendar.getInstance();  
		int currentYear = currCal.get(Calendar.YEAR);
		return getYearLast(currentYear);
	}
	
	/**
	 * 获取某年第一天日期
	 * @param year 年份
	 * @return Date
	 */
	public static Date getYearFirst(int year){
		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		calendar.set(Calendar.YEAR, year);
		Date currYearFirst = calendar.getTime();
		return currYearFirst;
	}
	
	/**
	 * 获取某年最后一天日期
	 * @param year 年份
	 * @return Date
	 */
	public static Date getYearLast(int year){
		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		calendar.set(Calendar.YEAR, year);
		calendar.roll(Calendar.DAY_OF_YEAR, -1);
		Date currYearLast = calendar.getTime();
		return currYearLast;
	}
}
