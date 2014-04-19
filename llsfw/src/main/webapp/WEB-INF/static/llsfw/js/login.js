$(function() {

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
		$('#loginForm').attr('action', basePath + '/loginController/login');
		$('#loginForm').form('submit', {
			onSubmit : function() {// 提交前置事件
				var isValid = $(this).form('validate');
				if (isValid) {// 验证通过,弹出遮罩
					$.messager.progress({
						text : '登录中...',
						interval : 200
					});
				}
				return isValid; // return false will stop the form submission
			},
			success : function(data) {
				// 关闭遮罩
				$.messager.progress('close');
				// 解析数据
				var datas = data;
				if (datas == 'success') {
					$.messager.progress({
						text : '登陆成功!请稍后....',
						interval : 200
					});
					// 跳转到系统界面
					setTimeout("window.location.href='" + basePath + "mainController/toMainPage';", 1000);
				} else {
					// 登录失败,弹出提示
					showErrorMsg(datas);
				}
			}
		});
	}
});