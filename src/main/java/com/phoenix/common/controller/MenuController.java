package com.phoenix.common.controller;

import com.phoenix.common.annotation.Log;
import com.phoenix.common.config.Constant;
import com.phoenix.common.domain.MenuDO;
import com.phoenix.common.domain.Tree;
import com.phoenix.common.service.MenuService;
import com.phoenix.common.utils.R;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/menu")
@Controller
public class MenuController extends BaseController {
	String prefix = "common/menu";
	@Autowired
	MenuService menuService;

	@RequiresPermissions("common:menu:menu")
	@GetMapping()
	String menu(Model model) {
		return prefix+"/menu";
	}

	@RequiresPermissions("common:menu:menu")
	@RequestMapping("/list")
	@ResponseBody
	public R list(@RequestParam Map<String, Object> params) {
		List<MenuDO> menus = menuService.list(params);
//		int total = menuService.count(params);
		return R.ok().put("data", menus);
	}

	@Log("添加菜单")
	@RequiresPermissions("common:menu:add")
	@GetMapping("/add")
	String add(Model model) {
		model.addAttribute("pName", menuService.list(new HashMap<>()));
		return prefix + "/add";
	}

	@Log("编辑菜单")
	@RequiresPermissions("common:menu:edit")
	@GetMapping("/edit/{id}")
	String edit(Model model, @PathVariable("id") String menuId) {
		MenuDO mdo = menuService.get(menuId);
		model.addAttribute("menu", mdo);
		return prefix+"/edit";
	}
	
	@GetMapping("/fontIconList")
	String fontIconList() {
		return prefix+"/fontIconList";
	}

	@Log("保存菜单")
	@RequiresPermissions("common:menu:add")
	@PostMapping("/save")
	@ResponseBody
	R save(MenuDO menu) {
		if (Constant.DEMO_ACCOUNT.equals(getUserName())) {
			return R.error(1, "演示系统不允许修改,完整体验请部署程序");
		}
		if (menuService.save(menu) > 0) {
			return R.ok();
		} else {
			return R.error(1, "保存失败");
		}
	}

	@Log("更新菜单")
	@RequiresPermissions("common:menu:edit")
	@PostMapping("/update")
	@ResponseBody
	R update(MenuDO menu) {
		if (Constant.DEMO_ACCOUNT.equals(getUserName())) {
			return R.error(1, "演示系统不允许修改,完整体验请部署程序");
		}
		if (menuService.update(menu) > 0) {
			return R.ok();
		} else {
			return R.error(1, "更新失败");
		}
	}

	@Log("删除菜单")
	@RequiresPermissions("common:menu:remove")
	@PostMapping("/remove")
	@ResponseBody
	R remove(String menuId) {
		if (Constant.DEMO_ACCOUNT.equals(getUserName())) {
			return R.error(1, "演示系统不允许修改,完整体验请部署程序");
		}
		if (menuService.remove(menuId) > 0) {
			return R.ok();
		} else {
			return R.error(1, "删除失败");
		}
	}

	@GetMapping("/tree")
	@ResponseBody
	Tree<MenuDO> tree() {
		Tree<MenuDO> tree = new Tree<MenuDO>();
		tree = menuService.getTree();
		return tree;
	}

	@GetMapping("/tree/{roleId}")
	@ResponseBody
	Tree<MenuDO> tree(@PathVariable("roleId") String roleId) {
		Tree<MenuDO> tree = new Tree<MenuDO>();
		tree = menuService.getTree(roleId);
		return tree;
	}
}
