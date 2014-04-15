<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!-- 常用参数设置(AppConfigListener中设置,切记不能添加执行效率过低的代码,如DB操作等....) -->
<script type="text/javascript">
	var path = '${pageContext.request.contextPath}';//上下文路径
	var basePath = '${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/';//全路径
</script>