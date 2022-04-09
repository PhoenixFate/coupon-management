package com.phoenix.system.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import com.phoenix.common.utils.GlobalParamUtil;
import com.phoenix.core.redis.JedisUtil;
import com.phoenix.core.utils.DateUtils;
import com.phoenix.core.utils.UUIDUtil;
import com.phoenix.system.dao.ReserveChannelDao;
import com.phoenix.system.domain.ReserveChannelDO;
import com.phoenix.system.service.ReserveChannelService;


@Service
public class ReserveChannelServiceImpl implements ReserveChannelService {
	@Autowired
	private ReserveChannelDao reserveChannelDao;
	
	@Override
	public List<ReserveChannelDO> list(Map<String, Object> map){
		List<ReserveChannelDO> list = reserveChannelDao.list(map);
		for(ReserveChannelDO reserveChannelDO : list) {
			String frozenExpireTimeRedis = JedisUtil.get("");
			if (StringUtils.isNotBlank(frozenExpireTimeRedis)) {
				reserveChannelDO.setFrozenStatus("1");
				reserveChannelDO.setFrozenExpireTime(DateUtils.parseDate(frozenExpireTimeRedis, DateUtils.FORMAT_yyyy_MM_dd_HH_mm_ss));
			}
		}
		return list;
	}
	
	public int count(Map<String, Object> map){
		return reserveChannelDao.count(map);
	}
	
	@Override
	public int save(ReserveChannelDO reserveChannelDO){
		reserveChannelDO.setChannelId(UUIDUtil.getUUID());
		reserveChannelDO.setOrgCode(GlobalParamUtil.getParam("orgCode"));
		reserveChannelDO.setCreateTime(new Date());
		return reserveChannelDao.save(reserveChannelDO);
	}
	
	@Override
	public boolean checkExists(Map<String, Object> params) {
		boolean exit = false;
		if(null == params.get("oldChannelCode") || !params.get("oldChannelCode").toString().equals(params.get("channelCode").toString())) {
			exit = reserveChannelDao.count(params) > 0;
		}
		return exit;
	}
	
	@Override
	public int update(ReserveChannelDO reserveChannelDO){
		reserveChannelDO.setUpdateTime(new Date());
		return reserveChannelDao.update(reserveChannelDO);
	}
	
}
