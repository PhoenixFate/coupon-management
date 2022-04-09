package com.phoenix.coupon.domain;

import java.io.Serializable;
import java.util.Date;

public class ConsumeLogCashOutDo implements Serializable  {
		private static final long serialVersionUID = 1L;

		//消费流水号
		private String consumeId;
		//账户id
		private String accountId;
		//账户号
		private String accountNo;
		//消费渠道码
		private String channelCode;
		//消费时间
		private Date consumeTime;
		//第三方订单号
		private String thirdOrdernumber;
		//描述
		private String remark;
		//优惠券号码
		private String couponNo;
		//撤销标记， 0 未退， 1 已退
		private String refundFlag;
		//撤销时间
		private Date refundTime;
		//消费业务类型
		private String bizCode;
		//消费业务类型名称
		private String bizName;
		//消费状态， 1 核销成功， 0 撤销
		private String consumeStatus;
		//医院代码
		private String orgCode;
		//账户姓名
		private String username;
		//核销操作员号
		private String consumeCollector;
		//撤销操作员号
		private String refundCollector;
		//优惠券基础信息ID
		private String couponId;
		//优惠券对应的产品编码
		private String productCode;
		//撤销渠道
		private String refundChannelCode;
		//业绩所属医生工号
		private String belogtoDoctor;
		//提现标记， 1 已提现， 0 未提现
		private String cashOutFlag;
		//提现记录表的主键
		private String cashOutFlow;
		//提现时间
		private Date cashOutTime;
		//本次提现数量
		private Integer cashOutNums;
		//提现医生ID
		private String doctorId;
		//医生工号
		private String doctorCode;
		//医生姓名
		private String doctorName;
		//后台提现操作员用户名
		private String cashOutOperator;
		public String getConsumeId() {
			return consumeId;
		}
		public void setConsumeId(String consumeId) {
			this.consumeId = consumeId;
		}
		public String getAccountId() {
			return accountId;
		}
		public void setAccountId(String accountId) {
			this.accountId = accountId;
		}
		public String getAccountNo() {
			return accountNo;
		}
		public void setAccountNo(String accountNo) {
			this.accountNo = accountNo;
		}
		public String getChannelCode() {
			return channelCode;
		}
		public void setChannelCode(String channelCode) {
			this.channelCode = channelCode;
		}
		
		public String getThirdOrdernumber() {
			return thirdOrdernumber;
		}
		public void setThirdOrdernumber(String thirdOrdernumber) {
			this.thirdOrdernumber = thirdOrdernumber;
		}
		public String getRemark() {
			return remark;
		}
		public void setRemark(String remark) {
			this.remark = remark;
		}
		public String getCouponNo() {
			return couponNo;
		}
		public void setCouponNo(String couponNo) {
			this.couponNo = couponNo;
		}
		public String getRefundFlag() {
			return refundFlag;
		}
		public void setRefundFlag(String refundFlag) {
			this.refundFlag = refundFlag;
		}
		public String getBizCode() {
			return bizCode;
		}
		public void setBizCode(String bizCode) {
			this.bizCode = bizCode;
		}
		public String getBizName() {
			return bizName;
		}
		public void setBizName(String bizName) {
			this.bizName = bizName;
		}
		public String getConsumeStatus() {
			return consumeStatus;
		}
		public void setConsumeStatus(String consumeStatus) {
			this.consumeStatus = consumeStatus;
		}
		public String getOrgCode() {
			return orgCode;
		}
		public void setOrgCode(String orgCode) {
			this.orgCode = orgCode;
		}
		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}
		public String getConsumeCollector() {
			return consumeCollector;
		}
		public void setConsumeCollector(String consumeCollector) {
			this.consumeCollector = consumeCollector;
		}
		public String getRefundCollector() {
			return refundCollector;
		}
		public void setRefundCollector(String refundCollector) {
			this.refundCollector = refundCollector;
		}
		public String getCouponId() {
			return couponId;
		}
		public void setCouponId(String couponId) {
			this.couponId = couponId;
		}
		public String getProductCode() {
			return productCode;
		}
		public void setProductCode(String productCode) {
			this.productCode = productCode;
		}
		public String getRefundChannelCode() {
			return refundChannelCode;
		}
		public void setRefundChannelCode(String refundChannelCode) {
			this.refundChannelCode = refundChannelCode;
		}
		public String getBelogtoDoctor() {
			return belogtoDoctor;
		}
		public void setBelogtoDoctor(String belogtoDoctor) {
			this.belogtoDoctor = belogtoDoctor;
		}
		public String getCashOutFlag() {
			return cashOutFlag;
		}
		public void setCashOutFlag(String cashOutFlag) {
			this.cashOutFlag = cashOutFlag;
		}
		public String getCashOutFlow() {
			return cashOutFlow;
		}
		public void setCashOutFlow(String cashOutFlow) {
			this.cashOutFlow = cashOutFlow;
		}
		public Integer getCashOutNums() {
			return cashOutNums;
		}
		public void setCashOutNums(Integer cashOutNums) {
			this.cashOutNums = cashOutNums;
		}
		public String getDoctorId() {
			return doctorId;
		}
		public void setDoctorId(String doctorId) {
			this.doctorId = doctorId;
		}
		public String getDoctorCode() {
			return doctorCode;
		}
		public void setDoctorCode(String doctorCode) {
			this.doctorCode = doctorCode;
		}
		public String getDoctorName() {
			return doctorName;
		}
		public void setDoctorName(String doctorName) {
			this.doctorName = doctorName;
		}
		public String getCashOutOperator() {
			return cashOutOperator;
		}
		public void setCashOutOperator(String cashOutOperator) {
			this.cashOutOperator = cashOutOperator;
		}
		public Date getConsumeTime() {
			return consumeTime;
		}
		public void setConsumeTime(Date consumeTime) {
			this.consumeTime = consumeTime;
		}

		public Date getRefundTime() {
			return refundTime;
		}
		public void setRefundTime(Date refundTime) {
			this.refundTime = refundTime;
		}
		public Date getCashOutTime() {
			return cashOutTime;
		}
		public void setCashOutTime(Date cashOutTime) {
			this.cashOutTime = cashOutTime;
		}
}
