package com.phoenix.coupon.dao;

import com.phoenix.coupon.domain.PackageProductRelationDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 活动和产品关系表
 * @author phoenix
 * @email sm516116978@outlook.com
 * @date 2019-07-11 10:44:02
 */
@Mapper
public interface PackageProductRelationDao {

	PackageProductRelationDO get(String relationId);
	
	List<PackageProductRelationDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(PackageProductRelationDO packageProductRelation);
	
	int update(PackageProductRelationDO packageProductRelation);
	
	int remove(String relation_id);
	
	int removeByProductId(String productCode);
	
	int batchRemove(String[] relationIds);
}
