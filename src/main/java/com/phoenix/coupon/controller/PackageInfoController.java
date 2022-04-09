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

import com.phoenix.coupon.domain.CouponInfoDO;
import com.phoenix.coupon.domain.PackageAndCoupDo;
import com.phoenix.coupon.domain.PackageCouponInfoDo;
import com.phoenix.coupon.domain.PackageInfoDO;
import com.phoenix.coupon.service.CouponInfoService;
import com.phoenix.coupon.service.PackageInfoService;
import com.phoenix.common.utils.R;
import com.phoenix.common.annotation.Log;
import com.phoenix.common.utils.Constant;
import com.phoenix.common.utils.Query;
import com.phoenix.core.utils.UUIDUtil;

/**
 * 优惠券套餐信息
 * 
 * @author phoenix
 * @email sm516116978@outlook.com
 * @date 2019-07-11 10:44:01
 */
 
@Controller
@RequestMapping("/coupon/packageInfo")
public class PackageInfoController {
	@Autowired
	private PackageInfoService packageInfoService;
	
	@Autowired
	private CouponInfoService couponInfoService;
	
	@GetMapping()
	@RequiresPermissions("coupon:packageInfo:packageInfo")
	String PackageInfo(){
	    return "coupon/packageInfo/packageInfo";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("coupon:packageInfo:packageInfo")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
     //   params.put("orgCode", GlobalParamUtil.getParam("orgCode"));
        Query query = new Query(params);
		List<PackageInfoDO> packageInfoList = packageInfoService.list(query);
		int total = packageInfoService.count(query);
		return R.ok().put("count", total).put("data", packageInfoList);
	}
	
	/**
	 * 添加优惠包
	 * @return
	 */
	@GetMapping("/add")
	@RequiresPermissions("coupon:packageInfo:packageInfo")
	String add(){
	    return "coupon/packageInfo/add";
	}

	/**
	 * 编辑优惠包
	 * @param packageId
	 * @param model
	 * @return
	 */
	@GetMapping("/edit/{packageId}")
	@RequiresPermissions("coupon:packageInfo:packageInfo")
	String edit(@PathVariable("packageId") String packageId,Model model){
		PackageInfoDO packageInfo = packageInfoService.get(packageId);
		model.addAttribute("packageInfo", packageInfo);
	    return "coupon/packageInfo/edit";
	}
	
	/**
	 * 跳转到服务包详情页面
	 * @param packageId
	 * @param model
	 * @return
	 */
	@Log(key = "spItems", value = "跳转到服务包详情页面")
	@GetMapping("/spItems/{packageId}")
	@RequiresPermissions("coupon:packageInfo:packageInfo")
	String packageItems(@PathVariable("packageId") String packageId, Model model){
	   model.addAttribute("packageId", packageId);
	   return "coupon/packageInfo/items";
	}
	
	@Log(key = "packageItemsList", value = "服务包详情")
	@ResponseBody
	@GetMapping("/packageItemsList")
	@RequiresPermissions("coupon:packageInfo:packageInfo")
	public R packageItemsList(@RequestParam("packageId") String packageId,@RequestParam Map<String, Object> params){
		
		//查询列表数据
       // params.put("orgCode", GlobalParamUtil.getParam("orgCode"));
		params.put("couponStatus", 1); //只展示启用的
        Query query = new Query(params);
        //所有优惠券
		List<CouponInfoDO> couponInfoList = couponInfoService.list(query);
		
	//	int total = couponInfoService.count(query);
		
		//将优惠券和包关联进行封装
		List<PackageCouponInfoDo> packageCouponInfoList = packageInfoService.getPacCouRelationInfoByPacId(packageId,couponInfoList);
		
		
		return R.ok().put("data", packageCouponInfoList);
	}
	
	/**
	 * 保存服务包的卡券选择
	 */
	@Log(key = "batchSave", value = "保存服务包的卡券选择")
	@PostMapping("/batchSave")
	@RequiresPermissions("coupon:packageInfo:packageInfo")
	@ResponseBody
	public R batchSave(@RequestBody List<PackageAndCoupDo> items){
		if(packageInfoService.batchSave(items)) {
			return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("coupon:packageInfo:packageInfo")
	public R save( PackageInfoDO packageInfo){
		packageInfo.setPackageId(UUIDUtil.getUUID());
		if(packageInfoService.save(packageInfo)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("coupon:packageInfo:packageInfo")
	public R update( PackageInfoDO packageInfo){
		packageInfoService.update(packageInfo);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("coupon:packageInfo:remove")
	public R remove( String packageId){
		
		PackageInfoDO packageInfo = new PackageInfoDO();
		packageInfo.setPackageId(packageId);
		packageInfo.setPackageStatus(Constant.PackageStatus.ty);
		int count = packageInfoService.update(packageInfo);
		if(count>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("coupon:packageInfo:batchRemove")
	public R remove(@RequestParam("ids[]") String[] packageIds){
		packageInfoService.batchRemove(packageIds);
		return R.ok();
	}
	
}
