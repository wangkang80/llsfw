<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript" charset="UTF-8" src="${pageContext.request.contextPath}/static/llsfw/js/serverParam/serverParamEdit.js"></script>
<form id="parameters_form_edit" name="parameters_form_edit" method="post">
	参数代码:
	<input id="parametersCode_edit" name="parametersCode" value="${param.PARAMETERS_CODE}" style="width: 150px; margin: 5px;"
		readonly="readonly" />
	<br />
	参数取值:
	<input id="parametersValue_edit" name="parametersValue" style="width: 150px; margin: 5px;" />
	<br />
	参数描述:
	<input id="parametersDesc_edit" name="parametersDesc" style="width: 150px; margin: 5px;" />
	<br />
	<center style="margin: 5px;">
		<a id="parameters_edit_btn" href="#">保存</a>
	</center>
</form>