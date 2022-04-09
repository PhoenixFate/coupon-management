var prefix = ctx + "channelInterfaceLog"

layui.use('table', function(){
	var table = layui.table;
	var tableObj = table.render({
		elem: '#exampleTable',
	    url: prefix + "/listStatistic",
	    where:{
	    	statisticType:2,
	    	channelCode:$("#channelCode").val(),
	    	startTime:$("#startTime").val(),
	    	endTime:$("#endTime").val()
	    },
	    limit:10,
	    cols: [[
	      {field:'businessName', title: '业务名称', sort: true},
	      {field:'statisticCount', title: '请求次数'}
//	      {field:'businessType', title: '操作',
//	    	  templet: function(row){
//	    		  var d = '<a class="layui-btn layui-btn-xs " lay-event="edit"  title="详细" onclick="detail(\''
//						+ row.businessType + '\')"><i class="fa fa-edit"></i></a> ';
//				  return d;
//	      		}
//	      }
	    ]],
	    page: {
		    curr: 1 //重新从第 1 页开始
		}
	});
	//查询、刷新
	$("#reload").on("click",function(){
		tableObj.reload({
			where: { 
				statisticType:2,
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

//function detail(id) {
//	var index = layer.open({
//		type : 2,
//		title : '详细',
//		maxmin : true,
//		shadeClose : false, // 点击遮罩关闭层
//		area : [ '800px', '480px' ],
//		content : prefix + '/' + $("#channelCode").val() + '/' + id // iframe的url
//	});
//	layer.full(index);
//}

layui.use('laydate', function(){
	var laydate = layui.laydate;
	laydate.render({ 
		  elem: "#startTime"
		});
	laydate.render({ 
		  elem: "#endTime"
		});
});
