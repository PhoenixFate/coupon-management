package com.phoenix.core.utils;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Pattern;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.FastDateFormat;

public class DateUtils {
    public static String FORMAT_yyyy_MM_dd = "yyyy-MM-dd";
    public static String FORMAT_yyyy_MM_dd_HH_mm_ss_SSS = "yyyy-MM-dd HH:mm:ss.SSS";
    public static String FORMAT_yyyy_MM_dd_HH_mm_ss = "yyyy-MM-dd HH:mm:ss";
    public static String FORMAT_yyyyMMddHHmmss = "yyyyMMddHHmmss";
    public static String FORMAT_yyyyMMdd = "yyyyMMdd";
    public static String REG_yyyyMMddHHmmss = "^\\d{14}$";
    public static String REG_yyyyMMdd = "^\\d{8}$";
    public static String REG_yyyy_MM_dd_HH_mm_ss = "^\\d{4}\\D+\\d{1,2}\\D+\\d{1,2}\\D+\\d{1,2}\\D+\\d{1,2}\\D+\\d{1,2}\\D*$";
    public static String REG_yyyy_MM_dd = "^[0-9]{4}-[0-9]{2}-[0-9]{2}$";
    private static final String[] dateFormatArray;

    static {
        dateFormatArray = new String[]{FORMAT_yyyy_MM_dd_HH_mm_ss, FORMAT_yyyyMMddHHmmss, FORMAT_yyyy_MM_dd, FORMAT_yyyyMMdd};
    }

    public DateUtils() {
    }

    public static String formatDate(Date date, String format) {
        SimpleDateFormat sf = new SimpleDateFormat(format);
        return sf.format(date);
    }

    public static Date parseDate(String date, String format) {
        SimpleDateFormat sf = new SimpleDateFormat(format);

        try {
            return sf.parse(date);
        } catch (ParseException var4) {
            return null;
        }
    }

    public static Date formatStringDate(String dateStr) {
        if (StringUtils.isEmpty(dateStr)) {
            return null;
        } else {
            Date resultDate = null;
            SimpleDateFormat sdf1 = null;

            try {
                if (Pattern.compile(REG_yyyy_MM_dd).matcher(dateStr).matches()) {
                    sdf1 = new SimpleDateFormat(FORMAT_yyyy_MM_dd);
                }

                if (Pattern.compile(REG_yyyyMMddHHmmss).matcher(dateStr).matches()) {
                    sdf1 = new SimpleDateFormat(FORMAT_yyyyMMddHHmmss);
                } else if (Pattern.compile(REG_yyyyMMdd).matcher(dateStr).matches()) {
                    sdf1 = new SimpleDateFormat(FORMAT_yyyyMMdd);
                } else if (Pattern.compile(REG_yyyy_MM_dd_HH_mm_ss).matcher(dateStr).matches()) {
                    sdf1 = new SimpleDateFormat(FORMAT_yyyy_MM_dd_HH_mm_ss);
                }

                resultDate = sdf1.parse(dateStr);
            } catch (ParseException var4) {
                var4.toString();
            }

            return resultDate;
        }
    }

    public static String transDateTime(String srcDateTime, String srcPattern, String destPattern) {
        try {
            srcDateTime = FastDateFormat.getInstance(destPattern).format(parseDate(srcDateTime, srcPattern));
        } catch (Exception var4) {
        }

        return srcDateTime;
    }

    public static String transDate(String srcDate) {
        if (srcDate.length() == 14) {
            return transDateTime(srcDate, FORMAT_yyyyMMddHHmmss, FORMAT_yyyy_MM_dd);
        } else {
            return srcDate.length() == 8 ? transDateTime(srcDate, FORMAT_yyyyMMdd, FORMAT_yyyy_MM_dd) : srcDate;
        }
    }

    public static String transDateTime(String srcDateTime) {
        if (srcDateTime.length() == 14) {
            return transDateTime(srcDateTime, FORMAT_yyyyMMddHHmmss, FORMAT_yyyy_MM_dd_HH_mm_ss);
        } else {
            return srcDateTime.length() == 8 ? transDateTime(srcDateTime, FORMAT_yyyyMMdd, FORMAT_yyyy_MM_dd) : srcDateTime;
        }
    }

    public static Date parseDateStr(String dateStr, String pattern) {
        try {
            return org.apache.commons.lang3.time.DateUtils.parseDate(dateStr, new String[]{pattern});
        } catch (ParseException var3) {
            return null;
        }
    }

    public static String getCurrDateTime(String ftm) {
        return DateFormatUtils.format(System.currentTimeMillis(), ftm);
    }

    public static String getBillConstractTransDate() {
        String transDate = "";
        SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_yyyyMMdd);
        Calendar cal = Calendar.getInstance();
        cal.set(5, cal.get(5) - 1);
        transDate = sdf.format(cal.getTime());
        return transDate;
    }

    public static String formatDate(String tradeDate, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Date date = null;
        String[] var7;
        int var6 = (var7 = dateFormatArray).length;
        int var5 = 0;

        while(var5 < var6) {
            String formatStr = var7[var5];
            SimpleDateFormat sdf1 = new SimpleDateFormat(formatStr);

            try {
                date = sdf1.parse(tradeDate);
                break;
            } catch (Exception var10) {
                ++var5;
            }
        }

        return sdf.format(date);
    }
}

