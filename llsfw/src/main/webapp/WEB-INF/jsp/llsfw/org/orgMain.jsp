<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="/WEB-INF/jsp/llsfw/base/head.jsp" />
<script type="text/javascript" charset="UTF-8" src="${pageContext.request.contextPath}/static/llsfw/js/org/orgMain.js"></script>
<title>组织机构维护</title>
</head>
<body>
	<div class="easyui-layout" data-options="fit:true">
		<div data-options="region:'west',split:true" style="width: 330px;">
			<div id="org_search" style="padding: 5px; height: auto;">
				<a id="org_search_btn" href="#" class="easyui-linkbutton">查询</a>
				<shiro:hasPermission name="orgController:add">
					<a href="#" class="easyui-menubutton" data-options="plain:false,menu:'#org_add_div_menu'">新增</a>
					<div id="org_add_div_menu" style="width: 100px;">
						<div id="org_add_root_btn">根组织</div>
						<div id="org_add_item_btn">普通组织</div>
					</div>
				</shiro:hasPermission>
				<shiro:hasPermission name="orgController:edit">
					<a id="org_edit_btn" href="#" class="easyui-linkbutton">修改</a>
				</shiro:hasPermission>
				<shiro:hasPermission name="orgController:delete">
					<a id="org_delete_btn" href="#" class="easyui-linkbutton">删除</a>
				</shiro:hasPermission>
			</div>
			<table id="org_search_table"></table>
			<div id="org_add_window"></div>
			<div id="org_edit_window"></div>
		</div>
		<div data-options="region:'center',border:false">
			<div class="easyui-layout" data-options="fit:true,border:false">
				<div data-options="region:'north',split:true" style="height: 300px;">
					<div id="job_search" style="padding: 5px; height: auto;">
						<a id="job_search_btn" href="#" class="easyui-linkbutton">查询</a>
						<shiro:hasPermission name="orgController:job_add">
							<a id="job_add_btn" href="#" class="easyui-linkbutton">新增</a>
						</shiro:hasPermission>
						<shiro:hasPermission name="orgController:job_edit">
							<a id="job_edit_btn" href="#" class="easyui-linkbutton">修改</a>
						</shiro:hasPermission>
						<shiro:hasPermission name="orgController:job_delete">
							<a id="job_delete_btn" href="#" class="easyui-linkbutton">删除</a>
						</shiro:hasPermission>
					</div>
					<table id="job_search_table"></table>
					<div id="job_add_window"></div>
					<div id="job_edit_window"></div>
				</div>
				<div data-options="region:'center',split:true">
					<div id="job_role_search" style="padding: 5px; height: auto;">
						<shiro:hasPermission name="orgController:role_add">
							<a id="job_role_add_btn" href="#" class="easyui-linkbutton">新增</a>
						</shiro:hasPermission>
						<shiro:hasPermission name="orgController:role_delete">
							<a id="job_role_delete_btn" href="#" class="easyui-linkbutton">删除</a>
						</shiro:hasPermission>
					</div>
					<table id="job_role_search_table"></table>
					<div id="job_role_add_window"></div>
				</div>
			</div>
		</div>
</body>
</html>