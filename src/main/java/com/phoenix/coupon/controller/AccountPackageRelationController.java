package com.phoenix.coupon.controller;

import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.phoenix.common.utils.Query;
import com.phoenix.common.utils.R;
import com.phoenix.coupon.domain.AccountPackageRelationDO;
import com.phoenix.coupon.service.AccountPackageRelationService;

/**
 * 用户服务包关系表
 * 
 * @author tangwei
 * @email 
 * @date 2019-07-26 15:35:06
 */
 
@Controller
@RequestMapping("/coupon/accountPackageRelation")
public class AccountPackageRelationController {
	@Autowired
	private AccountPackageRelationService accountPackageRelationService;
	
	@GetMapping()
	@RequiresPermissions("coupon:accountPackageRelation:accountPackageRelation")
	String AccountPackageRelation(){
	    return "coupon/accountPackageRelation/accountPackageRelation";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("coupon:accountPackageRelation:accountPackageRelation")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
      //  params.put("orgCode", GlobalParamUtil.getParam("orgCode"));
        Query query = new Query(params);
		List<AccountPackageRelationDO> accountPackageRelationList = accountPackageRelationService.list(query);
		int total = accountPackageRelationService.count(query);
		return R.ok().put("count", total).put("data", accountPackageRelationList);
	}
	
	@GetMapping("/add")
	@RequiresPermissions("coupon:accountPackageRelation:add")
	String add(){
	    return "coupon/accountPackageRelation/add";
	}

	@GetMapping("/edit/{relationId}")
	@RequiresPermissions("coupon:accountPackageRelation:edit")
	String edit(@PathVariable("relationId") String relationId,Model model){
		AccountPackageRelationDO accountPackageRelation = accountPackageRelationService.get(relationId);
		model.addAttribute("accountPackageRelation", accountPackageRelation);
	    return "coupon/accountPackageRelation/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("coupon:accountPackageRelation:add")
	public R save( AccountPackageRelationDO accountPackageRelation){
		if(accountPackageRelationService.save(accountPackageRelation)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("coupon:accountPackageRelation:edit")
	public R update( AccountPackageRelationDO accountPackageRelation){
		accountPackageRelationService.update(accountPackageRelation);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("coupon:accountPackageRelation:remove")
	public R remove( String relationId){
		if(accountPackageRelationService.remove(relationId)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("coupon:accountPackageRelation:batchRemove")
	public R remove(@RequestParam("ids[]") String[] relationIds){
		accountPackageRelationService.batchRemove(relationIds);
		return R.ok();
	}
	
}
