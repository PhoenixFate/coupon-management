var prefix = ctx + "coupon/alarmLogs";

layui.use('table', function(){
	var table = layui.table;
	var tableObj = table.render({
		elem: '#exampleTable',
	    url: prefix + "/list",
	    where:{
	    	alarmName:$("#alarmName").val(),
	    	alarmPhone:$("#alarmPhone").val()
	    },
	    limit:10,
	    cols: [[
				  {
			field : 'alarmCode', 
			title : '警报规则代码',
			width : 150
		  },
				  {
			field : 'alarmName', 
			title : '警报规则名称',
			width : 150
		  },
				  {
			field : 'alarmDesc', 
			title : '报警内容',
			width : 250
		  },
				  {
			field : 'alarmTime', 
			title : '报警时间',
			width : 170
		  },
				  {
			field : 'confirmStatus', 
			title : '处理标志',
			width : 120,
			templet : function(row) {
				if ("1" == row.confirmStatus) {
					return '<span class="layui-badge layui-bg-green">已处理</span>';
				}else if ("0" == row.confirmStatus) {
					return '<span class="layui-badge layui-bg-red">未处理</span>';
				}
			}
		  },
				  {
			field : 'confirmTime', 
			title : '确认时间',
			width : 150
		  },
				  {
			field : 'confirmUser', 
			title : '确认人',
			width : 150
		  },
				  {
			field : 'alarmType', 
			title : '报警类型',
			width : 150
		  },
				  {
			field : 'alarmPhone', 
			title : '报警号码',
			width : 150
		  },
			      {field:'id', title: '操作',width:120,fixed:'right',
	    	  templet: function(row){
	    		  var e = '<a class="layui-btn  layui-btn-xs " lay-event="edit" title="确认" onclick="edit(\''
						+ row.id + '\')"><i class="fa fa-edit"></i></a> ';
	    		  var r = '<a class="layui-btn layui-btn-danger layui-btn-xs " lay-event="del" title="删除" onclick="remove1(\''
						+ row.id + '\')"><i class="fa fa fa-remove"></i></a> ';
				  return e+r;
	      		}
	      }
	    ]],
	    page: {
		    curr: 1 //重新从第 1 页开始
		}
	});
	//批量停用
	$("#batchRemove").on("click",function(){
		var checkStatus = table.checkStatus('exampleTable'),
		rows = checkStatus.data;
		if (rows.length == 0) {
			layer.msg("请选择要删除的数据");
			return;
		}
		layer.confirm("确认要删除选中的'" + rows.length + "'条数据吗?", {
			btn : [ '确定', '取消' ]
		}, function() {
			var ids = new Array();
			// 遍历所有选择的行数据，取每条数据对应的ID
			$.each(rows, function(i, row) {
				ids[i] = row['id'];
			});
			$.post(
				prefix + '/batchRemove',
				{"ids" : ids },
				function(r) {
					if (r.code == 0) {
						layer.msg(r.msg);
						$("#reload").click();
					} else {
						layer.msg(r.msg);
					}
				}
			);
		});
	});
	//查询、刷新
	$("#reload").on("click",function(){
		tableObj.reload({
			where: { 
				alarmName:$("#alarmName").val(),
		    	alarmPhone:$("#alarmPhone").val()
			},
			page: {
			    curr: 1 //重新从第 1 页开始
			}
		});
	});
});

function reLoad() {
	tableObj.reload({
			where: { 
				alarmName:$("#alarmName").val(),
		    	alarmPhone:$("#alarmPhone").val()
			},
			page: {
			    curr: 1 //重新从第 1 页开始
			}
		});
}
function add() {
	layer.open({
		type : 2,
		title : '增加',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '520px' ],
		content : prefix + '/add' // iframe的url
	});
}
function edit(id) {
	layer.open({
		type : 2,
		title : '编辑',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '520px' ],
		content : prefix + '/edit/' + id // iframe的url
	});
}
function remove1(id) {
	layer.confirm('确定要删除选中的记录？', {
		btn : [ '确定', '取消' ]
	}, function() {
		$.ajax({
			url : prefix+"/remove",
			type : "post",
			data : {
				'id' : id
			},
			success : function(r) {
				if (r.code==0) {
					layer.msg(r.msg);
					$("#reload").click();
				}else{
					layer.msg(r.msg);
				}
			}
		});
	})
}

function resetPwd(id) {
}
function batchRemove() {
	var rows = $('#exampleTable').bootstrapTable('getSelections'); // 返回所有选择的行，当没有选择的记录时，返回一个空数组
	if (rows.length == 0) {
		layer.msg("请选择要删除的数据");
		return;
	}
	layer.confirm("确认要删除选中的'" + rows.length + "'条数据吗?", {
		btn : [ '确定', '取消' ]
	// 按钮
	}, function() {
		var ids = new Array();
		// 遍历所有选择的行数据，取每条数据对应的ID
		$.each(rows, function(i, row) {
			ids[i] = row['id'];
		});
		$.ajax({
			type : 'POST',
			data : {
				"ids" : ids
			},
			url : prefix + '/batchRemove',
			success : function(r) {
				if (r.code == 0) {
					layer.msg(r.msg);
					$("#reload").click();
				} else {
					layer.msg(r.msg);
				}
			}
		});
	}, function() {

	});
}