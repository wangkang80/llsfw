<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>

<!DOCTYPE html >
<html>
<head>
<jsp:include page="/WEB-INF/jsp/llsfw/base/head.jsp" />
<script type="text/javascript" charset="UTF-8" src="${pageContext.request.contextPath}/static/llsfw/js/main.js"></script>
<title>${appConfig.appTitle}</title>
</head>
<body class="easyui-layout" data-options="fit:true" style="overflow-y: hidden" scroll="no">
	<!-- 顶部 -->
	<div data-options="region:'north',collapsible:false" href="${pageContext.request.contextPath}/mainController/toTopPage"
		style="height: 65px; padding: 1px; overflow: hidden;"></div>
	<!-- 左侧 -->
	<div data-options="region:'west'" title="导航菜单" href="${pageContext.request.contextPath}/mainController/toLeft" style="width: 150px;"></div>
	<!-- 中间 -->
	<div data-options="region:'center'">
		<div id="maintabs" class="easyui-tabs" data-options="fit:true,border:false,plain:true" style="">
			<div title="欢迎" style="padding: 2px; overflow: hidden;" data-options="href:'${appConfig.mainPageConfig.mainPagePath}'"></div>
		</div>
	</div>
	<!-- 右侧 -->
	<div data-options="region:'east',collapsed:true" title="辅助工具" style="width: 180px;"
		href="${pageContext.request.contextPath}/mainController/toRight"></div>
	<!-- 底部 -->
	<div data-options="region:'south',border:false" href="${pageContext.request.contextPath}/mainController/toBottom"
		style="height: 25px; overflow: hidden;"></div>
</body>
</html>
