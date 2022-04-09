package com.phoenix.common.controller;

import com.phoenix.common.annotation.Log;
import com.phoenix.common.domain.RoleDO;
import com.phoenix.common.domain.UserDO;
import com.phoenix.common.domain.UserVO;
import com.phoenix.common.service.RoleService;
import com.phoenix.common.service.UserService;
import com.phoenix.common.utils.*;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/user")
@Controller
public class UserController extends BaseController {
	private String prefix="common/user";
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService;
	
	@Log("查看用户列表")
	@RequiresPermissions("common:user:user")
	@GetMapping("")
	public String user(Model model) {
		return prefix + "/user";
	}

	@RequiresPermissions("common:user:user")
	@GetMapping("/list")
	@ResponseBody
	public R list(@RequestParam Map<String, Object> params) {
		params.put("orgCode", GlobalParamUtil.getParam("orgCode"));
		Query query = new Query(params);
		List<UserDO> sysUserList = userService.list(query);
		int total = userService.count(params);
		return R.ok().put("count", total).put("data", sysUserList);
	}

	@RequiresPermissions("common:user:add")
	@Log("添加用户")
	@GetMapping("/add")
	public String add(Model model) {
		List<RoleDO> roles = roleService.list(new HashMap<>());
		model.addAttribute("roles", roles);
		return prefix + "/add";
	}

	@SuppressWarnings("unchecked")
	@RequiresPermissions("common:user:edit")
	@Log("编辑用户")
	@GetMapping("/edit/{id}")
	public String edit(Model model, @PathVariable("id") String userId) {
		R r = roleService.list(userId);
		List<RoleDO> roles = (List<RoleDO>)r.get("roles");
		model.addAttribute("roles", roles);
		String roleId = (String)r.get("roleId");
		UserDO userDO = userService.get(userId);
		userDO.setRoleId(roleId);
		model.addAttribute("user", userDO);
		return prefix+"/edit";
	}

	@RequiresPermissions("common:user:add")
	@Log("保存用户")
	@PostMapping("/save")
	@ResponseBody
	public R save(UserDO user) {
		if (userService.save(user) > 0) {
			return R.ok();
		}
		return R.error();
	}

	@RequiresPermissions("common:user:edit")
	@Log("更新用户")
	@PostMapping("/update")
	@ResponseBody
	public R update(UserDO user) {
		if (userService.update(user) > 0) {
			return R.ok();
		}
		return R.error();
	}


	@RequiresPermissions("common:user:edit")
	@Log("更新用户")
	@PostMapping("/updatePeronal")
	@ResponseBody
	public R updatePeronal(UserDO user) {
		if (userService.updatePersonal(user) > 0) {
			return R.ok();
		}
		return R.error();
	}


	@RequiresPermissions("common:user:remove")
	@Log("删除用户")
	@PostMapping("/remove")
	@ResponseBody
	public R remove(String userId) {
		if (userService.remove(userId) > 0) {
			return R.ok();
		}
		return R.error();
	}

	@RequiresPermissions("common:user:remove")
	@Log("批量删除用户")
	@PostMapping("/batchRemove")
	@ResponseBody
	public R batchRemove(@RequestParam("ids[]") String[] userIds) {
		int r = userService.batchremove(userIds);
		if (r > 0) {
			return R.ok();
		}
		return R.error();
	}

	@PostMapping("/checkExists")
	@ResponseBody
	public boolean checkExists(@RequestParam Map<String, Object> params) {
		// 存在，不通过，false
		return !userService.checkExists(params);
	}

	@RequiresPermissions("common:user:resetPwd")
	@Log("请求更改用户密码")
	@GetMapping("/resetPwd/{id}")
	public String resetPwd(@PathVariable("id") String userId, Model model) {
		UserDO userDO = new UserDO();
		userDO.setUserId(userId);
		model.addAttribute("user", userDO);
		return prefix + "/reset_pwd";
	}

	@Log("提交更改用户密码")
	@PostMapping("/resetPwd")
	@ResponseBody
	public R resetPwd(UserVO userVO) {
		try{
			userService.resetPwd(userVO,getUser());
			return R.ok();
		}catch (Exception e){
			return R.error(1,e.getMessage());
		}

	}
	@RequiresPermissions("common:user:resetPwd")
	@Log("admin提交更改用户密码")
	@PostMapping("/adminResetPwd")
	@ResponseBody
	public R adminResetPwd(UserVO userVO) {
		try{
			userService.adminResetPwd(userVO);
			return R.ok();
		}catch (Exception e){
			return R.error(1,e.getMessage());
		}

	}

	@GetMapping("/personal")
	public String personal(Model model) {
		UserDO userDO  = userService.get(getUserId());
		model.addAttribute("user",userDO);
	/*	model.addAttribute("hobbyList",dictService.getHobbyList(userDO));
		model.addAttribute("sexList",dictService.getSexList());*/
		return prefix + "/personal";
	}
	
}
