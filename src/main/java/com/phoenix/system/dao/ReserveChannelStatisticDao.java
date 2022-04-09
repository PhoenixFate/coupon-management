package com.phoenix.system.dao;

import java.util.List;
import java.util.Map;

import com.phoenix.system.domain.ReserveChannelStatisticDO;

public interface ReserveChannelStatisticDao {
	
	public ReserveChannelStatisticDO total(Map<String,Object> map);
	
	public List<ReserveChannelStatisticDO> list(Map<String,Object> map);
	
	public int count(Map<String,Object> map);
}