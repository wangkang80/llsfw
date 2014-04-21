/**
 * 
 */
$(function() {

	// 岗位代码
	$('#jobCode_add').validatebox({
		required : true,
		validType : [ "length[1,100]", "not_chinese", "remote[basePath + 'orgController/jobCodeUniqueCheck', 'jobCode']" ]
	});

	// 岗位名称
	$('#jobName_add').validatebox({
		required : true,
		validType : [ "length[1,45]" ]
	});

	// 所属组织
	$('#job_rogCode_add').combotree({
		url : basePath + 'orgController/getOrgNodeTree',
		required : true,
		lines : true,
		editable : false
	});

	// 保存按钮
	$('#job_save_add_btn').linkbutton({});

	// 绑定保存按钮事件
	$('#job_save_add_btn').click(function() {
		save();
	});

	// 保存方法
	function save() {
		$('#job_form_add').attr('action', basePath + 'orgController/addJob');
		// 提交
		$('#job_form_add').form('submit', {
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
					if (datas.returnCode == '1') {
						$('#job_add_window').window('close');
						$('#job_search_btn').click();
					} else {
						showErrorMsg(datas.result);
					}
				} catch (e) {
					showErrorWindow(data);
				}
			}
		});
	}
});
