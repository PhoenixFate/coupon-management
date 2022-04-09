var prefix = ctx + "coupon/productInfo";

layui.use('table', function(){
	var table = layui.table;
	table =  $.extend(table, {config: {checkName: 'checked'}});
	var tableObj = table.render({
		elem: '#exampleTable',
	    url: prefix+"/productItemsList",
	    where:{
	    	productCode:$("#productCode").val()
	    },
	    cols: [[
	      {type:'checkbox', fixed: 'left'},
	      {field:'packageName', title: '服务包名'},
	      {field:'packageDetail', title: '服务包详情'}
	    ]],
	    page:false,
	    limit: 1000
	});

	//查询、刷新
//	$("#reload").on("click",function(){
//		tableObj.reload({
//			where: { 
//				packageId:$("#packageId").val()
//			}
//		});
//	});
	$("#batchSave").on("click",function(){
		var checkStatus = table.checkStatus('exampleTable');
		rows = checkStatus.data;
//		if (rows.length == 0) {
//			layer.msg("请选择要保存的数据");
//			return;
//		}
		layer.confirm("确认要保存选中的'" + rows.length + "'条数据吗?", {
			btn : [ '确定', '取消' ]
		}, function() {
			var items = new Array();
			// 遍历所有选择的行数据，取每条数据对应的ID
			if (rows.length == 0) {
				var item = {};
				item.productCode = $("#productCode").val();
				item.packageId = "";
				items.push(item);
				
			} else {
				$.each(rows, function(i, row) {
					var item = {};
					item.productCode = $("#productCode").val();
					item.packageId = row['packageId'];
					items.push(item);
				});
			}
			$.ajax({
				cache : true,
				type : "POST",
				contentType : 'application/json;charset=utf-8',
				url : prefix+'/batchSave',
				dataType:"json",//必须
				data : JSON.stringify(items),
				async : false,
				error : function(request) {
					parent.layer.alert("Connection error");
				},
				success : function(data) {
					if (data.code == 0) {
						layer.msg(data.msg);
						tableObj.reload({
							where: { 
								productCode:$("#productCode").val()
							}
						});
					} else {
						layer.alert(data.msg)
					}
				}
			});
		});
	});
});