$().ready(function() {
	validateRule();
});

$.validator.setDefaults({
	submitHandler : function() {
		update();
	}
});
function update() {
	$.ajax({
		cache : true,
		type : "POST",
		url : ctx + "reserveInterfaceList/update",
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

function validateRule() {
	var icon = "<i class='fa fa-times-circle'></i> ";
	$("#signupForm").validate({
		ignore:"",
		rules : {
			interfaceName : {
				required : true
			},
			interfaceUrl : {
				required : true
			},
			interfacePerms : {
				required : true
			},
			interfaceOrderNum : {
				required : true,
				digits : true
			}
		},
		messages : {
			interfaceName : {
				required : icon + "请选填写接口名称"
			},
			interfaceUrl : {
				required : icon + "请选填写接口URL"
			},
			interfacePerms : {
				required : icon + "请选填写权限码"
			},
			interfaceOrderNum : {
				required : icon + "请填写排序",
				digits : icon + "请填写整数"
			}
		}
	})
}

