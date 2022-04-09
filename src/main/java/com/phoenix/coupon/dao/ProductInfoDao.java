package com.phoenix.coupon.dao;

import com.phoenix.coupon.domain.ProductInfoDO;
import com.phoenix.coupon.domain.productPackageInfoDo;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 产品（商品）信息表
 * @author phoenix
 * @email sm516116978@outlook.com
 * @date 2019-07-11 10:44:02
 */
@Mapper
public interface ProductInfoDao {
	
	List<productPackageInfoDo> getProPacRelationInfoByPacId(String productCode);
	
	List<productPackageInfoDo> getAllProPacInfo(String productCode);

	ProductInfoDO get(String productId);
	
	List<ProductInfoDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(ProductInfoDO productInfo);
	
	int update(ProductInfoDO productInfo);
	
	int remove(String product_id);
	
	int batchRemove(String[] productIds);
}
