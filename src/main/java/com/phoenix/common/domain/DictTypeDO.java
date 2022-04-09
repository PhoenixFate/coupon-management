package com.phoenix.common.domain;

import java.io.Serializable;



/**
 * 字典表
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-03-20 17:08:57
 */
public class DictTypeDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//编号
	private String typeId;
	//字典类型编码
	private String typeCode;
	//字典类型名称
	private String typeName;
	
	private String remarks;
	public String getTypeId() {
		return typeId;
	}
	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}
	public String getTypeCode() {
		return typeCode;
	}
	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
}
