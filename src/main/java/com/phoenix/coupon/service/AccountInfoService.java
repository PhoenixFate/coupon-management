package com.phoenix.coupon.service;

import com.phoenix.common.utils.R;
import com.phoenix.coupon.domain.AccountInfoDO;

import java.util.List;
import java.util.Map;

/**
 * 账户信息
 * 
 * @author phoenix
 * @email sm516116978@outlook.com
 * @date 2019-07-11 10:44:00
 */
public interface AccountInfoService {
	
	AccountInfoDO get(String accountNo);
	
	List<AccountInfoDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(AccountInfoDO accountInfo);
	
	int update(AccountInfoDO accountInfo);
	
	int remove(String accountNo);
	
	int batchRemove(String[] accountNos);

	R unfreeze(String accountNo);

	R freeze(String accountNo);

}
