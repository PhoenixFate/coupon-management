package com.phoenix.common.dao;

import java.util.List;
import java.util.Map;

import com.phoenix.system.vo.DataStatisticVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LoginDao {

	public DataStatisticVO getReserveStatistic(Map<String, Object> map);
	
	public DataStatisticVO getTakeStatistic(Map<String, Object> map);
	
	public DataStatisticVO getVoilateStatistic(Map<String, Object> map);
	
	public DataStatisticVO getRegisteUserStatistic(Map<String, Object> map);
	
	public List<DataStatisticVO> getLastWeekDataStatistic(Map<String, Object> map);
	
	public List<DataStatisticVO> getLastWeekChannelDataStatistic(Map<String, Object> map);
	
	public List<DataStatisticVO> getDoctorStatistic(Map<String, Object> map);
	
	public List<DataStatisticVO> getDeptStatistic(Map<String, Object> map);
	
	/*************************************检验检查***************************************/
	
	public DataStatisticVO getInspectReserveStatistic(Map<String, Object> map);
	
	public DataStatisticVO getInspectTakeStatistic(Map<String, Object> map);
	
	public DataStatisticVO getInspectVoilateStatistic(Map<String, Object> map);
	
	public List<DataStatisticVO> getLastWeekInspectDataStatistic(Map<String, Object> map);
	
	public List<DataStatisticVO> getItemStatistic(Map<String, Object> map);
	
}