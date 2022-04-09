package com.phoenix.paycenter.domain;

import java.io.Serializable;


/**
 * 警报规则表
 * 
 * @author phoenix
 * @email sm516116978@outlook.com
 * @date 2019-04-17 09:40:12
 */
public class AlarmRulesDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private String id;
	//警报规则代码
	private String alarmCode;
	//警报规则名称
	private String alarmName;
	//时间区间(00:00-18:00)
	private String timeRegion;
	//时间间隔，分钟
	private Integer timeSpace;
	//表达式(><=)
	private String expression;
	//阈值
	private Integer threshold;
	//报警类型
	private String alarmType;
	//报警号码，逗号分隔
	private String alarmPhone;
	//机构代码
	private String orgCode;
	
	//周几
	private String days;

	/**
	 * 设置：
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * 获取：
	 */
	public String getId() {
		return id;
	}
	/**
	 * 设置：警报规则代码
	 */
	public void setAlarmCode(String alarmCode) {
		this.alarmCode = alarmCode;
	}
	/**
	 * 获取：警报规则代码
	 */
	public String getAlarmCode() {
		return alarmCode;
	}
	/**
	 * 设置：警报规则名称
	 */
	public void setAlarmName(String alarmName) {
		this.alarmName = alarmName;
	}
	/**
	 * 获取：警报规则名称
	 */
	public String getAlarmName() {
		return alarmName;
	}
	/**
	 * 设置：时间区间(00:00-18:00)
	 */
	public void setTimeRegion(String timeRegion) {
		this.timeRegion = timeRegion;
	}
	/**
	 * 获取：时间区间(00:00-18:00)
	 */
	public String getTimeRegion() {
		return timeRegion;
	}
	/**
	 * 设置：时间间隔，分钟
	 */
	public void setTimeSpace(Integer timeSpace) {
		this.timeSpace = timeSpace;
	}
	/**
	 * 获取：时间间隔，分钟
	 */
	public Integer getTimeSpace() {
		return timeSpace;
	}
	/**
	 * 设置：表达式(><=)
	 */
	public void setExpression(String expression) {
		this.expression = expression;
	}
	/**
	 * 获取：表达式(><=)
	 */
	public String getExpression() {
		return expression;
	}
	/**
	 * 设置：阈值
	 */
	public void setThreshold(Integer threshold) {
		this.threshold = threshold;
	}
	/**
	 * 获取：阈值
	 */
	public Integer getThreshold() {
		return threshold;
	}
	/**
	 * 设置：报警类型
	 */
	public void setAlarmType(String alarmType) {
		this.alarmType = alarmType;
	}
	/**
	 * 获取：报警类型
	 */
	public String getAlarmType() {
		return alarmType;
	}
	/**
	 * 设置：报警号码，逗号分隔
	 */
	public void setAlarmPhone(String alarmPhone) {
		this.alarmPhone = alarmPhone;
	}
	/**
	 * 获取：报警号码，逗号分隔
	 */
	public String getAlarmPhone() {
		return alarmPhone;
	}
	/**
	 * 设置：机构代码
	 */
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
	/**
	 * 获取：机构代码
	 */
	public String getOrgCode() {
		return orgCode;
	}
	public String getDays() {
		return days;
	}
	public void setDays(String days) {
		this.days = days;
	}
}
