package com.phoenix.coupon.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 优惠券套餐信息
 * 
 * @author phoenix
 * @email sm516116978@outlook.com
 * @date 2019-07-11 10:44:01
 */
public class PackageInfoDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//套餐ID
	private String packageId;
	//发券流水号
	private String couponId;
	//活动名称
	private String packageName;
	//活动详情介绍
	private String packageDetail;
	//乐观锁
	private String version;
	//活动状态， 0 未启用， 1 启用， 2 停用
	private String packageStatus;
	//活动开始时间
	private String periodStartTime;
	//活动截止时间
	private String periodEndTime;
	//创建时间
	private Date createTime;
	//更新时间
	private Date updateTime;


	/**
	 * 设置：套餐ID
	 */
	public void setPackageId(String packageId) {
		this.packageId = packageId;
	}
	/**
	 * 获取：套餐ID
	 */
	public String getPackageId() {
		return packageId;
	}
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
	 * 设置：活动名称
	 */
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	/**
	 * 获取：活动名称
	 */
	public String getPackageName() {
		return packageName;
	}
	/**
	 * 设置：活动详情介绍
	 */
	public void setPackageDetail(String packageDetail) {
		this.packageDetail = packageDetail;
	}
	/**
	 * 获取：活动详情介绍
	 */
	public String getPackageDetail() {
		return packageDetail;
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
	 * 设置：活动状态， 0 未启用， 1 启用， 2 停用
	 */
	public void setPackageStatus(String packageStatus) {
		this.packageStatus = packageStatus;
	}
	/**
	 * 获取：活动状态， 0 未启用， 1 启用， 2 停用
	 */
	public String getPackageStatus() {
		return packageStatus;
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
