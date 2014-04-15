<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript" charset="UTF-8" src="${pageContext.request.contextPath}/static/llsfw/js/quartz/executionHistory.js"></script>
<div id="execution_history_search">
	作业组:
	<input type="text" id="execution_history_job_group" name="execution_history_job_group" size="20" value="${jonGroup}" />
	作业名:
	<input type="text" id="execution_history_job_name" name="execution_history_job_name" size="20" value="${jobName}" />
	触发器组:
	<input type="text" id="execution_history_trigger_group" name="execution_history_trigger_group" size="20" value="${triggerGroup}" />
	触发器名:
	<input type="text" id="execution_history_trigger_name" name="execution_history_trigger_name" size="20" value="${triggerName}" />
	状态:
	<input id="execution_status" name="execution_status" />
	<a href="#" class="easyui-linkbutton" data-options="plain:true" id="execution_history_search_btn">查询(精确)</a>
</div>
<table id="execution_history_table"></table>
<div id="execution_history_errorMsg_dialog"></div>