var prefix = ctx + "account/accountRechargeLog";

layui.use('table', function(){
	var table = layui.table;
	var tableObj = table.render({
		elem: '#exampleTable',
	    url: prefix + "/list",
	    where:{
	    	accountNo:$("#accountNo").val(),
	    	username:$("#username").val(),
	    	thirdOrdernumber:$("#thirdOrdernumber").val(),
	    	thirdPayFlow:$("#thirdPayFlow").val(),
	    	startTime:$("#startTime").val(),
	    	endTime:$("#endTime").val(),
	    	rechargeCollector:$("#rechargeCollector").val(),
	    	channelCode:$("#channelCode").val()
	    },
	    limit:10,
	    cols: [[
	      {
			field : 'accountNo', 
			title : '账户号',
			width : 180,
			fixed : 'left'
		  },{
				field : 'username', 
				title : '账户姓名',
				width : 120,
				fixed : 'left'
			  },
				  {
			field : 'rechargeAmount', 
			title : '充值金额',
			width : 100,
			templet : function(row) {
				return row.rechargeAmount/100.0 + '元';
			}
		  },
				  {
			field : 'rechargeTime', 
			title : '充值时间',
			width : 180
		  },
				  {
			field : 'payChannel', 
			title : '支付渠道',
			width : 120,
			templet : function(row) {
				if ("02" == row.payChannel) {
					return '<span class="layui-badge layui-bg-blue">支付宝</span>';
				} else if ("03" == row.payChannel) {
					return '<span class="layui-badge layui-bg-green">微信</span>';
				} else if ("04" == row.payChannel) {
					return '<span class="layui-badge layui-bg-black">银行卡</span>';
				} else if ("11" == row.payChannel) {
					return '<span class="layui-badge layui-bg-red">银联</span>';
				} else if ("13" == row.payChannel) {
					return '<span class="layui-badge layui-bg-red">工行聚合支付</span>';
				} else if ("12" == row.payChannel) {
					return '<span class="layui-badge layui-bg-red">银联闪付APP</span>';
				} else if ("01" == row.payChannel) {
					return '<span class="layui-badge layui-bg-gray">现金</span>';
				}  
				else {
					return '';
				}
			}
		  },
				  {
			field : 'rechargeStatus', 
			title : '充值状态',
			width : 100,
			templet : function(row) {
				if ("0" == row.rechargeStatus) {
					return '<span class="layui-badge layui-bg-red">失败</span>';
				} else if ("1" == row.rechargeStatus) {
					return '<span class="layui-badge layui-bg-green">成功</span>';
				} else if ("2" == row.rechargeStatus) {
					return '<span class="layui-badge layui-bg-gray">处理中</span>';
				} 
				else {
					return '';
				}
			}
		  },
				  {
			field : 'thirdOrdernumber', 
			title : '商户订单号',
			width : 270
		  },
				  {
			field : 'thirdPayFlow', 
			title : '第三方支付流水号',
			width : 270
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
			field : 'bankCardNo', 
			title : '银行卡号',
			width : 160
		  },
				  {
			field : 'remark', 
			title : '充值描述',
			width : 150
		  },
				  {
			field : 'terminalNo', 
			title : '充值终端号',
			width : 120
		  },
				  {
			field : 'accountBalance', 
			title : '账户余额',
			width : 100,
			templet : function(row) {
				return row.accountBalance/100.0 + '元';
			}
		  },
				  {
			field : 'returnFlag', 
			title : '退值标记',
			width : 100,
			templet : function(row) {
				if ("0" == row.returnFlag) {
					return '<span class="layui-badge layui-bg-blue">未退</span>';
				} else if ("1" == row.returnFlag) {
					return '<span class="layui-badge layui-bg-red">已退</span>';
				} 
				else {
					return '';
				}
			}
		  },
		  {
				field : 'channelCode', 
				title : '充值渠道码',
				width : 120
			  },
		  {
				field : 'rechargeCollector', 
				title : '充值操作员号',
				width : 120
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
				ids[i] = row['rechargeId'];
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
				accountNo:$("#accountNo").val(),
		    	username:$("#username").val(),
		    	thirdOrdernumber:$("#thirdOrdernumber").val(),
		    	thirdPayFlow:$("#thirdPayFlow").val(),
		    	startTime:$("#startTime").val(),
		    	endTime:$("#endTime").val(),
		    	rechargeCollector:$("#rechargeCollector").val(),
		    	channelCode:$("#channelCode").val()
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
				accountNo:$("#accountNo").val(),
		    	username:$("#username").val(),
		    	thirdOrdernumber:$("#thirdOrdernumber").val(),
		    	thirdPayFlow:$("#thirdPayFlow").val(),
		    	startTime:$("#startTime").val(),
		    	endTime:$("#endTime").val(),
		    	rechargeCollector:$("#rechargeCollector").val(),
		    	channelCode:$("#channelCode").val()
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
				'rechargeId' : id
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
			ids[i] = row['rechargeId'];
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