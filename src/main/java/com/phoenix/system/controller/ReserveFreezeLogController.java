package com.phoenix.system.controller;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.phoenix.common.controller.BaseController;
import com.phoenix.common.utils.DateTimeUtil;
import com.phoenix.common.utils.Query;
import com.phoenix.common.utils.R;
import com.phoenix.core.utils.DateUtils;
import com.phoenix.system.domain.ReserveChannelDO;
import com.phoenix.system.domain.ReserveFreezeLogDO;
import com.phoenix.system.service.ReserveChannelService;
import com.phoenix.system.service.ReserveFreezeLogService;

@Controller
@RequestMapping("/reserveFreezeLog")
public class ReserveFreezeLogController extends BaseController {
	private String prefix = "system/reserveFreezeLog";
	
	@Autowired
	private ReserveFreezeLogService reserveFreezeLogService;
	
	@Autowired
	private ReserveChannelService reserveChannelService;
	
	@GetMapping("")
	@RequiresPermissions("system:reserveFreezeLog:reserveFreezeLog")
	public String reserveFreezeLog(Model model) {
		//List<ReserveChannelDO> reserveChannelDOs = reserveChannelService.list(ImmutableMap.of("orgCode", GlobalParamUtil.getParam("orgCode")));
		List<ReserveChannelDO> reserveChannelDOs = reserveChannelService.list(new HashMap<String, Object>());
		model.addAttribute("reserveChannels", reserveChannelDOs);
		return prefix + "/reserveFreezeLog";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("system:reserveFreezeLog:reserveFreezeLog")
	public R list(@RequestParam Map<String, Object> params) {
		if (null != params.get("endTime") && StringUtils.isNotBlank(params.get("endTime").toString())) {
			params.put("endTime", DateTimeUtil.getDate(1, Calendar.DAY_OF_YEAR, params.get("endTime").toString(), DateUtils.FORMAT_yyyy_MM_dd));
		}
		Query query = new Query(params);
		List<ReserveFreezeLogDO> list = reserveFreezeLogService.list(query);
		int count = reserveFreezeLogService.count(params);
		return R.ok().put("count", count).put("data", list);
	}
	
}
