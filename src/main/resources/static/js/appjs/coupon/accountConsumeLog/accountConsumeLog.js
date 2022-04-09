var prefix = ctx + "coupon/accountConsumeLog";

layui.use('laydate', function(){
	  var laydate = layui.laydate;
	  
	  //执行一个laydate实例
	  laydate.render({
	    elem: '#startTime' //指定元素
	  });
	  
	  laydate.render({
		    elem: '#endTime' //指定元素
	 });
	  
	});

var tableObj;

layui.use('table', function(){
	var table = layui.table;
	tableObj = table.render({
		elem: '#exampleTable',
	    url: prefix + "/list",
	    where:{
	    	startTime:$("#startTime").val(),
	    	endTime:$("#endTime").val(),
	    	accountNo : $("#accountNo").val(),
			couponNo : $("#couponNo").val(),
			consumeStatus : $("#consumeStatus").val(),
			productCode : $("#productCode").val()
	    },
	    limit:10,
	    cols: [[
		    /*  {type:'checkbox', fixed: 'left'},
		      		  {
				field : 'consumeId', 
				title : '消费流水号',
				width : 150
			  },
					  {
				field : 'accountId', 
				title : '账户id',
				width : 150
			  },*/
	    	 {
				field : 'couponNo', 
				title : '优惠券号码',
				width : 220,
				fixed : 'left'
			  }, 
			  {
					field : 'username', 
					title : '账户姓名',
					width : 110,
					fixed : 'left'
				  },
			  {
				field : 'accountNo', 
				title : '账户号',
				width : 180,
				fixed : 'left'
			  },
			  
			  {
					field : 'couponName',
					title : '优惠券名称',
					width : 175
				},
			 
	    	{
				field : 'consumeStatus', 
				title : '消费状态',
				width : 120,
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
				field : 'channelCode', 
				title : '消费渠道码',
				width : 120
			  },
					  {
				field : 'consumeTime', 
				title : '消费时间',
				width : 180
			  },
					
			  {
					field : 'productCode', 
					title : '优惠券对应的产品编码',
					width : 170
				  },
			 
			  {
					field : 'refundFlag', 
					title : '撤销标记',
					width : 120,
					templet : function(row) {
						if ("0" == row.refundFlag) {
							return '<span class="layui-badge layui-bg-green">未撤销</span>';
						} else if ("1" == row.refundFlag) {
							return '<span class="layui-badge layui-bg-red">已撤销</span>';
						}else{
							return '<span></span>';
						}
					}
				  },
				  {
					field : 'cashOutFlag', 
					title : '提现标记',
					width : 120,
					templet : function(row) {
						if ("0" == row.cashOutFlag) {
							return '<span class="layui-badge layui-bg-green">未提现</span>';
						} else if ("1" == row.cashOutFlag) {
							return '<span class="layui-badge layui-bg-red">已提现</span>';
						}else{
							return '<span "></span>';
						}
					}
				  },
				  {
						field : 'thirdOrdernumber', 
						title : '第三方订单号',
						width : 220
					  },
				  {
				field : 'refundTime', 
				title : '撤销时间',
				width : 180
			  },
					  {
				field : 'bizCode', 
				title : '消费业务类型',
				width : 150
			  },
			  
					  {
				field : 'orgCode', 
				title : '医院代码',
				width : 150
			  },
					 /* {
				field : 'version', 
				title : '乐观锁',
				width : 150
			  },*/
					
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
				field : 'couponId', 
				title : '优惠券基础信息ID',
				width : 150
			  },
				
					  {
				field : 'refundChannelCode', 
				title : '撤销渠道',
				width : 150
			  },
					  {
				field : 'belogtoDoctor', 
				title : '业绩所属医生工号',
				width : 150
			  },
					  {
				field : 'consumeDoctorId', 
				title : 'doctor_info表主键',
				width : 150
			  },
			  
					  {
				field : 'cashOutTime', 
				title : '提现时间',
				width : 180
			  },
					  {
				field : 'cashOutFlow', 
				title : '提现记录表的主键',
				width : 150
			  }, 
			  {
					field : 'remark', 
					title : '描述',
					width : 150
				  },
		      {field:'consumeId', title: '操作',width:60,fixed:'right',
				  templet: function(row){
	    		  var html = "";
					if ("1" == row.consumeStatus && "1" != row.cashOutFlag){
						html = '<button  class="layui-btn layui-btn-danger '+s_refund_h+' layui-btn-xs " lay-event="view"  title="撤销" onclick="refund(\''
				    		  + row.consumeId + '\')"><i class="iconfont icon-caiwutuifei"></i></button> ';
					}
					return html;
	      		}
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
				ids[i] = row['channelCode'];
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
		    	accountNo : $("#accountNo").val(),
				couponNo : $("#couponNo").val(),
				consumeStatus : $("#consumeStatus").val(),
				productCode : $("#productCode").val()
			},
			page: {
			    curr: 1 //重新从第 1 页开始
			}
		});
	});
	
});


function refund(consumeId) {
	layer.confirm("确认要撤销该条消费记录吗?", {
		btn : [ '确定', '取消' ]
	}, function() {
		 $.ajax({
				cache : true,
				type : "POST",
				url : ctx + "/coupon/accountConsumeLog/refund",
				data : {
					consumeId:consumeId
				},// 你的formid
				async : false,
				error : function(request) {
					layer.msg("服务异常");
				},
				success : function(data) {
					if (data.code == 0) {
						layer.msg("撤销成功");
						$("#reload").click();
					} else {
						layer.msg(data.msg);
					}
				}
		  });
	});
}

function reLoad() {
	tableObj.reload({
			where: { 
				startTime:$("#startTime").val(),
				endTime:$("#endTime").val()
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
function edit(consumeId) {
	layer.open({
		type : 2,
		title : '编辑',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '520px' ],
		content : prefix + '/edit/' + consumeId // iframe的url
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
				'consumeId' : id
			},
			success : function(r) {
				if (r.code==0) {
					layer.msg(r.msg);
					$("#reload").click();
				}else{
					layer.msg(r.msg);
				}
			}
		});
	})
}

function resetPwd(id) {
}
function batchRemove() {
	var rows = $('#exampleTable').bootstrapTable('getSelections'); // 返回所有选择的行，当没有选择的记录时，返回一个空数组
	if (rows.length == 0) {
		layer.msg("请选择要删除的数据");
		return;
	}
	layer.confirm("确认要删除选中的'" + rows.length + "'条数据吗?", {
		btn : [ '确定', '取消' ]
	// 按钮
	}, function() {
		var ids = new Array();
		// 遍历所有选择的行数据，取每条数据对应的ID
		$.each(rows, function(i, row) {
			ids[i] = row['consumeId'];
		});
		$.ajax({
			type : 'POST',
			data : {
				"ids" : ids
			},
			url : prefix + '/batchRemove',
			success : function(r) {
				if (r.code == 0) {
					layer.msg(r.msg);
					$("#reload").click();
				} else {
					layer.msg(r.msg);
				}
			}
		});
	}, function() {

	});
}