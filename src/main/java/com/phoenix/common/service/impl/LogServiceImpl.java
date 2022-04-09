package com.phoenix.common.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.phoenix.common.dao.LogDao;
import com.phoenix.common.domain.LogDO;
import com.phoenix.common.service.LogService;

@Service
public class LogServiceImpl implements LogService {
	
	@Autowired
	private LogDao logDao;

	@Override
	public List<LogDO> list(Map<String, Object> map) {
		map.put("sort", "create_gmt");
		map.put("order", "desc");
		return logDao.list(map);
	}

	@Override
	public int count(Map<String, Object> map) {
		return logDao.count(map);
	}

}
