<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<br />
<b>计划任务状态(单节点操作):</b>
<table cellpadding="0" cellspacing="0" border="0" style="width: 100%; height: auto; padding: 3px;">
	<c:if test="${SchedulerRunningStatus!=null}">
		<tr>
			<td width="80px" style="border: solid 1px #B4B4B4; word-break: break-all; WORD-WRAP: break-word;" title="只能停止单节点上的计划任务,需各个节点依次停止">是否停止:</td>
			<td width="165px" style="border: solid 1px #B4B4B4; word-break: break-all; WORD-WRAP: break-word;">
				${SchedulerRunningStatus.isShutdown}
				<c:if test="${SchedulerRunningStatus.isShutdown==false}">
					<a id="scheduler_shutdown_btn" href="#" class="easyui-linkbutton">停止(慎重)</a>
					<script type="text/javascript">
						$('#scheduler_shutdown_btn').click(
								function() {
									$.messager.confirm('警告', '停止计划任务后,将无法再次启动,需重启应用才能恢复正常,请确认是会否操作(如暂时停止,可选择待机)?', function(r) {
										if (r) {
											$.messager.defaults = {
												ok : "是",
												cancel : "否"
											};
											$.messager.confirm('提示', '是否等待现有作业执行完成?', function(r) {
												$.messager.progress({
													text : '停止中...',
													interval : 200
												});
												$.ajax({
													type : 'POST',
													url : basePath + 'quartzController/schedulerShutdown?waitForJobsToComplete=' + r,
													success : function(data) {

														// 关闭遮罩
														$.messager.progress('close');

														// 解析数据
														var datas = strToJson(data);
														if (datas.code == '1') {
															$('#schedulerDetailPanel').panel('refresh',
																	basePath + 'quartzController/getSchedulerDetail');
														} else {
															alert('停止失败..');
														}
													}
												});
											});
										}
									});
								});
					</script>
				</c:if>
			</td>
		</tr>
		<tr>
			<td width="80px" style="border: solid 1px #B4B4B4; word-break: break-all; WORD-WRAP: break-word;"
				title="只能待机单节点上的计划任务,需各个节点依次停止,如果只是想让计划任务停止执行的话,可选择[暂停所有]功能">是否待机:</td>
			<td width="165px" style="border: solid 1px #B4B4B4; word-break: break-all; WORD-WRAP: break-word;">
				${SchedulerRunningStatus.isInStandbyMode}
				<c:if test="${SchedulerRunningStatus.isInStandbyMode==false}">
					<a id="scheduler_standby_btn" href="#" class="easyui-linkbutton">待机</a>
					<script type="text/javascript">
						$('#scheduler_standby_btn').click(
								function() {
									$.messager.confirm('警告', '是否要暂时停止计划任务执行?', function(r) {
										if (r) {
											$.messager.progress({
												text : '待机中...',
												interval : 200
											});
											$.ajax({
												type : 'POST',
												url : basePath + 'quartzController/schedulerStandby',
												success : function(data) {

													// 关闭遮罩
													$.messager.progress('close');

													// 解析数据
													var datas = strToJson(data);
													if (datas.code == '1') {
														$('#schedulerDetailPanel').panel('refresh',
																basePath + 'quartzController/getSchedulerDetail');
													} else {
														alert('待机失败..');
													}
												}
											});
										}
									});
								});
					</script>
				</c:if>
			</td>
		</tr>
		<tr>
			<td width="80px" style="border: solid 1px #B4B4B4; word-break: break-all; WORD-WRAP: break-word;">是否启动:</td>
			<td width="165px" style="border: solid 1px #B4B4B4; word-break: break-all; WORD-WRAP: break-word;">
				${SchedulerRunningStatus.isInStandbyMode?"false":"true"}
				<!-- quart中没有判断是否触发器正在运行的API(isStarted只是表示是否正常启动,并不代表当前计划任务的状态) -->
				<!-- 所以只有在计划任务为[待机]状态,并且不是[停止]状态的时候,才能执行[启动]计划任务 -->
				<!-- 已经[停止]的计划任务是无法再次重新[启动]的,需重启应用后才能恢复 -->
				<c:if test="${SchedulerRunningStatus.isInStandbyMode==true&&SchedulerRunningStatus.isShutdown==false}">
					<a id="scheduler_start_btn" href="#" class="easyui-linkbutton">启动</a>
					<script type="text/javascript">
						$('#scheduler_start_btn').click(
								function() {
									$.messager.prompt('提示', '是否要启动计划任务?<br/>请设置延迟启动时间,大于0则延迟启动,反之则立即启动', function(r) {
										var time = r;
										var re = /^[0-9]\d*$/;
										if (!re.test(time)) {
											showErrorMsg('延迟启动时间,请填入正整数');
											$('#schedulerDetailPanel').panel('refresh', basePath + 'quartzController/getSchedulerDetail');
										} else {
											$.messager.progress({
												text : '启动中...',
												interval : 200
											});
											$.ajax({
												type : 'POST',
												url : basePath + 'quartzController/schedulerStart?seconds=' + time,
												success : function(data) {

													// 关闭遮罩
													$.messager.progress('close');

													// 解析数据
													var datas = strToJson(data);
													if (datas.code == '1') {
														$('#schedulerDetailPanel').panel('refresh',
																basePath + 'quartzController/getSchedulerDetail');
													} else {
														alert('待机失败..');
													}
												}
											});
										}
									});
								});
					</script>
				</c:if>
			</td>
		</tr>
	</c:if>
	<c:if test="${schedulerStateList!=null}">
		<c:forEach items="${schedulerStateList}" var="item">
			<tr>
				<td width="80px" style="border: solid 1px #B4B4B4; word-break: break-all; WORD-WRAP: break-word;">计划任务:</td>
				<td width="165px" style="border: solid 1px #B4B4B4; word-break: break-all; WORD-WRAP: break-word;">${item.SCHED_NAME}</td>
			</tr>
			<tr>
				<td width="80px" style="border: solid 1px #B4B4B4; word-break: break-all; WORD-WRAP: break-word;">实例名称:</td>
				<td width="165px" style="border: solid 1px #B4B4B4; word-break: break-all; WORD-WRAP: break-word;">${item.INSTANCE_NAME}</td>
			</tr>
			<tr>
				<td width="80px" style="border: solid 1px #B4B4B4; word-break: break-all; WORD-WRAP: break-word;">最后检查:</td>
				<td width="165px" style="border: solid 1px #B4B4B4; word-break: break-all; WORD-WRAP: break-word;">${item.LAST_CHECKIN_TIME}</td>
			</tr>
			<tr>
				<td width="80px" style="border: solid 1px #B4B4B4; word-break: break-all; WORD-WRAP: break-word;">检查间隔:</td>
				<td width="165px" style="border: solid 1px #B4B4B4; word-break: break-all; WORD-WRAP: break-word;">${item.CHECKIN_INTERVAL}(毫秒)</td>
			</tr>
		</c:forEach>
	</c:if>
