<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript" charset="UTF-8" src="${pageContext.request.contextPath}/static/llsfw/js/quartz/addJobDetail.js"></script>
<form id="job_detail_form_add" name="job_detail_form_add" method="post">
	<table>
		<tr>
			<td>作业名称:</td>
			<td>
				<input id="jName_add" name="jName" style="width: 250px;" />
			</td>
		</tr>
		<tr>
			<td>作业组别:</td>
			<td>
				<input id="jGroup_add" name="jGroup" style="width: 250px;" value="${defaultGroup}" />
			</td>
		</tr>
		<tr>
			<td>作业CLASS:</td>
			<td>
				<input id="jClass_add" name="jClass" style="width: 250px;" />
			</td>

		</tr>
		<tr>
			<td>作业描述:</td>
			<td>
				<input id="jDesc_add" name="jDesc" style="width: 250px;" />
			</td>
		</tr>
		<tr>
			<td>禁止并发:</td>
			<td>
				<font color="red">
					需在作业上添加注解
					<br />
					@DisallowConcurrentExecution,并再次保存
				</font>
			</td>
		</tr>
		<tr>
			<td>遗漏恢复:</td>
			<td>
				<input id="jobShouldRecover_add" name="jobShouldRecover" type="checkbox"
					title="属性为true，则当Quartz服务被中止后，再次启动或集群中其他机器接手任务时会尝试恢复执行之前未完成的所有任务。例如QuartzJob B，在每次00秒的时候启动，假如在03:00的任务执行完之后服务器1被中止，服务器2在05:15的时候才接手；如果shouldRecover属性为true，则服务器2会尝试着补回原来在04:00和05:00的时候应该做的任务，如果shouldRecover属性为false，则服务器2只会从06:00的时候再执行B" />
			</td>
		</tr>
		<tr>
			<td>是否耐用:</td>
			<td>
				<input id="jobDurability_add" name="jobDurability" type="checkbox" checked="checked" />
			</td>
		</tr>
		<tr>
			<td colspan="4" align="center">
				<a id="job_detail_form_add_btn" href="#">保存</a>
				<a id="job_detail_form_delete_btn" href="#">删除</a>
			</td>
		</tr>
	</table>
</form>