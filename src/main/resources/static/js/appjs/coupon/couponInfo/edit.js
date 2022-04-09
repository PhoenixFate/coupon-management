$().ready(function() {
	validateRule();
	dispay();
	if($('#remainNums').val() != $("#totalNums").val()){
		$('#remainNums').attr("disabled",true);
		$('#totalNums').attr("disabled",true);
	}
});

$.validator.setDefaults({
	submitHandler : function() {
		update();
	}
});
function update() {
	var checkValue = $('input:radio[name="periodValidType"]:checked').val(); 
	 if (checkValue == "1") {
	  	$("#periodValidDays").val('');
   }
   else {
	  	$("#periodStartTime").val('');
	  	$("#periodEndTime").val('');
	  	
   }
	 
/*	 if($('#remainNums').val() > $("#totalNums").val()){
		 parent.layer.msg("优惠券剩余数不能大于总数");
		 return;
		}*/
	$.ajax({
		cache : true,
		type : "POST",
		url : ctx+"coupon/couponInfo/update",
		data : $('#signupForm').serialize(),// 你的formid
		async : false,
		error : function(request) {
			parent.layer.alert("Connection error");
		},
		success : function(data) {
			if (data.code == 0) {
				parent.layer.msg("操作成功");
				parent.$("#reload").click();
				var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
				parent.layer.close(index);

			} else {
				parent.layer.alert(data.msg)
			}

		}
	});

}

function dispay(){
	var checkValue = $('input:radio[name="periodValidType"]:checked').val(); 
	 if (checkValue == "1") {
    	$("#periodStartTime_div").show();
	  	$("#periodValidDays_div").hide();
	  	$("#periodStartType_div").hide();
    }
    else {
    	$("#periodStartTime_div").hide();
	  	$("#periodValidDays_div").show();
	  	$("#periodStartType_div").show();
    }
}
$('input:radio[name="periodValidType"]').click(function(){
	dispay();
});

function validateRule() {
	var icon = "<i class='fa fa-times-circle'></i> ";
	$("#signupForm").validate({
		rules : {
			periodStartTime : {
				required : true
			},
			periodEndTime : {
				required : true
			},
			periodValidDays : {
				required : true
			},
			couponName : {
				required : true
			},
			totalNums : {
				required : true,
				number : true
			},
			userLimits : {
				required : true,
				number : true
			},
			periodValidDays : {
				required : true,
				number : true
			},
			bizCode : {
				required : true
			}
		},
		messages : {
			/*periodStartTime : {
				required : icon + "卡券为固定时间段有效模式， 请输入起止时间"
			},
			periodEndTime : {
				required : icon + "卡券为固定时间段有效模式， 请输入起止时间"
			},*/
			userLimits : {
				required : icon + "卡券按照发券日期计算有效期， 请输入有效期天数"
			}
		
		}
	})
}

layui.use('laydate', function(){
	var laydate = layui.laydate;
	laydate.render({ 
		  elem: "#periodStartTime",
		  type : 'datetime'
			  ,trigger : 'click'
		});
	laydate.render({ 
		  elem: "#periodEndTime",
		  type : 'datetime'
			  ,trigger : 'click'
		});
});