<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<script type="text/javascript" charset="UTF-8" src="${pageContext.request.contextPath}/static/llsfw/js/top.js"></script>
<div
	style="width: 100%; height: 62px; background-image: url(${pageContext.request.contextPath}/${appConfig.topPageConfig.topPageBackgroundImagePath});">
	<div style="float: left; width: 100%; height: 100%;">
		<img src="${pageContext.request.contextPath}/${appConfig.topPageConfig.topPageLogoImagePath}" />
	</div>
	<div style="text-align: right; padding: 10px; position: absolute; right: 0px; bottom: 0px;">
		<span style="color: #CC33FF">当前用户:</span>
		<span style="color: #666633">${userName}</span>
		<br />
		<a href="javascript:void(0);" class="easyui-menubutton" menu="#controll_panel_menu">控制面板</a>
	</div>
	<div id="controll_panel_menu" style="width: 100px; display: none;">
		<div id="changePswd_menu">修改密码</div>
		<div class="menu-sep"></div>
		<div id="login_out_menu">退出系统</div>
	</div>
	<div id="change_pswd_window"></div>
</div>
