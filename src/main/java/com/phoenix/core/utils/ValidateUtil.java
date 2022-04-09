package com.phoenix.core.utils;

import com.phoenix.core.exception.ServerException;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

public class ValidateUtil {
    public ValidateUtil() {
    }

    public static boolean validate(Object paramObject) {
        return paramObject != null;
    }

    public static boolean validate(String paramString) {
        return paramString != null && paramString != "" && !paramString.equalsIgnoreCase("null") && paramString.trim().length() >= 1;
    }

    public static boolean validate(List<?> paramList) {
        return paramList != null && paramList.size() != 0;
    }

    public static <T> void notNull(T t) throws ServerException {
        if (t == null) {
            throw new ServerException("9199");
        }
    }

    public static <T> void notNull(T t, String errCode) throws ServerException {
        if (t == null) {
            throw new ServerException(errCode);
        }
    }

    public static <T> void notNull(T t, String errCode, String errMsg) throws ServerException {
        if (t == null) {
            throw new ServerException(errCode, errMsg);
        }
    }

    public static void notEmpty(String value) throws ServerException {
        if ("".equals(StringUtils.trimToEmpty(value))) {
            throw new ServerException("9199");
        }
    }

    public static void notEmpty(String value, String errCode, String errMsg) throws ServerException {
        if (value == null || "".equals(StringUtils.trimToEmpty(value))) {
            throw new ServerException(errCode, errMsg);
        }
    }

    public static void notEmpty(String value, String errCode) throws ServerException {
        if ("".equals(StringUtils.trimToEmpty(value))) {
            throw new ServerException(errCode);
        }
    }
}
