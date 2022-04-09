package com.phoenix.paycenter.entity;


/**
 * 描述：窗口扫码付出参
 * @author tw
 *
 * 2018年6月6日
 */
public class WuaiPayRefundRsp {
	/**
	 * 响应编码
	 */
	private String rspCode;

	/**
	 * 响应消息
	 */
	private String rspMsg;
	
	private String payMethod; 
	
	/**
	 * 商户订单号.商户系统内部订单号，要求32个字符内，只能是数字、大小写字母_-|*且在同一个商户号下唯一
	 */
	private String out_trade_no;
	
	/**
	 * 退款订单号， 同一个单号只能退一次款
	 */
	private String refundOrderNumber;
	
    //退款金额，单位分
    private Integer refundAmount;
    
    private String tradeNo; //第三方退款流水号

	public String getRspCode() {
		return rspCode;
	}

	public String getRspMsg() {
		return rspMsg;
	}

	public String getPayMethod() {
		return payMethod;
	}

	public String getOut_trade_no() {
		return out_trade_no;
	}

	public String getRefundOrderNumber() {
		return refundOrderNumber;
	}

	public void setRspCode(String rspCode) {
		this.rspCode = rspCode;
	}

	public void setRspMsg(String rspMsg) {
		this.rspMsg = rspMsg;
	}

	public void setPayMethod(String payMethod) {
		this.payMethod = payMethod;
	}

	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}

	public void setRefundOrderNumber(String refundOrderNumber) {
		this.refundOrderNumber = refundOrderNumber;
	}

	public Integer getRefundAmount() {
		return refundAmount;
	}

	public void setRefundAmount(Integer refundAmount) {
		this.refundAmount = refundAmount;
	}

	public String getTradeNo() {
		return tradeNo;
	}

	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}
	

}
