package com.phoenix.common.controller;

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

import com.phoenix.common.domain.DictTypeDO;
import com.phoenix.common.service.DictTypeService;
import com.phoenix.common.utils.Query;
import com.phoenix.common.utils.R;


@Controller
@RequestMapping("/dictType")
public class DictTypeController extends BaseController {
	@Autowired
	private DictTypeService dictTypeService;

	@GetMapping()
	@RequiresPermissions("common:dictType:dictType")
	String dictType() {
		return "common/dictType/dictType";
	}

	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("common:dictType:dictType")
	public R list(@RequestParam Map<String, Object> params) {
		// 查询列表数据
		Query query = new Query(params);
		List<DictTypeDO> dictTypeList = dictTypeService.list(query);
		int total = dictTypeService.count(query);
		return R.ok().put("count", total).put("data", dictTypeList);
	}

	@GetMapping("/add")
	@RequiresPermissions("common:dictType:add")
	String add() {
		return "common/dictType/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("common:dictType:edit")
	String edit(@PathVariable("id") String dictId, Model model) {
		DictTypeDO dictType = dictTypeService.get(dictId);
		model.addAttribute("dictType", dictType);
		return "common/dictType/edit";
	}

	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("common:dictType:add")
	public R save(DictTypeDO dictType) {
		int result = dictTypeService.save(dictType);
		if (result > 0) {
			return R.ok();
		}else if (result == -1) {
			return R.error("编码类型已存在,新增失败");
		}
		return R.error();
	}

	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("common:dictType:edit")
	public R update(DictTypeDO dictType) {
		int result = dictTypeService.update(dictType);
		if (result > 0) {
			return R.ok();
		}else if (result == -1) {
			return R.error("编码类型已存在,修改失败");
		}
		return R.error();
	}

	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ResponseBody
	@RequiresPermissions("common:dictType:remove")
	public R remove(String typeId) {
		int result = dictTypeService.remove(typeId);
		if (result > 0) {
			return R.ok();
		}else if (result == -1){
			return R.ok("该字典类型被引用,请解除关系后操作");
		}
		return R.error();
	}

	/**
	 * 删除
	 */
	@PostMapping("/batchRemove")
	@ResponseBody
	@RequiresPermissions("common:dictType:remove")
	public R remove(@RequestParam("ids[]") String[] typeIds) {
		int result = dictTypeService.batchRemove(typeIds);
		if (result > 0) {
			return R.ok();
		}else if (result == -1){
			return R.ok("有字典类型被引用,请解除关系后操作");
		}
		return R.error();
	}

	@GetMapping("/type")
	@ResponseBody
	public List<DictTypeDO> listType() {
		return dictTypeService.listType();
	}

}
	
