package com.phoenix.coupon.domain;

import java.io.Serializable;

public class productPackageInfoDo implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//服务包id
	private String packageId;
	
	//服务包名
	private String packageName;
	
	//服务包详情
	private String packageDetail;
	
	//产品包代码
	private String productCode;
	
	//关系
	private String relationId;
	
	//是否使用
	private Boolean checked;


	
	

	public String getRelationId() {
		return relationId;
	}



	public void setRelationId(String relationId) {
		this.relationId = relationId;
	}



	public String getPackageId() {
		return packageId;
	}



	public void setPackageId(String packageId) {
		this.packageId = packageId;
	}



	public String getPackageName() {
		return packageName;
	}



	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}



	public String getPackageDetail() {
		return packageDetail;
	}



	public void setPackageDetail(String packageDetail) {
		this.packageDetail = packageDetail;
	}







	public String getProductCode() {
		return productCode;
	}



	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}



	public Boolean getChecked() {
		return checked;
	}



	public void setChecked(Boolean checked) {
		this.checked = checked;
	}



	@Override
	public String toString() {
		return "productPackageInfoDo [packageId=" + packageId + ", packageName=" + packageName + ", packageDetail="
				+ packageDetail + ", productCode=" + productCode + ", relationId=" + relationId + ", checked=" + checked
				+ "]";
	}




	
	

}
