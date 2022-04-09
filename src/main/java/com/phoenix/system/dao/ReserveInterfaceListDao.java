package com.phoenix.system.dao;

import java.util.List;
import java.util.Map;

import com.phoenix.system.domain.ReserveInterfaceListDO;

public interface ReserveInterfaceListDao {
	
	public List<ReserveInterfaceListDO> list(Map<String, Object> map);
	
	public int count(Map<String, Object> map);
	
	public int save(ReserveInterfaceListDO reserveInterfaceListDO);
	
	public int update(ReserveInterfaceListDO reserveInterfaceListDO);
	
	public int remove(String interfaceId);
	
	public int batchRemove(String[] interfaceIds);
}