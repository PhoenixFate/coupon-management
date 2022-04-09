var prefix = ctx + "channelInterfaceLog"

layui.use('table', function(){
	var table = layui.table;
	var tableObj = table.render({
		elem: '#exampleTable',
	    url: prefix + "/listStatistic",
	    where:{
	    	statisticType:1,
	    	channelCode:$("#channelCode").val(),
	    	startTime:$("#startTime").val(),
	    	endTime:$("#endTime").val()
	    },
	    limit:10,
	    cols: [[
	      {field:'channelName', title: '渠道名称', sort: true},
	      {field:'statisticCount', title: '请求次数'},
	      {field:'channelCode', title: '操作',fixed:'right',
	    	  templet: function(row){
	    		  var d = '<a class="layui-btn layui-btn-xs " lay-event="edit"  title="详细" onclick="detail(\''
						+ row.channelCode + '\')"><i class="fa fa-reorder"></i></a> ';
				  return d;
	      		}
	      }
	    ]],
	    page: {
		    curr: 1 //重新从第 1 页开始
		}
	});
	//查询、刷新
	$("#reload").on("click",function(){
		tableObj.reload({
			where: { 
				statisticType:1,
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

function detail(id) {
	var url = prefix + '/statistics/' + id + '/';
	if($("#startTime").val()){
		url += $("#startTime").val()
	}
	url +="|";
	if($("#endTime").val()){
		url += $("#endTime").val()
	}
	var index = layer.open({
		type : 2,
		title : '详细',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '480px' ],
		content : url // iframe的url
	});
	layer.full(index);
}

layui.use('laydate', function(){
	var laydate = layui.laydate;
	laydate.render({ 
		  elem: "#startTime"
		});
	laydate.render({ 
		  elem: "#endTime"
		});
});
