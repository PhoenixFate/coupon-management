package com.phoenix.common.service;

import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.phoenix.common.utils.DateTimeUtil;
import com.phoenix.core.utils.DateUtils;
import com.phoenix.system.vo.DataStatisticVO;

@Service
public interface LoginService {
	
	public DataStatisticVO getReserveStatistic(Map<String, Object> map);
	
	public DataStatisticVO getTakeStatistic(Map<String, Object> map);
	
	public DataStatisticVO getVoilateStatistic(Map<String, Object> map);
	
	public DataStatisticVO getRegisteUserStatistic(Map<String, Object> map);
	
	public Map<String, Object> getLastWeekDataStatistic(Map<String, Object> map);
	
	public List<DataStatisticVO> getLastWeekChannelDataStatistic(Map<String, Object> map);
	
	public List<DataStatisticVO> getDoctorStatistic(Map<String, Object> map);
	
	public List<DataStatisticVO> getDeptStatistic(Map<String, Object> map);
	
	default List<String> getPastDayParams(int intervals){
		String yesterday = DateTimeUtil.getDate(-1, Calendar.DAY_OF_YEAR, new Date(), DateUtils.FORMAT_yyyy_MM_dd);
		List<String> dateList = DateTimeUtil.getDateList(intervals, Calendar.DAY_OF_YEAR, yesterday, DateUtils.FORMAT_yyyy_MM_dd).get("past");
		Collections.reverse(dateList);
		return dateList;
	}
	
/*************************************检验检查***************************************/
	
	public DataStatisticVO getInspectReserveStatistic(Map<String, Object> map);
	
	public DataStatisticVO getInspectTakeStatistic(Map<String, Object> map);
	
	public DataStatisticVO getInspectVoilateStatistic(Map<String, Object> map);
	
	public Map<String, Object> getLastWeekInspectDataStatistic(Map<String, Object> map);
	
	public List<DataStatisticVO> getItemStatistic(Map<String, Object> map);
	
}
