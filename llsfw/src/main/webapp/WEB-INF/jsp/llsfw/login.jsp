<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="/WEB-INF/jsp/llsfw/base/head.jsp" />
<script type="text/javascript" charset="UTF-8" src="${pageContext.request.contextPath}/static/llsfw/js/login.js"></script>
<title>${appConfig.appTitle}-登陆</title>
</head>
<body id="loginPageBody" background="${pageContext.request.contextPath}/${appConfig.loginPageConfig.loginPageBackgroundImagePath}">
	<div
		style="width: 100%; height: 62px; background-image: url(${pageContext.request.contextPath}/${appConfig.loginPageConfig.loginPageTileBackgroundImagePath});">
		<img src="${pageContext.request.contextPath}/${appConfig.loginPageConfig.loginPageLogoImagePath}">
	</div>
	<input type="hidden" id="msg" value="${rv}" />
	<div id="loginWindow" class="easyui-window"
		data-options="
    	title:'登录',
    	collapsible:false,
    	minimizable:false,
    	maximizable:false,
    	closable:false,
    	resizable:false,
    	draggable:false"
		style="width: 300px; height: 165px;">
		<form id="loginForm" method="post">
			<div style="padding: 0px 50px 0px 50px">
				<table width="100%" border="0">
					<tr>
						<td width="50px" align="right">用户名:</td>
						<td>
							<input id="username" name="username" type="text" style="width: 96%;" />
						</td>
					</tr>
					<tr>
						<td align="right">密码:</td>
						<td>
							<input id="password" name="password" type="password" style="width: 96%;" />
						</td>
					</tr>
				</table>
			</div>
			<div class="separator"></div>
			<div class="toolbar">
				<a id="submitBtn" href="javascript:void(0)" class="easyui-linkbutton">登录</a>
				<a id="resetBtn" href="javascript:void(0)" class="easyui-linkbutton">重置</a>
			</div>
		</form>
	</div>
	<div id="msgDiv"></div>
</body>
</html>