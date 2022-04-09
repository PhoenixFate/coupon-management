$(function(){
	chartPay();
	//180秒刷新一次
	setInterval(function(){
		self.location.reload();
	},180*1000);
});
	  
function chartPay(){
	var chart = echarts.init(document.getElementById("payChart"),'macarons');
	var option = {
			title : {
				text: '最近一周数据统计'
			},
		    tooltip: {
		        trigger: 'axis'
		    },
		    legend: {
		        data:['发券数','核销额']
		    },
		    calculable : true,
		    xAxis: {
		    	type : 'category',
		    	boundaryGap : false,
		    	data : statistics.dateList
		    },
		    yAxis: [{
		        type: 'value', 
		        name : '张数',
	            axisLabel : {
	                formatter: '{value} 张'
	            }
		    },
		    {
		       /* type: 'value', 
		        name : '张数',
	            axisLabel : {
	                formatter: '{value} 张'
	            }*/
		    }],
		    series: [
		        {
		            name:'发券数',
		            type:'line',
		            data:statistics.sendList ,
		            yAxisIndex: 0,
		            smooth:true,
		            itemStyle: {normal: {areaStyle: {type: 'default'}}}
		        },
		        {
		            name:'核销数',
		            type:'line',
		            data:statistics.consumeList,
		           // yAxisIndex: 1,
		            smooth:true,
		            itemStyle: {normal: {areaStyle: {type: 'default'}}}
		        }
		    ]
		};
		chart.setOption(option);
}

/*
layui.use('table', function(){
	var table = layui.table;
	var tableObj = table.render({
		elem: '#tradeErrorTable',
	    url: ctx + "paycenter/payOrder/errorList",
	    where:{
	    	page:1,
	    	limit:10
	    },
	    cols: [[
			{
				field : 'orderNumber',
				title : '订单编号',
				width : 270,
				fixed : 'left'
			},
			{
				field : 'incomeFeeYan',
				title : '实收金额',
				width : 110
			},
			{
				field : 'incometime',
				title : '收款时间',
				width : 170
			},
			{
				field : 'payMode',
				title : '支付类型',
				width : 90
			},
			{
				field : 'bizResultFlag',
				title : '业务状态',
				width : 100,
				templet : function(row) {
					if ("0" == row.bizResultFlag) {
						return '<span class="layui-badge layui-bg-red">失败</span>';
					} else if ("1" == row.bizResultFlag) {
						return '<span class="layui-badge layui-bg-green">成功</span>';
					} else if ("2" == row.bizResultFlag) {
						return '<span class="layui-badge layui-bg-red">超时</span>';
					} else if ("3" == row.bizResultFlag) {
						return '<span class="layui-badge layui-bg-orange">处理中</span>';
					} else if ("4" == row.bizResultFlag) {
						return '<span class="layui-badge layui-bg-black">未知</span>';
					} else if ("5" == row.bizResultFlag) {
						return '<span class="layui-badge layui-bg-gray">取消</span>';
					} else {
						return '';
					}
				}
			}]]
	});
	var tableObj1 = table.render({
		elem: '#logErrorTable',
	    url: ctx + "channelInterfaceLog/errorList",
	    where:{
	    	page:1,
	    	limit:10
	    },
	    cols: [[
	    	{field:'bizId', title: '订单编号', width:270,	fixed : 'left'},
		      {field:'businessName', title: '业务名称', width:140},
		      {field:'createGmt', title: '请求时间',type:'date' , width:170},
		      {field:'rspCode', title: '响应码', width:80},
		      {field:'result', title: '出参', width:250},
		      {field:'serverIp', title: '服务IP地址', width:130}
	    ]]
	});
});*/
