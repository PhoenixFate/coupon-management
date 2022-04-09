package com.phoenix.system.controller;

import com.google.common.collect.ImmutableMap;
import com.phoenix.common.controller.BaseController;
import com.phoenix.common.utils.GlobalParamUtil;
import com.phoenix.common.utils.Query;
import com.phoenix.common.utils.R;
import com.phoenix.system.domain.ReserveChannelDO;
import com.phoenix.system.domain.ReserveChannelStatisticDO;
import com.phoenix.system.service.ReserveChannelService;
import com.phoenix.system.service.ReserveChannelStatisticService;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/reserveChannelStatistic")
public class ReserveChannelStatisticController extends BaseController {
	private String prefix = "system/reserveChannelStatistic";
	
	@Autowired
	private ReserveChannelStatisticService reserveChannelStatisticService ;
	
	@Autowired
	private ReserveChannelService reserveChannelService;
	
	@GetMapping("")
	@RequiresPermissions("system:reserveChannelStatistic:reserveChannelStatistic")
	public String reserveChannelStatistic(Model model) {
		List<ReserveChannelDO> reserveChannelDOs = reserveChannelService.list(ImmutableMap.of("orgCode", GlobalParamUtil.getParam("orgCode")));
		model.addAttribute("reserveChannels", reserveChannelDOs);
		return prefix + "/reserveChannelStatistic";
	}
	
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("system:reserveChannelStatistic:reserveChannelStatistic")
	public R list(@RequestParam Map<String, Object> params) {
		Query query = new Query(params);
		List<ReserveChannelStatisticDO> list = reserveChannelStatisticService.list(query);
		int count = reserveChannelStatisticService.count(params);
		return R.ok().put("count", count).put("data", list);
	}
	
	
}
