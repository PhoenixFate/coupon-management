package com.phoenix.coupon.dao;

import com.phoenix.coupon.domain.AccountCouponInfoDO;
import com.phoenix.coupon.domain.CouponStatistic;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 账户优惠券信息
 * @author phoenix
 * @email sm516116978@outlook.com
 * @date 2019-07-11 10:44:00
 */
@Mapper
public interface AccountCouponInfoDao {

	AccountCouponInfoDO get(String accountNo);
	
	List<AccountCouponInfoDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(AccountCouponInfoDO accountCouponInfo);
	
	int update(AccountCouponInfoDO accountCouponInfo);
	
	int remove(String account_no);
	
	int batchRemove(String[] accountNos);

	List<CouponStatistic> liststatistic(HashMap<String, Object> map);
	
	List<CouponStatistic> list7days(Map<String,Object> map);
	
	List<CouponStatistic> liststatisticCount(Map<String,Object> map);
	
	List<CouponStatistic> sumSendList(Map<String, Object> param);
	
	int preConsumeCancel(AccountCouponInfoDO accountCouponInfo);

	int freeze(AccountCouponInfoDO info);
	
	
}
