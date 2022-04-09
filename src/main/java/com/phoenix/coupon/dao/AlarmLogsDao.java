package com.phoenix.coupon.dao;

import com.phoenix.coupon.domain.AlarmLogsDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 风控报警记录
 * @author phoenix
 * @email sm516116978@outlook.com
 * @date 2019-07-24 12:41:16
 */
@Mapper
public interface AlarmLogsDao {

	AlarmLogsDO get(String id);
	
	List<AlarmLogsDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(AlarmLogsDO alarmLogs);
	
	int update(AlarmLogsDO alarmLogs);
	
	int remove(String id);
	
	int batchRemove(String[] ids);
}
