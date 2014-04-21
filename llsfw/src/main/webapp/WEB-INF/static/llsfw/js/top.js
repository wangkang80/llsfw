/**
 * 
 */
$(function() {
	// 修改密码
	$('#changePswd_menu').click(function() {
		// 弹出密码修改窗口
		$('#change_pswd_window').window({
			title : '修改密码',
			collapsible : false,
			minimizable : false,
			maximizable : false,
			resizable : false,
			modal : true,
			width : 250,
			height : 180,
			href : basePath + 'mainController/toChangePswd'
		});
	});

	// 退出
	$('#login_out_menu').click(function() {
		$.messager.confirm('警告', '是否确定退出系统?', function(r) {
			if (r) {
				$.messager.progress({
					text : '请稍后...',
					interval : 200
				});
				top.location.href = basePath + 'logout';
			}
		});
	});
});