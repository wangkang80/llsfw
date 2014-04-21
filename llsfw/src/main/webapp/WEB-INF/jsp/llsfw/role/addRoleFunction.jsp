<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript" charset="UTF-8" src="${pageContext.request.contextPath}/static/llsfw/js/role/addRoleFunction.js"></script>
<div class="easyui-layout" data-options="fit:true">
	<div data-options="region:'center'">
		<table id="function_table"></table>
	</div>
	<div data-options="region:'east',split:true" style="width: 300px;">
		<table id="role_function_purview_table"></table>
	</div>
	<div data-options="region:'south',border:'false'" style="height: 35px; padding-top: 3px;">
		<center>
			<a id="function_table_save_btn" href="#" class="easyui-linkbutton">保存</a>
			<form id="role_function_form_add" name="role_function_form_add" method="post">
				<input id="roleCode_function" name="roleCode" type="hidden" value="${param.roleCode}" />
				<input id="role_functionCode" name="functionCode" type="hidden" />
				<input id="role_purviewCodes" name="purviewCodes" type="hidden" />
			</form>
		</center>
	</div>
</div>