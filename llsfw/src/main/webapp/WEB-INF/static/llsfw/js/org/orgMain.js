/**
 * 
 */
$(function() {
	// 组织表格
	$('#org_search_table').treegrid({
		title : '组织结构',
		url : basePath + 'orgController/getOrgNode',
		method : 'post',
		fit : true,
		rownumbers : false,
		fitColumns : false,
		singleSelect : true,
		pagination : false,
		toolbar : '#org_search',
		idField : 'ORG_CODE',
		treeField : 'ORG_NAME',
		columns : [ [ {
			title : '组织名称',
			field : 'ORG_NAME',
			align : 'left'
		} ] ],
		onClickRow : function(row) {
			$('#job_search_btn').click();
			$('#job_role_search_table').datagrid({
				url : basePath + 'orgController/getRole?jobCode=""'
			});
		},
		onLoadError : function() {
			showErrorWindow('数据加载失败!');
		}
	});

	// 绑定查询按钮事件
	$('#org_search_btn').click(function() {
		$('#org_search_table').treegrid('reload');
	});

	// 新增根组织
	$('#org_add_root_btn').click(function() {
		$('#org_add_window').window({
			title : '新增组织',
			collapsible : false,
			minimizable : false,
			maximizable : false,
			resizable : false,
			modal : true,
			width : 470,
			height : 150,
			href : basePath + 'orgController/toOrgAdd'
		});
	});

	// 新增叶组织
	$('#org_add_item_btn').click(function() {
		var row = $('#org_search_table').treegrid('getSelected');
		if (row) {
			$('#org_add_window').window({
				title : '新增组织',
				collapsible : false,
				minimizable : false,
				maximizable : false,
				resizable : false,
				modal : true,
				width : 470,
				height : 150,
				href : basePath + 'orgController/toOrgAdd?parentOrgCode=' + row.ORG_CODE
			});
		} else {
			showErrorMsg('请选择上级组织');
		}
	});

	// 修改按钮
	$('#org_edit_btn').click(function() {
		var row = $('#org_search_table').treegrid('getSelected');
		if (row) {
			$('#org_edit_window').window({
				title : '修改组织',
				collapsible : false,
				minimizable : false,
				maximizable : false,
				resizable : false,
				modal : true,
				width : 470,
				height : 150,
				href : basePath + 'orgController/toOrgEdit?orgCode=' + row.ORG_CODE
			});
		} else {
			showErrorMsg('请选择要修改的数据');
		}
	});

	// 删除按钮
	$('#org_delete_btn').click(function() {
		var row = $('#org_search_table').treegrid('getSelected');
		if (row) {
			$.messager.confirm('警告', '删除组织节点会一并删除子节点,并且会删除组织,职位,角色的关联,请确认是否删除?', function(r) {
				if (r) {
					$.ajax({
						type : 'POST',
						url : basePath + 'orgController/deleteOrg?orgCode=' + row.ORG_CODE,
						success : function(data) {
							// 解析数据
							var datas = strToJson(data);
							if (datas.returnCode == '1') {
								$('#org_search_btn').click();
								$('#job_search_btn').click();
								$('#job_role_search_table').datagrid('reload');
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

	// -----------------------------------------------------

	// 岗位表格
	$('#job_search_table').datagrid({
		title : '岗位',
		url : basePath + 'orgController/getJob',
		method : 'post',
		fit : true,
		rownumbers : true,
		singleSelect : true,
		pagination : false,
		toolbar : '#job_search',
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
			title : '所属组织',
			field : 'ORG_NAME',
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
			$('#job_role_search_table').datagrid({
				url : basePath + 'orgController/getRole?jobCode=' + rowData.JOB_CODE
			});
		},
		onLoadError : function() {
			showErrorMsg('数据加载失败!');
		}
	});

	// 绑定查询按钮事件
	$('#job_search_btn').click(function() {
		var row = $('#org_search_table').treegrid('getSelected');
		var orgCode = row ? row.ORG_CODE : '';
		var url = '';
		if (orgCode) {
			url = basePath + 'orgController/getJob?orgCode=' + orgCode;

		} else {
			url = basePath + 'orgController/getJob';
		}
		$('#job_search_table').datagrid({
			url : url
		});
	});

	// 绑定新增
	$('#job_add_btn').click(function() {
		var row = $('#org_search_table').treegrid('getSelected');
		var orgCode = row ? row.ORG_CODE : '';
		$('#job_add_window').window({
			title : '新增岗位',
			collapsible : false,
			minimizable : false,
			maximizable : false,
			resizable : false,
			modal : true,
			width : 470,
			height : 150,
			href : basePath + 'orgController/toJobAdd?orgCode=' + orgCode
		});
	});

	// 修改按钮
	$('#job_edit_btn').click(function() {
		var row = $('#job_search_table').datagrid('getSelected');
		if (row) {
			$('#job_edit_window').window({
				title : '修改岗位',
				collapsible : false,
				minimizable : false,
				maximizable : false,
				resizable : false,
				modal : true,
				width : 470,
				height : 150,
				href : basePath + 'orgController/toJobEdit?jobCode=' + row.JOB_CODE
			});
		} else {
			showErrorMsg('请选择要修改的数据');
		}
	});

	// 删除按钮
	$('#job_delete_btn').click(function() {
		var row = $('#job_search_table').datagrid('getSelected');
		if (row) {
			$.messager.confirm('警告', '删除岗位,会删除职位,角色的关联,请确认是否删除?', function(r) {
				if (r) {
					$.ajax({
						type : 'POST',
						url : basePath + 'orgController/deleteJob?jobCode=' + row.JOB_CODE,
						success : function(data) {
							// 解析数据
							var datas = strToJson(data);
							if (datas.returnCode == '1') {
								$('#job_search_btn').click();
								$('#job_role_search_table').datagrid('reload');
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

	// --------------------------

	// 角色表格
	$('#job_role_search_table').datagrid({
		title : '角色',
		method : 'post',
		fit : true,
		rownumbers : true,
		singleSelect : true,
		pagination : false,
		toolbar : '#job_role_search',
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
			title : '岗位代码',
			field : 'JOB_CODE',
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
			showErrorMsg('数据加载失败!');
		}
	});

	// 新增功能
	$('#job_role_add_btn').click(function() {
		var row = $('#job_search_table').datagrid('getSelected');
		if (row) {
			// 弹出新增窗口
			$('#job_role_add_window').window({
				title : '新增角色',
				collapsible : false,
				minimizable : false,
				maximizable : false,
				resizable : false,
				modal : true,
				width : 300,
				height : 350,
				href : basePath + 'orgController/toAddRole?jobCode=' + row.JOB_CODE
			});
		} else {
			showErrorMsg('请选择要添加角色的岗位');
		}
	});

	// 删除按钮
	$('#job_role_delete_btn').click(function() {
		var row = $('#job_role_search_table').datagrid('getSelected');
		if (row) {
			$.messager.confirm('警告', '请确认是否删除?', function(r) {
				if (r) {
					$.ajax({
						type : 'POST',
						url : basePath + 'orgController/deleteRole?jobCode=' + row.JOB_CODE + '&roleCode=' + row.ROLE_CODE,
						success : function(data) {
							// 解析数据
							var datas = strToJson(data);
							if (datas.returnCode == '1') {
								$('#job_role_search_table').datagrid('reload');
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