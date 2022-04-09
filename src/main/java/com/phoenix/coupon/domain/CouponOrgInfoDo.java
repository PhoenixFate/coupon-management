package com.phoenix.coupon.domain;

import java.io.Serializable;

public class CouponOrgInfoDo implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//机构代码
	private String orgCode;
	
	//机构名
	private String orgName;
	
	//机构登记
	private String orgGrade;
	
	//机构描述
	private String orgDesc;
	
	//优惠券ID
	private String couponId;
	
	//是否有关联
	private Boolean checked;

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getOrgGrade() {
		return orgGrade;
	}

	public void setOrgGrade(String orgGrade) {
		this.orgGrade = orgGrade;
	}

	public String getOrgDesc() {
		return orgDesc;
	}

	public void setOrgDesc(String orgDesc) {
		this.orgDesc = orgDesc;
	}

	public String getCouponId() {
		return couponId;
	}

	public void setCouponId(String couponId) {
		this.couponId = couponId;
	}
	
	


	public Boolean getChecked() {
		return checked;
	}

	public void setChecked(Boolean checked) {
		this.checked = checked;
	}

	@Override
	public String toString() {
		return "CouponOrgInfoDo [orgCode=" + orgCode + ", orgName=" + orgName + ", orgGrade=" + orgGrade + ", orgDesc="
				+ orgDesc + ", couponId=" + couponId + ", checked=" + checked + "]";
	}
	
	
	

}
