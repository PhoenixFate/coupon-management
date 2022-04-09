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

import com.phoenix.common.annotation.Log;
import com.phoenix.common.utils.Query;
import com.phoenix.common.utils.R;
import com.phoenix.core.utils.UUIDUtil;
import com.phoenix.coupon.domain.CouponInfoRulesDO;
import com.phoenix.coupon.service.CouponInfoRulesService;

/**
 * 警报规则表
 * 
 * @author tangwei
 * @email 
 * @date 2019-08-14 16:26:01
 */
 
@Controller
@RequestMapping("/coupon/couponInfoRules")
public class CouponInfoRulesController {
	@Autowired
	private CouponInfoRulesService couponInfoRulesService;
	
	@GetMapping()
	@RequiresPermissions("coupon:couponInfoRules:couponInfoRules")
	String CouponInfoRules(){
	    return "coupon/couponInfoRules/couponInfoRules";
	}
	
	/**
	 * 跳转到规则页面
	 * @param model
	 * @return
	 */
	@Log(key = "spItems", value = "跳转到配置机构详情页面")
	@GetMapping("/spItems/{couponId}")
	@RequiresPermissions("coupon:couponInfoRules:couponInfoRules")
	String couponItems(@PathVariable("couponId") String couponId, Model model){
	   model.addAttribute("couponId", couponId);
	   return "coupon/couponInfoRules/couponInfoRules";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("coupon:couponInfoRules:couponInfoRules")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
       // params.put("orgCode", GlobalParamUtil.getParam("orgCode"));
        Query query = new Query(params);
		List<CouponInfoRulesDO> couponInfoRulesList = couponInfoRulesService.list(query);
		int total = couponInfoRulesService.count(query);
		return R.ok().put("count", total).put("data", couponInfoRulesList);
	}
	
	@GetMapping("/add/{couponId}")
	@RequiresPermissions("coupon:couponInfoRules:couponInfoRules")
	String add(@PathVariable("couponId") String couponId,Model model){
		model.addAttribute("couponId", couponId);
	    return "coupon/couponInfoRules/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("coupon:couponInfoRules:couponInfoRules")
	String edit(@PathVariable("id") String id,Model model){
		CouponInfoRulesDO couponInfoRules = couponInfoRulesService.get(id);
		model.addAttribute("couponInfoRules", couponInfoRules);
	    return "coupon/couponInfoRules/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("coupon:couponInfoRules:couponInfoRules")
	public R save( CouponInfoRulesDO couponInfoRules){
		//couponInfoRules.setOrgCode(GlobalParamUtil.getParam("orgCode"));
		couponInfoRules.setId(UUIDUtil.getUUID());
		if(couponInfoRulesService.save(couponInfoRules)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("coupon:couponInfoRules:couponInfoRules")
	public R update( CouponInfoRulesDO couponInfoRules){
		couponInfoRulesService.update(couponInfoRules);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("coupon:couponInfoRules:couponInfoRules")
	public R remove( String id){
		if(couponInfoRulesService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("coupon:couponInfoRules:couponInfoRules")
	public R remove(@RequestParam("ids[]") String[] ids){
		couponInfoRulesService.batchRemove(ids);
		return R.ok();
	}
	
}
