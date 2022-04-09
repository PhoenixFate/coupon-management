package com.phoenix.coupon.service;

import com.phoenix.coupon.domain.CouponInfoDO;
import com.phoenix.coupon.domain.PackageAndCoupDo;
import com.phoenix.coupon.domain.PackageCouponInfoDo;
import com.phoenix.coupon.domain.PackageInfoDO;

import java.util.List;
import java.util.Map;

/**
 * 优惠券套餐信息
 * 
 * @author phoenix
 * @email sm516116978@outlook.com
 * @date 2019-07-11 10:44:01
 */
public interface PackageInfoService {
	
	 List<PackageCouponInfoDo> getPacCouRelationInfoByPacId(String params,List<CouponInfoDO> couponList);
	
	 Boolean batchSave(List<PackageAndCoupDo> items);
	 
	 int getPacCouRelationInfoCount(Map<String,Object> map);
	
	PackageInfoDO get(String packageId);
	
	List<PackageInfoDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(PackageInfoDO packageInfo);
	
	int update(PackageInfoDO packageInfo);
	
	int remove(String packageId);
	
	int batchRemove(String[] packageIds);
}
