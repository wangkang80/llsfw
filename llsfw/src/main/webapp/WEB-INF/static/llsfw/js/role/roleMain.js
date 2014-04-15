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
		// pageSize : pageSize,
		// pageList : [ pageSize * 1, pageSize * 2, pageSize * 3, pageSize * 4
		// ],
		toolbar : '#role_table_param',
		queryParams : {},
		columns : [ [ {
			title : '角色代码',
			field : 'ROLE_CODE',
			align : 'left',
			width : 100
		}, {
			title : '角色名称',
			field : 'ROLE_NAME',
			align : 'left',
			width : 100
		}, {
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
			$('#role_function_table').datagrid({
				url : basePath + 'roleController/getRoleFunctionList?roleCode=' + rowData.ROLE_CODE
			});
			$('#role_function_table').datagrid('load');
		},
		onLoadError : function() {
			showErrorWindow('数据加载失败!');
		}
	});

	$('#role_function_table').datagrid({
		title : '关联的功能列表',
		// url : basePath + 'serviceParamController/getParamsList',
		method : 'post',
		fit : true,
		rownumbers : true,
		singleSelect : true,
		pagination : false,
		// pageSize : pageSize,
		// pageList : [ pageSize * 1, pageSize * 2, pageSize * 3, pageSize * 4
		// ],
		toolbar : '#role_function_table_param',
		queryParams : {},
		columns : [ [ {
			title : '功能代码',
			field : 'FUNCTION_CODE',
			align : 'left',
			width : 100
		}, {
			title : '功能名称',
			field : 'FUNCTION_NAME',
			align : 'left',
			width : 100
		}, {
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
		} ] ],
		onLoadError : function() {
			showErrorWindow('数据加载失败!');
		}
	});

	// 删除功能
	$('#role_function_table_delete_btn').click(
			function() {
				var row = $('#role_function_table').datagrid('getSelected');
				if (row) {
					$.messager.confirm('警告', '是否确定删除此功能与角色的关联?', function(r) {
						if (r) {
							$.ajax({
								type : 'POST',
								url : basePath + 'roleController/deleteRoleFunction?functionCode=' + row.FUNCTION_CODE + '&roleCode='
										+ row.ROLE_CODE,
								success : function(data) {
									// 解析数据
									var datas = strToJson(data);
									if (datas.code == '1') {
										$('#role_function_table').datagrid('load');
									} else {
										alert('删除失败..');
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
				width : 300,
				height : 350,
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
							if (datas.code == '1') {
								$('#role_table_search_btn').click();
							} else {
								alert('删除失败..');
							}
						}
					});
				}
			});
		} else {
			// 登录失败,弹出提示
			showErrorMsg('请选择要删除的数据');
		}
	});
});