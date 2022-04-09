package com.phoenix.coupon.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 账户优惠券消费记录
 * 
 * @author phoenix
 * @email sm516116978@outlook.com
 * @date 2019-07-22 09:24:02
 */
public class AccountConsumeLogDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//消费流水号
	private String consumeId;
	//账户id
	private String accountId;
	//账户号
	private String accountNo;
	//消费渠道码
	private String channelCode;
	//消费时间
	private Date consumeTime;
	//第三方订单号
	private String thirdOrdernumber;
	//描述
	private String remark;
	//优惠券号码
	private String couponNo;
	//撤销标记， 0 未退， 1 已退
	private String refundFlag;
	//撤销时间
	private Date refundTime;
	//消费业务类型
	private String bizCode;
	//消费业务类型名称
	private String bizName;
	//消费状态， 1 核销成功， 0 撤销
	private String consumeStatus;
	//医院代码
	private String orgCode;
	//乐观锁
	private String version;
	//账户姓名
	private String username;
	//核销操作员号
	private String consumeCollector;
	//撤销操作员号
	private String refundCollector;
	//优惠券基础信息ID
	private String couponId;
	//优惠券对应的产品编码
	private String productCode;
	//撤销渠道
	private String refundChannelCode;
	//业绩所属医生工号
	private String belogtoDoctor;
	//doctor_info表主键
	private String consumeDoctorId;
	//提现标记， 1 已提现， 0 未提现
	private String cashOutFlag;
	//提现时间
	private Date cashOutTime;
	//提现记录表的主键
	private String cashOutFlow;
	//格式化后的消费时间
	private String formatConsumeTime;
	//格式化后的撤销时间
	private String formatRefundTime;
	//消费开始时间
	private String startTime;
	//消费结束时间
	private String endTime;
	
	// 卡券名称
	private String couponName;

	/**
	 * 设置：消费流水号
	 */
	public void setConsumeId(String consumeId) {
		this.consumeId = consumeId;
	}
	/**
	 * 获取：消费流水号
	 */
	public String getConsumeId() {
		return consumeId;
	}
	/**
	 * 设置：账户id
	 */
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	/**
	 * 获取：账户id
	 */
	public String getAccountId() {
		return accountId;
	}
	/**
	 * 设置：账户号
	 */
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	/**
	 * 获取：账户号
	 */
	public String getAccountNo() {
		return accountNo;
	}
	/**
	 * 设置：消费渠道码
	 */
	public void setChannelCode(String channelCode) {
		this.channelCode = channelCode;
	}
	/**
	 * 获取：消费渠道码
	 */
	public String getChannelCode() {
		return channelCode;
	}
	/**
	 * 设置：消费时间
	 */
	public void setConsumeTime(Date consumeTime) {
		this.consumeTime = consumeTime;
	}
	/**
	 * 获取：消费时间
	 */
	public Date getConsumeTime() {
		return consumeTime;
	}
	/**
	 * 设置：第三方订单号
	 */
	public void setThirdOrdernumber(String thirdOrdernumber) {
		this.thirdOrdernumber = thirdOrdernumber;
	}
	/**
	 * 获取：第三方订单号
	 */
	public String getThirdOrdernumber() {
		return thirdOrdernumber;
	}
	/**
	 * 设置：描述
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	/**
	 * 获取：描述
	 */
	public String getRemark() {
		return remark;
	}
	/**
	 * 设置：优惠券号码
	 */
	public void setCouponNo(String couponNo) {
		this.couponNo = couponNo;
	}
	/**
	 * 获取：优惠券号码
	 */
	public String getCouponNo() {
		return couponNo;
	}
	/**
	 * 设置：撤销标记， 0 未退， 1 已退
	 */
	public void setRefundFlag(String refundFlag) {
		this.refundFlag = refundFlag;
	}
	/**
	 * 获取：撤销标记， 0 未退， 1 已退
	 */
	public String getRefundFlag() {
		return refundFlag;
	}
	/**
	 * 设置：撤销时间
	 */
	public void setRefundTime(Date refundTime) {
		this.refundTime = refundTime;
	}
	/**
	 * 获取：撤销时间
	 */
	public Date getRefundTime() {
		return refundTime;
	}
	/**
	 * 设置：消费业务类型
	 */
	public void setBizCode(String bizCode) {
		this.bizCode = bizCode;
	}
	/**
	 * 获取：消费业务类型
	 */
	public String getBizCode() {
		return bizCode;
	}
	/**
	 * 设置：消费业务类型名称
	 */
	public void setBizName(String bizName) {
		this.bizName = bizName;
	}
	/**
	 * 获取：消费业务类型名称
	 */
	public String getBizName() {
		return bizName;
	}
	/**
	 * 设置：消费状态， 1 核销成功， 0 撤销
	 */
	public void setConsumeStatus(String consumeStatus) {
		this.consumeStatus = consumeStatus;
	}
	/**
	 * 获取：消费状态， 1 核销成功， 0 撤销
	 */
	public String getConsumeStatus() {
		return consumeStatus;
	}
	/**
	 * 设置：医院代码
	 */
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
	/**
	 * 获取：医院代码
	 */
	public String getOrgCode() {
		return orgCode;
	}
	/**
	 * 设置：乐观锁
	 */
	public void setVersion(String version) {
		this.version = version;
	}
	/**
	 * 获取：乐观锁
	 */
	public String getVersion() {
		return version;
	}
	/**
	 * 设置：账户姓名
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * 获取：账户姓名
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * 设置：核销操作员号
	 */
	public void setConsumeCollector(String consumeCollector) {
		this.consumeCollector = consumeCollector;
	}
	/**
	 * 获取：核销操作员号
	 */
	public String getConsumeCollector() {
		return consumeCollector;
	}
	/**
	 * 设置：撤销操作员号
	 */
	public void setRefundCollector(String refundCollector) {
		this.refundCollector = refundCollector;
	}
	/**
	 * 获取：撤销操作员号
	 */
	public String getRefundCollector() {
		return refundCollector;
	}
	/**
	 * 设置：优惠券基础信息ID
	 */
	public void setCouponId(String couponId) {
		this.couponId = couponId;
	}
	/**
	 * 获取：优惠券基础信息ID
	 */
	public String getCouponId() {
		return couponId;
	}
	/**
	 * 设置：优惠券对应的产品编码
	 */
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	/**
	 * 获取：优惠券对应的产品编码
	 */
	public String getProductCode() {
		return productCode;
	}
	/**
	 * 设置：撤销渠道
	 */
	public void setRefundChannelCode(String refundChannelCode) {
		this.refundChannelCode = refundChannelCode;
	}
	/**
	 * 获取：撤销渠道
	 */
	public String getRefundChannelCode() {
		return refundChannelCode;
	}
	/**
	 * 设置：业绩所属医生工号
	 */
	public void setBelogtoDoctor(String belogtoDoctor) {
		this.belogtoDoctor = belogtoDoctor;
	}
	/**
	 * 获取：业绩所属医生工号
	 */
	public String getBelogtoDoctor() {
		return belogtoDoctor;
	}
	/**
	 * 设置：doctor_info表主键
	 */
	public void setConsumeDoctorId(String consumeDoctorId) {
		this.consumeDoctorId = consumeDoctorId;
	}
	/**
	 * 获取：doctor_info表主键
	 */
	public String getConsumeDoctorId() {
		return consumeDoctorId;
	}
	/**
	 * 设置：提现标记， 1 已提现， 0 未提现
	 */
	public void setCashOutFlag(String cashOutFlag) {
		this.cashOutFlag = cashOutFlag;
	}
	/**
	 * 获取：提现标记， 1 已提现， 0 未提现
	 */
	public String getCashOutFlag() {
		return cashOutFlag;
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
	 * 设置：提现记录表的主键
	 */
	public void setCashOutFlow(String cashOutFlow) {
		this.cashOutFlow = cashOutFlow;
	}
	/**
	 * 获取：提现记录表的主键
	 */
	public String getCashOutFlow() {
		return cashOutFlow;
	}
	
	public String getFormatConsumeTime() {
		return formatConsumeTime;
	}
	public void setFormatConsumeTime(String formatConsumeTime) {
		this.formatConsumeTime = formatConsumeTime;
	}
	public String getFormatRefundTime() {
		return formatRefundTime;
	}
	public void setFormatRefundTime(String formatRefundTime) {
		this.formatRefundTime = formatRefundTime;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getCouponName() {
		return couponName;
	}
	public void setCouponName(String couponName) {
		this.couponName = couponName;
	}
	
	
}
