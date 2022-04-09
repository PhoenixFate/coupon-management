package com.phoenix.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.phoenix.system.dao.ReserveFreezeLogDao;
import com.phoenix.system.domain.ReserveFreezeLogDO;
import com.phoenix.system.service.ReserveFreezeLogService;


@Service
public class ReserveFreezeLogServiceImpl implements ReserveFreezeLogService {
	
	@Autowired
	private ReserveFreezeLogDao reserveFreezeLogDao;

	@Override
	public List<ReserveFreezeLogDO> list(Map<String, Object> map) {
		List<ReserveFreezeLogDO> reserveFreezeLogDOs = reserveFreezeLogDao.list(map);
		return reserveFreezeLogDOs;
	}

	@Override
	public int count(Map<String, Object> map) {
		return reserveFreezeLogDao.count(map);
	}

}
