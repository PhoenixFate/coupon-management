package com.phoenix.coupon.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.phoenix.common.annotation.Log;
import com.phoenix.common.domain.OrgDO;
import com.phoenix.common.service.OrgService;
import com.phoenix.common.utils.GlobalParamUtil;
import com.phoenix.common.utils.Query;
import com.phoenix.common.utils.R;
import com.phoenix.coupon.domain.CouponInfoDO;
import com.phoenix.coupon.service.CouponInfoService;
import com.phoenix.coupon.service.StatisticService;

/**
 * 支付订单表
 * 
 * @author phoenix
 * @email sm516116978@outlook.com
 * @date 2019-03-14 13:15:51
 */
 
@Controller
@RequestMapping("/coupon/statistic")
public class StatisticController {
	
	@Autowired
	private StatisticService statisticService;
	
	@Autowired
	private CouponInfoService couponInfoService;
	
	@Autowired
	private OrgService orgService;
	
	
	@Log("发券统计")
	@GetMapping("/sumSend")
	@RequiresPermissions("coupon:statistic:sumSend")
	String sumSend(Model model){
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("couponStatusList", Arrays.asList("1,2".split(",")));
		//params.put("channelCode", GlobalParamUtil.getParam("channelCode"));
		List<CouponInfoDO> couponList = couponInfoService.list(params);
		StringBuffer content = new StringBuffer();
		content.append("[{field:'tradeDate', title: '发券日期', fixed: 'left'},");
		if (null != couponList){
			/*Map<String, String> couponMap = new HashMap<>();
			couponMap.put("85df6862031b4ae392012bc6ad860e56", "couponyz");
			couponMap.put("45281535a1a342f08111329f80d81de3", "couponam");*/
			for (int i =0; i<couponList.size(); i++) {
				content.append("{field:'").append(couponList.get(i).getCouponId()).append("',title:'").append(couponList.get(i).getCouponName()).append("'},");
				
			/*	content.append("{field:'").append(couponMap.get(couponList.get(i).getCouponId())).append("',title:'").append(couponList.get(i).getCouponName()).append("'")
				.append(",templet: function(row){if(null!=row.").append(couponMap.get(couponList.get(i).getCouponId()))
				.append("){return'<span>'+row.").append(couponMap.get(couponList.get(i).getCouponId())).append("+'</span>';}else{return'<span>0</span>';}}")
				.append("},");*/
			}
			String tableCols = content.toString();
			tableCols = tableCols.substring(0, tableCols.length() -1 ) + "]";
			JSONArray jsonArray = JSONArray.parseArray(tableCols); 
			model.addAttribute("tableCols", jsonArray);
		}
	    return "coupon/statistic/sumSend";
	}
	
