package com.phoenix.coupon.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.phoenix.coupon.dao.CouponCashOutDao;
import com.phoenix.coupon.domain.ConsumeLogCashOutDo;
import com.phoenix.coupon.domain.CouponCashOutDO;
import com.phoenix.coupon.service.CouponCashOutService;

import java.util.List;
import java.util.Map;



@Service
public class CouponCashOutServiceImpl implements CouponCashOutService {
	@Autowired
	private CouponCashOutDao couponCashOutDao;
	
	@Override
	public CouponCashOutDO get(String id){
		return couponCashOutDao.get(id);
	}
	
	@Override
	public List<CouponCashOutDO> list(Map<String, Object> map){
		return couponCashOutDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return couponCashOutDao.count(map);
	}
	
	@Override
	public int save(CouponCashOutDO couponCashOut){
		return couponCashOutDao.save(couponCashOut);
	}
	
	@Override
	public int update(CouponCashOutDO couponCashOut){
		return couponCashOutDao.update(couponCashOut);
	}
	
	@Override
	public int remove(String id){
		return couponCashOutDao.remove(id);
	}
	
	@Override
	public int batchRemove(String[] ids){
		return couponCashOutDao.batchRemove(ids);
	}

	@Override
	public List<ConsumeLogCashOutDo> getCashOutInfo(String id) {
			return	 couponCashOutDao.getCashOutInfo(id);
	}

	@Override
	public int getCashOutInfoCount(Map<String, Object> map) {
		return couponCashOutDao.getCashOutInfoCount(map);
	}
	
}
