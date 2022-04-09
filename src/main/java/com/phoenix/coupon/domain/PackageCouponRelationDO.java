package com.phoenix.coupon.domain;

import java.io.Serializable;


/**
 * 活动和优惠券关系表
 * 
 * @author phoenix
 * @email sm516116978@outlook.com
 * @date 2019-07-11 10:44:01
 */
public class PackageCouponRelationDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//主键
	private String relationId;
	//活动套餐ID
	private String packageId;
	//优惠券ID
	private String couponId;
	
	private Integer couponNums;

	/**
	 * 设置：主键
	 */
	public void setRelationId(String relationId) {
		this.relationId = relationId;
	}
	/**
	 * 获取：主键
	 */
	public String getRelationId() {
		return relationId;
	}
	/**
	 * 设置：活动套餐ID
	 */
	public void setPackageId(String packageId) {
		this.packageId = packageId;
	}
	/**
	 * 获取：活动套餐ID
	 */
	public String getPackageId() {
		return packageId;
	}
	/**
	 * 设置：优惠券ID
	 */
	public void setCouponId(String couponId) {
		this.couponId = couponId;
	}
	/**
	 * 获取：优惠券ID
	 */
	public String getCouponId() {
		return couponId;
	}
	public Integer getCouponNums() {
		return couponNums;
	}
	public void setCouponNums(Integer couponNums) {
		this.couponNums = couponNums;
	}
}
