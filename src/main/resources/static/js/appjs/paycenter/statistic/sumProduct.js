var prefix = ctx + "paycenter/statistic";

layui
		.use(
				'table',
				function() {
					var table = layui.table;
					var tableObj = table
							.render({
								elem : '#exampleTable',
								height: 'full-200',
								url : prefix + "/sumProductList",
								where : {
									payChannel:$("#payChannel").val(),
							    	startTime:$("#startTime").val(),
							    	endTime:$("#endTime").val(),
							    	productCode:$("#productCode").val()
								},
								cols : [ [
										
										{
											field : 'collectorNo',
											title : '产品名称',
											templet : function(row) {
												if ("045" == row.collectorNo) {
													return '人工窗口';
												} else if ("099" == row.collectorNo) {
													return '自助机';
												}else if ("01" == row.collectorNo) {
													return '测试';
												}else {
													return row.collectorNo;
												}
											}
										},
										{
											field : 'totalFeeYan',
											title : '收款金额'
										},
										{
											field : 'nums',
											title : '收款笔数'
										},
										{
											field : 'averagePrice',
											title : '笔均金额'
										},
										{
											field : 'refundFeeYan',
											title : '退款金额'
										},
										{
											field : 'refundNums',
											title : '退款笔数'
										}
										] ]
							});

					// 查询、刷新
					$("#reload").on("click", function() {
						tableObj.reload({
							where : {
								payChannel:$("#payChannel").val(),
						    	startTime:$("#startTime").val(),
						    	endTime:$("#endTime").val(),
						    	productCode:$("#productCode").val()
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

