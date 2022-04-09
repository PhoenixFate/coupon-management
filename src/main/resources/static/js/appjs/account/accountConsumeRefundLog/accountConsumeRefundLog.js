var prefix = ctx + "account/accountConsumeRefundLog";

layui
		.use(
				'table',
				function() {
					var table = layui.table;
					var tableObj = table
							.render({
								elem : '#exampleTable',
								url : prefix + "/list",
								where : {
									consumeId : $("#consumeId").val(),
									orgCode : $("#orgCode").val(),
									accountNo : $("#accountNo").val(),
									thirdOrdernumber : $("#thirdOrdernumber").val(),
									refundOrdernumber : $("#refundOrdernumber").val(),
									refundCollector : $("#refundCollector").val(),
							    	channelCode:$("#channelCode").val()
								},
								limit : 10,
								cols : [ [
										{
											field : 'accountNo',
											title : '账户号',
											width : 180
										},
										{
											field : 'refundAmount',
											title : '退费金额',
											width : 100,
											templet : function(row) {
												return row.refundAmount / 100.0
														+ '元';
											}
										},
										{
											field : 'refundStatus',
											title : '退费状态',
											width : 100,
											templet : function(row) {
												if ("0" == row.refundStatus) {
													return '<span class="layui-badge layui-bg-red">失败</span>';
												} else if ("1" == row.refundStatus) {
													return '<span class="layui-badge layui-bg-green">成功</span>';
												} else {
													return '';
												}
											}
										}, {
											field : 'refundTime',
											title : '退费时间',
											width : 180
										}, {
											field : 'thirdOrdernumber',
											title : '消费订单号',
											width : 270
										}, {
											field : 'refundOrdernumber',
											title : '退款流水号',
											width : 270
										}, {
											field : 'remark',
											title : '描述',
											width : 150
										}, {
											field : 'terminalNo',
											title : '退费终端号',
											width : 120
										}, {
											field : 'refundCollector',
											title : '退费操作员',
											width : 120
										}, {
											field : 'accountBalance',
											title : '账户余额',
											width : 120,
											templet : function(row) {
												return row.accountBalance / 100.0
														+ '元';
											}
										}, {
											field : 'channelCode',
											title : '退费渠道码',
											width : 120
										} ] ],
								page : {
									curr : 1
								// 重新从第 1 页开始
								}
							});
					// 批量停用
					$("#batchRemove")
							.on(
									"click",
									function() {
										var checkStatus = table
												.checkStatus('exampleTable'), rows = checkStatus.data;
										if (rows.length == 0) {
											layer.msg("请选择要删除的数据");
											return;
										}
										layer.confirm("确认要删除选中的'" + rows.length
												+ "'条数据吗?", {
											btn : [ '确定', '取消' ]
										}, function() {
											var ids = new Array();
											// 遍历所有选择的行数据，取每条数据对应的ID
											$.each(rows, function(i, row) {
												ids[i] = row['consumeId'];
											});
											$.post(prefix + '/batchRemove', {
												"ids" : ids
											}, function(r) {
												if (r.code == 0) {
													layer.msg(r.msg);
													$("#reload").click();
												} else {
													layer.msg(r.msg);
												}
											});
										});
									});
					// 查询、刷新
					$("#reload").on("click", function() {
						tableObj.reload({
							where : {
								consumeId : $("#consumeId").val(),
								orgCode : $("#orgCode").val(),
								accountNo : $("#accountNo").val(),
								thirdOrdernumber : $("#thirdOrdernumber").val(),
								refundOrdernumber : $("#refundOrdernumber").val(),
								refundCollector : $("#refundCollector").val(),
						    	channelCode:$("#channelCode").val()
							},
							page : {
								curr : 1
							// 重新从第 1 页开始
							}
						});
					});
				});

function reLoad() {
	tableObj.reload({
		where : {
			consumeId : $("#consumeId").val(),
			orgCode : $("#orgCode").val(),
			accountNo : $("#accountNo").val(),
			thirdOrdernumber : $("#thirdOrdernumber").val(),
			refundOrdernumber : $("#refundOrdernumber").val(),
			refundCollector : $("#refundCollector").val(),
	    	channelCode:$("#channelCode").val()
		},
		page : {
			curr : 1
		// 重新从第 1 页开始
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
			url : prefix + "/remove",
			type : "post",
			data : {
				'consumeId' : id
			},
			success : function(r) {
				if (r.code == 0) {
					layer.msg(r.msg);
					reLoad();
				} else {
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
			ids[i] = row['consumeId'];
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