package com.phoenix.common.service;

import java.util.List;
import java.util.Map;

import com.phoenix.common.domain.DictDO;

/**
 * 字典表
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2017-09-29 18:28:07
 */
public interface DictService {
	
	DictDO get(String dictId);
	
	List<DictDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(DictDO sysDict);
	
	int update(DictDO sysDict);
	
	int remove(String dictId);
	
	int batchRemove(String[] dictIds);

	String getGlobalDictValue(String dictName, String dictType);

}
