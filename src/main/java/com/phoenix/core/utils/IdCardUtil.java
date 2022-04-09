package com.phoenix.core.utils;


import com.phoenix.core.exception.ServerException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang3.StringUtils;

public class IdCardUtil {
    public IdCardUtil() {
    }

    public static boolean IDCardValidate(String IDStr) throws ServerException {
        String errorInfo = "";
        String[] ValCodeArr = new String[]{"1", "0", "X", "9", "8", "7", "6", "5", "4", "3", "2"};
        String[] Wi = new String[]{"7", "9", "10", "5", "8", "4", "2", "1", "6", "3", "7", "9", "10", "5", "8", "4", "2"};
        String Ai = "";
        IDStr = StringUtils.trimToEmpty(IDStr).toUpperCase();
        if (IDStr.length() != 15 && IDStr.length() != 18) {
            errorInfo = "身份证号码长度应该为15位或18位。";
            throw new ServerException("1002", errorInfo);
        } else {
            if (IDStr.length() == 18) {
                Ai = IDStr.substring(0, 17);
            } else if (IDStr.length() == 15) {
                Ai = IDStr.substring(0, 6) + "19" + IDStr.substring(6, 15);
            }

            if (!isNumeric(Ai)) {
                errorInfo = "身份证15位号码都应为数字 ; 18位号码除最后一位外，都应为数字。";
                throw new ServerException("1002", errorInfo);
            } else {
                String strYear = Ai.substring(6, 10);
                String strMonth = Ai.substring(10, 12);
                String strDay = Ai.substring(12, 14);
                if (!DateUtil.isDate(strYear + "-" + strMonth + "-" + strDay)) {
                    errorInfo = "身份证生日无效。";
                    throw new ServerException("1002", errorInfo);
                } else {
                    GregorianCalendar gc = new GregorianCalendar();
                    SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");

                    try {
                        if (gc.get(1) - Integer.parseInt(strYear) > 150 || gc.getTime().getTime() - s.parse(strYear + "-" + strMonth + "-" + strDay).getTime() < 0L) {
                            errorInfo = "身份证生日不在有效范围。";
                            throw new ServerException("1002", errorInfo);
                        }
                    } catch (NumberFormatException var14) {
                        var14.printStackTrace();
                    } catch (ParseException var15) {
                        var15.printStackTrace();
                    }

                    if (Integer.parseInt(strMonth) <= 12 && Integer.parseInt(strMonth) != 0) {
                        if (Integer.parseInt(strDay) <= 31 && Integer.parseInt(strDay) != 0) {
                            Hashtable<String, String> h = GetAreaCode();
                            if (h.get(Ai.substring(0, 2)) == null) {
                                errorInfo = "身份证地区编码错误。";
                                throw new ServerException("1002", errorInfo);
                            } else {
                                int TotalmulAiWi = 0;

                                int modValue;
                                for(modValue = 0; modValue < 17; ++modValue) {
                                    TotalmulAiWi += Integer.parseInt(String.valueOf(Ai.charAt(modValue))) * Integer.parseInt(Wi[modValue]);
                                }

                                modValue = TotalmulAiWi % 11;
                                String strVerifyCode = ValCodeArr[modValue];
                                Ai = Ai + strVerifyCode;
                                if (IDStr.length() == 18) {
                                    if (!Ai.equals(IDStr)) {
                                        errorInfo = "身份证无效，不是合法的身份证号码";
                                        throw new ServerException("1002", errorInfo);
                                    } else {
                                        return true;
                                    }
                                } else {
                                    return true;
                                }
                            }
                        } else {
                            errorInfo = "身份证日期无效";
                            throw new ServerException("1002", errorInfo);
                        }
                    } else {
                        errorInfo = "身份证月份无效";
                        throw new ServerException("1002", errorInfo);
                    }
                }
            }
        }
    }

    private static Hashtable<String, String> GetAreaCode() {
        Hashtable<String, String> hashtable = new Hashtable();
        hashtable.put("11", "北京");
        hashtable.put("12", "天津");
        hashtable.put("13", "河北");
        hashtable.put("14", "山西");
        hashtable.put("15", "内蒙古");
        hashtable.put("21", "辽宁");
        hashtable.put("22", "吉林");
        hashtable.put("23", "黑龙江");
        hashtable.put("31", "上海");
        hashtable.put("32", "江苏");
        hashtable.put("33", "浙江");
        hashtable.put("34", "安徽");
        hashtable.put("35", "福建");
        hashtable.put("36", "江西");
        hashtable.put("37", "山东");
        hashtable.put("41", "河南");
        hashtable.put("42", "湖北");
        hashtable.put("43", "湖南");
        hashtable.put("44", "广东");
        hashtable.put("45", "广西");
        hashtable.put("46", "海南");
        hashtable.put("50", "重庆");
        hashtable.put("51", "四川");
        hashtable.put("52", "贵州");
        hashtable.put("53", "云南");
        hashtable.put("54", "西藏");
        hashtable.put("61", "陕西");
        hashtable.put("62", "甘肃");
        hashtable.put("63", "青海");
        hashtable.put("64", "宁夏");
        hashtable.put("65", "新疆");
        hashtable.put("71", "台湾");
        hashtable.put("81", "香港");
        hashtable.put("82", "澳门");
        hashtable.put("91", "国外");
        return hashtable;
    }

