<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript" charset="UTF-8" src="${pageContext.request.contextPath}/static/llsfw/js/user/addUserFunction.js"></script>

<div id="add_user_function_tree"></div>
<form id="add_user_function_form_edit" name="add_user_function_form_edit" method="post">
	<input type="hidden" id="add_user_function_userName" name="userName" value="${userName}">
	<input id="add_user_function_function_code" name="functionCode" type="hidden" />
	<input id="add_user_function_purview_code" name="purviewCode" type="hidden" />
</form>