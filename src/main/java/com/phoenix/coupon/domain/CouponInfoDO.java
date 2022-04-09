package com.phoenix.coupon.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 优惠券基础信息
 * 
 * @author phoenix
 * @email sm516116978@outlook.com
 * @date 2019-07-11 10:44:01
 */
public class CouponInfoDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//发券流水号
	private String couponId;
	//优惠券名称
	private String couponName;
	//优惠券详情介绍
	private String couponDetail;
	//优惠券总数
	private Integer totalNums;
	//剩余总数
	private Integer remainNums;
	//支持优惠券的医院编码
	private String useOrgcodes;
	//每人限领几张
	private Integer userLimits;
	//乐观锁
	private String version;
	//优惠券支持的业务代码
	private String bizCode;
	//优惠券状态， 0 未启用， 1 启用， 2 停用
	private String couponStatus;
	//有效期开始时间
	private String periodStartTime;
	//有效期截止时间
	private String periodEndTime;
	//有效期类型，1 按照固定时间段， 2 按照发券日期
	private String periodValidType;
	//按照发券日期计算有效期情况下有效期天数
	private Integer periodValidDays;
	//有效期起始时间类型， 1 立即生效， 2 次日生效， 3 次周生效， 4 次月生效
	private String periodStartType;
	//创建时间
	private Date createTime;
	//更新时间
	private Date updateTime;

	/**
	 * 设置：发券流水号
	 */
	public void setCouponId(String couponId) {
		this.couponId = couponId;
	}
	/**
	 * 获取：发券流水号
	 */
	public String getCouponId() {
		return couponId;
	}
	/**
	 * 设置：优惠券名称
	 */
	public void setCouponName(String couponName) {
		this.couponName = couponName;
	}
	/**
	 * 获取：优惠券名称
	 */
	public String getCouponName() {
		return couponName;
	}
	/**
	 * 设置：优惠券详情介绍
	 */
	public void setCouponDetail(String couponDetail) {
		this.couponDetail = couponDetail;
	}
	/**
	 * 获取：优惠券详情介绍
	 */
	public String getCouponDetail() {
		return couponDetail;
	}
	/**
	 * 设置：优惠券总数
	 */
	public void setTotalNums(Integer totalNums) {
		this.totalNums = totalNums;
	}
	/**
	 * 获取：优惠券总数
	 */
	public Integer getTotalNums() {
		return totalNums;
	}
	/**
	 * 设置：剩余总数
	 */
	public void setRemainNums(Integer remainNums) {
		this.remainNums = remainNums;
	}
	/**
	 * 获取：剩余总数
	 */
	public Integer getRemainNums() {
		return remainNums;
	}
	/**
	 * 设置：支持优惠券的医院编码
	 */
	public void setUseOrgcodes(String useOrgcodes) {
		this.useOrgcodes = useOrgcodes;
	}
	/**
	 * 获取：支持优惠券的医院编码
	 */
	public String getUseOrgcodes() {
		return useOrgcodes;
	}
	/**
	 * 设置：每人限领几张
	 */
	public void setUserLimits(Integer userLimits) {
		this.userLimits = userLimits;
	}
	/**
	 * 获取：每人限领几张
	 */
	public Integer getUserLimits() {
		return userLimits;
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
	 * 设置：优惠券状态， 0 未启用， 1 启用， 2 停用
	 */
	public void setCouponStatus(String couponStatus) {
		this.couponStatus = couponStatus;
	}
	/**
	 * 获取：优惠券状态， 0 未启用， 1 启用， 2 停用
	 */
	public String getCouponStatus() {
		return couponStatus;
	}

	/**
	 * 设置：有效期类型，1 按照固定时间段， 2 按照发券日期
	 */
	public void setPeriodValidType(String periodValidType) {
		this.periodValidType = periodValidType;
	}
	/**
	 * 获取：有效期类型，1 按照固定时间段， 2 按照发券日期
	 */
	public String getPeriodValidType() {
		return periodValidType;
	}
	/**
	 * 设置：按照发券日期计算有效期情况下有效期天数
	 */
	public void setPeriodValidDays(Integer periodValidDays) {
		this.periodValidDays = periodValidDays;
	}
	/**
	 * 获取：按照发券日期计算有效期情况下有效期天数
	 */
	public Integer getPeriodValidDays() {
		return periodValidDays;
	}
	/**
	 * 设置：有效期起始时间类型， 1 立即生效， 2 次日生效， 3 次周生效， 4 次月生效
	 */
	public void setPeriodStartType(String periodStartType) {
		this.periodStartType = periodStartType;
	}
	/**
	 * 获取：有效期起始时间类型， 1 立即生效， 2 次日生效， 3 次周生效， 4 次月生效
	 */
	public String getPeriodStartType() {
		return periodStartType;
	}
	/**
	 * 设置：创建时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * 获取：创建时间
	 */
	public Date getCreateTime() {
		return createTime;
	}
	/**
	 * 设置：更新时间
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	/**
	 * 获取：更新时间
	 */
	public Date getUpdateTime() {
		return updateTime;
	}
	public String getPeriodStartTime() {
		return periodStartTime;
	}
	public String getPeriodEndTime() {
		return periodEndTime;
	}
	public void setPeriodStartTime(String periodStartTime) {
		this.periodStartTime = periodStartTime;
	}
	public void setPeriodEndTime(String periodEndTime) {
		this.periodEndTime = periodEndTime;
	}
}
