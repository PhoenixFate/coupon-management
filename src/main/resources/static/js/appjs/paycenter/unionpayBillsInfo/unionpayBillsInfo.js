var prefix = ctx + "paycenter/unionpayBillsInfo";

layui.use('table', function(){
	var table = layui.table;
	var tableObj = table.render({
		elem: '#exampleTable',
	    url: prefix + "/list",
	    where:{
	    	searchName1:$("#searchName1").val(),
	    	searchName2:$("#searchName2").val()
	    },
	    limit:10,
	    cols: [[
	      {type:'checkbox', fixed: 'left'},
	      		  {
			field : 'zdId', 
			title : '主键',
			width : '100px'
		  },
				  {
			field : 'unionpayId', 
			title : '银联交易流水（流水号 + | + 参考号）银商订单号',
			width : '100px'
		  },
				  {
			field : 'originalUnionpayId', 
			title : '',
			width : '100px'
		  },
				  {
			field : 'hosCode', 
			title : '医院编码',
			width : '100px'
		  },
				  {
			field : 'merchantOrderNo', 
			title : '商户订单号',
			width : '100px'
		  },
				  {
			field : 'merchantCode', 
			title : '商户号',
			width : '100px'
		  },
				  {
			field : 'merchantName', 
			title : '商户名',
			width : '100px'
		  },
				  {
			field : 'terminalCode', 
			title : '终端号',
			width : '100px'
		  },
				  {
			field : 'transDate', 
			title : '交易日期YYYY-MM-DD',
			width : '100px'
		  },
				  {
			field : 'transHour', 
			title : '交易时间',
			width : '100px'
		  },
				  {
			field : 'transType', 
			title : '交易类型',
			width : '100px'
		  },
				  {
			field : 'cardNumber', 
			title : '银行卡号',
			width : '100px'
		  },
				  {
			field : 'transAmount', 
			title : '交易金额',
			width : '100px'
		  },
				  {
			field : 'liquidationAmount', 
			title : '清算金额',
			width : '100px'
		  },
				  {
			field : 'handlingFee', 
			title : '手续费',
			width : '100px'
		  },
				  {
			field : 'referenceNo', 
			title : '参考号',
			width : '100px'
		  },
				  {
			field : 'flowNumber', 
			title : '流水号',
			width : '100px'
		  },
				  {
			field : 'businessName', 
			title : '商户名称',
			width : '100px'
		  },
				  {
			field : 'cardType', 
			title : '卡类型',
			width : '100px'
		  },
				  {
			field : 'issuer', 
			title : '发卡行',
			width : '100px'
		  },
				  {
			field : 'actualPaymentAmount', 
			title : '实际支付金额',
			width : '100px'
		  },
				  {
			field : 'accountNumber', 
			title : '账单号',
			width : '100px'
		  },
				  {
			field : 'paymethod', 
			title : '支付方式',
			width : '100px'
		  },
				  {
			field : 'silverMerchantOrderNo', 
			title : '银商订单号',
			width : '100px'
		  },
				  {
			field : 'remark', 
			title : '备注',
			width : '100px'
		  },
				  {
			field : 'walletDiscountAmount', 
			title : '钱包优惠金额',
			width : '100px'
		  },
				  {
			field : 'merchantDiscountAmount', 
			title : '商户优惠金额',
			width : '100px'
		  },
				  {
			field : 'originalTransactionSerialNo', 
			title : '原交易流水号',
			width : '100px'
		  },
				  {
			field : 'returnOrderNumber', 
			title : '退货订单号',
			width : '100px'
		  },
				  {
			field : 'paymentPostscript', 
			title : '付款附言',
			width : '100px'
		  },
				  {
			field : 'branchShortName', 
			title : '分店简称',
			width : '100px'
		  },
				  {
			field : 'paymentRoute', 
			title : '支付途径(账单类型  1：pos，9:扫码，68：手机) 默认1',
			width : '100px'
		  },
				  {
			field : 'refundFlag', 
			title : '是否退款 1是',
			width : '100px'
		  },
				  {
			field : 'creatTime', 
			title : '创建时间',
			width : '100px'
		  },
			      {field:'id', title: '操作',width:60,fixed:'right',
	    	  templet: function(row){
	    		  var e = '<a class="layui-btn  layui-btn-xs '+s_edit_h+'" lay-event="edit" title="编辑" onclick="edit(\''
						+ row.zdId + '\')"><i class="fa fa-edit"></i></a> ';
	    		  var r = '<a class="layui-btn layui-btn-danger layui-btn-xs '+s_remove_h+'" lay-event="del" title="删除" onclick="remove(\''
						+ row.zdId + '\')"><i class="fa fa fa-remove"></i></a> ';
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
				ids[i] = row['zdId'];
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
				searchName1:$("#searchName1").val(),
				searchName2:$("#searchName2").val()
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
				'zdId' : id
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
			ids[i] = row['zdId'];
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