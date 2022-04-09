package com.phoenix.core.utils;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//


import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.commons.lang3.time.FastDateFormat;

public class DateUtil {
    public static final String SIMPLE = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_PATTERN = "yy/M/d";
    public static final String DATE1_PATTERN = "yyyy-MM-dd";
    public static final String DATETIME_PATTERN = "yy/M/d H:m:s";

    public DateUtil() {
    }

    public static String getShortDateFormatter() {
        Date date = new Date();
        DateFormat df = new SimpleDateFormat("yyyyMMdd");
        return df.format(date);
    }

    public static String getLongDateFormatter() {
        Date date = new Date();
        DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        return df.format(date);
    }

    public static String getDateFormatter(String format) throws Exception {
        Date date = new Date();

        try {
            DateFormat df = new SimpleDateFormat(format);
            return df.format(date);
        } catch (Exception var3) {
            throw new Exception("日期格式异常:" + format);
        }
    }

    public static String getCurrDateTime() {
        return DateFormatUtils.format(System.currentTimeMillis(), "yyyyMMddHHmmss");
    }

    public static String getDefaultDate() {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String s = df.format(new Date());
        return s;
    }

    public static String getSimpleCurrentDateTime() {
        Date date = new Date();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return df.format(date);
    }

    public static String getCurrDateAddTimeMillis(long l) {
        return DateFormatUtils.format(System.currentTimeMillis() + l, "yyyyMMddHHmmss");
    }

    public static String getCurrDateTime(String ftm) {
        return DateFormatUtils.format(System.currentTimeMillis(), ftm);
    }

    public static String getCurrDate() {
        return DateFormatUtils.format(System.currentTimeMillis(), "yyyyMMdd");
    }

    public static String getCurrTime() {
        return DateFormatUtils.format(System.currentTimeMillis(), "HHmmss");
    }

    public static int getCurrHour() {
        Calendar ca = Calendar.getInstance();
        return ca.get(11);
    }

    public static Date parseDate(String dateStr, String pattern) {
        try {
            return DateUtils.parseDate(dateStr, new String[]{pattern});
        } catch (ParseException var3) {
            return null;
        }
    }

    public static Date parseDate(String dateStr) throws ParseException {
        try {
            return DateUtils.parseDate(dateStr, new String[]{"yyyy-MM-dd"});
        } catch (ParseException var2) {
            throw var2;
        }
    }

    public static String transDateTime(String srcDateTime) {
        srcDateTime = srcDateTime == null ? "" : srcDateTime.trim();
        if (srcDateTime.length() == 14) {
            return transDateTime(srcDateTime, "yyyyMMddHHmmss", "yyyy-MM-dd HH:mm:ss");
        } else if (srcDateTime.length() == 12) {
            return transDateTime(srcDateTime, "yyyyMMddHHmm", "yyyy-MM-dd HH:mm");
        } else {
            return srcDateTime.length() == 8 ? transDateTime(srcDateTime, "yyyyMMdd", "yyyy-MM-dd") : srcDateTime;
        }
    }

    public static String transDateTime(long l, String ftm) {
        return DateFormatUtils.format(l, ftm);
    }

    public static String transDate(String srcDate) {
        srcDate = srcDate == null ? "" : srcDate.trim();
        if (srcDate.length() == 14) {
            return transDateTime(srcDate, "yyyyMMddHHmmss", "yyyy-MM-dd");
        } else if (srcDate.length() == 12) {
            return transDateTime(srcDate, "yyyyMMddHHmm", "yyyy-MM-dd");
        } else {
            return srcDate.length() == 8 ? transDateTime(srcDate, "yyyyMMdd", "yyyy-MM-dd") : srcDate;
        }
    }

