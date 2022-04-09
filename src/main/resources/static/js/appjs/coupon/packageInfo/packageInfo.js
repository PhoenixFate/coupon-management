var prefix = ctx + "coupon/packageInfo";

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
									packageName : $("#packageName").val()
								},
								limit : 10,
								cols : [ [
										{
											field : 'packageName',
											title : '服务包名称',
											width : 150
										},
										{
											field : 'packageDetail',
											title : '服务包介绍'
										},
										{
											field : 'packageStatus',
											title : '状态',
											width : 100,
											templet : function(row) {
												if ("0" == row.packageStatus) {
													return '<span class="layui-badge layui-bg-orange">未启用</span>';
												} else if ("1" == row.packageStatus) {
													return '<span class="layui-badge layui-bg-green">已启用</span>';
												} else if ("2" == row.packageStatus) {
													return '<span class="layui-badge layui-bg-red">停用</span>';
												}
											}
										},
										{
											field : 'periodStartTime',
											title : '活动时间',
											width : 320,
											templet : function(row) {
												return row.periodStartTime
														+ ' ~ '
														+ row.periodEndTime;
											}
										},
										{
											field : 'createTime',
											title : '创建时间',
											width : 170
										},

										{
											field : 'id',
											title : '操作',
											width : 150,
											fixed : 'right',
											templet : function(row) {
												var e = '<a class="layui-btn  layui-btn-xs " lay-event="edit" title="编辑" onclick="edit(\''
														+ row.packageId
														+ '\')"><i class="fa fa-edit"></i></a> ';
												var r = '<a class="layui-btn layui-btn-danger layui-btn-xs " lay-event="del" title="删除" onclick="remove1(\''
														+ row.packageId
														+ '\')"><i class="fa fa fa-remove"></i></a> ';
												var items = '<a class="layui-btn layui-btn-warm layui-btn-xs " lay-event="items" title="配置优惠券" onclick="items(\''
														+ row.packageId
														+ '\',\''
														+ row.packageName
														+ '\')"><i class="fa fa-bars"></i></a> ';
												return e + r + items;
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
												ids[i] = row['packageId'];
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
								packageName : $("#packageName").val()
							},
							page : {
								curr : 1
							// 重新从第 1 页开始
							}
						});
					});
				});

function items(packageId, packageName) {
	var index = layer.open({
		type : 2,
		title : packageName,
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '480px' ],
		content : prefix + '/spItems/' + packageId // iframe的url
	});
	layer.full(index);
}

function reLoad() {
	tableObj.reload({
		where : {
			packageName : $("#packageName").val()
		},
		page : {
			curr : 1
		// 重新从第 1 页开始
		}
	});
}
function add() {
	var index = layer.open({
		type : 2,
		title : '增加',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '520px' ],
		content : prefix + '/add' // iframe的url
	});
	layer.full(index);
}
function edit(id) {
	var index = layer.open({
		type : 2,
		title : '编辑',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '520px' ],
		content : prefix + '/edit/' + id // iframe的url
	});
	layer.full(index);
}
function remove1(id) {
	layer.confirm('确定要删除选中的记录？', {
		btn : [ '确定', '取消' ]
	}, function() {
		$.ajax({
			url : prefix + "/remove",
			type : "post",
			data : {
				'packageId' : id
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
			ids[i] = row['packageId'];
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