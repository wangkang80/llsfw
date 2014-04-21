<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="/WEB-INF/jsp/llsfw/base/head.jsp" />
<script type="text/javascript" charset="UTF-8" src="${pageContext.request.contextPath}/static/llsfw/js/function/functionMain.js"></script>
<title>功能维护</title>
</head>
<body class="easyui-layout" data-options="fit:true">
	<div data-options="region:'center'">
		<div id="function_search" style="padding: 5px; height: auto;">
			<a id="function_warring_btn" href="#" class="easyui-linkbutton">注意</a>
			<a id="function_search_btn" href="#" class="easyui-linkbutton">查询</a>
			<shiro:hasPermission name="functionController:add">
				<a id="function_add_btn" href="#" class="easyui-linkbutton">新增</a>
			</shiro:hasPermission>
			<shiro:hasPermission name="functionController:edit">
				<a id="function_edit_btn" href="#" class="easyui-linkbutton">修改</a>
			</shiro:hasPermission>
			<shiro:hasPermission name="functionController:delete">
				<a id="function_delete_btn" href="#" class="easyui-linkbutton">删除</a>
			</shiro:hasPermission>
		</div>
		<table id="function_search_table"></table>
		<div id="function_add_window"></div>
		<div id="function_edit_window"></div>
	</div>
	<div data-options="region:'east',split:true" style="width: 450px;">
		<div id="function_purview_search" style="padding: 5px; height: auto;">
			<shiro:hasPermission name="functionController:purview_add">
				<a id="function_purview_add_btn" href="#" class="easyui-linkbutton">新增</a>
			</shiro:hasPermission>
			<shiro:hasPermission name="functionController:purview_edit">
				<a id="function_purview_edit_btn" href="#" class="easyui-linkbutton">修改</a>
			</shiro:hasPermission>
			<shiro:hasPermission name="functionController:purview_delete">
				<a id="function_purview_delete_btn" href="#" class="easyui-linkbutton">删除</a>
			</shiro:hasPermission>
		</div>
		<table id="function_purview_search_table"></table>
		<div id="function_purview_add_windwos"></div>
		<div id="function_purview_edit_windwos"></div>
		<div id="function_warring_windows"></div>
	</div>
</body>
</html>