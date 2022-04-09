package com.phoenix.system.domain;

import java.io.Serializable;
import java.util.Date;

public class ReserveInterfaceListDO implements Serializable {
    private String interfaceId;

    private String interfaceName;

    private String interfaceUrl;

    private String interfacePerms;

    private Integer interfaceOrderNum;

    private Date createGmt;

    private Date updateGmt;

    private String remark;

    private static final long serialVersionUID = 1L;
    
    private boolean checked = false;

    public String getInterfaceId() {
        return interfaceId;
    }

    public void setInterfaceId(String interfaceId) {
        this.interfaceId = interfaceId == null ? null : interfaceId.trim();
    }

    public String getInterfaceName() {
        return interfaceName;
    }

    public void setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName == null ? null : interfaceName.trim();
    }

    public String getInterfaceUrl() {
        return interfaceUrl;
    }

    public void setInterfaceUrl(String interfaceUrl) {
        this.interfaceUrl = interfaceUrl == null ? null : interfaceUrl.trim();
    }

    public String getInterfacePerms() {
        return interfacePerms;
    }

    public void setInterfacePerms(String interfacePerms) {
        this.interfacePerms = interfacePerms == null ? null : interfacePerms.trim();
    }

    public Integer getInterfaceOrderNum() {
        return interfaceOrderNum;
    }

    public void setInterfaceOrderNum(Integer interfaceOrderNum) {
        this.interfaceOrderNum = interfaceOrderNum;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

}