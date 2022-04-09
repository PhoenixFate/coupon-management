var prefix = ctx + "coupon/accountInfo";

layui.use('table', function(){
	var table = layui.table;
	var tableObj = table.render({
		elem: '#exampleTable',
	    url: prefix + "/list",
	    where:{
	    	username:$("#username").val(),
	    	phoneno:$("#phoneno").val(),
	    	idno:$("#idno").val(),
	    	accountNo:$("#accountNo").val(),
	    	startTime:$("#startTime").val(),
	    	endTime:$("#endTime").val(),
	    	createChannel:$("#channelCode").val()
	    },
	    limit:10,
	    cols: [[
	      {
			field : 'accountNo', 
			title : '账户号',
			width : 180,
			fixed : 'left'
		  },
				  {
			field : 'username', 
			title : '账户姓名',
			width : 90,
			fixed : 'left'
		  },
		  {
				field : 'phoneno', 
				title : '手机号码',
				width : 120,
				fixed : 'left'
			  },
		  {
				field : 'idno', 
				title : '身份证号码',
				width : 180,
				fixed : 'left'
		  },
		  {
				field : 'accountType', 
				title : '账户类型',
				width : 120,
				templet : function(row) {
					if ("1" == row.accountType) {
						return '<span class="layui-badge layui-bg-orange">某慧医疗</span>';
					} else if ("2" == row.accountType) {
						return '<span class="layui-badge layui-bg-green">医院</span>';
					}
				}
			  },
		  {
			field : 'enableFlag', 
			title : '账户状态',
			width : 100,
			templet : function(row) {
				if ("1" == row.enableFlag) {
					return '<span class="layui-badge layui-bg-green">正常</span>';
				} else if ("2" == row.enableFlag) {
					return '<span class="layui-badge layui-bg-orange">冻结</span>';
				} else if ("3" == row.enableFlag) {
					return '<span class="layui-badge layui-bg-red">停用</span>';
				}else{
					return '<span class="layui-badge layui-bg-red">未知</span>';
				}
			}
		  },
				  {
			field : 'crtTime', 
			title : '开户时间',
			width : 160
		  },
				  {
			field : 'createChannel', 
			title : '开户渠道',
			width : 120
		  },
			      {field:'id', title: '操作',width:150,fixed:'right',
	    	  templet: function(row){
	    		  var e = '<a class="layui-btn  layui-btn-xs " lay-event="edit" title="解冻" onclick="edit(\''
						+ row.accountId + '\', \'' + row.accountNo + '\')"><i class="fa fa-edit"></i></a> ';
	    		  var r = '<a class="layui-btn layui-btn-danger layui-btn-xs " lay-event="del" title="冻结" onclick="remove1(\''
						+ row.accountId + '\', \'' + row.accountNo + '\')"><i class="fa fa fa-remove"></i></a> ';
	    		  var p = '<a class="layui-btn layui-btn-normal layui-btn-xs " lay-event="del" title="重置密码" onclick="resetPwd(\''
						+ row.accountId + '\', \'' + row.accountNo + '\')"><i class="fa fa-key"></i></a> ';
				  return e+r+p;
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
		    	username:$("#username").val(),
		    	phoneno:$("#phoneno").val(),
		    	idno:$("#idno").val(),
		    	accountNo:$("#accountNo").val(),
		    	startTime:$("#startTime").val(),
		    	endTime:$("#endTime").val(),
		    	createChannel:$("#channelCode").val()
			},
			page: {
			    curr: 1 //重新从第 1 页开始
			}
		});
	});
});


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
function edit(id, accountNo) {

	layer.confirm('确定要解冻账户号 ' + accountNo + '？', {
		btn : [ '确定', '取消' ]
	}, function() {
		$.ajax({
			url : prefix+"/unfreeze",
			type : "post",
			data : {
				'accountNo' : accountNo
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

function remove1(id, accountNo) {
	layer.confirm('确定要冻结账户号 ' + accountNo + '？，冻结后该账户将无法消费和退值', {
		btn : [ '确定', '取消' ]
	}, function() {
		$.ajax({
			url : prefix+"/freeze",
			type : "post",
			data : {
				'accountNo' : accountNo
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

function resetPwd(id, accountNo) {
	layer.confirm('确定要重置账户号 ' + accountNo + '的密码？，重置后原密码将不可用', {
		btn : [ '确定', '取消' ]
	}, function() {
		$.ajax({
			url : prefix+"/resetPwd",
			type : "post",
			data : {
				'accountId' : id
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


layui.use('laydate', function(){
	var laydate = layui.laydate;
	laydate.render({ 
		  elem: "#startTime"
			  ,trigger : 'click'
		});
	laydate.render({ 
		  elem: "#endTime"
			  ,trigger : 'click'
		});
});