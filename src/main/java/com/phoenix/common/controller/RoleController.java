package com.phoenix.common.controller;

import com.phoenix.common.annotation.Log;
import com.phoenix.common.domain.RoleDO;
import com.phoenix.common.domain.Tree;
import com.phoenix.common.service.RoleService;
import com.phoenix.common.utils.Query;
import com.phoenix.common.utils.R;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequestMapping("/role")
@Controller
public class RoleController extends BaseController {
	String prefix = "common/role";
	
	@Autowired
	private RoleService roleService;

	@Log("查看角色列表")
	@RequiresPermissions("common:role:role")
	@GetMapping()
	String role() {
		return prefix + "/role";
	}

	@RequiresPermissions("common:role:role")
	@GetMapping("/list")
	@ResponseBody()
	public R list(@RequestParam Map<String, Object> params) {
		Query query = new Query(params);
		List<RoleDO> roles = roleService.list(query);
		int total = roleService.count(params);
		return R.ok().put("count", total).put("data", roles);
	}

	@Log("添加角色")
	@RequiresPermissions("common:role:add")
	@GetMapping("/add")
	String add() {
		return prefix + "/add";
	}

	@Log("编辑角色")
	@RequiresPermissions("common:role:edit")
	@GetMapping("/edit/{id}")
	String edit(@PathVariable("id") String roleId, Model model) {
		RoleDO roleDO = roleService.get(roleId);
		model.addAttribute("role", roleDO);
		return prefix + "/edit";
	}

	@Log("保存角色")
	@RequiresPermissions("common:role:add")
	@PostMapping("/save")
	@ResponseBody()
	R save(RoleDO role) {
		if (roleService.save(role) > 0) {
			return R.ok();
		} else {
			return R.error(1, "保存失败");
		}
	}

	@Log("更新角色")
	@RequiresPermissions("common:role:edit")
	@PostMapping("/update")
	@ResponseBody()
	R update(RoleDO role) {
		if (roleService.update(role) > 0) {
			return R.ok();
		} else {
			return R.error(1, "保存失败");
		}
	}

	@Log("删除角色")
	@RequiresPermissions("common:role:remove")
	@PostMapping("/remove")
	@ResponseBody()
	R save(String roleId) {
		if (roleService.remove(roleId) > 0) {
			return R.ok();
		} else {
			return R.error(1, "删除失败");
		}
	}
	
	@RequiresPermissions("common:role:remove")
	@Log("批量删除角色")
	@PostMapping("/batchRemove")
	@ResponseBody
	R batchRemove(@RequestParam("ids[]") String[] roleIds) {
		int r = roleService.batchremove(roleIds);
		if (r > 0) {
			return R.ok();
		}
		return R.error();
	}
	
	@GetMapping("/tree")
	@ResponseBody
	List<Tree<RoleDO>> tree() {
		return roleService.getTree();
	}
}
