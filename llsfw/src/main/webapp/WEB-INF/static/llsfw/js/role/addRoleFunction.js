/**
 * 
 */
$(function() {
	// 构造表格
	$('#function_table').datagrid({
		url : basePath + 'roleController/getFunctionList?roleCode=' + $('#roleCode_function').val(),
		method : 'post',
		fit : true,
		rownumbers : true,
		singleSelect : false,
		pagination : false,
		toolbar : '#function_table_param',
		queryParams : {},
		columns : [ [ {
			checkbox : true,
			field : 'CK'
		}, {
			title : '功能代码',
			field : 'FUNCTION_CODE',
			align : 'left',
			width : 100
		}, {
			title : '功能名称',
			field : 'FUNCTION_NAME',
			align : 'left',
			width : 100
		} ] ],
		onLoadError : function() {
			showErrorWindow('数据加载失败!');
		}
	});

	// 保存按钮
	$('#function_table_save_btn').click(function() {
		// 获得选中的数据
		var rows = $('#function_table').datagrid('getSelections');
		var functionCodes = [];
		for (var i = 0; i < rows.length; i++) {
			functionCodes.push(rows[i].FUNCTION_CODE);
		}
		var codes = functionCodes.join(',');

		// 如果有选中数据,提交,没有则提示
		if (codes) {
			$('#functionCodeList').val(codes); // 设置表单值
			$('#role_function_form_add').attr('action', basePath + 'roleController/addRoleFunction');
			$('#role_function_form_add').form('submit', {
				onSubmit : function() {// 提交前置事件
					var isValid = $(this).form('validate');
					if (isValid) {// 验证通过,弹出遮罩
						$.messager.progress({
							text : '保存中...',
							interval : 200
						});
					}
					return isValid; // return false will stop the form
					// submission
				},
				success : function(data) {
					try {
						// 关闭遮罩
						$.messager.progress('close');

						// 解析数据
						var datas = strToJson(data);

						if (datas.code == '1') {
							$('#role_function_window_add').window('close');
							$('#role_function_table').datagrid('load');
						} else if (datas.code == '-1') {
							// 弹出提示
							showErrorMsg(datas.message);
						}
					} catch (e) {
						showErrorWindow(data);
					}
				}
			});
		} else {
			showErrorMsg('请选择要添加的功能');
		}
	});
});