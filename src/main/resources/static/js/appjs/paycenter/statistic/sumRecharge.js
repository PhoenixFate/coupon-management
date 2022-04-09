var prefix = ctx + "paycenter/statistic";

layui
		.use(
				'table',
				function() {
					var table = layui.table;
					var tableObj = table
							.render({
								elem : '#exampleTable',
								even: true, //开启隔行背景
							    size: 'sm', //小尺寸的表格
								height: 'full-200',
								url : prefix + "/sumRechargeList",
								where : {
							    	startTime:$("#startTime").val(),
							    	endTime:$("#endTime").val(),
							    	rechargeCollector:$("#rechargeCollector").val(),
							    	channelCode:$("#channelCode").val()
								},
								cols : [ [
									   {
											field : 'rechargeCollector',
											title : '收费员',
											rowspan : 2,
											fixed : 'left'
										},
										{
											title : '现金充值',
											align: 'center',
											colspan : 2,
											hide : hideCols['01']
										},
										{
											title : '银行卡充值',
											align: 'center',
											colspan : 2,
											hide : hideCols['04']
										},
										{
											title : '支付宝充值',
											align: 'center',
											colspan : 2,
											hide : hideCols['02']
										},
										{
											title : '微信充值',
											align: 'center',
											colspan : 2,
											hide : hideCols['03']
										},
										{
											title : '银联聚合支付充值',
											align: 'center',
											colspan : 2,
											hide : hideCols['11']
										},
										{
											title : '工行聚合支付充值',
											align: 'center',
											colspan : 2,
											hide : hideCols['13']
										},
										{
											title : '合计',
											align: 'center',
											colspan : 2
										}],
										[
										{
											field : 'cashSum',
											title : '笔数',
											align: 'center',
											hide : hideCols['01']
										},
										{
											field : 'cashFee',
											title : '金额',
											align: 'center',
											templet : function(row) {
												return row.cashFee / 100.0
														+ '元';
											},
											hide : hideCols['01']
										},
										{
											field : 'bankSum',
											title : '笔数',
											align: 'center',
											hide : hideCols['04']
										},
										{
											field : 'bankFee',
											title : '金额',
											align: 'center',
											templet : function(row) {
												return row.bankFee / 100.0
														+ '元';
											},
											hide : hideCols['04']
										},
										{
											field : 'zfbSum',
											title : '笔数',
											align: 'center',
											hide : hideCols['02']
										},
										{
											field : 'zfbFee',
											title : '金额',
											align: 'center',
											templet : function(row) {
												return row.zfbFee / 100.0
														+ '元';
											},
											hide : hideCols['02']
										},
										
										{
											field : 'wxSum',
											title : '笔数',
											align: 'center',
											hide : hideCols['03']
										},
										{
											field : 'wxFee',
											title : '金额',
											align: 'center',
											templet : function(row) {
												return row.wxFee / 100.0
														+ '元';
											},
											hide : hideCols['03']
										},
										
										{
											field : 'unionpaySum',
											title : '笔数',
											align: 'center',
											hide : hideCols['11']
										},
										{
											field : 'unionpayFee',
											title : '金额',
											align: 'center',
											templet : function(row) {
												return row.unionpayFee / 100.0
														+ '元';
											},
											hide : hideCols['11']
										},
										{
											field : 'icbcSum',
											title : '笔数',
											align: 'center',
											hide : hideCols['13']
										},
										{
											field : 'icbcFee',
											title : '金额',
											align: 'center',
											templet : function(row) {
												return row.icbcFee / 100.0
														+ '元';
											},
											hide : hideCols['13']
										},
										{
											field : 'totalSum',
											align: 'center',
											title : '笔数'
										},
										{
											field : 'totalFee',
											title : '金额',
											align: 'center',
											templet : function(row) {
												return row.totalFee / 100.0
														+ '元';
											}
										}
										] ]
							});

					// 查询、刷新
					$("#reload").on("click", function() {
						tableObj.reload({
							where : {
								startTime:$("#startTime").val(),
						    	endTime:$("#endTime").val(),
						    	rechargeCollector:$("#rechargeCollector").val(),
						    	channelCode:$("#channelCode").val()
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

