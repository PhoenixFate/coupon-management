package com.phoenix.common.dao;

import com.phoenix.common.domain.DictDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface DictDao {

	public DictDO get(String dictId);
	
	public List<DictDO> list(Map<String,Object> map);
	
	public int count(Map<String,Object> map);
	
	public int save(DictDO dict);
	
	public int update(DictDO dict);
	
	public int remove(String dict_id);
	
	public int batchRemove(String[] dictIds);

	List<DictDO> listType();

	int getTypeCout(String[] typeIds);
}
