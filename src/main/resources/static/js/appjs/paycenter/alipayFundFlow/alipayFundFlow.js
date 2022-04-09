var prefix = ctx + "paycenter/alipayFundFlow";

layui.use('table', function(){
	var table = layui.table;
	var tableObj = table.render({
		elem: '#exampleTable',
	    url: prefix + "/list",
	    where:{
	    	startTime:$("#startTime").val(),
	    	endTime:$("#endTime").val(),
	    	merchantOrderNo:$("#merchantOrderNo").val(),
	    	tradeType:$("#tradeType").val()
	    },
	    limit:10,
	    cols: [[
			{
			field : 'tradeTime', 
			title : '发生时间',
			width : 170,
			fixed : 'left'
		  },
		  {
				field : 'tradeType', 
				title : '收支类型',
				width : 120
		  },
		  {
				field : 'incomeFee', 
				title : '收入金额(元)',
				width : 120
		  },
		  {
				field : 'outcomeFee', 
				title : '支出金额(元)',
				width : 120
		  },
		  {
			field : 'accountRemain', 
			title : '账户结余(元)',
			width : 140
		  },
		  {
				field : 'remarks', 
				title : '备注',
				width : 200
		  },
		 {
			field : 'alipayBillsNo', 
			title : '支付宝流水号',
			width : 270
		  },
				  {
			field : 'merchantOrderNo', 
			title : '商户订单号',
			width : 270
		  },
				  {
			field : 'bizName', 
			title : '商品名称',
			width : 150
		  },
				  {
			field : 'buyerName', 
			title : '买家账号',
			width : 180
		  },
		 {
			field : 'tradeChannel', 
			title : '交易渠道',
			width : 100
		  },
		  {
				field : 'mchId', 
				title : '商户号',
				width : 180
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
				ids[i] = row['fundId'];
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
				startTime:$("#startTime").val(),
		    	endTime:$("#endTime").val(),
		    	merchantOrderNo:$("#merchantOrderNo").val(),
		    	tradeType:$("#tradeType").val()
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
				startTime:$("#startTime").val(),
		    	endTime:$("#endTime").val(),
		    	merchantOrderNo:$("#merchantOrderNo").val(),
		    	tradeType:$("#tradeType").val()
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
				'fundId' : id
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



layui.use('laydate', function(){
	var laydate = layui.laydate;
	laydate.render({ 
		  elem: "#startTime"
		});
	laydate.render({ 
		  elem: "#endTime"
		});
});