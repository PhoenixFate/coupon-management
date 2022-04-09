$().ready(function() {
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
		url : ctx + "org/save",
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
			orgCode : {
				required : true,
				remote : {
					url : ctx + "org/exit", // 后台处理程序
					type : "post", // 数据发送方式
					dataType : "json", // 接受数据格式
					data : { // 要传递的数据
						orgCode : function(){
							return $("#orgCode").val();
						}
					}
				}
			},
			orgName : {
				required : true
			},
			orgField : {
				required : true
			}
		},
		messages : {
			orgCode : {
				required : icon + "请输入机构编码",
				remote : icon + "机构编码已存在"
			},
			orgName : {
				required : icon + "请输入机构名称"
			},
			orgField : {
				required : icon + "请输入域"
			}
		}
	})
}