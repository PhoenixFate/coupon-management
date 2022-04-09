package com.phoenix.coupon.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.phoenix.common.service.DictService;
import com.phoenix.common.utils.Constant;
import com.phoenix.common.utils.R;
import com.phoenix.core.http.HttpUtils;
import com.phoenix.core.redis.JedisUtil;
import com.phoenix.core.utils.DateUtil;
import com.phoenix.core.utils.MD5Util;
import com.phoenix.core.utils.SignValidUtils;
import com.phoenix.core.utils.UUIDUtil;
import com.phoenix.coupon.dao.AccountConsumeLogDao;
import com.phoenix.coupon.dao.AccountCouponInfoDao;
import com.phoenix.coupon.dao.AccountInfoDao;
import com.phoenix.coupon.dao.CouponInfoDao;
import com.phoenix.coupon.domain.AccountCouponInfoDO;
import com.phoenix.coupon.domain.CouponInfoDO;
import com.phoenix.coupon.domain.CouponStatistic;
import com.phoenix.coupon.entity.ConsumeReq;
import com.phoenix.coupon.service.AccountCouponInfoService;
import com.phoenix.system.dao.ReserveChannelDao;
import com.phoenix.system.domain.ReserveChannelDO;



@Service
public class AccountCouponInfoServiceImpl implements AccountCouponInfoService {
	@Autowired
	private AccountCouponInfoDao accountCouponInfoDao;
	
	@Autowired
	private AccountConsumeLogDao accountConsumeLogDao;
	
	@Autowired
	private AccountInfoDao accountInfoDao;
	
	@Autowired
	private CouponInfoDao couponInfoDao;
	
	@Autowired
	private DictService dictService;
	
	@Autowired
	private ReserveChannelDao reserveChannelDao;
	
	@Override
	public AccountCouponInfoDO get(String accountNo){
		return accountCouponInfoDao.get(accountNo);
	}
	
	@Override
	public List<AccountCouponInfoDO> list(Map<String, Object> map){
		return accountCouponInfoDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return accountCouponInfoDao.count(map);
	}
	
	@Override
	public int save(AccountCouponInfoDO accountCouponInfo){
		return accountCouponInfoDao.save(accountCouponInfo);
	}
	
	@Override
	public int update(AccountCouponInfoDO accountCouponInfo){
		return accountCouponInfoDao.update(accountCouponInfo);
	}
	
	@Override
	public int remove(String accountNo){
		return accountCouponInfoDao.remove(accountNo);
	}
	
	@Override
	public int batchRemove(String[] accountNos){
		return accountCouponInfoDao.batchRemove(accountNos);
	}

	@Override
	public List<CouponStatistic> liststatistic(HashMap<String, Object> map) {
		List<CouponStatistic> list = new ArrayList<>();
		// 发券
		List<CouponStatistic> sendlist = accountCouponInfoDao.liststatistic(map);
		// 核销
		List<CouponStatistic> consumeList  = accountConsumeLogDao.consumeliststatistic(map);
		// 测校
		List<CouponStatistic> returnList  = accountConsumeLogDao.returnliststatistic(map);
		
		CouponStatistic coupon = null;
		if (null != sendlist && !sendlist.isEmpty()) {
			coupon = sendlist.get(0);
			coupon.setType("发券数");
			list.add(coupon);
		}
		if (null != consumeList && !consumeList.isEmpty()) {
			coupon = consumeList.get(0);
			coupon.setType("核销数");
			list.add(coupon);
		}
		if (null != returnList && !returnList.isEmpty()) {
			coupon = returnList.get(0);
			coupon.setType("撤销数");
			list.add(coupon);
		}
		
		List<CouponStatistic> accountList  = accountInfoDao.liststatistic(map);
		if (null != accountList && !accountList.isEmpty()) {
			coupon = accountList.get(0);
			coupon.setType("用户数");
			list.add(coupon);
		}
		return list;
	}

	/**
	 * 优惠券统计列表
	 * @author tw
	 * @param map
	 * @return
	 * 2019年7月18日
	 */
	@Override
	public List<CouponStatistic> liststatisticTotal(HashMap<String, Object> map) {
		
		Map<String, Integer> countMap = liststatisticCount(map);
		
		List<CouponStatistic> sumList  = accountConsumeLogDao.liststatisticAll(map);
		
		Map<String, String> couponMap = couponList(map);
		
		for (CouponStatistic coupon : sumList) {
			coupon.setCouponName(couponMap.get(coupon.getCouponId()));
			coupon.setTotalNums(0);
			if (countMap.containsKey(coupon.getCouponId())) {
				coupon.setTotalNums(countMap.get(coupon.getCouponId()));
				Double percent = (new Double(coupon.getNums()) / new Double(coupon.getTotalNums()) * 100d);
				coupon.setPercent(percent.intValue());
			}
		}
		return sumList;
	}
	
