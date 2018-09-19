<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<!DOCTYPE HTML>
<html>
<head>
    <base href="<%=basePath%>">
    <link rel="shortcut icon" href="favicon.ico"> <link href="${pageContext.request.contextPath }/hui/css/bootstrap.min.css?v=3.3.5" rel="stylesheet">
    <link href="${pageContext.request.contextPath }/hui/css/font-awesome.min.css?v=4.4.0" rel="stylesheet">

    <!-- Data Tables -->
    <link href="${pageContext.request.contextPath }/hui/css/plugins/dataTables/dataTables.bootstrap.css" rel="stylesheet">

    <link href="${pageContext.request.contextPath }/hui/css/animate.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath }/hui/css/style.min.css?v=4.0.0" rel="stylesheet"><base target="_blank">
    <link href="${pageContext.request.contextPath }/hui/css/login.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath }/layer/skin/default/layer.css">
    <link href="${pageContext.request.contextPath }/WEB-INF/css/dataTableLocal.css">

    <title>welcome to  shiro!</title>
</head>
<body class="gray-bg">
<div class="wrapper wrapper-content animated fadeInRight">
    <div class="row">
        <div class="col-sm-12">
            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <h5>基本 <small>分类，查找</small></h5>
                    <div class="ibox-tools">
                        <a class="collapse-link">
                            <i class="fa fa-chevron-up"></i>
                        </a>
                        <a class="dropdown-toggle" data-toggle="dropdown" href="table_data_tables.html#">
                            <i class="fa fa-wrench"></i>
                        </a>
                        <ul class="dropdown-menu dropdown-user">
                            <li><a href="table_data_tables.html#">选项1</a>
                            </li>
                            <li><a href="table_data_tables.html#">选项2</a>
                            </li>
                        </ul>
                        <a class="close-link">
                            <i class="fa fa-times"></i>
                        </a>
                    </div>
                </div>
                <div class="ibox-content">

                    <table class="table table-striped table-bordered table-hover dataTables-example">
                        <thead>
                        <tr>
                            <th>渲染引擎</th>
                            <th>浏览器</th>
                            <th>平台</th>
                            <th>引擎版本</th>
                            <th>CSS等级</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr class="gradeX">
                            <td>Trident</td>
                            <td>Internet Explorer 4.0
                            </td>
                            <td>Win 95+</td>
                            <td class="center">4</td>
                            <td class="center">X</td>
                        </tr>
                        <tr class="gradeC">
                            <td>Trident</td>
                            <td>Internet Explorer 5.0
                            </td>
                            <td>Win 95+</td>
                            <td class="center">5</td>
                            <td class="center">C</td>
                        </tr>
                        <tr class="gradeA">
                            <td>Trident</td>
                            <td>Internet Explorer 5.5
                            </td>
                            <td>Win 95+</td>
                            <td class="center">5.5</td>
                            <td class="center">A</td>
                        </tr>
                        <tr class="gradeA">
                            <td>Trident</td>
                            <td>Internet Explorer 6
                            </td>
                            <td>Win 98+</td>
                            <td class="center">6</td>
                            <td class="center">A</td>
                        </tr>
                        <tr class="gradeA">
                            <td>Trident</td>
                            <td>Internet Explorer 7</td>
                            <td>Win XP SP2+</td>
                            <td class="center">7</td>
                            <td class="center">A</td>
                        </tr>
                        <tr class="gradeA">
                            <td>Trident</td>
                            <td>AOL browser (AOL desktop)</td>
                            <td>Win XP</td>
                            <td class="center">6</td>
                            <td class="center">A</td>
                        </tr>
                        <tr class="gradeA">
                            <td>Gecko</td>
                            <td>Firefox 1.0</td>
                            <td>Win 98+ / OSX.2+</td>
                            <td class="center">1.7</td>
                            <td class="center">A</td>
                        </tr>
                        <tr class="gradeA">
                            <td>Gecko</td>
                            <td>Firefox 1.5</td>
                            <td>Win 98+ / OSX.2+</td>
                            <td class="center">1.8</td>
                            <td class="center">A</td>
                        </tr>
                        <tr class="gradeA">
                            <td>Gecko</td>
                            <td>Firefox 2.0</td>
                            <td>Win 98+ / OSX.2+</td>
                            <td class="center">1.8</td>
                            <td class="center">A</td>
                        </tr>
                        <tr class="gradeA">
                            <td>Gecko</td>
                            <td>Firefox 3.0</td>
                            <td>Win 2k+ / OSX.3+</td>
                            <td class="center">1.9</td>
                            <td class="center">A</td>
                        </tr>
                        <tr class="gradeA">
                            <td>Gecko</td>
                            <td>Camino 1.0</td>
                            <td>OSX.2+</td>
                            <td class="center">1.8</td>
                            <td class="center">A</td>
                        </tr>
                        <tr class="gradeA">
                            <td>Gecko</td>
                            <td>Camino 1.5</td>
                            <td>OSX.3+</td>
                            <td class="center">1.8</td>
                            <td class="center">A</td>
                        </tr>
                        <tr class="gradeA">
                            <td>Gecko</td>
                            <td>Netscape 7.2</td>
                            <td>Win 95+ / Mac OS 8.6-9.2</td>
                            <td class="center">1.7</td>
                            <td class="center">A</td>
                        </tr>
                        <tr class="gradeA">
                            <td>Gecko</td>
                            <td>Netscape Browser 8</td>
                            <td>Win 98SE+</td>
                            <td class="center">1.7</td>
                            <td class="center">A</td>
                        </tr>
                        <tr class="gradeA">
                            <td>Gecko</td>
                            <td>Netscape Navigator 9</td>
                            <td>Win 98+ / OSX.2+</td>
                            <td class="center">1.8</td>
                            <td class="center">A</td>
                        </tr>
                        <tr class="gradeA">
                            <td>Gecko</td>
                            <td>Mozilla 1.0</td>
                            <td>Win 95+ / OSX.1+</td>
                            <td class="center">1</td>
                            <td class="center">A</td>
                        </tr>
                        <tr class="gradeA">
                            <td>Gecko</td>
                            <td>Mozilla 1.1</td>
                            <td>Win 95+ / OSX.1+</td>
                            <td class="center">1.1</td>
                            <td class="center">A</td>
                        </tr>
                        <tr class="gradeA">
                            <td>Gecko</td>
                            <td>Mozilla 1.2</td>
                            <td>Win 95+ / OSX.1+</td>
                            <td class="center">1.2</td>
                            <td class="center">A</td>
                        </tr>
                        <tr class="gradeA">
                            <td>Gecko</td>
                            <td>Mozilla 1.3</td>
                            <td>Win 95+ / OSX.1+</td>
                            <td class="center">1.3</td>
                            <td class="center">A</td>
                        </tr>
                        <tr class="gradeA">
                            <td>Gecko</td>
                            <td>Mozilla 1.4</td>
                            <td>Win 95+ / OSX.1+</td>
                            <td class="center">1.4</td>
                            <td class="center">A</td>
                        </tr>
                        <tr class="gradeA">
                            <td>Gecko</td>
                            <td>Mozilla 1.5</td>
                            <td>Win 95+ / OSX.1+</td>
                            <td class="center">1.5</td>
                            <td class="center">A</td>
                        </tr>
                        <tr class="gradeA">
                            <td>Gecko</td>
                            <td>Mozilla 1.6</td>
                            <td>Win 95+ / OSX.1+</td>
                            <td class="center">1.6</td>
                            <td class="center">A</td>
                        </tr>
                        <tr class="gradeA">
                            <td>Gecko</td>
                            <td>Mozilla 1.7</td>
                            <td>Win 98+ / OSX.1+</td>
                            <td class="center">1.7</td>
                            <td class="center">A</td>
                        </tr>
                        <tr class="gradeA">
                            <td>Gecko</td>
                            <td>Mozilla 1.8</td>
                            <td>Win 98+ / OSX.1+</td>
                            <td class="center">1.8</td>
                            <td class="center">A</td>
                        </tr>
                        <tr class="gradeA">
                            <td>Gecko</td>
                            <td>Seamonkey 1.1</td>
                            <td>Win 98+ / OSX.2+</td>
                            <td class="center">1.8</td>
                            <td class="center">A</td>
                        </tr>
                        <tr class="gradeA">
                            <td>Gecko</td>
                            <td>Epiphany 2.20</td>
                            <td>Gnome</td>
                            <td class="center">1.8</td>
                            <td class="center">A</td>
                        </tr>
                        <tr class="gradeA">
                            <td>Webkit</td>
                            <td>Safari 1.2</td>
                            <td>OSX.3</td>
                            <td class="center">125.5</td>
                            <td class="center">A</td>
                        </tr>
                        <tr class="gradeA">
                            <td>Webkit</td>
                            <td>Safari 1.3</td>
                            <td>OSX.3</td>
                            <td class="center">312.8</td>
                            <td class="center">A</td>
                        </tr>
                        <tr class="gradeA">
                            <td>Webkit</td>
                            <td>Safari 2.0</td>
                            <td>OSX.4+</td>
                            <td class="center">419.3</td>
                            <td class="center">A</td>
                        </tr>
                        <tr class="gradeA">
                            <td>Webkit</td>
                            <td>Safari 3.0</td>
                            <td>OSX.4+</td>
                            <td class="center">522.1</td>
                            <td class="center">A</td>
                        </tr>
                        <tr class="gradeA">
                            <td>Webkit</td>
                            <td>OmniWeb 5.5</td>
                            <td>OSX.4+</td>
                            <td class="center">420</td>
                            <td class="center">A</td>
                        </tr>
                        <tr class="gradeA">
                            <td>Webkit</td>
                            <td>iPod Touch / iPhone</td>
                            <td>iPod</td>
                            <td class="center">420.1</td>
                            <td class="center">A</td>
                        </tr>
                        <tr class="gradeA">
                            <td>Webkit</td>
                            <td>S60</td>
                            <td>S60</td>
                            <td class="center">413</td>
                            <td class="center">A</td>
                        </tr>
                        <tr class="gradeA">
                            <td>Presto</td>
                            <td>Opera 7.0</td>
                            <td>Win 95+ / OSX.1+</td>
                            <td class="center">-</td>
                            <td class="center">A</td>
                        </tr>
                        <tr class="gradeA">
                            <td>Presto</td>
                            <td>Opera 7.5</td>
                            <td>Win 95+ / OSX.2+</td>
                            <td class="center">-</td>
                            <td class="center">A</td>
                        </tr>
                        <tr class="gradeA">
                            <td>Presto</td>
                            <td>Opera 8.0</td>
                            <td>Win 95+ / OSX.2+</td>
                            <td class="center">-</td>
                            <td class="center">A</td>
                        </tr>
                        <tr class="gradeA">
                            <td>Presto</td>
                            <td>Opera 8.5</td>
                            <td>Win 95+ / OSX.2+</td>
                            <td class="center">-</td>
                            <td class="center">A</td>
                        </tr>
                        <tr class="gradeA">
                            <td>Presto</td>
                            <td>Opera 9.0</td>
                            <td>Win 95+ / OSX.3+</td>
                            <td class="center">-</td>
                            <td class="center">A</td>
                        </tr>
                        <tr class="gradeA">
                            <td>Presto</td>
                            <td>Opera 9.2</td>
                            <td>Win 88+ / OSX.3+</td>
                            <td class="center">-</td>
                            <td class="center">A</td>
                        </tr>
                        <tr class="gradeA">
                            <td>Presto</td>
                            <td>Opera 9.5</td>
                            <td>Win 88+ / OSX.3+</td>
                            <td class="center">-</td>
                            <td class="center">A</td>
                        </tr>
                        <tr class="gradeA">
                            <td>Presto</td>
                            <td>Opera for Wii</td>
                            <td>Wii</td>
                            <td class="center">-</td>
                            <td class="center">A</td>
                        </tr>
                        <tr class="gradeA">
                            <td>Presto</td>
                            <td>Nokia N800</td>
                            <td>N800</td>
                            <td class="center">-</td>
                            <td class="center">A</td>
                        </tr>
                        <tr class="gradeA">
                            <td>Presto</td>
                            <td>Nintendo DS browser</td>
                            <td>Nintendo DS</td>
                            <td class="center">8.5</td>
                            <td class="center">C/A<sup>1</sup>
                            </td>
                        </tr>
                        <tr class="gradeC">
                            <td>KHTML</td>
                            <td>Konqureror 3.1</td>
                            <td>KDE 3.1</td>
                            <td class="center">3.1</td>
                            <td class="center">C</td>
                        </tr>
                        <tr class="gradeA">
                            <td>KHTML</td>
                            <td>Konqureror 3.3</td>
                            <td>KDE 3.3</td>
                            <td class="center">3.3</td>
                            <td class="center">A</td>
                        </tr>
                        <tr class="gradeA">
                            <td>KHTML</td>
                            <td>Konqureror 3.5</td>
                            <td>KDE 3.5</td>
                            <td class="center">3.5</td>
                            <td class="center">A</td>
                        </tr>
                        <tr class="gradeX">
                            <td>Tasman</td>
                            <td>Internet Explorer 4.5</td>
                            <td>Mac OS 8-9</td>
                            <td class="center">-</td>
                            <td class="center">X</td>
                        </tr>
                        <tr class="gradeC">
                            <td>Tasman</td>
                            <td>Internet Explorer 5.1</td>
                            <td>Mac OS 7.6-9</td>
                            <td class="center">1</td>
                            <td class="center">C</td>
                        </tr>
                        <tr class="gradeC">
                            <td>Tasman</td>
                            <td>Internet Explorer 5.2</td>
                            <td>Mac OS 8-X</td>
                            <td class="center">1</td>
                            <td class="center">C</td>
                        </tr>
                        <tr class="gradeA">
                            <td>Misc</td>
                            <td>NetFront 3.1</td>
                            <td>Embedded devices</td>
                            <td class="center">-</td>
                            <td class="center">C</td>
                        </tr>
                        <tr class="gradeA">
                            <td>Misc</td>
                            <td>NetFront 3.4</td>
                            <td>Embedded devices</td>
                            <td class="center">-</td>
                            <td class="center">A</td>
                        </tr>
                        <tr class="gradeX">
                            <td>Misc</td>
                            <td>Dillo 0.8</td>
                            <td>Embedded devices</td>
                            <td class="center">-</td>
                            <td class="center">X</td>
                        </tr>
                        <tr class="gradeX">
                            <td>Misc</td>
                            <td>Links</td>
                            <td>Text only</td>
                            <td class="center">-</td>
                            <td class="center">X</td>
                        </tr>
                        <tr class="gradeX">
                            <td>Misc</td>
                            <td>Lynx</td>
                            <td>Text only</td>
                            <td class="center">-</td>
                            <td class="center">X</td>
                        </tr>
                        <tr class="gradeC">
                            <td>Misc</td>
                            <td>IE Mobile</td>
                            <td>Windows Mobile 6</td>
                            <td class="center">-</td>
                            <td class="center">C</td>
                        </tr>
                        <tr class="gradeC">
                            <td>Misc</td>
                            <td>PSP browser</td>
                            <td>PSP</td>
                            <td class="center">-</td>
                            <td class="center">C</td>
                        </tr>
                        <tr class="gradeU">
                            <td>Other browsers</td>
                            <td>All others</td>
                            <td>-</td>
                            <td class="center">-</td>
                            <td class="center">U</td>
                        </tr>
                        </tbody>
                        <tfoot>
                        <tr>
                            <th>渲染引擎</th>
                            <th>浏览器</th>
                            <th>平台</th>
                            <th>引擎版本</th>
                            <th>CSS等级</th>
                        </tr>
                        </tfoot>
                    </table>

                </div>
            </div>
        </div>
    </div>

