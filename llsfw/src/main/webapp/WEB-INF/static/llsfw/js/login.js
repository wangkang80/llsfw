$(function() {

	// 用户名
	$("#userName").validatebox({
		required : true
	});

	// 密码
	$("#password").validatebox({
		required : true
	});

	// 用户名设置为焦点
	$('#userName').focus();

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
		if ($('#loginForm').form('validate')) {
			$.messager.progress({
				text : '登录中...',
				interval : 200
			});
			$.ajax({
				type : 'POST',
				url : basePath + 'loginController/loginCheck',
				data : $('#loginForm').serialize(),
				success : function(data) {
					// 关闭遮罩
					$.messager.progress('close');
					// 解析数据
					var datas = strToJson(data);
					if (datas.code == 'success') {
						$.messager.progress({
							text : '登陆成功!请稍后....',
							interval : 200
						});
						// 跳转到系统界面
						setTimeout("window.location.href='" + basePath + "mainController/toMainPage';", 1000);
					} else {
						// 登录失败,弹出提示
						showErrorMsg(datas.errorMessage);
					}
				}
			});
		}
	}
});