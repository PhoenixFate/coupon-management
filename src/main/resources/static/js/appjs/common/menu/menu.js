var prefix = ctx + "menu"

layui.use('table', function(){
	var table = layui.table;
	//列表页面
	var tableObj = table.render({
		elem: '#exampleTable',
	    url: prefix + "/list",
	    where:{
	    	searchName:$("#searchName").val()
	    },
	    cols: [[
	      {type:'checkbox', fixed: 'left'},
	      {field:'menuName', title: '菜单名', sort: true,
	    	  templet: function(row){
	    		  var menuName = row.menuName+"("+row.remark+")";
	    		  var deepth = row.deepth;
	    		  if(deepth>0){
	    			  menuName = "└&nbsp;&nbsp;"+menuName;
	    			  while(deepth>0){
	    				  menuName = "&nbsp;&nbsp;&nbsp;&nbsp;"+menuName;
	    				  deepth--;
	    			  }
	    			  
	    		  }
	    		  return menuName;
	      		}
	      },
	      {field:'menuUrl', title: '链接'},
	      {field:'menuPerms',  title: '权限'},
	      {field:'menuIcon',  title: '图标',width:100,
	    	  templet: function(row){
	    		  return "<i class='"+row.menuIcon+"'></i>";
      		  }
	      },
	      {field:'menuType', width:100, title: '菜单类型',
	    	  templet: function(row){
	    		  if (row.menuType == 0) {
		    		  return '<span class="label">目录</span>';
		    	  } else if (row.menuType == 1) {
		    		  return '<span class="label">菜单</span>';
		    	  } else if (row.menuType == 2) {
		    		  return '<span class="label">按钮</span>';
		    	  }
	      		}
	      },
	      {field:'menuId',width:150, title: '操作',
	    	  templet: function(row){
	    		  var e = '<a class="layui-btn  layui-btn-xs '+s_edit_h+'" lay-event="edit" title="编辑" onclick="edit(\''
						+ row.menuId + '\')"><i class="fa fa-edit "></i></a> ';
	    		  var d = '<a  class="layui-btn layui-btn-danger layui-btn-xs '+s_remove_h+'" lay-event="del"  title="删除" onclick="remove1(\''
						+ row.menuId + '\')"><i class="fa fa-remove"></i></a> ';
//	    		  var c = '<a  class="layui-btn  layui-btn-xs " lay-event="edit"  title="查看子菜单" onclick="show(\''
//					+ row.menuId + '\')"><i class="fa fa-caret-square-o-down"></i></a> ';
//	    		  if(row.parentMenuId != '0'){
//	    			  return e + d + c;
//	    		  }
	    		  return e + d;
	      		}
	      }
	    ]]
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
			btn : [ '确定', '取消' ]
		// 按钮
		}, function() {
			var ids = new Array();
			// 遍历所有选择的行数据，取每条数据对应的ID
			$.each(rows, function(i, row) {
				ids[i] = row['menuId'];
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
				searchName:$("#searchName").val()
			}
		});
	});
});
function add() {
	layer.open({
		type : 2,
		title : '添加',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '480px' ],
		content : prefix + '/add' // iframe的url
	});
}
function edit(id) {
	layer.open({
		type : 2,
		title : '编辑',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '480px' ],
		content : prefix + '/edit/' + id // iframe的url
	});
}
function remove1(id) {
	layer.confirm('确定要删除选中的记录？', {
		btn : [ '确定', '取消' ]
	}, function() {
		$.ajax({
			url : prefix+"/remove",
			type : "post",
			data : {
				'menuId' : id
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