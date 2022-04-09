var prefix = ctx + "coupon/accountPackageRelation";
var tableObj;
layui.use('table', function(){
	var table = layui.table;
	tableObj = table.render({
		elem: '#exampleTable',
	    url: prefix + "/list",
	    where:{
	    	/*startTime:$("#startTime").val(),
	    	endTime:$("#endTime").val(),*/
	    	accountNo:$("#accountNo").val(),
	    	username:$("#username").val()
	    },
	    limit:10,
	    cols: [[
	      	/*	  {
			field : 'relationId', 
			title : '主键',
			width : 150
		  },*/
	    	 {
	 			field : 'createTime', 
	 			title : '发放时间',
	 			fixed:'left',
	 			width : 180
	 		  },
				  /*{
			field : 'packageId', 
			title : '服务包ID',
			width : 150
		  },*/
			/*	  {
			field : 'accountId', 
			title : '优惠券ID',
			width : 150
		  },*/
				  {
			field : 'accountNo', 
			title : '用户账号',
			width : 180
		  },
				  {
			field : 'packageName', 
			title : '服务包名称',
			width : 150
		  },
				 
				  {
			field : 'username', 
			title : '用户姓名',
			width : 120
		  },
				  {
			field : 'productCode', 
			title : '该服务包对应的产品',
			width : 180
		  },
				  {
			field : 'productName', 
			title : '该服务包对应的产品名称',
			width : 190
		  },
				  {
			field : 'bizFlow', 
			title : '发放该服务包的业务流水',
			width : 190
		  },
				  {
			field : 'startTime', 
			title : '服务包生效时间',
			width : 180
		  },
				  {
			field : 'endTime', 
			title : '服务包失效时间',
			width : 180
		  },
			      {field:'relationId', title: '操作',width:60,fixed:'right',
	    	  templet: function(row){
	    		  var e = '<a class="layui-btn  layui-btn-xs " lay-event="items" title="编辑" onclick="items(\''
						+ row.relationId + '\', \'' + row.packageName+ '\' , \'' +row.accountNo+ '\')"><i class="fa fa-bars"></i></a> ';
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
				ids[i] = row['relationId'];
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
			/*	startTime:$("#startTime").val(),
		    	endTime:$("#endTime").val(),*/
		    	accountNo:$("#accountNo").val(),
		    	username:$("#username").val()
			},
			page: {
			    curr: 1 //重新从第 1 页开始
			}
		});
	});
});

function items(relationId, packageName, accountNo) {
	var index = layer.open({
		type : 2,
		title : packageName,
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '480px' ],
		content : ctx + 'coupon/accountCouponInfo/spItems/' + relationId + '/' + accountNo
	});
	layer.full(index);
}


function reLoad() {
	tableObj.reload({
			where: { 
		    	accountNo:$("#accountNo").val(),
		    	username:$("#username").val()
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
function remove(id) {
	layer.confirm('确定要删除选中的记录？', {
		btn : [ '确定', '取消' ]
	}, function() {
		$.ajax({
			url : prefix+"/remove",
			type : "post",
			data : {
				'relationId' : id
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
			ids[i] = row['relationId'];
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