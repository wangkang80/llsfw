/**
 * 
 */
$(function() {

	$('#job_form_edit').form({
		onLoadSuccess : function(data) { // 加载完毕后在格式化表单
			// 加载数据
			$('#jobName_edit').val(data.jobName);
			$('#job_rogCode_edit').val(data.orgCode);

			// 格式化表单------------------------------------

			// 岗位名称
			$('#jobName_edit').validatebox({
				required : true,
				validType : [ "length[1,100]" ]
			});

			// 所属组织
			$('#job_rogCode_edit').combotree({
				url : basePath + 'orgController/getOrgNodeTree',
				required : true,
				lines : true,
				editable : false
			});

			// 保存按钮
			$('#job_save_edit_btn').linkbutton({});

			// 绑定保存按钮事件
			$('#job_save_edit_btn').click(function() {
				save();
			});
		}
	});

	// 加载表单数据
	$('#job_form_edit').form('load', basePath + 'orgController/loadJob?jobCode=' + $('#jobCode_edit').val());

	// 保存方法
	function save() {
		$('#job_form_edit').attr('action', basePath + 'orgController/editJob');
		// 提交
		$('#job_form_edit').form('submit', {
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
						$('#job_edit_window').window('close');
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
