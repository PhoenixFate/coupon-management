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
								url : prefix + "/sumBizList",
								where : {
							    	startTime:$("#startTime").val(),
							    	endTime:$("#endTime").val(),
							    	consumeCollector:$("#consumeCollector").val(),
							    	channelCode:$("#channelCode").val()
								},
								cols : [ [
									   {
											field : 'consumeCollector',
											title : '收费员',
											rowspan : 2,
											fixed : 'left',
											width : 120
										},
										{
											title : '挂号',
											align: 'center',
											colspan : 2
										},
										{
											title : '缴费',
											align: 'center',
											colspan : 2
										},
										{
											title : '预约',
											align: 'center',
											colspan : 2
										},
										{
											title : '取号',
											align: 'center',
											colspan : 2
										},
										{
											title : '合计',
											align: 'center',
											colspan : 2
										}],
										[
										{
											field : 'ghSum',
											title : '笔数',
											align: 'center'
										},
										{
											field : 'ghFee',
											title : '金额',
											align: 'center',
											templet : function(row) {
												return row.ghFee / 100.0
														+ '元';
											}
										},
										{
											field : 'jfSum',
											title : '笔数',
											align: 'center'
										},
										{
											field : 'jfFee',
											title : '金额',
											align: 'center',
											templet : function(row) {
												return row.jfFee / 100.0
														+ '元';
											}
										},
										{
											field : 'yySum',
											title : '笔数',
											align: 'center'
										},
										{
											field : 'yyFee',
											title : '金额',
											align: 'center',
											templet : function(row) {
												return row.yyFee / 100.0
														+ '元';
											}
										},
										
										{
											field : 'qhSum',
											title : '笔数',
											align: 'center'
										},
										{
											field : 'qhFee',
											title : '金额',
											align: 'center',
											templet : function(row) {
												return row.qhFee / 100.0
														+ '元';
											}
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
						    	consumeCollector:$("#consumeCollector").val(),
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