    public static String transDate(String srcDate, String ftm) {
        srcDate = srcDate == null ? "" : srcDate.trim();
        if (srcDate.length() == 14) {
            return transDateTime(srcDate, "yyyyMMddHHmmss", ftm);
        } else if (srcDate.length() == 12) {
            return transDateTime(srcDate, "yyyyMMddHHmm", ftm);
        } else {
            return srcDate.length() == 8 ? transDateTime(srcDate, "yyyyMMdd", ftm) : srcDate;
        }
    }

    public static String transTime(String srcTime) {
        srcTime = srcTime == null ? "" : srcTime.trim();
        if (srcTime.length() == 14) {
            return transDateTime(srcTime, "yyyyMMddHHmmss", "HH:mm:ss");
        } else if (srcTime.length() == 12) {
            return transDateTime(srcTime, "yyyyMMddHHmm", "HH:mm:ss");
        } else {
            return srcTime.length() == 6 ? transDateTime(srcTime, "HHmmss", "HH:mm:ss") : srcTime;
        }
    }

    public static String transDateTime(String srcDateTime, String srcPattern, String destPattern) {
        srcDateTime = srcDateTime == null ? "" : srcDateTime.trim();

        try {
            srcDateTime = FastDateFormat.getInstance(destPattern).format(parseDate(srcDateTime, srcPattern));
        } catch (Exception var4) {
        }

        return srcDateTime;
    }

    public static String getDateTime(String s) {
        return transDateTime(s, "yyyy-MM-dd HH:mm:ss", "yyyyMMddHHmmss");
    }

    public static String getDate(String s) {
        return transDateTime(s, "yyyy-MM-dd", "yyyyMMdd");
    }

    public static String getTime(String time) {
        return transDateTime(time, "HH:mm:ss", "HHmmss");
    }

    public static String newDateOfAddDays(String dateTime, int p_days, String format) {
        String newDateTime = "";
        if (dateTime != null && !"".equals(dateTime)) {
            if (dateTime.length() == 10) {
                dateTime = dateTime + " 00:00:00";
            }

            Timestamp dd1 = null;

            try {
                dd1 = Timestamp.valueOf(dateTime);
                dd1 = newDateOfAddDays(dd1, p_days);
                SimpleDateFormat DATEFORMAT = new SimpleDateFormat(format);
                newDateTime = DATEFORMAT.format(dd1);
            } catch (Exception var6) {
                newDateTime = "";
            }
        }

        return newDateTime;
    }

    public static Timestamp newDateOfAddDays(Timestamp p_date, int p_days) {
        long dateValue = 0L;
        long daysValue = 0L;
        Timestamp newDate = null;
        dateValue = p_date.getTime() / 1000L;
        daysValue = (long)(p_days * 86400);
        dateValue += daysValue;
        newDate = new Timestamp(dateValue * 1000L);
        return newDate;
    }

    public static Date newDateOfAddDays(Date p_date, int p_days) {
        long dateValue = 0L;
        long daysValue = 0L;
        Date newDate = null;
        dateValue = p_date.getTime();
        daysValue = (long)(p_days * 86400000);
        dateValue += daysValue;
        newDate = new Date(dateValue);
        return newDate;
    }

    public static String newDateOfAddHours(String dateTime, int p_hours) {
        String newDateTime = "";
        if (dateTime != null && !"".equals(dateTime)) {
            Timestamp dd1 = null;

            try {
                dd1 = Timestamp.valueOf(dateTime);
                dd1 = newDateOfAddHours(dd1, p_hours);
                SimpleDateFormat DATEFORMAT = new SimpleDateFormat("yyyyMMddHHmmss");
                newDateTime = DATEFORMAT.format(dd1);
            } catch (Exception var5) {
                newDateTime = "";
            }
        }

        return newDateTime;
    }

    public static Timestamp newDateOfAddHours(Timestamp p_date, int p_hours) {
        long dateValue = 0L;
        long hoursValue = 0L;
        Timestamp newDate = null;
        dateValue = p_date.getTime();
        hoursValue = (long)(p_hours * 3600000);
        dateValue += hoursValue;
        newDate = new Timestamp(dateValue);
        return newDate;
    }

