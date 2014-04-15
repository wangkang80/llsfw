<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<jsp:include page="/WEB-INF/jsp/llsfw/base/headset.jsp" />
<jsp:include page="/WEB-INF/jsp/llsfw/base/head_jquery.jsp" />
<jsp:include page="/WEB-INF/jsp/llsfw/base/head_base.jsp" />
<jsp:include page="/WEB-INF/jsp/llsfw/base/head_easyui_loder.jsp" />
<jsp:include page="/WEB-INF/jsp/llsfw/base/head_init_data.jsp" />
<div id="ajaxRequestErrWindow"></div>

<!-- set easyui  -->
<script type="text/javascript">
	easyloader.locale = "zh_CN"; // 本地化设置
	easyloader.theme = "${appConfig.headPageConfig.easyuiThemesName}"; // 设置主题
</script>
