/**
 * 
 */
$(function() {
	// 用户密码采用初始化的形式

	// 用户名
	$('#loginName_add').validatebox({
		required : true,
		validType : [ "length[1,100]", "not_chinese", "remote[basePath + 'userController/loginNameUniqueCheck', 'loginName']" ]
	});

	// 用户名称
	$('#userName_add').validatebox({
		required : true,
		validType : [ "length[1,30]" ]
	});

	// 座机
	$('#userPhone_add').validatebox({
		required : true,
		validType : [ "length[1,100]", "phone" ]
	});

	// 手机
	$('#userMPhome_add').validatebox({
		required : true,
		validType : [ "length[1,100]", "mobile" ]
	});

	// 电子邮件
	$('#userEmail_add').validatebox({
		required : true,
		validType : [ "length[1,100]", "email" ]
	});

	// 用户状态
	$('#userStatus_add').combobox({
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

	// 保存按钮
	$('#user_add_btn').linkbutton({});

	// 绑定保存按钮事件
	$('#user_add_btn').click(function() {
		save();
	});

	// 保存方法
	function save() {
		$('#user_form_add').attr('action', basePath + 'userController/addUser');
		// 提交
		$('#user_form_add').form('submit', {
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
						$('#user_window_add').window('close');
						$('#user_table_search_btn').click();
						showInfoMsg(datas.result);
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
