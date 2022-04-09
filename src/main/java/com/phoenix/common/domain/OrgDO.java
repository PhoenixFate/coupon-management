package com.phoenix.common.domain;

import java.io.Serializable;
import java.util.Date;

public class OrgDO implements Serializable {
    private String orgId;

    private String orgCode;

    private String orgName;

    private String orgGrade;

    private String orgMobile;

    private String orgImg;

    private String orgLogoImg;

    private Integer orgOrderNum;

    private String status;

    private Date createGmt;

    private Date updateGmt;

    private String initRoleStatus;

    private String orgField;

    private String orgDisplayName;

    private String orgDisplayLogo;

    private String orgDesc;
    
	//退款密码
	private String refundPassword;
	
	// 旧密码
	private String oldRefundPassword;

    private static final long serialVersionUID = 1L;

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId == null ? null : orgId.trim();
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

    public String getOrgGrade() {
        return orgGrade;
    }

    public void setOrgGrade(String orgGrade) {
        this.orgGrade = orgGrade == null ? null : orgGrade.trim();
    }

    public String getOrgMobile() {
        return orgMobile;
    }

    public void setOrgMobile(String orgMobile) {
        this.orgMobile = orgMobile == null ? null : orgMobile.trim();
    }

    public String getOrgImg() {
        return orgImg;
    }

    public void setOrgImg(String orgImg) {
        this.orgImg = orgImg == null ? null : orgImg.trim();
    }

    public String getOrgLogoImg() {
        return orgLogoImg;
    }

    public void setOrgLogoImg(String orgLogoImg) {
        this.orgLogoImg = orgLogoImg == null ? null : orgLogoImg.trim();
    }

    public Integer getOrgOrderNum() {
        return orgOrderNum;
    }

