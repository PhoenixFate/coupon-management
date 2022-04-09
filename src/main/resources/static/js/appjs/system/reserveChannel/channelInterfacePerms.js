var prefix = ctx + "channelInterfacePerms"

layui.use('table', function(){
	var table = layui.table;
	table =  $.extend(table, {config: {checkName: 'checked'}});
	var tableObj = table.render({
		elem: '#exampleTable',
	    url: prefix + "/list",
	    where:{
	    	channelCode:$("#channelCode").val()
	    },
	    cols: [[
	      {type:'checkbox', fixed: 'left'},
	      {field:'interfaceName', title: '接口名称'},
	      {field:'interfaceUrl', title: '接口URL'},
	      {field:'interfacePerms', title: '权限码'}
	    ]]
	});
	//查询、刷新
	$("#reload").on("click",function(){
		tableObj.reload({
			where: { 
				channelCode:$("#channelCode").val()
			}
		});
	});
	$("#batchSave").on("click",function(){
		var checkStatus = table.checkStatus('exampleTable'),
		rows = checkStatus.data;
//		if (rows.length == 0) {
//			layer.msg("请选择要保存的数据");
//			return;
//		}
		layer.confirm("确认要保存选中的'" + rows.length + "'条数据吗?", {
			btn : [ '确定', '取消' ]
		}, function() {
			var ids = new Array();
			// 遍历所有选择的行数据，取每条数据对应的ID
			if (rows.length == 0) {
			} else {
				$.each(rows, function(i, row) {
					ids[i] = row['interfaceId'];
				});
			}
			$.post(
				prefix + '/batchSave',
				{"ids" : ids, "channelCode": $("#channelCode").val()},
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