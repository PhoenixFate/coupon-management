package com.phoenix.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.phoenix.system.dao.ReserveChannelStatisticDao;
import com.phoenix.system.domain.ReserveChannelStatisticDO;
import com.phoenix.system.service.ReserveChannelStatisticService;


@Service
public class ReserveChannelStatisticServiceImpl implements ReserveChannelStatisticService {
	
	@Autowired
	private ReserveChannelStatisticDao reserveChannelStatisticDao;
	
	@Override
	public List<ReserveChannelStatisticDO> list(Map<String, Object> map) {
		List<ReserveChannelStatisticDO> reserveChannelStatisticDOs = reserveChannelStatisticDao.list(map);
		if (null != reserveChannelStatisticDOs && !reserveChannelStatisticDOs.isEmpty()) {
			ReserveChannelStatisticDO reserveChannelStatisticDO = reserveChannelStatisticDao.total(map);
			if (null != reserveChannelStatisticDO) {
				reserveChannelStatisticDO.setChannelCode("total");
				reserveChannelStatisticDO.setChannelName("合计");
				reserveChannelStatisticDOs.add(reserveChannelStatisticDO);
			}
		}
		return reserveChannelStatisticDOs;
	}

	@Override
	public int count(Map<String, Object> map) {
		return reserveChannelStatisticDao.count(map);
	}

}