</table>
<c:if test="${simpleTriggerDetail!=null}">
	<br />
	<br />
	<b>触发器明细:</b>
	<table cellpadding="0" cellspacing="0" border="0" style="width: 100%; height: auto; padding: 3px;">
		<tr>
			<td width="80px" style="border: solid 1px #B4B4B4; word-break: break-all; WORD-WRAP: break-word;">计划任务:</td>
			<td width="165px" style="border: solid 1px #B4B4B4; word-break: break-all; WORD-WRAP: break-word;">${simpleTriggerDetail.SCHED_NAME}</td>
		</tr>
		<tr>
			<td width="80px" style="border: solid 1px #B4B4B4; word-break: break-all; WORD-WRAP: break-word;">触发器名:</td>
			<td width="165px" style="border: solid 1px #B4B4B4; word-break: break-all; WORD-WRAP: break-word;">${simpleTriggerDetail.TRIGGER_NAME}</td>
		</tr>
		<tr>
			<td width="80px" style="border: solid 1px #B4B4B4; word-break: break-all; WORD-WRAP: break-word;">触发器组:</td>
			<td width="165px" style="border: solid 1px #B4B4B4; word-break: break-all; WORD-WRAP: break-word;">${simpleTriggerDetail.TRIGGER_GROUP}</td>
		</tr>
		<tr>
			<td width="80px" style="border: solid 1px #B4B4B4; word-break: break-all; WORD-WRAP: break-word;">重复次数:</td>
			<td width="165px" style="border: solid 1px #B4B4B4; word-break: break-all; WORD-WRAP: break-word;">${simpleTriggerDetail.REPEAT_COUNT}</td>
		</tr>
		<tr>
			<td width="80px" style="border: solid 1px #B4B4B4; word-break: break-all; WORD-WRAP: break-word;">重复间隔:</td>
			<td width="165px" style="border: solid 1px #B4B4B4; word-break: break-all; WORD-WRAP: break-word;">${simpleTriggerDetail.REPEAT_INTERVAL}(毫秒)</td>
		</tr>
		<tr>
			<td width="80px" style="border: solid 1px #B4B4B4; word-break: break-all; WORD-WRAP: break-word;">触发次数:</td>
			<td width="165px" style="border: solid 1px #B4B4B4; word-break: break-all; WORD-WRAP: break-word;">${simpleTriggerDetail.TIMES_TRIGGERED}</td>
		</tr>
	</table>
