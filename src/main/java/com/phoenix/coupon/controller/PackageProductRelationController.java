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

import com.phoenix.coupon.domain.PackageProductRelationDO;
import com.phoenix.coupon.service.PackageProductRelationService;
import com.phoenix.common.utils.R;
import com.phoenix.common.utils.Query;
import com.phoenix.core.utils.UUIDUtil;

/**
 * 活动和产品关系表
 * 
 * @author phoenix
 * @email sm516116978@outlook.com
 * @date 2019-07-11 10:44:02
 */
 
@Controller
@RequestMapping("/coupon/packageProductRelation")
public class PackageProductRelationController {
	@Autowired
	private PackageProductRelationService packageProductRelationService;
	
	@GetMapping()
	@RequiresPermissions("coupon:packageProductRelation:packageProductRelation")
	String PackageProductRelation(){
	    return "coupon/packageProductRelation/packageProductRelation";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("coupon:packageProductRelation:packageProductRelation")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
       // params.put("orgCode", GlobalParamUtil.getParam("orgCode"));
        Query query = new Query(params);
		List<PackageProductRelationDO> packageProductRelationList = packageProductRelationService.list(query);
		int total = packageProductRelationService.count(query);
		return R.ok().put("count", total).put("data", packageProductRelationList);
	}
	
	@GetMapping("/add")
	@RequiresPermissions("coupon:packageProductRelation:add")
	String add(){
	    return "coupon/packageProductRelation/add";
	}

	@GetMapping("/edit/{relationId}")
	@RequiresPermissions("coupon:packageProductRelation:edit")
	String edit(@PathVariable("relationId") String relationId,Model model){
		PackageProductRelationDO packageProductRelation = packageProductRelationService.get(relationId);
		model.addAttribute("packageProductRelation", packageProductRelation);
	    return "coupon/packageProductRelation/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("coupon:packageProductRelation:add")
	public R save( PackageProductRelationDO packageProductRelation){
		packageProductRelation.setRelationId(UUIDUtil.getUUID());
		if(packageProductRelationService.save(packageProductRelation)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("coupon:packageProductRelation:edit")
	public R update( PackageProductRelationDO packageProductRelation){
		packageProductRelationService.update(packageProductRelation);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("coupon:packageProductRelation:remove")
	public R remove( String relationId){
		if(packageProductRelationService.remove(relationId)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("coupon:packageProductRelation:batchRemove")
	public R remove(@RequestParam("ids[]") String[] relationIds){
		packageProductRelationService.batchRemove(relationIds);
		return R.ok();
	}
	
}
