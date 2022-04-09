var prefix = ctx + "paycenter/wxFundFlow";

layui.use('table', function(){
	var table = layui.table;
	var tableObj = table.render({
		elem: '#exampleTable',
	    url: prefix + "/dayFundlist",
	    where:{
	    	startTime:$("#startTime").val(),
	    	endTime:$("#endTime").val(),
	    	mchId:$("#mchId").val()
	    },
	    limit:10,
	    cols: [[
			{
			field : 'tradeDate', 
			title : '交易日期',
			width : 140,
			fixed : 'left'
		  },
		  {
				field : 'incomeFee', 
				title : '当日收入金额(元)',
				width : 160
		  },
		  {
				field : 'refundFee', 
				title : '当日退款金额(元)',
				width : 160
		  },
		  {
				field : 'diffFee', 
				title : '实收(收入-退款)',
				width : 160
		  },
		  {
				field : 'accountRemain', 
				title : '当日账户结余(元)',
				width : 140
		  },
		  {
				field : 'outcomeFee', 
				title : '提现前一日金额(元)',
				width : 180
		  },
		  {
				field : 'mchId', 
				title : '商户号',
				width : 180
			}
	    ]],
	    page: {
		    curr: 1 //重新从第 1 页开始
		}
	});
	//批量停用
	$("#batchRemove").on("click",function(){
		var checkStatus = table.checkStatus('exampleTable'),
		rows = checkStatus.data;
		if (rows.length == 0) {
			layer.msg("请选择要删除的数据");
			return;
		}
		layer.confirm("确认要删除选中的'" + rows.length + "'条数据吗?", {
			btn : [ '确定', '取消' ]
		}, function() {
			var ids = new Array();
			// 遍历所有选择的行数据，取每条数据对应的ID
			$.each(rows, function(i, row) {
				ids[i] = row['fundId'];
			});
			$.post(
				prefix + '/batchRemove',
				{"ids" : ids },
				function(r) {
					if (r.code == 0) {
						layer.msg(r.msg);
						$("#reload").click();
					} else {
						layer.msg(r.msg);
					}
				}
			);
		});
	});
	//查询、刷新
	$("#reload").on("click",function(){
		tableObj.reload({
			where: { 
				startTime:$("#startTime").val(),
		    	endTime:$("#endTime").val(),
		    	mchId:$("#mchId").val()
			},
			page: {
			    curr: 1 //重新从第 1 页开始
			}
		});
	});
});

function reLoad() {
	tableObj.reload({
			where: { 
				startTime:$("#startTime").val(),
		    	endTime:$("#endTime").val(),
		    	mchId:$("#mchId").val()
			},
			page: {
			    curr: 1 //重新从第 1 页开始
			}
		});
}
function add() {
	layer.open({
		type : 2,
		title : '增加',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '520px' ],
		content : prefix + '/add' // iframe的url
	});
}
function edit(id) {
	layer.open({
		type : 2,
		title : '编辑',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '520px' ],
		content : prefix + '/edit/' + id // iframe的url
	});
}
function remove(id) {
	layer.confirm('确定要删除选中的记录？', {
		btn : [ '确定', '取消' ]
	}, function() {
		$.ajax({
			url : prefix+"/remove",
			type : "post",
			data : {
				'fundId' : id
			},
			success : function(r) {
				if (r.code==0) {
					layer.msg(r.msg);
					reLoad();
				}else{
					layer.msg(r.msg);
				}
			}
		});
	})
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