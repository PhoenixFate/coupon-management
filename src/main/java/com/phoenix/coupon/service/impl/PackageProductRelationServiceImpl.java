package com.phoenix.coupon.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.phoenix.coupon.dao.PackageProductRelationDao;
import com.phoenix.coupon.domain.PackageProductRelationDO;
import com.phoenix.coupon.service.PackageProductRelationService;



@Service
public class PackageProductRelationServiceImpl implements PackageProductRelationService {
	@Autowired
	private PackageProductRelationDao packageProductRelationDao;
	
	@Override
	public PackageProductRelationDO get(String relationId){
		return packageProductRelationDao.get(relationId);
	}
	
	@Override
	public List<PackageProductRelationDO> list(Map<String, Object> map){
		return packageProductRelationDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return packageProductRelationDao.count(map);
	}
	
	@Override
	public int save(PackageProductRelationDO packageProductRelation){
		return packageProductRelationDao.save(packageProductRelation);
	}
	
	@Override
	public int update(PackageProductRelationDO packageProductRelation){
		return packageProductRelationDao.update(packageProductRelation);
	}
	
	@Override
	public int remove(String relationId){
		return packageProductRelationDao.remove(relationId);
	}
	
	@Override
	public int batchRemove(String[] relationIds){
		return packageProductRelationDao.batchRemove(relationIds);
	}
	
}
