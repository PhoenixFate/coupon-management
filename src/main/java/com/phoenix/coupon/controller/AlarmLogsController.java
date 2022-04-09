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

import com.phoenix.coupon.domain.AlarmLogsDO;
import com.phoenix.coupon.service.AlarmLogsService;
import com.phoenix.common.utils.R;
import com.phoenix.common.utils.Query;
import com.phoenix.core.utils.UUIDUtil;

/**
 * 风控报警记录
 * 
 * @author phoenix
 * @email sm516116978@outlook.com
 * @date 2019-07-24 12:41:16
 */
 
@Controller
@RequestMapping("/coupon/alarmLogs")
public class AlarmLogsController {
	@Autowired
	private AlarmLogsService alarmLogsService;
	
	@GetMapping()
	@RequiresPermissions("coupon:alarmLogs:alarmLogs")
	String AlarmLogs(){
	    return "coupon/alarmLogs/alarmLogs";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("coupon:alarmLogs:alarmLogs")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<AlarmLogsDO> alarmLogsList = alarmLogsService.list(query);
		int total = alarmLogsService.count(query);
		return R.ok().put("count", total).put("data", alarmLogsList);
	}
	
	@GetMapping("/add")
	@RequiresPermissions("coupon:alarmLogs:add")
	String add(){
	    return "coupon/alarmLogs/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("coupon:alarmLogs:edit")
	String edit(@PathVariable("id") String id,Model model){
		AlarmLogsDO alarmLogs = alarmLogsService.get(id);
		model.addAttribute("alarmLogs", alarmLogs);
	    return "coupon/alarmLogs/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("coupon:alarmLogs:add")
	public R save( AlarmLogsDO alarmLogs){
	//	alarmLogs.setOrgCode(GlobalParamUtil.getParam("orgCode"));
		alarmLogs.setId(UUIDUtil.getUUID());
		if(alarmLogsService.save(alarmLogs)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("coupon:alarmLogs:edit")
	public R update( AlarmLogsDO alarmLogs){
		alarmLogsService.update(alarmLogs);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("coupon:alarmLogs:remove")
	public R remove( String id){
		if(alarmLogsService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("coupon:alarmLogs:batchRemove")
	public R remove(@RequestParam("ids[]") String[] ids){
		alarmLogsService.batchRemove(ids);
		return R.ok();
	}
	
}
