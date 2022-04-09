package com.phoenix.core.utils;

import com.phoenix.core.constant.HspConst;
import com.phoenix.core.exception.ServerException;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang3.StringUtils;

public class StrUtil {
    public StrUtil() {
    }

    public static String generateVerifyCode() {
        String result = "";
        Random random = new Random();

        for(int i = 0; i < 6; ++i) {
            result = result + random.nextInt(10);
        }

        return result;
    }

    public static String getVerifyCodeMsg(String verifyCode, int timeout) {
        return "验证码:" + verifyCode + ",为了保证您的账号安全,验证短信请勿转发给其他人。";
    }

    public static String crptyPwd(String pwd) {
        try {
            byte[] btInput = pwd.getBytes();
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            mdInst.update(btInput);
            byte[] md = mdInst.digest();
            StringBuffer sb = new StringBuffer();

            for(int i = 0; i < md.length; ++i) {
                int val = md[i] & 255;
                if (val < 16) {
                    sb.append("0");
                }

                sb.append(Integer.toHexString(val));
            }

            return sb.toString().toLowerCase();
        } catch (Exception var7) {
            return null;
        }
    }

    public static String crptyPhoneNumber(String phoneNumber) throws ServerException {
        if (phoneNumber == null) {
            throw new ServerException("1025");
        } else if (phoneNumber.trim().length() < HspConst.SYS_LIMIT_PHONENO_LEN) {
            throw new ServerException("1026");
        } else {
            return phoneNumber.substring(0, 3) + "****" + phoneNumber.substring(phoneNumber.length() - 4);
        }
    }

    public static String getFlowByCurrDate() {
        Random rd = new Random();
        StringBuffer indexStr = new StringBuffer();

        for(int i = 0; i < 4; ++i) {
            indexStr.append(String.valueOf(rd.nextInt(10)));
        }

        String flow = DateUtil.getCurrDateTime() + (indexStr != null ? indexStr.toString() : "");
        return flow;
    }

    public static boolean isOutRegisterTime(String seeTime, String fetchTime) {
        boolean flag = false;
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        if (fetchTime != null && !"".equals(fetchTime) && fetchTime.indexOf(" ") != -1) {
            fetchTime = fetchTime.substring(fetchTime.indexOf(" "));
        }

        try {
            Date CurrTimeD = sdf.parse(DateUtil.getCurrDateTime("HH:mm"));
            Date seeTimeD;
            if ("上午".equals(seeTimeConvert(seeTime))) {
                seeTimeD = sdf.parse(fetchTime);
                flag = !CurrTimeD.before(seeTimeD);
            } else if ("下午".equals(seeTimeConvert(seeTime))) {
                seeTimeD = sdf.parse(fetchTime);
                flag = !CurrTimeD.before(seeTimeD);
            }

            return flag;
        } catch (Exception var6) {
            var6.printStackTrace();
            return true;
        }
    }

    public static String seeTimeConvert(String seeTime) {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        if (seeTime != null && !"".equals(seeTime)) {
            if (!"上午".equals(seeTime) && !"下午".equals(seeTime)) {
                try {
                    if (sdf.parse(seeTime).before(sdf.parse("12:00"))) {
                        seeTime = "上午";
                    } else {
                        seeTime = "下午";
                    }

                    return seeTime;
                } catch (Exception var3) {
                    var3.printStackTrace();
                    return seeTime;
                }
            } else {
                return seeTime;
            }
        } else {
            return "";
        }
    }

