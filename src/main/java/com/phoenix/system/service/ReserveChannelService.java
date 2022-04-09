package com.phoenix.system.service;

import com.phoenix.system.domain.ReserveChannelDO;

import java.util.List;
import java.util.Map;

public interface ReserveChannelService {
	
	public List<ReserveChannelDO> list(Map<String, Object> map);
	
	public int count(Map<String, Object> map);
	
	public int save(ReserveChannelDO reserveChannelDO);
	
	public boolean checkExists(Map<String, Object> params);
	
	public int update(ReserveChannelDO reserveChannelDO);
	
}
