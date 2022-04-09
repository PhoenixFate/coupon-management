package com.phoenix.coupon.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.phoenix.core.redis.JedisUtil;
import com.phoenix.coupon.dao.CouponInfoRulesDao;
import com.phoenix.coupon.domain.CouponInfoRulesDO;
import com.phoenix.coupon.service.CouponInfoRulesService;



@Service
public class CouponInfoRulesServiceImpl implements CouponInfoRulesService {
	@Autowired
	private CouponInfoRulesDao couponInfoRulesDao;
	
	@Override
	public CouponInfoRulesDO get(String id){
		return couponInfoRulesDao.get(id);
	}
	
	@Override
	public List<CouponInfoRulesDO> list(Map<String, Object> map){
		return couponInfoRulesDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return couponInfoRulesDao.count(map);
	}
	
	@Override
	public int save(CouponInfoRulesDO couponInfoRules){
		int count = couponInfoRulesDao.save(couponInfoRules);
		if (count > 0) {
			cleanCouponRule(couponInfoRules.getCouponId());
		}
		return count;
	}
	
	@Override
	public int update(CouponInfoRulesDO couponInfoRules){
		int count = couponInfoRulesDao.update(couponInfoRules);
		if (count > 0) {
			cleanCouponRule(couponInfoRules.getCouponId());
		}
		return count;
	}
	
	@Override
	public int remove(String id){
		CouponInfoRulesDO couponInfoRules = couponInfoRulesDao.get(id);
		int count = couponInfoRulesDao.remove(id);
		if (count > 0) {
			cleanCouponRule(couponInfoRules.getCouponId());
		}
		return count;
	}
	
	@Override
	public int batchRemove(String[] ids){
		return couponInfoRulesDao.batchRemove(ids);
	}
	
	
	
	
	/**
	 * 清空规则缓存
	 * @author tw
	 * @param couponId
	 * 2019年8月16日
	 */
	protected void cleanCouponRule(String couponId)
	{
		String redisKey = "COUPON:INFO:rule:" + couponId;
		if(JedisUtil.exists(redisKey))
		{
			JedisUtil.delete(redisKey); 
		}
	}
}
