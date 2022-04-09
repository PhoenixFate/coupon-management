var prefix = ctx + "coupon/productInfo";

layui.use('table', function(){
	var table = layui.table;
	var tableObj = table.render({
		elem: '#exampleTable',
	    url: prefix + "/list",
	    where:{
	    	productName:$("#productName").val(),
	    	productCode:$("#productCode").val()
	    },
	    limit:10,
	    cols: [[
	      {
			field : 'productName', 
			title : '产品名称',
			width : 200
		  },
		  {
				field : 'productCode', 
				title : '产品编码',
				width : 200
			  },
				  {
			field : 'productDetail', 
			title : '产品详情介绍'
		  },
				  {
			field : 'productStatus', 
			title : '产品状态',
			width : 100,
			templet : function(row) {
				if ("0" == row.productStatus) {
					return '<span class="layui-badge layui-bg-orange">未启用</span>';
				} else if ("1" == row.productStatus) {
					return '<span class="layui-badge layui-bg-green">已启用</span>';
				} else if ("2" == row.productStatus) {
					return '<span class="layui-badge layui-bg-red">停用</span>';
				}
			}
		  },
				  {
			field : 'createTime', 
			title : '创建时间',
			width : 160
		  },
			      {field:'id', title: '操作',width:150,fixed:'right',
	    	  templet: function(row){
	    		  var e = '<a class="layui-btn  layui-btn-xs " lay-event="edit" title="编辑" onclick="edit(\''
						+ row.productId + '\')"><i class="fa fa-edit"></i></a> ';
	    		  var r = '<a class="layui-btn layui-btn-danger layui-btn-xs " lay-event="del" title="删除" onclick="remove1(\''
						+ row.productId + '\')"><i class="fa fa fa-remove"></i></a> ';
	    		  var items = '<a class="layui-btn layui-btn-warm layui-btn-xs " lay-event="items" title="配置服务包" onclick="items(\''
						+ row.productCode
						+ '\',\''
						+ row.productName
						+ '\')"><i class="fa fa-bars"></i></a> ';
				  return e+r+items;
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
				ids[i] = row['productId'];
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
				productName:$("#productName").val(),
		    	productCode:$("#productCode").val()
			},
			page: {
			    curr: 1 //重新从第 1 页开始
			}
		});
	});
});

function items(productCode, productName) {
	var index = layer.open({
		type : 2,
		title : productName,
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '480px' ],
		content : prefix + '/proItems/' + productCode // iframe的url
	});
	layer.full(index);
}



function reLoad() {
	tableObj.reload({
			where: { 
				productName:$("#productName").val(),
		    	productCode:$("#productCode").val()
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
				'productId' : id
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
			ids[i] = row['productId'];
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