	/**
	 * 优惠券统计
	 * @author tw
	 * @param map
	 * @return
	 * 2019年7月18日
	 */
	private Map<String, Integer> liststatisticCount(Map<String,Object> map)
	{
		List<CouponStatistic> countlist = accountCouponInfoDao.liststatisticCount(map);
		
		Map<String, Integer> couponCountMap = new HashMap<String, Integer>();
		
		for(CouponStatistic stat : countlist)
		{
			couponCountMap.put(stat.getCouponId(), stat.getTotalNums());
		}
		
		return couponCountMap;
		
	}
	
	/**
	 * 优惠券
	 * @author tw
	 * @param map
	 * @return
	 * 2019年7月18日
	 */
	private Map<String, String> couponList(Map<String,Object> map)
	{
		String redisKey = "coupon:info:all:key";
		Map<String, String> value = new HashMap<>();
    	if(JedisUtil.exists(redisKey)) {
    		String redisStr = JedisUtil.get(redisKey);
    		value = JSON.parseObject(redisStr, HashMap.class);
    		return value;
    	}
    	
    	List<CouponInfoDO> infoList = couponInfoDao.liststatistic(map);
    	value = infoList.stream().collect(Collectors.toMap(CouponInfoDO::getCouponId, CouponInfoDO::getCouponName, (key1, key2) -> key2));
		
		JedisUtil.set(redisKey, JSON.toJSONString(value), 10 * 50); //缓存5分钟
		
		return value;
	}

