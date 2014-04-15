<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript" charset="UTF-8" src="${pageContext.request.contextPath}/static/llsfw/js/function/functionEdit.js"></script>
<form id="function_form_edit" name="function_form_edit" method="post">
	<table>
		<tr>
			<td>功能代码:</td>
			<td>
				<input id="functionCode_edit" name="functionCode" value="${param.functionCode}" readonly="readonly" style="width: 150px;" />
			</td>
			<td>功能名称:</td>
			<td>
				<input id="functionName_edit" name="functionName" style="width: 150px;" />
			</td>
		</tr>
		<tr>
			<td>上级代码:</td>
			<td>
				<input id="parentFunctionCode_edit" name="parentFunctionCode" readonly="readonly" style="width: 150px;" />
			</td>
			<td>功能状态:</td>
			<td>
				<input id="status_edit" name="status" style="width: 150px;" />
			</td>
		</tr>
		<tr>
			<td>功能排序:</td>
			<td>
				<input id="sort_edit" name="sort" value="1" style="width: 150px;" />
			</td>
			<td>功能级别:</td>
			<td>
				<input id="levelNo_edit" name="levelNo" type="hidden" />
				<input id="levelNoDisplay_edit" name="levelNoDisplay" readonly="readonly" style="width: 150px;" />
			</td>
		</tr>
		<tr>
			<td>请求路径:</td>
			<td colspan="3">
				<input id="requestUrl_edit" name="requestUrl" style="width: 99%;" />
			</td>
		</tr>
		<tr>
			<td colspan="4" align="center">
				<a id="function_save_edit_btn" href="#">保存</a>
			</td>
		</tr>
	</table>
</form>