/**
 * 
 */
$(function() {
	// 参数代码
	$('#parametersCode_add').validatebox(
			{
				required : true,
				validType : [ "length[1,100]", "not_chinese",
						"remote[basePath + 'serviceParamController/parametersCodeUniqueCheck', 'parametersCode']" ]
			});

	// 参数取值
	$('#parametersValue_add').validatebox({
		required : true,
		validType : [ "length[1,250]" ]
	});

	// 参数描述
	$('#parametersDesc_add').validatebox({
		required : true,
		validType : [ "length[1,250]" ]
	});

	// 保存按钮
	$('#parameters_add_btn').linkbutton({});

	// 绑定保存按钮事件
	$('#parameters_add_btn').click(function() {
		save();
	});

	// 保存方法
	function save() {
		$('#parameters_form_add').attr('action', basePath + 'serviceParamController/addParameters');
		// 提交
		$('#parameters_form_add').form('submit', {
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
						$('#addServerParamWindows').window('close');
						$('#serverParamSearchBtn').click();
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
