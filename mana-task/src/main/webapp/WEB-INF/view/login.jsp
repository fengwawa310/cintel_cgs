<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<!DOCTYPE HTML >

<head>

    <title>H+ 后台主题UI框架 - 登录</title>
    <link href="${pageContext.request.contextPath }/hui/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath }/hui/css/font-awesome.min.css?v=4.4.0" rel="stylesheet">
    <link href="${pageContext.request.contextPath }/hui/css/animate.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath }/hui/css/style.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath }/hui/css/login.min.css" rel="stylesheet">
    <!--[if lt IE 8]>
    <meta http-equiv="refresh" content="0;ie.html" />
    <![endif]-->
    <script>
        if(window.top!==window.self){window.top.location=window.location};
    </script>

</head>

<body class="signin">
<div class="signinpanel">
    <div class="row">
        <div class="col-sm-7">
            <div class="signin-info">
                <div class="logopanel m-b">
                    <%--<h1>[ ]</h1>--%>
                </div>
                <div class="m-b"></div>
                <h4>欢迎使用 <strong>后台shiro框架</strong></h4>
                <ul class="m-b">
                    <li><i class="fa fa-arrow-circle-o-right m-r-xs"></i> 优势一：分布式部署</li>
                    <li><i class="fa fa-arrow-circle-o-right m-r-xs"></i> 优势二：单点登录</li>
                    <li><i class="fa fa-arrow-circle-o-right m-r-xs"></i> 优势三：集成dubbo</li>
                    <li><i class="fa fa-arrow-circle-o-right m-r-xs"></i> 优势四：解决分布式事务</li>
                    <li><i class="fa fa-arrow-circle-o-right m-r-xs"></i> 优势五：集成fastdfs</li>
                </ul>
                <%--<strong>还没有账号？ <a href="#">立即注册&raquo;</a></strong>--%>
            </div>
        </div>
        <div class="col-sm-5">
            <form  action="${pageContext.request.contextPath}/login/login.do" method="Post">
                <h4 class="no-margins">登录：</h4>
                <p class="m-t-md">登录后台</p>
                <input type="text" class="form-control uname" name="userName" placeholder="用户名" />
                <input type="password" class="form-control pword m-b" name="password" placeholder="密码" />
                <span></span>
                <br>
                <%--<button class="btn btn-success btn-block">登录</button>--%>
                <input type="submit" value="登    录" class="btn btn-success btn-block" />
            </form>
        </div>
    </div>
    <div class="signup-footer">
        <div class="pull-left">
            &copy; 2015 All Rights Reserved.
        </div>
    </div>
</div>
</body>

</html>