$().ready(function() {
	if ($("#modifyFlag").val().length <1){
		$("#oldPasswordDivId").hide();
	}
	
	validateRule();
});

$.validator.setDefaults({
	submitHandler : function() {
		update();
	}
});
function update() {
	
	if ($("#refundPassword").val().length < 6){
		layer.alert("密码长度必须大于6位,请重新输入");
		$("#refundPassword").focus();
		return false;
	}
	
	if ($("#refundPassword").val() != $("#checkRefundPassword").val()){
		layer.alert("两次输入的密码不一致,请重新输入");
		$("#refundPassword").focus();
		return false;
	}
	
	
	$.ajax({
		cache : true,
		type : "POST",
		url : ctx + "org/saveRefundConfig",
		data : {
			orgId:$("#orgId").val(),
			refundPassword:$("#refundPassword").val(),
			refundPassword:$("#refundPassword").val(),
			oldRefundPassword:$("#oldRefundPassword").val()
		},// 你的formid
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
function validateRule() {
	var icon = "<i class='fa fa-times-circle'></i> ";
	$("#signupForm").validate({
		rules : {
			refundPassword : {
				required : true
			},
			checkRefundPassword : {
				required : true
			},
			refundPath : {
				required : true
			}
		},
		messages : {
			refundPassword : {
				required : icon + "请输入退款密码"
			},
			checkRefundPassword : {
				required : icon + "请输入确认密码"
			},
			refundPath : {
				required : icon + "请输入退款地址"
			}
		}
	});
}
