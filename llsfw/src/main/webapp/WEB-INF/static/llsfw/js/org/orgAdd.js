/**
 * 
 */
$(function() {

	// 组织代码
	$('#orgCode_add').validatebox({
		required : true,
		validType : [ "length[1,100]", "not_chinese", "remote[basePath + 'orgController/orgCodeUniqueCheck', 'orgCode']" ]
	});

	// 功能名称
	$('#orgName_add').validatebox({
		required : true,
		validType : [ "length[1,45]" ]
	});

	// 功能排序
	$('#org_sort_add').numberspinner({
		required : true,
		min : 1,
		editable : false
	});

	// 保存按钮
	$('#org_save_add_btn').linkbutton({});

	// 绑定保存按钮事件
	$('#org_save_add_btn').click(function() {
		save();
	});

	// 保存方法
	function save() {
		$('#org_form_add').attr('action', basePath + 'orgController/addOrg');
		// 提交
		$('#org_form_add').form('submit', {
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
						$('#org_add_window').window('close');
						$('#org_search_btn').click();
					} else {
						// 弹出提示
						showErrorMsg(datas.result);
					}
				} catch (e) {
					showErrorWindow(data);
				}
			}
		});
	}
});
