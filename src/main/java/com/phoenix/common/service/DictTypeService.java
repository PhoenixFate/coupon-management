package com.phoenix.common.service;

import java.util.List;
import java.util.Map;

import com.phoenix.common.domain.DictTypeDO;

/**
 * 字典表
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2017-09-29 18:28:07
 */
public interface DictTypeService {
	
	DictTypeDO get(String dictId);
	
	List<DictTypeDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(DictTypeDO sysDict);
	
	int update(DictTypeDO sysDict);
	
	int remove(String dictId);
	
	int batchRemove(String[] dictIds);

	List<DictTypeDO> listType();
	

}
