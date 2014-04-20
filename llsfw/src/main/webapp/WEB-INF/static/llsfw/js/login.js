$(function() {

	// 弹出服务器消息
	if ($('#msg').val()) {
		showErrorMsg($('#msg').val());
	}

	// 用户名
	$("#username").validatebox({
		required : true
	});

	// 密码
	$("#password").validatebox({
		required : true
	});

	// 用户名设置为焦点
	$('#username').focus();

	// 表单重置
	$('#resetBtn').click(function() {
		$('#loginForm')[0].reset();
	});

	// 表单提交
	$('#submitBtn').click(function() {
		login();
	});

	// 界面键盘事件
	$('#loginForm').keydown(function(e) {
		if (e.keyCode == 13) {
			$('#submitBtn').click();
		}
	});

	// 登录
	function login() {
		// $('#loginForm').attr('action', basePath + '/loginController/login');
		if ($('#loginForm').form('validate')) {
			$.messager.progress({
				text : '登录中...',
				interval : 200
			});
			$('#loginForm').submit();
		}
	}
});