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
		url : ctx + "user/update",
		data : $('#signupForm').serialize(),// 你的formid
		async : false,
		error : function(request) {
			alert("Connection error");
		},
		success : function(data) {
			if (data.code == 0) {
				parent.layer.msg(data.msg);
				parent.$("#reload").click();
				var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
				parent.layer.close(index);

			} else {
				parent.layer.msg(data.msg);
			}

		}
	});
}
function validateRule() {
	var icon = "<i class='fa fa-times-circle'></i> ";
	$("#signupForm").validate({
		rules : {
			userReaName : {
				required : true
			},
			orgCode : {
				required : true,
			},
			roleId : {
				required : true,
			}
		},
		messages : {
			userReaName : {
				required : icon + "请输入姓名",
			},
			orgCode : {
				required : icon + "请选择所属机构",
			},
			roleId : {
				required : icon + "请选择角色",
			}
		}
	})
}