<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="/tags/function" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <jsp:include page="../common/common.jsp"></jsp:include>
    <link rel="shortcut icon" href="${pageContext.request.contextPath }/hui/favicon.ico">
    <link href="${pageContext.request.contextPath }/hui/css/bootstrap.min.css?v=3.3.5" rel="stylesheet">
    <link href="${pageContext.request.contextPath }/hui/css/font-awesome.min.css?v=4.4.0" rel="stylesheet">

    <!-- Data Tables -->
    <link href="${pageContext.request.contextPath }/hui/css/plugins/dataTables/dataTables.bootstrap.css" rel="stylesheet">

    <link href="${pageContext.request.contextPath }/hui/css/animate.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath }/hui/css/style.min.css?v=4.0.0" rel="stylesheet"><base target="_blank">

    <link href="${pageContext.request.contextPath }/layer/skin/default/layer.css">
    <link href="${pageContext.request.contextPath }/WEB-INF/css/ace.css">
    <title>welcome to  shiro!</title>
</head>
<body class="gray-bg">
<div class="wrapper wrapper-content animated fadeInRight">
    <div class="row">
        <div class="col-sm-12">
            <div class="ibox float-e-margins">
                <div class="ibox-content">
                    <div class="row" style="padding-bottom: 12px">
                        <div class="col-sm-12">
                                <form id="sysUsersearchForm" modelAttribute="sysUser" class="form-inline" action="/afcp_adc/back/sys/user/list" method="post">
                                        <div class="form-group">
                                            <span>登录名：</span>
                                             <input id="loginName" class=" form-control input-sm" name="loginName" value="" maxlength="50" type="text">
                                            <span>归属单位机构：</span>
                                            <div class="input-group">
                                                <input id="officeId" class=" form-control input-sm" name="office.id" value="" type="hidden">
                                                <input id="officeName" class=" form-control input-sm" name="office.name" readonly="readonly" value="" data-msg-required="" style="" type="text">
                                                <span class="input-group-btn">
                                                    <button id="officeButton" class="btn btn-sm btn-primary " type="button">
                                                             <i class="fa fa-search"></i>
                                                    </button>
                                                </span>
                                            </div>
                                            <span>姓   名：</span>
                                            <input id="name" class=" form-control input-sm" name="name" value="" maxlength="50" type="text">
                                        </div>
                                        <div class="form-group">
                                            <span>角色名：</span>
                                            <input id="loginName" class=" form-control input-sm" name="loginName" value="" maxlength="50" type="text">
                                            <span>归属单位机构：</span>
                                                <div class="input-group">
                                                    <input id="officeId" class=" form-control input-sm" name="office.id" value="" type="hidden">
                                                    <input id="officeName" class=" form-control input-sm" name="office.name" readonly="readonly" value="" data-msg-required="" style="" type="text">
                                                        <span class="input-group-btn">
                                                            <button id="officeButton" class="btn btn-sm btn-primary " type="button">
                                                                <i class="fa fa-search"></i>
                                                            </button>
                                                        </span>
                                                </div>
                                            <span>年   龄：</span>
                                            <select class="form-control input-select" path="userName" maxlength="50">
                                                <option>请选择人员</option>
                                                <c:forEach items="${fn:getDictList('sys_platLog_module')}" var='dict'>
                                                    <option value='${dict.value}' >${dict.label}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                </form>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-sm-12">
                            <div class="pull-left">
                                <button class="btn btn-white btn-sm " data-toggle="tooltip" data-placement="left" onclick="addSysUserDialog()" title="添加">
                                    <i class="fa fa-edit"></i> 添加
                                </button>
                                <%--<button class="btn btn-white btn-sm " data-toggle="tooltip" data-placement="left" onclick="sortOrRefresh()" title="刷新">--%>
                                    <%--<i class="glyphicon glyphicon-repeat"></i> 刷新--%>
                                <%--</button>--%>
                            </div>
                            <div class="pull-right">
                                <button  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search()" ><i class="fa fa-search"></i> 查询</button>
                                <button  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset()" ><i class="fa fa-refresh"></i> 重置</button>
                            </div>
                        </div>
                    </div>
                    <div class="row" >
                        <table id="dynamic-table" class="table table-striped table-bordered table-hover dataTables-example"></table>
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

