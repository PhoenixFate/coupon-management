var prefix = ctx + "channelInterfaceLog"

layui.use('table', function(){
	var table = layui.table;
	var tableObj = table.render({
		elem: '#exampleTable',
	    url: prefix + "/list",
	    height: 'full-250',
	    where:{
	    	channelCode:$("#channelCode").val(),
	    	businessName:$("#businessName").val(),
	    	startTime:$("#startTime").val(),
	    	endTime:$("#endTime").val(),
	    	rspCode:$("#rspCode").val()
	    },
	    limit:20,
	    cols: [[
	      {field:'bizId', title: '账户号 | 订单号', width:270,fixed : 'left'},
	      {field:'businessName', title: '业务名称', width:140, fixed : 'left'},
	      {field:'time', title: '响应时间', width:90, templet:function(row){
	    	  return row.time / 1000 + "秒";
	      }},
	      {field:'createGmt', title: '请求时间',type:'date' , width:170},
	      {field:'params', title: '入参', width:350},
	      {field:'rspCode', title: '响应码', width:80},
	      {field:'result', title: '出参', width:350},
	      {field:'ip', title: '请求IP地址', width:130},
	      {field:'serverIp', title: '服务IP地址', width:130},
	      {field:'channelName', title: '渠道名称', sort: true, width:180}
//	      {field:'patientId', title: '操作',
//	    	  templet: function(row){
//	    		  var e = '<a class="layui-btn  layui-btn-xs '+s_edit_h+'" lay-event="edit" title="编辑" onclick="edit(\''
//						+ row.patientId + '\')"><i class="fa fa-edit "></i></a> ';
//	    		  var d = '<a class="layui-btn layui-btn-danger layui-btn-xs '+s_remove_h+'" lay-event="del"  title="删除" onclick="remove(\''
//						+ row.patientId + '\')"><i class="fa fa-remove"></i></a> ';
//				  return e + d;
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
				channelCode:$("#channelCode").val(),
				businessName:$("#businessName").val(),
		    	startTime:$("#startTime").val(),
		    	bizId:$("#bizId").val(),
		    	endTime:$("#endTime").val(),
		    	rspCode:$("#rspCode").val()
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
