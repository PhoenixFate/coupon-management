package com.phoenix.system.controller;

import com.phoenix.common.annotation.Log;
import com.phoenix.common.controller.BaseController;
import com.phoenix.common.utils.Query;
import com.phoenix.common.utils.R;
import com.phoenix.system.domain.ReserveInterfaceListDO;
import com.phoenix.system.service.ReserveInterfaceListService;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/reserveInterfaceList")
public class ReserveInterfaceListController extends BaseController {
	private String prefix = "system/reserveInterfaceList";
	
	@Autowired
	private ReserveInterfaceListService reserveInterfaceListService;
	
	@Log("查看接口列表")
	@GetMapping("")
	@RequiresPermissions("system:reserveInterfaceList:reserveInterfaceList")
	String reserveInterfaceList(Model model) {
		return prefix + "/reserveInterfaceList";
	}

	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("system:reserveInterfaceList:reserveInterfaceList")
	public R list(@RequestParam Map<String, Object> params) {
		Query query = new Query(params);
		List<ReserveInterfaceListDO> list = reserveInterfaceListService.list(query);
		int count = reserveInterfaceListService.count(params);
		return R.ok().put("count", count).put("data", list);
	}

	@RequiresPermissions("system:reserveInterfaceList:add")
	@GetMapping("/add")
	public String add(Model model) {
		return prefix + "/add";
	}

	@RequiresPermissions("system:reserveInterfaceList:edit")
	@GetMapping("/edit/{id}")
	public String edit(Model model, @PathVariable("id") String id) {
		Map<String, Object> params = new HashMap<>();
		params.put("interfaceId", id);
		List<ReserveInterfaceListDO> reserveInterfaceListDOs = reserveInterfaceListService.list(params);
		model.addAttribute("reserveInterface", reserveInterfaceListDOs.get(0));
		return prefix+"/edit";
	}

	@Log("新增接口列表")
	@RequiresPermissions("system:reserveInterfaceList:add")
	@PostMapping("/save")
	@ResponseBody
	public R save(ReserveInterfaceListDO reserveInterfaceListDO) {
		if (reserveInterfaceListService.save(reserveInterfaceListDO) > 0) {
			return R.ok();
		}
		return R.error();
	}

	
	@Log("修改接口列表")
	@RequiresPermissions("system:reserveInterfaceList:edit")
	@PostMapping("/update")
	@ResponseBody
	public R update(ReserveInterfaceListDO reserveInterfaceListDO) {
		if (reserveInterfaceListService.update(reserveInterfaceListDO) > 0) {
			return R.ok();
		}
		return R.error();
	}
	
	
	@Log("删除接口列表")
	@RequiresPermissions("system:reserveInterfaceList:remove")
	@PostMapping("/remove")
	@ResponseBody
	public R remove(String interfaceId) {
		if (reserveInterfaceListService.remove(interfaceId) > 0) {
			return R.ok();
		}
		return R.error();
	}

	@Log("删除接口列表")
	@RequiresPermissions("system:reserveInterfaceList:remove")
	@PostMapping("/batchRemove")
	@ResponseBody
	public R batchRemove(@RequestParam("ids[]") String[] interfaceIds) {
		if (reserveInterfaceListService.batchRemove(interfaceIds) > 0) {
			return R.ok();
		}
		return R.error();
	}
	
}
