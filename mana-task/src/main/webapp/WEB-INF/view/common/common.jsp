<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<title>layer页面引用</title>
<head>
	<base href="<%=basePath%>">
	<!--[if lt IE 8]>
	<meta http-equiv="refresh" content="0;ie.html" />
	<![endif]-->

	<link href="${pageContext.request.contextPath }/WEB-INF/layer/skin/layer.css" rel="stylesheet">
</head>
	<script src="${pageContext.request.contextPath }/hui/js/jquery.min.js?v=2.1.4"></script>
	<script src="${pageContext.request.contextPath }/WEB-INF/layer/layer.js"></script>
	<script src="${pageContext.request.contextPath }/WEB-INF/js/common/common.js"></script>
	<script src="${pageContext.request.contextPath }/WEB-INF/js/common/layer-local.js"></script>
</html>
