package com.phoenix.coupon.service;

import java.util.List;
import java.util.Map;

import com.phoenix.common.utils.R;
import com.phoenix.coupon.domain.AccountConsumeLogDO;

/**
 * 账户优惠券消费记录
 * 
 * @author phoenix
 * @email sm516116978@outlook.com
 * @date 2019-07-22 09:24:02
 */
public interface AccountConsumeLogService {
	
	AccountConsumeLogDO get(String consumeId);
	
	List<AccountConsumeLogDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(AccountConsumeLogDO accountConsumeLog);
	
	int update(AccountConsumeLogDO accountConsumeLog);
	
	int remove(String consumeId);
	
	int batchRemove(String[] consumeIds);

	R refund(String id);

	R cashOut(Map<String, Object> param);
}
