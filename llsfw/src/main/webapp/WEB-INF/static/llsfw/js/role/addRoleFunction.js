/**
 * 
 */
$(function() {
	// 构造表格
	$('#function_table').treegrid(
			{
				url : basePath + 'roleController/getFunctionList?roleCode=' + $('#roleCode_function').val(),
				title : '功能列表',
				method : 'post',
				border : false,
				fit : true,
				rownumbers : true,
				singleSelect : true,
				pagination : false,
				idField : 'FUNCTION_CODE',
				treeField : 'FUNCTION_NAME',
				columns : [ [ {
					title : '功能名称',
					field : 'FUNCTION_NAME',
					align : 'left',
					width : 150
				}, {
					title : '功能代码',
					field : 'FUNCTION_CODE',
					align : 'left',
					width : 150
				}, {
					title : '深度级别',
					field : 'LEVEL_NO',
					align : 'left',
					width : 60,
					formatter : function(value, row, index) {
						if (value) {
							return levelDisplay(value);
						}
					}
				} ] ],
				onClickRow : function(row) {
					$('#role_function_purview_table').datagrid(
							{
								url : basePath + 'roleController/getFunctionPurviewList?roleCode=' + $('#roleCode_function').val()
										+ '&functionCode=' + row.FUNCTION_CODE
							});
				},
				onLoadError : function() {
					showErrorWindow('数据加载失败!');
				}
			});

	// 权限清单
	$('#role_function_purview_table').datagrid({
		title : '操作权限',
		method : 'post',
		fit : true,
		rownumbers : true,
		singleSelect : false,
		pagination : false,
		queryParams : {},
		columns : [ [ {
			title : '权限代码',
			field : 'PURVIEW_NAME',
			align : 'left',
			width : 100
		}, {
			title : '权限代码',
			field : 'PURVIEW_CODE',
			align : 'left',
			width : 100
		} ] ],
		onLoadError : function() {
			showErrorWindow('数据加载失败!');
		}
	});

	// 保存按钮
	$('#function_table_save_btn').click(function() {
		var functionRow = $('#function_table').treegrid('getSelected');
		if (functionRow) {
			var purviewRows = $('#role_function_purview_table').datagrid('getSelections');
			var purviewCodes = [];
			for (var i = 0; i < purviewRows.length; i++) {
				purviewCodes.push(purviewRows[i].PURVIEW_CODE);
			}
			var codes = purviewCodes.join(',');
			if (codes) {
				$('#role_functionCode').val(functionRow.FUNCTION_CODE); // 设置表单值
				$('#role_purviewCodes').val(codes); // 设置表单值
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
						return isValid;
					},
					success : function(data) {
						try {
							// 关闭遮罩
							$.messager.progress('close');

							// 解析数据
							var datas = strToJson(data);

							// 判断操作结果
							if (datas.returnCode == '1') {
								$('#role_function_table').treegrid('reload');
								$('#function_table').treegrid('reload');
								$('#role_function_purview_table').datagrid('reload');
							} else {
								showErrorMsg(datas.result);
							}
						} catch (e) {
							showErrorWindow(data);
						}
					}
				});
			} else {
				showErrorMsg('请选择功能对应的操作权限');
			}
		} else {
			showErrorMsg('请选择功能');
		}
	});
});