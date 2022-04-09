package com.phoenix.coupon.service;

import com.phoenix.coupon.domain.PackageInfoDO;
import com.phoenix.coupon.domain.ProductInfoDO;
import com.phoenix.coupon.domain.productPackageInfoDo;

import java.util.List;
import java.util.Map;

/**
 * 产品（商品）信息表
 * 
 * @author phoenix
 * @email sm516116978@outlook.com
 * @date 2019-07-11 10:44:02
 */
public interface ProductInfoService {
	
	List<productPackageInfoDo> getProPacRelationInfoByPacId(String productCode, List<PackageInfoDO> packageInfoList);
	
	ProductInfoDO get(String productId);
	
	Boolean batchSave(List<productPackageInfoDo> items);
	
	List<ProductInfoDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(ProductInfoDO productInfo);
	
	int update(ProductInfoDO productInfo);
	
	int remove(String productId);
	
	int batchRemove(String[] productIds);
}
