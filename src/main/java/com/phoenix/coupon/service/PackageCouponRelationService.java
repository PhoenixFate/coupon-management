package com.phoenix.coupon.service;

import com.phoenix.coupon.domain.PackageCouponRelationDO;

import java.util.List;
import java.util.Map;

/**
 * 活动和优惠券关系表
 * 
 * @author phoenix
 * @email sm516116978@outlook.com
 * @date 2019-07-11 10:44:01
 */
public interface PackageCouponRelationService {
	
	PackageCouponRelationDO get(String relationId);
	
	List<PackageCouponRelationDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(PackageCouponRelationDO packageCouponRelation);
	
	int update(PackageCouponRelationDO packageCouponRelation);
	
	int remove(String relationId);
	
	int batchRemove(String[] relationIds);
}
