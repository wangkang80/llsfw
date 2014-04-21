/**
 * 
 */
$(function() {

	// 权限代码
	$('#purviewCode_add').validatebox({
		required : true,
		validType : [ "length[1,100]" ]
	});

	// 权限描述
	$('#purviewName_add').validatebox({
		required : true,
		validType : [ "length[1,100]" ]
	});

	// 保存按钮
	$('#purview_add_btn').linkbutton({});

	// 绑定保存按钮事件
	$('#purview_add_btn').click(function() {
		save();
	});

	// 保存方法
	function save() {
		$('#function_purview_form_add').attr('action', basePath + 'functionController/purviewAdd');
		// 提交
		$('#function_purview_form_add').form('submit', {
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
						$('#function_purview_add_windwos').window('close');
						$('#function_purview_search_table').datagrid('load');
					} else if (datas.returnCode == '-1') {
						showErrorMsg(datas.result);
					}
				} catch (e) {
					showErrorWindow(data);
				}

			}
		});
	}
});
