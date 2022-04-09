var prefix = ctx + "org"

layui.use('table', function(){
	var table = layui.table;
	var tableObj = table.render({
		elem: '#exampleTable',
	    url: prefix + "/list",
	    where:{
	    	searchName:$("#searchName").val()
	    },
	    limit:10,
	    cols: [[
	      {type:'checkbox', fixed: 'left'},
	      {field:'orgCode', title: '机构编码', sort: true},
	      {field:'orgName', title: '机构名称'},
	      {field:'orgGrade', title: '机构等级'},
	      {field:'orgMobile', title: '联系号码'},
	      {field:'status', title: '状态',templet: function(row){
	    	  	if (row.status == '0') {
					return '<span class="label label-danger">停用</span>';
				} else if (row.status == '1') {
					return '<span class="label label-primary">正常</span>';
				}
			}
	      },
	      {field:'orgId', title: '操作',
	    	  templet: function(row){
	    		  var e = '<a class="layui-btn  layui-btn-xs " title="编辑" onclick="edit(\''
						+ row.orgId + '\')"><i class="fa fa-edit "></i></a> ';
	    		  var d = '<a  class="layui-btn layui-btn-danger layui-btn-xs " title="停用" onclick="remove1(\''
						+ row.orgId + '\')"><i class="fa fa-remove"></i></a> ';
	    		  var p = '<a class="layui-btn layui-btn-warn layui-btn-xs " lay-event="edit" title="退款配置" onclick="refundConfig(\''
					+ row.orgId + '\')"><i class="fa fa-cog "></i></a> ';
				  return e + d + p;
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
			btn : [ '确定', '取消' ]
		// 按钮
		}, function() {
			var ids = new Array();
			// 遍历所有选择的行数据，取每条数据对应的ID
			$.each(rows, function(i, row) {
				ids[i] = row['orgId'];
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
			},
			page: {
			    curr: 1 //重新从第 1 页开始
			}
		});
	});
});


function refundConfig(id) {
	layer.open({
		type : 2,
		title : '退款配置',
		maxmin : true,
		shadeClose : false,
		area : [ '800px', '500px' ],
		content : prefix + '/refundConfig/' + id// iframe的url
	});
}
function add() {
	var index = layer.open({
		type : 2,
		title : '添加',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '480px' ],
		content : prefix + '/add' // iframe的url
	});
	layer.full(index);
}
function edit(id) {
	var index = layer.open({
		type : 2,
		title : '编辑',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '480px' ],
		content : prefix + '/edit/' + id // iframe的url
	});
	layer.full(index);
}
function remove1(id) {
	layer.confirm('确定要停用选中的记录？', {
		btn : [ '确定', '取消' ]
	}, function() {
		$.post(prefix+"/remove",{'orgId' : id},function(r) {
				if (r.code==0) {
					layer.msg(r.msg);
					$("#reload").click();
				}else{
					layer.msg(r.msg);
				}
			}
		);
	})
}
function initRole(id) {
	layer.confirm('确定要初始化角色？', {
		btn : [ '确定', '取消' ]
	}, function() {
		layer.open({
			type : 2,
			title : '编辑',
			maxmin : true,
			shadeClose : false, // 点击遮罩关闭层
			area : [ '800px', '480px' ],
			content : prefix + '/initRole/' + id // iframe的url
		});
	})
}
function initData(orgCode) {
	layer.confirm('确定要初始化基础数据？', {
		btn : [ '确定', '取消' ]
	}, function() {
			$.post(prefix+"/initData",{'orgCode' : orgCode},function(r) {
				if (r.code==0) {
					layer.msg(r.msg);
					$("#reload").click();
				}else{
					layer.msg(r.msg);
				}
			}
		);
	})
}
