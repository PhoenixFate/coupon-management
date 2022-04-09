package com.phoenix.coupon.dao;

import com.phoenix.coupon.domain.AccountInfoDO;
import com.phoenix.coupon.domain.CouponStatistic;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 账户信息
 * @author phoenix
 * @email sm516116978@outlook.com
 * @date 2019-07-11 10:44:00
 */
@Mapper
public interface AccountInfoDao {

	AccountInfoDO get(String accountNo);
	
	List<AccountInfoDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(AccountInfoDO accountInfo);
	
	int update(AccountInfoDO accountInfo);
	
	int remove(String account_no);
	
	int batchRemove(String[] accountNos);
	
	List<CouponStatistic> liststatistic(Map<String,Object> map);
}
