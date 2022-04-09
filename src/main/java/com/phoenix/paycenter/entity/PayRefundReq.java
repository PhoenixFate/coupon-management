package com.phoenix.paycenter.entity;

/**
 * 
	 * 类描述 退款请求入参
 	 * @version  1.0
	 * @author  phoenix
	 * @version  2019年4月3日 上午9:13:08
 */
public class PayRefundReq {
	
	private String channelCode;
	
	private String sign;
	
	private String orgCode;
	
	/**
	 * 支付方式 02 支付宝， 03 微信， 11 银联聚合， 12 银联APP
	 */
	private String payMethod; 
	
	/**
	 * 商户订单号.商户系统内部订单号，要求32个字符内，只能是数字、大小写字母_-|*且在同一个商户号下唯一
	 */
	private String out_trade_no;
	
	/**
	 * 退款订单号， 同一个单号只能退一次款
	 */
	private String refundOrderNumber;
	
    private String tollCollector; //退款操作员
    private String desc; //退款描述
    
    //退款金额，单位分
    private Integer refundAmount;
    
    
    private Integer orderAmount;
    
    /**
	 * 机构支付渠道ID， 一个机构可以有多个支付配置， 比如门诊和住院收到不同的支付宝账户， 扫码付和付款码收到不同的账户
	 */
	private String orgPayChannel;
	
	
	private String transactionId;

	public String getChannelCode() {
		return channelCode;
	}

	public String getSign() {
		return sign;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public String getPayMethod() {
		return payMethod;
	}

	public String getOut_trade_no() {
		return out_trade_no;
	}

	public void setChannelCode(String channelCode) {
		this.channelCode = channelCode;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public void setPayMethod(String payMethod) {
		this.payMethod = payMethod;
	}

	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}

	public String getRefundOrderNumber() {
		return refundOrderNumber;
	}

	public void setRefundOrderNumber(String refundOrderNumber) {
		this.refundOrderNumber = refundOrderNumber;
	}

	public String getTollCollector() {
		return tollCollector;
	}

	public String getDesc() {
		return desc;
	}

	public Integer getRefundAmount() {
		return refundAmount;
	}

	public void setTollCollector(String tollCollector) {
		this.tollCollector = tollCollector;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public void setRefundAmount(Integer refundAmount) {
		this.refundAmount = refundAmount;
	}

	public String getOrgPayChannel() {
		return orgPayChannel;
	}

	public void setOrgPayChannel(String orgPayChannel) {
		this.orgPayChannel = orgPayChannel;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public Integer getOrderAmount() {
		return orderAmount;
	}

	public void setOrderAmount(Integer orderAmount) {
		this.orderAmount = orderAmount;
	}
	
	
}
