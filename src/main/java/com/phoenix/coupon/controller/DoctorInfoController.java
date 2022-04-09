package com.phoenix.coupon.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.phoenix.coupon.domain.DoctorInfoDO;
import com.phoenix.coupon.service.DoctorInfoService;
import com.phoenix.common.utils.R;
import com.google.common.collect.ImmutableMap;
import com.phoenix.common.domain.OrgDO;
import com.phoenix.common.domain.UserDO;
import com.phoenix.common.service.OrgService;
import com.phoenix.common.utils.Query;
import com.phoenix.core.utils.UUIDUtil;

/**
 * 医生信息表
 * 
 * @author phoenix
 * @email sm516116978@outlook.com
 * @date 2019-07-11 10:44:01
 */
 
@Controller
@RequestMapping("/coupon/doctorInfo")
public class DoctorInfoController {
	@Autowired
	private OrgService orgService;
	
	@Autowired
	private DoctorInfoService doctorInfoService;
	
	@GetMapping()
	@RequiresPermissions("coupon:doctorInfo:doctorInfo")
	String DoctorInfo(Model model){
		Map<String, Object> params = new HashMap<>();
		List<OrgDO> orgs = orgService.list(params);
		model.addAttribute("orgs", orgs);
	    return "coupon/doctorInfo/doctorInfo";
	}
	
	
	@GetMapping("/list")
	@RequiresPermissions("coupon:doctorInfo:doctorInfo")
	@ResponseBody
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
       // params.put("orgCode", GlobalParamUtil.getParam("orgCode"));
        Query query = new Query(params);
		List<DoctorInfoDO> doctorInfoList = doctorInfoService.list(query);
		int total = doctorInfoService.count(query);
		return R.ok().put("count", total).put("data", doctorInfoList);
	}
	
	@GetMapping("/add")
	@RequiresPermissions("coupon:doctorInfo:doctorInfo")
	String add(Model model){
		HashMap<String, Object> docMethodParam = new HashMap<String, Object>();
		model.addAttribute("hosCodes", orgService.list(docMethodParam));
	    return "coupon/doctorInfo/add";
	}

	@GetMapping("/edit/{docApplyId}")
	@RequiresPermissions("coupon:doctorInfo:doctorInfo")
	String edit(@PathVariable("docApplyId") String docApplyId,Model model){
		DoctorInfoDO doctorInfo = doctorInfoService.get(docApplyId);
		model.addAttribute("doctorInfo", doctorInfo);
		HashMap<String, Object> docMethodParam = new HashMap<String, Object>();
		model.addAttribute("hosCodes", orgService.list(docMethodParam));
	    return "coupon/doctorInfo/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("coupon:doctorInfo:doctorInfo")
	public R save( DoctorInfoDO doctorInfo){
	//	doctorInfo.setHosCode(GlobalParamUtil.getParam("orgCode"));
		doctorInfo.setDocApplyId(UUIDUtil.getUUID());
		/*if("0".equals(doctorInfo.getDoctorType())) doctorInfo.setDoctorTypeName("医生");
		else doctorInfo.setDoctorTypeName("护士");*/
		doctorInfo.setCreateTime(new Date());
		doctorInfo.setAuditStatus("1");
		
		/*HashMap<String, Object> params = new HashMap<>();
		params.put("orgCode",doctorInfo.getHosCode());
		List<OrgDO> orgList = orgService.list(params);
		doctorInfo.setHosName(orgList.get(0).getOrgName());*/
		
		UserDO principal = (UserDO) SecurityUtils.getSubject().getPrincipal();
		doctorInfo.setAuditUser(principal.getUserName());
		if(doctorInfoService.save(doctorInfo)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("coupon:doctorInfo:doctorInfo")
	public R update( DoctorInfoDO doctorInfo){
		doctorInfo.setUpdateTime(new Date());
		
		UserDO principal = (UserDO) SecurityUtils.getSubject().getPrincipal();
		doctorInfo.setAuditUser(principal.getUserName());
		List<DoctorInfoDO> list = doctorInfoService.list(ImmutableMap.of("hosCode",doctorInfo.getHosCode(),"docCode",doctorInfo.getDocCode()));
		
		if (null != list && !list.isEmpty()) {
			
			if(list.size() >1) {
				return R.error("该医院医生编码重复");
			}else if (!list.get(0).getDocApplyId().equals(doctorInfo.getDocApplyId())) {
				return R.error("该医院医生编码重复");
			}
		}
		doctorInfoService.update(doctorInfo);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("coupon:doctorInfo:doctorInfo")
	public R remove( String docApplyId){
		if(doctorInfoService.remove(docApplyId)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("coupon:doctorInfo:doctorInfo")
	public R remove(@RequestParam("ids[]") String[] docApplyIds){
		doctorInfoService.batchRemove(docApplyIds);
		return R.ok();
	}
	
	@PostMapping("/exit")
	@ResponseBody
	public boolean exit(@RequestParam Map<String, Object> params) {
		// 存在，不通过，false
		return !doctorInfoService.exit(params);
	}
	
}