<script src="${pageContext.request.contextPath }/WEB-INF/js/common/layer-local.js"></script>
<script>
    $(document).ready(function(){
        $("#dynamic-table_filter").css('display', 'block');
        dynamictable = $("#dynamic-table").DataTable(
                {
                    "bLengthChange" : false, /* 去掉每页显示多少条数据方法 */
                    "processing" : true,/*控制是否在数据加载时出现”Processing”的提示,一般在远程加载并且比较慢的情况下才会出现. 样式需要定义,否则比较丑.*/
                    "pageLength" : 10,/*默认*/
                    "lengthMenu" : [ [ 5, 10, 20, -1 ],
                        [ 5, 10, 20, "全部" ] ],
                    /*"deferRender": true,*/
                    "searching" : true,/*控制控件的搜索功能,如果为false,控件的搜索功能被完全禁用,而且默认搜索组件会被隐藏.*/
                    "serverSide" : true,/* 启用服务器端分页,当设为true时,列表的过滤,搜索和排序信息会传递到Server端进行处理,实现真翻页方案的必需属性.反之,所有的列表功能都在客户端计算并执行*/
                    "autoWidth" : false,/*用来启用或禁用自动列的宽度计算。通常被禁用作为优化（它需要一个有限的时间量来计算的宽度），默认值是true，所以通常将它设为false*/
                    /*  "order": [   //表示先对第1列进行升序排序，再按照第二列升序排序，最后按照第三列降序排序
                     [0, "desc"]
                     ], */
                    //因为需要多次初始化，所以需要设置允许销毁实例
                    "destroy" : true,
                    "bFilter" : false,
                    "orderMulti" : false, //启用多列排序
                    "order" : [], //取消默认排序查询,否则复选框一列会出现小箭头
                    /* "pagingType": "full_numbers", *///分页样式：simple,simple_numbers,full,full_numbers
                    /*   "table-layout":fix, */
                    "row-border" : true,
                    "oLanguage" : {
                        "sEmptyTable" : "没有记录",
                        "sProcessing" : "处理中，请稍候...",
                        "sLengthMenu" : "每页显示 _MENU_ 条记录",
                        "sZeroRecords" : "没有记录",
                        "sInfo" : " _START_ 到  _END_ 条记录,总记录数为 _TOTAL_ 条",
                        "sInfoEmpty" : "记录数为0",
                        "sInfoFiltered" : "(从全部记录数 _MAX_ 条中过滤)",
                        "sInfoPostFix" : "",
                        "sLoadingRecords" : "正在加载中,请稍候...",
                        "sSearch" : "搜索",
                        "sUrl" : "",
                        "oPaginate" : {
                            "sFirst" : "首页",
                            "sPrevious" : "上一页",
                            "sNext" : "下一页",
                            "sLast" : "末页"
                        }
                    },
                    ajax : {
                        url : "${pageContext.request.contextPath}/sysUser/list",
                        "type" : "post",
                    },
                    "fnDrawCallback"    : function(){
                        this.api().column(0).nodes().each(function(cell, i) {
                            cell.innerHTML =  i + 1;
                        });
                    },
                    "bDeferRender" : true,/*是否启用延迟加载：当你使用AJAX数据源时，可以提升速度。*/
                    "columns": [
                            {"sTitle":'<font size="2px">序号</font>',"sClass":"dt-center,gradeA","bSortable":false,"sWidth":"8%","data":null,"targets":0},
                            {"sTitle":'<font size="2px">登录名</font>', "sWidth": "10%","sClass" : "dt-center,gradeA", 'data': 'userName','bSortable': false,
                                'render':function(data, type, row, meta) {
                                    if(Common.isEmpty(data)){
                                        return "无数据"
                                    }
                                    return data;

                                }
                            },
                            {"sTitle":'<font size="2px">姓名</font>',"sWidth": "10%", "sClass" : "dt-center,gradeA", 'data': 'name','bSortable': false,
                                'render': function(data, type, row, meta) {
                                    if(Common.isEmpty(data)){
                                        return "无数据"
                                    }
                                    return data;
                                }},
                            {"sTitle":'<font size="2px">电话</font>', "sWidth": "10%","sClass" : "dt-center", 'data': 'tel','bSortable': false,
                                'render': function(data, type, row, meta) {
                                    if(Common.isEmpty(data)){
                                        return "无数据"
                                    }
                                    return data;
                                }},

                    ],
                    "columnDefs": [{"sTitle":'<font size="2px">操作</font>',
                        "data": "id",
                        "sWidth": "10%",
                        "sClass" : "dt-center",
                        'targets': [4],
                        'render': function(data, type, row, meta) {
                            var id ="\""+ row.id+"\"";
                            return "<button class='btn  btn-primary btn-outline btn-xs' onclick='openViewSysuserDialog("+id+")'>查看</button>" +
                                    "<button class='btn  btn-success btn-outline btn-xs' onclick='openEditSysuserDialog("+id+")'>修改</button>"+
                                    "<button class='btn  btn-danger btn-outline btn-xs' onclick='openDeleteSysuserDialog("+id+")'>删除</button>"
                                    ;
                        },
                        'bSortable': false
                    }]
                });
        /*去掉搜索框。*/
        $("#dynamic-table_filter").css("display", "none");
    })
    /*查看用户*/
    function openViewSysuserDialog(id) {
        var url="${pageContext.request.contextPath }/sysUser/form?id="+id;
        var name="查看用户";
        var width="60%"
        var high="80%"
        var button= [ '关闭']
        Layer.layOpen("",name ,url,width,high,button);
    }
    /*编辑看用户*/
    function openEditSysuserDialog(id){
        var url="${pageContext.request.contextPath }/sysUser/form?id="+id;
        var name="编辑用户";
        var width="60%"
        var high="80%"
        var button= ['确定', '关闭']
        Layer.layOpen("",name ,url,width,high,button);
    }
    /*删除用户*/
    function openDeleteSysuserDialog(id){
        var url="${pageContext.request.contextPath }/sysUser/form?id="+id;
        var queryDate=Common.form($("#sysUsersearchForm"))
        Layer.layConfirm("你确定要删除此条数据",
                function(){
                    dynamictable.search(queryDate).draw()
                },function(){

                }
         );
    }
    /*添加用户*/
    function addSysUserDialog(){
        var url="${pageContext.request.contextPath }/sysUser/form";
        console.info(url)
        var name="添加用户";
        var width="60%"
        var high="80%"
        var button= ['确定', '关闭']
        Layer.layOpen("",name ,url,width,high,button);
    }
</script>
</body>
</html>