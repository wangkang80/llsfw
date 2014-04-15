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
				$.ajax({
					type : 'POST',
					url : basePath + 'mainController/loginOut',
					success : function(data) {
						// 关闭遮罩
						$.messager.progress('close');
						// 解析数据
						var datas = strToJson(data);
						if ('success' == datas.code) {
							var top = getTopWinow();
							top.location.href = basePath;
						} else {
							alert('操作失败');
						}
					}
				});
			}
		});
	});
});