package com.phoenix.common.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "api")
public class ReserveConfig {

	private String url;
	private String channelCode;
	private String APPSECRET;
	private String orgCode;
	public String getUrl() {
		return url;
	}
	public String getChannelCode() {
		return channelCode;
	}
	public String getAPPSECRET() {
		return APPSECRET;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public void setChannelCode(String channelCode) {
		this.channelCode = channelCode;
	}
	public void setAPPSECRET(String aPPSECRET) {
		APPSECRET = aPPSECRET;
	}
	public String getOrgCode() {
		return orgCode;
	}
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
}
