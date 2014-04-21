/**
 * 
 */
$(function() {
	$('#role_form_edit').form({
		onLoadSuccess : function(data) { // 加载完毕后在格式化表单

			// 加载数据
			$('#roleName_edit').val(data.roleName);

			// 格式化表单

			// 角色名称
			$('#roleName_edit').validatebox({
				required : true,
				validType : [ "length[1,250]" ]
			});

			// 保存按钮
			$('#role_edit_btn').linkbutton({});

			// 绑定保存按钮事件
			$('#role_edit_btn').click(function() {
				save();
			});
		}
	});

	// 加载表单数据
	$('#role_form_edit').form('load', basePath + 'roleController/loadRole?roleCode=' + $('#roleCode_edit').val());

	// 保存方法
	function save() {
		$('#role_form_edit').attr('action', basePath + 'roleController/editRole');
		// 提交
		$('#role_form_edit').form('submit', {
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
						$('#role_window_edit').window('close');
						$('#role_table_search_btn').click();
					} else if (datas.returnCode == '-1') {
						// 弹出提示
						showErrorMsg(datas.message);
					}
				} catch (e) {
					showErrorWindow(data);
				}
			}
		});
	}
});
