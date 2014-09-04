<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript" charset="UTF-8" src="${pageContext.request.contextPath}/static/llsfw/js/quartz/addCronTrigger.js"></script>
<form id="cron_triggers_form_add" name="cron_le_triggers_form_add" method="post">
	<table>
		<tr>
			<td colspan="2" align="left">
				<b>作业:</b>
			</td>
		</tr>
		<tr>
			<td>作业名称:</td>
			<td>
				<input id="cron_jName_view" name="jName" style="width: 320px;" />
				&nbsp;&nbsp;
				<a id="cron_triggers_clear_job" style="cursor: pointer;">清除</a>
			</td>
		</tr>
		<tr>
			<td>作业组别:</td>
			<td>
				<input id="cron_jGroup_view" name="jGroup" style="width: 320px;" readonly="readonly" />
			</td>
		</tr>
		<tr>
			<td>作业CLASS:</td>
			<td>
				<input id="cron_jClass_view" name="jClass" style="width: 320px;" disabled="disabled" />
			</td>

		</tr>
		<tr>
			<td>作业描述:</td>
			<td>
				<input id="cron_jDesc_view" name="jDesc" style="width: 320px;" disabled="disabled" />
			</td>
		</tr>
		<tr>
			<td>作业状态:</td>
			<td>
				禁止并发:
				<input id="cron_concurrent_view" name="concurrent" type="checkbox" disabled="disabled" />
				遗漏恢复:
				<input id="cron_jobShouldRecover_view" name="jobShouldRecover" type="checkbox" disabled="disabled"
					title="属性为true，则当Quartz服务被中止后，再次启动或集群中其他机器接手任务时会尝试恢复执行之前未完成的所有任务。例如QuartzJob B，在每次00秒的时候启动，假如在03:00的任务执行完之后服务器1被中止，服务器2在05:15的时候才接手；如果shouldRecover属性为true，则服务器2会尝试着补回原来在04:00和05:00的时候应该做的任务，如果shouldRecover属性为false，则服务器2只会从06:00的时候再执行B" />
				是否持久:
				<input id="cron_jobDurability_view" name="jobDurability" type="checkbox" disabled="disabled" />
			</td>
		</tr>
		<tr>
			<td colspan="2" align="left">
				<b>触发器:</b>
			</td>
		</tr>
		<tr>
			<td>触发器名称:</td>
			<td>
				<input id="cron_tName_add" name="tName" style="width: 320px;" />
			</td>
		</tr>
		<tr>
			<td>触发器组别:</td>
			<td>
				<input id="cron_tGroup_add" name="tGroup" style="width: 320px;" value="${defaultGroup}" />
			</td>
		</tr>
		<tr>
			<td>CRON表达式:</td>
			<td>
				<input id="cron_cronExpression_add" name="cronExpression" style="width: 320px;" />
				&nbsp;&nbsp;
				<a id="cron_triggers_generate" style="cursor: pointer;">生成</a>
			</td>
		</tr>
		<tr>
			<td>优先级:</td>
			<td>
				<input id="cron_priority_add" name="priority" style="width: 320px;" value="${defaultPriority}" title="值越大,优先级越高" />
			</td>
		</tr>
		<tr>
			<td>开始时间:</td>
			<td>
				<input id="cron_triggerStartTime_add" name="triggerStartTime" style="width: 320px;" title="如果为空,则为当前时间" />
			</td>
		</tr>
		<tr>
			<td>结束时间:</td>
			<td>
				<input id="cron_triggerEndTime_add" name="triggerEndTime" style="width: 320px;" title="可以为空" />
			</td>
		</tr>
		<tr>
			<td colspan="2" align="center">
				<a id="cron_triggers_job_from_add_btn" href="#">保存</a>
			</td>
		</tr>
	</table>
</form>
<div id="cron_triggers_generate_window" style=""></div>