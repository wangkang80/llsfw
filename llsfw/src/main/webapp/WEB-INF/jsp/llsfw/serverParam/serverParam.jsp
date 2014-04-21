<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<html>
<head>
<jsp:include page="/WEB-INF/jsp/llsfw/base/head.jsp" />
<script type="text/javascript" charset="UTF-8" src="${pageContext.request.contextPath}/static/llsfw/js/serverParam/serverParam.js"></script>
<title>参数设定</title>
</head>
<body>
	<div id="server_param_search" style="padding: 5px; height: auto;">
		参数代码:
		<input type="text" id="parametersCode" name="parametersCode" size="10" />
		参数描述:
		<input type="text" id="parametersDesc" name="parametersDesc" size="10" />
		<br />
		<a id="serverParamSearchBtn" href="#" class="easyui-linkbutton">查询</a>
		<shiro:hasPermission name="serviceParamController:add">
			<a id="serverParamAddBtn" href="#" class="easyui-linkbutton">新增</a>
		</shiro:hasPermission>
		<shiro:hasPermission name="serviceParamController:edit">
			<a id="serverParamEditBtn" href="#" class="easyui-linkbutton">修改</a>
		</shiro:hasPermission>
		<shiro:hasPermission name="serviceParamController:delete">
			<a id="serverParamDeleteBtn" href="#" class="easyui-linkbutton">删除</a>
		</shiro:hasPermission>
	</div>
	<table id="serverParamTable"></table>
	<div id="addServerParamWindows"></div>
	<div id="editServerParamWindows"></div>
</body>
</html>