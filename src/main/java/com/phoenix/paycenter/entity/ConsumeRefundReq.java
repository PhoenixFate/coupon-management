package com.phoenix.paycenter.entity;

/**
 * 
	 * 类描述 账户充值入参
 	 * @version  1.0
	 * @author  phoenix
	 * @version  2019年2月27日 下午6:16:33
 */
public class ConsumeRefundReq {
	
	private String orgCode; // 机构代码
	private String channelCode; // 渠道代码
	private String sign; // 签名
	
	//退款金额， 单位分
    private Integer refundAmount;

    //充值商户订单号
  	private String thirdOrdernumber;
  	
  	//退款流水号
  	private String refundOrdernumber;
  	
  	//退款描述
  	private String remark;
  	//退款终端号
  	private String terminalNo;
    //退款操作员号
  	private String refundCollector;
	public String getOrgCode() {
		return orgCode;
	}
	public String getChannelCode() {
		return channelCode;
	}
	public String getSign() {
		return sign;
	}
	public Integer getRefundAmount() {
		return refundAmount;
	}
	public String getThirdOrdernumber() {
		return thirdOrdernumber;
	}
	public String getRemark() {
		return remark;
	}
	public String getTerminalNo() {
		return terminalNo;
	}
	public String getRefundCollector() {
		return refundCollector;
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
	public void setRefundAmount(Integer refundAmount) {
		this.refundAmount = refundAmount;
	}
	public void setThirdOrdernumber(String thirdOrdernumber) {
		this.thirdOrdernumber = thirdOrdernumber;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public void setTerminalNo(String terminalNo) {
		this.terminalNo = terminalNo;
	}
	public void setRefundCollector(String refundCollector) {
		this.refundCollector = refundCollector;
	}
	public String getRefundOrdernumber() {
		return refundOrdernumber;
	}
	public void setRefundOrdernumber(String refundOrdernumber) {
		this.refundOrdernumber = refundOrdernumber;
	}
  	
	
}