</div>

<button onclick="openAddFunction()" >打开layer测试</button>
<div class="bjX2 con"  scrolling="auto" style="display:none" id="coordinationBankOrder">
    <div class="wrapper wrapper-content animated fadeInRight">
        <div class="row">
            <div class="col-sm-7">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>基本表单 <small>简单登录表单示例</small></h5>
                        <div class="ibox-tools">
                            <a class="collapse-link">
                                <i class="fa fa-chevron-up"></i>
                            </a>
                            <a class="dropdown-toggle" data-toggle="dropdown" href="form_basic.html#">
                                <i class="fa fa-wrench"></i>
                            </a>
                            <ul class="dropdown-menu dropdown-user">
                                <li><a href="form_basic.html#">选项1</a>
                                </li>
                                <li><a href="form_basic.html#">选项2</a>
                                </li>
                            </ul>
                            <a class="close-link">
                                <i class="fa fa-times"></i>
                            </a>
                        </div>
                    </div>
                    <div class="ibox-content">
                        <div class="row">
                            <div class="col-sm-6 b-r">
                                <h3 class="m-t-none m-b">登录</h3>
                                <p>欢迎登录本站(⊙o⊙)</p>
                                <form role="form">
                                    <div class="form-group">
                                        <label>用户名</label>
                                        <input type="email" placeholder="请输入您注册的E-mail" class="form-control">
                                    </div>
                                    <div class="form-group">
                                        <label>密码</label>
                                        <input type="password" placeholder="请输入密码" class="form-control">
                                    </div>
                                    <div>
                                        <button class="btn btn-sm btn-primary pull-right m-t-n-xs" type="submit"><strong>登 录</strong>
                                        </button>
                                        <label>
                                            <input type="checkbox" class="i-checks">自动登录</label>
                                    </div>
                                </form>
                            </div>
                            <div class="col-sm-6">
                                <h4>还不是会员？</h4>
                                <p>您可以注册一个新账户</p>
                                <p class="text-center">
                                    <a href="form_basic.html"><i class="fa fa-sign-in big-icon"></i></a>
                                </p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-sm-5">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>横向表单</h5>
                        <div class="ibox-tools">
                            <a class="collapse-link">
                                <i class="fa fa-chevron-up"></i>
                            </a>
                            <a class="dropdown-toggle" data-toggle="dropdown" href="form_basic.html#">
                                <i class="fa fa-wrench"></i>
                            </a>
                            <ul class="dropdown-menu dropdown-user">
                                <li><a href="form_basic.html#">选项1</a>
                                </li>
                                <li><a href="form_basic.html#">选项2</a>
                                </li>
                            </ul>
                            <a class="close-link">
                                <i class="fa fa-times"></i>
                            </a>
                        </div>
                    </div>
                    <div class="ibox-content">
                        <form class="form-horizontal">
                            <p>欢迎登录本站(⊙o⊙)</p>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">用户名：</label>

                                <div class="col-sm-8">
                                    <input type="email" placeholder="用户名" class="form-control"> <span class="help-block m-b-none">请输入您注册时所填的E-mail</span>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">密码：</label>

                                <div class="col-sm-8">
                                    <input type="password" placeholder="密码" class="form-control">
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-sm-offset-3 col-sm-8">
                                    <button class="btn btn-sm btn-white" type="submit">登 录</button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-sm-8">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>内联表单</h5>
                        <div class="ibox-tools">
                            <a class="collapse-link">
                                <i class="fa fa-chevron-up"></i>
                            </a>
                            <a class="dropdown-toggle" data-toggle="dropdown" href="form_basic.html#">
                                <i class="fa fa-wrench"></i>
                            </a>
                            <ul class="dropdown-menu dropdown-user">
                                <li><a href="form_basic.html#">选项1</a>
                                </li>
                                <li><a href="form_basic.html#">选项2</a>
                                </li>
                            </ul>
                            <a class="close-link">
                                <i class="fa fa-times"></i>
                            </a>
                        </div>
                    </div>
                    <div class="ibox-content">
                        <form role="form" class="form-inline">
                            <div class="form-group">
                                <label for="exampleInputEmail2" class="sr-only">用户名</label>
                                <input type="email" placeholder="请输入用户名" id="exampleInputEmail2" class="form-control">
                            </div>
                            <div class="form-group">
                                <label for="exampleInputPassword2" class="sr-only">密码</label>
                                <input type="password" placeholder="请输入密码" id="exampleInputPassword2" class="form-control">
                            </div>
                            <div class="checkbox m-l m-r-xs">
                                <label class="i-checks">
                                    <input type="checkbox"><i></i> 自动登录</label>
                            </div>
                            <button class="btn btn-white" type="submit">登录</button>
                        </form>
                    </div>
                </div>
            </div>
            <div class="col-sm-4">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>弹出表单 <small>弹出框登录示例</small></h5>
                        <div class="ibox-tools">
                            <a class="collapse-link">
                                <i class="fa fa-chevron-up"></i>
                            </a>
                            <a class="dropdown-toggle" data-toggle="dropdown" href="form_basic.html#">
                                <i class="fa fa-wrench"></i>
                            </a>
                            <ul class="dropdown-menu dropdown-user">
                                <li><a href="form_basic.html#">选项1</a>
                                </li>
                                <li><a href="form_basic.html#">选项2</a>
                                </li>
                            </ul>
                            <a class="close-link">
                                <i class="fa fa-times"></i>
                            </a>
                        </div>
                    </div>
                    <div class="ibox-content">
                        <div class="text-center">
                            <a data-toggle="modal" class="btn btn-primary" href="form_basic.html#modal-form">打开登录窗口</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-sm-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>所有表单元素 <small>包括自定义样式的复选和单选按钮</small></h5>
                        <div class="ibox-tools">
                            <a class="collapse-link">
                                <i class="fa fa-chevron-up"></i>
                            </a>
                            <a class="dropdown-toggle" data-toggle="dropdown" href="form_basic.html#">
                                <i class="fa fa-wrench"></i>
                            </a>
                            <ul class="dropdown-menu dropdown-user">
                                <li><a href="form_basic.html#">选项1</a>
                                </li>
                                <li><a href="form_basic.html#">选项2</a>
                                </li>
                            </ul>
                            <a class="close-link">
                                <i class="fa fa-times"></i>
                            </a>
                        </div>
                    </div>
                    <div class="ibox-content">
                        <form method="get" class="form-horizontal">
                            <div class="form-group">
                                <label class="col-sm-2 control-label">普通</label>

                                <div class="col-sm-10">
                                    <input type="text" class="form-control">
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">带说明信息</label>
                                <div class="col-sm-10">
                                    <input type="text" class="form-control"> <span class="help-block m-b-none">帮助文本，可能会超过一行，以块级元素显示</span>
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">密码</label>

                                <div class="col-sm-10">
                                    <input type="password" class="form-control" name="password">
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">提示</label>

                                <div class="col-sm-10">
                                    <input type="text" placeholder="提示信息" class="form-control">
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">禁用</label>

                                <div class="col-sm-10">
                                    <input type="text" disabled="" placeholder="已被禁用" class="form-control">
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">静态控制</label>

                                <div class="col-sm-10">
                                    <p class="form-control-static">i@zi-han.net</p>
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">复选框&amp;单选框
                                    <br/>
                                    <small class="text-navy">普通Bootstrap元素</small>
                                </label>

                                <div class="col-sm-10">
                                    <div class="checkbox">
                                        <label>
                                            <input type="checkbox" value="">选项1</label>
                                    </div>
                                    <div class="radio">
                                        <label>
                                            <input type="radio" checked="" value="option1" id="optionsRadios1" name="optionsRadios">选项1</label>
                                    </div>
                                    <div class="radio">
                                        <label>
                                            <input type="radio" value="option2" id="optionsRadios2" name="optionsRadios">选项2</label>
                                    </div>
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">内联复选框</label>

                                <div class="col-sm-10">
                                    <label class="checkbox-inline">
                                        <input type="checkbox" value="option1" id="inlineCheckbox1">a</label>
                                    <label class="checkbox-inline">
                                        <input type="checkbox" value="option2" id="inlineCheckbox2">b</label>
                                    <label class="checkbox-inline">
                                        <input type="checkbox" value="option3" id="inlineCheckbox3">c</label>
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">复选框&amp;单选框
                                    <br/><small class="text-navy">自定义样式</small>
                                </label>

                                <div class="col-sm-10">
                                    <div class="checkbox i-checks">
                                        <label>
                                            <input type="checkbox" value=""> <i></i> 选项1</label>
                                    </div>
                                    <div class="checkbox i-checks">
                                        <label>
                                            <input type="checkbox" value="" checked=""> <i></i> 选项2（选中）</label>
                                    </div>
                                    <div class="checkbox i-checks">
                                        <label>
                                            <input type="checkbox" value="" disabled="" checked=""> <i></i> 选项3（选中并禁用）</label>
                                    </div>
                                    <div class="checkbox i-checks">
                                        <label>
                                            <input type="checkbox" value="" disabled=""> <i></i> 选项4（禁用）</label>
                                    </div>
                                    <div class="radio i-checks">
                                        <label>
                                            <input type="radio" value="option1" name="a"> <i></i> 选项1</label>
                                    </div>
                                    <div class="radio i-checks">
                                        <label>
                                            <input type="radio" checked="" value="option2" name="a"> <i></i> 选项2（选中）</label>
                                    </div>
                                    <div class="radio i-checks">
                                        <label>
                                            <input type="radio" disabled="" checked="" value="option2"> <i></i> 选项3（选中并禁用）</label>
                                    </div>
                                    <div class="radio i-checks">
                                        <label>
                                            <input type="radio" disabled="" name="a"> <i></i> 选项4（禁用）</label>
                                    </div>
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">内联复选框</label>

                                <div class="col-sm-10">
                                    <label class="checkbox-inline i-checks">
                                        <input type="checkbox" value="option1">a</label>
                                    <label class="checkbox-inline i-checks">
                                        <input type="checkbox" value="option2">b</label>
                                    <label class="checkbox-inline i-checks">
                                        <input type="checkbox" value="option3">c</label>
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">Select</label>

                                <div class="col-sm-10">
                                    <select class="form-control m-b" name="account">
                                        <option>选项 1</option>
                                        <option>选项 2</option>
                                        <option>选项 3</option>
                                        <option>选项 4</option>
                                    </select>

                                    <div class="col-sm-4 m-l-n">
                                        <select class="form-control" multiple="">
                                            <option>选项 1</option>
                                            <option>选项 2</option>
                                            <option>选项 3</option>
                                            <option>选项 4</option>
                                        </select>
                                    </div>
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="form-group has-success">
                                <label class="col-sm-2 control-label">验证通过</label>

                                <div class="col-sm-10">
                                    <input type="text" class="form-control">
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="form-group has-warning">
                                <label class="col-sm-2 control-label">未填写</label>

                                <div class="col-sm-10">
                                    <input type="text" class="form-control">
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="form-group has-error">
                                <label class="col-sm-2 control-label">验证未通过</label>

                                <div class="col-sm-10">
                                    <input type="text" class="form-control">
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">自定义尺寸</label>

                                <div class="col-sm-10">
                                    <input type="text" placeholder=".input-lg" class="form-control input-lg m-b">
                                    <input type="text" placeholder="Default input" class="form-control m-b">
                                    <input type="text" placeholder=".input-sm" class="form-control input-sm">
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">列尺寸</label>

                                <div class="col-sm-10">
                                    <div class="row">
                                        <div class="col-md-2">
                                            <input type="text" placeholder=".col-md-2" class="form-control">
                                        </div>
                                        <div class="col-md-3">
                                            <input type="text" placeholder=".col-md-3" class="form-control">
                                        </div>
                                        <div class="col-md-4">
                                            <input type="text" placeholder=".col-md-4" class="form-control">
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">文本框组</label>

                                <div class="col-sm-10">
                                    <div class="input-group m-b"><span class="input-group-addon">@</span>
                                        <input type="text" placeholder="用户名" class="form-control">
                                    </div>
                                    <div class="input-group m-b">
                                        <input type="text" class="form-control"> <span class="input-group-addon">.00</span>
                                    </div>
                                    <div class="input-group m-b"><span class="input-group-addon">&yen;</span>
                                        <input type="text" class="form-control"> <span class="input-group-addon">.00</span>
                                    </div>
                                    <div class="input-group m-b"><span class="input-group-addon"> <input type="checkbox"> </span>
                                        <input type="text" class="form-control">
                                    </div>
                                    <div class="input-group"><span class="input-group-addon"> <input type="radio"> </span>
                                        <input type="text" class="form-control">
                                    </div>
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">按钮插件</label>

                                <div class="col-sm-10">
                                    <div class="input-group m-b"><span class="input-group-btn">
                                            <button type="button" class="btn btn-primary">搜</button> </span>
                                        <input type="text" class="form-control">
                                    </div>
                                    <div class="input-group">
                                        <input type="text" class="form-control"> <span class="input-group-btn"> <button type="button" class="btn btn-primary">搜索
                                    </button> </span>
                                    </div>
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">带下拉框</label>

                                <div class="col-sm-10">
                                    <div class="input-group m-b">
                                        <div class="input-group-btn">
                                            <button data-toggle="dropdown" class="btn btn-white dropdown-toggle" type="button">操作 <span class="caret"></span>
                                            </button>
                                            <ul class="dropdown-menu">
                                                <li><a href="form_basic.html#">选项1</a>
                                                </li>
                                                <li><a href="form_basic.html#">选项2</a>
                                                </li>
                                                <li><a href="form_basic.html#">选项3</a>
                                                </li>
                                                <li class="divider"></li>
                                                <li><a href="form_basic.html#">选项4</a>
                                                </li>
                                            </ul>
                                        </div>
                                        <input type="text" class="form-control">
                                    </div>
                                    <div class="input-group">
                                        <input type="text" class="form-control">

                                        <div class="input-group-btn">
                                            <button data-toggle="dropdown" class="btn btn-white dropdown-toggle" type="button">操作 <span class="caret"></span>
                                            </button>
                                            <ul class="dropdown-menu pull-right">
                                                <li><a href="form_basic.html#">选项1</a>
                                                </li>
                                                <li><a href="form_basic.html#">选项2</a>
                                                </li>
                                                <li><a href="form_basic.html#">选项3</a>
                                                </li>
                                                <li class="divider"></li>
                                                <li><a href="form_basic.html#">选项4</a>
                                                </li>
                                            </ul>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">分段</label>

                                <div class="col-sm-10">
                                    <div class="input-group m-b">
                                        <div class="input-group-btn">
                                            <button tabindex="-1" class="btn btn-white" type="button">操作</button>
                                            <button data-toggle="dropdown" class="btn btn-white dropdown-toggle" type="button"><span class="caret"></span>
                                            </button>
                                            <ul class="dropdown-menu">
                                                <li><a href="form_basic.html#">选项1</a>
                                                </li>
                                                <li><a href="form_basic.html#">选项2</a>
                                                </li>
                                                <li><a href="form_basic.html#">选项3</a>
                                                </li>
                                                <li class="divider"></li>
                                                <li><a href="form_basic.html#">选项4</a>
                                                </li>
                                            </ul>
                                        </div>
                                        <input type="text" class="form-control">
                                    </div>
                                    <div class="input-group">
                                        <input type="text" class="form-control">

                                        <div class="input-group-btn">
                                            <button tabindex="-1" class="btn btn-white" type="button">操作</button>
                                            <button data-toggle="dropdown" class="btn btn-white dropdown-toggle" type="button"><span class="caret"></span>
                                            </button>
                                            <ul class="dropdown-menu pull-right">
                                                分段
                                            </ul>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <div class="col-sm-4 col-sm-offset-2">
                                    <button class="btn btn-primary" type="submit">保存内容</button>
                                    <button class="btn btn-white" type="submit">取消</button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div id="modal-form" class="modal fade" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-body">
                    <div class="row">
                        <div class="col-sm-6 b-r">
                            <h3 class="m-t-none m-b">登录</h3>

                            <p>欢迎登录本站(⊙o⊙)</p>

                            <form role="form">
                                <div class="form-group">
                                    <label>用户名：</label>
                                    <input type="email" placeholder="请输入用户名" class="form-control">
                                </div>
                                <div class="form-group">
                                    <label>密码：</label>
                                    <input type="password" placeholder="请输入密码" class="form-control">
                                </div>
                                <div>
                                    <button class="btn btn-sm btn-primary pull-right m-t-n-xs" type="submit"><strong>登录</strong>
                                    </button>
                                    <label>
                                        <input type="checkbox" class="i-checks">自动登录</label>
                                </div>
                            </form>
                        </div>
                        <div class="col-sm-6">
                            <h4>还不是会员？</h4>
                            <p>您可以注册一个账户</p>
                            <p class="text-center">
                                <a href="form_basic.html"><i class="fa fa-sign-in big-icon"></i></a>
                            </p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="${pageContext.request.contextPath }/hui/js/jquery.min.js?v=2.1.4"></script>
