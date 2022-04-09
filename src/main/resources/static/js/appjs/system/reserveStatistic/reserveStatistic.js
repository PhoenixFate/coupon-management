var prefix = ctx + "reserveStatistic"
var cols = new Array();
$(function(){
	if($("#statisticType").val() == '1'){
		cols = [[
		    {field:'deptName',title:'科室',sort:true},
		    {field:'statisticDate',title:'统计日期'},
		    {field:'totalCount',title:'总号源数'},
		    {field:'reserveCount',title:'预约数'},
		    {field:'refundCount',title: '退约数'},
		    {field:'violateCount',title:'违约数'}
		  ]];
	} else if($("#statisticType").val() == '2'){
		cols = [[
			{field:'doctorName',title:'医生',sort:true},
		    {field:'statisticDate',title:'统计日期'},
		    {field:'totalCount',title:'总号源数'},
		    {field:'reserveCount',title:'预约数'},
		    {field:'refundCount',title: '退约数'},
		    {field:'violateCount',title:'违约数'}
		  ]];
	}
});

layui.use('table', function(){
	var table = layui.table;
	var tableObj = table.render({
		elem: '#exampleTable',
	    url: prefix + "/list",
	    where:{
	    	statisticType:$("#statisticType").val(),
	    	deptCode:$("#deptCode").val(),
	    	searchName:$("#searchName").val(),
	    	startTime:$("#startTime").val(),
	    	endTime:$("#endTime").val()
	    },
	    limit:10,
	    cols: cols,
	    page: {
		    curr: 1 //重新从第 1 页开始
		}
	});
	//查询、刷新
	$("#reload").on("click",function(){
		tableObj.reload({
			where: { 
				statisticType:$("#statisticType").val(),
		    	deptCode:$("#deptCode").val(),
		    	searchName:$("#searchName").val(),
		    	startTime:$("#startTime").val(),
		    	endTime:$("#endTime").val()
			},
			page: {
			    curr: 1 //重新从第 1 页开始
			}
		});
	});
});

layui.use('laydate', function(){
	var laydate = layui.laydate;
	laydate.render({ 
		  elem: "#startTime"
		});
	laydate.render({ 
		  elem: "#endTime"
		});
});
