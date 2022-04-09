package com.phoenix.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.phoenix.core.utils.UUIDUtil;
import com.phoenix.system.dao.ReserveInterfaceListDao;
import com.phoenix.system.domain.ReserveInterfaceListDO;
import com.phoenix.system.service.ReserveInterfaceListService;

@Service
public class ReserveInterfaceListServiceImpl implements ReserveInterfaceListService {
	@Autowired
	private ReserveInterfaceListDao reserveInterfaceListDao;
	
	@Override
	public List<ReserveInterfaceListDO> list(Map<String, Object> map){
		return reserveInterfaceListDao.list(map);
	}

	@Override
	public int count(Map<String, Object> map) {
		return reserveInterfaceListDao.count(map);
	}

	@Override
	public int save(ReserveInterfaceListDO reserveInterfaceListDO) {
		reserveInterfaceListDO.setInterfaceId(UUIDUtil.getUUID());
		reserveInterfaceListDO.setCreateGmt(new Date());
		return reserveInterfaceListDao.save(reserveInterfaceListDO);
	}

	@Override
	public int update(ReserveInterfaceListDO reserveInterfaceListDO) {
		reserveInterfaceListDO.setUpdateGmt(new Date());
		return reserveInterfaceListDao.update(reserveInterfaceListDO);
	}

	@Override
	public int remove(String interfaceId) {
		return reserveInterfaceListDao.remove(interfaceId);
	}

	@Override
	public int batchRemove(String[] interfaceIds) {
		return reserveInterfaceListDao.batchRemove(interfaceIds);
	}
	
}
