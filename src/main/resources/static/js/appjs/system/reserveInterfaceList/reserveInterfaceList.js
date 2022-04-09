var prefix = ctx + "reserveInterfaceList"

layui.use('table', function(){
	var table = layui.table;
	var tableObj = table.render({
		elem: '#exampleTable',
	    url: prefix + "/list",
	    where:{
	    	interfacePerms:$("#interfacePerms").val(),
	    	searchName:$("#searchName").val()
	    },
	    limit:10,
	    cols: [[
	      {type:'checkbox', fixed: 'left'},
	      {field:'interfaceName', title: '接口名称'},
	      {field:'interfaceUrl', title: '接口URL',width:400},
	      {field:'interfacePerms', title: '权限码'},
	      {field:'interfaceId', title: '操作',width:60,fixed:'right',
	    	  templet: function(row){
	    		  var e = '<a class="layui-btn  layui-btn-xs '+s_edit_h+'" lay-event="edit" title="编辑" onclick="edit(\''
						+ row.interfaceId + '\')"><i class="fa fa-edit"></i></a> ';
//	    		  var r = '<a class="layui-btn layui-btn-danger layui-btn-xs '+s_remove_h+'" lay-event="del" title="删除" onclick="remove(\''
//						+ row.interfaceId + '\')"><i class="fa fa fa-remove"></i></a> ';
				  return e;
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
				ids[i] = row['interfaceId'];
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
				interfacePerms:$("#interfacePerms").val(),
				searchName:$("#searchName").val()
			},
			page: {
			    curr: 1 //重新从第 1 页开始
			}
		});
	});
});

function add() {
	var index = layer.open({
		type : 2,
		title : '添加',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '480px' ],
		content : prefix + '/add' // iframe的url
	});
//	layer.full(index);
}
function edit(id) {
	var index = layer.open({
		type : 2,
		title : '编辑',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '480px' ],
		content : prefix + '/edit/' + id // iframe的url
	});
//	layer.full(index);
}
function remove(id) {
	layer.confirm('确定要删除选中的记录？', {
		btn : [ '确定', '取消' ]
	}, function() {
		$.post(prefix+"/remove",{'ineterfaceId' : id},function(r) {
				if (r.code==0) {
					layer.msg(r.msg);
					$("#reload").click();
				}else{
					layer.msg(r.msg);
				}
			}
		);
	})
}
