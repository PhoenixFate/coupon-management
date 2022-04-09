package com.phoenix.coupon.service;

import com.phoenix.coupon.domain.PackageProductRelationDO;

import java.util.List;
import java.util.Map;

/**
 * 活动和产品关系表
 * 
 * @author phoenix
 * @email sm516116978@outlook.com
 * @date 2019-07-11 10:44:02
 */
public interface PackageProductRelationService {
	
	PackageProductRelationDO get(String relationId);
	
	List<PackageProductRelationDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(PackageProductRelationDO packageProductRelation);
	
	int update(PackageProductRelationDO packageProductRelation);
	
	int remove(String relationId);
	
	int batchRemove(String[] relationIds);
}
