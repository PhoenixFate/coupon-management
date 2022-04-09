var prefix = ctx + "account/accountIcbcZd";

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
									startTime:$("#startTime").val(),
							    	endTime:$("#endTime").val(),
									cardNo : $("#cardNo").val(),
									thirdOrdernumber : $("#thirdOrdernumber").val()
								},
								limit : 10,
								cols : [ [
										{
											field : 'tradeTime',
											title : '交易时间',
											width : 170,
											fixed : 'left'
										},
										{
											field : 'orderFee',
											title : '结算金额',
											fixed : 'left',
											width : 100,
											templet : function(row) {
												return row.orderFee / 100.0
														+ '元';
											}
										},
										{
											field : 'thirdOrdernumber',
											title : '商户订单号',
											width : 270
										},
										{
											field : 'cardNo',
											title : '支付卡号',
											width : 170
										},
										{
											field : 'refundFlag',
											title : '是否退款',
											width : 100,
											templet : function(row) {
												if ("1" == row.refundFlag) {
													return '<span class="layui-badge layui-bg-red">是</span>';
												} else {
													return '<span class="layui-badge layui-bg-green">否</span>';
												}
											}
										} ,

										{
											field : 'tradeIdx',
											title : '交易检索号',
											width : 120
										},
										{
											field : 'channel',
											title : '支付渠道',
											width : 90
										},
										{
											field : 'orderDate',
											title : '清算日期 ',
											width : 110
										},
										{
											field : 'dataSource',
											title : '数据来源',
											width : 90
										},
										{
											field : 'icbcOrdernumber',
											title : '工行订单号',
											width : 270
										}] ],
								page : {
									curr : 1
								// 重新从第 1 页开始
								}
							});
					// 查询、刷新
					$("#reload").on("click", function() {
						tableObj.reload({
							where : {
								startTime:$("#startTime").val(),
						    	endTime:$("#endTime").val(),
								cardNo : $("#cardNo").val(),
								thirdOrdernumber : $("#thirdOrdernumber").val()
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
			startTime:$("#startTime").val(),
	    	endTime:$("#endTime").val(),
			cardNo : $("#cardNo").val(),
			thirdOrdernumber : $("#thirdOrdernumber").val()
		},
		page : {
			curr : 1
		// 重新从第 1 页开始
		}
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
