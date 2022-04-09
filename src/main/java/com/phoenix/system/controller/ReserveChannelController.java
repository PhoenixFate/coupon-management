package com.phoenix.system.controller;

import com.phoenix.common.annotation.Log;
import com.phoenix.common.controller.BaseController;
import com.phoenix.common.utils.GlobalParamUtil;
import com.phoenix.common.utils.Query;
import com.phoenix.common.utils.R;
import com.phoenix.core.redis.JedisUtil;
import com.phoenix.core.utils.UUIDUtil;
import com.phoenix.system.domain.ReserveChannelDO;
import com.phoenix.system.service.ReserveChannelService;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/reserveChannel")
public class ReserveChannelController extends BaseController {
	private String prefix = "system/reserveChannel";
	
	@Autowired
	private ReserveChannelService reserveChannelService;
	
	@Log("查看渠道管理")
	@GetMapping("")
	@RequiresPermissions("system:reserveChannel:reserveChannel")
	public String reserveChannel() {
		return prefix + "/reserveChannel";
	}

	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("system:reserveChannel:reserveChannel")
	public R list(@RequestParam Map<String, Object> params) {
		params.put("orgCode", GlobalParamUtil.getParam("orgCode"));
		Query query = new Query(params);
		List<ReserveChannelDO> list = reserveChannelService.list(query);
		int count = reserveChannelService.count(params);
		return R.ok().put("count", count).put("data", list);
	}

	@RequiresPermissions("system:reserveChannel:add")
	@GetMapping("/add")
	public String add(Model model) {
		model.addAttribute("accessKeySecret", UUIDUtil.getUUID());
		return prefix + "/add";
	}

	@RequiresPermissions("system:reserveChannel:edit")
	@GetMapping("/edit/{id}")
	public String edit(Model model, @PathVariable("id") String channelId) {
		Map<String, Object> params = new HashMap<>();
		params.put("orgCode", GlobalParamUtil.getParam("orgCode"));
		params.put("channelId", channelId);
		List<ReserveChannelDO> reserveChannelList = reserveChannelService.list(params);
		model.addAttribute("reserveChannel", reserveChannelList.get(0));
		return prefix+"/edit";
	}

	@Log("新增渠道")
	@RequiresPermissions("system:reserveChannel:add")
	@PostMapping("/save")
	@ResponseBody
	public R save(ReserveChannelDO reserveChannelDO) {
		if (reserveChannelService.save(reserveChannelDO) > 0) {
			return R.ok();
		}
		return R.error();
	}
	
	@PostMapping("/checkExists")
	@ResponseBody
	public boolean checkExists(@RequestParam Map<String, Object> params) {
		params.put("orgCode", GlobalParamUtil.getParam("orgCode"));
		// 存在，不通过，false
		return !reserveChannelService.checkExists(params);
	}

	@Log("修改渠道")
	@RequiresPermissions("system:reserveChannel:edit")
	@PostMapping("/update")
	@ResponseBody
	public R update(ReserveChannelDO reserveChannelDO) {
		if (reserveChannelService.update(reserveChannelDO) > 0) {
			return R.ok();
		}
		return R.error();
	}
	
	@Log("修改渠道冻结状态")
	@RequiresPermissions("system:reserveChannel:edit")
	@PostMapping("/unfreeze")
	@ResponseBody
	public R unfreeze(ReserveChannelDO reserveChannelDO) {
		reserveChannelDO.setFrozenStatus("0");
		if (reserveChannelService.update(reserveChannelDO) > 0) {
			String redisKey = "COUPON:CHANNEL:FREEZE:" + reserveChannelDO.getChannelCode();
			JedisUtil.delete(redisKey); //清空redis
			Calendar cal = Calendar.getInstance();
			int minute = cal.get(Calendar.MINUTE);
			String countKey = "COUPON:CHANNEL:COUNTS:" + reserveChannelDO.getChannelCode() + ":" + minute; //累计数
			if(JedisUtil.exists(countKey))
			{
				String nums = JedisUtil.get(redisKey);
				JedisUtil.decrBy(redisKey, Long.parseLong(nums)); //清空计数
			}
			
			return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 设置后台全局渠道
	 */
	@ResponseBody
	@RequestMapping("/setGloabChannel")
	public R setGloabOrg(@RequestParam String channelCode) {
		
		Map<String, Object> params = new HashMap<>();
		params.put("channelCode", channelCode);
		List<ReserveChannelDO>  channelList = reserveChannelService.list(params);
		// 设置后台全局supplier
		if (null != channelList && !channelList.isEmpty()) {
			GlobalParamUtil.setParam("channelCode", channelCode);
			GlobalParamUtil.setParam("channelName", channelList.get(0).getChannelName());
		}
		
		return R.ok();
	}

}
