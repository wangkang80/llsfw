<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
		参数分类:
		<input id="parametersTypeCode" name="parametersTypeCode" />
		<br />
		<a id="serverParamSearchBtn" href="#" class="easyui-linkbutton">查询</a>
		<a id="serverParamAddBtn" href="#" class="easyui-linkbutton">新增</a>
		<a id="serverParamEditBtn" href="#" class="easyui-linkbutton">修改</a>
		<a id="serverParamDeleteBtn" href="#" class="easyui-linkbutton">删除</a>
	</div>
	<table id="serverParamTable"></table>
	<div id="addServerParamWindows"></div>
	<div id="editServerParamWindows"></div>
</body>
</html>