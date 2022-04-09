package com.phoenix.core.constant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Constant {
    public static String RSP_OK = "1";
    public static String RSP_ERROR = "0";
    public static String NEED = "1";
    public static String NOT_NEED = "0";
    public static String ENCRYPT_PAYCENTER = "phoenixPhoenix12";
    public static String ALIPAY_URL_SUCCESS = "1";
    public static String ALIPAY_URL_FAIL = "0";
    public static String ALIPAY_SUCCESS = "1";
    public static String ALIPAY_FAIL = "0";
    public static String ALIPAY_PAY_ING = "2";
    public static String REFUND_TYPE_TF = "2";
    public static String REFUND_TYPE_CZ = "1";
    public static String RSP_EXCEPTION = "2";
    public static List<String> payMethodList = new ArrayList();
    public static Map<String, String> thirdPayAcceptPayStatus = new HashMap();
    public static Map<String, String> thirdPayAcceptPayMethod = new HashMap();
    public static Map<String, String> thirdPayRefundPayStatus = new HashMap();
    public static Map<String, String> frontPay = new HashMap();
    public static Map<String, String> frontPayNotYb = new HashMap();
    public static Map<String, String> autoPayMethod = new HashMap();
    public static Map<String, String> autoRefundMethod = new HashMap();

    static {
        payMethodList.add("02");
        payMethodList.add("03");
        payMethodList.add("09");
        payMethodList.add("06");
        payMethodList.add("04");
        payMethodList.add("07");
        payMethodList.add("05");
        payMethodList.add("00");
        payMethodList.add("10");
        thirdPayAcceptPayStatus.put("00", "1");
        thirdPayAcceptPayStatus.put("11", "1");
        thirdPayAcceptPayStatus.put("12", "1");
        thirdPayAcceptPayStatus.put("09", "1");
        thirdPayAcceptPayStatus.put("02", "1");
        thirdPayAcceptPayMethod.put("02", "1");
        thirdPayAcceptPayMethod.put("03", "1");
        thirdPayAcceptPayMethod.put("11", "1");
        thirdPayRefundPayStatus.put("02", "1");
        thirdPayRefundPayStatus.put("09", "1");
        frontPayNotYb.put("04", "1");
        frontPayNotYb.put("10", "1");
        frontPay.put("04", "1");
        frontPay.put("10", "1");
        frontPay.put("06", "1");
        autoPayMethod.put("00", "1");
        autoPayMethod.put("07", "1");
        autoRefundMethod.put("07", "1");
        autoRefundMethod.put("02", "1");
        autoRefundMethod.put("03", "1");
        thirdPayAcceptPayMethod.put("11", "1");
    }

    public Constant() {
    }

    public interface Account_log {
        String BIZ_CODE = "ACCOUNT";
        String BIZ_NAME = "院内账户";
    }

    public interface BIZ_RESULT_FLAG {
        String SB = "0";
        String CG = "1";
        String CS = "2";
        String CLZ = "3";
        String WZ = "4";
        String QX = "5";
    }

    public interface BIZ_TYPE {
        String GH = "1";
        String ZYCZ = "5";
        String MZCZ = "4";
        String QH = "2";
        String JF = "3";
        String FK = "6";
        String YZTCZ = "7";
        String YZTFK = "8";
        String KHCZ = "9";
    }

    public interface CARD_VALID {
        String CARD_VALID_TRUE = "1";
        String CARD_VALID_FALSE = "0";
    }

    public interface HOSACC_PARAM {
        String USE_HOSACC_NO = "0";
        String USE_HOSACC_YES = "1";
    }

    public interface Inhos_log {
        String BIZ_CODE = "INHOS";
        String BIZ_NAME = "住院";
    }

    public interface ORDER_STATUS {
        String DZF = "00";
        String ZFZ = "01";
        String CLZ = "02";
        String ZFCG = "03";
        String HISSB = "04";
        String HISCS = "05";
        String XZFDSB = "06";
        String XZFDCS = "07";
    }

    public interface Others_log {
        String BIZ_CODE = "OTHERS";
        String BIZ_NAME = "其他";
    }

    public interface PAY_METHOD {
        String ZFB = "02";
        String WX = "03";
        String YH = "04";
        String YB = "06";
        String YNZH = "07";
        String XJ = "09";
        String YZT = "05";
        String LYZF = "00";
        String XYYKT = "10";
        String YLJHZF = "11";
    }

    public interface PAY_RESULT_FLAG {
        String YTK = "1";
        String WTK = "0";
        String XTK = "1";
        String CG = "1";
        String SB = "0";
        String CS = "2";
    }

    public interface PAY_STATUS {
        String DZF = "00";
        String ZFZ = "11";
        String ZFSB = "12";
        String ZFCG = "01";
        String DCZ = "14";
        String TKZ = "15";
        String TKSB = "17";
        String YTK = "05";
        String QXZF = "02";
        String ZFCS = "09";
        String CZCS = "10";
        String YTF = "18";
    }

    public interface REFUND_FLAG {
        String YTK = "1";
        String YTF = "2";
        String WTK = "0";
        String XTK = "1";
        String TKSB = "0";
    }

    public interface Receipt_Online_log {
        String BIZ_CODE = "RECEIPT_ONLINE";
        String BIZ_NAME = "电子凭条";
    }

    public interface Receipt_log {
        String BIZ_CODE = "RECEIPT";
        String BIZ_NAME = "挂号";
    }

    public interface Registration_log {
        String BIZ_CODE = "REGISTRATION";
        String BIZ_NAME = "缴费";
    }

    public interface Reservation_log {
        String BIZ_CODE = "RESERVEATION";
        String BIZ_NAME = "预约";
    }

    public interface SCHEDUE {
        String BIZ_SCHEDUEFLAG_ORDINARY = "1";
        String BIZ_SCHEDUEFLAG_EXPERT = "2";
        String DB_DO_FLAG_Y = "Y";
        String DB_DO_FLAG_N = "N";
        String DB_DO_FLAG_W = "W";
    }

    public interface Schedule_log {
        String BIZ_CODE = "SCHEDULE";
        String BIZ_NAME = "排班";
    }

    public interface Selfmachine_log {
        String BIZ_CODE = "SELFMACHINE";
        String BIZ_NAME = "自助机信息";
    }

    public interface ThirdPartyConst {
        String TP_NJ = "12320";
        String TP_NT = "NT12320";
        String TP_PK = "PK12320";
        String TP_XW = "XW12320";
        String TP_YZ = "YZ12320";
        String TP_ZJ = "ZJ12320";
        String TP_WH = "WH12320";
    }
}
