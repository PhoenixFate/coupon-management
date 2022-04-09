package com.phoenix.coupon.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.phoenix.common.utils.ClassUtil;
import com.phoenix.common.utils.QueryParams;
import com.phoenix.common.utils.R;
import com.phoenix.coupon.dao.AccountConsumeLogDao;
import com.phoenix.coupon.dao.AccountCouponInfoDao;
import com.phoenix.coupon.dao.CouponInfoDao;
import com.phoenix.coupon.domain.CouponInfoDO;
import com.phoenix.coupon.domain.CouponStatistic;
import com.phoenix.coupon.service.StatisticService;



@Service
public class StatisticServiceImpl implements StatisticService {
	@Autowired
	private AccountCouponInfoDao accountCouponInfoDao;
	
	@Autowired
	private AccountConsumeLogDao accountConsumeLogDao;
	
	@Autowired
	private CouponInfoDao couponInfoDao;
	
	@Override
	public R sumSendList(Map<String, Object> map) {
		
		List<Object> resultList = new ArrayList<>();
		try {
			QueryParams.initQueryTime(map);
			Map<String, Object> resultMap = new HashMap<String, Object>();
			List<CouponStatistic> statisticList = accountCouponInfoDao.sumSendList(map);
			resultList = initStatistic(map, resultList, resultMap, statisticList);
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return R.ok().put("count", resultList.size()).put("data", resultList);
		
	}

	@Override
	public R sumConsumeList(Map<String, Object> map) {
		List<Object> resultList = new ArrayList<>();
		try {
			QueryParams.initQueryTime(map);
			Map<String, Object> resultMap = new HashMap<String, Object>();
			List<CouponStatistic> statisticList = accountConsumeLogDao.sumConsumeList(map);
			resultList = initStatistic(map, resultList, resultMap, statisticList);
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return R.ok().put("count", resultList.size()).put("data", resultList);
	}

	@Override
	public R sumCancelList(Map<String, Object> map) {
		List<Object> resultList = new ArrayList<>();
		try {
			QueryParams.initQueryTime(map);
			Map<String, Object> resultMap = new HashMap<String, Object>();
			List<CouponStatistic> statisticList = accountConsumeLogDao.sumCancelList(map);
			resultList = initStatistic(map, resultList, resultMap, statisticList);
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return R.ok().put("count", resultList.size()).put("data", resultList);
	}

	@Override
	public R sumDoctorsList(Map<String, Object> map) {
		List<Object> resultList = new ArrayList<>();
		try {
			QueryParams.initQueryTime(map);
			Map<String, Object> resultMap = new HashMap<String, Object>();
			Set<String> keySet = new HashSet<>();
			List<CouponStatistic> statisticList = accountConsumeLogDao.sumDoctorsList(map);
			if (null != statisticList && !statisticList.isEmpty()) {
				
				map.put("couponStatusList", Arrays.asList("1,2".split(",")));
				List<CouponInfoDO> couponList = couponInfoDao.list(map);
				
				for (CouponInfoDO coupon : couponList) {
					// 设置默认已兑，未兑 属性名
					keySet.add(coupon.getCouponId()+"0");
					keySet.add(coupon.getCouponId()+"1");
				}
				
				
				Map<String, Integer> sumCountMap = new HashMap<>();
				Object obj = null;
				HashMap<String,Object> addMap = null;
			    HashMap<String,Object> addValMap = null;
			    HashMap<String,Object> doctorCouponMap = new HashMap<>();
			    Integer sum = 0;
			    String key = "";
			    Integer sumCount = 0;
				for (CouponStatistic statistic : statisticList) {
					key = statistic.getCouponId() + statistic.getCashOutFlag();
					if (sumCountMap.containsKey(key)) {
						sum = sumCountMap.get(key) + statistic.getNums();
						sumCountMap.put(key,  sum);
					}else {
						sumCountMap.put(key, statistic.getNums());
					}
					addMap = new HashMap<String,Object>();
					addValMap = new HashMap<String,Object>();
					if (resultMap.containsKey(statistic.getBelogtoDoctor())) {
						obj = resultMap.get(statistic.getBelogtoDoctor());
						sumCount = statistic.getNums();
						addMap.put(key, Class.forName("java.lang.Integer"));
						addValMap.put(key, sumCount);
						
						if (doctorCouponMap.containsKey(statistic.getBelogtoDoctor() + key)) {
							sumCount = statistic.getNums() + Integer.parseInt(doctorCouponMap.get(statistic.getBelogtoDoctor() + key).toString());
							addValMap.put(key, sumCount);
						}
						Object obj2 = new ClassUtil().dynamicClass(obj,addMap,addValMap);
						doctorCouponMap.put(statistic.getBelogtoDoctor() + key, statistic.getNums());
						resultMap.put(statistic.getBelogtoDoctor(), obj2);
					}else {
						obj = new Object();
						// 初始化对象属性值
						for (String val : keySet) {
							addMap.put(val, Class.forName("java.lang.Integer"));
							addValMap.put(val, 0);
						}
						addMap.put(key, Class.forName("java.lang.Integer"));
						addMap.put("belogtoDoctor", Class.forName("java.lang.String"));
						addValMap.put(key, statistic.getNums());
						addValMap.put("belogtoDoctor", statistic.getBelogtoDoctor());
						
						Object obj2= new ClassUtil().dynamicClass(obj,addMap,addValMap);
						
						doctorCouponMap.put(statistic.getBelogtoDoctor() + key, statistic.getNums());
						resultMap.put(statistic.getBelogtoDoctor(), obj2);
					}
				}
				
			    Collection<Object> valueCollection = resultMap.values();
			    resultList = new ArrayList<Object>(valueCollection);//map转list 
				if (null!=resultList && !resultList.isEmpty()) {
					Collections.sort(resultList,new Comparator<Object>(){  
			            @Override  
			            public int compare(Object b1, Object b2) {  
			            	return JSON.parseObject(JSON.toJSONString(b2)).getString("belogtoDoctor").compareTo(JSON.parseObject(JSON.toJSONString(b1)).getString("belogtoDoctor")); 
			            }  
			        });
				}
				obj = new Object();
				addMap = new HashMap<String,Object>();
				addValMap = new HashMap<String,Object>();
				if (resultList.size() > 0) {
					
					for (String val : keySet) {
						addMap.put(val, Class.forName("java.lang.Integer"));
						addValMap.put(val, 0);
					}
				    for(Map.Entry<String, Integer> count : sumCountMap.entrySet()) {
				    	addMap.put(count.getKey(), Class.forName("java.lang.Integer"));
						addValMap.put(count.getKey(), count.getValue());
				    }
				    addMap.put("belogtoDoctor", Class.forName("java.lang.String"));
					addValMap.put("belogtoDoctor", "合计");
					Object obj2= new ClassUtil().dynamicClass(obj,addMap,addValMap);
					//resultList.add(obj2);
					
					if (null != map.get("page")){
						
						int page = Integer.valueOf(map.get("page").toString());
						int limit = Integer.valueOf(map.get("limit").toString());
						resultList = resultList.subList((page-1) * limit, page*limit>=resultList.size()?resultList.size():page*limit);
						resultList.add(resultList.size(), obj2);
					}else{
						resultList.add(resultList.size(), obj2);
					}
					
				}
			}
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return R.ok().put("count", resultList.size()).put("data", resultList);
	}
	
	private List<Object> initStatistic(Map<String, Object> map, List<Object> resultList, Map<String, Object> resultMap,
			List<CouponStatistic> statisticList) throws ClassNotFoundException, Exception {
		Set<String> keySet = new HashSet<String>();
		if (null != statisticList && !statisticList.isEmpty()) {
			map.put("couponStatusList", Arrays.asList("1,2".split(",")));
			List<CouponInfoDO> couponList = couponInfoDao.list(map);
			
			for (CouponInfoDO coupon : couponList) {
				// 设置默认已兑，未兑 属性名
				keySet.add(coupon.getCouponId());
			}
			Map<String, Integer> sumCountMap = new HashMap<>();
			Object obj = null;
			HashMap<String,Object> addMap = null;
		    HashMap<String,Object> addValMap = null;
		    Integer sum = 0;
			for (CouponStatistic statistic : statisticList) {
				
				if (sumCountMap.containsKey(statistic.getCouponId())) {
					sum = sumCountMap.get(statistic.getCouponId()) + statistic.getNums();
					sumCountMap.put(statistic.getCouponId(),  sum);
				}else {
					sumCountMap.put(statistic.getCouponId(), statistic.getNums());
				}
				addMap = new HashMap<String,Object>();
				addValMap = new HashMap<String,Object>();
				if (resultMap.containsKey(statistic.getTradeDate())) {
					obj = resultMap.get(statistic.getTradeDate());
					addMap.put(statistic.getCouponId(), Class.forName("java.lang.Integer"));
					addValMap.put(statistic.getCouponId(), statistic.getNums());
					Object obj2 = new ClassUtil().dynamicClass(obj,addMap,addValMap);
					resultMap.put(statistic.getTradeDate(), obj2);
				}else {
					obj = new Object();
					
					// 初始化对象属性值
					for (String val : keySet) {
						addMap.put(val, Class.forName("java.lang.Integer"));
						addValMap.put(val, 0);
					}
					
					addMap.put(statistic.getCouponId(), Class.forName("java.lang.Integer"));
					addMap.put("tradeDate", Class.forName("java.lang.String"));
					addValMap.put(statistic.getCouponId(), statistic.getNums());
					addValMap.put("tradeDate", statistic.getTradeDate());
					Object obj2= new ClassUtil().dynamicClass(obj,addMap,addValMap);
					resultMap.put(statistic.getTradeDate(), obj2);
				}
			}
			
		    Collection<Object> valueCollection = resultMap.values();
		    resultList = new ArrayList<Object>(valueCollection);//map转list 
		    
			if (null!=resultList && !resultList.isEmpty()) {
				Collections.sort(resultList,new Comparator<Object>(){  
		            @Override  
		            public int compare(Object b1, Object b2) {  
		            	return JSON.parseObject(JSON.toJSONString(b2)).getString("tradeDate").compareTo(JSON.parseObject(JSON.toJSONString(b1)).getString("tradeDate")); 
		            }  
		        });
			}
			if (resultList.size() > 0) {
				obj = new Object();
			    addMap = new HashMap<String,Object>();
				addValMap = new HashMap<String,Object>();
				
				for (String val : keySet) {
					addMap.put(val, Class.forName("java.lang.Integer"));
					addValMap.put(val, 0);
				}
			    for(Map.Entry<String, Integer> count : sumCountMap.entrySet()) {
			    	addMap.put(count.getKey(), Class.forName("java.lang.Integer"));
					addValMap.put(count.getKey(), count.getValue());
			    }
			    addMap.put("tradeDate", Class.forName("java.lang.String"));
				addValMap.put("tradeDate", "合计");
				Object obj2= new ClassUtil().dynamicClass(obj,addMap,addValMap);
				//resultList.add(obj2);
				
				if (null != map.get("page")){
					
					int page = Integer.valueOf(map.get("page").toString());
					int limit = Integer.valueOf(map.get("limit").toString());
					resultList = resultList.subList((page-1) * limit, page*limit>=resultList.size()?resultList.size():page*limit);
					resultList.add(resultList.size(), obj2);
				}else{
					resultList.add(resultList.size(), obj2);
				}
				
			}
		}
		return resultList;
	}

}
