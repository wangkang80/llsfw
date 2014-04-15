<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="/WEB-INF/jsp/llsfw/base/head.jsp" />
<script type="text/javascript" charset="UTF-8" src="${pageContext.request.contextPath}/static/llsfw/js/function/functionMain.js"></script>
<title>功能维护</title>
</head>
<body>
	<div id="function_search" style="padding: 5px; height: auto;">
		<font style="color: red;">
			注意:
			<br />
			*.将节点状态更改为[停用],会自动更新子节点的状态变为[停用]
			<br />
			*.将节点状态更改为[启用],会自动更新父节点的状态变为[启用]
			<br />
			*.删除节点,将会自动删除子节点,并且与之关联的角色数据也会删除
		</font>
		<br />
		<a id="function_search_btn" href="#" class="easyui-linkbutton">查询</a>
		<a id="function_add_btn" href="#" class="easyui-linkbutton">新增</a>
		<a id="function_edit_btn" href="#" class="easyui-linkbutton">修改</a>
		<a id="function_delete_btn" href="#" class="easyui-linkbutton">删除</a>
	</div>
	<table id="function_search_table"></table>
	<div id="function_add_window"></div>
	<div id="function_edit_window"></div>
</body>
</html>