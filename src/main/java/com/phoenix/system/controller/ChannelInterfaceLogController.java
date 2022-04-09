package com.phoenix.system.controller;

import com.phoenix.common.annotation.Log;
import com.phoenix.common.controller.BaseController;
import com.phoenix.common.utils.DateTimeUtil;
import com.phoenix.common.utils.Query;
import com.phoenix.common.utils.R;
import com.phoenix.core.utils.DateUtils;
import com.phoenix.system.domain.ChannelInterfaceLogDO;
import com.phoenix.system.domain.ReserveChannelDO;
import com.phoenix.system.service.ChannelInterfaceLogService;
import com.phoenix.system.service.ReserveChannelService;
import com.phoenix.system.vo.ChannelInterfaceLogStatisticVO;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/channelInterfaceLog")
public class ChannelInterfaceLogController extends BaseController {
	private String prefix = "system/channelInterfaceLog";
	
	@Autowired
	private ChannelInterfaceLogService channelInterfaceLogService ;
	
	@Autowired
	private ReserveChannelService reserveChannelService;
	
	@Log("查看交易日志")
	@GetMapping({"","/{channelCode}/{businessType}"})
	@RequiresPermissions("system:channelInterfaceLog:channelInterfaceLog")
	public String channelInterfaceLog(@PathVariable(required = false) String channelCode,@PathVariable(required = false) String businessType,Model model) {
		List<ReserveChannelDO> reserveChannelDOs = reserveChannelService.list(null);
		model.addAttribute("reserveChannels", reserveChannelDOs);
		model.addAttribute("channelCode", "");
		model.addAttribute("businessType", "");
		if (StringUtils.isNotBlank(channelCode)) {
			model.addAttribute("channelCode", channelCode);
		}
		if (StringUtils.isNotBlank(businessType)) {
			model.addAttribute("businessType", businessType);
		}
		return prefix + "/channelInterfaceLog";
	}
	
	@GetMapping("/statistics")
	@RequiresPermissions("system:channelInterfaceLog:channelInterfaceLog")
	public String channelStatistics(Model model) {
		List<ReserveChannelDO> reserveChannelDOs = reserveChannelService.list(null);
		model.addAttribute("reserveChannels", reserveChannelDOs);
		return prefix + "/channelStatistics";
	}
	
	@GetMapping("/statistics/{channelCode}/{time}")
	@RequiresPermissions("system:channelInterfaceLog:channelInterfaceLog")
	public String businessStatistics(@PathVariable("channelCode") String channelCode,@PathVariable("time") String time,Model model) {
		List<ReserveChannelDO> reserveChannelDOs = reserveChannelService.list(null);
		model.addAttribute("reserveChannels", reserveChannelDOs);
		model.addAttribute("channelCode", channelCode);
		if (StringUtils.isNotBlank(time)) {
			if (time.endsWith("|")) {
				model.addAttribute("startTime", time.substring(0,time.indexOf("|")));
			} else {
				String[] timeArray = time.split("\\|");
				if (null !=timeArray && timeArray.length > 0) {
					model.addAttribute("startTime", timeArray[0]);
					model.addAttribute("endTime", timeArray[1]);
				}
			}
		}
		return prefix + "/businessStatistics";
	}
	
	@ResponseBody
	@GetMapping("/listStatistic")
	@RequiresPermissions("system:channelInterfaceLog:channelInterfaceLog")
	public R listStatistic(@RequestParam Map<String, Object> params) {
		if (null != params.get("endTime") && StringUtils.isNotBlank(params.get("endTime").toString())) {
			params.put("endTime", DateTimeUtil.getDate(1, Calendar.DAY_OF_YEAR, params.get("endTime").toString(), DateUtils.FORMAT_yyyy_MM_dd));
		}
		Query query = new Query(params);
		List<ChannelInterfaceLogStatisticVO> list = channelInterfaceLogService.listStatistic(query);
		int count = channelInterfaceLogService.countStatistic(params);
		return R.ok().put("count", count).put("data", list);
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("system:channelInterfaceLog:channelInterfaceLog")
	public R list(@RequestParam Map<String, Object> params) {
		if (null != params.get("endTime") && StringUtils.isNotBlank(params.get("endTime").toString())) {
			params.put("endTime", DateTimeUtil.getDate(1, Calendar.DAY_OF_YEAR, params.get("endTime").toString(), DateUtils.FORMAT_yyyy_MM_dd));
		}
		Query query = new Query(params);
		List<ChannelInterfaceLogDO> list = channelInterfaceLogService.list(query);
		int count = channelInterfaceLogService.count(params);
		return R.ok().put("count", count).put("data", list);
	}
	
	@ResponseBody
	@GetMapping("/errorList")
	@RequiresPermissions("system:channelInterfaceLog:channelInterfaceLog")
	public R errorList(@RequestParam Map<String, Object> params) {
		params.put("rspCode", "-1");
		Query query = new Query(params);
		List<ChannelInterfaceLogDO> list = channelInterfaceLogService.list(query);
		int count = channelInterfaceLogService.count(params);
		return R.ok().put("count", count).put("data", list);
	}
}
