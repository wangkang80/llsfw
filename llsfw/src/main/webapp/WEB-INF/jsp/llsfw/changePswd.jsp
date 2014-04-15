<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript" charset="UTF-8" src="${pageContext.request.contextPath}/static/llsfw/js/changePswd.js"></script>
<form id="change_pswd_form" name="change_pswd_form" method="post">
	原始密码:
	<input id="old_pswd" name="oldPswd" type="password" style="width: 150px; margin: 5px;" />
	<br />
	新改密码:
	<input id="new_pswd" name="newPswd" type="password" style="width: 150px; margin: 5px;" />
	<br />
	密码确认:
	<input id="pswf_confim" name="pswfConfim" type="password" style="width: 150px; margin: 5px;" />
	<br />
	<center style="margin: 5px;">
		<a id="pswd_save_btn" href="#">修改</a>
	</center>
</form>