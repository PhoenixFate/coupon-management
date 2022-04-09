var prefix = ctx + "account/accountReturnDetail";
var tableObj;
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
									returnId : $("#returnId").val()
								},
								limit : 10,
								cols : [ [
										{
											field : 'refundAmount',
											title : '退值金额',
											width : 100,
											templet : function(row) {
												return row.refundAmount / 100.0
														+ '元';
											}
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
													return row.payChannel;
												}
											}
										  },
										{
											field : 'refundTime',
											title : '退值时间',
											width : 180
										},
										{
											field : 'refundStatus',
											title : '退款状态',
											width : 100,
											templet : function(row) {
												if ("1" == row.refundStatus) {
													return '<span class="layui-badge layui-bg-green">成功</span>';
												} else if ("0" == row.refundStatus) {
													return '<span class="layui-badge layui-bg-red">失败</span>';
												} else if ("2" == row.refundStatus) {
													return '<span class="layui-badge layui-bg-orange">处理中</span>';
												} else {
													return '<span class="layui-badge layui-bg-red">未知</span>';
												}
											}
										},
										{
											field : 'refundOrdernumber',
											title : '退款订单号',
											width : 250
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
											field : 'terminalNo',
											title : '退款终端号',
											width : 150
										},
										{
											field : 'refundCollector',
											title : '退值操作员',
											width : 120
										},
										{
											field : 'channelCode',
											title : '退值渠道码',
											width : 150
										},
										{
											field : 'id',
											title : '操作',
											width : 100,
											fixed : 'right',
											templet : function(row) {
												if(row.refundStatus != "1")
												{
													var r = '<a class="layui-btn layui-btn-danger layui-btn-xs " lay-event="del" title="退值" onclick="returnConfirm(\''
														+ row.returnLogId
														+ '\')"><i class="fa fa fa-remove"></i></a> ';
													return r;
												}
												return "";
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
												ids[i] = row['returnLogId'];
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
								returnId : $("#returnId").val()
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
			returnId : $("#returnId").val()
		},
		page : {
			curr : 1
		// 重新从第 1 页开始
		}
	});
}

function returnConfirm(detailId) {
	layer.prompt({title: '请输入退款密码，并确认', formType: 1}, function(refundPwd, index){
		  layer.close(index);
			$.ajax({
				url : ctx + "account/accountReturnLog/returnConfirmDetail",
				type : "post",
				data : {
					'detailId' : detailId,
					'refundPwd' : refundPwd
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
		
		});
}