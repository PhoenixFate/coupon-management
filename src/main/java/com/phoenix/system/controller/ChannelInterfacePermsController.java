package com.phoenix.system.controller;

import com.phoenix.common.annotation.Log;
import com.phoenix.common.controller.BaseController;
import com.phoenix.common.utils.GlobalParamUtil;
import com.phoenix.common.utils.R;
import com.phoenix.system.domain.ChannelInterfacePermsDO;
import com.phoenix.system.domain.ReserveInterfaceListDO;
import com.phoenix.system.service.ChannelInterfacePermsService;
import com.phoenix.system.service.ReserveInterfaceListService;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Controller
@RequestMapping("/channelInterfacePerms")
public class ChannelInterfacePermsController extends BaseController {
	private String prefix = "system/reserveChannel";
	
	@Autowired
	private ChannelInterfacePermsService channelInterfacePermsService;
	
	@Autowired
	private ReserveInterfaceListService reserveInterfaceListService;
	
	@Log("查看渠道权限")
	@GetMapping("/{channelCode}")
	@RequiresPermissions("system:channelInterfacePerms:channelInterfacePerms")
	String channelInterfacePerms(@PathVariable("channelCode") String channelCode, Model model) {
		model.addAttribute("channelCode", channelCode);
		return prefix + "/channelInterfacePerms";
	}

	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("system:channelInterfacePerms:channelInterfacePerms")
	public R list(@RequestParam Map<String, Object> params) {
		List<ReserveInterfaceListDO> list = reserveInterfaceListService.list(null);
		params.put("orgCode", GlobalParamUtil.getParam("orgCode"));
		List<ChannelInterfacePermsDO> permsDOs = channelInterfacePermsService.list(params);
		List<String> interfaceIds = permsDOs.stream().map(ChannelInterfacePermsDO::getInterfaceListId).collect(Collectors.toList());
		for(ReserveInterfaceListDO reserveInterfaceListDO : list) {
			reserveInterfaceListDO.setChecked(false);
			if (interfaceIds.contains(reserveInterfaceListDO.getInterfaceId())) {
				reserveInterfaceListDO.setChecked(true);
			}
		}
		return R.ok().put("data", list);
	}

	
	@Log("保存渠道权限")
	@RequiresPermissions("system:channelInterfacePerms:add")
	@PostMapping("/batchSave")
	@ResponseBody
	public R batchSave(@RequestParam(value="ids[]",required=false) String[] interfaceIds,@RequestParam("channelCode") String channelCode) {
		if (channelInterfacePermsService.batchSave(interfaceIds,channelCode) > 0) {
			return R.ok();
		}
		return R.error();
	}
	
}
