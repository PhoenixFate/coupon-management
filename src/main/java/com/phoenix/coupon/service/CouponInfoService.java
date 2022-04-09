package com.phoenix.coupon.service;

import com.phoenix.common.domain.OrgDO;
import com.phoenix.coupon.domain.CouponInfoDO;
import com.phoenix.coupon.domain.CouponOrgInfoDo;

import java.util.List;
import java.util.Map;

/**
 * 优惠券基础信息
 * 
 * @author phoenix
 * @email sm516116978@outlook.com
 * @date 2019-07-11 10:44:01
 */
public interface CouponInfoService {
	
	List<CouponOrgInfoDo> getCouponOrgById(String couponId,List<OrgDO> orgList);
	
	Boolean batchSave(List<CouponOrgInfoDo> items);
	
	CouponInfoDO get(String couponId);
	
	List<CouponInfoDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(CouponInfoDO couponInfo);
	
	int update(CouponInfoDO couponInfo);
	
	int remove(String couponId);
	
	int batchRemove(String[] couponIds);
}
