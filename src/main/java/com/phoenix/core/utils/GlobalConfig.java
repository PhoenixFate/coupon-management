package com.phoenix.core.utils;

import org.springframework.core.env.Environment;

public class GlobalConfig {
    public static Environment envm;

    public GlobalConfig(Environment envm) {
        GlobalConfig.envm = envm;
    }

    public static String getConfig(String key, String defaultValue) {
        String value = envm.getProperty(key);
        return value == null ? defaultValue : value;
    }

    public static Integer getConfigInt(String key, Integer defaultValue) {
        String value = getConfig(key, "");
        return "".equals(value) ? defaultValue : Integer.parseInt(value);
    }
}