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
		url : ctx + "reserveChannel/update",
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
			channelName : {
				required : true
			}
//			numberSource : {
//				required : true,
//				digits : true
//			}
		},
		messages : {
			channelName : {
				required : icon + "请填写渠道名称"
			}
//			numberSource : {
//				required : icon + "请填写分配号源数",
//				digits : icon + "请填写整数"
//			}
		}
	})
}

