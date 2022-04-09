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
		url : ctx+"coupon/doctorInfo/update",
		data : $('#signupForm').serialize(),// 你的formid
		async : false,
		error : function(request) {
			parent.layer.msg("Connection error");
		},
		success : function(data) {
			if (data.code == 0) {
				parent.layer.msg("操作成功");
				parent.$("#reload").click();
				var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
				parent.layer.close(index);

			} else {
				parent.layer.msg(data.msg)
			}

		}
	});

}
function validateRule() {
	var icon = "<i class='fa fa-times-circle'></i> ";
	$("#signupForm").validate({
		rules : {
			docName : {
				required : true
			},
			hosCode : {
				required : true
			},
			docCode : {
				required : true,
				remote : {
					url : ctx + "coupon/doctorInfo/exit", // 后台处理程序
					type : "post", // 数据发送方式
					dataType : "json", // 接受数据格式
					data : { // 要传递的数据
						hosCode : function(){
							return   $("#hosCode").val();
						},
						docCode : function(){
							return  $("#docCode").val();
						},
						oldDocCode: $("#oldDocCode").val()
					}
				}
			}
			,docPhone:{
				required : true
			}
			
		},
		messages : {
			docName : {
				required : icon + "请输入医生姓名"
			},
			hosCode : {
				required : icon + "请选择所属医院"
			},
			docCode:{
				required : icon + "请输入医生编码",
				remote : icon + "医生编码已存在"
			},
			docPhone:{
				required : icon + "请输入医生手机号码"
			}
		}
	})
}