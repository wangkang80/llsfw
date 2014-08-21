<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="/WEB-INF/jsp/llsfw/base/head.jsp" />
<script type="text/javascript" charset="UTF-8" src="${pageContext.request.contextPath}/static/llsfw/js/quartz/quartzMain.js"></script>
<title>定时任务管理</title>
</head>
<body>
	<div class="easyui-layout" data-options="fit:true">
		<div data-options="region:'center',border:false">
			<div class="easyui-layout" data-options="fit:true">
				<div data-options="region:'north',split:true" style="height: 200px;">
					<div id="firedTriggers_search" style="padding: 2px; height: auto;">
						<a id="firedTriggers_search_btn" href="#" class="easyui-linkbutton" data-options="plain:true">查询</a>
					</div>
					<table id="firedTriggersTable"></table>
				</div>
				<div data-options="region:'center',split:true">
					<div id="triggers_search" style="height: auto;">
						<div style="padding: 2px;">
							<a href="#" class="easyui-linkbutton" data-options="plain:true" id="triggers_search_btn">查询</a>
							<shiro:hasPermission name="orgController:edit">
								<a href="#" class="easyui-linkbutton" data-options="plain:true" id="trigger_job_btn">触发</a>
								<a href="#" class="easyui-menubutton" data-options="menu:'#job_trigger_add_menu'">编辑</a>
								<a href="#" class="easyui-menubutton" data-options="menu:'#job_trigger_delete_menu'">删除</a>
								<a href="#" class="easyui-menubutton" data-options="menu:'#job_trigger_pause_menu'">暂停</a>
								<a href="#" class="easyui-menubutton" data-options="menu:'#job_trigger_resume_menu'">恢复</a>
								<div id="job_trigger_add_menu" style="width: 150px;">
									<div id="job_detail_add_btn">编辑作业</div>
									<div class="menu-sep"></div>
									<div id="simple_trigger_add_btn">编辑SIMPLE触发器</div>
									<div id="cron_trigger_add_btn">编辑CRON触发器</div>
								</div>
								<div id="job_trigger_delete_menu" style="width: 120px;">
									<div id="job_detail_delete_btn">删除作业</div>
									<div class="menu-sep"></div>
									<div id="trigger_delete_btn">删除触发器</div>
								</div>
								<div id="job_trigger_pause_menu" style="width: 120px;">
									<div id="pause_all_btn">暂停所有</div>
									<div class="menu-sep"></div>
									<div id="pause_job_btn">暂停作业</div>
									<div id="pause_trigger_btn">暂停触发器</div>
								</div>
								<div id="job_trigger_resume_menu" style="width: 120px;">
									<div id="resume_all_btn">恢复所有</div>
									<div class="menu-sep"></div>
									<div id="resume_job_btn">恢复作业</div>
									<div id="resume_trigger_btn">恢复触发器</div>
								</div>
							</shiro:hasPermission>
							<a href="#" class="easyui-menubutton" data-options="menu:'#scheduler_log_menu'">记录</a>
							<div id="scheduler_log_menu" style="width: 120px;">
								<div id="scheduler_log_btn">操作历史</div>
								<div class="menu-sep"></div>
								<div id="job_trigger_his_btn">执行历史</div>
							</div>
						</div>
						作业名称:
						<input type="text" id="jNameSearch" name="jNameSearch" size="20" />
						作业组别:
						<input type="text" id="jGroupSearch" name="jGroupSearch" size="20" />
						触发器名称:
						<input type="text" id="tNameSearch" name="tNameSearch" size="20" />
						触发器组别:
						<input type="text" id="tGroupSearch" name="tGroupSearch" size="20" />
					</div>
					<table id="triggersTable"></table>
					<div id="job_detail_add_window"></div>
					<div id="simple_trigger_add_window"></div>
					<div id="cron_trigger_add_window"></div>
					<div id="scheduler_log_window"></div>
					<div id="job_trigger_his_window"></div>
				</div>
			</div>
		</div>
		<div data-options="region:'east',split:true" style="width: 250px;">
			<div id="schedulerDetailPanel" style=""></div>
		</div>
	</div>
</body>
</html>