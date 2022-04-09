package com.phoenix.paycenter.service;

import com.phoenix.paycenter.domain.AlarmRulesDO;

import java.util.List;
import java.util.Map;

/**
 * 警报规则表
 * 
 * @author phoenix
 * @email sm516116978@outlook.com
 * @date 2019-04-17 09:40:12
 */
public interface AlarmRulesService {
	
	AlarmRulesDO get(String id);
	
	List<AlarmRulesDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(AlarmRulesDO alarmRules);
	
	int update(AlarmRulesDO alarmRules);
	
	int remove(String id);
	
	int batchRemove(String[] ids);
}
