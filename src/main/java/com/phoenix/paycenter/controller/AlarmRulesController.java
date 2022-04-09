package com.phoenix.paycenter.controller;

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

import com.phoenix.paycenter.domain.AlarmRulesDO;
import com.phoenix.paycenter.service.AlarmRulesService;
import com.phoenix.common.utils.R;
import com.phoenix.common.annotation.Log;
import com.phoenix.common.service.impl.DictServiceImpl;
import com.phoenix.common.utils.GlobalParamUtil;
import com.phoenix.common.utils.Query;
import com.phoenix.core.utils.UUIDUtil;

/**
 * 警报规则表
 * 
 * @author phoenix
 * @email sm516116978@outlook.com
 * @date 2019-04-17 09:40:12
 */
 
@Controller
@RequestMapping("/paycenter/alarmRules")
public class AlarmRulesController {
	@Autowired
	private AlarmRulesService alarmRulesService;
	
	@Autowired
	private DictServiceImpl dictServiceImpl;
	
	@Log("报警规则页面")
	@GetMapping()
	@RequiresPermissions("paycenter:alarmRules:alarmRules")
	String AlarmRules(){
	    return "paycenter/alarmRules/alarmRules";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("paycenter:alarmRules:alarmRules")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        params.put("orgCode", GlobalParamUtil.getParam("orgCode"));
        Query query = new Query(params);
		List<AlarmRulesDO> alarmRulesList = alarmRulesService.list(query);
		int total = alarmRulesService.count(query);
		return R.ok().put("count", total).put("data", alarmRulesList);
	}
	
	@Log("新增报警规则")
	@GetMapping("/add")
	@RequiresPermissions("paycenter:alarmRules:alarmRules")
	String add(Model model){
		HashMap<String, Object> payMethodParam = new HashMap<String, Object>();
		payMethodParam.put("dictType", "ALARM_RULES");
		model.addAttribute("alarmRulesDict", dictServiceImpl.list(payMethodParam));
	    return "paycenter/alarmRules/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("paycenter:alarmRules:alarmRules")
	String edit(@PathVariable("id") String id,Model model){
		AlarmRulesDO alarmRules = alarmRulesService.get(id);
		model.addAttribute("alarmRules", alarmRules);
		HashMap<String, Object> payMethodParam = new HashMap<String, Object>();
		payMethodParam.put("dictType", "ALARM_RULES");
		model.addAttribute("alarmRulesDict", dictServiceImpl.list(payMethodParam));
	    return "paycenter/alarmRules/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("paycenter:alarmRules:alarmRules")
	public R save( AlarmRulesDO alarmRules){
		alarmRules.setOrgCode(GlobalParamUtil.getParam("orgCode"));
		alarmRules.setId(UUIDUtil.getUUID());
		if(alarmRulesService.save(alarmRules)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@Log("修改报警规则")
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("paycenter:alarmRules:alarmRules")
	public R update( AlarmRulesDO alarmRules){
		alarmRulesService.update(alarmRules);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@Log("删除报警规则")
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("paycenter:alarmRules:alarmRules")
	public R remove( String id){
		if(alarmRulesService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@Log("删除报警规则")
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("paycenter:alarmRules:batchRemove")
	public R remove(@RequestParam("ids[]") String[] ids){
		alarmRulesService.batchRemove(ids);
		return R.ok();
	}
	
}
