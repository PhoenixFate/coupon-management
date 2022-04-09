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

import com.phoenix.common.utils.R;
import com.phoenix.common.annotation.Log;
import com.phoenix.common.utils.GlobalParamUtil;
import com.phoenix.common.utils.Query;
import com.phoenix.core.utils.UUIDUtil;
import com.phoenix.coupon.domain.ConsumeLogCashOutDo;
import com.phoenix.coupon.domain.CouponCashOutDO;
import com.phoenix.coupon.service.CouponCashOutService;

/**
 * 医生优惠券核销提现
 * 
 * @author phoenix
 * @email sm516116978@outlook.com
 * @date 2019-07-22 10:53:10
 */
 
@Controller
@RequestMapping("/coupon/couponCashOut")
public class CouponCashOutController {
	@Autowired
	private CouponCashOutService couponCashOutService;
	
	@GetMapping()
	@RequiresPermissions("coupon:couponCashOut:couponCashOut")
	String CouponCashOut(){
	    return "coupon/couponCashOut/couponCashOut";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("coupon:couponCashOut:couponCashOut")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
      //  params.put("orgCode", GlobalParamUtil.getParam("orgCode"));
		params.put("channelCode", GlobalParamUtil.getParam("channelCode"));
        Query query = new Query(params);
		List<CouponCashOutDO> couponCashOutList = couponCashOutService.list(query);
		int total = couponCashOutService.count(query);
		return R.ok().put("count", total).put("data", couponCashOutList);
	}
	
	@GetMapping("/add")
	@RequiresPermissions("coupon:couponCashOut:couponCashOut")
	String add(){
	    return "coupon/couponCashOut/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("coupon:couponCashOut:couponCashOut")
	String edit(@PathVariable("id") String id,Model model){
		CouponCashOutDO couponCashOut = couponCashOutService.get(id);
		model.addAttribute("couponCashOut", couponCashOut);
	    return "coupon/couponCashOut/edit";
	}
	
	/**
	 * 医生详情页
	 */
	@Log(key = "coItems", value = "医生提现详情页")
	@GetMapping("/coItems/{id}")
	@RequiresPermissions("coupon:couponCashOut:couponCashOut")
	String coItems(@PathVariable("id") String id, Model model){
		model.addAttribute("id", id);
	   return "coupon/couponCashOut/items";
	}
	
	/**
	 * 医生提现详情
	 * @return
	 */
	@Log(key = "coItemsList", value = "医生提现详情")
	@GetMapping("/coItemsList")
	@RequiresPermissions("coupon:couponCashOut:couponCashOut")
	@ResponseBody
	R coItemsList(@RequestParam("id") String id,@RequestParam Map<String, Object> params){
		
		Query query = new Query(params);
		List<ConsumeLogCashOutDo> cashOutInfoList = couponCashOutService.getCashOutInfo(id);
		int total = couponCashOutService.getCashOutInfoCount(params);
	   return R.ok().put("count", total).put("data", cashOutInfoList);
	}
	
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("coupon:couponCashOut:couponCashOut")
	public R save( CouponCashOutDO couponCashOut){
		//couponCashOut.setOrgCode(GlobalParamUtil.getParam("orgCode"));
		couponCashOut.setId(UUIDUtil.getUUID());
		if(couponCashOutService.save(couponCashOut)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("coupon:couponCashOut:couponCashOut")
	public R update( CouponCashOutDO couponCashOut){
		couponCashOutService.update(couponCashOut);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("coupon:couponCashOut:couponCashOut")
	public R remove( String id){
		if(couponCashOutService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("coupon:couponCashOut:couponCashOut")
	public R remove(@RequestParam("ids[]") String[] ids){
		couponCashOutService.batchRemove(ids);
		return R.ok();
	}
	
}
