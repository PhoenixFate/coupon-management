var prefix = ctx + "account/accountHisZd";

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
									searchName1 : $("#searchName1").val(),
									searchName2 : $("#searchName2").val()
								},
								limit : 10,
								cols : [ [
										{
											field : 'tradeTime',
											title : '交易时间',
											width : 150
										},
										{
											field : 'thirdOrdernumber',
											title : '消费订单号',
											width : 250
										},
										{
											field : 'bizCode',
											title : '业务类型',
											width : 100
										},
										{
											field : 'bizName',
											title : '业务名称',
											width : 120
										},
										{
											field : 'patientId',
											title : 'HIS病人ID',
											width : 120
										},
										{
											field : 'patientName',
											title : '病人姓名',
											width : 120
										},
										{
											field : 'idNo',
											title : '身份证号',
											width : 150
										},
										{
											field : 'phoneNo',
											title : '手机号',
											width : 120
										},
										{
											field : 'cardNo',
											title : '诊疗卡号',
											width : 150
										},
										{
											field : 'collectorNo',
											title : '操作员号',
											width : 120
										},
										{
											field : 'orderFee',
											title : '交易额',
											width : 100,
											templet : function(row) {
												return row.orderFee / 100.0
														+ '元';
											}
										},
										{
											field : 'channel',
											title : '渠道1：窗口，2：自助机，3：微信公众号，4支付宝服务窗，5：app， 6：其他',
											width : 150,
											templet : function(row) {
												if ("1" == row.channel) {
													return '<span class="layui-badge layui-bg-orange">窗口</span>';
												} else if ("2" == row.channel) {
													return '<span class="layui-badge layui-bg-orange"自助机</span>';
												} else if ("3" == row.channel) {
													return '<span class="layui-badge layui-bg-orange">微信公众号</span>';
												} else if ("4" == row.channel) {
													return '<span class="layui-badge layui-bg-orange">支付宝服务窗</span>';
												} else if ("5" == row.channel) {
													return '<span class="layui-badge layui-bg-orange">app</span>';
												} else {
													return '<span class="layui-badge layui-bg-red">其它</span>';
												}
											}
										},
										{
											field : 'refundFlag',
											title : '是否退款',
											width : 90,
											templet : function(row) {
												if ("1" == row.refundFlag) {
													return '<span class="layui-badge layui-bg-red">是</span>';
												} else {
													return '<span class="layui-badge layui-bg-green">否</span>';
												}
											}
										},
										{
											field : 'refundFee',
											title : '退费金额',
											width : 100,
											templet : function(row) {
												return row.refundFee / 100.0
														+ '元';
											}
										},
										{
											field : 'id',
											title : '操作',
											width : 60,
											fixed : 'right',
											templet : function(row) {
												var e = '<a class="layui-btn  layui-btn-xs " lay-event="edit" title="编辑" onclick="edit(\''
														+ row.id
														+ '\')"><i class="fa fa-edit"></i></a> ';
												var r = '<a class="layui-btn layui-btn-danger layui-btn-xs " lay-event="del" title="删除" onclick="remove(\''
														+ row.id
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
												ids[i] = row['id'];
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
								searchName1 : $("#searchName1").val(),
								searchName2 : $("#searchName2").val()
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
			searchName1 : $("#searchName1").val(),
			searchName2 : $("#searchName2").val()
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
				'id' : id
			},
			success : function(r) {
				if (r.code == 0) {
					layer.msg(r.msg);
					$("#reload").click();
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
			ids[i] = row['id'];
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