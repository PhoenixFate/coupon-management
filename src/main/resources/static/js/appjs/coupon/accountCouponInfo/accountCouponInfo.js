var prefix = ctx + "coupon/accountCouponInfo";

layui.use('laydate', function() {
	var laydate = layui.laydate;

	// 发券开始时间
	laydate.render({
		elem : '#provideStartTime',
		type : 'datetime',
		trigger : 'click'
	});

	// 发券结束时间
	laydate.render({
		elem : '#provideEndTime',
		type : 'datetime',
		trigger : 'click'
	});

	// 消费开始时间
	laydate.render({
		elem : '#consumeStartTime',
		type : 'datetime',
		trigger : 'click'
	});

	// 消费结束时间
	laydate.render({
		elem : '#ConsumeEndTime',
		type : 'datetime',
		trigger : 'click'
	});

});

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
									accountNo : $("#accountNo").val(),
									username : $("#username").val(),
									couponNo : $("#couponNo").val(),
									provideStartTime : $("#provideStartTime").val(),
									provideEndTime : $("#provideEndTime").val(),
									couponStatus : $("#couponStatus").val(),
									consumeStartTime : $("#consumeStartTime").val(),
									ConsumeEndTime : $("#ConsumeEndTime").val(),
									productCode : $("#productCode").val(),
									packageName:$("#packageName").val()
								},
								limit : 10,
								cols : [ [

										{
											field : 'couponNo',
											title : '优惠券号码',
											width : 220,
											fixed : 'left'
										},
										{
											field : 'username',
											title : '账户姓名',
											width : 100,
											fixed : 'left'
										},
										{
											field : 'accountNo',
											title : '账户号',
											width : 175,
											fixed : 'left'
										},
										{
											field : 'couponName',
											title : '优惠券名称',
											width : 175
										},
										
										{
											field : 'couponStatus',
											title : '优惠券状态',
											width : 100,
											templet : function(row) {
												if ("0" == row.couponStatus) {
													return '<span class="layui-badge layui-bg-green">未使用</span>';
												} else if ("1" == row.couponStatus) {
													return '<span class="layui-badge layui-bg-red">已使用</span>';
												} else if ("2" == row.couponStatus) {
													return '<span class="layui-badge layui-bg-orange">已冻结</span>';
												} else if ("3" == row.couponStatus) {
													return '<span class="layui-badge layui-bg-blue">已过期</span>';
												}else if ("4" == row.couponStatus) {
													return '<span class="layui-badge layui-bg-blue">已删除</span>';
												}
											}
										},
										{
											field : 'productCode',
											title : '发券商品编码',
											width : 160
										},
										{
											field : 'packageName',
											title : '服务包名称',
											width : 180
										},
										{
											field : 'provideTime',
											title : '发券时间',
											width : 160
										},
										{
											field : 'thirdOrdernumber',
											title : '消费订单号',
											width : 270
										},

										{
											field : 'bizCode',
											title : '业务编码',
											width : 100
										},
										
										{
											field : 'periodStartTime',
											title : '有效期',
											width : 320,
											templet : function(row) {
												return row.periodStartTime
														+ ' ~ '
														+ row.periodEndTime
											}
										},
										{
											field : 'channelCode',
											title : '发券渠道码',
											width : 100
										},
										{
											field : 'consumeTime',
											title : '消费时间',
											width : 160
										},
								// 暂时注释
									   {field:'provideId', title: '操作',width:100,fixed:'right',
										  templet: function(row){
							    		  var html = "";
											if (2 == row.couponStatus){
												html = '<button  class="layui-btn layui-btn-danger '+s_unfreeze_h+' layui-btn-xs " lay-event="view"  title="解冻" onclick="unfreeze(\''
										    		  + row.couponNo + '\')"><i class="iconfont icon-caiwutuifei"></i></button> ';
												
												var a = '<button  class="layui-btn layui-btn-warm '+s_consume_h+' layui-btn-xs " lay-event="view"  title="核销" onclick="consume(\''
									    		  + row.couponNo + '\')"><i class="iconfont icon-icon24"></i></button> ';
												return html + a;
											}
											if (0 == row.couponStatus){
												html = '<button  class="layui-btn layui-btn-danger '+s_freeze_h+' layui-btn-xs " lay-event="view"  title="冻结" onclick="freeze(\''
										    		  + row.couponNo + '\')"><i class="iconfont icon-caiwutuifei"></i></button> ';
												
												return html;
											} 
											return html;
							      		}
								      }
								] ],
								page : {
									curr : 1
								// 重新从第 1 页开始
								}
							});
					// 查询、刷新
					$("#reload")
							.on(
									"click",
									function() {
										tableObj
												.reload({
													where : {
														accountNo : $("#accountNo").val(),
														username : $("#username").val(),
														couponNo : $("#couponNo").val(),
														provideStartTime : $("#provideStartTime").val(),
														provideEndTime : $("#provideEndTime").val(),
														couponStatus : $("#couponStatus").val(),
														consumeStartTime : $("#consumeStartTime").val(),
														ConsumeEndTime : $("#ConsumeEndTime").val(),
														productCode : $("#productCode").val(),
														packageName:$("#packageName").val()
													},
													page : {
														curr : 1
													// 重新从第 1 页开始
													}
												});
									});
				});


function consume(couponNo) {
	layer.confirm("确认要核销该优惠券吗", {
		btn : [ '确定', '取消' ]
	}, function() {
		 $.ajax({
				cache : true,
				type : "POST",
				url : prefix + "/consume",
				data : {
					couponNo:couponNo
				},// 你的formid
				async : false,
				error : function(request) {
					layer.msg("服务异常");
				},
				success : function(data) {
					if (data.code == 0) {
						layer.msg("核销成功");
						$("#reload").click();
					} else {
						layer.msg(data.msg);
					}
				}
		  });
	});
}



function unfreeze(couponNo) {
	layer.confirm("确认要解冻该优惠券吗?解冻后优惠券可正常使用", {
		btn : [ '确定', '取消' ]
	}, function() {
		$.ajax({
			cache : true,
			type : "POST",
			url : prefix + "/unfreeze",
			data : {
				couponNo:couponNo
			},// 你的formid
			async : false,
			error : function(request) {
				layer.msg("服务异常");
			},
			success : function(data) {
				if (data.code == 0) {
					layer.msg("解冻成功");
					$("#reload").click();
				} else {
					layer.msg(data.msg);
				}
			}
	  });
	})
}


function freeze(couponNo) {
	layer.confirm("确认要冻结该优惠券吗?", {
		btn : [ '确定', '取消' ]
	}, function() {
		$.ajax({
			cache : true,
			type : "POST",
			url : prefix + "/freeze",
			data : {
				couponNo:couponNo
			},// 你的formid
			async : false,
			error : function(request) {
				layer.msg("服务异常");
			},
			success : function(data) {
				if (data.code == 0) {
					layer.msg("冻结成功");
					$("#reload").click();
				} else {
					layer.msg(data.msg);
				}
			}
	  });
	})
}

