var prefix = ctx + "coupon/packageInfo";

layui.use('table', function(){
	var table = layui.table;
	table =  $.extend(table, {config: {checkName: 'checked'}});
	var tableObj = table.render({
		elem: '#exampleTable',
	    url: prefix + '/packageItemsList',
	    where:{
	    	packageId:$("#packageId").val()
	    },
	    cols: [[
	      {type:'checkbox', fixed: 'left'},
	      {field:'couponName', title: '卡券名'},
	      {field:'couponDetail', title: '卡券详情'},
	      {field:'couponNums', title: '卡券发放个数',edit:'text'}
	    ]],
	    page:false,
	    limit: 1000
	});

	
	$("#batchSave").on("click",function(){
		var checkStatus = table.checkStatus('exampleTable'),
		rows = checkStatus.data;
		layer.confirm("确认要保存选中的'" + rows.length + "'条数据吗?", {
			btn : [ '确定', '取消' ]
		}, function() {
			var items = new Array();
			// 遍历所有选择的行数据，取每条数据对应的ID
			if (rows.length == 0) {
				var item = {};
				item.packageId = $("#packageId").val();
				item.couponId = "";
				item.couponNums ="";
				items.push(item);
			} else {
				$.each(rows, function(i, row) {
					var item = {};
					item.packageId = $("#packageId").val();
					item.couponId = row['couponId'];
					item.couponNums =row['couponNums'];
					items.push(item);
				});
			}
			
			$.ajax({
				cache : true,
				type : "POST",
				url : prefix + '/batchSave',
				contentType : 'application/json;charset=utf-8',
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
								packageId:$("#packageId").val()
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