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
import com.phoenix.common.utils.GlobalParamUtil;
import com.phoenix.common.utils.Query;
import com.phoenix.common.utils.R;
import com.phoenix.coupon.domain.AccountCouponInfoDO;
import com.phoenix.coupon.service.AccountCouponInfoService;

/**
 * 账户优惠券信息
 * 
 * @author phoenix
 * @email sm516116978@outlook.com
 * @date 2019-07-11 10:44:00
 */
 
@Controller
@RequestMapping("/coupon/accountCouponInfo")
public class AccountCouponInfoController {
	@Autowired
	private AccountCouponInfoService accountCouponInfoService;
	
	@GetMapping()
	@RequiresPermissions("coupon:accountCouponInfo:accountCouponInfo")
	String AccountCouponInfo(){
	    return "coupon/accountCouponInfo/accountCouponInfo";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("coupon:accountCouponInfo:accountCouponInfo")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
       // params.put("orgCode", GlobalParamUtil.getParam("orgCode"));
        params.put("channelCode", GlobalParamUtil.getParam("channelCode"));
        Query query = new Query(params);
		List<AccountCouponInfoDO> accountCouponInfoList = accountCouponInfoService.list(query);
		int total = accountCouponInfoService.count(query);
		return R.ok().put("count", total).put("data", accountCouponInfoList);
	}
	
	@GetMapping("/add")
	@RequiresPermissions("coupon:accountCouponInfo:add")
	String add(){
	    return "coupon/accountCouponInfo/add";
	}

	@GetMapping("/edit/{accountNo}")
	@RequiresPermissions("coupon:accountCouponInfo:edit")
	String edit(@PathVariable("accountNo") String accountNo,Model model){
		AccountCouponInfoDO accountCouponInfo = accountCouponInfoService.get(accountNo);
		model.addAttribute("accountCouponInfo", accountCouponInfo);
	    return "coupon/accountCouponInfo/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("coupon:accountCouponInfo:add")
	public R save( AccountCouponInfoDO accountCouponInfo){
		/*accountCouponInfo.setOrgCode(GlobalParamUtil.getParam("orgCode"));
		accountCouponInfo.setId(UUIDUtil.getUUID());
		if(accountCouponInfoService.save(accountCouponInfo)>0){
			return R.ok();
		}*/
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("coupon:accountCouponInfo:edit")
	public R update( AccountCouponInfoDO accountCouponInfo){
		accountCouponInfoService.update(accountCouponInfo);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("coupon:accountCouponInfo:remove")
	public R remove( String accountNo){
		if(accountCouponInfoService.remove(accountNo)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("coupon:accountCouponInfo:batchRemove")
	public R remove(@RequestParam("ids[]") String[] accountNos){
		accountCouponInfoService.batchRemove(accountNos);
		return R.ok();
	}
	
	@Log("优惠券解冻")
	@PostMapping( "/unfreeze")
	@ResponseBody
	@RequiresPermissions("coupon:accountCouponInfo:unfreeze")
	public R unfreeze( String couponNo){
		return accountCouponInfoService.unfreeze(couponNo);
	}
	
	@Log("优惠券冻结")
	@PostMapping( "/freeze")
	@ResponseBody
	@RequiresPermissions("coupon:accountCouponInfo:freeze")
	public R freeze( String couponNo){
		return accountCouponInfoService.freeze(couponNo);
	}
	
	@Log("冻结优惠券核销")
	@PostMapping( "/consume")
	@ResponseBody
	@RequiresPermissions("coupon:accountCouponInfo:consume")
	public R consume( String couponNo){
		return accountCouponInfoService.consume(couponNo);
	}
	
	
	/**
	 * 跳转到配置机构详情页面
	 * @param packageId
	 * @param model
	 * @return
	 */
	@Log(key = "spItems", value = "跳转到配置机构详情页面")
	@GetMapping("/spItems/{relationId}/{accountNo}")
	@RequiresPermissions("coupon:accountCouponInfo:accountCouponInfo")
	String couponItems(@PathVariable("relationId") String relationId, @PathVariable("accountNo") String accountNo, Model model){
	   model.addAttribute("accountNo", accountNo);
	   model.addAttribute("relationId", relationId);
	   return "coupon/accountPackageRelation/items";
	}
	
	@Log(key = "couponItemsList", value = "获取服务包下面用户卡券信息")
	@GetMapping( "/couponItemsList")
	@ResponseBody
	@RequiresPermissions("coupon:accountCouponInfo:accountCouponInfo")
	public R couponItemsList(@RequestParam Map<String, Object> params){
		List<AccountCouponInfoDO> couponList = accountCouponInfoService.list(params);
		return R.ok().put("count", couponList.size()).put("data", couponList);
	}
	
	
	
}
