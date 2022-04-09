package com.phoenix.coupon.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 账户信息
 * 
 * @author phoenix
 * @email sm516116978@outlook.com
 * @date 2019-07-11 10:44:00
 */
public class AccountInfoDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//主键
	private String accountId;
	//账户号
	private String accountNo;
	//账户类型， 1 某慧医疗
	private String accountType;
	//账户姓名
	private String username;
	//密码
	private String password;
	//账户状态， 1 正常， 2 冻结， 3 停用
	private String enableFlag;
	//开户时间
	private Date crtTime;
	//最后更新时间
	private Date updateTime;
	//手机号码
	private String phoneno;
	//开户渠道
	private String createChannel;
	//身份证号码
	private String idno;
	//乐观锁
	private String version;

	/**
	 * 设置：主键
	 */
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	/**
	 * 获取：主键
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
	 * 设置：账户类型， 1 某慧医疗
	 */
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	/**
	 * 获取：账户类型， 1 某慧医疗
	 */
	public String getAccountType() {
		return accountType;
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
	 * 设置：密码
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * 获取：密码
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * 设置：账户状态， 1 正常， 2 冻结， 3 停用
	 */
	public void setEnableFlag(String enableFlag) {
		this.enableFlag = enableFlag;
	}
	/**
	 * 获取：账户状态， 1 正常， 2 冻结， 3 停用
	 */
	public String getEnableFlag() {
		return enableFlag;
	}
	/**
	 * 设置：开户时间
	 */
	public void setCrtTime(Date crtTime) {
		this.crtTime = crtTime;
	}
	/**
	 * 获取：开户时间
	 */
	public Date getCrtTime() {
		return crtTime;
	}
	/**
	 * 设置：最后更新时间
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	/**
	 * 获取：最后更新时间
	 */
	public Date getUpdateTime() {
		return updateTime;
	}
	/**
	 * 设置：手机号码
	 */
	public void setPhoneno(String phoneno) {
		this.phoneno = phoneno;
	}
	/**
	 * 获取：手机号码
	 */
	public String getPhoneno() {
		return phoneno;
	}
	/**
	 * 设置：开户渠道
	 */
	public void setCreateChannel(String createChannel) {
		this.createChannel = createChannel;
	}
	/**
	 * 获取：开户渠道
	 */
	public String getCreateChannel() {
		return createChannel;
	}
	/**
	 * 设置：身份证号码
	 */
	public void setIdno(String idno) {
		this.idno = idno;
	}
	/**
	 * 获取：身份证号码
	 */
	public String getIdno() {
		return idno;
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
}
