var prefix = ctx + "coupon/couponCashOut";

layui.use('laydate', function(){
	  var laydate = layui.laydate;
	  
	  //执行一个laydate实例
	  laydate.render({
	    elem: '#startTime',
	    trigger: 'click'
	  });
	  
	  laydate.render({
		    elem: '#endTime',
		    	trigger: 'click'
		});
	  
});

var tableObj;

layui.use('table', function(){
	var table = layui.table;
	tableObj = table.render({
		elem: '#exampleTable',
	    url: prefix + "/list",
	    where:{
	    	doctorName:$("#doctorName").val(),
	    	startTime:$("#startTime").val(),
	    	endTime:$("#endTime").val()
	    },
	    limit:10,
	    cols: [[
  		  {
				field : 'doctorCode', 
				title : '医生工号'
			  },
					  {
				field : 'doctorName', 
				title : '医生姓名'
			  },
		  {
			field : 'cashOutNums', 
			title : '本次提现数量'
		  }, 
		  {
				field : 'cashOutTime', 
				title : '提现时间'
			  },
		  {
				field : 'orgCode', 
				title : '医院编码'
			  },
				 
				  {
			field : 'cashOutOperator', 
			title : '提现操作员用户名'
		  },
			      {field:'id', title: '操作',fixed:'right',
	    	  templet: function(row){
	    		/*  var r = '<a class="layui-btn layui-btn-danger layui-btn-xs " lay-event="del" title="删除" onclick="remove(\''
						+ row.id + '\')"><i class="fa fa fa-remove"></i></a> ';*/
	    		  var items = '<a class="layui-btn layui-btn-warm layui-btn-xs " lay-event="items" title="提现详情" onclick="items(\''
						+ row.id
						+ '\')"><i class="fa fa-bars"></i></a> ';
				  return items;
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
				ids[i] = row['id'];
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
				doctorName:$("#doctorName").val(),
		    	startTime:$("#startTime").val(),
		    	endTime:$("#endTime").val()
			},
			page: {
			    curr: 1 //重新从第 1 页开始
			}
		});
	});
});

/*function items(id) {
	var index = layer.open({
		type : 2,
		title : id,
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '480px' ],
		content : prefix + '/coItems/' + id,
		end : function() {
			reLoad();
		}
	});
	layer.full(index);
}
*/

function items(id) {
	var index = layer.open({
		type : 2,
		title : '提现关联的消费详情记录',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '520px' ],
		content : prefix + '/coItems/'+ id
	});
	
	layer.full(index);
}


function reLoad() {
	tableObj.reload({
			where: { 
				searchName1:$("#searchName1").val(),
				searchName2:$("#searchName2").val()
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
				'id' : id
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
			ids[i] = row['id'];
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
					reLoad();
				} else {
					layer.msg(r.msg);
				}
			}
		});
	}, function() {

	});
}