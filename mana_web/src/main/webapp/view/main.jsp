<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>首页</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0"/>
    <%@include file="common/includeBaseSet.jsp" %>
    <script type="text/javascript" src="${jypath}/static/js/jquery/jquery.cookie.js"></script>
    <link rel="stylesheet" href="${jypath}/static/css/system/bootstrap/bootstrap.min.css"/>
    <link rel="stylesheet" href="${jypath}/static/css/system/ace/font-awesome.min.css"/>
    <link rel="stylesheet" href="${jypath}/static/css/system/jquery/jquery-ui.min.css" type="text/css"/>
    <!--[if IE 7]>
    <link rel="stylesheet" href="${jypath}/static/css/system/ace/font-awesome-ie7.min.css"/>
    <![endif]-->
    <link rel="stylesheet" href="${jypath}/static/css/system/bootstrap/bootstrap-responsive.min.css"/>
    <!-- ace styles -->
    <link rel="stylesheet" href="${jypath}/static/css/system/ace/ace.min.css"/>
    <!--[if lte IE 8]>
    <link rel="stylesheet" href="${jypath}/static/css/system/ace/ace-ie.min.css"/>
    <![endif]-->
    <link type="text/css" rel="stylesheet" href="${jypath}/static/css/system/ace/ace-rtl.min.css"/>
    <link type="text/css" rel="stylesheet" href="${jypath}/static/css/system/ace/ace-responsive.min.css"/>
    <link type="text/css" rel="stylesheet" href="${jypath}/static/css/system/ace/ace-skins.min.css"/>
    <!-- 日期控件 -->
    <%-- <link type="text/css" rel="stylesheet" href="${jypath}/static/css/system/jquery/datepicker.css" />
    <link type="text/css" rel="stylesheet" href="${jypath}/static/css/system/jquery/ui.jqgrid.css" /> --%>
    <link type="text/css" rel="stylesheet" href="${jypath}/static/plugins/tabs/css/tab-control.min.css"/>
    <link type="text/css" rel="stylesheet" href="${jypath}/static/css/system/system/index.css"/>
    <link type="text/css" rel="stylesheet" href="${jypath}/view/css/awesome.css"/>
    <%--<link type="text/css" rel="stylesheet" href="${jypath}/view/css/zTreeStyle.css"/>--%>
    <link type="text/css" rel="stylesheet" href="${jypath}/view/css/main.css"/>
    <link rel="stylesheet" type="text/css" href="${jypath}/view/css/jquery.dataTables.min.css" />
    <link rel="stylesheet" type="text/css" href="${jypath}/view/flipcountdown-master/jquery.flipcountdown.css" />
    <link rel="stylesheet" type="text/css" href="${jypath}/view/css/scroll.css" />
    <script src="${jypath}/view/js/common.js"></script>
    <link rel="stylesheet" href="${jypath}/view/css/commonStyle.css"/>
    <style>
        .nav .active > a {
            color: white !important;
        }
        .nav-tabs>li.active>a, .nav-tabs>li.active>a:hover, .nav-tabs>li.active>a:focus {
            background-color: #4874C2;
            border-color: #cedfff;
            border-top: 1px solid #cedfff;
            margin-top: 0px;
            box-shadow: none;
        }
        .operationImg{
            width: 20px;
            height: 20px;
            margin-right: 8px;
        }
        .dataTables_wrapper .dataTables_paginate .paginate_button {
            box-sizing: border-box;
            display: inline-block;
            min-width: 1.5em;
            padding: 0.1em 1em;
            margin-left: 2px;
            margin-top: 2px;
            text-align: center;
            text-decoration: none !important;
            cursor: pointer;
            color: #333 !important;
            border: 1px solid transparent;
            border-radius: 2px;
        }
        .textDiv{
            height: 100%;
            line-height: 72px;
            text-align: center;
        }
        .textSpanDiv{
            font-weight: bold;
            font-size: 21px;
            color: #4175C7;
            float: right;
            text-shadow:2px 2px 5px grey;
        }
        .mainLastDiv{
            position: absolute;
            width: 96%;
            bottom: 14px;
        }
        .mainSelect{
            width: 90%;
            border: 1px solid #D1E1FF;
        }
    </style>
