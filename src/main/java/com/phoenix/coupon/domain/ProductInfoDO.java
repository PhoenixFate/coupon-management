package com.phoenix.coupon.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 产品（商品）信息表
 * 
 * @author phoenix
 * @email sm516116978@outlook.com
 * @date 2019-07-11 10:44:02
 */
public class ProductInfoDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//产品ID
	private String productId;
	//产品名称
	private String productName;
	//产品详情介绍
	private String productDetail;
	//产品编码，与业务对应
	private String productCode;
	//乐观锁
	private String version;
	//产品状态， 0 未启用， 1 启用， 2 停用
	private String productStatus;
	//创建时间
	private Date createTime;
	//更新时间
	private Date updateTime;

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
	/**
	 * 设置：产品名称
	 */
	public void setProductName(String productName) {
		this.productName = productName;
	}
	/**
	 * 获取：产品名称
	 */
	public String getProductName() {
		return productName;
	}
	/**
	 * 设置：产品详情介绍
	 */
	public void setProductDetail(String productDetail) {
		this.productDetail = productDetail;
	}
	/**
	 * 获取：产品详情介绍
	 */
	public String getProductDetail() {
		return productDetail;
	}
	/**
	 * 设置：产品编码，与业务对应
	 */
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	/**
	 * 获取：产品编码，与业务对应
	 */
	public String getProductCode() {
		return productCode;
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
	 * 设置：产品状态， 0 未启用， 1 启用， 2 停用
	 */
	public void setProductStatus(String productStatus) {
		this.productStatus = productStatus;
	}
	/**
	 * 获取：产品状态， 0 未启用， 1 启用， 2 停用
	 */
	public String getProductStatus() {
		return productStatus;
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
}
