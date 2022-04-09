var prefix = ctx + "paycenter/alipayBillsInfo";

layui
		.use(
				'table',
				function() {
					var table = layui.table;
					var tableObj = table.render({
						elem : '#exampleTable',
						height: 'full-250',
						url : prefix + "/list",
						where : {
							merchantOutOrderNo:$("#merchantOutOrderNo").val(),
							transCodeMsg:$("#transCodeMsg").val(),
					    	startTime:$("#startTime").val(),
					    	endTime:$("#endTime").val(),
					    	sellerAccount:$("#sellerAccount").val()
						},
						limit : 20,
						cols : [ [  {
							field : 'transTime',
							title : '交易时间',
							width : 170,
							fixed : 'left'
						}, {
							field : 'merchantOutOrderNo',
							title : '商户订单号',
							width : 270,
							fixed : 'left'
						}, {
							field : 'tradeNo',
							title : '支付宝交易号',
							width : 250
						},{
							field : 'buyerAccount',
							title : '对方账户',
							width : 180
						}, {
							field : 'goodsTitle',
							title : '商品名称',
							width : 100
						}, {
							field : 'transCodeMsg',
							title : '交易类型',
							width : 100
						}, {
							field : 'income',
							title : '收入金额',
							width : 130
						}, {
							field : 'memo',
							title : '备注',
							width : 100
						}, {
							field : 'sellerAccount',
							title : '收费员',
							width : 120
						}, {
							field : 'partnerId',
							title : '商户ID',
							width : 170
						}, {
							field : 'serviceFee',
							title : '手续费',
							width : 100
						}, {
							field : 'addRefundFee',
							title : '退款金额',
							width : 100
						}, {
							field : 'addRefundNum',
							title : '退款单号',
							width : 270
						}, {
							field : 'addRefundDate',
							title : '退款日期',
							width : 170
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
												ids[i] = row['alipayId'];
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
								merchantOutOrderNo:$("#merchantOutOrderNo").val(),
								transCodeMsg:$("#transCodeMsg").val(),
						    	startTime:$("#startTime").val(),
						    	endTime:$("#endTime").val(),
						    	sellerAccount:$("#sellerAccount").val()
							},
							page : {
								curr : 1
							// 重新从第 1 页开始
							}
						});
					});
				});

function reLoad() {
	$('#exampleTable').bootstrapTable('refresh');
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