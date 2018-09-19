<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
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

<script type="text/javascript">
</script>
</head>

<body    style="margin: 1px">
	欢迎登陆shiro测试系统！！<br>
    你好，shiro-登录检测成功<br>

    <shiro:hasPermission name="test:123">
        你好，shiro-授权检测成功<br>
    </shiro:hasPermission>

    <div> <a href="${pageContext.request.contextPath}/login/logout.do" >退出</a></div>
</body>
</html>