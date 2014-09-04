/**
 * 
 */
$(function() {

	// 作业名称
	$('#cron_jName_view').combogrid({
		// delay : 500,
		required : true,
		editable : false,
		panelWidth : 700,
		loadMsg : '加载中...',
		// mode : 'remote',
		method : 'post',
		rownumbers : true,
		singleSelect : true,
		url : basePath + 'quartzController/getJobDetail',
		idField : 'JOB_NAME',
		textField : 'JOB_NAME',
		columns : [ [ {
			title : '计划任务名称',
			field : 'SCHED_NAME',
			width : 140
		}, {
			title : '作业名称',
			field : 'JOB_NAME',
			width : 100
		}, {
			title : '作业组别',
			field : 'JOB_GROUP',
			width : 100
		}, {
			title : '作业类',
			field : 'JOB_CLASS_NAME',
			width : 250
		}, {
			title : '作业描述',
			field : 'DESCRIPTION',
			width : 100
		}, {
			title : '是否持久',
			field : 'IS_DURABLE',
			width : 100
		}, {
			title : '禁止并发',
			field : 'IS_NONCONCURRENT',
			width : 100
		}, {
			title : '遗漏恢复',
			field : 'REQUESTS_RECOVERY',
			width : 100
		} ] ],
		onSelect : function(rowIndex, rowData) {
			$('#cron_jGroup_view').val(rowData.JOB_GROUP);
			$('#cron_jClass_view').val(rowData.JOB_CLASS_NAME);
			$('#cron_jDesc_view').val(rowData.DESCRIPTION);
			$('#cron_concurrent_view').prop("checked", rowData.IS_NONCONCURRENT == '1' ? true : false);
			$('#cron_jobShouldRecover_view').prop("checked", rowData.REQUESTS_RECOVERY == '1' ? true : false);
			$('#cron_jobDurability_view').prop("checked", rowData.IS_DURABLE == '1' ? true : false);
			cronTriggerGrid(rowData.SCHED_NAME, rowData.JOB_NAME, rowData.JOB_GROUP);
		},
		onLoadError : function() {
			showErrorWindow('数据加载失败!');
		}
	});

	// 清除按钮
	$('#cron_triggers_clear_job').click(function() {
		cronTriggerGrid('', '', '');
		$('#cron_jName_view').combogrid("clear");
		$('#cron_jGroup_view').val("");
		$('#cron_jClass_view').val("");
		$('#cron_jDesc_view').val("");
		$('#cron_concurrent_view').prop("checked", false);
		$('#cron_jobShouldRecover_view').prop("checked", false);
		$('#cron_jobDurability_view').prop("checked", false);
	});

	// 触发器名称
	cronTriggerGrid('', '', '');

	// 触发器组别
	$('#cron_tGroup_add').combobox({
		required : true,
		url : basePath + 'quartzController/getTriggerGroupNames',
		valueField : 'TRIGGER_GROUP_NAME',
		textField : 'TRIGGER_GROUP_NAME',
		editable : true,
		required : true,
		validType : [ "length[1,100]", "not_chinese" ]
	});

	// CRON表达式
	$('#cron_cronExpression_add').validatebox({
		required : true,
		validType : [ "length[1,120]" ]
	});

	// 优先级
	$('#cron_priority_add').numberbox({
		required : true,
		validType : [ "length[1,13]" ],
		min : 1
	});

	// 开始时间
	$('#cron_triggerStartTime_add').datetimebox({
		required : false,
		validType : [ "yyyymmddhhmiss" ],
		showSeconds : true
	});

	// 结束时间
	$('#cron_triggerEndTime_add').datetimebox({
		required : false,
		validType : [ "yyyymmddhhmiss" ],
		showSeconds : true
	});

	// 生成cron
	$('#cron_triggers_generate').click(function() {
		$('#cron_triggers_generate_window').window({
			title : 'CRON生成',
			collapsible : false,
			minimizable : false,
			maximizable : false,
			resizable : false,
			modal : true,
			width : 660,
			height : 470,
			href : basePath + 'quartzController/toCronTriggersGeneratePage'
		});
	});

	// 保存按钮
	$('#cron_triggers_job_from_add_btn').linkbutton({});

	// 绑定保存按钮事件
	$('#cron_triggers_job_from_add_btn').click(function() {
		save();
	});

	// 保存方法
	function save() {
		$('#cron_triggers_form_add').attr('action', basePath + 'quartzController/addCronTrigger');
		$('#cron_triggers_form_add').form('submit', {
			onSubmit : function() {// 提交前置事件
				var isValid = $(this).form('validate');
				if (isValid) {// 验证通过,弹出遮罩
					$.messager.progress({
						text : '保存中...',
						interval : 200
					});
				}
				return isValid; // return false will stop the form submission
			},
			success : function(data) {
				try {
					// 关闭遮罩
					$.messager.progress('close');

					// 解析数据
					var datas = strToJson(data);

					if (datas.code == '1') {
						$('#cron_trigger_add_window').window('close');
						$('#triggers_search_btn').click();
					} else if (datas.code == '-1') {
						// 弹出提示
						showErrorMsg(datas.message);
					}
				} catch (e) {
					showErrorWindow(data);
				}
			}
		});
	}

	// 触发器名称
	function cronTriggerGrid(sName, jName, jGroup) {
		$('#cron_tName_add').combogrid({
			// delay : 500,
			required : true,
			validType : [ "length[1,100]", "not_chinese" ],
			panelWidth : 700,
			loadMsg : '加载中...',
			// mode : 'remote',
			method : 'post',
			rownumbers : true,
			singleSelect : true,
			url : basePath + 'quartzController/getCronTriggerList?sName=' + sName + '&jName=' + jName + '&jGroup=' + jGroup,
			idField : 'TRIGGER_NAME',
			textField : 'TRIGGER_NAME',
			columns : [ [ {
				title : '计划任务名称',
				field : 'SCHED_NAME',
				width : 140
			}, {
				title : '触发器名称',
				field : 'TRIGGER_NAME',
				width : 100
			}, {
				title : '触发器组别',
				field : 'TRIGGER_GROUP',
				width : 100
			}, {
				title : 'CRON表达式',
				field : 'CRON_EXPRESSION',
				width : 200
			}, {
				title : '执行时区',
				field : 'TIME_ZONE_ID',
				width : 100
			} ] ],
			onSelect : function(rowIndex, rowData) {
				$('#cron_tGroup_add').combobox('setValue', rowData.TRIGGER_GROUP);
				$('#cron_cronExpression_add').val(rowData.CRON_EXPRESSION);
			},
			onLoadError : function() {
				showErrorWindow('数据加载失败!');
			}
		});
	}
});
