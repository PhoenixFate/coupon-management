package com.phoenix.common.utils;

import java.util.Map;

import com.phoenix.core.utils.DateUtil;

public class QueryParams {
	
	public static void initQueryTime(Map<String, Object> map) {
		if (null == map.get("endTime") || "".equals(map.get("endTime"))) {
			try {
				map.put("endTime", DateUtil.dateEndStr());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else {
			map.put("endTime", map.get("endTime").toString() + " 23:59:59");
		}
		if (null == map.get("startTime") || "".equals(map.get("startTime"))) {
			try {
				map.put("startTime",  DateUtil.dateStartStr());
			} catch (Exception e) {
				e.printStackTrace();
			}		
		}else {
			map.put("startTime", map.get("startTime").toString() + " 00:00:00");
		}
	}
	
	
	public static void initQueryDate(Map<String, Object> map) {
		if (null == map.get("endTime") || "".equals(map.get("endTime"))) {
			try {
				map.put("endTime", DateUtil.getDateFormatter(DateUtil.DATE1_PATTERN));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			map.put("endTime", map.get("endTime").toString());
		}
		if (null == map.get("startTime") || "".equals(map.get("startTime"))) {
			try {
				map.put("startTime", DateUtil.getDateFormatter(DateUtil.DATE1_PATTERN));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
		}else {
			map.put("startTime", map.get("startTime").toString());
		}
	}
	

}
