/**
 * 
 */
$(function() {
	// 角色代码
	$('#roleCode_add').validatebox({
		required : true,
		validType : [ "length[1,100]", "not_chinese", "remote[basePath + 'roleController/roleCodeUniqueCheck', 'roleCode']" ]
	});

	// 角色名称
	$('#roleName_add').validatebox({
		required : true,
		validType : [ "length[1,250]" ]
	});

	// 保存按钮
	$('#role_add_btn').linkbutton({});

	// 绑定保存按钮事件
	$('#role_add_btn').click(function() {
		save();
	});

	// 保存方法
	function save() {
		$('#role_form_add').attr('action', basePath + 'roleController/addRole');
		// 提交
		$('#role_form_add').form('submit', {
			onSubmit : function() {// 提交前置事件
				var isValid = $(this).form('validate');
				if (isValid) {// 验证通过,弹出遮罩
					$.messager.progress({
						text : '保存中...',
						interval : 200
					});
				}
				return isValid; // return false will stop the form submission
			},
			success : function(data) {
				try {
					// 关闭遮罩
					$.messager.progress('close');

					// 解析数据
					var datas = strToJson(data);
					if (datas.returnCode == '1') {
						$('#role_window_add').window('close');
						$('#role_table_search_btn').click();
					} else {
						showErrorMsg(datas.result);
					}
				} catch (e) {
					showErrorWindow(data);
				}
			}
		});
	}
});
