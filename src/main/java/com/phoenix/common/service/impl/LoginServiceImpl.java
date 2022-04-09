package com.phoenix.common.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.phoenix.common.dao.LoginDao;
import com.phoenix.common.service.LoginService;
import com.phoenix.system.vo.DataStatisticVO;

@Service
public class LoginServiceImpl implements LoginService {
	
	@Autowired
	private LoginDao loginDao;

	@Override
	public DataStatisticVO getReserveStatistic(Map<String, Object> map) {
		return loginDao.getReserveStatistic(map);
	};
	
	@Override
	public DataStatisticVO getTakeStatistic(Map<String, Object> map) {
		return loginDao.getTakeStatistic(map);
	}
	
	@Override
	public DataStatisticVO getVoilateStatistic(Map<String, Object> map) {
		return loginDao.getVoilateStatistic(map);
	}
	
	@Override
	public DataStatisticVO getRegisteUserStatistic(Map<String, Object> map) {
		return loginDao.getRegisteUserStatistic(map);
	}
	
	@Override
	public Map<String, Object> getLastWeekDataStatistic(Map<String, Object> map) {
		List<Integer> reserveDataList = new ArrayList<>();
		List<Integer> violateDataList = new ArrayList<>();
		List<Integer> refundDataList = new ArrayList<>();
		List<String> dateList = getPastDayParams(7);
		map.put("startDate", dateList.get(0));
		map.put("endDate", dateList.get(6));
		List<DataStatisticVO> list = loginDao.getLastWeekDataStatistic(map);
		for(String date : dateList) {
			Integer reserveStatisticCount = 0;
			Integer violateStatisticCount = 0 ;
			Integer refundStatisticCount = 0 ;
			for(DataStatisticVO vo : list) {
				if(date.equals(vo.getStatisticDate())) {
					reserveStatisticCount = vo.getReserveStatisticCount();
					violateStatisticCount = vo.getViolateStatisticCount();
					refundStatisticCount = vo.getRefundStatisticCount();
					break;
				}
			}
			reserveDataList.add(reserveStatisticCount);
			violateDataList.add(violateStatisticCount);
			refundDataList.add(refundStatisticCount);
		}
		Map<String, Object> statis = new HashMap<String, Object>();
		statis.put("dateList", dateList);
		statis.put("reserveDataList", reserveDataList);
		statis.put("violateDataList", violateDataList);
		statis.put("refundDataList", refundDataList);
		return statis;
	}

	@Override
	public List<DataStatisticVO> getLastWeekChannelDataStatistic(Map<String, Object> map) {
		List<String> dateList = getPastDayParams(7);
		map.put("startDate", dateList.get(0));
		map.put("endDate", dateList.get(6));
		List<DataStatisticVO> list = loginDao.getLastWeekChannelDataStatistic(map);
		if (null == list || list.isEmpty()) {
			return null;
		}
		int reserveStatisticCountTotal = list.stream().mapToInt(DataStatisticVO::getReserveStatisticCount).sum();
		if (reserveStatisticCountTotal <= 0) {
			return null;
		}
		for(DataStatisticVO vo : list) {
			vo.setReserveStatisticPercent(new BigDecimal(vo.getReserveStatisticCount()*100).divide(new BigDecimal(reserveStatisticCountTotal),2,BigDecimal.ROUND_FLOOR).floatValue());
		}
		
		return list;
	}

	@Override
	public List<DataStatisticVO> getDoctorStatistic(Map<String, Object> map) {
		List<String> dateList = getPastDayParams(7);
		map.put("startDate", dateList.get(0));
		map.put("endDate", dateList.get(6));
		return loginDao.getDoctorStatistic(map);
	}

	@Override
	public List<DataStatisticVO> getDeptStatistic(Map<String, Object> map) {
		List<String> dateList = getPastDayParams(7);
		map.put("startDate", dateList.get(0));
		map.put("endDate", dateList.get(6));
		return loginDao.getDeptStatistic(map);
	}
	
	/*************************************检验检查***************************************/
	
	@Override
	public DataStatisticVO getInspectReserveStatistic(Map<String, Object> map) {
		return loginDao.getInspectReserveStatistic(map);
	};
	
	@Override
	public DataStatisticVO getInspectTakeStatistic(Map<String, Object> map) {
		return loginDao.getInspectTakeStatistic(map);
	}
	
	@Override
	public DataStatisticVO getInspectVoilateStatistic(Map<String, Object> map) {
		return loginDao.getInspectVoilateStatistic(map);
	}

	@Override
	public Map<String, Object> getLastWeekInspectDataStatistic(Map<String, Object> map) {
		List<Integer> reserveDataList = new ArrayList<>();
		List<Integer> violateDataList = new ArrayList<>();
		List<Integer> refundDataList = new ArrayList<>();
		List<String> dateList = getPastDayParams(7);
		map.put("startDate", dateList.get(0));
		map.put("endDate", dateList.get(6));
		List<DataStatisticVO> list = loginDao.getLastWeekInspectDataStatistic(map);
		for(String date : dateList) {
			Integer reserveStatisticCount = 0;
			Integer violateStatisticCount = 0 ;
			Integer refundStatisticCount = 0 ;
			for(DataStatisticVO vo : list) {
				if(date.equals(vo.getStatisticDate())) {
					reserveStatisticCount = vo.getReserveStatisticCount();
					violateStatisticCount = vo.getViolateStatisticCount();
					refundStatisticCount = vo.getRefundStatisticCount();
					break;
				}
			}
			reserveDataList.add(reserveStatisticCount);
			violateDataList.add(violateStatisticCount);
			refundDataList.add(refundStatisticCount);
		}
		Map<String, Object> statis = new HashMap<String, Object>();
		statis.put("dateList", dateList);
		statis.put("reserveDataList", reserveDataList);
		statis.put("violateDataList", violateDataList);
		statis.put("refundDataList", refundDataList);
		return statis;
	}
	
	@Override
	public List<DataStatisticVO> getItemStatistic(Map<String, Object> map) {
		List<String> dateList = getPastDayParams(7);
		map.put("startDate", dateList.get(0));
		map.put("endDate", dateList.get(6));
		return loginDao.getItemStatistic(map);
	}

}
