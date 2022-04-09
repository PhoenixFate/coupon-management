package com.phoenix.coupon.dao;

import com.phoenix.coupon.domain.AccountPackageRelationDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 用户服务包关系表
 * @author tangwei
 * @email 
 * @date 2019-07-26 15:35:06
 */
@Mapper
public interface AccountPackageRelationDao {

	AccountPackageRelationDO get(String relationId);
	
	List<AccountPackageRelationDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(AccountPackageRelationDO accountPackageRelation);
	
	int update(AccountPackageRelationDO accountPackageRelation);
	
	int remove(String relation_id);
	
	int batchRemove(String[] relationIds);
}
