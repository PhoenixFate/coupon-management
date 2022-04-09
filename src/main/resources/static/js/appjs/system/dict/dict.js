var prefix = ctx + "dict";

layui.use('table', function(){
	var table = layui.table;
	//列表页面
	var tableObj = table.render({
		elem: '#exampleTable',
	    url: prefix + "/listWithId",
	    where:{
	    	searchName:$('#searchName').val(),
	    	typeId:$('#typeId').val()
	    },
	    limit:10,
	    cols: [[
	      {type:'checkbox', fixed: 'left'},
	      {field:'dictName',title:'标签名',fixed: 'left'},
	      {field:'dictValue',title:'数据值',},
	      {field:'dictType',title:'类型'},
	      {field:'dictDescription',title:'类型名称'},
	      {field:'dictSort',title:'排序（升序）'},
	     /* {field:'parentDictId',title:'父级编号'},*/
	      {field:'remarks',title:'备注信息'},
	      {field:'dictId', title: '操作',fixed:'right', 
	    	  templet: function(row){
	    		  var e = '<a class="layui-btn layui-btn-xs '+s_edit_h+'" lay-event="edit" title="编辑" onclick="edit(\''
						+ row.dictId + '\')"><i class="fa fa-edit "></i></a> ';
	    		  var d = '<a  class="layui-btn layui-btn-danger layui-btn-xs '+s_remove_h+'" lay-event="del"  title="删除" onclick="remove1(\''
		    		  + row.dictId + '\')"><i class="fa fa-remove"></i></a> ';
				  return e  + d;
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
			layer.msg("请选择要停用的数据");
			return;
		}
		layer.confirm("确认要停用选中的'" + rows.length + "'条数据吗?", {
			btn:[ '确定', '取消' ]
		// 按钮
		}, function() {
			var ids = new Array();
			// 遍历所有选择的行数据，取每条数据对应的ID
			$.each(rows, function(i, row) {
				ids[i] = row['dictId'];
			});
			$.post(
				prefix + '/batchRemoveWithId',
				{"ids":ids },
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
		//selectLoad();
		tableObj.reload({
			where: { 
				searchName:$('#searchName').val(),
				typeId:$('#typeId').val()
			},
			page: {
			    curr: 1 //重新从第 1 页开始
			}
		});
	})
});
function add() {
	// iframe层
	layer.open({
		type:2,
		title:'增加',
		maxmin:true,
		shadeClose:false, // 点击遮罩关闭层
		area:[ '800px', '520px' ],
		content:prefix + '/addWithId'
	});
}
function remove1(dictId) {
	layer.confirm('确定要删除选中的记录？', {
		btn:[ '确定', '取消' ]
	}, function() {
		$.post(prefix + "/removeWithId",{'dictId':dictId},function(r) {
				if (r.code == 0) {
					layer.msg(r.msg);
					$("#reload").click();
				} else {
					layer.msg(r.msg);
				}
			}
		);
	});
}
function edit(id) {
	layer.open({
		type:2,
		title:'修改',
		maxmin:true,
		shadeClose:false,
		area:[ '800px', '520px' ],
		content:prefix + '/editWithId/' + id // iframe的url
	});
}
