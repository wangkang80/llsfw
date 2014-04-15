/**
 * 
 */
$(function() {
	$('#parameters_form_edit').form({
		onLoadSuccess : function(data) { // 加载完毕后在格式化表单

			// 加载数据
			$('#parametersValue_edit').val(data.parametersValue);
			$('#parametersDesc_edit').val(data.parametersDesc);
			$('#parametersTypeName_edit').val(data.parametersTypeName);
			$('#parametersTypeCode_edit').val(data.parametersTypeCode);

			// 格式化表单------------------------------------

			// 参数取值
			$('#parametersValue_edit').validatebox({
				required : true,
				validType : [ "length[1,250]" ]
			});

			// 参数描述
			$('#parametersDesc_edit').validatebox({
				required : true,
				validType : [ "length[1,250]" ]
			});

			// 类别描述
			$('#parametersTypeName_edit').combobox({
				required : true,
				url : basePath + 'serviceParamController/getAllParamType?hasAll=false',
				valueField : 'PARAMETERS_TYPE_NAME',
				textField : 'PARAMETERS_TYPE_NAME',
				editable : true,
				onSelect : function(rec) {
					$('#parametersTypeCode_edit').val(rec.PARAMETERS_TYPE_CODE);
				}
			// ,onLoadSuccess : function() { // 加载完成后,设置选中第一项
			// var val = $(this).combobox("getData");
			// for ( var item in val[0]) {
			// if (item == "PARAMETERS_TYPE_CODE") {
			// $(this).combobox("select", val[0][item]);
			// }
			// }
			// }
			});

			// 类别代码
			$('#parametersTypeCode_edit').validatebox({
				required : true,
				validType : [ "length[1,100]", "not_chinese" ]
			});

			// 保存按钮
			$('#parameters_edit_btn').linkbutton({});

			// 绑定保存按钮事件
			$('#parameters_edit_btn').click(function() {
				save();
			});
		}
	});

	// 加载表单数据
	$('#parameters_form_edit').form('load',
			basePath + 'serviceParamController/loadParam?parametersCode=' + $('#parametersCode_edit').val());

	// 保存方法
	function save() {
		$('#parameters_form_edit').attr('action', basePath + 'serviceParamController/editParameters');
		// 提交
		$('#parameters_form_edit').form('submit', {
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
				// 关闭遮罩
				$.messager.progress('close');

				// 解析数据
				var datas = strToJson(data);

				if (datas.code == '1') {
					$('#editServerParamWindows').window('close');
					$('#serverParamSearchBtn').click();
					$('#parametersTypeCode').combobox('reload');
				} else if (datas.code == '-1') {// 类别描述重复
					$('#parametersTypeName_edit').select();
					// 弹出提示
					showErrorMsg(datas.message);
				}
			}
		});
	}
});
