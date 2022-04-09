var prefix = ctx + "paycenter/payconfigChannelPerms";

layui.use('table', function(){
	var table = layui.table;
	table =  $.extend(table, {config: {checkName: 'checked'}});
	var tableObj = table.render({
		elem: '#exampleTable',
	    url: prefix + "/list",
	    where:{
	    	configId:$("#configId").val(),
	    	configType:$("#configType").val(),
	    	orgCode:$("#orgCode").val()
	    },
	    limit:10,
	    cols: [[
	      {type:'checkbox', fixed: 'left'},
	      {
			field : 'channelCode', 
			title : '渠道码',
			width : 150
		  },
	      {
				field : 'channelName', 
				title : '渠道名称',
				width : 200
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
				configId:$("#configId").val(),
		    	configType:$("#configType").val(),
		    	orgCode:$("#orgCode").val()
			},
			page: {
			    curr: 1 //重新从第 1 页开始
			}
		});
	});
	
	
	$("#batchSave").on("click",function(){
		var checkStatus = table.checkStatus('exampleTable'),
		rows = checkStatus.data;
		layer.confirm("确认要保存选中的'" + rows.length + "'条数据吗?", {
			btn : [ '确定', '取消' ]
		}, function() {
			var ids = new Array();
			// 遍历所有选择的行数据，取每条数据对应的ID
			if (rows.length == 0) {
			} else {
				$.each(rows, function(i, row) {
					ids[i] = row['channelCode'];
				});
			}
			$.post(
				prefix + '/batchSave',
				{"ids" : ids, "configId": $("#configId").val(), "configType": $("#configType").val(), "orgCode": $("#orgCode").val()},
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
});