    public static String seeTimeToStr(String seeTime) {
        if ("1".equals(seeTime)) {
            return "上午";
        } else if ("3".equals(seeTime)) {
            return "下午";
        } else if (seeTime != null && seeTime.indexOf(":") > 0) {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
                if (sdf.parse(seeTime).before(sdf.parse("12:00"))) {
                    seeTime = "上午";
                } else {
                    seeTime = "下午";
                }
            } catch (Exception var2) {
            }

            return seeTime;
        } else {
            return seeTime;
        }
    }

    public static String getDefaultSeeTime(String seeTime) {
        if (seeTime != null && seeTime.trim().length() != 0) {
            return seeTime;
        } else {
            return DateUtil.getCurrHour() < 12 ? "1" : "3";
        }
    }

    public static String getDefaultFee(String fee, String defaultPayAmount) {
        if (fee != null && fee.trim().length() > 0) {
            return fee;
        } else if (defaultPayAmount != null && defaultPayAmount.trim().length() != 0) {
            try {
                Double d = new Double(defaultPayAmount);
                DecimalFormat df = new DecimalFormat("0.00");
                return df.format(d / 100.0D);
            } catch (Exception var4) {
                return "0";
            }
        } else {
            return "0";
        }
    }

    public static String Html2Text(String htmlStr) {
        String textStr = "";

        try {
            String regEx_script = "<[//s]*?script[^>]*?>[//s//S]*?<[//s]*?///[//s]*?script[//s]*?>";
            String regEx_style = "<[//s]*?style[^>]*?>[//s//S]*?<[//s]*?///[//s]*?style[//s]*?>";
            String regEx_html = "<[^>]+>";
            String regEx_html1 = "<[^>]+";
            Pattern p_script = Pattern.compile(regEx_script, 2);
            Matcher m_script = p_script.matcher(htmlStr);
            htmlStr = m_script.replaceAll("");
            Pattern p_style = Pattern.compile(regEx_style, 2);
            Matcher m_style = p_style.matcher(htmlStr);
            htmlStr = m_style.replaceAll("");
            Pattern p_html = Pattern.compile(regEx_html, 2);
            Matcher m_html = p_html.matcher(htmlStr);
            htmlStr = m_html.replaceAll("");
            Pattern p_html1 = Pattern.compile(regEx_html1, 2);
            Matcher m_html1 = p_html1.matcher(htmlStr);
            htmlStr = m_html1.replaceAll("");
            textStr = htmlStr.replaceAll("</?[^<]+>", "");
            textStr = textStr.replaceAll("\\s*|\t|\r|\n", "");
            textStr = textStr.replaceAll("&nbsp;", "");
            textStr = textStr.replaceAll("&ldquo;", "“");
            textStr = textStr.replaceAll("&rdquo;", "”");
        } catch (Exception var14) {
            System.err.println("Html2Text: " + var14.getMessage());
        }

        return textStr;
    }

    public static boolean isOverLengthForDecimal(String strIn, int iLength, int iLengthOfDecimal) {
        if (strIn.startsWith("-")) {
            strIn = strIn.substring(1);
        }

        int iLengthOfForInteger = iLength - iLengthOfDecimal;
        if (strIn.indexOf(".") > 0) {
            if (strIn.substring(0, strIn.indexOf(".")).length() > iLengthOfForInteger) {
                return true;
            }
        } else {
            if (strIn.indexOf(".") == 0) {
                return false;
            }

            if (strIn.length() > iLengthOfForInteger) {
                return true;
            }
        }

        return false;
    }

    public static boolean isNull(String strIn) {
        if (strIn == null) {
            return true;
        } else {
            return delSpace(strIn, true).length() == 0;
        }
    }

    public static String delSpace(String strIn, boolean bFullSpaceDel) {
        if (strIn == null) {
            return "";
        } else {
            boolean bFlag = true;
            strIn = strIn.trim();

            while(bFlag && bFullSpaceDel && strIn.length() > 0) {
                String strStart = strIn.substring(0, 1);
                String strEnd = strIn.substring(strIn.length() - 1);
                if (!strEnd.equalsIgnoreCase("　") && !strEnd.equalsIgnoreCase(" ")) {
                    if (!strStart.equalsIgnoreCase("　") && !strStart.equalsIgnoreCase(" ")) {
                        bFlag = false;
                    } else {
                        strIn = strIn.substring(1, strIn.length());
                    }
                } else {
                    strIn = strIn.substring(0, strIn.length() - 1);
                }
            }

            return strIn;
        }
    }

    public static String delAllSpace(String strIn, boolean bFullSpaceDel) {
        StringBuffer sb = new StringBuffer();
        strIn = delSpace(strIn, bFullSpaceDel);

        for(int i = 0; i < strIn.length(); ++i) {
            if (bFullSpaceDel) {
                if (strIn.charAt(i) != 12288 || strIn.charAt(i) != ' ') {
                    sb.append(strIn.charAt(i));
                }
            } else if (strIn.charAt(i) != ' ') {
                sb.append(strIn.charAt(i));
            }
        }

        strIn = new String(sb);
        return strIn;
    }

    public static boolean isValidFile(String strFileName) {
        if (isNull(strFileName)) {
            return false;
        } else {
            File fTmp = new File(strFileName);
            return fTmp.exists() && fTmp.isFile() && fTmp.canRead();
        }
    }

    public static String replace(String strIn, String strRe, String strBy) {
        String strTemp = "";

        for(int iPos = strIn.indexOf(strRe); iPos != -1; iPos = strIn.indexOf(strRe)) {
            strTemp = strTemp + strIn.substring(0, iPos) + strBy;
            strIn = strIn.substring(iPos + strRe.length());
        }

        strIn = strTemp + strIn;
        return strIn;
    }

    public static String replaceNull(String value) {
        return value == null ? "" : value;
    }

    public static String transToHtml(String strIn) {
        if (strIn == null) {
            return "";
        } else if (strIn.equals("")) {
            return strIn;
        } else {
            strIn = replace(strIn, "&", "&amp;");
            strIn = replace(strIn, "<", "&lt;");
            strIn = replace(strIn, ">", "&gt;");
            strIn = replace(strIn, "\"", "&quot;");
            strIn = replace(strIn, "\r\n", "<br>");
            strIn = replace(strIn, "\n", "<br>");
            return strIn;
        }
    }

    public static String transToHtmlTA(String strIn) {
        String strRet = transToHtml(strIn);
        strRet = replace(strRet, "<br>", "\r\n");
        return strRet;
    }

    public static String FullToHalf(String s) {
        if (s == null) {
            return s;
        } else if (s.length() == 0) {
            return s;
        } else {
            int iLen = s.length();
            StringBuffer sb = new StringBuffer();

            for(int i = 0; i < iLen; ++i) {
                char c = s.charAt(i);
                if (c >= '！' && '～' > c) {
                    c -= 'ﻠ';
                } else if (c == '￥') {
                    c -= 'ﾉ';
                } else if (c == 8217) {
                    c = (char)(c - 8178);
                } else if (c == 8221) {
                    c = (char)(c - 8187);
                } else if (c == 12316) {
                    c = (char)(c - 12190);
                } else if (c == 8722) {
                    c = (char)(c - 8677);
                } else if (c == 8216) {
                    c = (char)(c - 8120);
                } else if (c == 12290) {
                    c = (char)(c - 12244);
                } else if (c == 12289) {
                    c = (char)(c - 12245);
                } else if (c == 12288) {
                    c = (char)(c - 12256);
                }

                sb.append(c);
            }

            return new String(sb);
        }
    }

    public static String HalfToFull(String s) {
        if (s == null) {
            return s;
        } else if (s.length() == 0) {
            return s;
        } else {
            int iLen = s.length();
            StringBuffer sb = new StringBuffer();

            for(int i = 0; i < iLen; ++i) {
                char c = s.charAt(i);
                if (c >= '!' && '~' >= c) {
                    c += 'ﻠ';
                } else if (c == '\\') {
                    c += 'ﾉ';
                } else if (c == '\'') {
                    c = (char)(c + 8178);
                } else if (c == '"') {
                    c = (char)(c + 8187);
                } else if (c == '~') {
                    c = (char)(c + 12190);
                } else if (c == '-') {
                    c = (char)(c + 8677);
                } else if (c == '`') {
                    c = (char)(c + 8120);
                } else if (c == '.') {
                    c = (char)(c + 12244);
                } else if (c == ',') {
                    c = (char)(c + 12245);
                } else if (c == ' ') {
                    c = (char)(c + 12256);
                }

                sb.append(c);
            }

            return new String(sb);
        }
    }

    public static boolean isHalfNum(String strIn) {
        if (strIn == null) {
            return true;
        } else {
            for(int i = 0; i < strIn.length(); ++i) {
                char c = strIn.charAt(i);
                if (c > '9' || c < '0') {
                    return false;
                }
            }

            return true;
        }
    }

    public static boolean isHalfEng(String strIn) {
        if (strIn == null) {
            return true;
        } else {
            for(int i = 0; i < strIn.length(); ++i) {
                char c = strIn.charAt(i);
                if ((c > 'z' || c < 'a') && (c > 'Z' || c < 'A')) {
                    return false;
                }
            }

            return true;
        }
    }

    public static boolean isHalfDec(String strIn) {
        if (strIn == null) {
            return true;
        } else if (strIn.indexOf(".") != strIn.lastIndexOf(".")) {
            return false;
        } else {
            for(int i = 0; i < strIn.length(); ++i) {
                char c = strIn.charAt(i);
                if ((c > '9' || c < '0') && c != '.') {
                    return false;
                }
            }

            return true;
        }
    }

    public static boolean isHalfDec(String strIn, int length, boolean isNegative) {
        if (strIn == null) {
            return true;
        } else {
            if (isNegative && strIn.indexOf("-") != -1) {
                if (strIn.indexOf("-") != 0) {
                    return false;
                }

                if (strIn.indexOf("-") != strIn.lastIndexOf("-")) {
                    return false;
                }

                strIn = replace(strIn, "-", "");
            }

            if (!isHalfDec(strIn)) {
                return false;
            } else {
                if (strIn.indexOf(".") > -1) {
                    strIn = strIn.substring(strIn.indexOf(".") + 1);
                    if (strIn.length() > length) {
                        return false;
                    }
                }

                return true;
            }
        }
    }

    public static boolean isHalfEng_Num(String strIn) {
        if (strIn == null) {
            return true;
        } else {
            for(int i = 0; i < strIn.length(); ++i) {
                char c = strIn.charAt(i);
                if ((c > 'z' || c < 'a') && (c > 'Z' || c < 'A') && (c > '9' || c < '0')) {
                    return false;
                }
            }

            return true;
        }
    }

    public static boolean isHalfEng_Num_Chr(String strIn, String SpecialCharList) {
        if (strIn == null) {
            return true;
        } else if (isNull(SpecialCharList)) {
            return isHalf(strIn);
        } else {
            for(int i = 0; i < strIn.length(); ++i) {
                char c = strIn.charAt(i);
                if ((c > 'z' || c < 'a') && (c > 'Z' || c < 'A') && (c > '9' || c < '0') && SpecialCharList.indexOf(String.valueOf(c)) == -1) {
                    return false;
                }
            }

            return true;
        }
    }

    public static boolean isEng_Num_Chr(String strIn, String SpecialCharList) {
        if (strIn == null) {
            return true;
        } else if (isNull(SpecialCharList)) {
            return true;
        } else {
            strIn = FullToHalf(strIn);
            SpecialCharList = FullToHalf(SpecialCharList);
            return isHalfEng_Num_Chr(strIn, SpecialCharList);
        }
    }

    public static boolean isHalf(String strIn) {
        if (strIn == null) {
            return true;
        } else {
            byte[] b = strIn.getBytes();
            return b.length == strIn.length();
        }
    }

    public static boolean isFull(String strIn) {
        if (strIn == null) {
            return false;
        } else {
            byte[] b = strIn.getBytes();
            return b.length == strIn.length() * 2;
        }
    }

    public static boolean isLowCase(String strIn) {
        for(int i = 0; i < strIn.length(); ++i) {
            char c = strIn.charAt(i);
            if (!Character.isLowerCase(c)) {
                return false;
            }
        }

        return true;
    }

    public static boolean sHalfLowEng_Num(String strIn) {
        for(int i = 0; i < strIn.length(); ++i) {
            char c = strIn.charAt(i);
            if ((c > 'z' || c < 'a') && (c > '9' || c < '0')) {
                return false;
            }
        }

        return true;
    }

    public static String decodeSingleQuote(String strIn) {
        if (strIn == null) {
            return strIn;
        } else {
            strIn = replace(strIn, "''", "'");
            return strIn;
        }
    }

    public static String encodeSingleQuote(String strIn) {
        if (strIn == null) {
            return strIn;
        } else {
            strIn = replace(strIn, "'", "''");
            return strIn;
        }
    }

    public static String encodeSqlLike(String strIn, String strEscape) {
        strIn = replace(strIn, strEscape, strEscape + strEscape);
        strIn = replace(strIn, "%", strEscape + "%");
        strIn = replace(strIn, "_", strEscape + "_");
        strIn = replace(strIn, "％", strEscape + "％");
        strIn = replace(strIn, "＿", strEscape + "＿");
        strIn = encodeSingleQuote(strIn);
        return strIn;
    }

    public static String getLenString(String strIn, char cIn, int iLen, int iType) {
        String strRet = strIn;
        int iNum = iLen - strIn.length();

        for(int i = 1; i <= iNum; ++i) {
            if (iType == 0) {
                strRet = String.valueOf(cIn) + strRet;
            } else {
                strRet = strRet + String.valueOf(cIn);
            }
        }

        return strRet;
    }

    public static String getFormatNumber(String strIn, int iLen) {
        if (isNull(strIn)) {
            return strIn;
        } else {
            String strRet = strIn;
            if (strIn.startsWith(".")) {
                strRet = "0" + strIn;
            }

            int iNum;
            if (strRet.indexOf(".") < 0) {
                iNum = iLen;
                strRet = strRet + ".";
            } else {
                iNum = iLen - strRet.substring(strRet.indexOf(".") + 1).length();
            }

            if (iNum > 0) {
                for(int i = 1; i <= iNum; ++i) {
                    strRet = strRet + "0";
                }
            } else if (iNum < 0) {
                strRet = strRet.substring(0, strRet.length() + iNum + 1);
                float fTemp = Float.parseFloat(strRet);
                int iTemp = (int)(fTemp * (float)((int)Math.pow(10.0D, (double)iLen)) + 0.5F);
                strRet = String.valueOf((float)iTemp / 100.0F);
            }

            return strRet;
        }
    }

    public static boolean isEmpty(String s) {
        return s == null || "".equals(s);
    }

    public static int getStringCnt(String strIn, char cIn) {
        int iRet = 0;
        if (strIn != null && strIn.length() != 0 && String.valueOf(cIn).length() != 0) {
            if (strIn.indexOf(String.valueOf(cIn)) == -1) {
                return 0;
            } else {
                for(int i = 0; i < strIn.length(); ++i) {
                    char c = strIn.charAt(i);
                    if (c == cIn) {
                        ++iRet;
                    }
                }

                return iRet;
            }
        } else {
            return 0;
        }
    }

    public static String formatMoney(String strMoney) {
        if (isNull(strMoney)) {
            return "";
        } else if (!isHalfDec(strMoney)) {
            return strMoney;
        } else {
            NumberFormat p = NumberFormat.getCurrencyInstance();
            String strOut = p.format((double)Float.parseFloat(strMoney));
            return strOut;
        }
    }

    public static boolean isURL(String strIn) {
        boolean bFlag = true;
        strIn = strIn.toLowerCase();
        if (!isHalf(strIn)) {
            return false;
        } else if (strIn.indexOf("http://") == -1) {
            return false;
        } else if (strIn.indexOf("http://") != 0) {
            return false;
        } else if (strIn.indexOf("http://") != strIn.lastIndexOf("http://")) {
            return false;
        } else {
            String strTemp = strIn.replace(':', '1');
            strTemp = strTemp.replace('.', '1');
            strTemp = strTemp.replace('-', '1');
            strTemp = strTemp.replace('_', '1');
            strTemp = strTemp.replace('/', '1');
            if (!isHalfEng_Num(strTemp)) {
                return false;
            } else {
                int nPos = strIn.indexOf("http://");
                String strRight = strIn.substring(nPos + 7);
                if (strRight.indexOf(".") == -1) {
                    return false;
                } else if (strRight.indexOf(".") != 0 && strRight.lastIndexOf(".") != strRight.length() - 1) {
                    nPos = strIn.lastIndexOf(".");
                    strTemp = strIn.substring(nPos + 1);
                    if (isHalfNum(strTemp)) {
                        return false;
                    } else {
                        strTemp = strRight;
                        nPos = strRight.indexOf(".");

                        while(bFlag) {
                            strTemp = strTemp.substring(nPos + 1);
                            nPos = strTemp.indexOf(".");
                            if (nPos == 0) {
                                return false;
                            }

                            if (nPos == -1) {
                                bFlag = false;
                            }
                        }

                        return true;
                    }
                } else {
                    return false;
                }
            }
        }
    }

    public static boolean isEmail(String strIn) {
        boolean bFlag = true;
        if (!isHalf(strIn)) {
            return false;
        } else if (strIn.indexOf("@") == -1) {
            return false;
        } else if (strIn.indexOf("@") != strIn.lastIndexOf("@")) {
            return false;
        } else {
            String strTemp = strIn.replace('@', '1');
            strTemp = strTemp.replace('.', '1');
            strTemp = strTemp.replace('-', '1');
            strTemp = strTemp.replace('_', '1');
            if (!isHalfEng_Num(strTemp)) {
                return false;
            } else {
                int nPos = strIn.indexOf("@");
                String strLeft = strIn.substring(0, nPos);
                String strRight = strIn.substring(nPos + 1);
                if (strLeft.startsWith(".")) {
                    return false;
                } else if (strLeft.length() < 1) {
                    return false;
                } else if (strRight.indexOf(".") == -1) {
                    return false;
                } else if (strRight.indexOf(".") != 0 && strRight.lastIndexOf(".") != strRight.length() - 1) {
                    nPos = strIn.lastIndexOf(".");
                    strTemp = strIn.substring(nPos + 1);
                    if (isHalfNum(strTemp)) {
                        return false;
                    } else {
                        strTemp = strRight;
                        nPos = strRight.indexOf(".");

                        while(bFlag) {
                            strTemp = strTemp.substring(nPos + 1);
                            nPos = strTemp.indexOf(".");
                            if (nPos == 0) {
                                return false;
                            }

                            if (nPos == -1) {
                                bFlag = false;
                            }
                        }

                        return true;
                    }
                } else {
                    return false;
                }
            }
        }
    }

    public static boolean isFileName(String strFileName) {
        strFileName = strFileName.trim();
        if (strFileName == null) {
            return true;
        } else if (isHalfEng_Num(strFileName)) {
            return true;
        } else if (strFileName.indexOf("_") == -1 && strFileName.indexOf(".") == -1) {
            return false;
        } else {
            int iS1 = 0;
            int iS2 = 0;
            int iLength = strFileName.length();

            int i;
            for(i = 0; i < iLength; ++i) {
                char cTmp = strFileName.charAt(i);
                if (cTmp == '_') {
                    if (i == 0 || i == iLength - 1) {
                        return false;
                    }

                    ++iS1;
                } else if (cTmp == '.') {
                    if (iS1 == 1) {
                        return false;
                    }

                    if (i == iLength - 1) {
                        return false;
                    }

                    ++iS2;
                } else {
                    if ((cTmp > 'z' || cTmp < 'a') && (cTmp > 'Z' || cTmp < 'A') && (cTmp > '9' || cTmp < '0')) {
                        return false;
                    }

                    iS1 = 0;
                }

                if (iS1 > 1) {
                    return false;
                }

                if (iS2 > 1) {
                    return false;
                }
            }

            if (iS2 == 1) {
                int iPosPoint = 0;
                i = strFileName.indexOf(".");
                String strFileNameTmp = strFileName.substring(i + 1, strFileName.length());
                if (!isHalfEng_Num(strFileNameTmp)) {
                    return false;
                }
            }

            return true;
        }
    }

    public static boolean isPathAndFile(String strVal) {
        String strDblSep = File.separator + File.separator;
        String strSub = "";
        StringBuffer sb = new StringBuffer();
        if (strVal.indexOf(strDblSep) < 0 && strVal.indexOf("//") < 0) {
            for(int i = 0; i < strVal.length(); ++i) {
                char c = strVal.charAt(i);
                if (c != '/' && c != File.separatorChar) {
                    sb.append(c);
                }
            }

            strSub = sb.toString();
            if (isFileName(strSub)) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public static String[] getExcelKeyValue(String strIn) {
        String[] result = new String[2];
        if (isEmpty(strIn)) {
            result[0] = "";
            result[1] = "";
            return result;
        } else if (!isExcelKeyValue(strIn)) {
            result[0] = strIn;
            result[1] = strIn;
            return result;
        } else {
            int iStartIndex = strIn.lastIndexOf(91);
            result[0] = strIn.substring(iStartIndex + 1, strIn.length() - 1);
            result[1] = strIn.substring(0, iStartIndex);
            return result;
        }
    }

    public static boolean isExcelKeyValue(String strIn) {
        if (isNull(strIn)) {
            return true;
        } else {
            int iEndIndex = strIn.lastIndexOf("]");
            if (iEndIndex == -1) {
                return false;
            } else if (iEndIndex != strIn.length() - 1) {
                return false;
            } else {
                int iStartIndex = strIn.lastIndexOf("[", iEndIndex);
                if (iStartIndex == -1) {
                    return false;
                } else if (iStartIndex == 0) {
                    return false;
                } else {
                    String key = strIn.substring(iStartIndex + 1, iEndIndex);
                    if (isNull(key)) {
                        return false;
                    } else {
                        key = replace(key, "_", "");
                        return isHalfEng_Num(key);
                    }
                }
            }
        }
    }

    public static boolean isNumeric(String str, Class<? extends Number> clazz) {
        try {
            if (clazz.equals(Byte.class)) {
                Byte.parseByte(str);
            } else if (clazz.equals(Double.class)) {
                Double.parseDouble(str);
            } else if (clazz.equals(Float.class)) {
                Float.parseFloat(str);
            } else if (clazz.equals(Integer.class)) {
                Integer.parseInt(str);
            } else if (clazz.equals(Long.class)) {
                Long.parseLong(str);
            } else if (clazz.equals(Short.class)) {
                Short.parseShort(str);
            }

            return true;
        } catch (NumberFormatException var3) {
            return false;
        }
    }

    public static String deleteLeftZero(String strIn) {
        long lIn = Long.parseLong(strIn);
        String strOut = String.valueOf(lIn);
        return strOut;
    }

    public static List copyList(List list) {
        List result = new ArrayList();
        if (list == null) {
            return null;
        } else {
            for(int i = 0; i < list.size(); ++i) {
                result.add(list.get(i));
            }

            return result;
        }
    }

    public static void writeFile(String originalFile, String destFile) throws IOException {
        File fileOriginal = new File(originalFile);
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(fileOriginal));
        File fileDest = new File(destFile);
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(fileDest));
        byte[] bufferData = new byte[8192];

        while(bis.read(bufferData) != -1) {
            bos.write(bufferData);
        }

        bos.close();
        bis.close();
    }

    public static boolean isFileExists(String fileName) {
        File f = new File(fileName);
        return f.exists();
    }

    public static boolean isEqual(String oldValue, String newValue) {
        if (isEmpty(oldValue) && !isEmpty(newValue)) {
            return false;
        } else if (!isEmpty(oldValue) && isEmpty(newValue)) {
            return false;
        } else if (isEmpty(oldValue) && isEmpty(newValue)) {
            return true;
        } else {
            return oldValue.equals(newValue);
        }
    }

    public static boolean isEqual(int oldValue, int newValue) {
        return oldValue == newValue;
    }

    public static String arrayToString(String[] array, String split) {
        StringBuffer sb = new StringBuffer();
        if (array != null) {
            for(int i = 0; i < array.length; ++i) {
                sb.append(array[i]);
                if (i < array.length - 1) {
                    sb.append(split);
                }
            }
        }

        return sb.toString();
    }

    public static String createPayCode() {
        String s = "";
        List<Integer> list = new ArrayList();

        int i;
        for(i = 0; i < 10; ++i) {
            list.add(i);
            Collections.shuffle(list);
        }

        for(i = 0; i < 12; ++i) {
            if (i != 0 && i % 4 == 0) {
                s = s + " ";
            }

            int r = (int)(Math.random() * 10.0D);
            s = s + String.valueOf(list.get(r));
        }

        return s;
    }

    public static String generatePassword() {
        try {
            Class var0 = StrUtil.class;
            synchronized(StrUtil.class) {
                String password = "";
                long current = System.currentTimeMillis();
                byte[] now = (new Long(current)).toString().getBytes();
                MessageDigest md = MessageDigest.getInstance("MD5");
                md.update(now);
                byte[] buffer = md.digest();
                StringBuffer sb = new StringBuffer(buffer.length * 2);

                for(int i = 0; i < buffer.length; ++i) {
                    sb.append(Character.forDigit((buffer[i] & 240) >> 4, 16));
                    sb.append(Character.forDigit(buffer[i] & 15, 16));
                }

                password = sb.toString();
                if (password.length() > 8) {
                    password = password.substring(0, 8);
                }

                return password;
            }
        } catch (NoSuchAlgorithmException var10) {
            return null;
        }
    }

    public static String getSystemLineSep() {
        String strLineSep = System.getProperty("line.separator");
        return strLineSep;
    }

    public static String getSystemFileSep() {
        String strFileSep = System.getProperty("file.separator");
        return strFileSep;
    }

    public static boolean isMorningSeeTimeInterval(String seeTime) {
        if (seeTime == null) {
            return false;
        } else {
            int index = seeTime.indexOf(":");
            if (index < 0) {
                return false;
            } else {
                return Integer.parseInt(seeTime.substring(0, index)) < 12;
            }
        }
    }

    public static boolean isAfternoonSeeTimeInterval(String seeTime) {
        if (seeTime == null) {
            return false;
        } else {
            int index = seeTime.indexOf(":");
            if (index < 0) {
                return false;
            } else {
                return Integer.parseInt(seeTime.substring(0, index)) >= 12;
            }
        }
    }

    public static String StrTimeToIntTime(String seeTime) {
        if ("上午".equals(seeTime)) {
            seeTime = "1";
        } else if ("中午".equals(seeTime)) {
            seeTime = "2";
        } else if ("下午".equals(seeTime)) {
            seeTime = "3";
        } else if ("夜晚".equals(seeTime)) {
            seeTime = "4";
        } else if ("早班".equals(seeTime)) {
            seeTime = "5";
        } else if ("全天".equals(seeTime)) {
            seeTime = "6";
        }

        return seeTime;
    }

    public static String intTimeToStrTime(String seeTime) {
        if ("1".equals(seeTime)) {
            seeTime = "上午";
        } else if ("2".equals(seeTime)) {
            seeTime = "下午";
        } else if ("3".equals(seeTime)) {
            seeTime = "下午";
        } else if ("4".equals(seeTime)) {
            seeTime = "夜晚";
        } else if ("5".equals(seeTime)) {
            seeTime = "早班";
        } else if ("6".equals(seeTime)) {
            seeTime = "全天";
        }

        return seeTime;
    }

    public static boolean isOverCancleTime(String reservateDate) {
        boolean flag = false;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        try {
            Date date = sdf.parse(reservateDate);
            long time = date.getTime() - (new Date()).getTime();
            if (time >= 0L) {
                flag = true;
            }
        } catch (ParseException var6) {
            var6.printStackTrace();
        }

        return flag;
    }

    public static String resolveExp(String exp, Object obj, HashMap<String, String> map) throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        if (StringUtils.isNotBlank(exp) && exp.contains("{") && exp.contains("}")) {
            String propertyName = exp.substring(exp.indexOf("{") + 1, exp.indexOf("}"));
            Object propertyValue;
            if (map.containsKey(propertyName)) {
                propertyValue = map.get(propertyName);
            } else {
                propertyValue = getObjectPropertyValue(obj, propertyName);
            }

            exp = replaceFirstPropertyValue(exp, propertyValue);
            if (StringUtils.isNotBlank(exp) && exp.contains("{") && exp.contains("}")) {
                exp = resolveExp(exp, obj);
            }
        }

        return exp;
    }

    public static String resolveExp(String exp, Object obj) throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        if (StringUtils.isNotBlank(exp) && exp.contains("{") && exp.contains("}")) {
            String propertyName = exp.substring(exp.indexOf("{") + 1, exp.indexOf("}"));
            Object propertyValue = getObjectPropertyValue(obj, propertyName);
            exp = replaceFirstPropertyValue(exp, propertyValue);
            if (StringUtils.isNotBlank(exp) && exp.contains("{") && exp.contains("}")) {
                exp = resolveExp(exp, obj);
            }
        }

        return exp;
    }

    public static String replaceFirstPropertyValue(String src, Object propertyValue) {
        if (src == null) {
            return "";
        } else {
            String s1 = src.substring(0, src.indexOf("{"));
            String s2 = src.substring(src.indexOf("}") + 1, src.length());
            return s1 + (propertyValue == null ? "" : propertyValue) + s2;
        }
    }

    public static String replacePropertyValue(String src, String propertyName, String propertyValue) {
        return src == null ? "" : src.replaceAll("\\{" + propertyName + "\\}", propertyValue == null ? "" : propertyValue);
    }

    public static Object getObjectPropertyValue(Object obj, String propertyName) throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        try {
            String method = "get" + String.valueOf(propertyName.charAt(0)).toUpperCase() + propertyName.subSequence(1, propertyName.length());
            return obj.getClass().getMethod(method).invoke(obj);
        } catch (Exception var3) {
            return null;
        }
    }

    public static boolean socialCheck15(String socialCode) {
        socialCode = rightPad(socialCode, "0", 15);
        Pattern Pattern15 = Pattern.compile("[1-9]\\d{7}((0\\d)|(1[0-2]))((0[1-9])|([1|2]\\d)|3[0-1])\\d{3}");
        Matcher Matcher15 = Pattern15.matcher(socialCode);
        return Matcher15.matches();
    }

    public static String rightPad(String a, String as_pad, int str_num) {
        String ls_result = a;
        int str_num_cn = a.getBytes(Charset.forName("GBK")).length;
        int str_num_D = str_num_cn - a.length();

        for(int i = 1; i <= str_num - str_num_cn; ++i) {
            ls_result = ls_result + as_pad;
        }

        return ls_result.substring(0, str_num - str_num_D);
    }
}