</head>
<body id='indexBody'>

<div class="main-container row breadcrumbs no-padding" id="main-container">
    <%--专案表格--%>
    <div class="col-md-8 separatorDiv">
        <div class="wt100">
            <table id="dynamic-table" class="cell-border table table-striped table-bordered table-hover dataTable"  cellspacing="0" width="100%">
            </table>
        </div>
    </div>
    <%--右侧展示div--%>
    <div class="col-md-4 separatorDiv">
            <%--在研案件数--%>
        <div class="col-md-12 countDiv no-padding">
            <div class="col-md-5 textDiv">
                <span class="textSpanDiv">在研案件数：</span>
            </div>
            <div class="col-md-7" style="height: 100%;margin-top: 20px;">
                <div id="caseNum"></div>
            </div>
        </div>
            <%--组织数--%>
        <div class="col-md-12 countDiv no-padding">
            <div class="col-md-5 textDiv">
                <span class="textSpanDiv">组&nbsp;&nbsp;&nbsp;织&nbsp;&nbsp;&nbsp;数：</span>
            </div>
            <div class="col-md-7" style="height: 100%;margin-top: 20px;">
                <div id="orgNum"></div>
            </div>
        </div>
            <%--人员数--%>
        <div class="col-md-12 countDiv no-padding">
            <div class="col-md-5 textDiv">
                <span class="textSpanDiv">人&nbsp;&nbsp;&nbsp;员&nbsp;&nbsp;&nbsp;数：</span>
            </div>
            <div class="col-md-7" style="height: 100%;margin-top: 20px;">
                <div id="personNum"></div>
            </div>
        </div>
                <%--公告栏--%>
        <div class="col-md-12 countDiv02 no-padding">
            <div id="thinking" style="width: 100%;padding-top: 5px;">
                <ul id="list_thinking" >

                </ul>

            </div>
        </div>
                <%--浏览历史栏--%>
        <div class="col-md-12 countDiv02 no-padding">
            <div id="thinking02" style="width: 100%;padding-top: 5px;">
                <ul id="list_thinking02" >

                </ul>

            </div>
        </div>
                <%--快速链接--%>
        <div class="col-md-12 countDiv03 no-padding mainLastDiv">
            <div class="col-md-1 no-padding" style="height: 100%;line-height: 70px;text-align: right">
                <img src="http://localhost:8080/mana_web/view/assets/images/gallery/chain.png" style="width: 24px;height: 24px">
            </div>
            <div class="col-md-11 no-padding" style="height: 100%;line-height: 70px;text-align: center">
                <select id="fastHref" class="mainSelect" onchange="fastHref();">

                </select>
            </div>
        </div>
    </div>
</div>

<script src="${jypath}/static/js/ace/ace-extra.min.js"></script>
<script src="${jypath}/static/js/bootstrap/bootstrap.min.js"></script>
<script src="${jypath}/static/js/ace/typeahead-bs2.min.js"></script>
<script src="${jypath}/static/js/ace/ace-elements.min.js"></script>
<script src="${jypath}/static/js/ace/ace.min.js"></script>
<script src="${jypath}/static/js/jquery/jquery-ui.min.js"></script>
<script src="${jypath}/static/js/jquery/jquery.md5.js"></script>
<!-- 滚动条-->
<%-- <script src="${jypath}/static/js/jquery/jquery.slimscroll.min.js"></script> --%>
<!-- 饼图-->
<%-- <script src="${jypath}/static/js/jquery/jquery.easy-pie-chart.min.js"></script> --%>
<!-- 线状图 -->
<%-- <script src="${jypath}/static/js/jquery/jquery.sparkline.min.js"></script> --%>
<!-- 图表 -->
<%-- <script src="${jypath}/static/js/jquery/flot/jquery.flot.min.js"></script> --%>
<!-- 饼图 -->
<%-- <script src="${jypath}/static/js/jquery/flot/jquery.flot.pie.min.js"></script> --%>
<%-- <script src="${jypath}/static/js/jquery/flot/jquery.flot.resize.min.js"></script> --%>
<script src="${jypath}/static/plugins/tabs/js/tab-control.min.js"></script>
<script src="${jypath}/static/js/system/jy/jy.main.js"></script>
<script src="${jypath}/view/js/jquery.ztree.core.js"></script>
<script src="${jypath}/view/js/jquery.ztree.excheck.js"></script>
<script src="${jypath}/view/js/jquery.ztree.exedit.js"></script>
<script src="${jypath}/static/js/system/index/index.js"></script>
<%-- <script type="text/javascript" src="${jypath}/static/plugins/webuploader/cropper/jquery.js"></script> --%>
<script src="${jypath}/view/assets/js/layer/layer.js"></script>
 <script src="${jypath}/view/flipcountdown-master/jquery.flipcountdown.js"></script>
 <script src="${jypath}/view/js/jquery.dataTables.min.js"></script>
