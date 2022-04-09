package com.phoenix.coupon.domain;

public class CouponStatistic {
	
	private Integer nums;
	
	private Integer totalNums;
	
	// 优惠核销数
	private Integer consumeNums;
	
	// 优惠券发券数
	private Integer sendNums;
	
	//用户数
	private Integer accountNums;
	
	private String channelCode;
	
	private String channelName;
	
	//核销操作员号
	private String consumeCollector;
	//'业绩所属医生工号
	private String belogtoDoctor;
	
	// 产品id
	private String productId;
	//产品名称
	private String productName;
	
	private String tradeDate;
	
	// 占比
	private Integer percent;
	
	private String type;
	
	//
	private String couponId;
	//优惠券名称
	private String couponName;
	
	// 提现标记， 1 已提现， 0 未提现
	private String cashOutFlag;

	public Integer getConsumeNums() {
		return consumeNums;
	}

	public void setConsumeNums(Integer consumeNums) {
		this.consumeNums = consumeNums;
	}

	public Integer getSendNums() {
		return sendNums;
	}

	public void setSendNums(Integer sendNums) {
		this.sendNums = sendNums;
	}

	public Integer getAccountNums() {
		return accountNums;
	}

	public void setAccountNums(Integer accountNums) {
		this.accountNums = accountNums;
	}

	public String getChannelCode() {
		return channelCode;
	}

	public void setChannelCode(String channelCode) {
		this.channelCode = channelCode;
	}

	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	public String getConsumeCollector() {
		return consumeCollector;
	}

	public void setConsumeCollector(String consumeCollector) {
		this.consumeCollector = consumeCollector;
	}

	public String getBelogtoDoctor() {
		return belogtoDoctor;
	}

	public void setBelogtoDoctor(String belogtoDoctor) {
		this.belogtoDoctor = belogtoDoctor;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getTradeDate() {
		return tradeDate;
	}

	public void setTradeDate(String tradeDate) {
		this.tradeDate = tradeDate;
	}


	public Integer getPercent() {
		return percent;
	}

	public void setPercent(Integer percent) {
		this.percent = percent;
	}

	public Integer getNums() {
		return nums;
	}

	public void setNums(Integer nums) {
		this.nums = nums;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}


	public String getCouponName() {
		return couponName;
	}

	public void setCouponName(String couponName) {
		this.couponName = couponName;
	}

	public Integer getTotalNums() {
		return totalNums;
	}

	public void setTotalNums(Integer totalNums) {
		this.totalNums = totalNums;
	}

	public String getCouponId() {
		return couponId;
	}

	public void setCouponId(String couponId) {
		this.couponId = couponId;
	}

	public String getCashOutFlag() {
		return cashOutFlag;
	}

	public void setCashOutFlag(String cashOutFlag) {
		this.cashOutFlag = cashOutFlag;
	}

}