    private static boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        return isNum.matches();
    }

    public static String getGenderByIdNo(String idNo) {
        idNo = StringUtils.trimToEmpty(idNo);
        int i;
        if (idNo.length() == 18) {
            i = Integer.parseInt(idNo.substring(16, 17));
        } else {
            if (idNo.length() != 15) {
                return null;
            }
            i = Integer.parseInt(idNo.substring(14, 15));
        }
        return i % 2 == 0 ? "1" : "0";
    }

    public static String getSexByIdNo(String idNo) {
        String sex = "";
        idNo = StringUtils.trimToEmpty(idNo);
        int sexCode;
        if (idNo.length() == 18) {
            sexCode = Integer.parseInt(idNo.substring(16, 17));
        } else {
            if (idNo.length() != 15) {
                return sex;
            }
            sexCode = Integer.parseInt(idNo.substring(14, 15));
        }
        if (sexCode % 2 == 0) {
            sex = "F";
        } else {
            sex = "M";
        }
        return sex;
    }

    public static String getBirthDayByIdNo(String idNo) {
        idNo = StringUtils.trimToEmpty(idNo);
        if (idNo.length() == 15) {
            return "19" + idNo.substring(6, 8) + "-" + idNo.substring(8, 10) + "-" + idNo.substring(10, 12);
        } else {
            return idNo.length() == 18 ? idNo.substring(6, 10) + "-" + idNo.substring(10, 12) + "-" + idNo.substring(12, 14) : null;
        }
    }

    public static int calculateAge(String birthdayStr) throws ServerException {
        if (birthdayStr != null && !"".equals(StringUtils.trim(birthdayStr))) {
            try {
                Date birthday = DateUtil.parseDate(birthdayStr, "yyyy-MM-dd");
                if (birthday != null) {
                    Calendar calendar = Calendar.getInstance();
                    int year1 = calendar.get(1);
                    calendar.setTime(birthday);
                    int year2 = calendar.get(1);
                    int age = year1 - year2;
                    return age;
                }
            } catch (Exception var6) {
                throw new ServerException("1070");
            }
        }

        return 0;
    }

    public static void main(String[] args) throws ServerException {
        System.out.println(getFullAge("1999-05-08"));
    }

    public static final String getIDCard(String id) {
        if (15 != id.length()) {
            return id;
        } else {
            int[] W = new int[]{7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2, 1};
            String[] A = new String[]{"1", "0", "X", "9", "8", "7", "6", "5", "4", "3", "2"};
            int s = 0;
            String newid = id.substring(0, 6) + "19" + id.substring(6, id.length());

            for(int i = 0; i < newid.length(); ++i) {
                int j = Integer.parseInt(newid.substring(i, i + 1)) * W[i];
                s += j;
            }

            s %= 11;
            newid = newid + A[s];
            return newid;
        }
    }

    public static String calculateAge(String birthdayStr, String format) {
        if (birthdayStr != null && !"".equals(birthdayStr)) {
            Date birthday = DateUtil.parseDate(birthdayStr, format);
            if (birthday != null) {
                Calendar calendar = Calendar.getInstance();
                int year1 = calendar.get(1);
                calendar.setTime(birthday);
                int year2 = calendar.get(1);
                int age = year1 - year2;
                return String.valueOf(age);
            }
        }

        return "";
    }

    public static String countAge(String birthdayStr, String vistadatestr, String ymd) {
        String age = "";
        if (birthdayStr != null && birthdayStr.length() == 8 && vistadatestr != null && vistadatestr.length() == 8) {
            Date birthday = DateUtil.parseDate(birthdayStr, "yyyyMMdd");
            Date visitdate = DateUtil.parseDate(vistadatestr, "yyyyMMdd");
            if (birthday != null && visitdate != null) {
                Calendar calendar1 = Calendar.getInstance();
                calendar1.setTime(visitdate);
                int year1 = calendar1.get(1);
                int month1 = calendar1.get(2) + 1;
                int day1 = calendar1.get(5);
                Calendar calendar2 = Calendar.getInstance();
                calendar2.setTime(birthday);
                int year2 = calendar2.get(1);
                int month2 = calendar2.get(2) + 1;
                int day2 = calendar2.get(5);
                int year = year1 - year2;
                int month = month1 - month2;
                int day = day1 - day2;
                if (month < 0) {
                    --year;
                    month = 12 + month1 - month2;
                }

                if (day < 0) {
                    --month;
                    if (month < 0) {
                        --year;
                        month = 11;
                    }
                }

                if ("1".equalsIgnoreCase(ymd)) {
                    age = String.valueOf(year);
                } else if ("2".equalsIgnoreCase(ymd)) {
                    age = String.valueOf(year * 12 + month);
                } else if ("3".equalsIgnoreCase(ymd)) {
                    long l = visitdate.getTime() - birthday.getTime();
                    age = String.valueOf(l / 60L / 60L / 1000L / 24L);
                }
            }
        }

        return age;
    }

    public static String getFullAge(String birthday) {
        String fullAge = "0";

        try {
            Date birthDate = DateUtil.parseDate(birthday, "yyyy-MM-dd");
            String birthformat = DateUtil.formatDate(birthDate, "yyyyMMdd");
            String age = countAge(birthformat, DateUtil.formatDate(new Date(), "yyyyMMdd"), "1");
            if ("0".equals(age)) {
                String mouth = countAge(birthformat, DateUtil.formatDate(new Date(), "yyyyMMdd"), "2");
                fullAge = mouth + "月";
            } else {
                int mouth = Integer.parseInt(countAge(birthformat, DateUtil.formatDate(new Date(), "yyyyMMdd"), "2")) % 12;
                fullAge = age + "岁" + mouth + "月";
            }

            return fullAge;
        } catch (Exception var6) {
            var6.printStackTrace();
            return "0";
        }
    }
}
