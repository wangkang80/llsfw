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
		columns : [ [ {
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
		}, {
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
		onLoadError : function() {
			showErrorWindow('数据加载失败!');
		}
	});

	// 绑定查询按钮事件
	$('#function_search_btn').click(function() {
		$('#function_search_table').treegrid('reload');
	});

	// 删除按钮
	$('#function_delete_btn').click(function() {
		var row = $('#function_search_table').datagrid('getSelected');
		if (row) {
			$.messager.confirm('警告', '删除功能节点会一并删除子节点,并且会删除角色与功能的关联,请确认是否删除?', function(r) {
				if (r) {
					$.ajax({
						type : 'POST',
						url : basePath + 'functionController/deleteFunction?functionCode=' + row.FUNCTION_CODE,
						success : function(data) {
							// 解析数据
							var datas = strToJson(data);
							if (datas.code == '1') {
								$('#function_search_btn').click();
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

	// 修改按钮
	$('#function_edit_btn').click(function() {
		var row = $('#function_search_table').datagrid('getSelected');
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
		var row = $('#function_search_table').datagrid('getSelected');
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
});

/**
 * 格式化功能级别
 */
function levelDisplay(value) {
	if (value) {
		if (value == 1) {
			return '系统(' + value + ')';
		} else if (value == 2) {
			return '菜单(' + value + ')';
		} else if (value == 3) {
			return '功能(' + value + ')';
		} else {
			return '未知(' + value + ')';
		}
	}
}