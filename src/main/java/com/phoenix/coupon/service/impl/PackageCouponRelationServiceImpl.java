package com.phoenix.coupon.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.phoenix.coupon.dao.PackageCouponRelationDao;
import com.phoenix.coupon.domain.PackageCouponRelationDO;
import com.phoenix.coupon.service.PackageCouponRelationService;



@Service
public class PackageCouponRelationServiceImpl implements PackageCouponRelationService {
	@Autowired
	private PackageCouponRelationDao packageCouponRelationDao;
	
	@Override
	public PackageCouponRelationDO get(String relationId){
		return packageCouponRelationDao.get(relationId);
	}
	
	@Override
	public List<PackageCouponRelationDO> list(Map<String, Object> map){
		return packageCouponRelationDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return packageCouponRelationDao.count(map);
	}
	
	@Override
	public int save(PackageCouponRelationDO packageCouponRelation){
		return packageCouponRelationDao.save(packageCouponRelation);
	}
	
	@Override
	public int update(PackageCouponRelationDO packageCouponRelation){
		return packageCouponRelationDao.update(packageCouponRelation);
	}
	
	@Override
	public int remove(String relationId){
		return packageCouponRelationDao.remove(relationId);
	}
	
	@Override
	public int batchRemove(String[] relationIds){
		return packageCouponRelationDao.batchRemove(relationIds);
	}
	
}
