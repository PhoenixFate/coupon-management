var prefix = ctx + "paycenter/unionpayBillsInfo";


$().ready(function() {
});

layui.use('table', function(){
	var table = layui.table;
	//列表页面
	var tableObj = table.render({
		elem: '#exampleTable',
		height: 'full-300',
	    url: prefix + "/list",
	    where:{
	    	hosCode:parent.$("#globalOrgCode").val(),
	    	startTime:$("#startTime").val(),
	    	endTime:$("#endTime").val(),
	    	unionpayId:$("#unionpayId").val(),
			dbz:$("input[name='dbz']").is(':checked'),
			refund:$("input[name='refund']").is(':checked'),
			merchantCodes : $("#merchantCodes").val(),
			cardNumber:$("#cardNumber").val(),
			silverMerchantOrderNo:$("#silverMerchantOrderNo").val(),
			referenceNo:$("#referenceNo").val(),
			terminalCode:$("#terminalCode").val(),
			transAmount:$("#transAmount").val()
	    },
	    limit:20,
	    cols: [[
		  {field : 'silverMerchantOrderNo', title : '银商订单号', width : 250, fixed: 'left', templet:function(row){
	    	  if (null != row.unionpayId){
	    		  return  '<span>' + row.unionpayId +'</span>';
	    	  }else{
	    		  return  '<span>合计</span>';
	    	  }
	      }},
	      {field : 'transDate', title : '交易日期', width : 110},
	      {field : 'transHour', title : '交易时间', width : 100},
	      {field:'unionpayId', title: '交易流水号',  sort: true,width:250},
	      {field : 'merchantCode', title : '商户号', width : 150},
	   /*   {field : 'merchantName', title : '商户名', width : 270},*/
	      {field : 'terminalCode', title : '终端号', width : 100},
	      {field : 'transType', title : '交易类型', width : 100},
	      {field : 'cardNumber', title : '银行卡号', width : 160},
	      {field : 'transAmount', title : '交易金额', width : 100},
	      {field : 'liquidationAmount', title : '清算金额', width : 100},
	      {field : 'handlingFee', title : '手续费', width : 100},
	      {field : 'referenceNo', title : '参考号', width : 140},
	      {field : 'flowNumber', title : '流水号', width : 100},
	      {field : 'businessName', title : '商户名称', width : 170},
	      {field : 'cardType', title : '卡类型', width : 100},
	      {field : 'issuer', title : '发卡行', width : 170},
	      {field : 'accountNumber', title : '账单号', width : 100},
	      {field : 'paymethod', title : '支付方式', width : 100},
	      {field : 'remark', title : '备注', width : 100},
	      {field : 'originalTransactionSerialNo', title : '原交易流水号', width : 170},
	      {field : 'returnOrderNumber', title : '退货订单号', width : 100},
	      {field : 'paymentPostscript', title : '付款附言', width : 100},
	      {field : 'branchShortName', title : '分店简称', width : 100}
	    ]],
	    page: {
		    curr: 1 //重新从第 1 页开始
		}
	});
	//查询、刷新
	$("#reload").on("click",function(){
		tableObj.reload({
			where: { 
				hosCode:parent.$("#globalOrgCode").val(),
		    	startTime:$("#startTime").val(),
		    	endTime:$("#endTime").val(),
		    	unionpayId:$("#unionpayId").val(),
				dbz:$("input[name='dbz']").is(':checked'),
				refund:$("input[name='refund']").is(':checked'),
				merchantCodes : $("#merchantCodes").val(),
				cardNumber:$("#cardNumber").val(),
				silverMerchantOrderNo:$("#silverMerchantOrderNo").val(),
				referenceNo:$("#referenceNo").val(),
				terminalCode:$("#terminalCode").val(),
				transAmount:$("#transAmount").val()
			},
			page: {
			    curr: 1 //重新从第 1 页开始
			}
		});
	});
});


function refresh() {
		layer.confirm('确定刷新账单？', {
			btn : [ '确定', '取消' ]
		}, function() {
			$.ajax({
				cache : true,
				type : "POST",
				url : prefix + "/refresh",
				data : {
					hosCode:parent.$("#globalOrgCode").val(),
			    	startTime:$("#startTime").val(),
			    	endTime:$("#endTime").val(),
				},// 你的formid
				async : false,
				error : function(request) {
					layer.alert("Connection error");
				},
				success : function(data) {
					if (data.code == 0) {
						layer.msg("刷新成功");
						$("#reload").click();
					} else {
						layer.alert(data.msg);
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
    	,value:new Date(new Date()-1000*60*60*24)
	  });

	laydate.render({
	    elem: '#endTime'
	    ,format: 'yyyy-MM-dd'
    	,value:new Date(new Date()-1000*60*60*24)
	  });
});


function daysBetween(sDate1,sDate2){
	//Date.parse() 解析一个日期时间字符串，并返回1970/1/1 午夜距离该日期时间的毫秒数
	var time1 = Date.parse(new Date(sDate1));
	var time2 = Date.parse(new Date(sDate2));
	var nDays = Math.abs(parseInt((time2 - time1)/1000/3600/24));
	return  nDays;
};

function downloadExcel(){
	var days = daysBetween($("#startTime").val(),$("#endTime").val());
	if (days > 30){
		layer.msg("导出日期范围不能超过30天！");
		return false;
	}
	var param="startTime="+$("#startTime").val()+"&endTime="+$("#endTime").val()+"&unionpayId="+$("#unionpayId").val()+"&merchantCodes=" + $("#merchantCodes").val()
		+"&merchantOrderNo="+$("#merchantOrderNo").val()+"&dbz="+$("input[name='dbz']").is(':checked')
		+"&cardNumber="+$("#cardNumber").val()+"&silverMerchantOrderNo="+$("#silverMerchantOrderNo").val()
		+"&terminalCode="+$("#terminalCode").val()+"&transAmount="+$("#transAmount").val()
		+"&referenceNo="+$("#referenceNo").val()+"&refund="+$("input[name='refund']").is(':checked');
	window.location = prefix+"/exportUnionpayZD?"+param;
}