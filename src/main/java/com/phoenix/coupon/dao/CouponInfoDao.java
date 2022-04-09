package com.phoenix.coupon.dao;

import com.phoenix.coupon.domain.CouponInfoDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 优惠券基础信息
 * @author phoenix
 * @email sm516116978@outlook.com
 * @date 2019-07-11 10:44:01
 */
@Mapper
public interface CouponInfoDao {

	CouponInfoDO get(String couponId);
	
	List<CouponInfoDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(CouponInfoDO couponInfo);
	
	int update(CouponInfoDO couponInfo);
	
	int remove(String coupon_id);
	
	int batchRemove(String[] couponIds);

	List<CouponInfoDO> liststatistic(Map<String, Object> map);
}
