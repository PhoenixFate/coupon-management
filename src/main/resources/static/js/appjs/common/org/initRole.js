$().ready(function() {
	getRoleTreeData();
	validateRule();
});

$.validator.setDefaults({
	submitHandler : function() {
		save();
	}
});
function getRoleTreeData() {
	$.ajax({
		type : "GET",
		url : ctx + "sys/role/tree",
		success : function(roleTree) {
			loadRoleTree(roleTree);
		}
	});
}
function loadRoleTree(roleTree) {
	$('#roleTree').jstree({
		'core' : {
			"multiple": true,
			'data' : roleTree
		},
		"checkbox" : {
			"three_state" : true,
		},
		"plugins" : [ "wholerow", "checkbox" ]
	});
}
var roleIds;
function getAllSelectNodes() {
	var ref = $('#roleTree').jstree(true); // 获得整个树
	roleIds = ref.get_selected(); // 获得所有选中节点的，返回值为数组
	$("#roleTree").find(".jstree-undetermined").each(function(i, element) {
		roleIds.push($(element).closest('.jstree-node').attr("id"));
	});
}
function save() {
	$.ajax({
		cache : true,
		type : "POST",
		url : ctx + "sys/org/initRoleSave",
		data : {
			orgId : $("#orgId").val(),
			roleIds : roleIds
		},
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
		},
		messages : {
		}
	})
}