<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript" charset="UTF-8" src="${pageContext.request.contextPath}/static/llsfw/js/quartz/addSimpleTrigger.js"></script>
<form id="simple_triggers_form_add" name="simple_triggers_form_add" method="post">
	<table>
		<tr>
			<td colspan="2" align="left">
				<b>作业:</b>
			</td>
		</tr>
		<tr>
			<td>作业名称:</td>
			<td>
				<input id="simple_jName_view" name="jName" style="width: 320px;" />
				&nbsp;&nbsp;
				<a id="simple_triggers_clear_job" style="cursor: pointer;">清除</a>
			</td>
		</tr>
		<tr>
			<td>作业组别:</td>
			<td>
				<input id="simple_jGroup_view" name="jGroup" style="width: 320px;" readonly="readonly" />
			</td>
		</tr>
		<tr>
			<td>作业CLASS:</td>
			<td>
				<input id="simple_jClass_view" name="jClass" style="width: 320px;" disabled="disabled" />
			</td>

		</tr>
		<tr>
			<td>作业描述:</td>
			<td>
				<input id="simple_jDesc_view" name="jDesc" style="width: 320px;" disabled="disabled" />
			</td>
		</tr>
		<tr>
			<td>作业状态:</td>
			<td>
				禁止并发:
				<input id="simple_concurrent_view" name="concurrent" type="checkbox" disabled="disabled" />
				遗漏恢复:
				<input id="simple_jobShouldRecover_view" name="jobShouldRecover" type="checkbox" disabled="disabled"
					title="属性为true，则当Quartz服务被中止后，再次启动或集群中其他机器接手任务时会尝试恢复执行之前未完成的所有任务。例如QuartzJob B，在每次00秒的时候启动，假如在03:00的任务执行完之后服务器1被中止，服务器2在05:15的时候才接手；如果shouldRecover属性为true，则服务器2会尝试着补回原来在04:00和05:00的时候应该做的任务，如果shouldRecover属性为false，则服务器2只会从06:00的时候再执行B" />
				是否耐用:
				<input id="simple_jobDurability_view" name="jobDurability" type="checkbox" disabled="disabled" />
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
				<input id="simple_tName_add" name="tName" style="width: 320px;" />
			</td>
		</tr>
		<tr>
			<td>触发器组别:</td>
			<td>
				<input id="simple_tGroup_add" name="tGroup" style="width: 320px;" value="${defaultGroup}" />
			</td>
		</tr>
		<tr>
			<td>重复次数:</td>
			<td>
				<input id="simple_triggerRepeatCount_add" name="triggerRepeatCount" style="width: 320px;" title="-1为无限,反之则执行到指定次数后自动删除该触发器"
					value="${REPEAT_INDEFINITELY}" />
			</td>
		</tr>
		<tr>
			<td>执行间隔(毫秒):</td>
			<td>
				<input id="simple_intervalInMilliseconds_add" name="intervalInMilliseconds" style="width: 320px;" title="任务每次执行的间隔,按开始时间计算" />
			</td>
		</tr>
		<tr>
			<td>优先级:</td>
			<td>
				<input id="simple_priority_add" name="priority" style="width: 320px;" value="${defaultPriority}" title="值越大,优先级越高" />
			</td>
		</tr>
		<tr>
			<td>开始时间:</td>
			<td>
				<input id="simple_triggerStartTime_add" name="triggerStartTime" style="width: 320px;" title="如果为空,则为当前时间" />
			</td>
		</tr>
		<tr>
			<td>结束时间:</td>
			<td>
				<input id="simple_triggerEndTime_add" name="triggerEndTime" style="width: 320px;" title="可以为空" />
			</td>
		</tr>
		<tr>
			<td colspan="2" align="center">
				<a id="simple_triggers_job_from_add_btn" href="#">保存</a>
			</td>
		</tr>
	</table>
</form>