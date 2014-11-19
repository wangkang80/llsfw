/**
 * 
 */
$(function() {

	// 功能代码
	$('#functionCode_add').validatebox({
		required : true,
		validType : [ "length[1,100]", "not_chinese", "remote[basePath + 'functionController/functionCodeUniqueCheck', 'functionCode']" ]
	});

	// 功能名称
	$('#functionName_add').validatebox({
		required : true,
		validType : [ "length[1,45]" ]
	});

	// 功能状态
	$('#status_add').combobox({
		required : true,
		method : 'get',
		url : basePath + 'static/llsfw/json/enableOrDisable.json',
		valueField : 'value',
		textField : 'text',
		editable : false,
		onLoadSuccess : function() { // 加载完成后,设置选中第一项
			var val = $(this).combobox("getData");
			for ( var item in val[0]) {
				if (item == "value") {
					$(this).combobox("select", val[0][item]);
					break;
				}
			}
		}
	});

	// 功能排序
	$('#sort_add').numberspinner({
		required : true,
		min : 1,
		editable : false
	});

	// 功能级别
	$('#levelNoDisplay_add').val(levelDisplay($('#levelNo_add').val()));

	// 请求路径(级别=3,要求url必填)
	if ($('#levelNo_add').val() == 3) {
		$('#requestUrl_add').validatebox({
			required : true,
			validType : [ "length[1,100]", "not_chinese" ]
		});
	} else {
		$('#requestUrl_add').attr("readonly", "readonly");
	}

	// 保存按钮
	$('#function_save_add_btn').linkbutton({});

	// 绑定保存按钮事件
	$('#function_save_add_btn').click(function() {
		save();
	});

	// 保存方法
	function save() {
		$('#function_form_add').attr('action', basePath + 'functionController/addFunction');
		// 提交
		$('#function_form_add').form('submit', {
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
						$('#function_add_window').window('close');
						$('#function_search_btn').click();
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
