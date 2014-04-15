/**
 * 
 */
$(function() {
	$('#function_form_edit').form({
		onLoadSuccess : function(data) { // 加载完毕后在格式化表单
			// 加载数据
			$('#functionName_edit').val(data.functionName);
			$('#parentFunctionCode_edit').val(data.parentFunctionCode);
			$('#status_edit').val(data.status);
			$('#sort_edit').val(data.sort);
			$('#levelNo_edit').val(data.levelNo);
			$('#requestUrl_edit').val(data.requestUrl);

			// 格式化表单------------------------------------

			// 功能名称
			$('#functionName_edit').validatebox({
				required : true,
				validType : [ "length[1,45]" ]
			});

			// 功能状态
			$('#status_edit').combobox({
				required : true,
				method : 'get',
				url : basePath + 'static/llsfw/json/enableOrDisable.json',
				valueField : 'value',
				textField : 'text',
				editable : false
			});

			// 功能排序
			$('#sort_edit').numberspinner({
				required : true,
				min : 1,
				editable : false
			});

			// 功能级别
			$('#levelNoDisplay_edit').val(levelDisplay($('#levelNo_edit').val()));

			// 请求路径(级别=3,要求url必填)
			if ($('#levelNo_edit').val() == 3) {
				$('#requestUrl_edit').validatebox({
					required : true,
					validType : [ "length[1,100]", "not_chinese" ]
				});
			} else {
				$('#requestUrl_edit').attr("readonly", "readonly");
			}

			// 保存按钮
			$('#function_save_edit_btn').linkbutton({});

			// 绑定保存按钮事件
			$('#function_save_edit_btn').click(function() {
				save();
			});
		}
	});

	// 加载表单数据
	$('#function_form_edit').form('load', basePath + 'functionController/loadFunction?functionCode=' + $('#functionCode_edit').val());

	// 保存方法
	function save() {
		$('#function_form_edit').attr('action', basePath + 'functionController/editFunction');
		// 提交
		$('#function_form_edit').form('submit', {
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
						$('#function_edit_window').window('close');
						$('#function_search_btn').click();
					} else {
						// 弹出提示
						showErrorMsg(datas.errorMessage);
					}
				} catch (e) {
					showErrorWindow(data);
				}
			}
		});
	}
});