    public static String newDateOfAddMinutes(String dateTime, int p_hours) {
        String newDateTime = "";
        if (dateTime != null && !"".equals(dateTime)) {
            Timestamp dd1 = null;

            try {
                dd1 = Timestamp.valueOf(dateTime);
                dd1 = newDateOfAddMinutes(dd1, p_hours);
                SimpleDateFormat DATEFORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                newDateTime = DATEFORMAT.format(dd1);
            } catch (Exception var5) {
                newDateTime = "";
            }
        }

        return newDateTime;
    }

    public static Timestamp newDateOfAddMinutes(Timestamp p_date, int p_minutes) {
        long dateValue = 0L;
        long minutesValue = 0L;
        Timestamp newDate = null;
        dateValue = p_date.getTime();
        minutesValue = (long)(p_minutes * '\uea60');
        dateValue += minutesValue;
        newDate = new Timestamp(dateValue);
        return newDate;
    }

    public static long signDaysBetweenTowDate(String d1, String d2) {
        java.sql.Date dd1 = null;
        java.sql.Date dd2 = null;
        long result = -1L;

        try {
            dd1 = java.sql.Date.valueOf(d1);
            dd2 = java.sql.Date.valueOf(d2);
            result = signDaysBetweenTowDate(dd1, dd2);
        } catch (Exception var7) {
            result = -1L;
        }

        return result;
    }

    public static long signDaysBetweenTowDate(java.sql.Date d1, java.sql.Date d2) {
        return (d1.getTime() - d2.getTime()) / 86400000L;
    }

    public static long signDaysBetweenTowDate(Date d1, Date d2) {
        return (d1.getTime() - d2.getTime()) / 86400000L;
    }

    public static long signSecondsBetweenTowDate(String d1, String d2) {
        Timestamp dd1 = null;
        Timestamp dd2 = null;
        long result = -1L;

        try {
            dd1 = Timestamp.valueOf(d1);
            dd2 = Timestamp.valueOf(d2);
            result = signSecondsBetweenTowDate(dd1, dd2);
        } catch (Exception var7) {
            result = -1L;
        }

        return result;
    }

    public static long signSecondsBetweenTowDate(Timestamp d1, Timestamp d2) {
        return (d1.getTime() - d2.getTime()) / 1000L;
    }

    public static String secondsToHourMin(int s) {
        int hour = s / 3600;
        int min = (s - hour * 3600) / 60;
        if (min == 0) {
            min = 1;
        }

        return hour + ":" + min;
    }

    public static Date str2date(String dateString, String fmt) {
        SimpleDateFormat sdf = new SimpleDateFormat(fmt);
        Date date = null;

        try {
            date = sdf.parse(dateString);
        } catch (ParseException var5) {
            var5.printStackTrace();
        }

        return date;
    }

    public static String date2str(Date date, String fmt) {
        return (new SimpleDateFormat(StringUtils.isBlank(fmt) ? "yyyyMMddHHmmss" : fmt)).format(date);
    }

    public static Timestamp getCurrentTimeStamp() {
        Date today = new Date();
        return new Timestamp(today.getTime());
    }

    public static int getSecondBetweenTwoDate(Date startDate, Date endDate) {
        return (int)(endDate.getTime() - startDate.getTime()) / 1000;
    }