	@Log("发券统计")
	@ResponseBody
	@RequestMapping("/sumSendList")
	@RequiresPermissions("coupon:statistic:sumSend")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
		params.put("channelCode", GlobalParamUtil.getParam("channelCode"));
        Query query = new Query(params);
		return statisticService.sumSendList(query);
	}
	
	@Log("核销统计")
	@GetMapping("/sumConsume")
	@RequiresPermissions("coupon:statistic:sumConsume")
	String sumConsume(Model model){
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("couponStatusList", Arrays.asList("1,2".split(",")));
		//params.put("channelCode", GlobalParamUtil.getParam("channelCode"));
		List<CouponInfoDO> couponList = couponInfoService.list(params);
		StringBuffer content = new StringBuffer();
		content.append("[{field:'tradeDate', title: '核销日期', fixed: 'left'},");
		if (null != couponList){
			for (int i =0; i<couponList.size(); i++) {
				content.append("{field:'").append(couponList.get(i).getCouponId()).append("',title:'").append(couponList.get(i).getCouponName()).append("'},");
				
			}
			String tableCols = content.toString();
			tableCols = tableCols.substring(0, tableCols.length() -1 ) + "]";
			JSONArray jsonArray = JSONArray.parseArray(tableCols); 
			model.addAttribute("tableCols", jsonArray);
		}
	    return "coupon/statistic/sumConsume";
	}
	@Log("核销统计")
	@ResponseBody
	@RequestMapping("/sumConsumeList")
	@RequiresPermissions("coupon:statistic:sumConsume")
	public R sumConsumeList(@RequestParam Map<String, Object> params){
		//查询列表数据
		params.put("channelCode", GlobalParamUtil.getParam("channelCode"));
        Query query = new Query(params);
		return statisticService.sumConsumeList(query);
	}
	
	@Log("消费撤销统计")
	@GetMapping("/sumCancel")
	@RequiresPermissions("coupon:statistic:sumCancel")
	String sumCancel(Model model){
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("couponStatusList", Arrays.asList("1,2".split(",")));
		//params.put("channelCode", GlobalParamUtil.getParam("channelCode"));
		List<CouponInfoDO> couponList = couponInfoService.list(params);
		
		StringBuffer content = new StringBuffer();
		content.append("[{field:'tradeDate', title: '消费撤销日期', fixed: 'left'},");
		if (null != couponList){
			for (int i =0; i<couponList.size(); i++) {
				content.append("{field:'").append(couponList.get(i).getCouponId()).append("',title:'").append(couponList.get(i).getCouponName()).append("'},");
				
			}
			String tableCols = content.toString();
			tableCols = tableCols.substring(0, tableCols.length() -1 ) + "]";
			JSONArray jsonArray = JSONArray.parseArray(tableCols); 
			model.addAttribute("tableCols", jsonArray);
		}
		
	    return "coupon/statistic/sumCancel";
	}
	@Log("消费撤销统计")
	@ResponseBody
	@RequestMapping("/sumCancelList")
	@RequiresPermissions("coupon:statistic:sumCancel")
	public R sumCancelList(@RequestParam Map<String, Object> params){
		//查询列表数据
		params.put("channelCode", GlobalParamUtil.getParam("channelCode"));
        Query query = new Query(params);
		return statisticService.sumCancelList(query);
	}
	
	@Log("医生统计")
	@GetMapping("/sumDoctors")
	@RequiresPermissions("coupon:statistic:sumDoctors")
	String sumDoctors(Model model){
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("couponStatusList", Arrays.asList("1,2".split(",")));
		//params.put("channelCode", GlobalParamUtil.getParam("channelCode"));
		
		params.put("status", "1");
		List<OrgDO> orgs = orgService.list(params);
		model.addAttribute("orgs", orgs);
		
		List<CouponInfoDO> couponList = couponInfoService.list(params);
		StringBuffer content = new StringBuffer();
		content.append("[{field:'belogtoDoctor', title: '工号',  rowspan: 2, fixed: 'left',width:120},");
		if (null != couponList){
			for (int i =0; i<couponList.size(); i++) {
				if (i == couponList.size()-1) {
					content.append("{title:'").append(couponList.get(i).getCouponName()).append("', align: 'center' ,  colspan: 2},");
				}else {
					content.append("{title:'").append(couponList.get(i).getCouponName()).append("', align: 'center' ,  colspan: 2},");
				}
			}
			// content.append("{field: 'belogtoDoctor', title: '操作',rowspan: 2, width:120, fixed:'right'}]");
			 
			 content.append("{field:'belogtoDoctor', title: '操作', fixed: 'right', rowspan: 2, width:60, templet: function(row){if (null != row.belogtoDoctor &&  '合计' != row.belogtoDoctor){return  '<a  class=\"layui-btn layui-btn-warn '+s_cashOut_h+' layui-btn-xs \" lay-event=\"cashOut\"  title=\"提现\" onclick=\"cashOut(\\'' + row.belogtoDoctor + '\\')\"><i class=\"icon iconfont icon-icon24\"></i></a> ';}else{return  '';}}}]");
			
			//String tableCols = content.toString();
			//JSONArray jsonArray = JSONArray.parseArray(tableCols); 
			model.addAttribute("tableCols", content.toString());
			
			StringBuffer content1 = new StringBuffer();
			
			content1.append("[");
			for (int i =0; i<couponList.size(); i++) {
				if (i == couponList.size()-1) {
					content1.append("{field:'").append(couponList.get(i).getCouponId() + "1").append("',title:'").append("已兑").append("'},");
					content1.append("{field:'").append(couponList.get(i).getCouponId() + "0").append("',title:'").append("未兑").append("'}]");
				}else {
					content1.append("{field:'").append(couponList.get(i).getCouponId() + "1").append("',title:'").append("已兑").append("'},");
					content1.append("{field:'").append(couponList.get(i).getCouponId() + "0").append("',title:'").append("未兑").append("'},");
				}
			}
			
			
			//String tableCols1 = content1.toString();
			//JSONArray jsonArray1 = JSONArray.parseArray(tableCols1); 
			model.addAttribute("tableCols1", content1.toString());
		}
	    return "coupon/statistic/sumDoctors";
	}
	
	@Log("医生统计")
	@ResponseBody
	@RequestMapping("/sumDoctorsList")
	@RequiresPermissions("coupon:statistic:sumDoctors")
	public R sumDoctorsList(@RequestParam Map<String, Object> params){
		//查询列表数据
		params.put("channelCode", GlobalParamUtil.getParam("channelCode"));
        Query query = new Query(params);
		return statisticService.sumDoctorsList(query);
	}
}
