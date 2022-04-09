package com.phoenix.coupon.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.phoenix.common.utils.Constant;
import com.phoenix.common.utils.R;
import com.phoenix.core.redis.JedisUtil;
import com.phoenix.coupon.dao.AccountInfoDao;
import com.phoenix.coupon.domain.AccountInfoDO;
import com.phoenix.coupon.service.AccountInfoService;



@Service
public class AccountInfoServiceImpl implements AccountInfoService {
	@Autowired
	private AccountInfoDao accountInfoDao;
	
	private int timeOut = 48 * 60 * 60; //缓存48小时
	
	@Override
	public AccountInfoDO get(String accountNo){
		return accountInfoDao.get(accountNo);
	}
	
	@Override
	public List<AccountInfoDO> list(Map<String, Object> map){
		return accountInfoDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return accountInfoDao.count(map);
	}
	
	@Override
	public int save(AccountInfoDO accountInfo){
		return accountInfoDao.save(accountInfo);
	}
	
	@Override
	public int update(AccountInfoDO accountInfo){
		return accountInfoDao.update(accountInfo);
	}
	
	@Override
	public int remove(String accountNo){
		return accountInfoDao.remove(accountNo);
	}
	
	@Override
	public int batchRemove(String[] accountNos){
		return accountInfoDao.batchRemove(accountNos);
	}

	@Override
	public R unfreeze(String accountNo) {
		AccountInfoDO accountInfo = new AccountInfoDO();
		accountInfo.setAccountNo(accountNo);
		accountInfo.setEnableFlag(Constant.EnableFlag.ZC);
		if(accountInfoDao.update(accountInfo) > 0) {
			queryAccount(accountNo);
			return R.ok();
		}
		return R.error("用户解冻异常");
		
	}

	@Override
	public R freeze(String accountNo) {
		AccountInfoDO accountInfo = new AccountInfoDO();
		accountInfo.setAccountNo(accountNo);
		accountInfo.setEnableFlag(Constant.EnableFlag.DJ);
		if(accountInfoDao.update(accountInfo) > 0) {
			queryAccount(accountNo);
			return R.ok();
		}
		return R.error("用户解冻异常");
	}
	
	/**
	 * 
	  * 方法描述：查询账户信息
	  * @param 
	  * @return 
	  * @version 1.0
	  * @author phoenix
	  * 2019年7月16日 下午2:24:15
	 */
	protected void queryAccount(String accountNo)
	{
		String redisKey = this.getRedisKey(accountNo);
		if(JedisUtil.exists(redisKey))
		{
			JedisUtil.delete(redisKey);
		}
	}
	
	/**
	 * 
	  * 方法描述：获取缓存的key
	  * @param 
	  * @return 
	  * @version 1.0
	  * @author phoenix
	  * 2019年6月3日 下午6:00:54
	 */
	protected String getRedisKey(String accountNo)
	{
		return "COUPON:ACCOUNT:INFO:" + accountNo;
	}

}
