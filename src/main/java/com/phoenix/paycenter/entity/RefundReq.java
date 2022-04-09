package com.phoenix.paycenter.entity;

public class RefundReq {
	private String orgCode;
	private String orderNumber;
	// 退款密码
	private String refundPwd;
	// 退款金额
	private String refundAmount;
	public String getOrgCode() {
		return orgCode;
	}
	public String getOrderNumber() {
		return orderNumber;
	}
	public String getRefundPwd() {
		return refundPwd;
	}
	public String getRefundAmount() {
		return refundAmount;
	}
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	public void setRefundPwd(String refundPwd) {
		this.refundPwd = refundPwd;
	}
	public void setRefundAmount(String refundAmount) {
		this.refundAmount = refundAmount;
	}

}