<script>

    var dynamictable;

    $(document).ready(function () {
        var userName = "${user.name}";
        addHeader('indexBody',userName);
        initMenu();

        //图标数量更新
        MessageTimeTask();
        sysNoticeTask();//系统消息定时查询

        $('#main-container').height($(window).height() - 125);

        $.ajax({
            type: 'POST',
            async: false,
            url: getRootPath_web()+"sysMenu/getFastHref",//发送请求
            dataType : "json",
            success: function(data) {
                $('#fastHref').append(data.fastHref);
            }
        });


        var logLi = "";
        //获取日志列表
        $.ajax({
            type: 'POST',
            async: false,
            url: getRootPath_web()+"sysLog/findLogList",//发送请求
            dataType : "json",
            success: function(data) {
                // var jsonObj = JSON.parse(data.data);//转换为json对象
                var jsonObj = data.data;//转换为json数组
                for(var i=0;i<jsonObj.length;i++){
                    logLi += "<li>\n" +
                        "<a href=\"#\" target=\"_blank\" class=\"gray normal\">\n" +
                        ""+jsonObj[i]+"</a>\n" +
                        "</li>"
                }
                $('#list_thinking02').append(logLi);
            }
        });

        var noticeLi = "";
        //获取日志列表
        $.ajax({
            type: 'POST',
            async: false,
            url: getRootPath_web()+"notice/getNoticeList",//发送请求
            dataType : "json",
            success: function(data) {
                // var jsonObj = JSON.parse(data.data);//转换为json对象
                var jsonObj = data.data;//转换为json数组
                for(var i=0;i<jsonObj.length;i++){
                    noticeLi += "<li>\n" +
                        "<a href=\"#\" target=\"_blank\" class=\"gray normal\">\n" +
                        ""+jsonObj[i]+"</a>\n" +
                        "</li>"
                }
                $('#list_thinking').append(noticeLi);
            }
        });

        // 专案表格代码
        $("#dynamic-table_filter").css('display', 'block');
        dynamictable = $("#dynamic-table").DataTable(
            {
                "bLengthChange" : false, /* 去掉每页显示多少条数据方法 */
                "processing" : true,/*控制是否在数据加载时出现”Processing”的提示,一般在远程加载并且比较慢的情况下才会出现. 样式需要定义,否则比较丑.*/
                "pageLength" : 5,/*默认*/
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
                    url : getRootPath_web()+"case/findCase?isArchive=0&isAbandon=0&isApproval=1",
                    "type" : "post"
                },
                /*序号自增长*/
                "fnDrawCallback" : function(){
                    this.api().column(0).nodes().each(function(cell, i) {
                        cell.innerHTML =  i + 1;
                    });
                },
                "bDeferRender" : true,/*是否启用延迟加载：当你使用AJAX数据源时，可以提升速度。*/
                "columns": [
                    {"sTitle":'<font>序号</font>','sWidth' :'8%',"sClass" :"dt-center", 'bSortable': false,
                        'render': function(data, type, row, meta) {
                            if(data==""||data==null){
                                return "--";
                            }
                            return data;
                        }},
                    {"sTitle":'<font>登记时间</font>',"sWidth":"15%","sClass":"dt-left", 'data': 'happenTimeUp','bSortable': false,
                        'render': function(data, type, row, meta) {
                            if(data==""||data==null){
                                return "--";
                            }
                            return data.substring(0,data.indexOf("."));
                        }},
                    {"sTitle":'<font>专案编号</font>',"sWidth":"10%","sClass":"dt-left", 'data': 'caseNo','bSortable': false,
                        'render': function(data, type, row, meta) {
                            if(data==""||data==null){
                                return "--";
                            }
                            return data;
                        }},
                    {"sTitle":'<font>专案名称</font>',"sWidth":"10%","sClass":"dt-left", 'data': 'caseTitle','bSortable': false,
                        'render': function(data, type, row, meta) {
                            if(data==""||data==null){
                                return "--";
                            }
                            return data;
                        }},

                    {"sTitle":'<font>专案种类</font>',"sWidth":"10%","sClass":"dt-left", 'data': 'caseClass','bSortable': false,
                        'render': function(data, type, row, meta) {
                            if(data==""||data==0||data=="0"||data==null){
                                return "--";
                            }
                            return dicCommon[19][data];
                        }},
                    {"sTitle":'<font>专案状态</font>',"sWidth":"10%","sClass":"dt-left", 'data': 'caseState','bSortable': false,
                        'render': function(data, type, row, meta) {
                            if(data==""||data==0||data=="0"||data==null){
                                return "--";
                            }
                            return dicCommon[18][data];
                        }},
                    {"sTitle":'<font>专案描述</font>',"sWidth":"27%","sClass":"dt-left", 'data': 'acceptUnitName','bSortable': false,
                        'render': function(data, type, row, meta) {
                            if(data==""||data==null){
                                return "--";
                            }
                            return data;
                        }}
                ],
                "columnDefs": [{"sTitle":'<font>操作</font>',
                    "sWidth":"10%",
                    "data": "id",
                    "sClass" : "dt-left",//字体居中dt-center
                    'targets': [7],
                    'render': function(data, type, row, meta) {
                        var data="'"+data+"'";
//								var caseNo="'"+row.caseNo+"'";
                        var html='<img href="#"  class="operationImg" src="http://localhost:8080/mana_web/view/assets/images/gallery/file.png">' +
                            '<img href="#" class="operationImg" src="http://localhost:8080/mana_web/view/assets/images/gallery/guide.png">';
                        return html;
                    },
                    'bSortable': false
                }]
            });
        $("#dynamic-table_filter").css("display", "none");

    });

    // 新闻滚动展示以及计数板展示
    $(function(){
        // 滚动速度为1秒，每隔6秒滚动一次
        // 公告新闻栏
        setInterval(function () {
            $('#thinking ul li:last').hide().insertBefore($("#thinking ul li:first")).slideDown(1000);
        }, 6000);
        // 滚动速度为1秒，每隔8秒滚动一次
        // 浏览历史新闻栏
        setInterval(function () {
            $('#thinking02 ul li:last').hide().insertBefore($("#thinking02 ul li:first")).slideDown(1000);
        }, 8000);


        // 在研案件数
        $('#caseNum').flipcountdown({
            tick:function(){
                var caseNum = 220;
                $.ajax({
                    type: 'POST',
                    async: false,
                    url: getRootPath_web()+"case/getCaseNum",//发送请求
                    dataType : "json",
                    success: function(data) {
                        console.log(data.caseNum);
                        caseNum = data.caseNum;
                    }
                });
                return caseNum;
            }
        });
        // 组织数
        $('#orgNum').flipcountdown({
            tick:function(){
                var gangNum = 4329;
                $.ajax({
                    type: 'POST',
                    async: false,
                    url: getRootPath_web()+"/suspecthandler/getGangNum",//发送请求
                    dataType : "json",
                    success: function(data) {
                        console.log(data.gangNum);
                        gangNum = data.gangNum;
                    }
                });
                return gangNum;
            }
        });
        // 人员数
        $('#personNum').flipcountdown({
            tick:function(){
                var suspectNum = 940821;
                $.ajax({
                    type: 'POST',
                    async: false,
                    url: getRootPath_web()+"/suspecthandler/getSuspectNum",//发送请求
                    dataType : "json",
                    success: function(data) {
                        console.log(data.suspectNum);
                        suspectNum = data.suspectNum;
                    }
                });
                return suspectNum;
            }
        });

    })

    // 快速链接操作
    function fastHref() {
        var fastHref=$("#fastHref").val();
        console.log(fastHref);
        if(fastHref != ""){
            window.open(fastHref);
        }
    }

</script>
</body>
</html>