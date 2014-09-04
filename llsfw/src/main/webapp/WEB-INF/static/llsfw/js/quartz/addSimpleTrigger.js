/**
 * 
 */
$(function() {

	// 作业名称
	$('#simple_jName_view').combogrid({
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
			$('#simple_jGroup_view').val(rowData.JOB_GROUP);
			$('#simple_jClass_view').val(rowData.JOB_CLASS_NAME);
			$('#simple_jDesc_view').val(rowData.DESCRIPTION);
			$('#simple_concurrent_view').prop("checked", rowData.IS_NONCONCURRENT == '1' ? true : false);
			$('#simple_jobShouldRecover_view').prop("checked", rowData.REQUESTS_RECOVERY == '1' ? true : false);
			$('#simple_jobDurability_view').prop("checked", rowData.IS_DURABLE == '1' ? true : false);
			simpleTriggerNameGrid(rowData.SCHED_NAME, rowData.JOB_NAME, rowData.JOB_GROUP);
		},
		onLoadError : function() {
			showErrorWindow('数据加载失败!');
		}
	});

	// 清除按钮
	$('#simple_triggers_clear_job').click(function() {
		simpleTriggerNameGrid('', '', '');
		$('#simple_jName_view').combogrid("clear");
		$('#simple_jGroup_view').val("");
		$('#simple_jClass_view').val("");
		$('#simple_jDesc_view').val("");
		$('#simple_concurrent_view').prop("checked", false);
		$('#simple_jobShouldRecover_view').prop("checked", false);
		$('#simple_jobDurability_view').prop("checked", false);
	});

	// 作业名称
	simpleTriggerNameGrid('', '', '');

	// 作业组别
	$('#simple_tGroup_add').combobox({
		required : true,
		url : basePath + 'quartzController/getTriggerGroupNames',
		valueField : 'TRIGGER_GROUP_NAME',
		textField : 'TRIGGER_GROUP_NAME',
		editable : true,
		required : true,
		validType : [ "length[1,100]", "not_chinese" ]
	});

	// 重复次数
	$('#simple_triggerRepeatCount_add').numberbox({
		required : true,
		validType : [ "length[1,7]" ],
		min : -1
	});

	// 执行间隔(毫秒)
	$('#simple_intervalInMilliseconds_add').numberbox({
		required : true,
		validType : [ "length[1,12]" ],
		min : 1000
	});

	// 优先级
	$('#simple_priority_add').numberbox({
		required : true,
		validType : [ "length[1,13]" ],
		min : 1
	});

	// 开始时间
	$('#simple_triggerStartTime_add').datetimebox({
		required : false,
		validType : [ "yyyymmddhhmiss" ],
		showSeconds : true
	});

	// 结束时间
	$('#simple_triggerEndTime_add').datetimebox({
		required : false,
		validType : [ "yyyymmddhhmiss" ],
		showSeconds : true
	});

	// 保存按钮
	$('#simple_triggers_job_from_add_btn').linkbutton({});

	// 绑定保存按钮事件
	$('#simple_triggers_job_from_add_btn').click(function() {
		save();
	});

	// 保存方法
	function save() {
		$('#simple_triggers_form_add').attr('action', basePath + 'quartzController/addSimpleTirgger');
		$('#simple_triggers_form_add').form('submit', {
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
						$('#simple_trigger_add_window').window('close');
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

	// 触发器名称查询
	function simpleTriggerNameGrid(sName, jName, jGroup) {
		$('#simple_tName_add').combogrid({
			// delay : 500,
			required : true,
			validType : [ "length[1,100]", "not_chinese" ],
			panelWidth : 700,
			loadMsg : '加载中...',
			// mode : 'remote',
			method : 'post',
			rownumbers : true,
			singleSelect : true,
			url : basePath + 'quartzController/getSimpleTriggerList?sName=' + sName + '&jName=' + jName + '&jGroup=' + jGroup,
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
				title : '执行次数',
				field : 'REPEAT_COUNT',
				width : 100
			}, {
				title : '执行间隔',
				field : 'REPEAT_INTERVAL',
				width : 100
			}, {
				title : '触发次数',
				field : 'TIMES_TRIGGERED',
				width : 100
			} ] ],
			onSelect : function(rowIndex, rowData) {
				$('#simple_tGroup_add').combobox('setValue', rowData.TRIGGER_GROUP);
				$('#simple_intervalInMilliseconds_add').numberbox('setValue', rowData.REPEAT_INTERVAL);
				$('#simple_triggerRepeatCount_add').numberbox('setValue', rowData.REPEAT_COUNT);
			},
			onLoadError : function() {
				showErrorWindow('数据加载失败!');
			}
		});
	}
});
