package com.phoenix.system.service;

import com.phoenix.system.domain.ReserveFreezeLogDO;

import java.util.List;
import java.util.Map;

public interface ReserveFreezeLogService {
	
	public List<ReserveFreezeLogDO> list(Map<String, Object> map);
	
	public int count(Map<String, Object> map);
	
}
