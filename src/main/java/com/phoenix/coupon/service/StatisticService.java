package com.phoenix.coupon.service;

import java.util.Map;

import com.phoenix.common.utils.R;


/**
 * 优惠券统计
 * @author tw
 * 2019年7月22日
 */
public interface StatisticService {

	R sumSendList(Map<String, Object> param);

	R sumConsumeList(Map<String, Object> param);

	R sumCancelList(Map<String, Object> param);

	R sumDoctorsList(Map<String, Object> param);
	
}
