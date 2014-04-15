<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript" charset="UTF-8" src="${pageContext.request.contextPath}/static/llsfw/js/user/addUserRole.js"></script>
<form id="user_role_form_add" name="user_role_form_add" method="post">
	<input id="user_role" name="loginName" type="hidden" value="${param.loginName}" />
	<input id="roleCodeList" name="roleCodeList" type="hidden" />
</form>
<div id="role_table_param" style="padding: 5px; height: auto;">
	<a id="role_table_save_btn" href="#" class="easyui-linkbutton">保存</a>
</div>
<table id="role_table"></table>