var prefix = ctx + "paycenter/payOrder";

layui
		.use(
				'table',
				function() {
					var table = layui.table;
					var tableObj = table
							.render({
								elem : '#exampleTable',
								height: 'full-300',
								url : prefix + "/list",
								where : {
									channelCode:$("#channelCode").val(),
									orderNumber:$("#orderNumber").val(),
									payChannel:$("#payChannel").val(),
									bizName:$("#bizName").val(),
							    	startTime:$("#startTime").val(),
							    	endTime:$("#endTime").val(),
							    	userName:$("#userName").val(),
							    	paystatus:$("#paystatus").val(),
							    	payMode:$("#payMode").val(),
							    	bizResultFlag:$("#bizResultFlag").val(),
							    	collectorNo:$("#collectorNo").val()
								},
								limit : 20,
								cols : [ [
										{
											field : 'paystatus',
											fixed : 'left',
											title : '状态',
											width : 80,
											templet : function(row) {
												if ("00" == row.paystatus) {
													return '<span class="layui-badge layui-bg-black">未支付</span>';
												} else if ("02" == row.paystatus) {
													return '<span class="layui-badge layui-bg-orange">失败</span>';
												} else if ("01" == row.paystatus) {
													return '<span class="layui-badge layui-bg-green">成功</span>';
												} else if ("05" == row.paystatus) {
													return '<span class="layui-badge layui-bg-red">退</span>';
												} else if ("03" == row.paystatus) {
													return '<span class="layui-badge">撤销</span>';
												} else if ("04" == row.paystatus) {
													return '<span class="layui-badge layui-bg-gray">关闭</span>';
												} else {
													return '';
												}
											}
										},
										{
											field : 'orderNumber',
											title : '订单编号',
											width : 270,
											fixed : 'left'
										},
										{
											field : 'ordertime',
											title : '下单时间',
											width : 170
										},
										{
											field : 'totalFeeYan',
											title : '订单金额',
											width : 110
										},
										{
											field : 'incomeFeeYan',
											title : '实收金额',
											width : 130
										},
										{
											field : 'incometime',
											title : '收款时间',
											width : 170
										},
										{
											field : 'userName',
											title : '用户姓名',
											width : 100
										},
										{
											field : 'accountNo',
											title : '付款账号',
											width : 140
										},
										{
											field : 'payChannel',
											title : '支付方式',
											width : 110,
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
												} else {
													return '';
												}
											}
										},
										{
											field : 'mchId',
											title : '商户号',
											width : 160
										},
										{
											field : 'subMchId',
											title : '子商户号',
											width : 120
										},
										{
											field : 'transactionId',
											title : '第三方支付订单号',
											width : 260
										},
										{
											field : 'refundFeeYan',
											title : '退款金额',
											width : 130
										},
										{
											field : 'productCode',
											title : '产品编码',
											width : 90
										},
										{
											field : 'collectorNo',
											title : '收费员',
											width : 100
										},
										{
											field : 'bizName',
											title : '业务名称',
											width : 120
										},
										{
											field : 'channelCode',
											title : '调用渠道',
											width : 120
										},
										{
											field : 'payMode',
											title : '支付类型',
											width : 100
										},
										{
											field : 'bizResultFlag',
											title : '业务状态',
											width : 100,
											templet : function(row) {
												if ("0" == row.bizResultFlag) {
													return '<span class="layui-badge layui-bg-red">失败</span>';
												} else if ("1" == row.bizResultFlag) {
													return '<span class="layui-badge layui-bg-green">成功</span>';
												} else if ("2" == row.bizResultFlag) {
													return '<span class="layui-badge layui-bg-red">超时</span>';
												} else if ("3" == row.bizResultFlag) {
													return '<span class="layui-badge layui-bg-orange">处理中</span>';
												} else if ("4" == row.bizResultFlag) {
													return '<span class="layui-badge layui-bg-black">未知</span>';
												} else if ("5" == row.bizResultFlag) {
													return '<span class="layui-badge layui-bg-gray">取消</span>';
												} else {
													return '';
												}
											}
										},
										{
											field : 'bizFlow',
											title : '业务流水号',
											width : 180
										},
										{
											field : 'bizUploadTime',
											title : '业务上传时间',
											width : 180
										},
										{
											field : 'flow',
											title : '操作',
											width : 80,
											fixed : 'right',
											templet : function(row) {
												var html = "";
												if("05" == row.paystatus)
												{
													var a = '<a  class="layui-btn layui-btn-warn layui-btn-xs " lay-event="view"  title="退款详情" onclick="refundList(\''
											    		  + row.orderNumber + '\',\'' + row.orgCode + '\')"><i class="icon iconfont icon-shouye28"></i></a> ';
													return a;
												}
												if("01" == row.paystatus)
												{
													var a = '<button  class="layui-btn layui-btn-danger layui-btn-xs " lay-event="view"  title="退款" onclick="refund(\''
											    		  + row.orderNumber + '\',\'' + row.orgCode + '\')"><i class="iconfont icon-caiwutuifei"></i></button> ';
													return a;
												}
									    		  
												return '';
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
												ids[i] = row['orderNumber'];
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
								channelCode:$("#channelCode").val(),
								orderNumber:$("#orderNumber").val(),
								payChannel:$("#payChannel").val(),
								bizName:$("#bizName").val(),
						    	startTime:$("#startTime").val(),
						    	endTime:$("#endTime").val(),
						    	userName:$("#userName").val(),
						    	paystatus:$("#paystatus").val(),
						    	payMode:$("#payMode").val(),
						    	bizResultFlag:$("#bizResultFlag").val(),
						    	collectorNo:$("#collectorNo").val()
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


function refund(orderNumber, orgCode) {
	
	layer.prompt({title: '请输入退款密码，并确认', formType: 1}, function(refundPwd, index){
		  layer.close(index);
		  layer.prompt({title: '请输入退款金额(单位元,支持两位小数)，并确认', formType: 3}, function(refundAmount, index){
			  var fix_amount=/^(([1-9]\d*)|\d)(\.\d{1,2})?$/;
			  if(fix_amount.test(refundAmount)==false){
			       layer.alert("请输入有效金额");
			       return false;
		      }
			  if(refundAmount == 0){
				  layer.alert("退款金额不能等于0");
			       return false;
			  }
			  layer.close(index);
			  $.ajax({
					cache : true,
					type : "POST",
					url : ctx + "paycenter/payOrderRefund/refund",
					data : {
						orderNumber : orderNumber,
						orgCode : orgCode,
						refundPwd:refundPwd,
						refundAmount:refundAmount
					},// 你的formid
					async : false,
					error : function(request) {
						layer.alert("Connection error");
					},
					success : function(data) {
						if (data.rspCode == 1) {
							layer.msg("退款成功");
							$("#reload").click();
						} else {
							layer.alert(data.rspMsg);
						}

					}
				});
		  });
		});
}

function refundList(orderNumber, orgCode) {
	var index = layer.open({
		type : 2,
		title : '退款记录',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '480px' ],
		content : ctx + 'paycenter/payOrderRefund/' + orderNumber + '/' + orgCode // iframe的url
	});
	layer.full(index);
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

