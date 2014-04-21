/**
 * 
 */
$(function() {
	// 构造表格
	$('#job_role_table').datagrid({
		url : basePath + 'orgController/getRoleList?jobCode=' + $('#job_role').val(),
		method : 'post',
		fit : true,
		rownumbers : true,
		singleSelect : false,
		pagination : false,
		toolbar : '#job_role_table_param',
		queryParams : {},
		columns : [ [ {
			checkbox : true,
			field : 'CK'
		}, {
			title : '角色代码',
			field : 'ROLE_CODE',
			align : 'left',
			width : 100
		}, {
			title : '角色名称',
			field : 'ROLE_NAME',
			align : 'left',
			width : 100
		} ] ],
		onLoadError : function() {
			showErrorWindow('数据加载失败!');
		}
	});

	// 保存按钮
	$('#job_role_table_save_btn').click(function() {
		// 获得选中的数据
		var rows = $('#job_role_table').datagrid('getSelections');
		var roleCodes = [];
		for (var i = 0; i < rows.length; i++) {
			roleCodes.push(rows[i].ROLE_CODE);
		}
		var codes = roleCodes.join(',');

		// 如果有选中数据,提交,没有则提示
		if (codes) {
			$('#job_roleCodeList').val(codes); // 设置表单值
			$('#job_role_form_add').attr('action', basePath + 'orgController/addRole');
			$('#job_role_form_add').form('submit', {
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
						if (datas.returnCode == '1') {
							$('#job_role_add_window').window('close');
							$('#job_role_search_table').datagrid('reload');
						} else {
							showErrorMsg('删除失败');
						}
					} catch (e) {
						showErrorWindow(data);
					}
				}
			});
		} else {
			showErrorMsg('请选择要添加的角色');
		}
	});
});