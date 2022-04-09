package com.phoenix.coupon.dao;

import com.phoenix.coupon.domain.PackageCouponInfoDo;
import com.phoenix.coupon.domain.PackageInfoDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 优惠券套餐信息
 * @author phoenix
 * @email sm516116978@outlook.com
 * @date 2019-07-11 10:44:01
 */
@Mapper
public interface PackageInfoDao {
	
	int setPacCouNums(String couponNums,String couponId,String packageId);
	
	List<PackageCouponInfoDo> getAllPacCouInfo(String packageId);

	List<PackageCouponInfoDo> getPacCouRelationInfoByPacId(String packageId);
	
	int getPacCouRelationInfoCount(Map<String,Object> map);
	
	PackageInfoDO get(String packageId);
	
	List<PackageInfoDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(PackageInfoDO packageInfo);
	
	int update(PackageInfoDO packageInfo);
	
	int remove(String package_id);
	
	int batchRemove(String[] packageIds);
}
