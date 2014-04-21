<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript" charset="UTF-8" src="${pageContext.request.contextPath}/static/llsfw/js/user/addUserJob.js"></script>
<form id="user_job_form_add" name="user_job_form_add" method="post">
	<input id="user_job" name="userName" type="hidden" value="${userName}" />
	<input id="jobCodeList" name="jobCodeList" type="hidden" />
</form>
<div id="job_table_param" style="padding: 5px; height: auto;">
	<a id="job_table_save_btn" href="#" class="easyui-linkbutton">保存</a>
</div>
<table id="job_table"></table>