</c:if>
<c:if test="${cronTriggerDetail!=null}">
	<b>触发器明细:</b>
	<table cellpadding="0" cellspacing="0" border="0" style="width: 100%; height: auto; padding: 3px;">
		<tr>
			<td width="80px" style="border: solid 1px #B4B4B4; word-break: break-all; WORD-WRAP: break-word;">计划任务:</td>
			<td width="165px" style="border: solid 1px #B4B4B4; word-break: break-all; WORD-WRAP: break-word;">${cronTriggerDetail.SCHED_NAME}</td>
		</tr>
		<tr>
			<td width="80px" style="border: solid 1px #B4B4B4; word-break: break-all; WORD-WRAP: break-word;">触发器名:</td>
			<td width="165px" style="border: solid 1px #B4B4B4; word-break: break-all; WORD-WRAP: break-word;">${cronTriggerDetail.TRIGGER_NAME}</td>
		</tr>
		<tr>
			<td width="80px" style="border: solid 1px #B4B4B4; word-break: break-all; WORD-WRAP: break-word;">触发器组:</td>
			<td width="165px" style="border: solid 1px #B4B4B4; word-break: break-all; WORD-WRAP: break-word;">${cronTriggerDetail.TRIGGER_GROUP}</td>
		</tr>
		<tr>
			<td width="80px" style="border: solid 1px #B4B4B4; word-break: break-all; WORD-WRAP: break-word;">CRON:</td>
			<td width="165px" style="border: solid 1px #B4B4B4; word-break: break-all; WORD-WRAP: break-word;">${cronTriggerDetail.CRON_EXPRESSION}</td>
		</tr>
		<tr>
			<td width="80px" style="border: solid 1px #B4B4B4; word-break: break-all; WORD-WRAP: break-word;">执行时区:</td>
			<td width="165px" style="border: solid 1px #B4B4B4; word-break: break-all; WORD-WRAP: break-word;">${cronTriggerDetail.TIME_ZONE_ID}</td>
		</tr>
	</table>
