var prefix = ctx + "coupon/couponCashOut";

layui.use('table', function(){
	var table = layui.table;
	var tableObj = table.render({
		elem: '#exampleTable',
	    url: prefix + '/coItemsList',
	    where:{
	    	id:$("#id").val()
	    },
	    cols: [[
	    		{
					field : 'cashOutFlag', 
					title : '提现标记',
					width : 100,
					fixed:'left',
					templet : function(row) {
						if ("0" == row.cashOutFlag) {
							return '<span class="layui-badge layui-bg-red">未提现</span>';
						} else if ("1" == row.cashOutFlag) {
							return '<span class="layui-badge layui-bg-green">已提现</span>';
						}else{
							return '<span "></span>';
						}
					}
				  },
				  {
						field : 'couponNo', 
						title : '优惠券号码',
						fixed:'left',
						width : 220
				  },
				  {
					field : 'cashOutTime', 
					title : '提现时间',
					width : 170
				  },
	    	
				 
			  {
					field : 'username', 
					title : '账户姓名',
					width : 110
				  },
	    	  
						  {
					field : 'accountNo', 
					title : '账户号',
					width : 150
				  },
						  {
					field : 'channelCode', 
					title : '消费渠道码',
					width : 110
				  },
						  {
					field : 'consumeTime', 
					title : '消费时间',
					width : 180
				  },
						  {
					field : 'thirdOrdernumber', 
					title : '第三方订单号',
					width : 250
				  },
				 
				 /* {
						field : 'refundFlag', 
						title : '撤销标记',
						width : 150,
						templet : function(row) {
							if ("0" == row.refundFlag) {
								return '<span class="layui-badge layui-bg-red">未退</span>';
							} else if ("1" == row.refundFlag) {
								return '<span class="layui-badge layui-bg-green">已退</span>';
							}else{
								return '<span></span>';
							}
						}
					  },
						  {
					field : 'refundTime', 
					title : '撤销时间',
					width : 150
				  },*/
						  {
					field : 'bizCode', 
					title : '消费业务类型',
					width : 120
				  },
				 
						  {
					field : 'consumeCollector', 
					title : '核销操作员号',
					width : 150
				  },
						  {
					field : 'refundCollector', 
					title : '撤销操作员号',
					width : 150
				  },
				  
				  {
						field : 'consumeStatus', 
						title : '消费状态',
						width : 150,
						templet : function(row) {
							if ("0" == row.consumeStatus) {
								return '<span class="layui-badge layui-bg-red">撤销</span>';
							} else if ("1" == row.consumeStatus) {
								return '<span class="layui-badge layui-bg-green">核销成功</span>';
							}else{
								return '<span"></span>';
							}
						}
					  },
					  {
						field : 'accountId', 
						title : '账户id',
						width : 150
					  },
					  {
					field : 'remark', 
					title : '描述',
					width : 150
				  }
	    ]]
	});

	
});