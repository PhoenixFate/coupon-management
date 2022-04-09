package com.phoenix.common.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.phoenix.common.domain.LogDO;
import com.phoenix.common.service.LogService;
import com.phoenix.common.utils.Query;
import com.phoenix.common.utils.R;

@RequestMapping("/log")
@Controller
public class LogController {
	@Autowired
	LogService logService;
	String prefix = "common/log";

	@GetMapping()
	String log() {
		return prefix + "/log";
	}

	@ResponseBody
	@GetMapping("/list")
	public R list(@RequestParam Map<String, Object> params) {
		 Query query = new Query(params);
		List<LogDO> checkInfoList = logService.list(query);
		int total = logService.count(query);
		return R.ok().put("count", total).put("data", checkInfoList);
	}
}
