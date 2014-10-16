<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<table cellpadding="0" cellspacing="0" border="0" style="width: 100%; height: 100%; padding: 3px;">
	<tr>
		<td width="130px" style="border: solid 1px #B4B4B4; word-break: break-all; WORD-WRAP: break-word;">计划任务实例:</td>
		<td style="border: solid 1px #B4B4B4; word-break: break-all; WORD-WRAP: break-word;">${metaData['SCHEDULER_CLASS']}</td>
	</tr>
	<tr>
		<td style="border: solid 1px #B4B4B4; word-break: break-all; WORD-WRAP: break-word;">计划任务版本:</td>
		<td style="border: solid 1px #B4B4B4; word-break: break-all; WORD-WRAP: break-word;">${metaData['VERSION']}</td>
	</tr>
	<tr>
		<td style="border: solid 1px #B4B4B4; word-break: break-all; WORD-WRAP: break-word;">开始运行日期:</td>
		<td style="border: solid 1px #B4B4B4; word-break: break-all; WORD-WRAP: break-word;">${metaData['RUNNING_SINCE']}</td>
	</tr>
	<tr>
		<td style="border: solid 1px #B4B4B4; word-break: break-all; WORD-WRAP: break-word;">作业存储实例:</td>
		<td style="border: solid 1px #B4B4B4; word-break: break-all; WORD-WRAP: break-word;">${metaData['JOB_STORE_CLASS']}</td>
	</tr>
	<tr>
		<td style="border: solid 1px #B4B4B4; word-break: break-all; WORD-WRAP: break-word;">是否集群模式:</td>
		<td style="border: solid 1px #B4B4B4; word-break: break-all; WORD-WRAP: break-word;">${metaData['JOB_STORE_CLUSTERED']}</td>
	</tr>
	<tr>
		<td style="border: solid 1px #B4B4B4; word-break: break-all; WORD-WRAP: break-word;">是否持久化:</td>
		<td style="border: solid 1px #B4B4B4; word-break: break-all; WORD-WRAP: break-word;">${metaData['JOB_STORE_S_P']}</td>
	</tr>
	<tr>
		<td style="border: solid 1px #B4B4B4; word-break: break-all; WORD-WRAP: break-word;">线程池实例:</td>
		<td style="border: solid 1px #B4B4B4; word-break: break-all; WORD-WRAP: break-word;">${metaData['THREAD_POOL_CLASS']}</td>
	</tr>
	<tr>
		<td style="border: solid 1px #B4B4B4; word-break: break-all; WORD-WRAP: break-word;">线程池大小:</td>
		<td style="border: solid 1px #B4B4B4; word-break: break-all; WORD-WRAP: break-word;">${metaData['THREAD_POOL_SIZE']}</td>
	</tr>
	<tr>
		<td style="border: solid 1px #B4B4B4; word-break: break-all; WORD-WRAP: break-word;">执行作业数量:</td>
		<td style="border: solid 1px #B4B4B4; word-break: break-all; WORD-WRAP: break-word;">${metaData['NUMBER_OF_JOBS']}</td>
	</tr>
	<tr>
		<td style="border: solid 1px #B4B4B4; word-break: break-all; WORD-WRAP: break-word;">摘要:</td>
		<td style="border: solid 1px #B4B4B4; word-break: break-all; WORD-WRAP: break-word;">${metaData['SUMMARY']}</td>
	</tr>
</table>
