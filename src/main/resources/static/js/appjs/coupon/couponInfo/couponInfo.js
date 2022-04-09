var prefix = ctx + "coupon/couponInfo";

var tableObj
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
									couponName : $("#couponName").val(),
									bizCode : $("#bizCode").val()
								},
								limit : 10,
								cols : [ [
										{
											field : 'couponName',
											title : '优惠券名称',
											width : 150,
											fixed : 'left'
										},
										{
											field : 'totalNums',
											title : '优惠券总数',
											width : 100,
											fixed : 'left'
										},
										{
											field : 'couponDetail',
											title : '优惠券详情介绍',
											width : 150
										},
										{
											field : 'remainNums',
											title : '剩余总数',
											width : 90
										},
										{
											field : 'useOrgcodes',
											title : '医院编码',
											width : 150
										},
										{
											field : 'userLimits',
											title : '限领张数',
											width : 90
										},
										{
											field : 'bizCode',
											title : '业务代码',
											width : 90
										},
										{
											field : 'couponStatus',
											title : '优惠券状态',
											width : 100,
											templet : function(row) {
												if ("0" == row.couponStatus) {
													return '<span class="layui-badge layui-bg-orange">未启用</span>';
												} else if ("1" == row.couponStatus) {
													return '<span class="layui-badge layui-bg-green">已启用</span>';
												} else if ("2" == row.couponStatus) {
													return '<span class="layui-badge layui-bg-red">停用</span>';
												}
											}
										},
										{
											field : 'periodStartTime',
											title : '有效期开始时间',
											width : 180
										},
										{
											field : 'periodEndTime',
											title : '有效期截止时间',
											width : 180
										},
										{
											field : 'periodValidType',
											title : '有效期类型',
											width : 150,
											templet : function(row) {
												if ("1" == row.periodValidType) {
													return '<span class="layui-badge layui-bg-orange">按照固定时间段</span>';
												} else if ("2" == row.periodValidType) {
													return '<span class="layui-badge layui-bg-green">按照发券日期</span>';
												}
											}
										},
										{
											field : 'periodValidDays',
											title : '有效期天数',
											width : 100
										},
										{
											field : 'periodStartType',
											title : '生效形式',
											width : 100,
											templet : function(row) {
												if ("1" == row.periodStartType) {
													return '<span class="layui-badge layui-bg-green">立即生效</span>';
												} else if ("2" == row.periodStartType) {
													return '<span class="layui-badge layui-bg-orange">次日生效</span>';
												} else if ("3" == row.periodStartType) {
													return '<span class="layui-badge layui-bg-blue">次周生效</span>';
												} else if ("4" == row.periodStartType) {
													return '<span class="layui-badge layui-bg-red">次月生效</span>';
												}
											}
										},
										{
											field : 'createTime',
											title : '创建时间',
											width : 180
										},
										{
											field : 'id',
											title : '操作',
											width : 170,
											fixed : 'right',
											templet : function(row) {
												var e = '<a class="layui-btn  layui-btn-xs " lay-event="edit" title="编辑" onclick="edit(\''
														+ row.couponId
														+ '\')"><i class="fa fa-edit"></i></a> ';
												var r = '<a class="layui-btn layui-btn-danger layui-btn-xs " lay-event="del" title="停用" onclick="remove1(\''
														+ row.couponId
														+ '\')"><i class="fa fa fa-remove"></i></a> ';
												var items = '<a class="layui-btn layui-btn-warm layui-btn-xs " lay-event="items" title="配置医院" onclick="items(\''
														+ row.couponId
														+ '\',\''
														+ row.couponName
														+ '\')"><i class="fa fa-bars"></i></a> ';
												var p = '<a class="layui-btn layui-btn-warn layui-btn-xs " lay-event="edit" title="规则配置" onclick="rule(\''
													+ row.couponId+ '\',\''
													+ row.couponName + '\')"><i class="fa fa-cog "></i></a> ';
												return e + r + items + p;
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
												ids[i] = row['couponId'];
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
								couponName : $("#couponName").val(),
								bizCode : $("#bizCode").val()
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
			couponName : $("#couponName").val(),
			bizCode : $("#bizCode").val()
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
	layer.confirm('确定要停用选中的记录？', {
		btn : [ '确定', '取消' ]
	}, function() {
		$.ajax({
			url : prefix + "/remove",
			type : "post",
			data : {
				'couponId' : id
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

function items(couponId, couponName) {
	var index = layer.open({
		type : 2,
		title : couponName,
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '480px' ],
		content : prefix + '/spItems/' + couponId,
		end : function() {
			reLoad();
		}
	});
	layer.full(index);
}


function rule(couponId, couponName) {
	var index = layer.open({
		type : 2,
		title : couponName + '->规则配置',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '480px' ],
		content : ctx + 'coupon/couponInfoRules/spItems/' + couponId,
		end : function() {
			reLoad();
		}
	});
	layer.full(index);
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
			ids[i] = row['couponId'];
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