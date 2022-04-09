package com.phoenix.coupon.entity;

/**
 * 
	 * 类描述 优惠券核销入参
 	 * @version  1.0
	 * @author  phoenix
	 * @version  2019年7月22日 上午10:49:32
 */
public class ConsumeReq {
	
	private String orgCode; // 机构代码
	private String channelCode; // 渠道代码
	private String sign; // 签名
	private String accountNo; //// 账户号
	//账户id
	private String couponNo; //券号
	
    //第三方订单号，商户订单号
  	private String orderNumber;
  	private String collectorNo;
  	
    //医生登录token
  	private String token; 
  	
  	
	public String getOrgCode() {
		return orgCode;
	}
	public String getChannelCode() {
		return channelCode;
	}
	public String getSign() {
		return sign;
	}
	public String getAccountNo() {
		return accountNo;
	}
	public String getCouponNo() {
		return couponNo;
	}
	public String getOrderNumber() {
		return orderNumber;
	}
	public String getCollectorNo() {
		return collectorNo;
	}
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
	public void setChannelCode(String channelCode) {
		this.channelCode = channelCode;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	public void setCouponNo(String couponNo) {
		this.couponNo = couponNo;
	}
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	public void setCollectorNo(String collectorNo) {
		this.collectorNo = collectorNo;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
  	
}
