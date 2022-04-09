$().ready(function() {
	
	validateRule();
	
	//触发的id
	var ids = new Array("attachmentUrl");
	initSysUploader(ids,'',true);
	
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
		url : ctx + "attachment/save",
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
			attachmentName : {
				required : true
			},
			attachmentUrl:{
				required : true
			}
		},
		messages : {
			attachmentName : {
				required : icon + "请填写名称"
			},
			attachmentUrl:{
				required : icon + "请上传附件"
			}
		}
	})
}
