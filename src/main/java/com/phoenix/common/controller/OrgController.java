package com.phoenix.common.controller;

import com.phoenix.common.annotation.Log;
import com.phoenix.common.domain.OrgDO;
import com.phoenix.common.domain.Tree;
import com.phoenix.common.service.OrgService;
import com.phoenix.common.utils.Query;
import com.phoenix.common.utils.R;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/org")
public class OrgController extends BaseController {
	private String prefix = "common/org";
	
	@Autowired
	private OrgService orgService;
	
	@GetMapping("")
	@RequiresPermissions("common:org:org")
	public String org() {
		return prefix + "/org";
	}

	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("common:org:org")
	public R list(@RequestParam Map<String, Object> params) {
		Query query = new Query(params);
		List<OrgDO> orgList = orgService.list(query);
		int total = orgService.count(params);
		return R.ok().put("count", total).put("data", orgList);
	}

	@RequiresPermissions("common:org:add")
	@GetMapping("/add")
	public String add(Model model) {
		return prefix + "/add";
	}

	@RequiresPermissions("common:org:edit")
	@GetMapping("/edit/{id}")
	public String edit(Model model, @PathVariable("id") String orgId) {
		OrgDO orgDO = orgService.get(orgId);
		model.addAttribute("org", orgDO);
		return prefix+"/edit";
	}

	@RequiresPermissions("common:org:add")
	@PostMapping("/save")
	@ResponseBody
	public R save(OrgDO org) {
		if (orgService.save(org) > 0) {
			return R.ok();
		}
		return R.error();
	}
	
	@PostMapping("/exit")
	@ResponseBody
	public boolean exit(@RequestParam Map<String, Object> params) {
		// 存在，不通过，false
		return !orgService.exit(params);
	}

	@RequiresPermissions("common:org:edit")
	@PostMapping("/update")
	@ResponseBody
	public R update(OrgDO org) {
		if (orgService.update(org) > 0) {
			return R.ok();
		}
		return R.error();
	}

	@RequiresPermissions("common:org:remove")
	@PostMapping("/remove")
	@ResponseBody
	public R remove(String orgId) {
		if (orgService.remove(orgId) > 0) {
			return R.ok();
		}
		return R.error();
	}

	@RequiresPermissions("common:org:remove")
	@PostMapping("/batchRemove")
	@ResponseBody
	public R batchRemove(@RequestParam("ids[]") String[] orgIds) {
		int r = orgService.batchRemove(orgIds);
		if (r > 0) {
			return R.ok();
		}
		return R.error();
	}
	
	@ResponseBody
	@GetMapping("/getOrgs")
	@RequiresPermissions("common:org:org")
	public R getOrgs(@RequestParam Map<String, Object> params) {
		List<OrgDO> orgList = orgService.getOrgs(params);
		return R.ok().put("orgs", orgList);
	}
	
	/**
	 * 设置后台全局orgCode
	 */
	@ResponseBody
	@RequestMapping("/setGloabOrg")
	public R setGloabOrg(@RequestParam String orgCode) {
		/*Map<String, Object> params = new HashMap<>();
		params.put("orgCode", orgCode);
		List<OrgDO> orgs = orgService.list(params);
		GlobalParamUtil.setParam("orgCode", orgCode);
		GlobalParamUtil.setParam("orgName", orgs.get(0).getOrgName());
		GlobalParamUtil.setParam("orgField", orgs.get(0).getOrgField());*/
		return R.ok();
	}
	
	@GetMapping("/tree")
	@ResponseBody
	Tree<OrgDO> tree() {
		Tree<OrgDO> tree = new Tree<OrgDO>();
		tree = orgService.getTree();
		return tree;
	}
	
	
	@GetMapping("/refundConfig/{id}")
	@RequiresPermissions("sys:org:refundConfig")
	String refundConfig(Model model, @PathVariable("id") String orgId){
		OrgDO orgDO = orgService.get(orgId);
		model.addAttribute("orgDO", orgDO);
	    return prefix + "/refundConfig";
	}
	
	
	@ResponseBody
	@Log("退款密码配置")
	@PostMapping("/saveRefundConfig")
	@RequiresPermissions("sys:org:refundConfig")
	public R saveRefundConfig(OrgDO orgDO){
		int result = orgService.saveRefundConfig(orgDO);
		if(result>0){
			return R.ok();
		}else if(result == -1) {
			return R.error(-1, "旧密码输入错误");
		}else {
			return R.error();
		}
	}
}
