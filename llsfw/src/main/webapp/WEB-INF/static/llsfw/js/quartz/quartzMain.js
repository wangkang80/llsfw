/**
 * 
 */
$(function() {
	// 构造表格
	$('#firedTriggersTable').datagrid({
		title : '正在执行的触发器列表',
		url : basePath + 'quartzController/getFiredTriggers',
		method : 'post',
		rownumbers : true,
		fit : true,
		singleSelect : true,
		toolbar : '#firedTriggers_search',
		frozenColumns : [ [ {
			title : '触发器名称',
			field : 'TRIGGER_NAME',
			align : 'left',
			width : 100
		}, {
			title : '触发器组别',
			field : 'TRIGGER_GROUP',
			align : 'left',
			width : 100
		} ] ],
		columns : [ [ {
			title : '触发时间',
			field : 'FIRED_TIME',
			align : 'left',
			width : 150,
			formatter : function(value, row, index) {
				if (value) {
					var unixTimestamp = new Date(value);
					return unixTimestamp.toLocaleString();
				}
			}
		}, {
			title : '执行时间',
			field : 'SCHED_TIME',
			align : 'left',
			width : 150,
			formatter : function(value, row, index) {
				if (value) {
					var unixTimestamp = new Date(value);
					return unixTimestamp.toLocaleString();
				}
			}
		}, {
			title : '优先级',
			field : 'PRIORITY',
			align : 'left',
			width : 50
		}, {
			title : '状态',
			field : 'STATE',
			align : 'left',
			width : 80
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
			title : '禁止并发',
			field : 'IS_NONCONCURRENT',
			align : 'left',
			width : 60
		}, {
			title : '请求恢复',
			field : 'REQUESTS_RECOVERY',
			align : 'left',
			width : 60
		}, {
			title : '计划任务名称',
			field : 'SCHED_NAME',
			align : 'left',
			width : 140
		}, {
			title : '条目编号',
			field : 'ENTRY_ID',
			align : 'left',
			width : 260
		}, {
			title : '实例名称',
			field : 'INSTANCE_NAME',
			align : 'left',
			width : 200
		} ] ],
		onDblClickRow : function(rowIndex, rowData) {
			var triggerName = rowData.TRIGGER_NAME;
			var triggerGroup = rowData.TRIGGER_GROUP;
			var jobName = rowData.JOB_NAME;
			var jonGroup = rowData.JOB_GROUP;
			openJobTriggerHisWindow(triggerName, triggerGroup, jobName, jonGroup);
		},
		onClickRow : function(rowIndex, rowData) {
			var sName = rowData.SCHED_NAME;
			var tName = rowData.TRIGGER_NAME;
			var tGroup = rowData.TRIGGER_GROUP;

			var param = '?sName=' + sName;
			param += '&tName=' + tName;
			param += '&tGroup=' + tGroup;

			$('#schedulerDetailPanel').panel('refresh', basePath + 'quartzController/getSchedulerDetail' + param);
		},
		onLoadError : function() {
			showErrorWindow('数据加载失败!');
		}
	});

	// 查询按钮
	$('#firedTriggers_search_btn').click(function() {
		$('#firedTriggersTable').datagrid('load', {});
	});

	// 构造表格
	$('#triggersTable').datagrid({
		title : '触发器列表',
		url : basePath + 'quartzController/getTriggers',
		method : 'post',
		rownumbers : true,
		fit : true,
		singleSelect : true,
		toolbar : '#triggers_search',
		frozenColumns : [ [ {
			title : '作业名称',
			field : 'JOB_NAME',
			align : 'left',
			sortable : 'true',
			width : 100
		}, {
			title : '作业组别',
			field : 'JOB_GROUP',
			align : 'left',
			sortable : 'true',
			width : 100
		}, {
			title : '触发器名称',
			field : 'TRIGGER_NAME',
			align : 'left',
			sortable : 'true',
			width : 100
		}, {
			title : '触发器组别',
			field : 'TRIGGER_GROUP',
			align : 'left',
			sortable : 'true',
			width : 100
		} ] ],
		columns : [ [ {
			title : '描述',
			field : 'DESCRIPTION',
			align : 'left',
			width : 100
		}, {
			title : '下次触发时间',
			field : 'NEXT_FIRE_TIME',
			align : 'left',
			sortable : 'true',
			width : 150,
			formatter : function(value, row, index) {
				if (value) {
					var unixTimestamp = new Date(value);
					return unixTimestamp.toLocaleString();
				}
			}
		}, {
			title : '上次触发时间',
			field : 'PREV_FIRE_TIME',
			align : 'left',
			sortable : 'true',
			width : 150,
			formatter : function(value, row, index) {
				if (value) {
					var unixTimestamp = new Date(value);
					return unixTimestamp.toLocaleString();
				}
			}
		}, {
			title : '优先级',
			field : 'PRIORITY',
			sortable : 'true',
			align : 'left',
			width : 50
		}, {
			title : '触发器状态',
			field : 'TRIGGER_STATE',
			sortable : 'true',
			align : 'left',
			width : 80
		}, {
			title : '触发器类型',
			field : 'TRIGGER_TYPE',
			sortable : 'true',
			align : 'left',
			width : 70
		}, {
			title : '开始时间',
			field : 'START_TIME',
			sortable : 'true',
			align : 'left',
			width : 150,
			formatter : function(value, row, index) {
				if (value) {
					var unixTimestamp = new Date(value);
					return unixTimestamp.toLocaleString();
				}
			}
		}, {
			title : '结束时间',
			field : 'END_TIME',
			sortable : 'true',
			align : 'left',
			width : 150,
			formatter : function(value, row, index) {
				if (value) {
					var unixTimestamp = new Date(value);
					return unixTimestamp.toLocaleString();
				}
			}
		}, {
			title : '日历名称',
			field : 'CALENDAR_NAME',
			align : 'left',
			width : 70
		}, {
			title : '跳过执行',
			field : 'MISFIRE_INSTR',
			align : 'left',
			width : 80
		}, {
			title : '计划任务名称',
			field : 'SCHED_NAME',
			align : 'left',
			width : 100
		} ] ],
		onDblClickRow : function(rowIndex, rowData) {
			var triggerName = rowData.TRIGGER_NAME;
			var triggerGroup = rowData.TRIGGER_GROUP;
			var jobName = rowData.JOB_NAME;
			var jonGroup = rowData.JOB_GROUP;
			openJobTriggerHisWindow(triggerName, triggerGroup, jobName, jonGroup);
		},
		onClickRow : function(rowIndex, rowData) {
			var sName = rowData.SCHED_NAME;
			var jName = rowData.JOB_NAME;
			var jGroup = rowData.JOB_GROUP;
			var tName = rowData.TRIGGER_NAME;
			var tGroup = rowData.TRIGGER_GROUP;
			var tType = rowData.TRIGGER_TYPE;

			var param = '?sName=' + sName;
			param += '&jName=' + jName;
			param += '&jGroup=' + jGroup;
			param += '&tName=' + tName;
			param += '&tGroup=' + tGroup;
			param += '&tType=' + tType;

			$('#schedulerDetailPanel').panel('refresh', basePath + 'quartzController/getSchedulerDetail' + param);
		},
		onLoadError : function() {
			showErrorWindow('数据加载失败!');
		}
	});

	// 操作历史
	$('#scheduler_log_btn').click(function() {
		$('#scheduler_log_window').window({
			title : '操作历史',
			collapsible : false,
			minimizable : false,
			maximizable : true,
			resizable : false,
			modal : true,
			width : 800,
			height : 500,
			href : basePath + 'quartzController/toSchedulerLogPage'
		});
	});

	// 执行历史
	$('#job_trigger_his_btn').click(function() {
		openJobTriggerHisWindow(null, null, null, null);
	});
	function openJobTriggerHisWindow(triggerName, triggerGroup, jobName, jonGroup) {
		var param = '?';
		if (triggerName != null) {
			param += 'triggerName=' + triggerName + '&';
		}
		if (triggerGroup != null) {
			param += 'triggerGroup=' + triggerGroup + '&';
		}
		if (jobName != null) {
			param += 'jobName=' + jobName + '&';
		}
		if (jonGroup != null) {
			param += 'jonGroup=' + jonGroup + '&';
		}

		$('#job_trigger_his_window').window({
			title : '执行历史',
			collapsible : false,
			minimizable : false,
			maximizable : true,
			resizable : false,
			modal : true,
			width : 1000,
			height : 500,
			href : basePath + 'quartzController/toExecutionHistoryPage' + param
		});
	}

	// 查询按钮
	$('#triggers_search_btn').click(function() {
		$('#triggersTable').datagrid('load', {
			tNameSearch : $('#tNameSearch').val(),
			tGroupSearch : $('#tGroupSearch').val(),
			jNameSearch : $('#jNameSearch').val(),
			jGroupSearch : $('#jGroupSearch').val()
		});
	});

	// 绑定查询按钮
	$('#tNameSearch,#tGroupSearch,#jNameSearch,#jGroupSearch').keydown(function(e) {
		if (e.keyCode == 13) {
			$('#triggers_search_btn').click();
		}
	});

	// 编辑作业
	$('#job_detail_add_btn').click(function() {
		$('#job_detail_add_window').window({
			title : '编辑作业',
			collapsible : false,
			minimizable : false,
			maximizable : false,
			resizable : false,
			modal : true,
			width : 480,
			height : 500,
			href : basePath + 'quartzController/toAddJobDetail'
		});
	});

	// 添加SIMPLE触发器
	$('#simple_trigger_add_btn').click(function() {
		$('#simple_trigger_add_window').window({
			title : '编辑SIMPLE触发器',
			collapsible : false,
			minimizable : false,
			maximizable : false,
			resizable : false,
			modal : true,
			width : 480,
			height : 500,
			href : basePath + 'quartzController/toAddSimpleTrigger'
		});
	});

	// 添加cron触发器
	$('#cron_trigger_add_btn').click(function() {
		$('#cron_trigger_add_window').window({
			title : '编辑CRON触发器',
			collapsible : false,
			minimizable : false,
			maximizable : false,
			resizable : false,
			modal : true,
			width : 480,
			height : 500,
			href : basePath + 'quartzController/toAddCronTrigger'
		});
	});

	// 移除触发器
	$('#trigger_delete_btn').click(function() {
		schedulerOp('unscheduleJob');
	});

	// 移除作业
	$('#job_detail_delete_btn').click(function() {
		schedulerOp('deleteJob');
	});

	// 暂停所有
	$('#pause_all_btn').click(function() {
		schedulerOpNoSelected('pauseAll');
	});

	// 暂停作业
	$('#pause_job_btn').click(function() {
		schedulerOp('pauseJob');
	});

	// 暂停触发器
	$('#pause_trigger_btn').click(function() {
		schedulerOp('pauseTrigger');
	});

	// 恢复所有
	$('#resume_all_btn').click(function() {
		schedulerOpNoSelected('resumeAll');
	});

	// 恢复作业
	$('#resume_job_btn').click(function() {
		schedulerOp('resumeJob');
	});

	// 恢复作业
	$('#resume_trigger_btn').click(function() {
		schedulerOp('resumeTrigger');
	});

	// 触发
	$('#trigger_job_btn').click(function() {
		schedulerOpNoConfirm('triggerJob');
	});

	function schedulerOpNoSelected(op) {
		var param = '?op=' + op;
		$.messager.confirm('警告', '是否确认要操作?', function(r) {
			if (r) {
				schedulerOpAjax(param);
			}
		});
	}
	function schedulerOpNoConfirm(op) {
		var row = $('#triggersTable').datagrid('getSelected');
		if (row) {
			var jn = row.JOB_NAME;
			var jg = row.JOB_GROUP;
			var tn = row.TRIGGER_NAME;
			var tg = row.TRIGGER_GROUP;

			var param = '?op=' + op;
			param += '&jn=' + jn;
			param += '&jg=' + jg;
			param += '&tn=' + tn;
			param += '&tg=' + tg;

			schedulerOpAjax(param);

		} else {
			showErrorMsg('请选择要操作的数据');
		}
	}
	function schedulerOp(op) {
		var row = $('#triggersTable').datagrid('getSelected');
		if (row) {
			var jn = row.JOB_NAME;
			var jg = row.JOB_GROUP;
			var tn = row.TRIGGER_NAME;
			var tg = row.TRIGGER_GROUP;

			var param = '?op=' + op;
			param += '&jn=' + jn;
			param += '&jg=' + jg;
			param += '&tn=' + tn;
			param += '&tg=' + tg;

			$.messager.confirm('警告', '是否确认要操作?', function(r) {
				if (r) {
					schedulerOpAjax(param);
				}
			});
		} else {
			showErrorMsg('请选择要操作的数据');
		}
	}

	function schedulerOpAjax(param) {
		$.ajax({
			type : 'POST',
			url : basePath + 'quartzController/schedulerOp' + param,
			success : function(data) {
				// 解析数据
				var datas = strToJson(data);
				if (datas.code == '1') {
					$('#triggers_search_btn').click();
				} else {
					showErrorMsg('操作失败');
				}
			}
		});
	}

	// 初始化panel
	$('#schedulerDetailPanel').panel({
		title : "计划任务明细",
		fit : true,
		href : basePath + "quartzController/getSchedulerDetail",
		tools : [ {
			iconCls : 'icon-reload',
			handler : function() {
				$('#schedulerDetailPanel').panel('refresh', basePath + 'quartzController/getSchedulerDetail');
			}
		} ]
	});

	// 自动轮询
	var TRIGGER_SEARCH_INTERVAL = getServerParam(basePath, 'TRIGGER_SEARCH_INTERVAL');
	setInterval("$('#firedTriggers_search_btn').click();", TRIGGER_SEARCH_INTERVAL);
	setInterval("$('#triggers_search_btn').click();", TRIGGER_SEARCH_INTERVAL);

	// 设置查询标题
	$('#firedTriggers_search_btn').linkbutton({
		text : '查询(' + TRIGGER_SEARCH_INTERVAL + 'ms)'
	});

	// 设置查询标题
	$('#triggers_search_btn').linkbutton({
		text : '查询(' + TRIGGER_SEARCH_INTERVAL + 'ms)'
	});

});