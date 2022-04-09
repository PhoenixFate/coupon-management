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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.phoenix.coupon.domain.PackageCouponRelationDO;
import com.phoenix.coupon.service.PackageCouponRelationService;
import com.phoenix.common.utils.R;
import com.phoenix.common.utils.Query;
import com.phoenix.core.utils.UUIDUtil;

/**
 * 活动和优惠券关系表
 * 
 * @author phoenix
 * @email sm516116978@outlook.com
 * @date 2019-07-11 10:44:01
 */
 
@Controller
@RequestMapping("/coupon/packageCouponRelation")
public class PackageCouponRelationController {
	@Autowired
	private PackageCouponRelationService packageCouponRelationService;
	
	@GetMapping()
	@RequiresPermissions("coupon:packageCouponRelation:packageCouponRelation")
	String PackageCouponRelation(){
	    return "coupon/packageCouponRelation/packageCouponRelation";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("coupon:packageCouponRelation:packageCouponRelation")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
      //  params.put("orgCode", GlobalParamUtil.getParam("orgCode"));
        Query query = new Query(params);
		List<PackageCouponRelationDO> packageCouponRelationList = packageCouponRelationService.list(query);
		int total = packageCouponRelationService.count(query);
		return R.ok().put("count", total).put("data", packageCouponRelationList);
	}
	
	@GetMapping("/add")
	@RequiresPermissions("coupon:packageCouponRelation:add")
	String add(){
	    return "coupon/packageCouponRelation/add";
	}

	@GetMapping("/edit/{relationId}")
	@RequiresPermissions("coupon:packageCouponRelation:edit")
	String edit(@PathVariable("relationId") String relationId,Model model){
		PackageCouponRelationDO packageCouponRelation = packageCouponRelationService.get(relationId);
		model.addAttribute("packageCouponRelation", packageCouponRelation);
	    return "coupon/packageCouponRelation/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("coupon:packageCouponRelation:add")
	public R save( PackageCouponRelationDO packageCouponRelation){
		packageCouponRelation.setPackageId(UUIDUtil.getUUID());
		if(packageCouponRelationService.save(packageCouponRelation)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("coupon:packageCouponRelation:edit")
	public R update( PackageCouponRelationDO packageCouponRelation){
		packageCouponRelationService.update(packageCouponRelation);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("coupon:packageCouponRelation:remove")
	public R remove( String relationId){
		if(packageCouponRelationService.remove(relationId)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("coupon:packageCouponRelation:batchRemove")
	public R remove(@RequestParam("ids[]") String[] relationIds){
		packageCouponRelationService.batchRemove(relationIds);
		return R.ok();
	}
	
}