<script src="${pageContext.request.contextPath }/hui/js/bootstrap.min.js?v=3.3.5"></script>
<script src="${pageContext.request.contextPath }/hui/js/plugins/jeditable/jquery.jeditable.js"></script>
<script src="${pageContext.request.contextPath }/hui/js/plugins/dataTables/jquery.dataTables.js"></script>
<script src="${pageContext.request.contextPath }/hui/js/plugins/dataTables/dataTables.bootstrap.js"></script>
<script src="${pageContext.request.contextPath }/hui/js/content.min.js?v=1.0.0"></script>

<script src="${pageContext.request.contextPath }/layer/layui.js"></script>
<script>
    $(document).ready(function(){$(".dataTables-example").dataTable();var oTable=$("#editable").dataTable();oTable.$("td").editable("../example_ajax.php",{"callback":function(sValue,y){var aPos=oTable.fnGetPosition(this);oTable.fnUpdate(sValue,aPos[0],aPos[1])},"submitdata":function(value,settings){return{"row_id":this.parentNode.getAttribute("id"),"column":oTable.fnGetPosition(this)[2]}},"width":"90%","height":"100%"})});function fnClickAddRow(){$("#editable").dataTable().fnAddData(["Custom row","New row","New row","New row","New row"])};
</script>
<script type="text/javascript" src="http://tajs.qq.com/stats?sId=9051096" charset="UTF-8"></script>
<script src="${pageContext.request.contextPath }/WEB-INF/js/common/layer-local.js"></script>
<script>
    function openAddFunction() {
        //官网欢迎页
//        ii = layer.open({
//            offset: 20,
//            type: 1,
//            title: '协同银行添加',
//            fix: false,
//            maxmin: true,
//            shadeClose: true,
//            area: ['80%', '90%'],
//            content: "123456",
//            end: function () {
////                         $('#dynamic-table-police').attr("style", "display:none");
//            },
//        });
        var url="localhost:8088/welcom/page?id=1";
        console.info(url)
        data=$("#coordinationBankOrder").html();
        Layer.layOpen( "",data ,url);
    }
</script>
</body>
</html>