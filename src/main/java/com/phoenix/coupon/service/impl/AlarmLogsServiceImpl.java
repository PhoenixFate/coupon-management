package com.phoenix.coupon.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.phoenix.coupon.dao.AlarmLogsDao;
import com.phoenix.coupon.domain.AlarmLogsDO;
import com.phoenix.coupon.service.AlarmLogsService;



@Service
public class AlarmLogsServiceImpl implements AlarmLogsService {
	@Autowired
	private AlarmLogsDao alarmLogsDao;
	
	@Override
	public AlarmLogsDO get(String id){
		return alarmLogsDao.get(id);
	}
	
	@Override
	public List<AlarmLogsDO> list(Map<String, Object> map){
		return alarmLogsDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return alarmLogsDao.count(map);
	}
	
	@Override
	public int save(AlarmLogsDO alarmLogs){
		return alarmLogsDao.save(alarmLogs);
	}
	
	@Override
	public int update(AlarmLogsDO alarmLogs){
		return alarmLogsDao.update(alarmLogs);
	}
	
	@Override
	public int remove(String id){
		return alarmLogsDao.remove(id);
	}
	
	@Override
	public int batchRemove(String[] ids){
		return alarmLogsDao.batchRemove(ids);
	}
	
}
