package com.phoenix.core.utils;


import com.alibaba.fastjson.JSON;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;

public class SignValidUtils {
    public SignValidUtils() {
    }

    public static String generateSignStr(String paramJson) {
        if (StringUtils.isEmpty(paramJson)) {
            return "签名入参为空";
        } else {
            try {
                Map<String, Object> paramMap = JSON.parseObject(paramJson);
                paramMap.remove("sign");
                String paramStr = createLinkString(paramMap);
                return paramStr;
            } catch (Exception var3) {
                return var3.getMessage();
            }
        }
    }

    public static String createLinkString(Map<String, Object> params) {
        List<String> keys = new ArrayList(params.keySet());
        Collections.sort(keys);
        String prestr = "";

        for(int i = 0; i < keys.size(); ++i) {
            String key = (String)keys.get(i);
            String value = params.get(key).toString();
            if (i == keys.size() - 1) {
                prestr = prestr + key + "=" + value;
            } else {
                prestr = prestr + key + "=" + value + "&";
            }
        }

        return prestr;
    }

    public static void main(String[] args) {
        System.out.println();
    }
}

