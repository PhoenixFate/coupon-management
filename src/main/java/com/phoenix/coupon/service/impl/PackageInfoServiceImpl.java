package com.phoenix.coupon.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.phoenix.core.redis.JedisUtil;
import com.phoenix.coupon.dao.PackageCouponRelationDao;
import com.phoenix.coupon.dao.PackageInfoDao;
import com.phoenix.coupon.domain.CouponInfoDO;
import com.phoenix.coupon.domain.PackageAndCoupDo;
import com.phoenix.coupon.domain.PackageCouponInfoDo;
import com.phoenix.coupon.domain.PackageCouponRelationDO;
import com.phoenix.coupon.domain.PackageInfoDO;
import com.phoenix.coupon.service.PackageInfoService;

@Service
public class PackageInfoServiceImpl implements PackageInfoService {

	Logger logger = LoggerFactory.getLogger(PackageInfoServiceImpl.class);

	@Autowired
	private PackageInfoDao packageInfoDao;

	@Autowired
	private PackageCouponRelationDao packageCouponRelationDao;

	@Override
	public PackageInfoDO get(String packageId) {
		return packageInfoDao.get(packageId);
	}

	@Override
	public List<PackageInfoDO> list(Map<String, Object> map) {
		return packageInfoDao.list(map);
	}

	@Override
	public int count(Map<String, Object> map) {
		return packageInfoDao.count(map);
	}

	@Override
	public int save(PackageInfoDO packageInfo) {
		return packageInfoDao.save(packageInfo);
	}

	@Override
	public int update(PackageInfoDO packageInfo) {
		int count = packageInfoDao.update(packageInfo);
		if (count > 0) {
			queryPackageInfo(packageInfo.getPackageId());
		}
		return count;
	}

	@Override
	public int remove(String packageId) {
		return packageInfoDao.remove(packageId);
	}

	@Override
	public int batchRemove(String[] packageIds) {
		return packageInfoDao.batchRemove(packageIds);
	}

