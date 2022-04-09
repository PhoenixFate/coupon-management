package com.phoenix.coupon.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 风控报警记录
 * 
 * @author phoenix
 * @email sm516116978@outlook.com
 * @date 2019-07-24 12:41:16
 */
public class AlarmLogsDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private String id;
	//警报规则代码
	private String alarmCode;
	//警报规则名称
	private String alarmName;
	//报警内容
	private String alarmDesc;
	//报警时间
	private Date alarmTime;
	//人工处理标志,1已处理， 0 未处理
	private String confirmStatus;
	//确认时间
	private Date confirmTime;
	//确认人
	private String confirmUser;
	//报警类型
	private String alarmType;
	//报警号码，逗号分隔
	private String alarmPhone;
	//机构代码
	private String orgCode;

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
	 * 设置：报警内容
	 */
	public void setAlarmDesc(String alarmDesc) {
		this.alarmDesc = alarmDesc;
	}
	/**
	 * 获取：报警内容
	 */
	public String getAlarmDesc() {
		return alarmDesc;
	}
	/**
	 * 设置：报警时间
	 */
	public void setAlarmTime(Date alarmTime) {
		this.alarmTime = alarmTime;
	}
	/**
	 * 获取：报警时间
	 */
	public Date getAlarmTime() {
		return alarmTime;
	}
	/**
	 * 设置：人工处理标志,1已处理， 0 未处理
	 */
	public void setConfirmStatus(String confirmStatus) {
		this.confirmStatus = confirmStatus;
	}
	/**
	 * 获取：人工处理标志,1已处理， 0 未处理
	 */
	public String getConfirmStatus() {
		return confirmStatus;
	}
	/**
	 * 设置：确认时间
	 */
	public void setConfirmTime(Date confirmTime) {
		this.confirmTime = confirmTime;
	}
	/**
	 * 获取：确认时间
	 */
	public Date getConfirmTime() {
		return confirmTime;
	}
	/**
	 * 设置：确认人
	 */
	public void setConfirmUser(String confirmUser) {
		this.confirmUser = confirmUser;
	}
	/**
	 * 获取：确认人
	 */
	public String getConfirmUser() {
		return confirmUser;
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
}
