package com.phoenix.system.dao;

import java.util.List;
import java.util.Map;

import com.phoenix.system.domain.ChannelInterfacePermsDO;

public interface ChannelInterfacePermsDao {
	
	public List<ChannelInterfacePermsDO> list(Map<String, Object> map);
	
	public int count(Map<String, Object> map);
	
	public int save(ChannelInterfacePermsDO channelInterfacePermsDO);
	
	public int batchSave(List<ChannelInterfacePermsDO> channelInterfacePermsDOs);
	
	public int remove(String id);
	
	public int batchRemove(Map<String, Object> map);
}