</c:if>
<c:if test="${jobDetail!=null}">
	<br />
	<br />
	<b>作业明细:</b>
	<table cellpadding="0" cellspacing="0" border="0" style="width: 100%; height: auto; padding: 3px;">
		<tr>
			<td width="80px" style="border: solid 1px #B4B4B4; word-break: break-all; WORD-WRAP: break-word;">计划任务:</td>
			<td width="165px" style="border: solid 1px #B4B4B4; word-break: break-all; WORD-WRAP: break-word;">${jobDetail.SCHED_NAME}</td>
		</tr>
		<tr>
			<td width="80px" style="border: solid 1px #B4B4B4; word-break: break-all; WORD-WRAP: break-word;">作业名称:</td>
			<td width="165px" style="border: solid 1px #B4B4B4; word-break: break-all; WORD-WRAP: break-word;">${jobDetail.JOB_NAME}</td>
		</tr>
		<tr>
			<td width="80px" style="border: solid 1px #B4B4B4; word-break: break-all; WORD-WRAP: break-word;">作业组别:</td>
			<td width="165px" style="border: solid 1px #B4B4B4; word-break: break-all; WORD-WRAP: break-word;">${jobDetail.JOB_GROUP}</td>
		</tr>
		<tr>
			<td width="80px" style="border: solid 1px #B4B4B4; word-break: break-all; WORD-WRAP: break-word;">作业描述:</td>
			<td width="165px" style="border: solid 1px #B4B4B4; word-break: break-all; WORD-WRAP: break-word;">${jobDetail.DESCRIPTION}</td>
		</tr>
		<tr>
			<td width="80px" style="border: solid 1px #B4B4B4; word-break: break-all; WORD-WRAP: break-word;">作业类型:</td>
			<td width165pxpx" style="border: solid 1px #B4B4B4; word-break: break-all; WORD-WRAP: break-word;">${jobDetail.JOB_CLASS_NAME}</td>
		</tr>
		<tr>
			<td width="80px" style="border: solid 1px #B4B4B4; word-break: break-all; WORD-WRAP: break-word;">是否耐用:</td>
			<td width="165px" style="border: solid 1px #B4B4B4; word-break: break-all; WORD-WRAP: break-word;">${jobDetail.IS_DURABLE}</td>
		</tr>
		<tr>
			<td width="80px" style="border: solid 1px #B4B4B4; word-break: break-all; WORD-WRAP: break-word;">禁止并发:</td>
			<td width="165px" style="border: solid 1px #B4B4B4; word-break: break-all; WORD-WRAP: break-word;">${jobDetail.IS_NONCONCURRENT}</td>
		</tr>
		<tr>
			<td width="80px" style="border: solid 1px #B4B4B4; word-break: break-all; WORD-WRAP: break-word;">遗漏恢复:</td>
			<td width="165px" style="border: solid 1px #B4B4B4; word-break: break-all; WORD-WRAP: break-word;">${jobDetail.REQUESTS_RECOVERY}</td>
		</tr>
		<tr>
			<td width="80px" style="border: solid 1px #B4B4B4; word-break: break-all; WORD-WRAP: break-word;">是否更新:</td>
			<td width="165px" style="border: solid 1px #B4B4B4; word-break: break-all; WORD-WRAP: break-word;">${jobDetail.IS_UPDATE_DATA}</td>
		</tr>
	</table>
</c:if>
<br />
<a id="scheduler_clear_btn" href="#" class="easyui-linkbutton">清除计划任务数据</a>
<script type="text/javascript">
	$('#scheduler_clear_btn').click(function() {
		$.messager.prompt('提示', '清除后数据将无法恢复,请输入操作密码:', function(r) {
			if (r) {
				$.messager.progress({
					text : '删除中...',
					interval : 200
				});
				$.ajax({
					type : 'POST',
					url : basePath + 'quartzController/schedulerClear?pswd=' + r,
					success : function(data) {

						// 关闭遮罩
						$.messager.progress('close');

						// 解析数据
						var datas = strToJson(data);
						if (datas.code == '1') {
							$('#schedulerDetailPanel').panel('refresh', basePath + 'quartzController/getSchedulerDetail');
						} else {
							showErrorMsg(datas.errorMessage);
						}
					}
				});
			}
		});
	});
</script>