	/**
	 * 对优惠券和服务包进行数据封装
	 * 
	 * packageId 包Id couponList 所有优惠券集合 pacCouRelList 根据与包Id有关联的关系数据
	 */
	@Override
	public List<PackageCouponInfoDo> getPacCouRelationInfoByPacId(String packageId, List<CouponInfoDO> couponList) {

		logger.info("优惠包的入参==》packageId=" + packageId);

		List<PackageCouponInfoDo> list = new ArrayList<>();

		try {
			// 查询相关的优惠券
			List<PackageCouponInfoDo> pcri = packageInfoDao.getPacCouRelationInfoByPacId(packageId);

			// 所有的优惠券
			for (CouponInfoDO cid : couponList) {

				PackageCouponInfoDo pcif = new PackageCouponInfoDo();
				// 默认的的优惠券个数
				pcif.setCouponNums(1);
				// 用于标识是否被选中过
				pcif.setChecked(false);

				// 遍历有关联的优惠券
				for (PackageCouponInfoDo pcifd : pcri) {
					// 如果是本优惠包关联券，修改内容
					if (cid.getCouponId().equals(pcifd.getCouponId())) {
						pcif.setCouponNums(pcifd.getCouponNums());
						pcif.setChecked(true);
						break;
					}
				}

				// 优惠券id
				pcif.setCouponId(cid.getCouponId());
				// 优惠券名
				pcif.setCouponName(cid.getCouponName());
				// 优惠券详情
				pcif.setCouponDetail(cid.getCouponDetail());
				list.add(pcif);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("优惠包的出参==》" + list);
		return list;
	}

	/**
	 * 优惠包中，券的个数
	 */
	@Override
	public int getPacCouRelationInfoCount(Map<String, Object> map) {
		return packageInfoDao.getPacCouRelationInfoCount(map);
	}

	/**
	 * 保存用户选择 allPacCouInfo 本服务包的关系表 items 勾选的选项
	 */
	@Override
	public Boolean batchSave(List<PackageAndCoupDo> items) {

		logger.info("用户优惠券勾选保存入参==》items=" + items);

		String packageId = null;
		// 标识是否异常
		Boolean flag = true;
		// 标识是否有关系
		Boolean relation = false;

		try {

			if (items.size() > 0) {

				packageId = items.get(0).getPackageId();

				if (!"".equals( items.get(0).getCouponId())  &&  !"".equals(items.get(0).getCouponNums()) ) {
					
					// 获取关系表
					List<PackageCouponInfoDo> allPacCouInfo = packageInfoDao.getAllPacCouInfo(packageId);

					// 遍历用户选项
					for (PackageAndCoupDo pacList : items) {
						
						if(allPacCouInfo.size() == 0) {
							relation = true;
						}else {
							// 遍历关系表
							for (PackageCouponInfoDo pcif : allPacCouInfo) {
								// 如果用户选项和关系表有关联，则更新
								if (pacList.getCouponId() == pcif.getCouponId()
										|| pacList.getCouponId().equals(pcif.getCouponId())) {
									// 如果存在，就更新
									String couponId = pacList.getCouponId();
									String couponNums = pacList.getCouponNums();
									int setPacCouNums = packageInfoDao.setPacCouNums(couponNums, couponId, packageId);
									if (setPacCouNums <= 0) flag = false;
									relation = false;
									break;
								}
								// 用户选择的couponId不在关系表中
								relation = true;
							}
						}
						// 用户选择的couponId不在关系表中,进行关系表的添加
						if (relation) {
							PackageCouponRelationDO packageCouponRelation = new PackageCouponRelationDO();
							UUID randomUUID = UUID.randomUUID();
							packageCouponRelation.setRelationId(randomUUID.toString());
							packageCouponRelation.setCouponId(pacList.getCouponId());
							packageCouponRelation.setCouponNums(Integer.parseInt(pacList.getCouponNums()));
							packageCouponRelation.setPackageId(pacList.getPackageId());
							int save = packageCouponRelationDao.save(packageCouponRelation);
							if (save <= 0) flag = false;
								
						}
					}

					// 添加和更新完毕之后，重新查询关系表
					List<PackageCouponInfoDo> newAllPacCouInfo = packageInfoDao.getAllPacCouInfo(packageId);

					// 遍历新的关系表
					for (PackageCouponInfoDo packageCouponInfoDo : newAllPacCouInfo) {
						Boolean exits = true;
						// 遍历用户选项
						for (PackageAndCoupDo pacList : items) {
							if (packageCouponInfoDo.getCouponId() == pacList.getCouponId()
									|| packageCouponInfoDo.getCouponId().equals(pacList.getCouponId()))
								exits = false;
						}
						// 遍历完成后也没有用户的选项，则将此关系在关系表中删除
						if (exits) {
							// 如果不存在关系表中，将去删除
							int remove = packageCouponRelationDao.remove(packageCouponInfoDo.getRelationId());
							if (remove <= 0) flag = false;
						}
					}
				} else {
					//没有选择任何数据，直接从数据库中删除所有关系
					int count = packageCouponRelationDao.removeByPacId(packageId);
					if(count < 0) flag = false;
				}
			}else {
				flag = false;
			}
		} catch (Exception e) {
			flag = false;
			e.printStackTrace();
		}
		logger.info("用户优惠券勾选保存出参==》flag=" + flag);
		return flag;
	}

	
	/**
	 * 
	  * 方法描述：查询服务包信息
	  * @param 
	  * @return 
	  * @version 1.0
	  * @author phoenix
	  * 2019年7月16日 下午3:41:46
	 */
	protected void queryPackageInfo(String packageId)
	{
		String redisKey = "COUPON:PACKAGE:INFO:" + packageId;
		if(JedisUtil.exists(redisKey))
		{
			JedisUtil.delete(redisKey);
		}
	}
}
