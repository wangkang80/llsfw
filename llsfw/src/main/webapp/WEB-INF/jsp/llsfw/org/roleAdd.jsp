<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript" charset="UTF-8" src="${pageContext.request.contextPath}/static/llsfw/js/org/roleAdd.js"></script>
<form id="job_role_form_add" name="job_role_form_add" method="post">
	<input id="job_role" name="jobCode" type="hidden" value="${jobCode}" />
	<input id="job_roleCodeList" name="roleCodeList" type="hidden" />
</form>
<div id="job_role_table_param" style="padding: 5px; height: auto;">
	<a id="job_role_table_save_btn" href="#" class="easyui-linkbutton">保存</a>
</div>
<table id="job_role_table"></table>