	@Override
	public Map<String, Object> list7days(HashMap<String, Object> map) {
		Calendar cal = Calendar.getInstance();
		List<String> dateList = new ArrayList<String>();
		int days = 7;
		cal.add(Calendar.DAY_OF_YEAR, -days);
		List<Integer> sendList = new ArrayList<Integer>();
		List<Double> avgsendList = new ArrayList<Double>();
		List<Integer> consumeList = new ArrayList<Integer>();
		List<Double> avgconsumeList = new ArrayList<Double>();
		for(int i = 0; i <= days; i++)
		{
			dateList.add(DateUtil.getDateStr(cal.getTime()));
			sendList.add(0);
			avgsendList.add(0d);
			consumeList.add(0);
			avgconsumeList.add(0d);
			cal.add(Calendar.DAY_OF_YEAR, 1);
		}
		
		
		cal = Calendar.getInstance();
		map.put("endTime", DateUtil.dateEndStr(cal.getTime()));
		cal.add(Calendar.DAY_OF_YEAR, - days);
		map.put("startTime", DateUtil.dateStartStr(cal.getTime()));
		// 发券
		List<CouponStatistic> list = accountCouponInfoDao.list7days(map);
		
		String start = DateUtil.formatDate(cal.getTime(), "yyyy-MM-dd") + " 00:00:00";
		Integer totalNums = 0;
		for(CouponStatistic st : list ) {
			totalNums += st.getNums();
		}
		double avgNums = totalNums / (days + 1);
		for(CouponStatistic st : list ) {
			try {
				int diff = DateUtil.daysBetween(start, st.getTradeDate() + " 00:00:00");
				if(diff < 0 || diff >= sendList.size()) {
					continue;
				}
				sendList.set(diff, st.getNums());
				avgsendList.set(diff, avgNums);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		// 核销
		List<CouponStatistic> consumList = accountConsumeLogDao.list7daysConsum(map);
		
		totalNums = 0;
		for(CouponStatistic st : list ) {
			totalNums += st.getNums();
		}
		days = 7;
		avgNums = totalNums / (days + 1);
		
		for(CouponStatistic st : consumList ) {
			try {
				int diff = DateUtil.daysBetween(start, st.getTradeDate() + " 00:00:00");
				if(diff < 0 || diff >= sendList.size()) {
					continue;
				}
				consumeList.set(diff, st.getNums());
				avgconsumeList.set(diff, avgNums);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("dateList", dateList);
		result.put("sendList", sendList);
		result.put("consumeList", consumeList);
		result.put("avgsendList", avgsendList);
		result.put("avgconsumeList", avgconsumeList);
		
		return result;
	}
	
	@Override
	public R unfreeze(String couponNo) {
		if(StringUtils.isAnyEmpty(couponNo)) {
			return R.error("解冻的优惠券无效");
		}
		
		AccountCouponInfoDO info = accountCouponInfoDao.get(couponNo);
		if(info == null)
			return R.error("优惠券无效");
		if(!Constant.CouponStatus.YDJ.equals(info.getCouponStatus()))
		{
			return R.error("优惠券状态无效，请核实");
		}
		
		info.setCouponStatus(Constant.CouponStatus.WSY);
		//info.setThirdOrdernumber(req.getOrderNumber());
		info.setVersion(UUIDUtil.getUUID());
		info.setConsumeTime(new Date());
		if(this.accountCouponInfoDao.preConsumeCancel(info) > 0)
		{
			refreshAccountCouponInfoDO(info);
			return R.ok("解冻成功");
		}
		return R.error("优惠券状态非法， 请核对");
	}
	
	
	@Override
	public R freeze(String couponNo) {
		if(StringUtils.isAnyEmpty(couponNo)) {
			return R.error("冻结的优惠券无效");
		}
		
		AccountCouponInfoDO info = accountCouponInfoDao.get(couponNo);
		if(info == null)
			return R.error("优惠券无效");
		if(!Constant.CouponStatus.WSY.equals(info.getCouponStatus()))
		{
			return R.error("优惠券状态无效，请核实");
		}
		
		info.setCouponStatus(Constant.CouponStatus.YDJ);
		//info.setThirdOrdernumber(req.getOrderNumber());
		info.setVersion(UUIDUtil.getUUID());
		info.setConsumeTime(new Date());
		if(this.accountCouponInfoDao.freeze(info) > 0)
		{
			refreshAccountCouponInfoDO(info);
			return R.ok("解冻成功");
		}
		return R.error("优惠券状态非法， 请核对");
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
			if(JedisUtil.exists(redisKey))
			{
				JedisUtil.delete(redisKey); 
			}
		} catch (Exception e) {
		}
		
		
	}

	/**
	 * 优惠券核销
	 * @author tw
	 * @param couponNo
	 * @return
	 * 2019年8月13日
	 */
	@Override
	public R consume(String couponNo) {
		if(StringUtils.isAnyEmpty(couponNo)) {
			return R.error("解冻的优惠券无效");
		}
		
		AccountCouponInfoDO info = accountCouponInfoDao.get(couponNo);
		if(info == null)
			return R.error("优惠券无效");
		if(!Constant.CouponStatus.YDJ.equals(info.getCouponStatus()))
		{
			return R.error("优惠券状态无效，请核实");
		}
		
		try {
			// 调用接口做核销
			String payUrl = dictService.getGlobalDictValue("COUPON_SERVER_URL", "XTCS");
			if(payUrl == null || StringUtils.isEmpty(payUrl)) {
				return R.error("没有配置核销接口地址COUPON_SERVER_URL");
			}
			if(payUrl.endsWith("/")) {
				payUrl = payUrl.substring(0, payUrl.length() - 1);
			}
			
			Map<String, Object> channelParam = new HashMap<String, Object>();
			channelParam.put("channelCode", info.getChannelCode());
			ReserveChannelDO channelDo = reserveChannelDao.getByChannelCode(channelParam);
			if(channelDo == null) {
				return R.error(info.getChannelCode() + " 该渠道码不存在，无法核销");
			}
			
			ConsumeReq req = new ConsumeReq();
			req.setChannelCode(channelDo.getChannelCode());
			req.setOrgCode(info.getBelogtoOrgcode());
			req.setAccountNo(info.getAccountNo());
			req.setCollectorNo(info.getBelogtoDoctor());
			req.setCouponNo(couponNo);
			req.setOrderNumber(UUIDUtil.getUUID());
			String sign = this.getSign(JSON.toJSONString(req), channelDo.getAccessKeySecret());
			req.setSign(sign);
			String result = HttpUtils.sendPost(payUrl + "/coupon/consume", JSON.toJSONString(req));
			JSONObject json = JSON.parseObject(result);
			
			if (StringUtils.isNotEmpty(result) && 1 == json.getInteger("code")) {
				return R.ok();
			}else {
				return R.error(json.getString("msg"));
			}
				
		} catch (Exception e) {
			e.printStackTrace();
			return R.error("核销异常，请刷新后查看结果");
		}
		
	}
	

	
	private String getSign(String jsonStr, String keySecret) {
		String stringA = SignValidUtils.generateSignStr(jsonStr) + "&key=" + keySecret;
		String rightSign = MD5Util.MD5Encode(stringA, "UTF-8");
		return rightSign;
	}
}
