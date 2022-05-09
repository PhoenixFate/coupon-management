package com.phoenix.common.utils;

import com.phoenix.core.redis.JedisUtil;

/**
 * 
	 * 类描述 基于shiro用户的全局数据处理
 	 * @version  1.0
	 * @author  phoenix
	 * @version  2018年4月13日 下午3:11:49
 */
public class GlobalParamUtil {

	private static int expire_time = 24 * 60 * 60;
	/**
	 * 
	  * 方法描述：设置基于登录用户的全局变量
	  * @param 
	  * @return 
	  * @version 1.0
	  * @author phoenix
	  * 2018年4月13日 下午3:10:24
	 */
	public static boolean setParam(String key, String value)
	{
		try
		{
			String redisKey = getKey(key);
			JedisUtil.set(redisKey, value, expire_time);
		}catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	
	/**
	 * 
	  * 方法描述：获取基于用户的全局变量
	  * @param 
	  * @return 
	  * @version 1.0
	  * @author phoenix
	  * 2018年4月13日 下午3:11:22
	 */
	public static String getParam(String key)
	{
		try
		{
			String redisKey = getKey(key);
			return JedisUtil.get(redisKey);
		}catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	private static String getKey(String key)
	{
		try
		{
			String sessionId = ShiroUtils.getSubject().getSession().getId().toString();
			return "GLOBALPARAM:" + sessionId + ":" + key;
		}catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
}
