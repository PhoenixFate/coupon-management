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
		url : ctx + "dict/updateWithId",
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
			dictName : {
				required : true
			},
			dictValue : {
				required : true
			},
			dictType : {
				required : true
			},
			dictDescription : {
				required : true
			}
			
		},
		messages : {
			dictName : {
				required : icon + "请输入标签名"
			},
			dictValue : {
				required : icon + "请输入数据值"
			},
			dictType : {
				required : icon + "请输入类型"
			},
			dictDescription : {
				required : icon + "请输入类型名称"
			}
		}
	})
}