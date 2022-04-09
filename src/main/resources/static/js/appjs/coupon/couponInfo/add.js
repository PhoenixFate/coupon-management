$().ready(function() {
	validateRule();
	$("#periodValidDays_div").hide();
  	$("#periodStartType_div").hide();
});

$.validator.setDefaults({
	submitHandler : function() {
		save();
	}
});
function save() {
	var checkValue = $('input:radio[name="periodValidType"]:checked').val(); 
	 if (checkValue == "1") {
		  	$("#periodValidDays").val('');
		  	/*if (0 == trim($("#periodStartTime").val()).length){
		  		parent.layer.msg("卡券为固定时间段有效模式， 请输入起止时间");
		  		return ;
			}
		  	if ('' == $("#periodEndTime").val()){
		  		parent.layer.msg("卡券为固定时间段有效模式， 请输入起止时间");
		  		return ;
			}*/
	   }
	   else {
		  	$("#periodStartTime").val('');
		  	$("#periodEndTime").val('');
		  	/*if ($("#periodValidDays").val() > 0){
		  		parent.layer.msg("卡券按照发券日期计算有效期， 请输入有效期天数");
		  		return ;
		  	}*/
	   }
	$.ajax({
		cache : true,
		type : "POST",
		url : ctx+"coupon/couponInfo/save",
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

function trim(str){
	return str.replace(/(^\s*)|(\s*$)/g, "");
}
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

$('input:radio[name="periodValidType"]').click(function(){
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
});

