package com.phoenix.system.service;

import com.phoenix.system.domain.ChannelInterfacePermsDO;

import java.util.List;
import java.util.Map;

public interface ChannelInterfacePermsService {
	
	public List<ChannelInterfacePermsDO> list(Map<String, Object> map);
	
	public int count(Map<String, Object> map);
	
	public int save(ChannelInterfacePermsDO channelInterfacePermsDO);
	
	public int batchSave(String[] interfaceIds, String channelCode);
	
}