    public void setOrgOrderNum(Integer orgOrderNum) {
        this.orgOrderNum = orgOrderNum;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
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

    public String getInitRoleStatus() {
        return initRoleStatus;
    }

    public void setInitRoleStatus(String initRoleStatus) {
        this.initRoleStatus = initRoleStatus == null ? null : initRoleStatus.trim();
    }

    public String getOrgField() {
        return orgField;
    }

    public void setOrgField(String orgField) {
        this.orgField = orgField == null ? null : orgField.trim();
    }

    public String getOrgDisplayName() {
        return orgDisplayName;
    }

    public void setOrgDisplayName(String orgDisplayName) {
        this.orgDisplayName = orgDisplayName == null ? null : orgDisplayName.trim();
    }

    public String getOrgDisplayLogo() {
        return orgDisplayLogo;
    }

    public void setOrgDisplayLogo(String orgDisplayLogo) {
        this.orgDisplayLogo = orgDisplayLogo == null ? null : orgDisplayLogo.trim();
    }

    public String getOrgDesc() {
        return orgDesc;
    }

    public void setOrgDesc(String orgDesc) {
        this.orgDesc = orgDesc == null ? null : orgDesc.trim();
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        OrgDO other = (OrgDO) that;
        return (this.getOrgId() == null ? other.getOrgId() == null : this.getOrgId().equals(other.getOrgId()))
            && (this.getOrgCode() == null ? other.getOrgCode() == null : this.getOrgCode().equals(other.getOrgCode()))
            && (this.getOrgName() == null ? other.getOrgName() == null : this.getOrgName().equals(other.getOrgName()))
            && (this.getOrgGrade() == null ? other.getOrgGrade() == null : this.getOrgGrade().equals(other.getOrgGrade()))
            && (this.getOrgMobile() == null ? other.getOrgMobile() == null : this.getOrgMobile().equals(other.getOrgMobile()))
            && (this.getOrgImg() == null ? other.getOrgImg() == null : this.getOrgImg().equals(other.getOrgImg()))
            && (this.getOrgLogoImg() == null ? other.getOrgLogoImg() == null : this.getOrgLogoImg().equals(other.getOrgLogoImg()))
            && (this.getOrgOrderNum() == null ? other.getOrgOrderNum() == null : this.getOrgOrderNum().equals(other.getOrgOrderNum()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getCreateGmt() == null ? other.getCreateGmt() == null : this.getCreateGmt().equals(other.getCreateGmt()))
            && (this.getUpdateGmt() == null ? other.getUpdateGmt() == null : this.getUpdateGmt().equals(other.getUpdateGmt()))
            && (this.getInitRoleStatus() == null ? other.getInitRoleStatus() == null : this.getInitRoleStatus().equals(other.getInitRoleStatus()))
            && (this.getOrgField() == null ? other.getOrgField() == null : this.getOrgField().equals(other.getOrgField()))
            && (this.getOrgDisplayName() == null ? other.getOrgDisplayName() == null : this.getOrgDisplayName().equals(other.getOrgDisplayName()))
            && (this.getOrgDisplayLogo() == null ? other.getOrgDisplayLogo() == null : this.getOrgDisplayLogo().equals(other.getOrgDisplayLogo()))
            && (this.getOrgDesc() == null ? other.getOrgDesc() == null : this.getOrgDesc().equals(other.getOrgDesc()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getOrgId() == null) ? 0 : getOrgId().hashCode());
        result = prime * result + ((getOrgCode() == null) ? 0 : getOrgCode().hashCode());
        result = prime * result + ((getOrgName() == null) ? 0 : getOrgName().hashCode());
        result = prime * result + ((getOrgGrade() == null) ? 0 : getOrgGrade().hashCode());
        result = prime * result + ((getOrgMobile() == null) ? 0 : getOrgMobile().hashCode());
        result = prime * result + ((getOrgImg() == null) ? 0 : getOrgImg().hashCode());
        result = prime * result + ((getOrgLogoImg() == null) ? 0 : getOrgLogoImg().hashCode());
        result = prime * result + ((getOrgOrderNum() == null) ? 0 : getOrgOrderNum().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getCreateGmt() == null) ? 0 : getCreateGmt().hashCode());
        result = prime * result + ((getUpdateGmt() == null) ? 0 : getUpdateGmt().hashCode());
        result = prime * result + ((getInitRoleStatus() == null) ? 0 : getInitRoleStatus().hashCode());
        result = prime * result + ((getOrgField() == null) ? 0 : getOrgField().hashCode());
        result = prime * result + ((getOrgDisplayName() == null) ? 0 : getOrgDisplayName().hashCode());
        result = prime * result + ((getOrgDisplayLogo() == null) ? 0 : getOrgDisplayLogo().hashCode());
        result = prime * result + ((getOrgDesc() == null) ? 0 : getOrgDesc().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", orgId=").append(orgId);
        sb.append(", orgCode=").append(orgCode);
        sb.append(", orgName=").append(orgName);
        sb.append(", orgGrade=").append(orgGrade);
        sb.append(", orgMobile=").append(orgMobile);
        sb.append(", orgImg=").append(orgImg);
        sb.append(", orgLogoImg=").append(orgLogoImg);
        sb.append(", orgOrderNum=").append(orgOrderNum);
        sb.append(", status=").append(status);
        sb.append(", createGmt=").append(createGmt);
        sb.append(", updateGmt=").append(updateGmt);
        sb.append(", initRoleStatus=").append(initRoleStatus);
        sb.append(", orgField=").append(orgField);
        sb.append(", orgDisplayName=").append(orgDisplayName);
        sb.append(", orgDisplayLogo=").append(orgDisplayLogo);
        sb.append(", orgDesc=").append(orgDesc);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

	public String getRefundPassword() {
		return refundPassword;
	}

	public String getOldRefundPassword() {
		return oldRefundPassword;
	}

	public void setRefundPassword(String refundPassword) {
		this.refundPassword = refundPassword;
	}

	public void setOldRefundPassword(String oldRefundPassword) {
		this.oldRefundPassword = oldRefundPassword;
	}
}