package com.phoenix.coupon.controller;

import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.phoenix.coupon.domain.PackageInfoDO;
import com.phoenix.coupon.domain.ProductInfoDO;
import com.phoenix.coupon.domain.productPackageInfoDo;
import com.phoenix.coupon.service.PackageInfoService;
import com.phoenix.coupon.service.ProductInfoService;
import com.phoenix.common.utils.R;
import com.phoenix.common.annotation.Log;
import com.phoenix.common.utils.Constant;
import com.phoenix.common.utils.Query;
import com.phoenix.core.utils.UUIDUtil;

/**
 * 产品（商品）信息表
 * 
 * @author phoenix
 * @email sm516116978@outlook.com
 * @date 2019-07-11 10:44:02
 */
 
@Controller
@RequestMapping("/coupon/productInfo")
public class ProductInfoController {
	
	@Autowired
	private PackageInfoService packageInfoService;
	
	@Autowired
	private ProductInfoService productInfoService;
	
	@GetMapping()
	@RequiresPermissions("coupon:productInfo:productInfo")
	String ProductInfo(){
	    return "coupon/productInfo/productInfo";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("coupon:productInfo:productInfo")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
      //  params.put("orgCode", GlobalParamUtil.getParam("orgCode"));
        Query query = new Query(params);
		List<ProductInfoDO> productInfoList = productInfoService.list(query);
		int total = productInfoService.count(query);
		return R.ok().put("count", total).put("data", productInfoList);
	}
	
	/**
	 * 显示产品下对应的服务包（页面跳转）
	 */
	@Log(key = "proItems", value = "显示产品下对应的服务包")
	@GetMapping("/proItems/{productCode}")
	@RequiresPermissions("coupon:productInfo:productInfo")
	String productItems(@PathVariable("productCode") String productCode,Model model){
	    return "coupon/productInfo/items";
	}
	
	/**
	 * 显示产品下对应的服务包
	 */
	@ResponseBody
	@Log(key = "productItemsList", value = "显示产品下对应的服务包")
	@GetMapping("/productItemsList")
	@RequiresPermissions("coupon:productInfo:productInfo")
	public R productItemsList(@RequestParam("productCode") String productCode,@RequestParam Map<String, Object> params){
		
		//获取所有的服务包
        //params.put("orgCode", GlobalParamUtil.getParam("orgCode"));
		params.put("packageStatus", 1); //只展示启用的
        Query query = new Query(params);
        List<PackageInfoDO> packageInfoList = packageInfoService.list(query);
        
      //  int total = packageInfoService.count(query);
        
      //将产品和服务包关联封装
  		List<productPackageInfoDo> proPacRelationInfoList = productInfoService.getProPacRelationInfoByPacId(productCode,packageInfoList);
		
	    return R.ok().put("data", proPacRelationInfoList);
	}
	
	
	@GetMapping("/add")
	@RequiresPermissions("coupon:productInfo:productInfo")
	String add(){
	    return "coupon/productInfo/add";
	}

	@GetMapping("/edit/{productId}")
	@RequiresPermissions("coupon:productInfo:productInfo")
	String edit(@PathVariable("productId") String productId,Model model){
		ProductInfoDO productInfo = productInfoService.get(productId);
		model.addAttribute("productInfo", productInfo);
	    return "coupon/productInfo/edit";
	}
	
	/**
	 * 保存产品包的服务包
	 */
	@Log(key = "batchSave", value = "保存产品包的服务包")
	@PostMapping("/batchSave")
	@RequiresPermissions("coupon:productInfo:productInfo")
	@ResponseBody
	public R batchSave(@RequestBody List<productPackageInfoDo> items){
		if(productInfoService.batchSave(items)) {
			return R.ok();
		}
		return R.error();
	}
	
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("coupon:productInfo:productInfo")
	public R save( ProductInfoDO productInfo){
		productInfo.setProductId(UUIDUtil.getUUID());
		productInfo.setVersion(UUIDUtil.getUUID());
		if(productInfoService.save(productInfo)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("coupon:productInfo:productInfo")
	public R update( ProductInfoDO productInfo){
		productInfoService.update(productInfo);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("coupon:productInfo:remove")
	public R remove( String productId){
		
		ProductInfoDO productInfo = new ProductInfoDO();
		productInfo.setProductId(productId);
		productInfo.setProductStatus(Constant.ProductStatus.ty);
		if(productInfoService.update(productInfo)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("coupon:productInfo:batchRemove")
	public R remove(@RequestParam("ids[]") String[] productIds){
		productInfoService.batchRemove(productIds);
		return R.ok();
	}
	
}
