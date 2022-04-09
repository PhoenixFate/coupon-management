var prefix = ctx + "account/accountReturnLog";
var tableObj ;
layui
		.use(
				'table',
				function() {
					var table = layui.table;
					tableObj = table
							.render({
								elem : '#exampleTable',
								url : prefix + "/list",
								where : {
									returnId : $("#returnId").val(),
									accountId : $("#accountId").val()
								},
								limit : 10,
								cols : [ [
									{
										field : 'returnTime',
										title : '退值时间',
										width : 170
									},
										{
											field : 'username',
											title : '账户姓名',
											width : 100
										},
										{
											field : 'returnAmount',
											title : '退值总金额',
											width : 100,
											templet : function(row) {
												return row.returnAmount / 100
														+ '元';
											}

										},
										{
											field : 'cashReturnAmount',
											title : '现金退值金额',
											width : 120,
											templet : function(row) {
												return row.cashReturnAmount / 100
														+ '元';
											}

										},
										{
											field : 'bankReturnAmount',
											title : '银行卡退值金额',
											width : 130,
											templet : function(row) {
												return row.bankReturnAmount / 100
														+ '元';
											}

										},
										{
											field : 'onlinepayReturnAmount',
											title : '线上支付退值金额',
											width : 150,
											templet : function(row) {
												return row.onlinepayReturnAmount / 100
														+ '元';
											}

										},
										{
											field : 'returnStatus',
											title : '退值状态',
											width : 100,
											templet : function(row) {
												if ("1" == row.returnStatus) {
													return '<span class="layui-badge layui-bg-green">成功</span>';
												} else if ("0" == row.returnStatus) {
													return '<span class="layui-badge layui-bg-orange">未退</span>';
												} else if ("2" == row.returnStatus) {
													return '<span class="layui-badge layui-bg-orange">处理中</span>';
												} else if ("3" == row.returnStatus) {
													return '<span class="layui-badge layui-bg-red">取消</span>';
												} else {
													return '<span class="layui-badge layui-bg-red">未知</span>';
												}
											}
										},
										{
											field : 'returnCollector',
											title : '退值操作员号',
											width : 150
										},
										{
											field : 'id',
											title : '操作',
											width : 100,
											fixed : 'right',
											templet : function(row) {
												var e = '<a class="layui-btn  layui-btn-xs " lay-event="edit" title="明细" onclick="detail(\''
														+ row.returnId
														+ '\')"><i class="icon iconfont icon-shouye28"></i></a> ';
												var r = '<a class="layui-btn layui-btn-danger layui-btn-xs " lay-event="del" title="取消" onclick="remove(\''
														+ row.returnId
														+ '\')"><i class="fa fa fa-remove"></i></a> ';
												return e + r;
											}
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
												ids[i] = row['returnId'];
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
								returnId : $("#returnId").val(),
								accountId : $("#accountId").val()
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
			returnId : $("#returnId").val(),
			accountId : $("#accountId").val()
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
function detail(id) {
	var index = layer.open({
		type : 2,
		title : '退值详情',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '520px' ],
		content : ctx + "/account/accountReturnDetail?returnId=" + id // iframe的url
	});
	layer.full(index);
}
function remove(id) {
	layer.confirm('确定要取消本次退值？', {
		btn : [ '确定', '取消' ]
	}, function() {
		$.ajax({
			url : prefix + "/remove",
			type : "post",
			data : {
				'returnId' : id
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
			ids[i] = row['returnId'];
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