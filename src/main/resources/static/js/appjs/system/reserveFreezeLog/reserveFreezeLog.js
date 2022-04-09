var prefix = ctx + "reserveFreezeLog"

layui.use('table', function(){
	var table = layui.table;
	var tableObj = table.render({
		elem: '#exampleTable',
	    url: prefix + "/list",
	    where:{
	    	channelCode:$("#channelCode").val(),
	    	startTime:$("#startTime").val(),
	    	endTime:$("#endTime").val()
	    },
	    limit:10,
	    cols: [[
	      {field:'channelName', title: '渠道名称', sort: true},
	      {field:'descr', title: '冻结描述'},
	      {field:'createTime', title: '冻结时间',type:'date'},
	      {field:'freezeEndTime', title: '冻结截止时间', type:"date"}
	    ]],
	    page: {
		    curr: 1 //重新从第 1 页开始
		}
	});
	//查询、刷新
	$("#reload").on("click",function(){
		tableObj.reload({
			where: { 
				channelCode:$("#channelCode").val(),
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