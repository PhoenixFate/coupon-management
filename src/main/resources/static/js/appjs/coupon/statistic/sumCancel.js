var prefix = ctx + "coupon/statistic"

var colslist;
$().ready(function() {
	colslist = eval($("#tableCols").val());
});


layui.use('table', function(){
	var table = layui.table;
	//列表页面
	var tableObj = table.render({
		elem: '#exampleTable',
	    url: prefix + "/sumCancelList",
	    where:{
	    	startTime:$("#startTime").val(),
	    	endTime:$("#endTime").val()
	    },
	    cols: [
	    	colslist
	    ]
	});

	//查询、刷新
	$("#reload").on("click",function(){
		tableObj.reload({
			where: { 
		    	startTime:$("#startTime").val(),
		    	endTime:$("#endTime").val()
			}
		});
	});
});


layui.use('laydate', function(){
	var laydate = layui.laydate;
	laydate.render({
	    elem: '#startTime'
	    ,format: 'yyyy-MM-dd'
	    ,value:new Date()
	  });

	laydate.render({
	    elem: '#endTime'
	    ,format: 'yyyy-MM-dd'
	    ,value:new Date()
	  });
});
