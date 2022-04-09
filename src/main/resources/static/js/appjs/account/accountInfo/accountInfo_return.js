var prefix = ctx + "account/accountInfo";

layui.use('table', function(){
	var table = layui.table;
	var tableObj = table.render({
		elem: '#exampleTable',
	    url: prefix + "/list",
	    where:{
	    	username:$("#username").val(),
	    	phoneno:$("#phoneno").val(),
	    	idno:$("#idno").val(),
	    	accountType:$("#accountType").val(),
	    	startTime:$("#startTime").val(),
	    	endTime:$("#endTime").val()
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
				width : 110,
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
						return '<span class="layui-badge layui-bg-orange">身份证</span>';
					} else if ("2" == row.accountType) {
						return '<span class="layui-badge layui-bg-orange">医保卡</span>';
					} else if ("3" == row.accountType) {
						return '<span class="layui-badge layui-bg-orange">院内就诊卡</span>';
					} else if ("4" == row.accountType) {
						return '<span class="layui-badge layui-bg-orange">居民健康卡</span>';
					} else{
						return '<span class="layui-badge layui-bg-red">未知卡类型</span>';
					}
				}
			  },
			  {
					field : 'accountBalance', 
					title : '账户余额',
					width : 100,
					templet : function(row) {
						return row.accountBalance/100.0;
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
			      {field:'id', title: '操作',width:120,fixed:'right',
	    	  templet: function(row){
	    		  var a = '<button  class="layui-btn layui-btn-danger layui-btn-xs " lay-event="view"  title="退值" onclick="accountReturn(\''
		    		  + row.accountId + '\',\'' + row.accountNo + '\')"><i class="iconfont icon-caiwutuifei"></i></button> ';
	    		  var b = '<button  class="layui-btn  layui-btn-xs " lay-event="view"  title="退值记录" onclick="returnLog(\'' + row.accountId + '\')"><i class="icon iconfont icon-shouye28"></i></button> ';
				  return a + b;
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
		    	accountType:$("#accountType").val(),
		    	startTime:$("#startTime").val(),
		    	endTime:$("#endTime").val()
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

function remove(id, accountNo) {
	layer.confirm('确定要冻结账户号 ' + accountNo + '？，冻结后该账户将无法消费和退值', {
		btn : [ '确定', '取消' ]
	}, function() {
		$.ajax({
			url : prefix+"/freeze",
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


function returnLog(accountId) {
	var index = layer.open({
		type : 2,
		title : '退费记录',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '480px' ],
		content : ctx + "account/accountReturnLog?accountId=" + accountId // iframe的url
	});
	
	layer.full(index);
}

function returnLogInfo(returnId, accountId) {
	layer.open({
		type : 2,
		title : '退值详情',
		maxmin : false,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '500px', '400px' ],
		content : ctx + "account/accountReturnLog/accountReturnLogInfo?accountId=" + accountId + "&returnId=" + returnId // iframe的url
	});
	
}

function accountReturn(id, accountNo) {
	
	var confirm = layer.confirm('确定要对账户号 ' + accountNo + ' 退值？', {
		btn : [ '确定', '取消' ]
	}, function() {
		$.ajax({
			url : ctx + "account/accountReturnLog/genAccountReturn",
			type : "post",
			data : {
				'accountId' : id
			},
			success : function(r) {
				if (r.code==0) {
					layer.close(confirm);
					$("#reload").click();
					var data = r.data;
					returnLogInfo(data.returnId, id);
					
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
		});
	laydate.render({ 
		  elem: "#endTime"
		});
});