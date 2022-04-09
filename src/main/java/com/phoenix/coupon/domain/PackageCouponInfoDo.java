package com.phoenix.coupon.domain;

import java.io.Serializable;

public class PackageCouponInfoDo  implements Serializable  {
	private static final long serialVersionUID = 1L;

	//服务包id
	private String packageId;
	
	//关系主键
	private String relationId;
	
	//优惠券id
	private String couponId;
	
	//优惠券发放数目
	private Integer couponNums;
	
	//优惠券名称
	private String couponName;
	
	//优惠券详情
	private String couponDetail;
	
	//优惠券是否被使用
	private Boolean checked;
	
	
	
	public String getPackageId() {
		return packageId;
	}

	public void setPackageId(String packageId) {
		this.packageId = packageId;
	}

	public String getRelationId() {
		return relationId;
	}

	public void setRelationId(String relationId) {
		this.relationId = relationId;
	}

	public Integer getCouponNums() {
		return couponNums;
	}

	public void setCouponNums(Integer couponNums) {
		this.couponNums = couponNums;
	}

	public String getCouponName() {
		return couponName;
	}

	public void setCouponName(String couponName) {
		this.couponName = couponName;
	}
	
	public String getCouponId() {
		return couponId;
	}

	public void setCouponId(String couponId) {
		this.couponId = couponId;
	}

	public String getCouponDetail() {
		return couponDetail;
	}

	public void setCouponDetail(String couponDetail) {
		this.couponDetail = couponDetail;
	}
	
	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	@Override
	public String toString() {
		return "PackageCouponInfoDo [packageId=" + packageId + ", relationId=" + relationId + ", couponId=" + couponId
				+ ", couponNums=" + couponNums + ", couponName=" + couponName + ", couponDetail=" + couponDetail
				+ ", checked=" + checked + "]";
	}


}
