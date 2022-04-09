package com.phoenix.coupon.dao;

import com.phoenix.coupon.domain.DoctorInfoDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 医生信息表
 * @author phoenix
 * @email sm516116978@outlook.com
 * @date 2019-07-11 10:44:01
 */
@Mapper
public interface DoctorInfoDao {

	DoctorInfoDO get(String docApplyId);
	
	List<DoctorInfoDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(DoctorInfoDO doctorInfo);
	
	int update(DoctorInfoDO doctorInfo);
	
	int remove(String doc_apply_id);
	
	int batchRemove(String[] docApplyIds);
}
