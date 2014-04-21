/**
 * 
 */
$(function() {

	// 构造表格
	$('#role_table').datagrid({
		title : '角色',
		url : basePath + 'roleController/getRoleList',
		method : 'post',
		fit : true,
		rownumbers : true,
		singleSelect : true,
		pagination : false,
		toolbar : '#role_table_param',
		queryParams : {},
		frozenColumns : [ [ {
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
		columns : [ [ {
			title : '创建人',
			field : 'CREATE_BY',
			align : 'left',
			width : 100
		}, {
			title : '创建日期',
			field : 'CREATE_DATE',
			align : 'left',
			width : 100,
			formatter : function(value, row, index) {
				if (value) {
					var unixTimestamp = new Date(value);
					return unixTimestamp.toLocaleDateString();
				}
			}
		}, {
			title : '修改人',
			field : 'UPDATE_BY',
			align : 'left',
			width : 100
		}, {
			title : '修改日期',
			field : 'UPDATE_DATE',
			align : 'left',
			width : 100,
			formatter : function(value, row, index) {
				if (value) {
					var unixTimestamp = new Date(value);
					return unixTimestamp.toLocaleDateString();
				}
			}
		} ] ],
		onClickRow : function(rowIndex, rowData) {
			$('#curr_role_code').val(rowData.ROLE_CODE);// 记录当前选择的角色代码
			$('#role_function_table').treegrid({
				url : basePath + 'roleController/getRoleFunctionList?roleCode=' + rowData.ROLE_CODE
			});
		},
		onLoadError : function() {
			showErrorWindow('数据加载失败!');
		}
	});

	// 构造表格
	$('#role_function_table').treegrid({
		title : '角色关联功能权限',
		method : 'post',
		fit : true,
		rownumbers : true,
		singleSelect : true,
		pagination : false,
		toolbar : '#role_function_table_param',
		idField : 'FUNCTION_CODE',
		treeField : 'FUNCTION_NAME',
		columns : [ [ {
			title : '功能名称',
			field : 'FUNCTION_NAME',
			align : 'left',
			width : 300,
			formatter : function(value, row, index) {
				if (value) {
					// 特殊逻辑转换
					if (row.LEVEL_NO == 'PURVIEW') {
						return value + '(' + row.FUNCTION_CODE_DISPLY + ':' + row.PURVIEW_CODE + ')';
					} else {
						return value + '(' + row.FUNCTION_CODE + ')';
					}

				}
			}
		}, {
			title : '深度级别',
			field : 'LEVEL_NO',
			align : 'left',
			width : 100,
			formatter : function(value, row, index) {
				if (value) {
					return levelDisplay(value);
				}
			}
		} ] ],
		onLoadError : function() {
			showErrorWindow('数据加载失败!');
		}
	});

	// 删除功能
	$('#role_function_table_delete_btn').click(
			function() {
				var row = $('#role_function_table').treegrid('getSelected');
				if (row) {
					var url;
					if (row.LEVEL_NO == 'PURVIEW') {
						url = basePath + 'roleController/deleteRoleFunction?functionCode=' + row.FUNCTION_CODE_DISPLY + '&purviewCode='
								+ row.PURVIEW_CODE + '&roleCode=' + $('#curr_role_code').val();
					} else {
						url = basePath + 'roleController/deleteRoleFunction?functionCode=' + row.FUNCTION_CODE + '&roleCode='
								+ $('#curr_role_code').val();
					}
					$.messager.confirm('警告', '是否确定删除此功能与角色的关联?<br />注意:会一并删除子节点下的关联', function(r) {
						if (r) {
							$.ajax({
								type : 'POST',
								url : url,
								success : function(data) {
									// 解析数据
									var datas = strToJson(data);
									if (datas.returnCode == '1') {
										$('#role_function_table').treegrid('reload');
									} else {
										showErrorMsg('删除失败');
									}
								}
							});
						}
					});
				} else {
					showErrorMsg('请选择要删除的功能');
				}
			});

	// 新增功能
	$('#role_function_table_add_btn').click(function() {
		var row = $('#role_table').datagrid('getSelected');
		if (row) {
			// 弹出新增窗口
			$('#role_function_window_add').window({
				title : '新增功能',
				collapsible : false,
				minimizable : false,
				maximizable : false,
				resizable : false,
				modal : true,
				width : 800,
				height : 400,
				href : basePath + 'roleController/toAddRoleFunction?roleCode=' + row.ROLE_CODE
			});
		} else {
			showErrorMsg('请选择要添加功能的角色');
		}
	});

	// -------------------------------------------------

	// 绑定查询按钮事件
	$('#role_table_search_btn').click(function() {
		$('#role_table').datagrid('load');
	});

	// 新增按钮
	$('#role_table_add_btn').click(function() {
		// 弹出新增窗口
		$('#role_window_add').window({
			title : '新增角色',
			collapsible : false,
			minimizable : false,
			maximizable : false,
			resizable : false,
			modal : true,
			width : 250,
			height : 150,
			href : basePath + 'roleController/toRoleAdd'
		});
	});

	// 修改按钮
	$('#role_table_edit_btn').click(function() {
		var row = $('#role_table').datagrid('getSelected');
		if (row) {
			// 弹出新增窗口
			$('#role_window_edit').window({
				title : '修改角色',
				collapsible : false,
				minimizable : false,
				maximizable : false,
				resizable : false,
				modal : true,
				width : 250,
				height : 150,
				href : basePath + 'roleController/toRoleEdit?roleCode=' + row.ROLE_CODE
			});
		} else {
			// 登录失败,弹出提示
			showErrorMsg('请选择要修改的数据');
		}
	});

	// 删除
	$('#role_table_delete_btn').click(function() {
		var row = $('#role_table').datagrid('getSelected');
		if (row) {
			$.messager.confirm('警告', '删除角色会一并删除用户的关联,以及关联的功能,请确认是否删除?', function(r) {
				if (r) {
					$.ajax({
						type : 'POST',
						url : basePath + 'roleController/deleteRole?roleCode=' + row.ROLE_CODE,
						success : function(data) {
							// 解析数据
							var datas = strToJson(data);
							if (datas.returnCode == '1') {
								$('#role_table_search_btn').click();
							} else {
								showErrorMsg('删除失败');
							}
						}
					});
				}
			});
		} else {
			showErrorMsg('请选择要删除的数据');
		}
	});
});