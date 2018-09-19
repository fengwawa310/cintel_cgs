<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<title>h+首页公共引用</title>
<head>
	<base href="<%=basePath%>">
	<!--[if lt IE 8]>
	<meta http-equiv="refresh" content="0;ie.html" />
	<![endif]-->
	<link rel="shortcut icon" href="favicon.ico">
	<link href="${pageContext.request.contextPath }/hui/css/bootstrap.min.css?v=3.3.5" rel="stylesheet">
	<link href="${pageContext.request.contextPath }/hui/css/font-awesome.min.css?v=4.4.0" rel="stylesheet">
	<link href="${pageContext.request.contextPath }/hui/css/animate.min.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath }/hui/css/style.min.css?v=4.0.0" rel="stylesheet">

	<link href="${pageContext.request.contextPath }/WEB-INF/layer/skin/layer.css" rel="stylesheet">
</head>
	<script src="${pageContext.request.contextPath }/hui/js/jquery.min.js?v=2.1.4"></script>
	<script src="${pageContext.request.contextPath }/hui/js/bootstrap.min.js?v=3.3.5"></script>
	<script src="${pageContext.request.contextPath }/hui/js/plugins/metisMenu/jquery.metisMenu.js"></script>
	<script src="${pageContext.request.contextPath }/hui/js/plugins/slimscroll/jquery.slimscroll.min.js"></script>
	<script src="${pageContext.request.contextPath }/hui/js/plugins/layer/layer.min.js"></script>
	<script src="${pageContext.request.contextPath }/hui/js/hplus.min.js?v=4.0.0"></script>
	<script src="${pageContext.request.contextPath }/hui/js/contabs.min.js" type="text/javascript" ></script>
	<script src="${pageContext.request.contextPath }/hui/js/plugins/pace/pace.min.js"></script>

	<script src="${pageContext.request.contextPath }/WEB-INF/layer/layer.js"></script>
	<script src="${pageContext.request.contextPath }/WEB-INF/js/common/common.js"></script>
</html>
