package com.phoenix.system.dao;

import java.util.List;
import java.util.Map;

import com.phoenix.system.domain.ReserveChannelDO;

public interface ReserveChannelDao {
	
	public ReserveChannelDO getByChannelCode(Map<String,Object> map);
	
	public List<ReserveChannelDO> list(Map<String,Object> map);
	
	public int count(Map<String,Object> map);
	
	public int save(ReserveChannelDO reserveChannelDO);
	
	public int batchSave(List<ReserveChannelDO> reserveChannelDOs);
	
	public int update(ReserveChannelDO reserveChannelDO);
	
}