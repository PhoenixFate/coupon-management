var prefix = ctx + "account/accountConsumeLog";

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
									accountNo : $("#accountNo").val(),
									username : $("#username").val(),
									thirdOrdernumber : $("#thirdOrdernumber")
											.val(),
									startTime : $("#startTime").val(),
									endTime : $("#endTime").val(),
									refundFlag : $("#refundFlag").val(),
							    	channelCode:$("#channelCode").val()
								},
								limit : 10,
								cols : [ [
										{
											field : 'accountNo',
											title : '账户号',
											width : 200,
											fixed : 'left',
											templet : function(row) {
												if ("1" == row.refundFlag) {
													return '<span class="layui-badge layui-bg-red">退</span>&nbsp;'
															+ row.accountNo;
												} else {
													return row.accountNo;
												}
											}
										},
										{
											field : 'username',
											title : '账户姓名',
											width : 120,
											fixed : 'left'
										},
										{
											field : 'consumeAmount',
											title : '消费金额',
											width : 100,
											templet : function(row) {
												return row.consumeAmount / 100.0
														+ '元';
											}
										},
										{
											field : 'consumeTime',
											title : '消费时间',
											width : 170
										},
										{
											field : 'thirdOrdernumber',
											title : '第三方订单号',
											width : 270
										},
										{
											field : 'remark',
											title : '描述',
											width : 150
										},
										{
											field : 'terminalNo',
											title : '消费终端号',
											width : 120
										},
										{
											field : 'refundAmount',
											title : '退款金额',
											width : 100,
											templet : function(row) {
												return row.refundAmount / 100.0
														+ '元';
											}
										},
										{
											field : 'accountBalance',
											title : '账户余额',
											width : 100,
											templet : function(row) {
												return row.accountBalance / 100.0
														+ '元';
											}
										},
										{
											field : 'bizName',
											title : '消费业务类型',
											width : 120
										},
										{
											field : 'consumeStatus',
											title : '消费状态',
											width : 100,
											templet : function(row) {
												if ("0" == row.consumeStatus) {
													return '<span class="layui-badge layui-bg-red">失败</span>';
												} else if ("1" == row.consumeStatus) {
													return '<span class="layui-badge layui-bg-green">成功</span>';
												} else if ("2" == row.consumeStatus) {
													return '<span class="layui-badge layui-bg-gray">处理中</span>';
												} else {
													return '';
												}
											}
										},
										{
											field : 'channelCode',
											title : '消费渠道码',
											width : 120
										},
										{
											field : 'id',
											title : '操作',
											width : 90,
											fixed : 'right',
											templet : function(row) {
												
												var html = "";
												if ("1" == row.refundFlag)
												{
													var a = '<a  class="layui-btn layui-btn-warn layui-btn-xs " lay-event="view"  title="退款详情" onclick="refundList(\''
											    		  + row.consumeId + '\',\'' + row.orgCode + '\')"><i class="icon iconfont icon-shouye28"></i></a> ';
													html =  a;
													if(row.refundAmount < row.consumeAmount)
													{
														var b = '<button  class="layui-btn layui-btn-danger layui-btn-xs " lay-event="view"  title="退款" onclick="refund(\''
												    		  + row.thirdOrdernumber + '\',\'' + row.orgCode + '\')"><i class="iconfont icon-caiwutuifei"></i></button> ';
														html = a + b;
													}
													
													return html;
												}
												else
												{
													var a = '<button  class="layui-btn layui-btn-danger layui-btn-xs " lay-event="view"  title="退款" onclick="refund(\''
											    		  + row.thirdOrdernumber + '\',\'' + row.orgCode + '\')"><i class="iconfont icon-caiwutuifei"></i></button> ';
													html = a;
													return html;
												}
												
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
												ids[i] = row['consumeId'];
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
					$("#reload").on(
							"click",
							function() {
								tableObj.reload({
									where : {
										accountNo : $("#accountNo").val(),
										username : $("#username").val(),
										thirdOrdernumber : $(
												"#thirdOrdernumber").val(),
										startTime : $("#startTime").val(),
										endTime : $("#endTime").val(),
										refundFlag : $("#refundFlag").val(),
								    	channelCode:$("#channelCode").val()
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
			accountNo : $("#accountNo").val(),
			username : $("#username").val(),
			thirdOrdernumber : $("#thirdOrdernumber").val(),
			startTime : $("#startTime").val(),
			endTime : $("#endTime").val(),
			refundFlag : $("#refundFlag").val(),
	    	channelCode:$("#channelCode").val()
		},
		page : {
			curr : 1
		// 重新从第 1 页开始
		}
	});
}


function refund(thirdOrdernumber, orgCode) {
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
					url : ctx + "/account/accountConsumeRefundLog/refund",
					data : {
						orderNumber : thirdOrdernumber,
						orgCode : orgCode,
						refundPwd:refundPwd,
						refundAmount:refundAmount
					},// 你的formid
					async : false,
					error : function(request) {
						layer.alert("服务异常");
					},
					success : function(data) {
						if (data.code == 1) {
							layer.msg("退款成功");
							$("#reload").click();
						} else {
							layer.alert(data.msg);
						}

					}
				});
		  });
		});
}

function refundList(consumeId, orgCode) {
	var index = layer.open({
		type : 2,
		title : '退款记录',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '480px' ],
		content : ctx + 'account/accountConsumeRefundLog/accountConsumeRefundLogList/' + consumeId + '/' + orgCode // iframe的url
	});
	layer.full(index);
}

