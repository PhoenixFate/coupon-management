package com.phoenix.coupon.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.phoenix.coupon.dao.AccountPackageRelationDao;
import com.phoenix.coupon.domain.AccountPackageRelationDO;
import com.phoenix.coupon.service.AccountPackageRelationService;



@Service
public class AccountPackageRelationServiceImpl implements AccountPackageRelationService {
	@Autowired
	private AccountPackageRelationDao accountPackageRelationDao;
	
	@Override
	public AccountPackageRelationDO get(String relationId){
		return accountPackageRelationDao.get(relationId);
	}
	
	@Override
	public List<AccountPackageRelationDO> list(Map<String, Object> map){
		return accountPackageRelationDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return accountPackageRelationDao.count(map);
	}
	
	@Override
	public int save(AccountPackageRelationDO accountPackageRelation){
		return accountPackageRelationDao.save(accountPackageRelation);
	}
	
	@Override
	public int update(AccountPackageRelationDO accountPackageRelation){
		return accountPackageRelationDao.update(accountPackageRelation);
	}
	
	@Override
	public int remove(String relationId){
		return accountPackageRelationDao.remove(relationId);
	}
	
	@Override
	public int batchRemove(String[] relationIds){
		return accountPackageRelationDao.batchRemove(relationIds);
	}
	
}
