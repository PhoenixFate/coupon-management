package com.phoenix.coupon.dao;

import com.phoenix.coupon.domain.PackageCouponRelationDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 活动和优惠券关系表
 * @author phoenix
 * @email sm516116978@outlook.com
 * @date 2019-07-11 10:44:01
 */
@Mapper
public interface PackageCouponRelationDao {

	PackageCouponRelationDO get(String relationId);
	
	List<PackageCouponRelationDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(PackageCouponRelationDO packageCouponRelation);
	
	int update(PackageCouponRelationDO packageCouponRelation);
	
	int remove(String relation_id);
	
	int removeByPacId(String packageId);
	
	int batchRemove(String[] relationIds);
}
