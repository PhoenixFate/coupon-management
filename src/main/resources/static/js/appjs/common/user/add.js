$().ready(function() {
	$("#orgCode").val(parent.parent.$("#globalOrgCode").val());
	validateRule();
});

$.validator.setDefaults({
	submitHandler : function() {
		save();
	}
});
function save() {
	$.ajax({
		cache : true,
		type : "POST",
		url : ctx + "user/save",
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
		rules : {
			userRealName : {
				required : true
			},
			userName : {
				required : true,
				minlength : 2,
				remote : {
					url : ctx + "user/checkExists", // 后台处理程序
					type : "post", // 数据发送方式
					dataType : "json", // 接受数据格式
					data : { // 要传递的数据
						userName : function() {
							return $("#userName").val();
						}
					}
				}
			},
			password : {
				required : true,
				minlength : 6
			},
			confirmPassword : {
				required : true,
				minlength : 6,
				equalTo : "#password"
			},
			orgCode : {
				required : true,
			},
			roleId : {
				required : true,
			}
		},
		messages : {
			userRealName : {
				required : icon + "请输入姓名"
			},
			userName : {
				required : icon + "请输入您的用户名",
				minlength : icon + "用户名必须两个字符以上",
				remote : icon + "用户名已经存在"
			},
			password : {
				required : icon + "请输入您的密码",
				minlength : icon + "密码必须6个字符以上"
			},
			confirmPassword : {
				required : icon + "请再次输入密码",
				minlength : icon + "密码必须6个字符以上",
				equalTo : icon + "两次输入的密码不一致"
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

