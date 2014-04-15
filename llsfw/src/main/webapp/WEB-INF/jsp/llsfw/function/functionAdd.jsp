<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript" charset="UTF-8" src="${pageContext.request.contextPath}/static/llsfw/js/function/functionAdd.js"></script>
<form id="function_form_add" name="function_form_add" method="post">
	<table>
		<tr>
			<td>功能代码:</td>
			<td>
				<input id="functionCode_add" name="functionCode" style="width: 150px;" />
			</td>
			<td>功能名称:</td>
			<td>
				<input id="functionName_add" name="functionName" style="width: 150px;" />
			</td>
		</tr>
		<tr>
			<td>上级代码:</td>
			<td>
				<input id="parentFunctionCode_add" name="parentFunctionCode" value="${param.parentFunctionCode}" readonly="readonly"
					style="width: 150px;" />
			</td>
			<td>功能状态:</td>
			<td>
				<input id="status_add" name="status" style="width: 150px;" />
			</td>
		</tr>
		<tr>
			<td>功能排序:</td>
			<td>
				<input id="sort_add" name="sort" value="1" style="width: 150px;" />
			</td>
			<td>功能级别:</td>
			<td>
				<input id="levelNo_add" name="levelNo" type="hidden" value="${param.levelNo+1}" />
				<input id="levelNoDisplay_add" name="levelNoDisplay" readonly="readonly" style="width: 150px;" />
			</td>
		</tr>
		<tr>
			<td>请求路径:</td>
			<td colspan="3">
				<input id="requestUrl_add" name="requestUrl" style="width: 99%;" />
			</td>
		</tr>
		<tr>
			<td colspan="4" align="center">
				<a id="function_save_add_btn" href="#">保存</a>
			</td>
		</tr>
	</table>
</form>