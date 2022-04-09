package com.phoenix.system.vo;

import java.io.Serializable;

public class DataStatisticVO implements Serializable {
    
	//预约数
	private Integer reserveStatisticCount = 0;
	
	//预约数百分比
	private Float reserveStatisticPercent = 0f;
	
	//违约数
	private Integer violateStatisticCount = 0;
	
	//退约数
	private Integer refundStatisticCount = 0;
	
	//取号数
	private Integer takeStatisticCount = 0;
	
	//注册数
	private Integer registeUserStatisticCount = 0;
	
	//统计日期
	private String statisticDate;
	
	//渠道编码
	private String channelCode;
	
	//渠道名称
	private String channelName;
	
	//科室编码
	private String deptCode;
	
	//科室名称
	private String deptName;
	
	//医生编码
	private String doctorCode;
	
	//医生名称
	private String doctorName;
	
	//检验检查项目编码
	private String itemCode;
	
	//检验检查项目名称
	private String itemName;

    public Integer getReserveStatisticCount() {
		return reserveStatisticCount;
	}

	public void setReserveStatisticCount(Integer reserveStatisticCount) {
		this.reserveStatisticCount = reserveStatisticCount;
	}

	public Integer getViolateStatisticCount() {
		return violateStatisticCount;
	}

	public void setViolateStatisticCount(Integer violateStatisticCount) {
		this.violateStatisticCount = violateStatisticCount;
	}

	public Float getReserveStatisticPercent() {
		return reserveStatisticPercent;
	}

	public void setReserveStatisticPercent(Float reserveStatisticPercent) {
		this.reserveStatisticPercent = reserveStatisticPercent;
	}

	public Integer getRefundStatisticCount() {
		return refundStatisticCount;
	}

	public void setRefundStatisticCount(Integer refundStatisticCount) {
		this.refundStatisticCount = refundStatisticCount;
	}

	public Integer getTakeStatisticCount() {
		return takeStatisticCount;
	}

	public void setTakeStatisticCount(Integer takeStatisticCount) {
		this.takeStatisticCount = takeStatisticCount;
	}

	public Integer getRegisteUserStatisticCount() {
		return registeUserStatisticCount;
	}

	public void setRegisteUserStatisticCount(Integer registeUserStatisticCount) {
		this.registeUserStatisticCount = registeUserStatisticCount;
	}

	public String getStatisticDate() {
		return statisticDate;
	}

	public void setStatisticDate(String statisticDate) {
		this.statisticDate = statisticDate;
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

	public String getDeptCode() {
		return deptCode;
	}

	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
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
	
	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	private static final long serialVersionUID = 1L;

}