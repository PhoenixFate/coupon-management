package com.phoenix.coupon.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.phoenix.core.redis.JedisUtil;
import com.phoenix.coupon.dao.PackageProductRelationDao;
import com.phoenix.coupon.dao.ProductInfoDao;
import com.phoenix.coupon.domain.PackageInfoDO;
import com.phoenix.coupon.domain.PackageProductRelationDO;
import com.phoenix.coupon.domain.ProductInfoDO;
import com.phoenix.coupon.domain.productPackageInfoDo;
import com.phoenix.coupon.service.ProductInfoService;



@Service
public class ProductInfoServiceImpl implements ProductInfoService {
	
	Logger logger = LoggerFactory.getLogger(ProductInfoServiceImpl.class);
	
	@Autowired
	private ProductInfoDao productInfoDao;
	
	@Autowired
	private PackageProductRelationDao packageProductRelationDao;
	
	@Override
	public ProductInfoDO get(String productId){
		return productInfoDao.get(productId);
	}
	
	@Override
	public List<ProductInfoDO> list(Map<String, Object> map){
		return productInfoDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return productInfoDao.count(map);
	}
	
	@Override
	public int save(ProductInfoDO productInfo){
		return productInfoDao.save(productInfo);
	}
	
	@Override
	public int update(ProductInfoDO productInfo){
		int count = productInfoDao.update(productInfo);
		if (count> 0) {
			ProductInfoDO info = productInfoDao.get(productInfo.getProductId());
			queryProductInfo(info.getProductCode());
		}
		return count;
	}
	
	@Override
	public int remove(String productId){
		return productInfoDao.remove(productId);
	}
	
	@Override
	public int batchRemove(String[] productIds){
		return productInfoDao.batchRemove(productIds);
	}

	/**
	 * 将产品和服务包数据重新封装
	 */
	@Override
	public List<productPackageInfoDo> getProPacRelationInfoByPacId(String productCode, List<PackageInfoDO> packageInfoList) {
		
		logger.info("产品入参==》productCode="+productCode);
		
		List<productPackageInfoDo> list = new ArrayList<>();
		
		try {
			
			//获取产品关联的服务包信息
			List<productPackageInfoDo> proPacRelationInfoByPacId = productInfoDao.getProPacRelationInfoByPacId(productCode);
			
			//遍历服务包
			for (PackageInfoDO pacInfo : packageInfoList) {
				
				productPackageInfoDo proPacInfoDao = new productPackageInfoDo();
				proPacInfoDao.setChecked(false);
				
				//遍历产品关联服务包信息
				for (productPackageInfoDo proPacInfo : proPacRelationInfoByPacId) {
					
					//如果服务包的id与查询出来的数据中存储的卡券包id相同，则将这个服务包的状态变为已选择
					if(pacInfo.getPackageId().equals(proPacInfo.getPackageId())) {
						proPacInfoDao.setChecked(true);
						break;
					}
				}
				
				proPacInfoDao.setPackageDetail(pacInfo.getPackageDetail());
				proPacInfoDao.setPackageName(pacInfo.getPackageName());
				proPacInfoDao.setPackageId(pacInfo.getPackageId());
				
				list.add(proPacInfoDao);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("产品出参==》List<productPackageInfoDo> :"+list);
		return list;
	}

	/**
	 * 存储产品与服务包的关系
	 */
	@Override
	public Boolean batchSave(List<productPackageInfoDo> items) {
		logger.info("存储产品与服务包关系入参==》items=" + items);
		
		String productCode = null;
		// 标识是否异常
		Boolean flag = true;
		// 标识是否有关系
		Boolean relation = false;
		
		try {
			
			if (items.size() > 0) {
				productCode = items.get(0).getProductCode();
				if (!"".equals( items.get(0).getPackageId())) {
					//获取关系表
					List<productPackageInfoDo> allProPacInfo = productInfoDao.getAllProPacInfo(productCode);
					//遍历用户选项
					for (productPackageInfoDo ppiList : items) {
						if(allProPacInfo.size() == 0) {
							relation = true;
						}else {
							//遍历关系表
							for (productPackageInfoDo ppid : allProPacInfo) {
								// 如果用户选项和关系表有关联，则更新
								if(ppiList.getPackageId() == ppid.getPackageId() || ppiList.getPackageId().equals(ppid.getPackageId())) {
									relation = false;
									break;
								}
								// 用户选择的package不在关系表中
								relation = true;
							}
						}
						// 用户选择的package不在关系表中,进行关系表的添加
						if (relation) {
							PackageProductRelationDO pprd = new PackageProductRelationDO();
							UUID randomUUID = UUID.randomUUID();
							pprd.setRelationId(randomUUID.toString());
							pprd.setProductId(productCode);
							pprd.setPackageId(ppiList.getPackageId());
							int save = packageProductRelationDao.save(pprd);
							if (save <= 0) flag = false;
						}
					}
					//获取关系表
					List<productPackageInfoDo> newAllProPacInfo = productInfoDao.getAllProPacInfo(productCode);
					
					// 遍历新的关系表,将新关系表中，不存在用户选项的关系给删除掉
					for (productPackageInfoDo productPackageInfoDo : newAllProPacInfo) {
						Boolean exits = true;
						// 遍历用户选项
						for (productPackageInfoDo ppiList : items) {
							if(productPackageInfoDo.getPackageId() == ppiList.getPackageId() || 
									productPackageInfoDo.getPackageId().equals(ppiList.getPackageId()) ) 
								exits = false;
						}
						// 遍历完成后也没有用户的选项，则将此关系在关系表中删除
						if (exits) {
							// 如果不存在关系表中，将去删除
							int remove = packageProductRelationDao.remove(productPackageInfoDo.getRelationId());
							if (remove <= 0) flag = false;
						}
					}
				} else {
					//没有选择任何数据，直接从数据库中删除所有关系
					int count = packageProductRelationDao.removeByProductId(productCode);
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
	  * 方法描述：查询产品信息
	  * @param 
	  * @return 
	  * @version 1.0
	  * @author phoenix
	  * 2019年7月17日 上午11:41:31
	 */
	protected void queryProductInfo(String productCode)
	{
		String redisKey = "COUPON:PRODUCT:ID:" + productCode;
		if(JedisUtil.exists(redisKey))
		{
			JedisUtil.delete(redisKey);
		}
	}
}
