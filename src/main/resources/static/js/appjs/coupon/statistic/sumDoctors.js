var prefix = ctx + "coupon/statistic"
var colslist,colslist1;
$().ready(function() {
	
	colslist = eval($("#tableCols").val());
	colslist1 = eval($("#tableCols1").val());
	/*colslist = $("#tableCols").val();
	colslist1 = $("#tableCols1").val();*/
	
});



layui.use('table', function(){
	var table = layui.table;
	//列表页面
	var tableObj = table.render({
		elem: '#exampleTable',
	    url: prefix + "/sumDoctorsList",
	    where:{
	    	startTime:$("#startTime").val(),
	    	endTime:$("#endTime").val(),
	    	orgCode:$("#orgCode").val()
	    },
	    limit:10,
	    height: 450,
	    cols: [
	    	colslist,colslist1
	   ],
	    page: {
		    curr: 1 //重新从第 1 页开始
		}
	});
	//查询、刷新
	$("#reload").on("click",function(){
		tableObj.reload({
			where: { 
		    	startTime:$("#startTime").val(),
		    	endTime:$("#endTime").val(),
		    	orgCode:$("#orgCode").val()
			},
			page: {
			    curr: 1 //重新从第 1 页开始
			}
		});
	});
});

function cashOut(belogtoDoctor){
	var orgCode = $("#orgCode").val();
	if ('' == orgCode){
		layer.alert("请先选择医院查询后再操作");
		layer.msg("请先选择医院查询后再操作");
	}
	layer.confirm("确认要提现("+belogtoDoctor+")医生的优惠券?", {
		btn : [ '确定', '取消' ]
	}, function() {
		 $.ajax({
				cache : true,
				type : "POST",
				url : ctx + "/coupon/accountConsumeLog/cashOut",
				data : {
					startTime:$("#startTime").val(),
			    	endTime:$("#endTime").val(),
					belogtoDoctor:belogtoDoctor,
			    	orgCode:$("#orgCode").val()
				},// 你的formid
				async : false,
				error : function(request) {
					layer.msg("服务异常");
				},
				success : function(data) {
					if (data.code == 1) {
						layer.msg("提现成功");
						$("#reload").click();
					} else {
						layer.msg(data.msg);
					}
				}
		  });
	});
}


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

