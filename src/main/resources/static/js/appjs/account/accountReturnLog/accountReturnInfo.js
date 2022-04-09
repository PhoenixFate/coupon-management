var prefix = ctx + "account/accountReturnLog";

function cancel() {
	layer.confirm('确定要取消本次退值？', {
		btn : [ '确定', '取消' ]
	}, function() {
		$.ajax({
			url : prefix + "/remove",
			type : "post",
			data : {
				'returnId' : $("#returnId").val()
			},
			success : function(r) {
				if (r.code == 0) {
					layer.msg(r.msg);
				} else {
					layer.msg(r.msg);
				}
			}
		});
	})
}


function returnConfirm() {
	layer.prompt({title: '请输入退款密码，并确认', formType: 1}, function(refundPwd, index){
		  layer.close(index);
			$.ajax({
				url : prefix + "/returnConfirm",
				type : "post",
				data : {
					'returnId' : $("#returnId").val(),
					'refundPwd' : refundPwd
				},
				success : function(r) {
					if (r.code == 0) {
						layer.msg(r.msg);
					} else {
						layer.msg(r.msg);
					}
				}
			});
		
		});
}
