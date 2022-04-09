package com.phoenix.coupon.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.phoenix.common.annotation.Log;
import com.phoenix.common.domain.OrgDO;
import com.phoenix.common.service.OrgService;
import com.phoenix.common.service.impl.DictServiceImpl;
import com.phoenix.common.utils.Query;
import com.phoenix.common.utils.R;
import com.phoenix.core.utils.UUIDUtil;
import com.phoenix.coupon.domain.CouponInfoDO;
import com.phoenix.coupon.domain.CouponOrgInfoDo;
import com.phoenix.coupon.service.CouponInfoService;
import com.phoenix.system.domain.ReserveChannelDO;
import com.phoenix.system.service.ReserveChannelService;

/**
 * 优惠券基础信息
 * 
 * @author phoenix
 * @email sm516116978@outlook.com
 * @date 2019-07-11 10:44:01
 */
 
@Controller
@RequestMapping("/coupon/couponInfo")
public class CouponInfoController {
	@Autowired
	private CouponInfoService couponInfoService;
	
	@Autowired
	private ReserveChannelService reserveChannelService;
	
	@Autowired
	private DictServiceImpl dictServiceImpl;
	
	@Autowired
	private OrgService orgService;
	
	@GetMapping()
	@RequiresPermissions("coupon:couponInfo:couponInfo")
	String CouponInfo(Model model){
		List<ReserveChannelDO> reserveChannelDOs = reserveChannelService.list(new HashMap<String, Object>());
		model.addAttribute("reserveChannels", reserveChannelDOs);
	    return "coupon/couponInfo/couponInfo";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("coupon:couponInfo:couponInfo")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
       // params.put("orgCode", GlobalParamUtil.getParam("orgCode"));
        Query query = new Query(params);
		List<CouponInfoDO> couponInfoList = couponInfoService.list(query);
		int total = couponInfoService.count(query);
		return R.ok().put("count", total).put("data", couponInfoList);
	}
	
	@GetMapping("/add")
	@RequiresPermissions("coupon:couponInfo:couponInfo")
	String add(Model model){
		HashMap<String, Object> payMethodParam = new HashMap<String, Object>();
		payMethodParam.put("dictType", "BIZCODE");
		model.addAttribute("bizCodes", dictServiceImpl.list(payMethodParam));
	    return "coupon/couponInfo/add";
	}

	@GetMapping("/edit/{couponId}")
	@RequiresPermissions("coupon:couponInfo:couponInfo")
	String edit(@PathVariable("couponId") String couponId,Model model){
		CouponInfoDO couponInfo = couponInfoService.get(couponId);
		model.addAttribute("couponInfo", couponInfo);
		HashMap<String, Object> payMethodParam = new HashMap<String, Object>();
		payMethodParam.put("dictType", "BIZCODE");
		model.addAttribute("bizCodes", dictServiceImpl.list(payMethodParam));
	    return "coupon/couponInfo/edit";
	}
	
	/**
	 * 跳转到配置机构详情页面
	 * @param packageId
	 * @param model
	 * @return
	 */
	@Log(key = "spItems", value = "跳转到配置机构详情页面")
	@GetMapping("/spItems/{couponId}")
	@RequiresPermissions("coupon:couponInfo:couponInfo")
	String couponItems(@PathVariable("couponId") String couponId, Model model){
	   model.addAttribute("couponId", couponId);
	   return "coupon/couponInfo/items";
	}
	
	/**
	 * 配置机构
	 * @param packageId
	 * @param model
	 * @return
	 */
	@Log(key = "couponItemList", value = "配置机构")
	@GetMapping("/couponItemList")
	@RequiresPermissions("coupon:couponInfo:couponInfo")
	@ResponseBody
	R couponItemList(@RequestParam("couponId") String couponId,@RequestParam Map<String, Object> params){
		
		params.put("status", 1);  // 只显示启用的
		Query query = new Query(params);
		List<OrgDO> orgList = orgService.list(query);
		// int total = orgService.count(params);
		
		List<CouponOrgInfoDo> couponOrgList = couponInfoService.getCouponOrgById(couponId,orgList);
		
	   return R.ok().put("data", couponOrgList);
	}
	
	/**
	 * 保存优惠券中机构配置
	 */
	@Log(key = "batchSave", value = "保存优惠券中机构编码配置")
	@PostMapping("/batchSave")
	@RequiresPermissions("coupon:couponInfo:couponInfo")
	@ResponseBody
	public R batchSave(@RequestBody List<CouponOrgInfoDo> items){
		if(couponInfoService.batchSave(items)) {
			return R.ok();
		}
		return R.error();
	}
	
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("coupon:couponInfo:couponInfo")
	public R save( CouponInfoDO couponInfo){
		couponInfo.setCouponId(UUIDUtil.getUUID());
		couponInfo.setVersion(UUIDUtil.getUUID());
		couponInfo.setRemainNums(couponInfo.getTotalNums());
		if(couponInfoService.save(couponInfo)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("coupon:couponInfo:couponInfo")
	public R update( CouponInfoDO couponInfo){
		couponInfoService.update(couponInfo);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("coupon:couponInfo:couponInfo")
	public R remove( String couponId){
		CouponInfoDO info = couponInfoService.get(couponId);
		info.setCouponStatus("2");
		couponInfoService.update(info);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("coupon:couponInfo:batchRemove")
	public R remove(@RequestParam("ids[]") String[] couponIds){
		couponInfoService.batchRemove(couponIds);
		return R.ok();
	}
	
}
