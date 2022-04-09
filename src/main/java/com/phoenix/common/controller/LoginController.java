package com.phoenix.common.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.phoenix.common.annotation.Log;
import com.phoenix.common.domain.MenuDO;
import com.phoenix.common.domain.Tree;
import com.phoenix.common.service.LoginService;
import com.phoenix.common.service.MenuService;
import com.phoenix.common.utils.GlobalParamUtil;
import com.phoenix.common.utils.MD5Utils;
import com.phoenix.common.utils.Query;
import com.phoenix.common.utils.R;
import com.phoenix.common.utils.ShiroUtils;
import com.phoenix.core.utils.DateUtil;
import com.phoenix.coupon.domain.CouponStatistic;
import com.phoenix.coupon.service.AccountCouponInfoService;
import com.phoenix.system.domain.ReserveChannelDO;
import com.phoenix.system.service.ReserveChannelService;
import com.phoenix.system.vo.DataStatisticVO;

@Controller
public class LoginController extends BaseController {

	@Autowired
	private MenuService menuService;

	@Autowired
	private LoginService loginService;
	
	@Autowired
	private AccountCouponInfoService accountCouponInfoService;
	
	@Autowired
	private ReserveChannelService channelService;
	
	/**
	 * 
	  * 方法描述：默认
	  * @param 
	  * @return 
	  * @version 1.0
	  * @author phoenix
	  * 2019年2月22日 下午3:54:36
	 */
	@GetMapping({ "/", "" })
	public String welcome(Model model) {
		return "redirect:/login";
	}

	
	/**
	 * 
	  * 方法描述：默认医生预约
	  * @param 
	  * @return 
	  * @version 1.0
	  * @author phoenix
	  * 2019年2月22日 下午3:57:27
	 */
	@GetMapping({ "/index" })
	public String index(Model model) {
		Map<String, Object> params = new HashMap<>();
		params.put("userId", ShiroUtils.getUserId());
		List<Tree<MenuDO>> menus = menuService.listMenuTree(params);
		model.addAttribute("menus", menus);
		model.addAttribute("picUrl", "../img/logo.png");
		model.addAttribute("username", getUser().getUserName());
		if (ShiroUtils.isAdmin()) {
			params = new HashMap<>();
		}
		params.put("status", "1");
		/*List<OrgDO> orgs = orgService.list(params);
		if(ShiroUtils.isAdmin())
		{
			OrgDO dOrg = new OrgDO();
			dOrg.setOrgCode("");
			dOrg.setOrgName("全部医院");
			orgs.add(dOrg);
		}
		model.addAttribute("orgs", orgs);
		// 设置后台全局supplier
		if (null != orgs && !orgs.isEmpty()) {
			GlobalParamUtil.setParam("orgCode", orgs.get(0).getOrgCode());
			GlobalParamUtil.setParam("orgName", orgs.get(0).getOrgName());
			GlobalParamUtil.setParam("orgField", orgs.get(0).getOrgField());
			model.addAttribute("defaultOrg", orgs.get(0).getOrgCode());
		}*/
		
		List<ReserveChannelDO>  channelList = channelService.list(params);
		if(ShiroUtils.isAdmin())
		{
			ReserveChannelDO channel = new ReserveChannelDO();
			channel.setChannelCode("");
			channel.setChannelName("全部渠道");
			channelList.add(channel);
		}
		model.addAttribute("channelList", channelList);
		// 设置后台全局supplier
		if (null != channelList && !channelList.isEmpty()) {
			GlobalParamUtil.setParam("channelCode", channelList.get(0).getChannelCode());
			GlobalParamUtil.setParam("channelName", channelList.get(0).getChannelName());
			model.addAttribute("defaultChannel", channelList.get(0).getChannelCode());
		}
		return "index_v1";
	}

	@GetMapping("/login")
	public String login() {
		return "login";
	}

	@Log("登录")
	@PostMapping("/login")
	@ResponseBody
	public R ajaxLogin(String username, String password) {
		// 用于其他系统的免密登陆
		try {
			Map<String, String> userMap = new HashMap<String, String>();
			userMap.put("username", username);
			userMap.put("password", password);
		} catch (Exception e) {
		} // -------------结束

		password = MD5Utils.encrypt(username, password);
		UsernamePasswordToken token = new UsernamePasswordToken(username, password);
		Subject subject = SecurityUtils.getSubject();
		try {
			subject.login(token);
			return R.ok();
		} catch (AuthenticationException e) {
			return R.error("用户或密码错误");
		}
	}



	@GetMapping("/logout")
	public String logout() {
		ShiroUtils.logout();
		return "redirect:/login";
	}
	
	/**
	 * 
	  * 方法描述： 这是默认的专家预约homepage
	  * @param 
	  * @return 
	  * @version 1.0
	  * @author phoenix
	  * 2019年2月22日 下午4:02:53
	 */
	@GetMapping("/main")
	public String main(@RequestParam Map<String, Object> params, Model model) {
		
		HashMap<String, Object> param = new HashMap<String, Object>();
		param.put("startTime", DateUtil.dateStartStr());
		param.put("endTime", DateUtil.dateEndStr());
		param.put("orgCode", GlobalParamUtil.getParam("orgCode"));
		
		param.put("channelCode", GlobalParamUtil.getParam("channelCode"));
		
		
		List<CouponStatistic> list = this.accountCouponInfoService.liststatistic(param);
		model.addAttribute("todayDatas", list);
		// 获取365天前的日期
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH) -365);
		
		param.put("startTime", sdf.format(cal.getTime()) + " 00:00:00");
		param.put("endTime", DateUtil.dateEndStr());
		param.put("channelCode", GlobalParamUtil.getParam("channelCode"));
		List<CouponStatistic> totalList = accountCouponInfoService.liststatisticTotal(param);
		model.addAttribute("totalDatas", totalList);
		
		
		Map<String, Object> daysList = accountCouponInfoService.list7days(param);
		model.addAttribute("statistics", daysList);
		
		
		//最新用户申诉
		params.put("page", 1);
		params.put("limit", 2);
		params.put("indexAppealStatus", 1);
		
		return "main"; 
	}
	
	public static void main(String[] args) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH) -365);
		System.out.println(sdf.format(cal.getTime()));
	}
	
	@ResponseBody
	@GetMapping("/topDoctor")
	public R topDoctor(@RequestParam Map<String, Object> params) {
		params.put("orgCode", GlobalParamUtil.getParam("orgCode"));
		Query query = new Query(params);
		List<DataStatisticVO> list = loginService.getDoctorStatistic(query);
		return R.ok().put("count", list.size()).put("data", list);
	}
	
	@ResponseBody
	@GetMapping("/topDept")
	public R topDept(@RequestParam Map<String, Object> params) {
		params.put("orgCode", GlobalParamUtil.getParam("orgCode"));
		Query query = new Query(params);
		List<DataStatisticVO> list = loginService.getDeptStatistic(query);
		return R.ok().put("count", list.size()).put("data", list);
	}
	
	@ResponseBody
	@GetMapping("/topItem")
	public R topItem(@RequestParam Map<String, Object> params) {
		params.put("orgCode", GlobalParamUtil.getParam("orgCode"));
		Query query = new Query(params);
		List<DataStatisticVO> list = loginService.getItemStatistic(query);
		return R.ok().put("count", list.size()).put("data", list);
	}

	@GetMapping("/403")
	public String error403() {
		return "403";
	}

}
