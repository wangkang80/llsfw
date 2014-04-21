/**
 * 
 */
$(function() {
	// 构造表格
	$('#job_table').datagrid({
		url : basePath + 'userController/getJobList?loginName=' + $('#user_job').val(),
		method : 'post',
		fit : true,
		rownumbers : true,
		singleSelect : false,
		pagination : false,
		toolbar : '#job_table_param',
		queryParams : {},
		columns : [ [ {
			checkbox : true,
			field : 'CK'
		}, {
			title : '岗位代码',
			field : 'JOB_CODE',
			align : 'left',
			width : 100
		}, {
			title : '岗位名称',
			field : 'JOB_NAME',
			align : 'left',
			width : 100
		} ] ],
		onLoadError : function() {
			showErrorWindow('数据加载失败!');
		}
	});

	// 保存按钮
	$('#job_table_save_btn').click(function() {
		// 获得选中的数据
		var rows = $('#job_table').datagrid('getSelections');
		var jobCodes = [];
		for (var i = 0; i < rows.length; i++) {
			jobCodes.push(rows[i].JOB_CODE);
		}
		var codes = jobCodes.join(',');

		// 如果有选中数据,提交,没有则提示
		if (codes) {
			$('#jobCodeList').val(codes); // 设置表单值
			$('#user_job_form_add').attr('action', basePath + 'userController/addUserJob');
			$('#user_job_form_add').form('submit', {
				onSubmit : function() {// 提交前置事件
					var isValid = $(this).form('validate');
					if (isValid) {// 验证通过,弹出遮罩
						$.messager.progress({
							text : '保存中...',
							interval : 200
						});
					}
					return isValid;
				},
				success : function(data) {
					try {
						// 关闭遮罩
						$.messager.progress('close');

						// 解析数据
						var datas = strToJson(data);

						if (datas.returnCode == '1') {
							$('#user_job_window_add').window('close');
							$('#user_job_table').datagrid('reload');
						} else {
							showErrorMsg(datas.result);
						}
					} catch (e) {
						showErrorWindow(data);
					}
				}
			});
		} else {
			showErrorMsg('请选择要添加的岗位');
		}
	});
});