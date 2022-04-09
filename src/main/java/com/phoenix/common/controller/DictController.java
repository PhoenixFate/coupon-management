package com.phoenix.common.controller;

import java.util.HashMap;
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

import com.google.common.collect.ImmutableMap;
import com.phoenix.common.domain.DictDO;
import com.phoenix.common.service.DictService;
import com.phoenix.common.service.DictTypeService;
import com.phoenix.common.utils.GlobalParamUtil;
import com.phoenix.common.utils.Query;
import com.phoenix.common.utils.R;

@Controller
@RequestMapping("/dict")
public class DictController extends BaseController {
	@Autowired
	private DictService dictService;
	@Autowired
	private DictTypeService dictTypeService;

	@GetMapping()
	@RequiresPermissions("common:dict:dict")
	String dict(Model model) {
		model.addAttribute("dictTypeList", dictTypeService.listType());
		return "common/dict/dict";
	}

	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("common:dict:dict")
	public R list(@RequestParam Map<String, Object> params) {
		// 查询列表数据
		Query query = new Query(params);
		List<DictDO> dictList = dictService.list(query);
		int total = dictService.count(query);
		return R.ok().put("count", total).put("data", dictList);
	}

	@GetMapping("/add")
	@RequiresPermissions("common:dict:add")
	String add(Model model) {
		model.addAttribute("dictTypeList", dictTypeService.listType());
		return "common/dict/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("common:dict:edit")
	String edit(@PathVariable("id") String dictId, Model model) {
		model.addAttribute("dictTypeList", dictTypeService.listType());
		DictDO dict = dictService.get(dictId);
		model.addAttribute("dict", dict);
		return "common/dict/edit";
	}

	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("common:dict:add")
	public R save(DictDO dict) {
		if (dictService.save(dict) > 0) {
			return R.ok();
		}
		return R.error();
	}

	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("common:dict:edit")
	public R update(DictDO dict) {
		dictService.update(dict);
		return R.ok();
	}

	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ResponseBody
	@RequiresPermissions("common:dict:remove")
	public R remove(String dictId) {
		if (dictService.remove(dictId) > 0) {
			return R.ok();
		}
		return R.error();
	}

	/**
	 * 删除
	 */
	@PostMapping("/batchRemove")
	@ResponseBody
	@RequiresPermissions("common:dict:remove")
	public R remove(@RequestParam("ids[]") String[] dictIds) {
		dictService.batchRemove(dictIds);
		return R.ok();
	}


	// 类别已经指定增加
	@GetMapping("/add/{type}/{description}")
	@RequiresPermissions("common:dict:add")
	String addD(Model model, @PathVariable("type") String type, @PathVariable("description") String description) {
		model.addAttribute("type", type);
		model.addAttribute("description", description);
		return "common/dict/add";
	}

	@ResponseBody
	@GetMapping("/list/{type}")
	public List<DictDO> listByType(@PathVariable("type") String type) {
		// 查询列表数据
		Map<String, Object> map = new HashMap<>(16);
		map.put("type", type);
		List<DictDO> dictList = dictService.list(map);
		return dictList;
	}
	
	
	@ResponseBody
	@GetMapping("/selectList")
	@RequiresPermissions("common:dict:dict")
	public List<DictDO> selectList(@RequestParam("dictType") String dictType) {
		// 查询列表数据
		List<DictDO> dictList = dictService.list(ImmutableMap.of("dictType", dictType));
		return dictList;
	}
	
	/************************************************关联机构的字典****************************************************/
	
	@GetMapping("/dictWithId")
	@RequiresPermissions("system:dict:dict")
	String dictWithId(Model model) {
		model.addAttribute("dictTypeList", dictTypeService.listType());
		return "system/dict/dict";
	}

	@ResponseBody
	@GetMapping("/listWithId")
	@RequiresPermissions("system:dict:dict")
	public R listWithId(@RequestParam Map<String, Object> params) {
		// 查询列表数据
		params.put("id", GlobalParamUtil.getParam("orgCode"));
		Query query = new Query(params);
		List<DictDO> dictList = dictService.list(query);
		int total = dictService.count(query);
		return R.ok().put("count", total).put("data", dictList);
	}

	@GetMapping("/addWithId")
	@RequiresPermissions("system:dict:add")
	String addWithId(Model model) {
		model.addAttribute("dictTypeList", dictTypeService.listType());
		return "system/dict/add";
	}

	@GetMapping("/editWithId/{id}")
	@RequiresPermissions("system:dict:edit")
	String editWithId(@PathVariable("id") String dictId, Model model) {
		model.addAttribute("dictTypeList", dictTypeService.listType());
		DictDO dict = dictService.get(dictId);
		model.addAttribute("dict", dict);
		return "system/dict/edit";
	}

	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/saveWithId")
	@RequiresPermissions("system:dict:add")
	public R saveWithId(DictDO dict) {
		dict.setId(GlobalParamUtil.getParam("orgCode"));
		if (dictService.save(dict) > 0) {
			return R.ok();
		}
		return R.error();
	}

	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/updateWithId")
	@RequiresPermissions("system:dict:edit")
	public R updateWithId(DictDO dict) {
		dictService.update(dict);
		return R.ok();
	}

	/**
	 * 删除
	 */
	@PostMapping("/removeWithId")
	@ResponseBody
	@RequiresPermissions("system:dict:remove")
	public R removeWithId(String dictId) {
		if (dictService.remove(dictId) > 0) {
			return R.ok();
		}
		return R.error();
	}

	/**
	 * 删除
	 */
	@PostMapping("/batchRemoveWithId")
	@ResponseBody
	@RequiresPermissions("system:dict:remove")
	public R removeWithId(@RequestParam("ids[]") String[] dictIds) {
		dictService.batchRemove(dictIds);
		return R.ok();
	}
}
