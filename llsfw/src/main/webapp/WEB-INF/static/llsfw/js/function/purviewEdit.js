/**
 * 
 */
$(function() {

	// 初始化表单数据
	$('#function_purview_form_edit').form({
		onLoadSuccess : function(data) {

			// 加载数据
			$('#purviewName_edit').val(data.purviewName);

			// 格式化表单

			// 角色名称
			$('#purviewName_edit').validatebox({
				required : true,
				validType : [ "length[1,100]" ]
			});

			// 保存按钮
			$('#purview_edit_btn').linkbutton({});

			// 绑定保存按钮事件
			$('#purview_edit_btn').click(function() {
				save();
			});
		}
	});

	// 加载表单数据
	$('#function_purview_form_edit').form(
			'load',
			basePath + 'functionController/loadPurview?functionCode=' + $('#function_code_edit').val() + '&purviewCode='
					+ $('#purviewCode_edit').val());

	// 保存方法
	function save() {
		$('#function_purview_form_edit').attr('action', basePath + 'functionController/purviewEdit');
		// 提交
		$('#function_purview_form_edit').form('submit', {
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
						$('#function_purview_edit_windwos').window('close');
						$('#function_purview_search_table').datagrid('load');
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
