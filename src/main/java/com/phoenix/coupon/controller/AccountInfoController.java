package com.phoenix.coupon.controller;

import java.util.HashMap;
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

import com.phoenix.coupon.domain.AccountInfoDO;
import com.phoenix.coupon.service.AccountInfoService;
import com.phoenix.system.domain.ReserveChannelDO;
import com.phoenix.system.service.ReserveChannelService;
import com.phoenix.common.utils.R;
import com.phoenix.common.annotation.Log;
import com.phoenix.common.utils.Query;

/**
 * 账户信息
 * 
 * @author phoenix
 * @email sm516116978@outlook.com
 * @date 2019-07-11 10:44:00
 */
 
@Controller
@RequestMapping("/coupon/accountInfo")
public class AccountInfoController {
	@Autowired
	private AccountInfoService accountInfoService;
	
	@Autowired
	private ReserveChannelService reserveChannelService;
	
	@GetMapping()
	@RequiresPermissions("coupon:accountInfo:accountInfo")
	String AccountInfo(Model model){
		List<ReserveChannelDO> reserveChannelDOs = reserveChannelService.list(new HashMap<String, Object>());
		model.addAttribute("reserveChannels", reserveChannelDOs);
	    return "coupon/accountInfo/accountInfo";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("coupon:accountInfo:accountInfo")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
     //   params.put("orgCode", GlobalParamUtil.getParam("orgCode"));
        Query query = new Query(params);
		List<AccountInfoDO> accountInfoList = accountInfoService.list(query);
		int total = accountInfoService.count(query);
		return R.ok().put("count", total).put("data", accountInfoList);
	}
	
	@GetMapping("/add")
	@RequiresPermissions("coupon:accountInfo:add")
	String add(){
	    return "coupon/accountInfo/add";
	}

	@GetMapping("/edit/{accountNo}")
	@RequiresPermissions("coupon:accountInfo:edit")
	String edit(@PathVariable("accountNo") String accountNo,Model model){
		AccountInfoDO accountInfo = accountInfoService.get(accountNo);
		model.addAttribute("accountInfo", accountInfo);
	    return "coupon/accountInfo/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("coupon:accountInfo:add")
	public R save( AccountInfoDO accountInfo){
		/*accountInfo.setOrgCode(GlobalParamUtil.getParam("orgCode"));
		accountInfo.setId(UUIDUtil.getUUID());
		if(accountInfoService.save(accountInfo)>0){
			return R.ok();
		}*/
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("coupon:accountInfo:edit")
	public R update( AccountInfoDO accountInfo){
		accountInfoService.update(accountInfo);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("coupon:accountInfo:remove")
	public R remove( String accountNo){
		if(accountInfoService.remove(accountNo)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("coupon:accountInfo:batchRemove")
	public R remove(@RequestParam("ids[]") String[] accountNos){
		accountInfoService.batchRemove(accountNos);
		return R.ok();
	}
	
	
	@Log("账户解冻")
	@PostMapping( "/unfreeze")
	@ResponseBody
	@RequiresPermissions("coupon:accountCouponInfo:unfreeze")
	public R unfreeze( String accountNo){
		return accountInfoService.unfreeze(accountNo);
	}
	
	@Log("账户冻结")
	@PostMapping( "/freeze")
	@ResponseBody
	@RequiresPermissions("coupon:accountCouponInfo:freeze")
	public R freeze( String accountNo){
		return accountInfoService.freeze(accountNo);
	}
	
}
