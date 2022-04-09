package com.phoenix.coupon.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 用户服务包关系表
 * 
 * @author tangwei
 * @email 
 * @date 2019-07-26 15:35:06
 */
public class AccountPackageRelationDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//主键
	private String relationId;
	//服务包ID
	private String packageId;
	//优惠券ID
	private String accountId;
	//用户账号
	private String accountNo;
	//服务包名称
	private String packageName;
	//发放时间
	private Date createTime;
	//用户姓名
	private String username;
	//该服务包对应的产品
	private String productCode;
	//该服务包对应的产品名称
	private String productName;
	//发放该服务包的业务流水
	private String bizFlow;
	//服务包生效时间
	private String startTime;
	//服务包失效时间
	private String endTime;

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
	 * 设置：服务包ID
	 */
	public void setPackageId(String packageId) {
		this.packageId = packageId;
	}
	/**
	 * 获取：服务包ID
	 */
	public String getPackageId() {
		return packageId;
	}
	/**
	 * 设置：优惠券ID
	 */
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	/**
	 * 获取：优惠券ID
	 */
	public String getAccountId() {
		return accountId;
	}
	/**
	 * 设置：用户账号
	 */
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	/**
	 * 获取：用户账号
	 */
	public String getAccountNo() {
		return accountNo;
	}
	/**
	 * 设置：服务包名称
	 */
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	/**
	 * 获取：服务包名称
	 */
	public String getPackageName() {
		return packageName;
	}
	/**
	 * 设置：发放时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * 获取：发放时间
	 */
	public Date getCreateTime() {
		return createTime;
	}
	/**
	 * 设置：用户姓名
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * 获取：用户姓名
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * 设置：该服务包对应的产品
	 */
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	/**
	 * 获取：该服务包对应的产品
	 */
	public String getProductCode() {
		return productCode;
	}
	/**
	 * 设置：该服务包对应的产品名称
	 */
	public void setProductName(String productName) {
		this.productName = productName;
	}
	/**
	 * 获取：该服务包对应的产品名称
	 */
	public String getProductName() {
		return productName;
	}
	/**
	 * 设置：发放该服务包的业务流水
	 */
	public void setBizFlow(String bizFlow) {
		this.bizFlow = bizFlow;
	}
	/**
	 * 获取：发放该服务包的业务流水
	 */
	public String getBizFlow() {
		return bizFlow;
	}
	/**
	 * 设置：服务包生效时间
	 */
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	/**
	 * 获取：服务包生效时间
	 */
	public String getStartTime() {
		return startTime;
	}
	/**
	 * 设置：服务包失效时间
	 */
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	/**
	 * 获取：服务包失效时间
	 */
	public String getEndTime() {
		return endTime;
	}
}
