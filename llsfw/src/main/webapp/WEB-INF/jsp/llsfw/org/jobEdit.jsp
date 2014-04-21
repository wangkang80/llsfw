<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript" charset="UTF-8" src="${pageContext.request.contextPath}/static/llsfw/js/org/jobEdit.js"></script>
<form id="job_form_edit" name="job_form_edit" method="post">
	<table>
		<tr>
			<td>岗位代码:</td>
			<td>
				<input id="jobCode_edit" name="jobCode" value="${jobCode}" readonly="readonly" style="width: 150px;" />
			</td>
			<td>岗位名称:</td>
			<td>
				<input id="jobName_edit" name="jobName" style="width: 150px;" />
			</td>
		</tr>
		<tr>
			<td>所属组织:</td>
			<td colspan="3">
				<input id="job_rogCode_edit" name="orgCode" style="width: 150px;" />
			</td>
		</tr>
		<tr>
			<td colspan="4" align="center">
				<a id="job_save_edit_btn" href="#">保存</a>
			</td>
		</tr>
	</table>
</form>