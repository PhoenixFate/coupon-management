var prefix = ctx + "coupon/accountCouponInfo";

layui.use('table', function(){
	var table = layui.table;
	table =  $.extend(table, {config: {checkName: 'checked'}});
	var tableObj = table.render({
		elem: '#exampleTable',
	    url: prefix + '/couponItemsList',
	    where:{
	    	packageRelationId:$("#relationId").val(),
	    	accountNo:$("#accountNo").val()
	    },
	    cols: [[
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
			}
	    ]]
	});
	
});