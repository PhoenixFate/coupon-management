var prefix = ctx + "user"
$(function() {
});


layui.use('table', function(){
	var table = layui.table;
	//列表页面
	var tableObj = table.render({
		elem: '#exampleTable',
	    url: prefix + "/list",
	    where:{
	    	searchName:$("#searchName").val()
	    },
	    limit:10,
	    cols: [[
	      {type:'checkbox', fixed: 'left'},
	      {field:'userName', title: '用户名', sort: true},
	      {field:'userRealName', title: '姓名'},
	      {field:'orgName', title: '所属机构'},
	      {field:'mobile', title: '联系号码'},
	      {field:'email', width:200,title: '邮箱'},
	      {field:'status',width:80, title: '状态',templet: function(row){
	    	  if (row.status == 0) {
	    		  return '<span class="label label-danger">停用</span>';
	    	  } else if (row.status == 1) {
	    		  return '<span class="label label-primary">正常</span>';
	    	  }
	      	}
	      },
	      {field:'userId', title: '操作',
	    	  templet: function(row){
	    		  var e = '<a class="layui-btn  layui-btn-xs '+s_edit_h+'" lay-event="edit" title="编辑" onclick="edit(\''
						+ row.userId + '\')"><i class="fa fa-edit "></i></a> ';
	    		  var r = '<a class="layui-btn layui-btn-danger layui-btn-xs '+s_resetPwd_h+'" lay-event="resePwd" title="重置密码" onclick="resetPwd(\''
	    		  		+ row.userId + '\')"><i class="fa fa-key "></i></a> ';
	    		  var d = '<a  class="layui-btn layui-btn-danger layui-btn-xs '+s_remove_h+'" lay-event="del"  title="删除" onclick="remove1(\''
						+ row.userId + '\')"><i class="fa fa-remove"></i></a> ';
				  return e + r + d;
	      		}
	      }
	    ]],
	    page: {
		    curr: 1 //重新从第 1 页开始
		}
	});
	//批量删除
	$("#batchRemove").on("click",function(){
		var checkStatus = table.checkStatus('exampleTable'),
		rows = checkStatus.data;
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
				ids[i] = row['userId'];
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
				searchName:$("#searchName").val()
			},
			page: {
			    curr: 1 //重新从第 1 页开始
			}
		});
	})
});
function add() {
	// iframe层
	layer.open({
		type : 2,
		title : '添加用户',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '480px' ],
		content : prefix + '/add'
	});
}
function remove1(id) {
	layer.confirm('确定要删除选中的记录？', {
		btn : [ '确定', '取消' ]
	}, function() {
		$.post(prefix + "/remove",{'userId':id},function(r) {
				if (r.code == 0) {
					layer.msg(r.msg);
					$("#reload").click();
				} else {
					layer.msg(r.msg);
				}
			}
		);
	});
}
function edit(id) {
	layer.open({
		type : 2,
		title : '用户修改',
		maxmin : true,
		shadeClose : false,
		area : [ '800px', '480px' ],
		content : prefix + '/edit/' + id // iframe的url
	});
}
function resetPwd(id) {
	layer.open({
		type : 2,
		title : '重置密码',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '400px', '260px' ],
		content : prefix + '/resetPwd/' + id // iframe的url
	});
}
