package com.phoenix.coupon.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 医生优惠券核销提现
 * 
 * @author phoenix
 * @email sm516116978@outlook.com
 * @date 2019-07-22 10:53:10
 */
public class CouponCashOutDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//主键
	private String id;
	//提现时间
	private Date cashOutTime;
	//本次提现数量
	private Integer cashOutNums;
	//提现医生ID
	private String doctorId;
	//医生工号
	private String doctorCode;
	//医生姓名
	private String doctorName;
	//医生编码
	private String orgCode;
	//后台提现操作员用户名
	private String cashOutOperator;

	/**
	 * 设置：主键
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * 获取：主键
	 */
	public String getId() {
		return id;
	}
	/**
	 * 设置：提现时间
	 */
	public void setCashOutTime(Date cashOutTime) {
		this.cashOutTime = cashOutTime;
	}
	/**
	 * 获取：提现时间
	 */
	public Date getCashOutTime() {
		return cashOutTime;
	}
	/**
	 * 设置：本次提现数量
	 */
	public void setCashOutNums(Integer cashOutNums) {
		this.cashOutNums = cashOutNums;
	}
	/**
	 * 获取：本次提现数量
	 */
	public Integer getCashOutNums() {
		return cashOutNums;
	}
	/**
	 * 设置：提现医生ID
	 */
	public void setDoctorId(String doctorId) {
		this.doctorId = doctorId;
	}
	/**
	 * 获取：提现医生ID
	 */
	public String getDoctorId() {
		return doctorId;
	}
	/**
	 * 设置：医生工号
	 */
	public void setDoctorCode(String doctorCode) {
		this.doctorCode = doctorCode;
	}
	/**
	 * 获取：医生工号
	 */
	public String getDoctorCode() {
		return doctorCode;
	}
	/**
	 * 设置：医生姓名
	 */
	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}
	/**
	 * 获取：医生姓名
	 */
	public String getDoctorName() {
		return doctorName;
	}
	/**
	 * 设置：医生编码
	 */
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
	/**
	 * 获取：医生编码
	 */
	public String getOrgCode() {
		return orgCode;
	}
	/**
	 * 设置：后台提现操作员用户名
	 */
	public void setCashOutOperator(String cashOutOperator) {
		this.cashOutOperator = cashOutOperator;
	}
	/**
	 * 获取：后台提现操作员用户名
	 */
	public String getCashOutOperator() {
		return cashOutOperator;
	}
}
