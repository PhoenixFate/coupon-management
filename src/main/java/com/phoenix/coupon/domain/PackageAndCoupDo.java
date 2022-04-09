package com.phoenix.coupon.domain;

public class PackageAndCoupDo {
	
	//服务包ID
	private String packageId;
	
	//优惠券id
	private String couponId;
	
	//优惠券个数
	private String couponNums;

	public String getPackageId() {
		return packageId;
	}

	public void setPackageId(String packageId) {
		this.packageId = packageId;
	}

	public String getCouponId() {
		return couponId;
	}

	public void setCouponId(String couponId) {
		this.couponId = couponId;
	}

	public String getCouponNums() {
		return couponNums;
	}

	public void setCouponNums(String couponNums) {
		this.couponNums = couponNums;
	}

	@Override
	public String toString() {
		return "PackageAndCoupDo [packageId=" + packageId + ", couponId=" + couponId + ", couponNums=" + couponNums
				+ "]";
	}
	
}
