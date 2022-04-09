package com.phoenix.common.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 字典表
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-03-20 17:08:57
 */
public class DictDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String dictId;

	private String typeId;

	private String dictName;

	private String dictValue;

	private String dictType;

	private String dictDescription;

	private String dictSort;

	private String parentDictId;

	private String createUser;

	private Date createGmt;

	private String updateUser;

	private Date updateGmt;

	private String remarks;
	
	private String id;

	public String getDictId() {
		return dictId;
	}

	public void setDictId(String dictId) {
		this.dictId = dictId;
	}

	public String getTypeId() {
		return typeId;
	}

	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}

	public String getDictName() {
		return dictName;
	}

	public void setDictName(String dictName) {
		this.dictName = dictName;
	}

	public String getDictValue() {
		return dictValue;
	}

	public void setDictValue(String dictValue) {
		this.dictValue = dictValue;
	}

	public String getDictType() {
		return dictType;
	}

	public void setDictType(String dictType) {
		this.dictType = dictType;
	}

	public String getDictDescription() {
		return dictDescription;
	}

	public void setDictDescription(String dictDescription) {
		this.dictDescription = dictDescription;
	}

	public String getDictSort() {
		return dictSort;
	}

	public void setDictSort(String dictSort) {
		this.dictSort = dictSort;
	}

	public String getParentDictId() {
		return parentDictId;
	}

	public void setParentDictId(String parentDictId) {
		this.parentDictId = parentDictId;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public Date getCreateGmt() {
		return createGmt;
	}

	public void setCreateGmt(Date createGmt) {
		this.createGmt = createGmt;
	}

	public String getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	public Date getUpdateGmt() {
		return updateGmt;
	}

	public void setUpdateGmt(Date updateGmt) {
		this.updateGmt = updateGmt;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	
}
