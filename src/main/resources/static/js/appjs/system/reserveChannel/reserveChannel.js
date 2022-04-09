var prefix = ctx + "reserveChannel"

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
//	      {type:'checkbox', fixed: 'left'},
	      {field:'channelCode', title: '渠道编码',width:150, sort: true, fixed: 'left'},
	      {field:'channelName', title: '渠道名称',width:150},
//	      {field:'numberSource', title: '分配号源数'},
	      {field:'delFlag', title: '状态',width:100, 
	    	  templet: function(row){
		    	  if (row.delFlag == '1') {
		    		  statusHtml = '<span class="label label-warning">停用</span>';
		    	  } else {
		    		  statusHtml = '<span class="label label-primary">正常</span>';
		    	  }
		    	  return statusHtml;
	      		}
	      },
	      {field:'interfaceFrequency', title: '阈值（次/分钟）',width:140},
	      {field:'accessKeySecret', title: '鉴权密钥',width:300},
	      {field:'frozenStatus', title: '冻结状态', width:100,
	    	  templet: function(row){
		    	  if (row.frozenStatus == '1') {
		    		  statusHtml = '<span class="label label-warning">冻结</span>';
		    	  } else {
		    		  statusHtml = '<span class="label label-primary">正常</span>';
		    	  }
		    	  return statusHtml;
	      		}
	      },
	      {field:'frozenExpireTime', title: '冻结截止时间', type:"date",width:150},
	      {field:'channelId', title: '操作',fixed:'right',width:150,
	    	  templet: function(row){
	    		  var e = '<a class="layui-btn  layui-btn-xs '+s_edit_h+'" lay-event="edit" title="编辑" onclick="edit(\''
						+ row.channelId + '\')"><i class="fa fa-edit"></i></a> ';
	    		  var p = '<a class="layui-btn layui-btn-normal layui-btn-xs '+s_perms_h+'" lay-event="perms" title="渠道接口权限" onclick="perms(\''
						+ row.channelCode + '\',\'' + row.channelName + '\')"><i class="fa fa-key"></i></a> ';
	    		  var c = "";
	    		  if(row.frozenStatus == '1'){
	    			  c = '<a class="layui-btn  layui-btn-xs '+s_edit_h+'" lay-event="unfreeze" title="解除冻结" onclick="unfreeze(\''
						+ row.channelId + '\')"><i class="fa fa fa-unlock"></i></a> ';
	    		  }
				  return e + p + c;
	      		}
	      }
	    ]],
	    page: {
		    curr: 1 //重新从第 1 页开始
		}
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

function add() {
	var index = layer.open({
		type : 2,
		title : '添加',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '480px' ],
		content : prefix + '/add' // iframe的url
	});
//	layer.full(index);
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
//	layer.full(index);
}
function perms(channelCode,channelName) {
	var index = layer.open({
		type : 2,
		title : channelName,
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '480px' ],
		content : ctx + 'channelInterfacePerms/' + channelCode // iframe的url
	});
	layer.full(index);
}
function unfreeze(id) {
	layer.confirm('确定要解除冻结选中的记录？', {
		btn : [ '确定', '取消' ]
	}, function() {
		$.post(prefix+"/unfreeze",{'channelId' : id},function(r) {
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
