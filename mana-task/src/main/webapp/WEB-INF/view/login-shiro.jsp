<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>shiro-登录damo</title>
</head>
<body>
	<div id="header">
		<div class="header_title">
		</div>
	</div>
	<div id="content">
		<center>
			<div class="con">
				<div class="con_title">
					<span class="con_title_sp">欢迎使用系统</span>
				</div>
				<form action="${pageContext.request.contextPath}/login/login.do" method="Post" id="form1">
					<div class="con_panel">
						<div class="con_input">
							<span>用&nbsp;户&nbsp;名</span>&nbsp;&nbsp;<input type="text" name="userName" placeholder="用户名：tom" />
						</div>
						<div class="con_input">
							<span>密&nbsp;&nbsp;&nbsp;&nbsp;码</span>&nbsp;&nbsp;
							<input type="text" name="password"	placeholder="密码:123" />
						</div>
						<%--<div class="con_input">
							<span>记住我</span>
							<input id="rememberMe" type="checkbox" name="rememberMe" value="true">
						</div>--%>
						<input type="submit" value="登    录" class="submit-btn" />
					</div>
				</form>
			</div>
		</center>
	</div>

	<div style="text-align: center;">
	<a href="#" target="_blank">Copyright © 1999-2018,  All Rights Reserved</a>
	</div>

</body>
</html>
