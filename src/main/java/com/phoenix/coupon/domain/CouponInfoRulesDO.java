package com.phoenix.coupon.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 警报规则表
 * 
 * @author tangwei
 * @email 
 * @date 2019-08-14 16:26:01
 */
public class CouponInfoRulesDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private String id;
	//优惠券id
	private String couponId;
	//属性名
	private String ruleKey;
	//属性描述
	private String ruleName;
	//值
	private String ruleVal;
	//表达式(><=)
	private String expression;
	//创建用户id
	private String createUser;
	//创建时间
	private Date createGmt;
	//修改用户id
	private String updateUser;
	//创建时间
	private Date updateGmt;

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
	 * 设置：优惠券id
	 */
	public void setCouponId(String couponId) {
		this.couponId = couponId;
	}
	/**
	 * 获取：优惠券id
	 */
	public String getCouponId() {
		return couponId;
	}
	/**
	 * 设置：属性名
	 */
	public void setRuleKey(String ruleKey) {
		this.ruleKey = ruleKey;
	}
	/**
	 * 获取：属性名
	 */
	public String getRuleKey() {
		return ruleKey;
	}
	/**
	 * 设置：属性描述
	 */
	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
	}
	/**
	 * 获取：属性描述
	 */
	public String getRuleName() {
		return ruleName;
	}
	/**
	 * 设置：值
	 */
	public void setRuleVal(String ruleVal) {
		this.ruleVal = ruleVal;
	}
	/**
	 * 获取：值
	 */
	public String getRuleVal() {
		return ruleVal;
	}
	/**
	 * 设置：表达式(><=)
	 */
	public void setExpression(String expression) {
		this.expression = expression;
	}
	/**
	 * 获取：表达式(><=)
	 */
	public String getExpression() {
		return expression;
	}
	/**
	 * 设置：创建用户id
	 */
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	/**
	 * 获取：创建用户id
	 */
	public String getCreateUser() {
		return createUser;
	}
	/**
	 * 设置：创建时间
	 */
	public void setCreateGmt(Date createGmt) {
		this.createGmt = createGmt;
	}
	/**
	 * 获取：创建时间
	 */
	public Date getCreateGmt() {
		return createGmt;
	}
	/**
	 * 设置：修改用户id
	 */
	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}
	/**
	 * 获取：修改用户id
	 */
	public String getUpdateUser() {
		return updateUser;
	}
	/**
	 * 设置：创建时间
	 */
	public void setUpdateGmt(Date updateGmt) {
		this.updateGmt = updateGmt;
	}
	/**
	 * 获取：创建时间
	 */
	public Date getUpdateGmt() {
		return updateGmt;
	}
}
