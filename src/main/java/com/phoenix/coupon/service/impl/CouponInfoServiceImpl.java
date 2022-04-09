package com.phoenix.coupon.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.phoenix.common.domain.OrgDO;
import com.phoenix.core.redis.JedisUtil;
import com.phoenix.coupon.dao.CouponInfoDao;
import com.phoenix.coupon.domain.CouponInfoDO;
import com.phoenix.coupon.domain.CouponOrgInfoDo;
import com.phoenix.coupon.service.CouponInfoService;



@Service
public class CouponInfoServiceImpl implements CouponInfoService {
	
	Logger logger = LoggerFactory.getLogger(CouponInfoServiceImpl.class);
	
	@Autowired
	private CouponInfoDao couponInfoDao;
	
	@Override
	public CouponInfoDO get(String couponId){
		return couponInfoDao.get(couponId);
	}
	
	@Override
	public List<CouponInfoDO> list(Map<String, Object> map){
		return couponInfoDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return couponInfoDao.count(map);
	}
	
	@Override
	public int save(CouponInfoDO couponInfo){
		return couponInfoDao.save(couponInfo);
	}
	
	@Override
	public int update(CouponInfoDO couponInfo){
		int count = couponInfoDao.update(couponInfo);
		if (count > 0) {
			queryCouponInfo(couponInfo.getCouponId());
		}
		return count;
	}
	
	@Override
	public int remove(String couponId){
		return couponInfoDao.remove(couponId);
	}
	
	@Override
	public int batchRemove(String[] couponIds){
		return couponInfoDao.batchRemove(couponIds);
	}

	/**
	 * 优惠券配置
	 * 
	 * 机构集合orgList
	 * 优惠券Id couponId
	 * 
	 */
	@Override
	public List<CouponOrgInfoDo> getCouponOrgById(String couponId,List<OrgDO> orgList) {
		
		logger.info("优惠券&机构入参==》orgList=" + orgList+",couponId="+couponId);
		
		List<CouponOrgInfoDo> list = new ArrayList<>();
		
		try {
			HashMap<String, Object> map = new HashMap<>();
			map.put("couponId", couponId);
			//查询优惠券中的机构码
			List<CouponInfoDO> cifdList = couponInfoDao.list(map);
			String useOrgcodes = cifdList.get(0).getUseOrgcodes();
			
			//如果此优惠券中没有机构代码
			if(useOrgcodes == null || "".equals(useOrgcodes)) {
				//遍历机构集合
				for (OrgDO orgDO : orgList) {
					CouponOrgInfoDo cofd = new CouponOrgInfoDo();
					cofd.setCouponId(couponId);
					cofd.setOrgCode(orgDO.getOrgCode());
					cofd.setOrgGrade(orgDO.getOrgGrade());
					cofd.setOrgName(orgDO.getOrgName());
					cofd.setChecked(false);
					
					list.add(cofd);
				}
			}else {
				//将优惠券中的机构代码拆分
				List<String> couponOrgList = Arrays.asList(useOrgcodes.split(","));
		        
		        //遍历机构集合
		        for (OrgDO orgDO : orgList) {
		        	CouponOrgInfoDo cofd = new CouponOrgInfoDo();
		        	cofd.setChecked(false);
		        	
		        	//遍历优惠券中机构代码
		        	for (String col : couponOrgList) {
		        		if( orgDO.getOrgCode().equals(col) ) {
		        			cofd.setChecked(true);
		        		}
					}
		        	cofd.setCouponId(couponId);
					cofd.setOrgCode(orgDO.getOrgCode());
					cofd.setOrgGrade(orgDO.getOrgGrade());
					cofd.setOrgName(orgDO.getOrgName());
					
					list.add(cofd);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("优惠券&机构出参==》List<CouponOrgInfoDo>=" + list);
		return list;
	}
	
	/**
	 * 保存优惠券中机构编码配置
	 */
	@Override
	public Boolean batchSave(List<CouponOrgInfoDo> items) {
		logger.info("保存优惠券中机构编码配置入参==》items=" + items);
		
		String couponId = null;
		// 标识是否异常
		Boolean flag = true;
		
		try {
			if (items.size() > 0) {
				couponId = items.get(0).getCouponId();
				if ("".equals( items.get(0).getOrgCode())) {
					CouponInfoDO couponInfoDO = new CouponInfoDO();
					couponInfoDO.setUseOrgcodes("");
					couponInfoDO.setCouponId(couponId);
					if( couponInfoDao.update(couponInfoDO) <=0 ) flag =false;
				}else {
					StringBuilder builder = new StringBuilder();
					//遍历集合
					for (CouponOrgInfoDo coid : items) {
						builder.append(coid.getOrgCode()+",");
					}
					CouponInfoDO couponInfoDO = new CouponInfoDO();
					couponInfoDO.setUseOrgcodes(builder.toString().substring(0, builder.length()-1));
					couponInfoDO.setCouponId(couponId);
					if( couponInfoDao.update(couponInfoDO) <=0 ) flag =false;
				}
			}else {
				flag = false;
			}
		} catch (Exception e) {
			flag = false;
			e.printStackTrace();
		}
		logger.info("保存优惠券中机构编码配置出参==》flag=" + flag);
		return flag;
	}
	
	
	/**
	 * 
	  * 方法描述：查询优惠券信息
	  * @param 
	  * @return 
	  * @version 1.0
	  * @author phoenix
	  * 2019年7月17日 上午9:30:18
	 */
	protected void queryCouponInfo(String couponId)
	{
		String redisKey = "COUPON:INFO:ID:" + couponId;
		if(JedisUtil.exists(redisKey))
		{
			JedisUtil.delete(redisKey); 
		}
	}
}
