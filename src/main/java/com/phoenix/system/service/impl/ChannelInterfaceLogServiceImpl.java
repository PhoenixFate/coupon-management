package com.phoenix.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.phoenix.system.dao.ChannelInterfaceLogDao;
import com.phoenix.system.dao.ReserveChannelDao;
import com.phoenix.system.domain.ChannelInterfaceLogDO;
import com.phoenix.system.domain.ReserveChannelDO;
import com.phoenix.system.service.ChannelInterfaceLogService;
import com.phoenix.system.vo.ChannelInterfaceLogStatisticVO;


@Service
public class ChannelInterfaceLogServiceImpl implements ChannelInterfaceLogService {
	
	@Autowired
	private ChannelInterfaceLogDao channelInterfaceLogDao;
	
	@Autowired
	private ReserveChannelDao reserveChannelDao;
	
	@Override
	public List<ChannelInterfaceLogStatisticVO> listStatistic(Map<String, Object> map) {
		List<ChannelInterfaceLogStatisticVO> channelInterfaceLogStatisticsVOs = channelInterfaceLogDao.listStatistic(map);
		if (null == channelInterfaceLogStatisticsVOs || channelInterfaceLogStatisticsVOs.isEmpty()) {
			return new ArrayList<>();
		}
		ChannelInterfaceLogStatisticVO statisticVO = channelInterfaceLogDao.total(map);
		if ("2".equals(map.get("statisticType").toString())) {
			if (null != statisticVO) {
				statisticVO.setBusinessType("total");
				statisticVO.setBusinessName("合计");
				channelInterfaceLogStatisticsVOs.add(statisticVO);
			}
			return channelInterfaceLogStatisticsVOs;
		}
		List<ChannelInterfaceLogStatisticVO> list = new ArrayList<>();
		Map<String,Integer> statisticsMap = channelInterfaceLogStatisticsVOs.stream().collect(Collectors.toMap(ChannelInterfaceLogStatisticVO::getChannelCode, ChannelInterfaceLogStatisticVO::getStatisticCount));
		List<ReserveChannelDO> reserveChannelDOs = reserveChannelDao.list(map);
		reserveChannelDOs.stream().forEach(c -> {
			ChannelInterfaceLogStatisticVO vo = new ChannelInterfaceLogStatisticVO();
			vo.setChannelCode(c.getChannelCode());
			vo.setChannelName(c.getChannelName());
			vo.setStatisticsCount(0);
			if (statisticsMap.containsKey(c.getChannelCode())) {
				vo.setStatisticsCount(statisticsMap.get(c.getChannelCode()));
			}
			list.add(vo);
		});
		if (null != statisticVO) {
			statisticVO.setChannelCode("total");
			statisticVO.setChannelName("合计");
			list.add(statisticVO);
		}
		return list;
	}

	@Override
	public int countStatistic(Map<String, Object> map) {
		return channelInterfaceLogDao.countStatistic(map);
	}

	@Override
	public List<ChannelInterfaceLogDO> list(Map<String, Object> map) {
		List<ChannelInterfaceLogDO> channelInterfaceLogDOs = channelInterfaceLogDao.list(map);
		return channelInterfaceLogDOs;
	}

	@Override
	public int count(Map<String, Object> map) {
		return channelInterfaceLogDao.count(map);
	}

}
