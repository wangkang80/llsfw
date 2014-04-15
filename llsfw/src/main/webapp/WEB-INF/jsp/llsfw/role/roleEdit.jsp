<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript" charset="UTF-8" src="${pageContext.request.contextPath}/static/llsfw/js/role/roleEdit.js"></script>
<form id="role_form_edit" name="role_form_edit" method="post">
	角色代码:
	<input id="roleCode_edit" name="roleCode" value="${param.roleCode}" readonly="readonly" style="width: 150px; margin: 5px;" />
	<br />
	角色名称:
	<input id="roleName_edit" name="roleName" style="width: 150px; margin: 5px;" />
	<br />
	<center style="margin: 5px;">
		<a id="role_edit_btn" href="#">保存</a>
	</center>
</form>