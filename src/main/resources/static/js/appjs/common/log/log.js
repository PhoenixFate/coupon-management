var prefix = ctx + "log"

layui.use('table', function(){
	var table = layui.table;
	var tableObj = table.render({
		elem: '#exampleTable',
	    url: prefix + "/list",
	    where:{
	    	searchOperation : $("#searchOperation").val(),
	    	searchUsername : $("#searchUsername").val()
	    },
	    limit:10,
	    cols: [[
	      {field:'userName', width:130,title: '用户名',sort: true},
	      {field:'operation', width:180,title: '操作'},
	      {field:'time', width:100,title: '用时'},
	      {field:'method', title: '方法'},
	      {field:'ip',width:150, title: 'IP地址'},
	      {field:'createGmt', width:180,title: '创建时间'}
	    ]],
	    page: {
		    curr: 1 //重新从第 1 页开始
		}
	});
	//查询、刷新
	$("#reload").on("click",function(){
		tableObj.reload({
			where: { 
				searchOperation : $("#searchOperation").val(),
		    	searchUsername : $("#searchUsername").val()
			},
			page: {
			    curr: 1 //重新从第 1 页开始
			}
		});
	});
});