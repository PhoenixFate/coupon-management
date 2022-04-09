package com.phoenix.core.constant;

import java.util.HashMap;

public class ZzConstant {
    public static String DATA_TYPE_YBHIS = "YBHIS";
    public static String DATA_TYPE_HIS = "ZYHIS";
    public static String DATA_TYPE_ZYBANK = "ZYBANK";
    public static String DATA_TYPE_MZHOSACC = "MZHOSACC";
    public static String DATA_TYPE_ZYHOSACC = "ZYHOSACC";
    public static String DATA_TYPE_MZHIS = "MZHIS";
    public static String DATA_TYPE_CARD = "CARD";
    public static String DATA_TYPE_YZT = "YZT";
    public static String PLATFORM_DATA_TYPE_HIS = "HIS";
    public static String PLATFORM_DATA_TYPE_HOSACC = "HOSACC";
    public static String PLATFORM_DATA_TYPE_BANK = "BANK";
    public static String PRINT_TYPE_REPORT = "1";
    public static String PRINT_TYPE_RECEIPT = "2";
    public static String PRINT_STATE_FAIL;
    public static String PRINT_STATE_SUCCESS;
    public static String RECHARGE_PARAM_ERROR = "0";
    public static String RECHARGE_GETAREAINFO_ERROR = "0";
    public static String RECHARGE_SUCCESS = "1";
    public static String RECHARGE_FAIL = "0";
    public static String RECHARGE_OVERTIME = "0";
    public static String RECHARGE_ORDERFAIL = "0";
    public static String ALIPAY_URL_SUCCESS = "1";
    public static String ALIPAY_URL_FAIL = "0";
    public static String ALIPAY_SUCCESS = "1";
    public static String ALIPAY_FAIL = "0";
    public static String ALIPAY_PAY_ING = "2";
    public static String ORDERCCONFIM_STATE_SUCC = "1";
    public static String ORDERCCONFIM_STATE_FAIL = "0";
    private static HashMap<String, String> zzmsg_Container = new HashMap();

    static {
        zzmsg_Container.put(RECHARGE_PARAM_ERROR, "查询参数出错");
        zzmsg_Container.put(RECHARGE_SUCCESS, "操作成功");
        zzmsg_Container.put(RECHARGE_FAIL, "充值失败");
        zzmsg_Container.put(RECHARGE_GETAREAINFO_ERROR, "获取HIS病区信息错误");
        zzmsg_Container.put(ALIPAY_URL_SUCCESS, "获取二维码成功");
        zzmsg_Container.put(ALIPAY_URL_FAIL, "获取二维码失败");
        zzmsg_Container.put(ALIPAY_SUCCESS, "支付宝支付成功");
        zzmsg_Container.put(ALIPAY_FAIL, "支付宝支付失败");
        zzmsg_Container.put(ALIPAY_PAY_ING, "支付宝支付中");
        zzmsg_Container.put(RECHARGE_OVERTIME, "该时间段不能充值");
        zzmsg_Container.put(ORDERCCONFIM_STATE_SUCC, "下单成功");
        zzmsg_Container.put(ORDERCCONFIM_STATE_FAIL, "下单失败");
        zzmsg_Container.put(RECHARGE_ORDERFAIL, "记录订单失败");
    }

    public ZzConstant() {
    }

    public static String getRspMsg(String rspCode) {
        return (String)zzmsg_Container.get(rspCode);
    }
}
