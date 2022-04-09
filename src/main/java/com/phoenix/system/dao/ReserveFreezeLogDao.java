package com.phoenix.system.dao;

import java.util.List;
import java.util.Map;

import com.phoenix.system.domain.ReserveFreezeLogDO;

public interface ReserveFreezeLogDao {
	
	public List<ReserveFreezeLogDO> list(Map<String, Object> map);
	
	public int count(Map<String, Object> map);
}