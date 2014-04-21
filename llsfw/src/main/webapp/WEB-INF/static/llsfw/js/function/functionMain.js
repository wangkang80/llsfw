/**
 * 
 */
$(function() {
	// 构造表格
	$('#function_search_table').treegrid({
		title : '功能维护',
		url : basePath + 'functionController/getAppNode',
		method : 'post',
		fit : true,
		rownumbers : true,
		singleSelect : true,
		pagination : false,
		// pageSize : pageSize,
		// pageList : [ pageSize * 1, pageSize * 2, pageSize * 3, pageSize * 4
		// ],
		toolbar : '#function_search',
		// queryParams : {},
		idField : 'FUNCTION_CODE',
		treeField : 'FUNCTION_NAME',
		frozenColumns : [ [ {
			title : '功能名称',
			field : 'FUNCTION_NAME',
			align : 'left',
			width : 150
		}, {
			title : '功能代码',
			field : 'FUNCTION_CODE',
			align : 'left',
			width : 100
		}, {
			title : '上级功能代码',
			field : 'PARENT_FUNCTION_CODE',
			align : 'left',
			width : 100
		} ] ],
		columns : [ [ {
			title : '功能状态',
			field : 'STATUS',
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
			title : '深度级别',
			field : 'LEVEL_NO',
			align : 'left',
			width : 60,
			formatter : function(value, row, index) {
				if (value) {
					return levelDisplay(value);
				}
			}
		}, {
			title : '功能路径',
			field : 'REQUEST_URL',
			align : 'left',
			width : 250
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
		onClickRow : function(row) {
			$('#function_purview_search_table').datagrid({
				url : basePath + 'functionController/getPurviewList?functionCode=' + row.FUNCTION_CODE
			});
		},
		onLoadError : function() {
			showErrorWindow('数据加载失败!');
		}
	});

	// 注意按钮
	$('#function_warring_btn')
			.click(
					function() {
						showErrorWindow('<font style="color: red;">注意:<br />*.将节点状态更改为[停用],会自动更新子节点的状态变为[停用]<br />*.将节点状态更改为[启用],会自动更新父节点的状态变为[启用]<br />*.删除节点,将会自动删除子节点,并且与之关联的角色数据也会删除<br />*.创建功能会自动创建操作权限[*],[view],并且这两个操作权限是不可删除的<br />*.删除操作权限会连带删除所有关联此操作权限的数据</font>');
					});

	// 绑定查询按钮事件
	$('#function_search_btn').click(function() {
		$('#function_search_table').treegrid('reload');
	});

	// 删除按钮
	$('#function_delete_btn').click(function() {
		var row = $('#function_search_table').treegrid('getSelected');
		if (row) {
			$.messager.confirm('警告', '删除功能节点会一并删除子节点,并且会删除角色与功能的关联,请确认是否删除?', function(r) {
				if (r) {
					$.ajax({
						type : 'POST',
						url : basePath + 'functionController/deleteFunction?functionCode=' + row.FUNCTION_CODE,
						success : function(data) {
							// 解析数据
							var datas = strToJson(data);
							if (datas.returnCode == '1') {
								$('#function_search_btn').click();
								$('#function_purview_search_table').datagrid('reload');
							} else {
								showErrorMsg('删除失败');
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

	// 修改按钮
	$('#function_edit_btn').click(function() {
		var row = $('#function_search_table').treegrid('getSelected');
		if (row) {
			// 弹出修改窗口
			$('#function_edit_window').window({
				title : '修改功能',
				collapsible : false,
				minimizable : false,
				maximizable : false,
				resizable : false,
				modal : true,
				width : 470,
				height : 220,
				href : basePath + 'functionController/toFunctionEdit?functionCode=' + row.FUNCTION_CODE
			});
		} else {
			// 提示
			showErrorMsg('请选择要修改的功能');
		}
	});

	// 新增按钮
	$('#function_add_btn').click(function() {
		var row = $('#function_search_table').treegrid('getSelected');
		var levelNo = row ? row.LEVEL_NO : 0;
		var parentFunctionCode = row ? row.FUNCTION_CODE : '';

		// 如果当前级别已经为3了,则不允许添加了
		if (levelNo == 3) {
			showErrorMsg('最大节点层次数为3层,不能继续添加');
		} else {
			var url = basePath + 'functionController/toFunctionAdd?levelNo=' + levelNo + '&parentFunctionCode=' + parentFunctionCode;
			// 弹出新增窗口
			$('#function_add_window').window({
				title : '新增功能',
				collapsible : false,
				minimizable : false,
				maximizable : false,
				resizable : false,
				modal : true,
				width : 470,
				height : 220,
				href : url
			});
		}
	});

	// 权限表格
	$('#function_purview_search_table').datagrid({
		title : '关联的权限列表',
		method : 'post',
		fit : true,
		rownumbers : true,
		singleSelect : true,
		pagination : false,
		toolbar : '#function_purview_search',
		queryParams : {},
		columns : [ [ {
			title : '权限代码',
			field : 'purviewCode',
			align : 'left',
			width : 100
		}, {
			title : '权限名称',
			field : 'purviewName',
			align : 'left',
			width : 100
		}, {
			title : '创建人',
			field : 'createBy',
			align : 'left',
			width : 100
		}, {
			title : '创建日期',
			field : 'createDate',
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

	// 删除操作权限
	$('#function_purview_delete_btn').click(
			function() {
				var row = $('#function_purview_search_table').datagrid('getSelected');
				if (row) {
					if (row.purviewCode != "*" && row.purviewCode != "view") {
						$.messager.confirm('警告', '是否确定删除此功能的操作权限?', function(r) {
							if (r) {
								$.ajax({
									type : 'POST',
									url : basePath + 'functionController/purviewDelete?functionCode=' + row.functionCode + '&purviewCode='
											+ row.purviewCode,
									success : function(data) {
										// 解析数据
										var datas = strToJson(data);
										if (datas.returnCode == '1') {
											$('#function_purview_search_table').datagrid('load');
										} else {
											showErrorMsg('删除失败');
										}
									}
								});
							}
						});
					} else {
						showErrorMsg('不能删除[*]和[view]权限');
					}
				} else {
					showErrorMsg('请选择要删除的操作权限');
				}
			});

	// 新增操作权限
	$('#function_purview_add_btn').click(function() {
		var row = $('#function_search_table').treegrid('getSelected');
		if (row) {
			if (row.LEVEL_NO == 3) {
				$('#function_purview_add_windwos').window({
					title : '新增操作权限',
					collapsible : false,
					minimizable : false,
					maximizable : false,
					resizable : false,
					modal : true,
					width : 250,
					height : 170,
					href : basePath + 'functionController/toPurviewAdd?functionCode=' + row.FUNCTION_CODE
				});
			} else {
				showErrorMsg('请在功能节点上添加操作权限');
			}
		} else {
			showErrorMsg('请选择要添加操作权限的功能');
		}
	});

	// 修改权限操作
	$('#function_purview_edit_btn').click(function() {
		var row = $('#function_purview_search_table').datagrid('getSelected');
		if (row) {
			$('#function_purview_edit_windwos').window({
				title : '修改操作权限',
				collapsible : false,
				minimizable : false,
				maximizable : false,
				resizable : false,
				modal : true,
				width : 250,
				height : 170,
				href : basePath + 'functionController/toPurviewEdit?functionCode=' + row.functionCode + '&purviewCode=' + row.purviewCode
			});
		} else {
			showErrorMsg('请选择要添加操作权限的功能');
		}
	});
});