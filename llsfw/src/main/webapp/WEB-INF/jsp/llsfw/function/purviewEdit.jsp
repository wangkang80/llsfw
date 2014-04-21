<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript" charset="UTF-8" src="${pageContext.request.contextPath}/static/llsfw/js/function/purviewEdit.js"></script>
<form id="function_purview_form_edit" name="function_purview_form_edit" method="post">
	功能代码:
	<input id="function_code_edit" name="functionCode" style="width: 150px; margin: 5px;" value="${functionCode}" readonly="readonly" />
	<br />
	权限代码:
	<input id="purviewCode_edit" name="purviewCode" style="width: 150px; margin: 5px;" value="${purviewCode}" readonly="readonly" />
	<br />
	权限描述:
	<input id="purviewName_edit" name="purviewName" style="width: 150px; margin: 5px;" />
	<br />
	<center style="margin: 5px;">
		<a id="purview_edit_btn" href="#">保存</a>
	</center>
</form>