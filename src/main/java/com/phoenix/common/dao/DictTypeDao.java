package com.phoenix.common.dao;

import com.phoenix.common.domain.DictTypeDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface DictTypeDao {

	public DictTypeDO get(String dictId);
	
	public List<DictTypeDO> list(Map<String,Object> map);
	
	public int count(Map<String,Object> map);
	
	public int save(DictTypeDO dict);
	
	public int update(DictTypeDO dict);
	
	public int remove(String dict_id);
	
	public int batchRemove(String[] dictIds);

}
