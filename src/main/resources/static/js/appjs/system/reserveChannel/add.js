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
		url : ctx + "reserveChannel/save",
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
			channelCode : {
				required : true,
				remote : {
					url : ctx + "reserveChannel/checkExists", // 后台处理程序
					type : "post", // 数据发送方式
					dataType : "json", // 接受数据格式
					data : { // 要传递的数据
						channelCode : function(){
							return $("#channelCode").val();
						}
					}
				}
			},
			channelName : {
				required : true
			}
//			numberSource : {
//				required : true,
//				digits : true
//			}
		},
		messages : {
			channelCode : {
				required : icon + "请填写渠道编码",
				remote : icon + "渠道编码已存在"
			},
			channelName : {
				required : icon + "请选填写渠道名称"
			}
//			numberSource : {
//				required : icon + "请填写分配号源数",
//				digits : icon + "请填写整数"
//			}
		}
	})
}
