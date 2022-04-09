var prefix = ctx + "paycenter/wxBillsInfo";

layui.use('table', function(){
	var table = layui.table;
	var tableObj = table.render({
		elem: '#exampleTable',
		height: 'full-250',
	    url: prefix + "/list",
	    where:{
	    	merchantOutOrderNo:$("#merchantOutOrderNo").val(),
	    	tradeStatus:$("#tradeStatus").val(),
	    	startTime:$("#startTime").val(),
	    	endTime:$("#endTime").val(),
	    	deviceId:$("#deviceId").val()
	    },
	    limit:20,
	    cols: [[
	        
				  {
			field : 'tradeTime', 
			title : '交易时间',
			width : 170,
			fixed : 'left'
		  },
		  {
				field : 'hspsOrderNum', 
				title : '商户订单号',
				width : 270,
				fixed : 'left'
		 },
		 {
				field : 'tradeOrderNo', 
				title : '微信订单号',
				width : 250
		},
		  {
			field : 'appId', 
			title : 'APPID',
			width : 170
		  },
				  {
			field : 'machId', 
			title : '商户号',
			width : 120
		  },
				  {
			field : 'machChildId', 
			title : '子商户号',
			width : 120
		  },
				  {
			field : 'deviceId', 
			title : '收费员',
			width : 120
		  },
				  {
			field : 'tradeType', 
			title : '交易类型',
			width : 120
		  },
				  {
			field : 'tradeStatus', 
			title : '交易状态',
			width : 100
		  },
				  {
			field : 'depositBankNo', 
			title : '付款银行',
			width : 120
		  },
				  {
			field : 'orderFee', 
			title : '订单金额',
			width : 130
		  },
				  {
			field : 'wxRefundOrderNo', 
			title : '微信退款单号',
			width : 260
		  },
				  {
			field : 'hspsRefundOrderNo', 
			title : '商户退款单号',
			width : 270
		  },
				  {
			field : 'refundFee', 
			title : '退款金额',
			width : 130
		  },
				  {
			field : 'refundType', 
			title : '退款类型',
			width : 100
		  },
				  {
			field : 'refundStatus', 
			title : '退款状态',
			width : 100
		  },
				  {
			field : 'commodity', 
			title : '商品名称',
			width : 100
		  },
				  {
			field : 'serviceFee', 
			title : '服务费',
			width : 100
		  },
				  {
			field : 'rate', 
			title : '费率',
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
				ids[i] = row['wxId'];
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
				merchantOutOrderNo:$("#merchantOutOrderNo").val(),
		    	tradeStatus:$("#tradeStatus").val(),
		    	startTime:$("#startTime").val(),
		    	endTime:$("#endTime").val(),
		    	deviceId:$("#deviceId").val()
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
				'wxId' : id
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
			ids[i] = row['wxId'];
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

layui.use('laydate', function(){
	var laydate = layui.laydate;
	laydate.render({ 
		  elem: "#startTime"
		});
	laydate.render({ 
		  elem: "#endTime"
		});
});