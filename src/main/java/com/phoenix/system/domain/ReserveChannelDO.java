package com.phoenix.system.domain;

import java.io.Serializable;
import java.util.Date;

public class ReserveChannelDO implements Serializable {
    private String channelId;

    private String channelCode;

    private String channelName;

    private Integer numberSource;

    private String orgCode;

    private Date createTime;

    private Date updateTime;
    
    private String delFlag;
    
    private String accessKeyId;

    private String accessKeySecret;
    
    private Integer interfaceFrequency;
    
    private String frozenStatus;
    
    private Date frozenExpireTime;
    
    private boolean checked = false;

    private static final long serialVersionUID = 1L;

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId == null ? null : channelId.trim();
    }

    public String getChannelCode() {
        return channelCode;
    }

    public void setChannelCode(String channelCode) {
        this.channelCode = channelCode == null ? null : channelCode.trim();
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName == null ? null : channelName.trim();
    }

    public Integer getNumberSource() {
        return numberSource;
    }

    public void setNumberSource(Integer numberSource) {
        this.numberSource = numberSource;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode == null ? null : orgCode.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
    
    public String getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag == null ? null : delFlag.trim();
	}

	public String getAccessKeyId() {
        return accessKeyId;
    }

    public void setAccessKeyId(String accessKeyId) {
        this.accessKeyId = accessKeyId == null ? null : accessKeyId.trim();
    }

    public String getAccessKeySecret() {
        return accessKeySecret;
    }

    public void setAccessKeySecret(String accessKeySecret) {
        this.accessKeySecret = accessKeySecret == null ? null : accessKeySecret.trim();
    }

	public Integer getInterfaceFrequency() {
		return interfaceFrequency;
	}

	public void setInterfaceFrequency(Integer interfaceFrequency) {
		this.interfaceFrequency = interfaceFrequency;
	}

	public String getFrozenStatus() {
		return frozenStatus;
	}

	public void setFrozenStatus(String frozenStatus) {
		this.frozenStatus = frozenStatus == null ? null : frozenStatus.trim();
	}

	public Date getFrozenExpireTime() {
		return frozenExpireTime;
	}

	public void setFrozenExpireTime(Date frozenExpireTime) {
		this.frozenExpireTime = frozenExpireTime;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

}