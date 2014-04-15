<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript" charset="UTF-8" src="${pageContext.request.contextPath}/static/llsfw/js/right.js"></script>
<div class="easyui-layout" data-options="fit:true" style="overflow-y: hidden" scroll="no">
	<div data-options="region:'north',collapsible:false" style=" height: 180px; overflow: hidden;">
		<div class="easyui-calendar" data-options="border:false"></div>
	</div>
	<div data-options="region:'center',collapsible:false" style=" overflow: hidden;">
		<div class="easyui-layout" data-options="fit:true" style="overflow-y: hidden" scroll="no">
			<div data-options="region:'north',collapsible:false" style=" height: 180px; overflow: hidden;">
				<div id="user_online_panel" style="">
					<table id="user_online_table"></table>
				</div>
			</div>
		</div>
	</div>
</div>