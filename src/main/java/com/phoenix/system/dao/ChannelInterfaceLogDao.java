package com.phoenix.system.dao;

import com.phoenix.system.domain.ChannelInterfaceLogDO;
import com.phoenix.system.vo.ChannelInterfaceLogStatisticVO;

import java.util.List;
import java.util.Map;

public interface ChannelInterfaceLogDao {
	
	public ChannelInterfaceLogStatisticVO total(Map<String,Object> map);
	
	public List<ChannelInterfaceLogStatisticVO> listStatistic(Map<String,Object> map);
	
	public int countStatistic(Map<String,Object> map);
	
	public List<ChannelInterfaceLogDO> list(Map<String,Object> map);
	
	public int count(Map<String,Object> map);
	
}