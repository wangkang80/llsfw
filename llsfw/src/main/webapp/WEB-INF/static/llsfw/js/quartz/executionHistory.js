/**
 * 
 */
$(function() {
	// 分页条数
	var pageSize = getServerParam(basePath, 'PAGE_SIZE').parametersValue;

	// 查询按钮
	$('#execution_history_search_btn').click(function() {
		$('#execution_history_table').datagrid('load', {
			execution_history_trigger_group : $('#execution_history_trigger_group').val(),
			execution_history_trigger_name : $('#execution_history_trigger_name').val(),
			execution_history_job_group : $('#execution_history_job_group').val(),
			execution_history_job_name : $('#execution_history_job_name').val(),
			execution_status : $('#execution_status').combobox('getValue')
		});
	});
	// 状态
	$('#execution_status').combobox({
		method : 'get',
		url : basePath + 'static/llsfw/json/executionHistoryStatus.json',
		valueField : 'value',
		textField : 'text',
		editable : false,
		onLoadSuccess : function() { // 加载完成后,设置选中第一项
			var val = $(this).combobox('getData');
			for ( var item in val[0]) {
				if (item == 'PARAMETERS_TYPE_CODE') {
					$(this).combobox('select', val[0][item]);
				}
			}
		}
	});

	// 构造表格
	$('#execution_history_table').datagrid(
			{
				url : basePath + 'quartzController/loadExecutionHistoryList',
				toolbar : '#execution_history_search',
				method : 'post',
				rownumbers : true,
				fit : true,
				singleSelect : true,
				pagination : true,
				queryParams : {
					execution_history_trigger_group : $('#execution_history_trigger_group').val(),
					execution_history_trigger_name : $('#execution_history_trigger_name').val(),
					execution_history_job_group : $('#execution_history_job_group').val(),
					execution_history_job_name : $('#execution_history_job_name').val(),
					execution_status : $('#execution_status').combobox('getValue')
				},
				pageSize : pageSize / 2,
				pageList : [ pageSize / 2, pageSize ],
				columns : [ [ {
					title : '计划开始时间',
					field : 'SCHEDULED_FIRE_TIME',
					align : 'left',
					width : 150,
					formatter : function(value, row, index) {
						if (value) {
							var unixTimestamp = new Date(value);
							return unixTimestamp.toLocaleString();
						}
					}
				}, {
					title : '实际开始时间',
					field : 'FIRE_TIME',
					align : 'left',
					width : 150,
					formatter : function(value, row, index) {
						if (value) {
							var unixTimestamp = new Date(value);
							return unixTimestamp.toLocaleString();
						}
					}
				}, {
					title : '实际结束时间',
					field : 'END_TIME',
					align : 'left',
					width : 150,
					formatter : function(value, row, index) {
						if (value) {
							var unixTimestamp = new Date(value);
							return unixTimestamp.toLocaleString();
						}
					}
				}, {
					title : '耗时(毫秒)',
					field : 'JOB_RUN_TIME',
					align : 'left',
					width : 70
				}, {
					title : '状态',
					field : 'STATUS',
					align : 'left',
					width : 65
				}, {
					title : '结果',
					field : 'RESULT',
					align : 'left',
					width : 60
				}, {
					title : '错误',
					field : 'ERROR_MSG',
					align : 'center',
					width : 40,
					formatter : function(value, row, index) {
						if (value) {
							return "<input type='button' class='icon-error' style='border:0px' title='双击查看详细信息' />";
						} else {
							return "";
						}
					}
				}, {
					title : '触发器名称',
					field : 'TRIGGER_NAME',
					align : 'left',
					width : 100
				}, {
					title : '触发器组别',
					field : 'TRIGGER_GROUP',
					align : 'left',
					width : 100
				}, {
					title : '作业名称',
					field : 'JOB_NAME',
					align : 'left',
					width : 100
				}, {
					title : '作业组别',
					field : 'JOB_GROUP',
					align : 'left',
					width : 100
				}, {
					title : '作业类',
					field : 'JOB_CLASS',
					align : 'left',
					width : 100
				}, {
					title : '线程组名称',
					field : 'THREAD_GROUP_NAME',
					align : 'left',
					width : 100
				}, {
					title : '线程ID',
					field : 'THREAD_ID',
					align : 'left',
					width : 100
				}, {
					title : '线程优先级',
					field : 'THREAD_PRIORITY',
					align : 'left',
					width : 100
				}, {
					title : '计划任务ID',
					field : 'SCHEDULED_ID',
					align : 'left',
					width : 100
				}, {
					title : '计划任务名称',
					field : 'SCHEDULED_NAME',
					align : 'left',
					width : 100
				}, {
					title : '创建时间',
					field : 'CREATE_DATE',
					align : 'left',
					width : 150,
					formatter : function(value, row, index) {
						if (value) {
							var unixTimestamp = new Date(value);
							return unixTimestamp.toLocaleString();
						}
					}
				} ] ],
				onHeaderContextMenu : function(e, field) {
					e.preventDefault();
					if (!cmenu) {
						createColumnMenu();
					}
					cmenu.menu('show', {
						left : e.pageX,
						top : e.pageY
					});
				},
				rowStyler : function(rowIndex, rowData) {
					if (rowData.STATUS == 'error') {
						return 'background-color:red;color:#fff;font-weight:bold;';
					} else if (rowData.STATUS == 'executionVetoed') {
						return 'background-color:blue;color:#fff;font-weight:bold;';
					} else if (rowData.STATUS == 'misfired') {
						return 'background-color:yellow;color:#000;font-weight:bold;';
					} else if (rowData.STATUS == 'triggering' || rowData.STATUS == 'vetoed(false)' || rowData.STATUS == 'vetoed(true)'
							|| rowData.STATUS == 'toBeExecuted' || rowData.STATUS == 'executed') {
						return 'background-color:green;color:#fff;font-weight:bold;';
					}
				},
				onDblClickCell : function(rowIndex, field, value) {
					$('#execution_history_errorMsg_dialog').dialog({
						title : '详细信息',
						width : 500,
						height : 500,
						cache : false,
						maximizable : true,
						content : value
					});
				},
				onLoadError : function() {
					showErrorWindow('数据加载失败!');
				}
			});

	var cmenu;
	function createColumnMenu() {
		cmenu = $('<div/>').appendTo('body');
		cmenu.menu({
			onClick : function(item) {
				if (item.iconCls == 'icon-ok') {
					$('#execution_history_table').datagrid('hideColumn', item.name);
					cmenu.menu('setIcon', {
						target : item.target,
						iconCls : 'icon-empty'
					});
				} else {
					$('#execution_history_table').datagrid('showColumn', item.name);
					cmenu.menu('setIcon', {
						target : item.target,
						iconCls : 'icon-ok'
					});
				}
			}
		});
		var fields = $('#execution_history_table').datagrid('getColumnFields');
		for (var i = 0; i < fields.length; i++) {
			var field = fields[i];
			var col = $('#execution_history_table').datagrid('getColumnOption', field);
			cmenu.menu('appendItem', {
				text : col.title,
				name : field,
				iconCls : 'icon-ok'
			});
		}
	}
});