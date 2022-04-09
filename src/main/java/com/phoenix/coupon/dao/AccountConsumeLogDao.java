package com.phoenix.coupon.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.phoenix.coupon.domain.AccountConsumeLogDO;
import com.phoenix.coupon.domain.CouponStatistic;


/**
 * 账户优惠券消费记录
 * @author phoenix
 * @email sm516116978@outlook.com
 * @date 2019-07-19 14:54:10
 */
@Mapper
public interface AccountConsumeLogDao {

	AccountConsumeLogDO get(String consumeId);
	
	List<AccountConsumeLogDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(AccountConsumeLogDO accountConsumeLog);
	
	int update(AccountConsumeLogDO accountConsumeLog);
	
	int remove(String consume_id);
	
	int batchRemove(String[] consumeIds);
	
	//List<CouponStatistic> liststatistic(Map<String,Object> map);
	
	List<CouponStatistic> returnliststatistic(Map<String,Object> map);
	
	List<CouponStatistic> list7daysConsum(Map<String,Object> map);
	
	List<CouponStatistic> liststatisticAll(Map<String,Object> map);

	List<CouponStatistic> sumConsumeList(Map<String, Object> map);

	List<CouponStatistic> sumCancelList(Map<String, Object> map);

	List<CouponStatistic> sumDoctorsList(Map<String, Object> map);

	List<CouponStatistic> consumeliststatistic(HashMap<String, Object> map);
	
}
