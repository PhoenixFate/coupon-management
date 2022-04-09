var prefix = ctx + "paycenter/payOrderRefund";

layui.use('table', function(){
	var table = layui.table;
	var tableObj = table.render({
		elem: '#exampleTable',
	    url: prefix + "/list",
	    where:{
	    	orderNumber:$("#orderNumber").val(),
	    	orgCode:$("#orgCode").val()
	    },
	    limit:10,
	    cols: [[
	      {type:'checkbox', fixed: 'left'},
	      		  {
			field : 'orderNumber', 
			title : '订单号',
			width : 270
		  },
				  {
			field : 'paystatus', 
			title : '退款状态',
			width : 100,
			templet : function(row) {
				if ("0" == row.paystatus) {
					return '<span class="layui-badge layui-bg-black">未退款</span>';
				} else if ("2" == row.paystatus) {
					return '<span class="layui-badge layui-bg-orange">退款失败</span>';
				} else if ("1" == row.paystatus) {
					return '<span class="layui-badge layui-bg-green">退款成功</span>';
				} else {
					return '';
				}
			}
		  },
				  {
			field : 'refundtime', 
			title : '退款时间',
			width : 180
		  },
				  {
			field : 'refundFeeYan', 
			title : '退款金额',
			width : 100
		  },
				  {
			field : 'outRefundNo', 
			title : '商户退款单号',
			width : 270
		  },
				  {
			field : 'refundId', 
			title : '第三方退款流水号',
			width : 260
		  },
				  {
			field : 'channelCode', 
			title : '来源渠道',
			width : 100
		  },
				  {
			field : 'remark', 
			title : '退款描述',
			width : 100
		  },
				  {
			field : 'collectorNo', 
			title : '操作员',
			width : 100
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
				ids[i] = row['orderNumber'];
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
				orderNumber:$("#orderNumber").val(),
		    	orgCode:$("#orgCode").val()
			},
			page: {
			    curr: 1 //重新从第 1 页开始
			}
		});
	});
});

function reLoad() {
	$('#exampleTable').bootstrapTable('refresh');
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
				'orderNumber' : id
			},
			success : function(r) {
				if (r.code==0) {
					layer.msg(r.msg);
					reLoad();
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
			ids[i] = row['orderNumber'];
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