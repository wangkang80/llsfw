/**
 * 设置未来(全局)的AJAX请求默认选项 主要设置了AJAX请求遇到Session过期的情况
 */
$.ajaxSetup({
	cache : false,
	async : false,
	type : 'POST',
	complete : function(xhr, status) {
		var sessionStatus = xhr.getResponseHeader('sessionstatus');
		var p = xhr.getResponseHeader('path');
		if (sessionStatus == 'timeout') {
			alert("由于您长时间没有操作, session已过期, 请重新登录.");
			var top = getTopWinow();
			top.location.href = p;
		} else if (sessionStatus == 'noAuth') {
			alert("您访问了一个未经授权的资源,请使用有权限访问该资源的账号登录系统.");
			var top = getTopWinow();
			top.location.href = p;
		}
	},
	error : function(jqXHR, textStatus, errorThrown) {
		showErrorWindow(jqXHR.responseText);
	}
});

/**
 * 显示错误信息
 * 
 * @param errorMessage
 *            错误信息
 */
function showErrorWindow(errorMessage) {
	$('#ajaxRequestErrWindow').window({
		title : '(错误/警告)提示',
		content : errorMessage,
		width : '600',
		height : '400',
		collapsible : false,
		minimizable : false,
		maximizable : false,
		closable : true,
		resizable : false,
		draggable : false
	});
}

/**
 * 弹出提示信息
 * 
 * @param msg
 *            消息
 */
function showErrorMsg(msg) {
	$.messager.show({
		title : '提示',
		width : 200,
		height : 80,
		msg : '<center>' + msg + '</center>',
		timeout : 2000,
		showType : null,
		style : {
			right : '',
			top : document.body.scrollTop + document.documentElement.scrollTop,
			bottom : ''
		}
	});
}

/**
 * 弹出提示信息
 * 
 * @param msg
 *            消息
 */
function showInfoMsg(msg) {
	$.messager.show({
		title : '提示',
		width : 200,
		height : 80,
		msg : '<center>' + msg + '</center>',
		timeout : 2000,
		showType : null,
		style : {
			right : '',
			top : document.body.scrollTop + document.documentElement.scrollTop,
			bottom : ''
		}
	});
}

/**
 * <p>
 * Description: 获得指定参数
 * </p>
 * 
 * @param parametersCode
 *            参数代码
 * @return 参数对象
 */
function getServerParam(path, parametersCode) {
	var parameters = null;
	$.ajax({
		type : 'POST',
		url : path + '/pageInit/getServerParam?parametersCode=' + parametersCode,
		success : function(data) {
			// 解析数据
			parameters = strToJson(data);
		}
	});
	return parameters;
}

/**
 * 在页面中任何嵌套层次的窗口中获取顶层窗口
 * 
 * @return 当前页面的顶层窗口对象
 */
function getTopWinow() {
	var p = window;
	while (p != p.parent) {
		p = p.parent;
	}
	return p;
}

function strToJson(str) {
	/**
	 * 由AnnotationMethodHandlerAdapter更换到RequestMappingHandlerAdapter后无需手动将字符串转为json对象
	 * 这里更改为直接返回传入的值,什么都不做 同时,这个方法转为弃用
	 * 
	 * 兼容string和object
	 */
	// var json = (new Function("return " + str))();
	// return json;
	if (typeof (str) == "string") {
		var json = (new Function("return " + str))();
		return json;
	}
	return str;
}

/**
 * 格式化功能级别
 */
function levelDisplay(value) {
	if (value) {
		if (value == '1') {
			return '系统(' + value + ')';
		} else if (value == '2') {
			return '菜单(' + value + ')';
		} else if (value == '3') {
			return '功能(' + value + ')';
		} else if (value == 'PURVIEW') {
			return '权限(' + value + ')';
		} else {
			return '未知(' + value + ')';
		}
	}
}

// $.ajaxSetup({
// cache : false,
// async : false,
// error : function(jqXHR, textStatus, errorThrown) {
// switch (jqXHR.status) {
// case (500):
// alert("错误500");
// break;
// case (401):
// alert("错误401");
// break;
// case (403):
// alert("错误403");
// break;
// case (408):
// alert("错误408");
// break;
// default:
// alert("未知错误");
// }
// }
// });