    public static int daysBetween(String smdate, String bdate) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        cal.setTime(sdf.parse(smdate));
        long time1 = cal.getTimeInMillis();
        cal.setTime(sdf.parse(bdate));
        long time2 = cal.getTimeInMillis();
        long between_days = (time2 - time1) / 86400000L;
        return Integer.parseInt(String.valueOf(between_days));
    }

    public static int daysBetween(Date smdate, Date bdate) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        smdate = sdf.parse(sdf.format(smdate));
        bdate = sdf.parse(sdf.format(bdate));
        Calendar cal = Calendar.getInstance();
        cal.setTime(smdate);
        long time1 = cal.getTimeInMillis();
        cal.setTime(bdate);
        long time2 = cal.getTimeInMillis();
        long between_days = (time2 - time1) / 86400000L;
        return Integer.parseInt(String.valueOf(between_days));
    }

    public static String getDateByDays(int days, String position) {
        long curTime = (new Date()).getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        long intervalTime = "0".equals(position) ? curTime - 86400000L * (long)days : curTime + 86400000L * (long)days;
        Date intervalDate = new Date(intervalTime);
        return sdf.format(intervalDate);
    }

    public static Date getDateAddMonth(int amount) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(2, amount);
        return new Date(calendar.getTimeInMillis());
    }

    public static String dateDifferent(String date1, String date2) {
        new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date d1 = null;
        Date d2 = null;
        StringBuilder sb = new StringBuilder();
        return sb.toString();
    }

    public static String dateNowToNumStr() {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        return formatter.format(date);
    }

    public static String dateStartStr() {
        return formatDate(new Date(), "yyyy-MM-dd") + " 00:00:00";
    }

    public static String dateStartStr(Date date) {
        return formatDate(date, "yyyy-MM-dd") + " 00:00:00";
    }

    public static String dateEndStr() {
        return formatDate(new Date(), "yyyy-MM-dd") + " 23:59:59";
    }

    public static String dateEndStr(Date date) {
        return formatDate(date, "yyyy-MM-dd") + " 23:59:59";
    }

    public static String formatDate(Date date, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }

    public static Date dateStart() {
        String str = dateStartStr();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;

        try {
            date = format.parse(str);
        } catch (ParseException var4) {
            var4.printStackTrace();
        }

        return date;
    }

    public static Date dateEnd() {
        String str = dateEndStr();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;

        try {
            date = format.parse(str);
        } catch (ParseException var4) {
            var4.printStackTrace();
        }

        return date;
    }

    public static String formatDate(String strYear, String strMonth, String strDay) {
        try {
            int iYear = Integer.parseInt(strYear);
            int iMonth = Integer.parseInt(strMonth);
            int iDay = Integer.parseInt(strDay);
            return formatDate(iYear, iMonth, iDay);
        } catch (Exception var6) {
            return null;
        }
    }

    public static String formatDate(int iYear, int iMonth, int iDay) {
        try {
            Calendar cld = Calendar.getInstance();
            cld.set(iYear, iMonth - 1, iDay);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
            return sdf.format(cld.getTime());
        } catch (Exception var5) {
            return null;
        }
    }

    public static String formatDateTime(String strYear, String strMonth, String strDay, String strHour, String strMinute, String strSecond) {
        try {
            int iYear = Integer.parseInt(strYear);
            int iMonth = Integer.parseInt(strMonth);
            int iDay = Integer.parseInt(strDay);
            int iHour = Integer.parseInt(strHour);
            int iMinute = Integer.parseInt(strMinute);
            int iSecond = Integer.parseInt(strSecond);
            return formatDateTime(iYear, iMonth, iDay, iHour, iMinute, iSecond);
        } catch (Exception var12) {
            return null;
        }
    }

    public static String formatDateTime(int iYear, int iMonth, int iDay, int iHour, int iMinute, int iSecond) {
        try {
            Calendar cld = Calendar.getInstance();
            cld.set(iYear, iMonth - 1, iDay, iHour, iMinute, iSecond);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            return sdf.format(cld.getTime());
        } catch (Exception var8) {
            return null;
        }
    }

    public static String formatDateTime(Calendar cld) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            return sdf.format(cld.getTime());
        } catch (Exception var2) {
            return null;
        }
    }

    public static boolean checkDateTime(String strYear, String strMonth, String strDay, String strHour, String strMinute, String strSecond) {
        return formatDateTime(strYear, strMonth, strDay, strHour, strMinute, strSecond) != null;
    }

    public static String getTimeStr() {
        long timeStamp = System.currentTimeMillis() / 1000L;
        return String.valueOf(timeStamp);
    }

    public static boolean checkDate(int iYear, int iMonth, int iDay) {
        return formatDate(iYear, iMonth, iDay) != null;
    }

    public static boolean checkDate(String strYear, String strMonth, String strDay) {
        return formatDate(strYear, strMonth, strDay) != null;
    }

    public static boolean checkDateTime(int iYear, int iMonth, int iDay, int iHour, int iMinute, int iSecond) {
        return formatDateTime(iYear, iMonth, iDay, iHour, iMinute, iSecond) != null;
    }

    public static String getDateStr(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String datestr = sdf.format(date);
        return datestr;
    }

    public static String getDateStr(Date date, String dateformat) {
        SimpleDateFormat sdf = new SimpleDateFormat(dateformat);
        String datestr = sdf.format(date);
        return datestr;
    }

    public static String getYearStr(String str) {
        String yearStr = "";
        yearStr = str.substring(0, 4);
        return yearStr;
    }

    public static String getMonthStr(String str) {
        int startIndex = str.indexOf("年");
        int endIndex = str.indexOf("月");
        String monthStr = str.substring(startIndex + 1, endIndex);
        return monthStr;
    }

    public static char formatDigit(char sign) {
        return sign;
    }

    public static int getMidLen(String str, int pos1, int pos2) {
        return str.substring(pos1 + 1, pos2).length();
    }

    public static int getLastLen(String str, int pos2) {
        return str.substring(pos2 + 1).length();
    }

    public static String getDayStr(String str) {
        String dayStr = "";
        int startIndex = str.indexOf("月");
        int endIndex = str.indexOf("日");
        dayStr = str.substring(startIndex + 1, endIndex);
        return dayStr;
    }

    public static String formatStr(String str) {
        StringBuffer sb = new StringBuffer();
        int pos1 = str.indexOf("-");
        int pos2 = str.lastIndexOf("-");
        if (getMidLen(str, pos1, pos2) == 1) {
            sb.append(formatDigit(str.charAt(5)) + "月");
            if (str.charAt(7) != '0') {
                if (getLastLen(str, pos2) == 1) {
                    sb.append(formatDigit(str.charAt(7)) + "日");
                }

                if (getLastLen(str, pos2) == 2) {
                    if (str.charAt(7) != '1' && str.charAt(8) != '0') {
                        sb.append(formatDigit(str.charAt(7)) + "十" + formatDigit(str.charAt(8)) + "日");
                    } else if (str.charAt(7) != '1' && str.charAt(8) == '0') {
                        sb.append(formatDigit(str.charAt(7)) + "十日");
                    } else if (str.charAt(7) == '1' && str.charAt(8) != '0') {
                        sb.append("十" + formatDigit(str.charAt(8)) + "日");
                    } else {
                        sb.append("十日");
                    }
                }
            } else {
                sb.append(formatDigit(str.charAt(8)) + "日");
            }
        }

        if (getMidLen(str, pos1, pos2) == 2) {
            if (str.charAt(5) != '0' && str.charAt(6) != '0') {
                sb.append("十" + formatDigit(str.charAt(6)) + "月");
                if (getLastLen(str, pos2) == 1) {
                    sb.append(formatDigit(str.charAt(8)) + "日");
                }

                if (getLastLen(str, pos2) == 2) {
                    if (str.charAt(8) != '0') {
                        if (str.charAt(8) != '1' && str.charAt(9) != '0') {
                            sb.append(formatDigit(str.charAt(8)) + "十" + formatDigit(str.charAt(9)) + "日");
                        } else if (str.charAt(8) != '1' && str.charAt(9) == '0') {
                            sb.append(formatDigit(str.charAt(8)) + "十日");
                        } else if (str.charAt(8) == '1' && str.charAt(9) != '0') {
                            sb.append("十" + formatDigit(str.charAt(9)) + "日");
                        } else {
                            sb.append("十日");
                        }
                    } else {
                        sb.append(formatDigit(str.charAt(9)) + "日");
                    }
                }
            } else if (str.charAt(5) != '0' && str.charAt(6) == '0') {
                sb.append("十月");
                if (getLastLen(str, pos2) == 1) {
                    sb.append(formatDigit(str.charAt(8)) + "日");
                }

                if (getLastLen(str, pos2) == 2) {
                    if (str.charAt(8) != '0') {
                        if (str.charAt(8) != '1' && str.charAt(9) != '0') {
                            sb.append(formatDigit(str.charAt(8)) + "十" + formatDigit(str.charAt(9)) + "日");
                        } else if (str.charAt(8) != '1' && str.charAt(9) == '0') {
                            sb.append(formatDigit(str.charAt(8)) + "十日");
                        } else if (str.charAt(8) == '1' && str.charAt(9) != '0') {
                            sb.append("十" + formatDigit(str.charAt(9)) + "日");
                        } else {
                            sb.append("十日");
                        }
                    } else {
                        sb.append(formatDigit(str.charAt(9)) + "日");
                    }
                }
            } else {
                sb.append(formatDigit(str.charAt(6)) + "月");
                if (getLastLen(str, pos2) == 1) {
                    sb.append(formatDigit(str.charAt(8)) + "日");
                }

                if (getLastLen(str, pos2) == 2) {
                    if (str.charAt(8) != '0') {
                        if (str.charAt(8) != '1' && str.charAt(9) != '0') {
                            sb.append(formatDigit(str.charAt(8)) + "十" + formatDigit(str.charAt(9)) + "日");
                        } else if (str.charAt(8) != '1' && str.charAt(9) == '0') {
                            sb.append(formatDigit(str.charAt(8)) + "十日");
                        } else if (str.charAt(8) == '1' && str.charAt(9) != '0') {
                            sb.append("十" + formatDigit(str.charAt(9)) + "日");
                        } else {
                            sb.append("十日");
                        }
                    } else {
                        sb.append(formatDigit(str.charAt(9)) + "日");
                    }
                }
            }
        }

        return sb.toString();
    }

    public static String getWeekOfDate(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date dt = null;

        try {
            dt = sdf.parse(date);
            String[] weekDaysName = new String[]{"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
            String[] var10000 = new String[]{"0", "1", "2", "3", "4", "5", "6"};
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(dt);
            int intWeek = calendar.get(7) - 1;
            return weekDaysName[intWeek];
        } catch (ParseException var7) {
            var7.printStackTrace();
            return "";
        }
    }

    public static String getWeekNumOfDate(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date dt = null;

        try {
            dt = sdf.parse(date);
            String[] weekDaysCode = new String[]{"7", "1", "2", "3", "4", "5", "6"};
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(dt);
            int intWeek = calendar.get(7) - 1;
            return weekDaysCode[intWeek];
        } catch (ParseException var6) {
            var6.printStackTrace();
            return "";
        }
    }

    public static boolean isDate(String strDate) {
        Pattern pattern = Pattern.compile("^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))(\\s(((0?[0-9])|([1-2][0-3]))\\:([0-5]?[0-9])((\\s)|(\\:([0-5]?[0-9])))))?$");
        Matcher m = pattern.matcher(strDate);
        return m.matches();
    }

    public static int getMaxDay(int iYear, int iMonth) {
        int[] monthdays = new int[]{31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        if (iYear > 0 && iMonth > 0 && iMonth <= 12) {
            if (String.valueOf(iYear).length() <= 2) {
                if (iYear >= 0 && iYear <= 29) {
                    iYear += 2000;
                } else {
                    iYear += 1900;
                }
            } else if (String.valueOf(iYear).length() != 4) {
                return -1;
            }

            if (iYear % 400 == 0 || iYear % 4 == 0 && iYear % 100 != 0) {
                monthdays[1] = 29;
            }

            return monthdays[iMonth - 1];
        } else {
            return -1;
        }
    }

    public static String getSysTime() {
        long lDate = System.currentTimeMillis();
        Date date = new Date(lDate);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        return sdf.format(date);
    }

    public static int getLastDayInMonth(int year, int month) {
        Calendar c = Calendar.getInstance();
        c.set(1, year - 1900);
        c.set(2, month - 1);
        return c.getActualMaximum(5);
    }

    public static int getLastDayInMonth(String date, String pattern) {
        Date d = parseDateTime(date, pattern);
        return getLastDayInMonth(d);
    }

    public static int getLastDayInMonth(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.getActualMaximum(5);
    }

    public static String changeMonth(String date, String pattern, int n) {
        Date d = parseDateTime(date, pattern);
        d = changeMonth(d, n);
        return getDateTime(d, pattern);
    }

    public static Date changeMonth(Date now, int n) {
        if (now == null) {
            return null;
        } else {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            String s = sdf.format(now);
            sdf = new SimpleDateFormat("M");
            int m = Integer.parseInt(sdf.format(now));
            m += n;
            s = s.substring(0, 5) + m + s.substring(7);
            sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

            try {
                return sdf.parse(s);
            } catch (ParseException var6) {
                return null;
            }
        }
    }

    public static String getDateTime(Date d, String p) {
        if (d == null) {
            return null;
        } else {
            SimpleDateFormat sdf = new SimpleDateFormat(p);
            return sdf.format(d);
        }
    }

    public static boolean compareDate(String beforeDate, String afterDate, String p) {
        Date d1 = parseDateTime(beforeDate, p);
        Date d2 = parseDateTime(afterDate, p);
        return d1 != null && d2 != null ? d1.before(d2) : false;
    }

    public static int compareDate2(String beforeDate, String afterDate, String p) {
        Date d1 = parseDateTime(beforeDate, p);
        Date d2 = parseDateTime(afterDate, p);
        return d1 != null && d2 != null ? d1.compareTo(d2) : 0;
    }

    public static Date parseDateTime(String d, String p) {
        if (isEmpty(d)) {
            return null;
        } else {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat(p);
                return sdf.parse(d);
            } catch (ParseException var3) {
                return null;
            }
        }
    }

    public static Calendar mondayOfWeek(Calendar date) {
        int def = date.get(7) - 2;
        date.add(5, -def);
        return date;
    }

    public static String intTimeToHHmm(String time) {
        if (time != null && !"".equals(time)) {
            try {
                time = time.substring(0, 2) + ":" + time.substring(2, 4);
            } catch (Exception var2) {
                var2.printStackTrace();
            }
        }

        return time;
    }

    public static String getOneDay(int size) {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        long time = date.getTime();
        time += (long)(86400000 * size);
        Date d = new Date(time);
        return sdf.format(d);
    }

    private static boolean isEmpty(String s) {
        return s == null || "".equals(s);
    }

    public static boolean isValidDate(String seeTime, String dateFormat) {
        boolean convertSuccess = true;
        SimpleDateFormat format = new SimpleDateFormat(dateFormat);

        try {
            format.setLenient(false);
            format.parse(seeTime);
        } catch (ParseException var5) {
            convertSuccess = false;
        }

        return convertSuccess;
    }

    public static String getScheEndDate() {
        String transDate = "";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        cal.set(5, cal.get(5) + 7);
        transDate = sdf.format(cal.getTime());
        return transDate;
    }

    public static boolean ifInnerTime(String date1, String date2, String ftm) {
        boolean flag = false;
        SimpleDateFormat hour_sd = new SimpleDateFormat(ftm);

        try {
            Date cur_time = hour_sd.parse(hour_sd.format(new Date()));
            if (cur_time.after(hour_sd.parse(date1)) && cur_time.before(hour_sd.parse(date2))) {
                flag = true;
            }

            return flag;
        } catch (ParseException var6) {
            return flag;
        }
    }

    public static void main(String[] args) {
        System.out.println(getWeekNumOfDate("2019-05-12"));
    }
}
