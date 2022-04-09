package com.phoenix.coupon.service;

import com.phoenix.coupon.domain.CouponInfoRulesDO;

import java.util.List;
import java.util.Map;

/**
 * 警报规则表
 * 
 * @author tangwei
 * @email 
 * @date 2019-08-14 16:26:01
 */
public interface CouponInfoRulesService {
	
	CouponInfoRulesDO get(String id);
	
	List<CouponInfoRulesDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(CouponInfoRulesDO couponInfoRules);
	
	int update(CouponInfoRulesDO couponInfoRules);
	
	int remove(String id);
	
	int batchRemove(String[] ids);
}
