package com.phoenix.coupon.dao;

import com.phoenix.coupon.domain.ConsumeLogCashOutDo;
import com.phoenix.coupon.domain.CouponCashOutDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 医生优惠券核销提现
 * @author phoenix
 * @email sm516116978@outlook.com
 * @date 2019-07-22 10:53:10
 */
@Mapper
public interface CouponCashOutDao {

	CouponCashOutDO get(String id);
	
	List<CouponCashOutDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(CouponCashOutDO couponCashOut);
	
	int update(CouponCashOutDO couponCashOut);
	
	int remove(String id);
	
	int batchRemove(String[] ids);
	
	List<ConsumeLogCashOutDo> getCashOutInfo(String id);
	
	int getCashOutInfoCount(Map<String,Object> map);
}
