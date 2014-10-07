/**
 * 
 */
$(function() {

	// 当前选择的用户
	var currUserName = '';
	// 当前选择的岗位
	var currJobCodeList = '';
	// 当前用户选择的角色
	var currRoleCodeList = '';

	// 分页条数
	var userTablePageSize = getServerParam(basePath, 'PAGE_SIZE');

	// 构造表格
	$('#user_table').datagrid({
		title : '用户',
		url : basePath + 'userController/getUserList',
		method : 'post',
		fit : true,
		rownumbers : true,
		singleSelect : true,
		pagination : true,
		pageSize : userTablePageSize / 2,
		pageList : [ userTablePageSize / 2, userTablePageSize ],
		toolbar : '#user_table_param',
		queryParams : {},
		frozenColumns : [ [ {
			title : '登录名',
			field : 'LOGIN_NAME',
			align : 'left',
			width : 80
		}, {
			title : '用户名',
			field : 'USER_NAME',
			align : 'left',
			width : 80
		} ] ],
		columns : [ [ {
			title : '状态',
			field : 'USER_STATUS',
			align : 'left',
			width : 45,
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
			width : 80
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
			width : 80
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
			currUserName = rowData.LOGIN_NAME;
			currJobCodeList = '';
			currRoleCodeList = '';
			loadUserJobTable(currUserName);
			loadRoleTable(currUserName, currJobCodeList);
			loadOrgTable(currUserName, currJobCodeList);
			loadFunctionTable(currUserName, currJobCodeList, currRoleCodeList);
		},
		onLoadSuccess : function(data) {
			currUserName = '';
			currJobCodeList = '';
			currRoleCodeList = '';
			loadUserJobTable(currUserName);
			loadRoleTable(currUserName, currJobCodeList);
			loadOrgTable(currUserName, currJobCodeList);
			loadFunctionTable(currUserName, currJobCodeList, currRoleCodeList);
		},
		onLoadError : function() {
			showErrorWindow('数据加载失败!');
		}
	});

	// 加载岗位列表
	function loadUserJobTable() {
		$('#user_job_table').datagrid({
			url : basePath + 'userController/getUserJobList?loginName=' + currUserName
		});
	}

	// 加载角色列表
	function loadRoleTable() {
		$('#user_job_role_table').datagrid({
			url : basePath + 'userController/getUserJobRoleList?loginName=' + currUserName + '&jobName=' + currJobCodeList
		});

	}

	// 加载功能树
	function loadFunctionTable() {
		$('#user_job_org_function_all_table').tree(
				{
					url : basePath + 'userController/getUserJobRoleFunctionTree?loginName=' + currUserName + '&jobName=' + currJobCodeList
							+ '&roleName=' + currRoleCodeList
				});
		$('#user_job_org_function_job_table').tree(
				{
					url : basePath + 'userController/getUserJobRoleFunctionTree?loadFunctionType=1&loginName=' + currUserName + '&jobName='
							+ currJobCodeList + '&roleName=' + currRoleCodeList
				});
		$('#user_job_org_function_user_table').tree({
			url : basePath + 'userController/getUserJobRoleFunctionTree?loadFunctionType=2&loginName=' + currUserName
		});

	}

	// 加载组织树
	function loadOrgTable() {
		$('#user_job_org_all_table').tree({
			url : basePath + 'userController/getUserJobOrgTree?loginName=' + currUserName + '&jobName=' + currJobCodeList
		});
		$('#user_job_org_higher_table').tree({
			url : basePath + 'userController/getUserJobOrgTree?loadOrgType=1&loginName=' + currUserName + '&jobName=' + currJobCodeList
		});
		$('#user_job_org_lower_table').tree({
			url : basePath + 'userController/getUserJobOrgTree?loadOrgType=2&loginName=' + currUserName + '&jobName=' + currJobCodeList
		});
	}

	// 绑定查询按钮事件
	$('#user_table_search_btn').click(function() {
		$('#user_table').datagrid('reload');
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
								showInfoMsg(datas.result);
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
								showInfoMsg(datas.result);
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

	// -------------------------------------------

	// 岗位表格
	$('#user_job_table').datagrid({
		title : '岗位列表',
		method : 'post',
		fit : true,
		rownumbers : false,
		singleSelect : false,
		pagination : false,
		queryParams : {},
		columns : [ [ {
			title : '岗位代码',
			field : 'JOB_CODE',
			align : 'left',
			width : 80
		}, {
			title : '岗位名称',
			field : 'JOB_NAME',
			align : 'left',
			width : 80
		}, {
			title : '组织机构',
			field : 'ORG_NAME',
			align : 'left',
			width : 80
		}, {
			title : '创建人',
			field : 'CREATE_BY',
			align : 'left',
			width : 80
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
		onClickRow : function(rowIndex, rowData) {
			var selections = $('#user_job_table').datagrid("getSelections");
			var jobCodes = '';
			if (selections.length > 0) {
				for (var i = 0; i < selections.length; i++) {
					jobCodes += "'" + selections[i].JOB_CODE + "',";
				}
				jobCodes = jobCodes.substring(0, jobCodes.length - 1);
			} else {
				jobCodes = '';
			}
			currJobCodeList = jobCodes;
			currRoleCodeList = '';
			loadRoleTable(currUserName, currJobCodeList);
			loadOrgTable(currUserName, currJobCodeList);
			loadFunctionTable(currUserName, currJobCodeList, currRoleCodeList);
		},
		onLoadError : function() {
			showErrorWindow('数据加载失败!');
		}
	});

	// 角色表格
	$('#user_job_role_table').datagrid({
		title : '角色列表',
		method : 'post',
		fit : true,
		rownumbers : false,
		singleSelect : false,
		pagination : false,
		queryParams : {},
		columns : [ [ {
			title : '角色代码',
			field : 'ROLE_CODE',
			align : 'left',
			width : 80
		}, {
			title : '角色名称',
			field : 'ROLE_NAME',
			align : 'left',
			width : 80
		}, {
			title : '所属岗位',
			field : 'JOB_NAME',
			align : 'left',
			width : 80
		}, {
			title : '创建人',
			field : 'CREATE_BY',
			align : 'left',
			width : 80
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
		onClickRow : function(rowIndex, rowData) {
			var selections = $('#user_job_role_table').datagrid("getSelections");
			var roleCodes = '';
			if (selections.length > 0) {
				for (var i = 0; i < selections.length; i++) {
					roleCodes += "'" + selections[i].ROLE_CODE + "',";
				}
				roleCodes = roleCodes.substring(0, roleCodes.length - 1);
			} else {
				roleCodes = '';
			}
			currRoleCodeList = roleCodes;
			loadFunctionTable(currUserName, currJobCodeList, currRoleCodeList);
		},
		onLoadError : function() {
			showErrorWindow('数据加载失败!');
		}
	});

	// 组织机构树
	$('#user_job_org_all_table').tree({
		checkbox : false,
		parentField : "PARENT_ORG_CODE",
		textFiled : "ORG_NAME",
		idFiled : "ORG_CODE",
		formatter : function(node) {
			var text = node.text;
			if (node.MAIN_ORG == "1") {
				text = "<strong>" + text + "</strong>";
			}
			return text;
		}
	});
	$('#user_job_org_higher_table').tree({
		checkbox : false,
		parentField : "PARENT_ORG_CODE",
		textFiled : "ORG_NAME",
		idFiled : "ORG_CODE",
		formatter : function(node) {
			var text = node.text;
			if (node.MAIN_ORG == "1") {
				text = "<strong>" + text + "</strong>";
			}
			return text;
		}
	});
	$('#user_job_org_lower_table').tree({
		checkbox : false,
		parentField : "PARENT_ORG_CODE",
		textFiled : "ORG_NAME",
		idFiled : "ORG_CODE",
		formatter : function(node) {
			var text = node.text;
			if (node.MAIN_ORG == "1") {
				text = "<strong>" + text + "</strong>";
			}
			return text;
		}
	});

	$('#user_job_org_function_all_table').tree({
		checkbox : false,
		parentField : "PARENT_FUNCTION_CODE",
		textFiled : "FUNCTION_NAME",
		idFiled : "FUNCTION_CODE"
	});
	$('#user_job_org_function_job_table').tree({
		checkbox : false,
		parentField : "PARENT_FUNCTION_CODE",
		textFiled : "FUNCTION_NAME",
		idFiled : "FUNCTION_CODE"
	});
	$('#user_job_org_function_user_table').tree({
		checkbox : false,
		parentField : "PARENT_FUNCTION_CODE",
		textFiled : "FUNCTION_NAME",
		idFiled : "FUNCTION_CODE"
	});

	// ----岗位授权
	$('#user_table_job_permissions_btn').click(function() {
		var row = $('#user_table').datagrid('getSelected');
		if (row) {
			$('#user_job_window_add').window({
				title : '岗位授权',
				collapsible : false,
				minimizable : false,
				maximizable : false,
				resizable : false,
				modal : true,
				width : 900,
				height : 500,
				href : basePath + 'userController/toAddUserJob?userName=' + row.LOGIN_NAME,
				tools : [ {
					iconCls : 'icon-reload',
					handler : function() {
						$('#user_job_window_add').panel('refresh');
					}
				} ]
			});
		} else {
			showErrorMsg('请选择要授权的用户');
		}
	});

	// ----直接授权
	$('#user_table_user_permissions_defpswd_btn').click(function() {
		var row = $('#user_table').datagrid('getSelected');
		if (row) {
			$('#user_user_function_window_add').window({
				title : '直接授权',
				collapsible : false,
				minimizable : false,
				maximizable : false,
				resizable : false,
				modal : true,
				width : 300,
				height : 500,
				href : basePath + 'userController/toAddUserFunction?userName=' + row.LOGIN_NAME,
				tools : [ {
					iconCls : 'icon-reload',
					handler : function() {
						$('#user_user_function_window_add').panel('refresh');
					}
				} ]
			});
		} else {
			showErrorMsg('请选择要授权的用户');
		}
	});
});