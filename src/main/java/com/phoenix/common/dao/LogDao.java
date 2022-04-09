package com.phoenix.common.dao;

import com.phoenix.common.domain.LogDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface LogDao {

	public LogDO get(String logId);
	
	public List<LogDO> list(Map<String,Object> map);
	
	public int count(Map<String,Object> map);
	
	public int save(LogDO log);
	
}
