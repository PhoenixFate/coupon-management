package com.phoenix.coupon.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.ImmutableMap;
import com.phoenix.common.utils.Constant;
import com.phoenix.common.utils.QueryParams;
import com.phoenix.common.utils.R;
import com.phoenix.common.utils.ShiroUtils;
import com.phoenix.core.exception.ServiceMsg;
import com.phoenix.core.redis.JedisUtil;
import com.phoenix.core.utils.UUIDUtil;
import com.phoenix.coupon.dao.AccountConsumeLogDao;
import com.phoenix.coupon.dao.AccountCouponInfoDao;
import com.phoenix.coupon.dao.CouponCashOutDao;
import com.phoenix.coupon.dao.DoctorInfoDao;
import com.phoenix.coupon.domain.AccountConsumeLogDO;
import com.phoenix.coupon.domain.AccountCouponInfoDO;
import com.phoenix.coupon.domain.CouponCashOutDO;
import com.phoenix.coupon.domain.DoctorInfoDO;
import com.phoenix.coupon.service.AccountConsumeLogService;




@Service
public class AccountConsumeLogServiceImpl implements AccountConsumeLogService {
	@Autowired
	private AccountConsumeLogDao accountConsumeLogDao;
	
	@Autowired
	private AccountCouponInfoDao accountCouponInfoDao;
	
	@Autowired
	private CouponCashOutDao couponCashOutDao;
	
	@Autowired DoctorInfoDao doctorInfoDao;
	
