package com.phoenix.paycenter.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.phoenix.paycenter.dao.AlarmRulesDao;
import com.phoenix.paycenter.domain.AlarmRulesDO;
import com.phoenix.paycenter.service.AlarmRulesService;



@Service
public class AlarmRulesServiceImpl implements AlarmRulesService {
	@Autowired
	private AlarmRulesDao alarmRulesDao;
	
	@Override
	public AlarmRulesDO get(String id){
		return alarmRulesDao.get(id);
	}
	
	@Override
	public List<AlarmRulesDO> list(Map<String, Object> map){
		return alarmRulesDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return alarmRulesDao.count(map);
	}
	
	@Override
	public int save(AlarmRulesDO alarmRules){
		return alarmRulesDao.save(alarmRules);
	}
	
	@Override
	public int update(AlarmRulesDO alarmRules){
		return alarmRulesDao.update(alarmRules);
	}
	
	@Override
	public int remove(String id){
		return alarmRulesDao.remove(id);
	}
	
	@Override
	public int batchRemove(String[] ids){
		return alarmRulesDao.batchRemove(ids);
	}
	
}
