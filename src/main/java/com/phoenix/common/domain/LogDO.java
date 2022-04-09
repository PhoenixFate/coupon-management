package com.phoenix.common.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 系统日志
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-03-20 17:01:32
 */
public class LogDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//日志Id
	private String logId;
	//用户id
	private String userId;
	//用户名
	private String userName;
	//用户操作
	private String operation;
	//响应时间
	private Integer time;
	//请求方法
	private String method;
	//请求参数
	private String params;
	//IP地址
	private String ip;
	//创建时间
	private Date createGmt;

	/**
	 * 设置：日志Id
	 */
	public void setLogId(String logId) {
		this.logId = logId;
	}
	/**
	 * 获取：日志Id
	 */
	public String getLogId() {
		return logId;
	}
	/**
	 * 设置：用户id
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
	/**
	 * 获取：用户id
	 */
	public String getUserId() {
		return userId;
	}
	/**
	 * 设置：用户名
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * 获取：用户名
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * 设置：用户操作
	 */
	public void setOperation(String operation) {
		this.operation = operation;
	}
	/**
	 * 获取：用户操作
	 */
	public String getOperation() {
		return operation;
	}
	/**
	 * 设置：响应时间
	 */
	public void setTime(Integer time) {
		this.time = time;
	}
	/**
	 * 获取：响应时间
	 */
	public Integer getTime() {
		return time;
	}
	/**
	 * 设置：请求方法
	 */
	public void setMethod(String method) {
		this.method = method;
	}
	/**
	 * 获取：请求方法
	 */
	public String getMethod() {
		return method;
	}
	/**
	 * 设置：请求参数
	 */
	public void setParams(String params) {
		this.params = params;
	}
	/**
	 * 获取：请求参数
	 */
	public String getParams() {
		return params;
	}
	/**
	 * 设置：IP地址
	 */
	public void setIp(String ip) {
		this.ip = ip;
	}
	/**
	 * 获取：IP地址
	 */
	public String getIp() {
		return ip;
	}
	/**
	 * 设置：创建时间
	 */
	public void setCreateGmt(Date createGmt) {
		this.createGmt = createGmt;
	}
	/**
	 * 获取：创建时间
	 */
	public Date getCreateGmt() {
		return createGmt;
	}
	
	@Override
	public String toString() {
		return "LogDO{" +
				"logId=" + logId +
				", userId=" + userId +
				", userName='" + userName + '\'' +
				", operation='" + operation + '\'' +
				", time=" + time +
				", method='" + method + '\'' +
				", params='" + params + '\'' +
				", ip='" + ip + '\'' +
				", createGmt=" + createGmt +
				'}';
	}
}