	@Override
	public AccountConsumeLogDO get(String consumeId){
		
		AccountConsumeLogDO accountConsumeLogDO = null;
		try {
			accountConsumeLogDO = accountConsumeLogDao.get(consumeId);
			
			//改变数据库时间为对应格式
		    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			
			accountConsumeLogDO.setFormatConsumeTime( sdf.format(accountConsumeLogDO.getConsumeTime()) ) ;
			accountConsumeLogDO.setFormatRefundTime(sdf.format(accountConsumeLogDO.getRefundTime()));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return accountConsumeLogDO;
	}
	
	@Override
	public List<AccountConsumeLogDO> list(Map<String, Object> map){
		return accountConsumeLogDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return accountConsumeLogDao.count(map);
	}
	
	@Override
	public int save(AccountConsumeLogDO accountConsumeLog){
		
		try {
			//改变数据库时间为对应格式
		    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		    
		   accountConsumeLog.setConsumeTime(sdf.parse(accountConsumeLog.getFormatConsumeTime()));
		   accountConsumeLog.setRefundTime(sdf.parse(accountConsumeLog.getFormatRefundTime()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return accountConsumeLogDao.save(accountConsumeLog);
	}
	
	@Override
	public int update(AccountConsumeLogDO accountConsumeLog){
		
		try {
			//改变数据库时间为对应格式
		    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		    
		   accountConsumeLog.setConsumeTime(sdf.parse(accountConsumeLog.getFormatConsumeTime()));
		   accountConsumeLog.setRefundTime(sdf.parse(accountConsumeLog.getFormatRefundTime()));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return accountConsumeLogDao.update(accountConsumeLog);
	}
	
	@Override
	public int remove(String channelCode){
		return accountConsumeLogDao.remove(channelCode);
	}
	
	@Override
	public int batchRemove(String[] consumeIds){
		return accountConsumeLogDao.batchRemove(consumeIds);
	}

	@Override
	public R refund(String id) {
		AccountConsumeLogDO consumeLogDO =  accountConsumeLogDao.get(id);
		if (null == consumeLogDO) {
			return R.error("撤销的消费记录不存在,请检查");
		}
		if (!ServiceMsg.SUCCESS.equals(consumeLogDO.getConsumeStatus())) {
			return R.error("未核销成功的的消费记录不可撤销");
		}
		if (ServiceMsg.SUCCESS.equals(consumeLogDO.getCashOutFlag())) {
			return R.error("已提现的消费信息不可撤销");
		}
		// 修改用户优惠券表
		List<AccountCouponInfoDO> couponList = accountCouponInfoDao.list(ImmutableMap.of("couponNo", consumeLogDO.getCouponNo()));
		if (null == couponList || couponList.isEmpty() || couponList.size() >1) {
			return R.error("用户优惠券信息异常,撤销失败,请联系管理员");
		}
		AccountCouponInfoDO couponInfoDO = couponList.get(0);
		if(!Constant.CouponStatus.YSY.equals(couponInfoDO.getCouponStatus())) {
			return R.error("优惠券状态无效，请核实");
		}
		consumeLogDO.setRefundCollector(ShiroUtils.getUser().getUserName());
		consumeLogDO.setRefundChannelCode(consumeLogDO.getChannelCode());
		consumeLogDO.setRefundFlag(Constant.RefundFlag.yt);
		consumeLogDO.setConsumeStatus(Constant.ConsumeStatus.cx);
		consumeLogDO.setRefundTime(new Date());
		// 修改消费记录表撤销状态
		if (accountConsumeLogDao.update(consumeLogDO) > 0) {
			couponInfoDO.setCouponStatus(Constant.CouponStatus.WSY);
			couponInfoDO.setReturnFlag(Constant.RefundFlag.yt);
			couponInfoDO.setReturnTime(new Date());
			couponInfoDO.setVersion(UUIDUtil.getUUID());
			if(this.accountCouponInfoDao.update(couponInfoDO) > 0){
				refreshAccountCouponInfoDO(couponInfoDO);
				return R.ok("撤销成功");
			}
			return R.error("优惠券状态非法， 请核对");
			
		}
		return R.error("撤销失败， 请核对");
	}
	/**
	 * 
	  * 方法描述：刷新优惠券信息
	  * @param 
	  * @return 
	  * @version 1.0
	  * @author phoenix
	  * 2019年7月18日 下午1:31:53
	 */
	protected void refreshAccountCouponInfoDO(AccountCouponInfoDO info)
	{
		String redisKey = "COUPON:INFO:NO:" + info.getCouponNo();
		try {
			if (JedisUtil.exists(redisKey))
			JedisUtil.delete(redisKey);
		} catch (Exception e) {
		}
	}
	@Override
	public R cashOut(Map<String, Object> param) {
		
		QueryParams.initQueryTime(param);
		List<AccountConsumeLogDO> consumeList = accountConsumeLogDao.list(param);
		if (null == consumeList || consumeList.isEmpty()) {
			return R.error("没有需要提现的消费,提现失败");
		}
		param.put("docCode", param.get("belogtoDoctor"));
		param.put("hosCode", param.get("orgCode"));
		List<DoctorInfoDO> doctorList = doctorInfoDao.list(param);
		if (null == doctorList || doctorList.isEmpty() || doctorList.size() > 1) {
			return R.error("医生信息异常,请联系管理员");
		}
		DoctorInfoDO doctor = doctorList.get(0);
		CouponCashOutDO couponCashOut = new CouponCashOutDO();
		String id = UUIDUtil.getUUID();
		couponCashOut.setId(id);
		couponCashOut.setCashOutNums(consumeList.size());
		couponCashOut.setCashOutOperator(ShiroUtils.getUser().getUserName());
		couponCashOut.setCashOutTime(new Date());
		couponCashOut.setDoctorCode(doctor.getDocCode());
		couponCashOut.setDoctorId(doctor.getDocApplyId());
		couponCashOut.setDoctorName(doctor.getDocName());
		couponCashOut.setOrgCode(doctor.getHosCode());
		// 入库提现记录表
		for (AccountConsumeLogDO consumeLog : consumeList) {
			consumeLog.setCashOutFlag(Constant.CashOutFlag.ytx);
			consumeLog.setCashOutFlow(id);
			consumeLog.setCashOutTime(new Date());
			try {
				accountConsumeLogDao.update(consumeLog);
			}catch (Exception e) {
				e.getMessage();
			}
		}
		if (couponCashOutDao.save(couponCashOut) > 0) {
			return R.ok();
		}
		return R.error("提现异常，请联系管理员");
	}
	
}
