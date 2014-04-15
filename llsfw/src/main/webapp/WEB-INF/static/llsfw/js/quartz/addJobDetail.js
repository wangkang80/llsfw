/**
 * 
 */
$(function() {

	// 作业名称
	$('#jName_add').combogrid({
		// delay : 500,
		required : true,
		validType : [ "length[1,100]", "not_chinese" ],
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
			title : '是否耐用',
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
			$('#jGroup_add').combobox('setValue', rowData.JOB_GROUP);
			$('#jClass_add').val(rowData.JOB_CLASS_NAME);
			$('#jDesc_add').val(rowData.DESCRIPTION);
			$('#jobShouldRecover_add').prop("checked", rowData.REQUESTS_RECOVERY == '1' ? true : false);
			$('#jobDurability_add').prop("checked", rowData.IS_DURABLE == '1' ? true : false);

		},
		onLoadError : function() {
			showErrorWindow('数据加载失败!');
		}
	});

	// 作业组别
	$('#jGroup_add').combobox({
		required : true,
		url : basePath + 'quartzController/getJobGroupNames',
		valueField : 'JOB_GROUP_NAME',
		textField : 'JOB_GROUP_NAME',
		editable : true,
		required : true,
		validType : [ "length[1,100]", "not_chinese" ]
	});

	// 作业class
	$('#jClass_add').validatebox({
		required : true,
		validType : [ "length[1,250]", "not_chinese", "remote[basePath + 'quartzController/jClassCheck', 'jClass']" ]
	});

	// 作业描述
	$('#jDesc_add').validatebox({
		required : true,
		validType : [ "length[1,100]" ]
	});

	// 删除按钮
	$('#job_detail_form_delete_btn').linkbutton({});

	// 绑定删除按钮事件
	$('#job_detail_form_delete_btn').click(function() {
		deleteJobDetail();
	});

	// 删除方法
	function deleteJobDetail() {
		var jn = $('#jName_add').combogrid('getValue');
		var jg = $('#jGroup_add').combobox('getValue');
		var tn = '';
		var tg = '';
		if (jn == null || jg == null) {
			showErrorMsg('请选择需要删除的数据');
		} else {
			var param = '?op=deleteJob';
			param += '&jn=' + jn;
			param += '&jg=' + jg;
			param += '&tn=' + tn;
			param += '&tg=' + tg;
			$.ajax({
				type : 'POST',
				url : basePath + 'quartzController/schedulerOp' + param,
				success : function(data) {
					// 解析数据
					var datas = strToJson(data);
					if (datas.code == '1') {
						showErrorMsg('删除成功');
						$('#job_detail_add_window').window('close');
						$('#triggers_search_btn').click();
					} else {
						showErrorMsg('操作失败');
					}
				}
			});
		}
	}

	// 保存按钮
	$('#job_detail_form_add_btn').linkbutton({});

	// 绑定保存按钮事件
	$('#job_detail_form_add_btn').click(function() {
		save();
	});

	// 保存方法
	function save() {
		$('#job_detail_form_add').attr('action', basePath + 'quartzController/addJobDetail');
		$('#job_detail_form_add').form('submit', {
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
						$('#job_detail_add_window').window('close');
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
});
