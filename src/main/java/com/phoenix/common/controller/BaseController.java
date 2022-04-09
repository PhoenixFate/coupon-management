package com.phoenix.common.controller;

import org.springframework.stereotype.Controller;

import com.phoenix.common.domain.UserDO;
import com.phoenix.common.utils.ShiroUtils;

@Controller
public class BaseController {
	public UserDO getUser() {
		return ShiroUtils.getUser();
	}

	public String getUserId() {
		return getUser().getUserId();
	}

	public String getUserName() {
		return getUser().getUserName();
	}
}