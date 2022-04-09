package com.phoenix.system.domain;

import java.io.Serializable;

public class ChannelInterfacePermsDO implements Serializable {
    private String id;

    private String interfaceListId;

    private String channelCode;
    
    private String orgCode;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getInterfaceListId() {
        return interfaceListId;
    }

    public void setInterfaceListId(String interfaceListId) {
        this.interfaceListId = interfaceListId == null ? null : interfaceListId.trim();
    }

    public String getChannelCode() {
        return channelCode;
    }

    public void setChannelCode(String channelCode) {
        this.channelCode = channelCode == null ? null : channelCode.trim();
    }

    public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode == null ? null : orgCode.trim();
	}

}