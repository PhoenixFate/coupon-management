package com.phoenix.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.phoenix.common.utils.GlobalParamUtil;
import com.phoenix.core.utils.UUIDUtil;
import com.phoenix.system.dao.ChannelInterfacePermsDao;
import com.phoenix.system.domain.ChannelInterfacePermsDO;
import com.phoenix.system.service.ChannelInterfacePermsService;

@Service
public class ChannelInterfacePermsServiceImpl implements ChannelInterfacePermsService {
	@Autowired
	private ChannelInterfacePermsDao channelInterfacePermsDao;
	
	@Override
	public List<ChannelInterfacePermsDO> list(Map<String, Object> map){
		return channelInterfacePermsDao.list(map);
	}
	
	public int count(Map<String, Object> map){
		return channelInterfacePermsDao.count(map);
	}
	
	@Override
	public int save(ChannelInterfacePermsDO channelInterfacePermsDO){
		channelInterfacePermsDO.setId(UUIDUtil.getUUID());
		channelInterfacePermsDO.setOrgCode(GlobalParamUtil.getParam("orgCode"));
		return channelInterfacePermsDao.save(channelInterfacePermsDO);
	}
	
	@Transactional
	@Override
	public int batchSave(String[] interfaceIds, String channelCode) {
		List<String> needInsertInterfaceIds = new ArrayList<>();
		List<String> needDeleteInterfaceIds = new ArrayList<>();
		Map<String, Object> map = new HashMap<>();
		map.put("channelCode", channelCode);
		map.put("orgCode", GlobalParamUtil.getParam("orgCode"));
		if (null == interfaceIds || interfaceIds.length == 0) {
			if (channelInterfacePermsDao.batchRemove(map) <= 0) {
				throw new RuntimeException("保存失败");
			}
			return 1;
		}
		List<ChannelInterfacePermsDO> existsPerms = channelInterfacePermsDao.list(map);
		if (null != existsPerms && !existsPerms.isEmpty()) {
			List<String> existsInterfaceIds = existsPerms.stream().map(ChannelInterfacePermsDO::getInterfaceListId).collect(Collectors.toList());
			List<String> list = new ArrayList<>(Arrays.asList(interfaceIds));
			if (list.removeAll(existsInterfaceIds)) {
				needInsertInterfaceIds = list;
			}
			list = new ArrayList<>(Arrays.asList(interfaceIds));
			if (existsInterfaceIds.removeAll(list)) {
				needDeleteInterfaceIds = existsInterfaceIds;
			};
		} else {
			needInsertInterfaceIds = Arrays.asList(interfaceIds);
		}
		if (!needDeleteInterfaceIds.isEmpty()) {
			map.put("needDeleteInterfaceIds", needDeleteInterfaceIds);
			if (channelInterfacePermsDao.batchRemove(map) <= 0) {
				throw new RuntimeException("保存失败");
			}
		}
		List<ChannelInterfacePermsDO> list = new ArrayList<>();
		for(String interfaceId : needInsertInterfaceIds) {
			ChannelInterfacePermsDO channelInterfacePermsDO = new ChannelInterfacePermsDO();
			channelInterfacePermsDO.setId(UUIDUtil.getUUID());
			channelInterfacePermsDO.setChannelCode(channelCode);
			channelInterfacePermsDO.setInterfaceListId(interfaceId);
			channelInterfacePermsDO.setOrgCode(GlobalParamUtil.getParam("orgCode"));
			list.add(channelInterfacePermsDO);
		}
		if (!list.isEmpty()) {
			if (channelInterfacePermsDao.batchSave(list) <= 0) {
				throw new RuntimeException("保存失败");
			}
		}
		return 1;
	}
}
