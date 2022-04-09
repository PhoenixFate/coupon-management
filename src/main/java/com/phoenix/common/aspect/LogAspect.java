package com.phoenix.common.aspect;

import java.lang.reflect.Method;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.phoenix.common.annotation.Log;
import com.phoenix.common.dao.LogDao;
import com.phoenix.common.domain.LogDO;
import com.phoenix.common.domain.UserDO;
import com.phoenix.common.utils.HttpContextUtils;
import com.phoenix.common.utils.IPUtils;
import com.phoenix.common.utils.JSONUtils;
import com.phoenix.common.utils.ShiroUtils;
import com.phoenix.core.utils.UUIDUtil;

@Aspect
@Component
public class LogAspect {
	@Autowired
	private LogDao logDao;

	@Pointcut("@annotation(com.phoenix.common.annotation.Log)")
	public void logPointCut() {
	}

	@Around("logPointCut()")
	public Object around(ProceedingJoinPoint point) throws Throwable {
		long beginTime = System.currentTimeMillis();
		// 执行方法
		Object result = point.proceed();
		// 执行时长(毫秒)
		long time = System.currentTimeMillis() - beginTime;
		//异步保存日志
		saveLog(point, time);
		
		return result;
	}

	private void saveLog(ProceedingJoinPoint joinPoint, long time) {
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		Method method = signature.getMethod();
		LogDO sysLog = new LogDO();
		Log syslog = method.getAnnotation(Log.class);
		if (syslog != null) {
			// 注解上的描述
			sysLog.setOperation(syslog.value());
		}
		sysLog.setLogId(UUIDUtil.getUUID());
		// 请求的方法名
		String className = joinPoint.getTarget().getClass().getName();
		String methodName = signature.getName();
		sysLog.setMethod(className + "." + methodName + "()");
		// 请求的参数
		Object[] args = joinPoint.getArgs();
		try {
			String params = JSONUtils.beanToJson(args[0]).substring(0, 4999);
			sysLog.setParams(params);
		} catch (Exception e) {

		}
		// 获取request
		HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
		// 设置IP地址
		sysLog.setIp(IPUtils.getIpAddr(request));
		// 用户名
		UserDO currUser = ShiroUtils.getUser();
		if (null == currUser) {
			if (null != sysLog.getParams()) {
				sysLog.setUserId("-1");
				sysLog.setUserName(sysLog.getParams());
			} else {
				sysLog.setUserId("-1");
				sysLog.setUserName("获取用户信息为空");
			}
		} else {
			sysLog.setUserId(ShiroUtils.getUserId());
			sysLog.setUserName(ShiroUtils.getUser().getUserName());
		}
		sysLog.setTime((int) time);
		// 系统当前时间
		Date date = new Date();
		sysLog.setCreateGmt(date);
		// 保存系统日志
		logDao.save(sysLog);
	}
}
