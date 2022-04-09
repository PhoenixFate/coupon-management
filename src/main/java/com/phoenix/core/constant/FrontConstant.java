package com.phoenix.core.constant;

import java.util.HashMap;

public class FrontConstant {
    public static final String LOG_CURRENT_STEPT_REGISTER = "001";
    public static final String LOG_CURRENT_STEPT_PREREGISTER = "002";
    public static final String LOG_CURRENT_STEPT_PREREGISTERCONFIRM = "003";
    public static final String LOG_CURRENT_STEPT_PAYMENT = "004";
    public static final String LOG_CURRENT_STEPT_RECHARGE = "005";
    public static final String LOG_NEXT_STEP_FINISH = "F";
    public static final String LOG_NEXT_STEP_CANCEL = "C";
    public static final String FUNCTION_REGISTER_CODE = "1";
    public static final String FUNCTION_PAYMENT_CODE = "2";
    public static final String FUNTION_REPORT_CODE = "3";
    public static final String FUNCTION_PREREGISTER_CODE = "4";
    public static final String FUNCTION_RECHARGE_CODE = "5";
    public static final String HSPS_APP_TYPE = "HSPS";
    public static final String UNDERLINE_PTDY = "PTDY";
    public static final String UNDERLINE_YYQH = "YYQH";
    public static final String LOG_REQUEST = "0";
    public static final String LOG_RESPONSE = "1";
    public static final String BIZ_FAIL = "-1";
    public static final String LOG_UNDO = "0";
    public static final String LOG_DO = "1";
    public static final String LOG_ISDO = "2";
    private static HashMap<String, String> log_Container = new HashMap();
    public static String GET_CHECK_CODE;
    public static String OPEN_SI_ONLINE_PAY;
    public static String GET_SI_ONLINE_PAY;
    public static String SCAN_PAY_REQUEST;
    public static String SCAN_PAY_RESULT_UPLOAD;
    public static String GET_SELF_PATCHVERSION;
    public static String GET_PATCHVERSION_CODES;
    public static String SAVE_VERSIONDOWN_LOG;

    static {
        log_Container.put("1", "挂号");
        log_Container.put("4", "预约挂号");
        log_Container.put("2", "缴费");
        log_Container.put("5", "充值");
        GET_CHECK_CODE = "sys/getCheckCode";
        OPEN_SI_ONLINE_PAY = "si/openSiOnlinePay";
        GET_SI_ONLINE_PAY = "si/getSiOnlinePayInfo";
        SCAN_PAY_REQUEST = "scan/scanPayRequest";
        SCAN_PAY_RESULT_UPLOAD = "scan/scanPayResultUpload";
        GET_SELF_PATCHVERSION = "selfpatchversion/getSelfPatchVersion";
        GET_PATCHVERSION_CODES = "selfpatchversion/getDownVersionCode";
        SAVE_VERSIONDOWN_LOG = "selfpatchversion/saveVersionDownLog";
    }

    public FrontConstant() {
    }

    public static String getRspMsg(String logType) {
        return (String)log_Container.get(logType);
    }

    public static boolean isLsCard(String cardNo) {
        boolean flag = false;
        if (cardNo != null && cardNo.indexOf("LS") != -1) {
            flag = true;
        }

        return flag;
    }
}
