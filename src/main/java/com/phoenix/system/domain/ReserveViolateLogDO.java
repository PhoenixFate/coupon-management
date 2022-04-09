package com.phoenix.system.domain;

import java.io.Serializable;
import java.util.Date;

public class ReserveViolateLogDO implements Serializable {
	private String reserveId;

    private String scheduleId;

    private String ordinaryFlag;

    private String deptCode;

    private String deptName;

    private String doctorCode;

    private String doctorName;

    private String doctorTitle;

    private String scheduleDate;

    private String scheduleDayWeeks;

    private String reservatePath;

    private String reserveStatus;

    private String orgCode;

    private String orgName;

    private String patientId;

    private String patientName;

    private String patientSex;

    private String patientAge;

    private String idNo;
    
    private String encryptionIdNo;

    private String phoneNo;

    private Date createGmt;

    private Date updateGmt;

    private String cleanFlag;

    private String appealStatus;

    private String appealContent;

    private Date appealGmt;

    private String appealAuditContent;

    private Date appealAuditGmt;
    
    private String appealAuditUser;

    private static final long serialVersionUID = 1L;

    public String getReserveId() {
        return reserveId;
    }

    public void setReserveId(String reserveId) {
        this.reserveId = reserveId == null ? null : reserveId.trim();
    }

    public String getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(String scheduleId) {
        this.scheduleId = scheduleId == null ? null : scheduleId.trim();
    }

    public String getOrdinaryFlag() {
        return ordinaryFlag;
    }

    public void setOrdinaryFlag(String ordinaryFlag) {
        this.ordinaryFlag = ordinaryFlag == null ? null : ordinaryFlag.trim();
    }

    public String getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode == null ? null : deptCode.trim();
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName == null ? null : deptName.trim();
    }

    public String getDoctorCode() {
        return doctorCode;
    }

    public void setDoctorCode(String doctorCode) {
        this.doctorCode = doctorCode == null ? null : doctorCode.trim();
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName == null ? null : doctorName.trim();
    }

    public String getDoctorTitle() {
        return doctorTitle;
    }

    public void setDoctorTitle(String doctorTitle) {
        this.doctorTitle = doctorTitle == null ? null : doctorTitle.trim();
    }

    public String getScheduleDate() {
        return scheduleDate;
    }

    public void setScheduleDate(String scheduleDate) {
        this.scheduleDate = scheduleDate == null ? null : scheduleDate.trim();
    }

    public String getScheduleDayWeeks() {
        return scheduleDayWeeks;
    }

    public void setScheduleDayWeeks(String scheduleDayWeeks) {
        this.scheduleDayWeeks = scheduleDayWeeks == null ? null : scheduleDayWeeks.trim();
    }

    public String getReservatePath() {
        return reservatePath;
    }

    public void setReservatePath(String reservatePath) {
        this.reservatePath = reservatePath == null ? null : reservatePath.trim();
    }

    public String getReserveStatus() {
        return reserveStatus;
    }

    public void setReserveStatus(String reserveStatus) {
        this.reserveStatus = reserveStatus == null ? null : reserveStatus.trim();
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode == null ? null : orgCode.trim();
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName == null ? null : orgName.trim();
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId == null ? null : patientId.trim();
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName == null ? null : patientName.trim();
    }

    public String getPatientSex() {
        return patientSex;
    }

    public void setPatientSex(String patientSex) {
        this.patientSex = patientSex == null ? null : patientSex.trim();
    }

    public String getPatientAge() {
        return patientAge;
    }

    public void setPatientAge(String patientAge) {
        this.patientAge = patientAge == null ? null : patientAge.trim();
    }

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo == null ? null : idNo.trim();
    }

    public String getEncryptionIdNo() {
		return encryptionIdNo;
	}

	public void setEncryptionIdNo(String encryptionIdNo) {
		this.encryptionIdNo = encryptionIdNo == null ? null : encryptionIdNo.trim();
	}

	public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo == null ? null : phoneNo.trim();
    }

    public Date getCreateGmt() {
        return createGmt;
    }

    public void setCreateGmt(Date createGmt) {
        this.createGmt = createGmt;
    }

    public Date getUpdateGmt() {
        return updateGmt;
    }

    public void setUpdateGmt(Date updateGmt) {
        this.updateGmt = updateGmt;
    }

    public String getCleanFlag() {
        return cleanFlag;
    }

    public void setCleanFlag(String cleanFlag) {
        this.cleanFlag = cleanFlag == null ? null : cleanFlag.trim();
    }

    public String getAppealStatus() {
        return appealStatus;
    }

    public void setAppealStatus(String appealStatus) {
        this.appealStatus = appealStatus == null ? null : appealStatus.trim();
    }

    public String getAppealContent() {
        return appealContent;
    }

    public void setAppealContent(String appealContent) {
        this.appealContent = appealContent == null ? null : appealContent.trim();
    }

    public Date getAppealGmt() {
        return appealGmt;
    }

    public void setAppealGmt(Date appealGmt) {
        this.appealGmt = appealGmt;
    }

    public String getAppealAuditContent() {
        return appealAuditContent;
    }

    public void setAppealAuditContent(String appealAuditContent) {
        this.appealAuditContent = appealAuditContent == null ? null : appealAuditContent.trim();
    }

    public Date getAppealAuditGmt() {
        return appealAuditGmt;
    }

    public void setAppealAuditGmt(Date appealAuditGmt) {
        this.appealAuditGmt = appealAuditGmt;
    }

	public String getAppealAuditUser() {
		return appealAuditUser;
	}

	public void setAppealAuditUser(String appealAuditUser) {
		this.appealAuditUser = appealAuditUser == null ? null : appealAuditUser.trim();
	}
}