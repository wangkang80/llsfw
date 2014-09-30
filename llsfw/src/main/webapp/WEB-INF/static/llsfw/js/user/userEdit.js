/**
 * 
 */
$(function() {

	$('#user_form_edit').form({
		onLoadSuccess : function(data) { // 加载完毕后在格式化表单

			// 加载数据
			$('#userName_edit').val(data.userName);
			$('#userPhone_edit').val(data.userPhone);
			$('#userMPhome_edit').val(data.userMPhome);
			$('#userEmail_edit').val(data.userEmail);
			$('#userStatus_edit').val(data.userStatus);

			// 格式化表单

			// 用户名称
			$('#userName_edit').validatebox({
				required : true,
				validType : [ "length[1,30]" ]
			});

			// 座机
			$('#userPhone_edit').validatebox({
				required : true,
				validType : [ "length[1,100]", "phone" ]
			});

			// 手机
			$('#userMPhome_edit').validatebox({
				required : true,
				validType : [ "length[1,100]", "mobile" ]
			});

			// 电子邮件
			$('#userEmail_edit').validatebox({
				required : true,
				validType : [ "length[1,100]", "email" ]
			});

			// 用户状态
			$('#userStatus_edit').combobox({
				required : true,
				method : 'get',
				url : basePath + 'static/llsfw/json/enableOrDisable.json',
				valueField : 'value',
				textField : 'text',
				editable : false
			});

			// 保存按钮
			$('#user_edit_btn').linkbutton({});

			// 绑定保存按钮事件
			$('#user_edit_btn').click(function() {
				save();
			});
		}
	});

	// 加载表单数据
	$('#user_form_edit').form('load', basePath + 'userController/loadUser?loginName=' + $('#loginName_edit').val());

	// 保存方法
	function save() {
		$('#user_form_edit').attr('action', basePath + 'userController/editUser');
		// 提交
		$('#user_form_edit').form('submit', {
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
						$('#user_window_edit').window('close');
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
