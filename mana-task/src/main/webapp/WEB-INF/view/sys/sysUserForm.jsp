<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<!DOCTYPE HTML >
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <base href="<%=basePath%>">
    <link rel="shortcut icon" href="${pageContext.request.contextPath }/hui/favicon.ico">
    <link href="${pageContext.request.contextPath }/hui/css/bootstrap.min.css?v=3.3.5" rel="stylesheet">
    <link href="${pageContext.request.contextPath }/hui/css/font-awesome.min.css?v=4.4.0" rel="stylesheet">

    <link href="${pageContext.request.contextPath }/hui/css/animate.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath }/hui/css/style.min.css?v=4.0.0" rel="stylesheet"><base target="_blank">

    <link href="${pageContext.request.contextPath }/layer/skin/default/layer.css">
    <link href="${pageContext.request.contextPath }/WEB-INF/css/ace.css">
    <title>welcome to  shiro!</title>
    <style>
        /*控制表单表格，边距宽高*/
        .wrapper-content {
            padding: 9px;
        }
    </style>
</head>
<body class="gray-bg">
<div class="wrapper wrapper-content animated fadeInRight">
    <%--添加弹框--%>
    <div   scrolling="auto" style="" id="addSysUserDialog">
        <div class="wrapper wrapper-content animated fadeInRight">
            <form:form modelAttribute="sysUser">
                <table class="table table-bordered  ">
                    <tbody>
                        <tr>
                            <td class="width-15 active">
                                <label class="control-label pull-right active" >用户名:</label>
                            </td>
                            <td class="width-50 ">
                                <form:input type="text"  path="userName" class="form-control" />
                            </td>
                            <td class="width-15 active">
                                <label class=" control-label pull-right" >姓名:</label>
                            </td>
                            <td class="width-50 ">
                                <form:input type="text"  path="name" class="form-control" />
                            </td>
                        </tr>
                        <tr>
                            <td class="width-20 active">
                                <label class=" pull-right active" >电话:</label>
                            </td>
                            <td class="width-40 ">
                                <form:input type="text"  path="tel" class="form-control" />
                            </td>
                            <td class="width-20 active">
                                <label class=" control-label pull-right" >报警电话:</label>
                            </td>
                            <td class="width-40 ">
                                <input type="text"  placeholder="Username" class="form-control" />
                            </td>
                        </tr>
                    </tbody>
                </table>
            </form:form>
        </div>
    </div>
</div>

<script src="${pageContext.request.contextPath }/hui/js/jquery.min.js?v=2.1.4"></script>
<script src="${pageContext.request.contextPath }/hui/js/bootstrap.min.js?v=3.3.5"></script>

</body>
</html>