<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="/WEB-INF/jsp/llsfw/base/head.jsp" />
<script type="text/javascript" charset="UTF-8" src="${pageContext.request.contextPath}/static/llsfw/js/role/roleMain.js"></script>
<title>角色维护</title>
</head>
<body class="easyui-layout" data-options="fit:true">
	<div data-options="region:'center'">
		<div id="role_table_param" style="padding: 5px; height: auto;">
			<a id="role_table_search_btn" href="#" class="easyui-linkbutton">查询</a>
			<a id="role_table_add_btn" href="#" class="easyui-linkbutton">新增</a>
			<a id="role_table_edit_btn" href="#" class="easyui-linkbutton">修改</a>
			<a id="role_table_delete_btn" href="#" class="easyui-linkbutton">删除</a>
		</div>
		<table id="role_table"></table>
		<div id="role_window_add"></div>
		<div id="role_window_edit"></div>
	</div>
	<div data-options="region:'east',split:true" style="width: 450px;">
		<div id="role_function_table_param" style="padding: 5px; height: auto;">
			<a id="role_function_table_add_btn" href="#" class="easyui-linkbutton">新增</a>
			<a id="role_function_table_delete_btn" href="#" class="easyui-linkbutton">删除</a>
		</div>
		<table id="role_function_table"></table>
		<div id="role_function_window_add"></div>
	</div>
</body>
</html>