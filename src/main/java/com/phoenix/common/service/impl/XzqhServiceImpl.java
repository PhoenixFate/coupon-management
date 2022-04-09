package com.phoenix.common.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.phoenix.common.dao.XzqhDao;
import com.phoenix.common.domain.XzqhDO;
import com.phoenix.common.service.XzqhService;

@Service
public class XzqhServiceImpl implements XzqhService {
	
	@Autowired
	private XzqhDao xzqhDao;

	@Override
	public List<XzqhDO> selectXzqh(Map<String, Object> map) {
		return xzqhDao.selectXzqh(map);
	}

}
