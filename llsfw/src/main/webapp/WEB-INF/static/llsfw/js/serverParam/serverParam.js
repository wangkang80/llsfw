/**
 * 
 */
$(function() {
	// 构造表格
	$('#serverParamTable').datagrid({
		title : '参数维护',
		url : basePath + 'serviceParamController/getParamsList',
		method : 'post',
		fit : true,
		rownumbers : true,
		singleSelect : true,
		pagination : false,
		toolbar : '#server_param_search',
		queryParams : {},
		columns : [ [ {
			title : '参数代码',
			field : 'PARAMETERS_CODE',
			align : 'left',
			width : 200
		}, {
			title : '参数取值',
			field : 'PARAMETERS_VALUE',
			align : 'left',
			width : 150
		}, {
			title : '参数描述',
			field : 'PARAMETERS_DESC',
			align : 'left',
			width : 150
		}, {
			title : '类别代码',
			field : 'PARAMETERS_TYPE_CODE',
			align : 'left',
			width : 100
		}, {
			title : '类别描述',
			field : 'PARAMETERS_TYPE_NAME',
			align : 'left',
			width : 100
		} ] ],
		onLoadError : function() {
			showErrorWindow('数据加载失败!');
		}
	});

	// 绑定查询按钮事件
	$('#serverParamSearchBtn').click(function() {
		$('#serverParamTable').datagrid('load', {
			parametersCode : $('#parametersCode').val(),
			parametersDesc : $('#parametersDesc').val(),
			parametersTypeCode : $('#parametersTypeCode').combobox('getValue')
		});
	});

	// 绑定查询事件
	$('#parametersCode,#parametersDesc').keydown(function(e) {
		if (e.keyCode == 13) {
			$('#serverParamSearchBtn').click();
		}
	});
	$('#parametersTypeCode').combobox({
		url : basePath + 'serviceParamController/getAllParamType?hasAll=true',
		valueField : 'PARAMETERS_TYPE_CODE',
		textField : 'PARAMETERS_TYPE_NAME',
		editable : false,
		onLoadSuccess : function() { // 加载完成后,设置选中第一项
			var val = $(this).combobox('getData');
			for ( var item in val[0]) {
				if (item == 'PARAMETERS_TYPE_CODE') {
					$(this).combobox('select', val[0][item]);
				}
			}
		},
		onSelect : function(param) {
			$('#serverParamSearchBtn').click();
		}
	});

	// 新增按钮
	$('#serverParamAddBtn').click(function() {
		// 弹出新增窗口
		$('#addServerParamWindows').window({
			title : '新增参数',
			collapsible : false,
			minimizable : false,
			maximizable : false,
			resizable : false,
			modal : true,
			width : 250,
			height : 230,
			href : basePath + 'serviceParamController/toServerParamAdd'
		});
	});

	// 删除
	$('#serverParamDeleteBtn').click(function() {
		var row = $('#serverParamTable').datagrid('getSelected');
		if (row) {
			$.messager.confirm('警告', '参数有可能会被多个地方调用,如删除可能会引起系统出错,请确认是否删除?', function(r) {
				if (r) {
					$.ajax({
						type : 'POST',
						url : basePath + 'serviceParamController/deleteParam?parametersCode=' + row.PARAMETERS_CODE,
						success : function(data) {
							// 解析数据
							var datas = strToJson(data);
							if (datas.code == '1') {
								$('#serverParamSearchBtn').click();
								$('#parametersTypeCode').combobox('reload');
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
	$('#serverParamEditBtn').click(function() {
		var row = $('#serverParamTable').datagrid('getSelected');
		if (row) {
			// 弹出新增窗口
			$('#editServerParamWindows').window({
				title : '修改参数',
				collapsible : false,
				minimizable : false,
				maximizable : false,
				resizable : false,
				modal : true,
				width : 250,
				height : 230,
				href : basePath + 'serviceParamController/toServerParamEdit?PARAMETERS_CODE=' + row.PARAMETERS_CODE
			});
		} else {
			// 登录失败,弹出提示
			showErrorMsg('请选择要修改的数据');
		}
	});
});
