<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="/WEB-INF/jsp/llsfw/base/head.jsp" />
<jsp:include page="/WEB-INF/jsp/llsfw/base/head_easyui_expand.jsp" />
<script type="text/javascript" charset="UTF-8" src="${pageContext.request.contextPath}/static/llsfw/js/user/userMain.js"></script>
<title>用户维护</title>
</head>
<body class="easyui-layout" data-options="fit:true">
	<div data-options="region:'center'">
		<div id="user_table_param" style="padding: 5px; height: auto;">
			<a id="user_table_search_btn" href="#" class="easyui-linkbutton">查询</a>
			<a id="user_table_add_btn" href="#" class="easyui-linkbutton">新增</a>
			<a id="user_table_edit_btn" href="#" class="easyui-linkbutton">修改</a>
			<a id="user_table_set_defpswd_btn" href="#" class="easyui-linkbutton">密码初始化</a>
		</div>
		<table id="user_table"></table>
		<div id="user_window_add"></div>
		<div id="user_window_edit"></div>
	</div>
	<div data-options="region:'east',split:true" style="width: 450px;">
		<div id="user_role_table_param" style="padding: 5px; height: auto;">
			<a id="user_role_table_add_btn" href="#" class="easyui-linkbutton">新增</a>
			<a id="user_role_table_delete_btn" href="#" class="easyui-linkbutton">删除</a>
		</div>
		<table id="user_role_table"></table>
		<div id="user_role_window_add"></div>
	</div>
</body>
</html>