var prefix = ctx + "paycenter/wxPayConfig";

layui.use('table', function(){
	var table = layui.table;
	var tableObj = table.render({
		elem: '#exampleTable',
	    url: prefix + "/list",
	    limit:10,
	    cols: [[
				  {
			field : 'appid', 
			title : 'appid',
			width : 170
		  },
				  {
			field : 'mchId', 
			title : '商户号',
			width : 110
		  },
				  {
			field : 'apiKey', 
			title : 'api key',
			width : 200
		  },
				  {
			field : 'subAppid', 
			title : '子商户appid',
			width : 170
		  },
				  {
			field : 'subMchId', 
			title : '子商户号',
			width : 110
		  },
				  {
			field : 'hosPayId', 
			title : '支付渠道码',
			width : 100
		  },
				  {
			field : 'mchName', 
			title : '商户名称',
			width : 150
		  },
				  {
			field : 'p12FilePath', 
			title : 'p12证书路径',
			width : 200
		  },
		  {field:'id', title: '操作',width:130,fixed:'right',
	    	  templet: function(row){
	    		  var e = '<a class="layui-btn  layui-btn-xs " lay-event="edit" title="编辑" onclick="edit(\''
						+ row.id + '\')"><i class="fa fa-edit"></i></a> ';
	    		  var r = '<a class="layui-btn layui-btn-danger layui-btn-xs " lay-event="del" title="删除" onclick="remove(\''
						+ row.id + '\')"><i class="fa fa fa-remove"></i></a> ';
	    		  var p = '<a class="layui-btn layui-btn-normal layui-btn-xs " lay-event="perms" title="渠道权限" onclick="perms(\''
						+ row.id + '\',\'' + row.orgCode + '\')"><i class="fa fa-key"></i></a> ';
				  return e+r+p;
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
			page: {
			    curr: 1 //重新从第 1 页开始
			}
		});
	});
});

function perms(configId, orgCode) {
	var index = layer.open({
		type : 2,
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '500px', '480px' ],
		content : ctx + 'paycenter/payconfigChannelPerms?configId=' + configId + '&configType=wx&orgCode='+orgCode // iframe的url
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
function remove(id) {
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
					reLoad();
				} else {
					layer.msg(r.msg);
				}
			}
		});
	}, function() {

	});
}