package com.phoenix.coupon.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 医生信息表
 * 
 * @author phoenix
 * @email sm516116978@outlook.com
 * @date 2019-07-11 10:44:01
 */
public class DoctorInfoDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//主键
	private String docApplyId;
	//医生编码
	private String docCode;
	//医生名称
	private String docName;
	//医生身份证
	private String docCard;
	//医生联系号码
	private String docPhone;
	//医生类别
	private String doctorType;
	//医生类别名称
	private String doctorTypeName;
	//医生职称
	private String docTitle;
	//医院编码
	private String hosCode;
	//审核状态(0 未审核 1 审核通过 2 审核未通过)
	private String auditStatus;
	//审核意见
	private String auditMsg;
	//创建时间
	private Date createTime;
	//修改时间
	private Date updateTime;
	//审核用户
	private String auditUser;
	//
	private String deptName;
	//医院名称
	private String hosName;
	//
	private String openId;
	//类型，微信，支付
	private String openType;
	
	private String oldDocCode;

	/**
	 * 设置：主键
	 */
	public void setDocApplyId(String docApplyId) {
		this.docApplyId = docApplyId;
	}
	/**
	 * 获取：主键
	 */
	public String getDocApplyId() {
		return docApplyId;
	}
	/**
	 * 设置：医生编码
	 */
	public void setDocCode(String docCode) {
		this.docCode = docCode;
	}
	/**
	 * 获取：医生编码
	 */
	public String getDocCode() {
		return docCode;
	}
	/**
	 * 设置：医生名称
	 */
	public void setDocName(String docName) {
		this.docName = docName;
	}
	/**
	 * 获取：医生名称
	 */
	public String getDocName() {
		return docName;
	}
	/**
	 * 设置：医生身份证
	 */
	public void setDocCard(String docCard) {
		this.docCard = docCard;
	}
	/**
	 * 获取：医生身份证
	 */
	public String getDocCard() {
		return docCard;
	}
	/**
	 * 设置：医生联系号码
	 */
	public void setDocPhone(String docPhone) {
		this.docPhone = docPhone;
	}
	/**
	 * 获取：医生联系号码
	 */
	public String getDocPhone() {
		return docPhone;
	}
	/**
	 * 设置：医生类别
	 */
	public void setDoctorType(String doctorType) {
		this.doctorType = doctorType;
	}
	/**
	 * 获取：医生类别
	 */
	public String getDoctorType() {
		return doctorType;
	}
	/**
	 * 设置：医生类别名称
	 */
	public void setDoctorTypeName(String doctorTypeName) {
		this.doctorTypeName = doctorTypeName;
	}
	/**
	 * 获取：医生类别名称
	 */
	public String getDoctorTypeName() {
		return doctorTypeName;
	}
	/**
	 * 设置：医生职称
	 */
	public void setDocTitle(String docTitle) {
		this.docTitle = docTitle;
	}
	/**
	 * 获取：医生职称
	 */
	public String getDocTitle() {
		return docTitle;
	}
	/**
	 * 设置：医院编码
	 */
	public void setHosCode(String hosCode) {
		this.hosCode = hosCode;
	}
	/**
	 * 获取：医院编码
	 */
	public String getHosCode() {
		return hosCode;
	}
	/**
	 * 设置：审核状态(0 未审核 1 审核通过 2 审核未通过)
	 */
	public void setAuditStatus(String auditStatus) {
		this.auditStatus = auditStatus;
	}
	/**
	 * 获取：审核状态(0 未审核 1 审核通过 2 审核未通过)
	 */
	public String getAuditStatus() {
		return auditStatus;
	}
	/**
	 * 设置：审核意见
	 */
	public void setAuditMsg(String auditMsg) {
		this.auditMsg = auditMsg;
	}
	/**
	 * 获取：审核意见
	 */
	public String getAuditMsg() {
		return auditMsg;
	}
	/**
	 * 设置：创建时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * 获取：创建时间
	 */
	public Date getCreateTime() {
		return createTime;
	}
	/**
	 * 设置：修改时间
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	/**
	 * 获取：修改时间
	 */
	public Date getUpdateTime() {
		return updateTime;
	}
	/**
	 * 设置：审核用户
	 */
	public void setAuditUser(String auditUser) {
		this.auditUser = auditUser;
	}
	/**
	 * 获取：审核用户
	 */
	public String getAuditUser() {
		return auditUser;
	}
	/**
	 * 设置：
	 */
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	/**
	 * 获取：
	 */
	public String getDeptName() {
		return deptName;
	}
	/**
	 * 设置：医院名称
	 */
	public void setHosName(String hosName) {
		this.hosName = hosName;
	}
	/**
	 * 获取：医院名称
	 */
	public String getHosName() {
		return hosName;
	}
	/**
	 * 设置：
	 */
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	/**
	 * 获取：
	 */
	public String getOpenId() {
		return openId;
	}
	/**
	 * 设置：类型，微信，支付
	 */
	public void setOpenType(String openType) {
		this.openType = openType;
	}
	/**
	 * 获取：类型，微信，支付
	 */
	public String getOpenType() {
		return openType;
	}
	public String getOldDocCode() {
		return oldDocCode;
	}
	public void setOldDocCode(String oldDocCode) {
		this.oldDocCode = oldDocCode;
	}
	
	
}
