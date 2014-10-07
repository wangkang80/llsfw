/**
 * 
 */

$(function() {
	$('#add_user_function_tree').tree(
			{
				url : basePath + 'userController/loadUserFunctionTree?userName=' + $('#add_user_function_userName').val(),
				checkbox : true,
				onlyLeafCheck : true,
				parentField : "PARENT_FUNCTION_CODE",
				textFiled : "FUNCTION_NAME",
				idFiled : "FUNCTION_CODE",
				onCheck : function(node, checked) {
					var functionCode = node.F_CODE;
					var purviewCode = node.P_CODE;
					$('#add_user_function_function_code').val(functionCode);
					$('#add_user_function_purview_code').val(purviewCode);
					if (checked) {
						$('#add_user_function_form_edit').attr('action', basePath + 'userController/addUserFunction');
						$('#add_user_function_form_edit').form('submit', {
							onSubmit : function() {// 提交前置事件
								var isValid = $(this).form('validate');
								if (isValid) {// 验证通过,弹出遮罩
									$.messager.progress({
										text : '保存中...',
										interval : 200
									});
								}
								return isValid;
							},
							success : function(data) {
								try {
									// 关闭遮罩
									$.messager.progress('close');

									// 解析数据
									var datas = strToJson(data);

									if (datas.returnCode == '1') {
										showInfoMsg(datas.result);
									} else {
										showErrorMsg(datas.result);
									}
								} catch (e) {
									showErrorWindow(data);
								}
							}
						});
					} else {
						$.messager.progress({
							text : '保存中...',
							interval : 200
						});

						$.ajax({
							type : 'POST',
							url : basePath + 'userController/deleteUserFunction?userName=' + $('#add_user_function_userName').val()
									+ '&functionCode=' + functionCode + '&purviewCode=' + purviewCode,
							success : function(data) {
								// 解析数据
								var datas = strToJson(data);
								if (datas.returnCode == '1') {
									showInfoMsg(datas.result);
								} else {
									showErrorMsg("删除失败");
								}
							}
						});

						// 关闭遮罩
						$.messager.progress('close');
					}
				}
			});
});