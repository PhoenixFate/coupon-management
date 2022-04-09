package com.phoenix.coupon.domain;

import java.io.Serializable;


/**
 * 活动和产品关系表
 * 
 * @author phoenix
 * @email sm516116978@outlook.com
 * @date 2019-07-11 10:44:02
 */
public class PackageProductRelationDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//主键
	private String relationId;
	//活动套餐ID
	private String packageId;
	//产品ID
	private String productId;

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
	 * 设置：产品ID
	 */
	public void setProductId(String productId) {
		this.productId = productId;
	}
	/**
	 * 获取：产品ID
	 */
	public String getProductId() {
		return productId;
	}
}
