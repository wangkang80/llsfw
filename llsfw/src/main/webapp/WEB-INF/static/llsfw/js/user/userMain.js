/**
 * 
 */
$(function() {
	// 构造表格
	$('#user_table').datagrid({
		title : '用户',
		url : basePath + 'userController/getUserList',
		method : 'post',
		fit : true,
		rownumbers : true,
		singleSelect : true,
		pagination : false,
		toolbar : '#user_table_param',
		queryParams : {},
		frozenColumns : [ [ {
			title : '登录名',
			field : 'LOGIN_NAME',
			align : 'left',
			width : 100
		}, {
			title : '用户名',
			field : 'USER_NAME',
			align : 'left',
			width : 100
		} ] ],
		columns : [ [ {
			title : '状态',
			field : 'USER_STATUS',
			align : 'left',
			width : 60,
			formatter : function(value, row, index) {
				if (value) {
					if (value == 1) {
						return '启用';
					} else if (value == 0) {
						return '停用';
					} else {
						return '未知';
					}
				}
			}
		}, {
			title : '座机',
			field : 'USER_PHONE',
			align : 'left',
			width : 100
		}, {
			title : '手机',
			field : 'USER_M_PHOME',
			align : 'left',
			width : 100
		}, {
			title : '电子邮件',
			field : 'USER_EMAIL',
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
			$('#user_job_table').datagrid({
				url : basePath + 'userController/getUserJobList?loginName=' + rowData.LOGIN_NAME
			});
		},
		onLoadError : function() {
			showErrorWindow('数据加载失败!');
		}
	});

	// 开启过滤
	$('#user_table').datagrid("enableFilter", [ {
		field : 'USER_STATUS',
		type : 'combobox',
		options : {
			method : 'get',
			url : basePath + 'static/llsfw/json/enableOrDisableHasAll.json',
			valueField : 'value',
			textField : 'text',
			editable : false,
			onLoadSuccess : function() { // 加载完成后,设置选中第一项
				var val = $(this).combobox("getData");
				for ( var item in val[0]) {
					if (item == "value") {
						$(this).combobox("select", val[0][item]);
					}
				}
			},
			onChange : function(value) {
				if (value == '') {
					$('#user_table').datagrid('removeFilterRule', 'USER_STATUS');
				} else {
					$('#user_table').datagrid('addFilterRule', {
						field : 'USER_STATUS',
						op : 'equal',
						value : value
					});
				}
				$('#user_table').datagrid('doFilter');
			}
		}
	}, {
		field : 'CREATE_DATE',
		type : 'datebox',
		options : {
			onChange : function(value) {
				if (value == '') {
					$('#user_table').datagrid('removeFilterRule', 'CREATE_DATE');
				} else {
					if (/^(\d{4})\-(\d{2})\-(\d{2})$/.test(value)) {
						$('#user_table').datagrid('addFilterRule', {
							field : 'CREATE_DATE',
							op : 'greaterorequal',
							value : value
						});
					}
				}
				$('#user_table').datagrid('doFilter');
			}
		}
	}, {
		field : 'UPDATE_DATE',
		type : 'datebox',
		options : {
			onChange : function(value) {
				if (value == '') {
					$('#user_table').datagrid('removeFilterRule', 'UPDATE_DATE');
				} else {
					if (/^(\d{4})\-(\d{2})\-(\d{2})$/.test(value)) {
						$('#user_table').datagrid('addFilterRule', {
							field : 'UPDATE_DATE',
							op : 'greaterorequal',
							value : value
						});
					}
				}
				$('#user_table').datagrid('doFilter');
			}
		}
	} ]);

	// 绑定查询按钮事件
	$('#user_table_search_btn').click(function() {
		$('#user_table').datagrid('load');
	});

	// 新增按钮
	$('#user_table_add_btn').click(function() {
		// 弹出新增窗口
		$('#user_window_add').window({
			title : '新增用户',
			collapsible : false,
			minimizable : false,
			maximizable : false,
			resizable : false,
			modal : true,
			width : 470,
			height : 200,
			href : basePath + 'userController/toUserAdd'
		});
	});

	// 修改按钮
	$('#user_table_edit_btn').click(function() {
		var row = $('#user_table').datagrid('getSelected');
		if (row) {
			// 弹出新增窗口
			$('#user_window_edit').window({
				title : '修改用户',
				collapsible : false,
				minimizable : false,
				maximizable : false,
				resizable : false,
				modal : true,
				width : 470,
				height : 200,
				href : basePath + 'userController/toUserEdit?loginName=' + row.LOGIN_NAME
			});
		} else {
			// 登录失败,弹出提示
			showErrorMsg('请选择要修改的数据');
		}
	});

	// 删除
	$('#user_table_delete_btn').click(function() {
		var row = $('#user_table').datagrid('getSelected');
		if (row) {
			$.messager.confirm('警告', '将会删除用户以及用户与岗位的关联,是否要操作?', function(r) {
				if (r) {
					$.ajax({
						type : 'POST',
						url : basePath + 'userController/userDelete?loginName=' + row.LOGIN_NAME,
						success : function(data) {
							// 解析数据
							var datas = strToJson(data);
							if (datas.returnCode == '1') {
								$('#user_table_search_btn').click();
								$('#user_job_table').datagrid('reload');
							} else {
								showErrorMsg("删除失败");
							}
						}
					});
				}
			});
		} else {
			showErrorMsg('请选择要删除的用户');
		}
	});

	// 密码初始化
	$('#user_table_set_defpswd_btn').click(function() {
		var row = $('#user_table').datagrid('getSelected');
		if (row) {
			$.messager.confirm('警告', '将会将用户密码重新设置为默认值,是否要操作?', function(r) {
				if (r) {
					$.ajax({
						type : 'POST',
						url : basePath + 'userController/saveDefPswd?loginName=' + row.LOGIN_NAME,
						success : function(data) {
							// 解析数据
							var datas = strToJson(data);
							if (datas.returnCode == '1') {
								$('#user_table_search_btn').click();
							} else {
								showErrorMsg("初始化密码失败");
							}
						}
					});
				}
			});
		} else {
			showErrorMsg('请选择要初始化密码的用户');
		}
	});

	// ---------------------------------------------------------------

	$('#user_job_table').datagrid({
		title : '关联的岗位列表',
		method : 'post',
		fit : true,
		rownumbers : true,
		singleSelect : true,
		pagination : false,
		toolbar : '#user_job_table_param',
		queryParams : {},
		columns : [ [ {
			title : '岗位代码',
			field : 'JOB_CODE',
			align : 'left',
			width : 100
		}, {
			title : '岗位名称',
			field : 'JOB_NAME',
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

	// 删除岗位
	$('#user_job_table_delete_btn').click(function() {
		var row = $('#user_job_table').datagrid('getSelected');
		if (row) {
			$.messager.confirm('警告', '是否确定删除此用户的岗位关联?', function(r) {
				if (r) {
					$.ajax({
						type : 'POST',
						url : basePath + 'userController/deleteUserJob?loginName=' + row.LOGIN_NAME + '&jobCode=' + row.JOB_CODE,
						success : function(data) {
							// 解析数据
							var datas = strToJson(data);
							if (datas.returnCode == '1') {
								$('#user_job_table').datagrid('load');
							} else {
								showErrorMsg("删除失败");
							}
						}
					});
				}
			});
		} else {
			showErrorMsg('请选择要删除的岗位');
		}
	});

	// 新增岗位
	$('#user_job_table_add_btn').click(function() {
		var row = $('#user_table').datagrid('getSelected');
		if (row) {
			// 弹出新增窗口
			$('#user_job_window_add').window({
				title : '新增岗位',
				collapsible : false,
				minimizable : false,
				maximizable : false,
				resizable : false,
				modal : true,
				width : 300,
				height : 350,
				href : basePath + 'userController/toAddUserJob?userName=' + row.LOGIN_NAME
			});
		} else {
			showErrorMsg('请选择要添加岗位的用户');
		}
	});

	// -------------------------------------------------
});