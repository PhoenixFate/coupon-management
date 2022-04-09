$().ready(function() {
	getMenuTreeData();
	validateRule();
	//打开图标列表
    $("#ico-btn").click(function(){
        layer.open({
            type: 2,
			title:'图标列表',
            content: ctx + 'menu/fontIconList',
            area: ['480px', '90%'],
            success: function(layero, index){
                //var body = layer.getChildFrame('.ico-list', index);
                //console.log(layero, index);
            }
        });
    });
});

$.validator.setDefaults({
	submitHandler : function() {
		update();
	}
});
function getMenuTreeData() {
	$.ajax({
		type : "GET",
		url : ctx + "menu/tree",
		success : function(menuTree) {
			loadMenuTree(menuTree);
		}
	});
}
function loadMenuTree(menuTree) {
	$('#menuTree').jstree({
		'core' : {
			"multiple": false,
			'data' : menuTree
		},
		"checkbox" : {
			"three_state" : false,
		},
		"plugins" : [ "wholerow", "checkbox" ]
	}).on('changed.jstree',function(e,data){//当前选中节点的id
		$("#parentMenuId").val(data.instance.get_node(data.selected[0]).id);
	}).on('loaded.jstree', function(e, data){  
	    var inst = data.instance;  
	    inst.select_node($("#parentMenuId").val());  
	}); 
}
function update() {
	$.ajax({
		cache : true,
		type : "POST",
		url : ctx + "menu/update",
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
			name : {
				required : true
			}
		},
		messages : {
			name : {
				required : icon + "请输入名字"
			}
		}
	})
}