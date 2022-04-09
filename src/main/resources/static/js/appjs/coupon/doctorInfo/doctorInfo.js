var prefix = ctx + "coupon/doctorInfo";

layui.use('table', function(){
	var table = layui.table;
	var tableObj = table.render({
		elem: '#exampleTable',
	    url: prefix + "/list",
	    where:{
	    	docPhone:$("#docPhone").val(),
	    	docName:$("#docName").val(),
	    	hosCode:$("#hosCode").val()
	    },
	    limit:10,
	    cols: [[
		  {
			field : 'docCode', 
			title : '医生工号',
			width : 100, 
			fixed: 'left'
		  },
				  {
			field : 'docName', 
			title : '医生姓名',
			width : 100, 
			fixed: 'left'
		  },
		  {
				field : 'hosName', 
				title : '所属医院',
				width : 150
			  },
				  {
			field : 'docCard', 
			title : '医生身份证',
			width : 150
		  },
				  {
			field : 'docPhone', 
			title : '医生手机号码',
			width : 120
		  },
		  {
				field : 'deptName', 
				title : '科室名称',
				width : 130
			  },
				  {
			field : 'docTitle', 
			title : '医生职称',
			width : 120
		  },
				  {
			field : 'auditStatus', 
			title : '审核状态',
			width : 120,
			templet : function(row) {
				if ("0" == row.auditStatus) {
					return '<span class="layui-badge layui-bg-orange">未审核</span>';
				} else if ("1" == row.auditStatus) {
					return '<span class="layui-badge layui-bg-green">审核通过</span>';
				} else if ("2" == row.auditStatus) {
					return '<span class="layui-badge layui-bg-red">审核未通过</span>';
				} else if ("3" == row.auditStatus) {
					return '<span class="layui-badge layui-bg-red">已删除</span>';
				} else{
					return '<span></span>';
				}
			}
		  },
				  {
			field : 'auditMsg', 
			title : '审核意见',
			width : 150
		  },
				  {
			field : 'createTime', 
			title : '创建时间',
			width : 180
		  },
				  {
			field : 'auditUser', 
			title : '审核人',
			width : 120
		  },
			      {field:'id', title: '操作',width:90,fixed:'right',
	    	  templet: function(row){
	    		  var e = '<a class="layui-btn  layui-btn-xs " lay-event="edit" title="编辑" onclick="edit(\''
						+ row.docApplyId + '\')"><i class="fa fa-edit"></i></a> ';
	    		  var r = '<a class="layui-btn layui-btn-danger layui-btn-xs " lay-event="del" title="删除" onclick="remove1(\''
						+ row.docApplyId + '\')"><i class="fa fa fa-remove"></i></a> ';
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
				ids[i] = row['docApplyId'];
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
				docPhone:$("#docPhone").val(),
				docName:$("#docName").val(),
		    	hosCode:$("#hosCode").val()
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
				docPhone:$("#docPhone").val(),
				docName:$("#docName").val(),
		    	hosCode:$("#hosCode").val()
			},
			page: {
			    curr: 1 //重新从第 1 页开始
			}
		});
}

function add() {
	var index = layer.open({
		type : 2,
		title : '增加',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '520px' ],
		content : prefix + '/add' // iframe的url
	});
	layer.full(index);
	
	/*layer.open({
		type : 2,
		title : '增加',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '520px' ],
		content : prefix + '/add' // iframe的url
	});*/
}
function edit(id) {
	
	var index = layer.open({
		type : 2,
		title : '编辑',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '520px' ],
		content : prefix + '/edit/' + id // iframe的url
	});
	layer.full(index);
	/*layer.open({
		type : 2,
		title : '编辑',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '520px' ],
		content : prefix + '/edit/' + id // iframe的url
	});*/
}
function remove1(id) {
	layer.confirm('确定要删除选中的记录？', {
		btn : [ '确定', '取消' ]
	}, function() {
		$.ajax({
			url : prefix+"/remove",
			type : "post",
			data : {
				'docApplyId' : id
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
			ids[i] = row['docApplyId'];
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