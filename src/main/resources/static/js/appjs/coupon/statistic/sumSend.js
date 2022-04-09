var prefix = ctx + "coupon/statistic"

var colslist;
$().ready(function() {
	colslist = eval($("#tableCols").val());
});


layui.use('table', function(){
	var table = layui.table;
	//列表页面
	var tableObj = table.render({
		elem: '#exampleTable',
	    url: prefix + "/sumSendList",
	    where:{
	    	startTime:$("#startTime").val(),
	    	endTime:$("#endTime").val()
	    },
	    cols: [
	    	colslist
	    	/*[{
	    		field: 'tradeDate',
	    		title: '发券日期',
	    		fixed: 'left'
	    	},
	    	{
	    		field: '85df6862031b4ae392012bc6ad860e56',
	    		title: '义诊券',
	    		templet: function(row){
	    			if(null!=row.85df6862031b4ae392012bc6ad860e56){
	    				return'<span>'+row.85df6862031b4ae392012bc6ad860e56+'</span>';
	    			}else{
	    				return'<span>0</span>';
	    			}
	    		}
	    	},
	    	{
	    		field: '45281535a1a342f08111329f80d81de3',
	    		title: '推拿按摩券',
	    		templet: function(row){
	    			if(null!=row.45281535a1a342f08111329f80d81de3){
	    				return'<span>'+row.45281535a1a342f08111329f80d81de3+'</span>';
	    			}else{
	    				return'<span>0</span>';
	    			}
	    		}
	    	}]*/
	    ]
	});

	//查询、刷新
	$("#reload").on("click",function(){
		tableObj.reload({
			where: { 
		    	startTime:$("#startTime").val(),
		    	endTime:$("#endTime").val()
			}
		});
	});
});


layui.use('laydate', function(){
	var laydate = layui.laydate;
	laydate.render({
	    elem: '#startTime'
	    ,format: 'yyyy-MM-dd'
	    ,value:new Date()
	  });

	laydate.render({
	    elem: '#endTime'
	    ,format: 'yyyy-MM-dd'
	    ,value:new Date()
	  });
});
