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
import com.alibaba.druid.util.StringUtils;
import com.phoenix.common.annotation.Log;
import com.phoenix.common.utils.Constant;
import com.phoenix.common.utils.GlobalParamUtil;
import com.phoenix.common.utils.Query;
import com.phoenix.core.utils.UUIDUtil;
import com.phoenix.coupon.domain.AccountConsumeLogDO;
import com.phoenix.coupon.service.AccountConsumeLogService;

/**
 * 账户优惠券消费记录
 * 
 * @author phoenix
 * @email sm516116978@outlook.com
 * @date 2019-07-22 09:24:02
 */
 
@Controller
@RequestMapping("/coupon/accountConsumeLog")
public class AccountConsumeLogController {
	@Autowired
	private AccountConsumeLogService accountConsumeLogService;
	
	@GetMapping()
	@RequiresPermissions("coupon:accountConsumeLog:accountConsumeLog")
	String AccountConsumeLog(){
	    return "coupon/accountConsumeLog/accountConsumeLog";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("coupon:accountConsumeLog:accountConsumeLog")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
       // params.put("orgCode", GlobalParamUtil.getParam("orgCode"));
        params.put("channelCode", GlobalParamUtil.getParam("channelCode"));
        Query query = new Query(params);
		List<AccountConsumeLogDO> accountConsumeLogList = accountConsumeLogService.list(query);
		int total = accountConsumeLogService.count(query);
		return R.ok().put("count", total).put("data", accountConsumeLogList);
	}
	
	@GetMapping("/add")
	@RequiresPermissions("coupon:accountConsumeLog:accountConsumeLog")
	String add(){
	    return "coupon/accountConsumeLog/add";
	}

	@GetMapping("/edit/{consumeId}")
	@RequiresPermissions("coupon:accountConsumeLog:accountConsumeLog")
	String edit(@PathVariable("consumeId") String consumeId,Model model){
		AccountConsumeLogDO accountConsumeLog = accountConsumeLogService.get(consumeId);
		model.addAttribute("accountConsumeLog", accountConsumeLog);
	    return "coupon/accountConsumeLog/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("coupon:accountConsumeLog:accountConsumeLog")
	public R save( AccountConsumeLogDO accountConsumeLog){
		//accountConsumeLog.setOrgCode(GlobalParamUtil.getParam("orgCode"));
		accountConsumeLog.setConsumeId(UUIDUtil.getUUID());
		if(accountConsumeLogService.save(accountConsumeLog)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("coupon:accountConsumeLog:accountConsumeLog")
	public R update( AccountConsumeLogDO accountConsumeLog){
		accountConsumeLogService.update(accountConsumeLog);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("coupon:accountConsumeLog:accountConsumeLog")
	public R remove( String consumeId){
		if(accountConsumeLogService.remove(consumeId)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("coupon:accountConsumeLog:accountConsumeLoge")
	public R remove(@RequestParam("ids[]") String[] consumeIds){
		accountConsumeLogService.batchRemove(consumeIds);
		return R.ok();
	}
	
	
	@Log("核销撤销")
	@PostMapping( "/refund")
	@ResponseBody
	@RequiresPermissions("coupon:accountConsumeLog:refund")
	public R refund( String consumeId){
		return accountConsumeLogService.refund(consumeId);
	}
	
	@Log("提现")
	@PostMapping( "/cashOut")
	@ResponseBody
	@RequiresPermissions("coupon:accountConsumeLog:cashOut")
	public R cashOut(@RequestParam Map<String, Object> param){
		if (null == param.get("belogtoDoctor") || StringUtils.isEmpty(param.get("belogtoDoctor").toString())) {
			return R.error("提现医生编码为空,请联系管理员");
		}
		if (null == param.get("orgCode") || StringUtils.isEmpty(param.get("orgCode").toString())) {
			return R.error("未选择提现医院，提现失败");
		}
		String channelCode = GlobalParamUtil.getParam("channelCode");
		if (StringUtils.isEmpty(channelCode)) {
			return R.error("全渠道下不可提现");
		}
		param.put("channelCode", channelCode);
		param.put("consumeStatus", Constant.ConsumeStatus.hxcg);
		param.put("cashOutFlag", Constant.CashOutFlag.wtx);
		
		try {
			return accountConsumeLogService.cashOut(param);
		}catch (Exception e) {
			return R.error("服务异常,请联系管理员");
		}
		
	}
	
	
}
