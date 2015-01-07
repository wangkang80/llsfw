$(function() {

	/**
	 * 当session超时的时候,会在当前页面刷新出登录界面,但是由于起初的设计原因和shiro的跳转策略问题,
	 * 重新登陆后跳转的并不完美,则诞生如下代码,当弹出登陆界面的时候,刷新主页面,让整个系统跳转到登陆界面中,
	 * 登陆后则重新开始.
	 */
	try {
		// parent.refreshTab();
		parent.reloadApp();
	} catch (e) {
	}

	$('#loginWindow').window({
		title : '登录',
		collapsible : false,
		minimizable : false,
		maximizable : false,
		closable : false,
		resizable : false,
		draggable : false,
		width : 300,
		height : 165
	});

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

	$('#submitBtn').linkbutton({});

	$('#resetBtn').linkbutton({});

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
		if ($('#loginForm').form('validate')) {
			$.messager.progress({
				text : '登录中...',
				interval : 200
			});
			$('#loginForm').submit();
		}
	}
});