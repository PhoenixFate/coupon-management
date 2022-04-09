package com.phoenix.coupon.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.phoenix.coupon.dao.DoctorInfoDao;
import com.phoenix.coupon.domain.DoctorInfoDO;
import com.phoenix.coupon.service.DoctorInfoService;



@Service
public class DoctorInfoServiceImpl implements DoctorInfoService {
	@Autowired
	private DoctorInfoDao doctorInfoDao;
	
	@Override
	public DoctorInfoDO get(String docApplyId){
		return doctorInfoDao.get(docApplyId);
	}
	
	@Override
	public List<DoctorInfoDO> list(Map<String, Object> map){
		return doctorInfoDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return doctorInfoDao.count(map);
	}
	
	@Override
	public int save(DoctorInfoDO doctorInfo){
		return doctorInfoDao.save(doctorInfo);
	}
	
	@Override
	public int update(DoctorInfoDO doctorInfo){
		return doctorInfoDao.update(doctorInfo);
	}
	
	@Override
	public int remove(String docApplyId){
		return doctorInfoDao.remove(docApplyId);
	}
	
	@Override
	public int batchRemove(String[] docApplyIds){
		return doctorInfoDao.batchRemove(docApplyIds);
	}

	@Override
	public boolean exit(Map<String, Object> params) {
		boolean exit = false;
		if(null == params.get("oldDocCode") || !params.get("oldDocCode").toString().equals(params.get("docCode").toString())) {
			exit = doctorInfoDao.list(params).size() > 0;
		}
		return exit;
	}
	
}
