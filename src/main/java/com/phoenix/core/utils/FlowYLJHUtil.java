package com.phoenix.core.utils;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import java.util.Random;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;

public class FlowYLJHUtil {
    public FlowYLJHUtil() {
    }

    public static synchronized String getYLJHRcptStreamNoIndex(String payconfigkey) {
        if (payconfigkey != null && StringUtils.isNotEmpty(payconfigkey)) {
            JSONObject payConfig = JSON.parseObject(payconfigkey);
            if (payConfig != null && StringUtils.isNotEmpty(payConfig.getString("msgSrcId"))) {
                String msgSrcId = payConfig.getString("msgSrcId");
                if (msgSrcId.length() > 4) {
                    msgSrcId = msgSrcId.substring(0, 4);
                } else if (msgSrcId.length() < 4) {
                    for(int i = msgSrcId.length(); i < 4; ++i) {
                        msgSrcId = msgSrcId + "0";
                    }
                }

                return msgSrcId + DateFormatUtils.format(System.currentTimeMillis(), "yyyyMMddHHmmssSSS") + getFlow(7);
            }
        }

        return null;
    }

    public static String getFlow(int length) {
        int count = 0;
        char[] str = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
        StringBuffer flow = new StringBuffer("");
        Random r = new Random();

        while(count < length) {
            int i = Math.abs(r.nextInt(9));
            if (i >= 0 && i < str.length) {
                flow.append(str[i]);
                ++count;
            }
        }

        return flow.toString();
    }
}
