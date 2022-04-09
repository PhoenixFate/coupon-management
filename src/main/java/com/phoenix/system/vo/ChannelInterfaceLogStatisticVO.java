package com.phoenix.system.vo;

import java.io.Serializable;

public class ChannelInterfaceLogStatisticVO implements Serializable {
    private String channelCode;
    
    private String channelName;

    private String businessType;
    
    private String businessName;

    private Integer statisticCount = 0;

    private static final long serialVersionUID = 1L;

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

	public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType == null ? null : businessType.trim();
    }

	public String getBusinessName() {
		return businessName;
	}

	public void setBusinessName(String businessName) {
		this.businessName = businessName == null ? null : businessName.trim();
	}

	public Integer getStatisticCount() {
		return statisticCount;
	}

	public void setStatisticsCount(Integer statisticCount) {
		this.statisticCount = statisticCount;
	}

}