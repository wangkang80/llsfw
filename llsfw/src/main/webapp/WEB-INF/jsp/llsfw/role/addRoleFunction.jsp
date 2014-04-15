<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript" charset="UTF-8" src="${pageContext.request.contextPath}/static/llsfw/js/role/addRoleFunction.js"></script>
<form id="role_function_form_add" name="role_function_form_add" method="post">
	<input id="roleCode_function" name="roleCode" type="hidden" value="${param.roleCode}" />
	<input id="functionCodeList" name="functionCodeList" type="hidden" />
</form>
<div id="function_table_param" style="padding: 5px; height: auto;">
	<a id="function_table_save_btn" href="#" class="easyui-linkbutton">保存</a>
</div>
<table id="function_table"></table>