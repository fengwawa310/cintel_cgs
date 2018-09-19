<%@ page contentType="text/html;charset=UTF-8" language="java" import="java.util.*,com.common.consts.Global"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<%
	String sysName = Global.getUnicodeConfig("sys.name"); // 属性名
	String sysLogo = Global.getConfig("sys.logo"); // 属性名
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>智鹰系统</title>
<%@include file="common/includeBaseSet.jsp" %>
<meta name="viewport"
	content="width=device-width,initial-scale=1.0,user-scalable=no">
<script type="text/javascript" charset="utf-8" src="js/include.js"></script>
<script src="${jypath}/view/assets/js/jquery-2.0.3.min.js"></script>
<link rel="stylesheet"
	href="${jypath}/view/assets/css/bootstrap.min.css">
<link rel="stylesheet"
	href="${jypath}/view/assets/css/bootstrapValidator.min.css">
<link rel="stylesheet"
	href="${jypath}/view/assets/css/font-awesome.min.css" />
<link rel="stylesheet" href="${jypath}//view/css/login.css">
<script src="${jypath}/view/assets/js/bootstrapValidator.min.js"></script>
</head>

<body>
	<div class="container wrap1">
		<div class="mg-b20 text-center">
			<img class="img-logo"src="${jypath}<%=sysLogo%>">
			<h1><%=sysName%></h1>
		</div>
		<div
			class="col-sm-8 col-md-5 center-auto pd-sm-50 pd-xs-20 main_content">
			<p class="text-center font16">用户登录</p>
			<form id="loginForm" action="${jypath}/login/login.do">
				<div class="form-group mg-t20">
					<i class="icon-user username-img"></i> <input name="userName"
						class="form-control login_input" id="username"
						placeholder="请输入用户名" value="" />
				</div>

				<div class="form-group mg-t20">
					<i class="icon-lock password-img"></i> <input name="password"
						type="password" class="form-control login_input" id="password"
						placeholder="请输入密码" value="" />
				</div>
				<!--  <button type="button" class="login_btn" onclick="login();">登 录</button> -->
				<input type="submit" value="登    录" class="login_btn" />
			</form>
		</div>
	</div>
	<div class="copy-right">
		<a href="#" target="_blank">版本号：v1.3.3</a>
	</div>
</body>
<script src="${jypath}/view/js/login.js"></script>
<script src="${jypath}/view/js/common.js"></script>
<script>
	$(window).ready(function () {
	    //获取配置文件信息
        getSysConfig();
    });
</script>
</html>
