package com.phoenix.coupon.service;

import com.phoenix.coupon.domain.DoctorInfoDO;

import java.util.List;
import java.util.Map;

/**
 * 医生信息表
 * 
 * @author phoenix
 * @email sm516116978@outlook.com
 * @date 2019-07-11 10:44:01
 */
public interface DoctorInfoService {
	
	DoctorInfoDO get(String docApplyId);
	
	List<DoctorInfoDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(DoctorInfoDO doctorInfo);
	
	int update(DoctorInfoDO doctorInfo);
	
	int remove(String docApplyId);
	
	int batchRemove(String[] docApplyIds);

	boolean exit(Map<String, Object> params);
}
