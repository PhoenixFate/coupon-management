var prefix = ctx + "account/accountRechargeRefundLog";

layui.use('table', function(){
	var table = layui.table;
	var tableObj = table.render({
		elem: '#exampleTable',
	    url: prefix + "/list",
	    where:{
	    	consumeId:$("#consumeId").val(),
	    	orgCode:$("#orgCode").val()
	    },
	    limit:10,
	    cols: [[
	     {
			field : 'rechargeId', 
			title : '充值流水号',
			width : 150
		  },
				  {
			field : 'accountId', 
			title : '账户id',
			width : 150
		  },
				  {
			field : 'accountNo', 
			title : '账户号',
			width : 150
		  },
				  {
			field : 'channelCode', 
			title : '退值渠道码',
			width : 150
		  },
				  {
			field : 'refundStatus', 
			title : '退款状态， 1 成功， 0 失败， 用于判断是否退款成功',
			width : 150
		  },
				  {
			field : 'refundOrdernumber', 
			title : '退款订单号',
			width : 150
		  },
				  {
			field : 'thirdPayFlow', 
			title : '第三方支付流水号， 支付宝微信银联等生成的支付流水号',
			width : 150
		  },
				  {
			field : 'bankPzh', 
			title : '银行交易凭证号',
			width : 150
		  },
				  {
			field : 'bankFlow', 
			title : '银联交易流水号',
			width : 150
		  },
				  {
			field : 'bankMerchantNo', 
			title : '银行商户号',
			width : 150
		  },
				  {
			field : 'remark', 
			title : '退值描述',
			width : 150
		  },
				  {
			field : 'terminalNo', 
			title : '退款终端号（操作员号）',
			width : 150
		  },
				  {
			field : 'accountBalance', 
			title : '退值完成之后账户余额',
			width : 150
		  },
				  {
			field : 'refundAmount', 
			title : '退值金额',
			width : 150
		  },
				  {
			field : 'refundTime', 
			title : '退值时间',
			width : 150
		  },
				  {
			field : 'refundCollector', 
			title : '退值操作员编号',
			width : 150
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
				ids[i] = row['refundLogId'];
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
				consumeId:$("#consumeId").val(),
		    	orgCode:$("#orgCode").val()
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
				consumeId:$("#consumeId").val(),
		    	orgCode:$("#orgCode").val()
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
				'refundLogId' : id
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
			ids[i] = row['refundLogId'];
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