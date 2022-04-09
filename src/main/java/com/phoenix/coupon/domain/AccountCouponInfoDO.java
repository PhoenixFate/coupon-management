package com.phoenix.coupon.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 账户优惠券信息
 * 
 * @author phoenix
 * @email sm516116978@outlook.com
 * @date 2019-07-11 10:44:00
 */
public class AccountCouponInfoDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//发券流水号
	private String provideId;
	//账户id
	private String accountId;
	//账户号
	private String accountNo;
	//充值渠道码
	private String channelCode;
	//发券时间
	private Date provideTime;
	//优惠券号码
	private String couponNo;
	//第三方订单号，商户订单号
	private String thirdOrdernumber;
	//充值描述
	private String remark;
	//撤销标记， 0 未撤销， 1 已撤销
	private String returnFlag;
	//撤销时间
	private Date returnTime;
	//乐观锁
	private String version;
	//账户姓名
	private String username;
	//优惠券支持的业务代码
	private String bizCode;
	//优惠券基础信息ID
	private String couponId;
	//发券业务流水号，用于去重
	private String bizFlow;
	//优惠券对应的产品编号
	private String productCode;
	//优惠券状态， 0 未使用， 1 已使用， 2 已冻结，3 已过期
	private String couponStatus;
	//消费时间
	private Date consumeTime;
	//有效期开始时间
	private Date periodStartTime;
	//有效期截止时间
	private Date periodEndTime;
	
	//用户服务包关系表的主键
	private String packageRelationId;

	//业绩所属医生工号
	private String belogtoDoctor;
	
	private String belogtoOrgcode;
	
	private String packageId;
	
	private String packageName;
	
	// 卡券名称
	private String couponName;

	/**
	 * 设置：发券流水号
	 */
	public void setProvideId(String provideId) {
		this.provideId = provideId;
	}
	/**
	 * 获取：发券流水号
	 */
	public String getProvideId() {
		return provideId;
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
	 * 设置：充值渠道码
	 */
	public void setChannelCode(String channelCode) {
		this.channelCode = channelCode;
	}
	/**
	 * 获取：充值渠道码
	 */
	public String getChannelCode() {
		return channelCode;
	}
	/**
	 * 设置：发券时间
	 */
	public void setProvideTime(Date provideTime) {
		this.provideTime = provideTime;
	}
	/**
	 * 获取：发券时间
	 */
	public Date getProvideTime() {
		return provideTime;
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
	 * 设置：第三方订单号，商户订单号
	 */
	public void setThirdOrdernumber(String thirdOrdernumber) {
		this.thirdOrdernumber = thirdOrdernumber;
	}
	/**
	 * 获取：第三方订单号，商户订单号
	 */
	public String getThirdOrdernumber() {
		return thirdOrdernumber;
	}
	/**
	 * 设置：充值描述
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	/**
	 * 获取：充值描述
	 */
	public String getRemark() {
		return remark;
	}
	/**
	 * 设置：撤销标记， 0 未撤销， 1 已撤销
	 */
	public void setReturnFlag(String returnFlag) {
		this.returnFlag = returnFlag;
	}
	/**
	 * 获取：撤销标记， 0 未撤销， 1 已撤销
	 */
	public String getReturnFlag() {
		return returnFlag;
	}
	/**
	 * 设置：撤销时间
	 */
	public void setReturnTime(Date returnTime) {
		this.returnTime = returnTime;
	}
	/**
	 * 获取：撤销时间
	 */
	public Date getReturnTime() {
		return returnTime;
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
	 * 设置：优惠券支持的业务代码
	 */
	public void setBizCode(String bizCode) {
		this.bizCode = bizCode;
	}
	/**
	 * 获取：优惠券支持的业务代码
	 */
	public String getBizCode() {
		return bizCode;
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
	 * 设置：发券业务流水号，用于去重
	 */
	public void setBizFlow(String bizFlow) {
		this.bizFlow = bizFlow;
	}
	/**
	 * 获取：发券业务流水号，用于去重
	 */
	public String getBizFlow() {
		return bizFlow;
	}
	/**
	 * 设置：优惠券对应的产品编号
	 */
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	/**
	 * 获取：优惠券对应的产品编号
	 */
	public String getProductCode() {
		return productCode;
	}
	/**
	 * 设置：优惠券状态， 0 未使用， 1 已使用， 2 已冻结，3 已过期
	 */
	public void setCouponStatus(String couponStatus) {
		this.couponStatus = couponStatus;
	}
	/**
	 * 获取：优惠券状态， 0 未使用， 1 已使用， 2 已冻结，3 已过期
	 */
	public String getCouponStatus() {
		return couponStatus;
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
	 * 设置：有效期开始时间
	 */
	public void setPeriodStartTime(Date periodStartTime) {
		this.periodStartTime = periodStartTime;
	}
	/**
	 * 获取：有效期开始时间
	 */
	public Date getPeriodStartTime() {
		return periodStartTime;
	}
	/**
	 * 设置：有效期截止时间
	 */
	public void setPeriodEndTime(Date periodEndTime) {
		this.periodEndTime = periodEndTime;
	}
	/**
	 * 获取：有效期截止时间
	 */
	public Date getPeriodEndTime() {
		return periodEndTime;
	}
	public String getPackageRelationId() {
		return packageRelationId;
	}
	public void setPackageRelationId(String packageRelationId) {
		this.packageRelationId = packageRelationId;
	}
	public String getBelogtoDoctor() {
		return belogtoDoctor;
	}
	public void setBelogtoDoctor(String belogtoDoctor) {
		this.belogtoDoctor = belogtoDoctor;
	}
	public String getBelogtoOrgcode() {
		return belogtoOrgcode;
	}
	public void setBelogtoOrgcode(String belogtoOrgcode) {
		this.belogtoOrgcode = belogtoOrgcode;
	}
	public String getPackageId() {
		return packageId;
	}
	public void setPackageId(String packageId) {
		this.packageId = packageId;
	}
	public String getPackageName() {
		return packageName;
	}
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	public String getCouponName() {
		return couponName;
	}
	public void setCouponName(String couponName) {
		this.couponName = couponName;
	}
	
	
}
