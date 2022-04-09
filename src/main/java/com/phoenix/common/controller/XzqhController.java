package com.phoenix.common.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.phoenix.common.domain.XzqhDO;
import com.phoenix.common.service.XzqhService;
import com.phoenix.common.utils.R;

@RequestMapping("/xzqh")
@Controller
public class XzqhController {
	@Autowired
	private XzqhService xzqhService;

	@ResponseBody
	@PostMapping("/selectXzqh")
	public R list(@RequestParam Map<String, Object> params) {
		List<XzqhDO> xzqhs = xzqhService.selectXzqh(params);
		return R.ok().put("data", xzqhs);
	}
}
