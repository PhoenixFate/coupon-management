package com.phoenix.coupon.service;

import com.phoenix.coupon.domain.AccountPackageRelationDO;

import java.util.List;
import java.util.Map;

/**
 * 用户服务包关系表
 * 
 * @author tangwei
 * @email 
 * @date 2019-07-26 15:35:06
 */
public interface AccountPackageRelationService {
	
	AccountPackageRelationDO get(String relationId);
	
	List<AccountPackageRelationDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(AccountPackageRelationDO accountPackageRelation);
	
	int update(AccountPackageRelationDO accountPackageRelation);
	
	int remove(String relationId);
	
	int batchRemove(String[] relationIds);
}
