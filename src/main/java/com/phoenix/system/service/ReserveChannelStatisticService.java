package com.phoenix.system.service;

import com.phoenix.system.domain.ReserveChannelStatisticDO;

import java.util.List;
import java.util.Map;

public interface ReserveChannelStatisticService {
	
	public List<ReserveChannelStatisticDO> list(Map<String,Object> map);
	
	public int count(Map<String,Object> map);
	
}
