package com.phoenix.coupon.service;

import com.phoenix.common.utils.R;
import com.phoenix.coupon.domain.AccountCouponInfoDO;
import com.phoenix.coupon.domain.CouponStatistic;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 账户优惠券信息
 * 
 * @author phoenix
 * @email sm516116978@outlook.com
 * @date 2019-07-11 10:44:00
 */
public interface AccountCouponInfoService {
	
	AccountCouponInfoDO get(String accountNo);
	
	List<AccountCouponInfoDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(AccountCouponInfoDO accountCouponInfo);
	
	int update(AccountCouponInfoDO accountCouponInfo);
	
	int remove(String accountNo);
	
	int batchRemove(String[] accountNos);

	List<CouponStatistic> liststatistic(HashMap<String, Object> param);

	List<CouponStatistic> liststatisticTotal(HashMap<String, Object> param);

	Map<String, Object> list7days(HashMap<String, Object> param);
	
	R unfreeze(String couponNo);

	R consume(String couponNo);

	R freeze(String couponNo);
}
