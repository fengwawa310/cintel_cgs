<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
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

    <!-- Data Tables -->
    <link href="${pageContext.request.contextPath }/hui/css/plugins/dataTables/dataTables.bootstrap.css" rel="stylesheet">

    <link href="${pageContext.request.contextPath }/hui/css/animate.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath }/hui/css/style.min.css?v=4.0.0" rel="stylesheet"><base target="_blank">

    <link href="${pageContext.request.contextPath }/layer/skin/default/layer.css">

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
                                <form id="searchForm" class="form-inline" action="/afcp_adc/back/sys/user/list" method="post">
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
                                            <input id="name" class=" form-control input-sm" name="name" value="" maxlength="50" type="text">
                                        </div>
                                </form>

                        </div>
                    </div>
                    <div class="row">
                        <div class="col-sm-12">
                            <div class="pull-left">
                                <button class="btn btn-white btn-sm " data-toggle="tooltip" data-placement="left" onclick="sortOrRefresh()" title="添加">
                                    <i class="fa fa-edit"></i> 添加
                                </button>
                                <button class="btn btn-white btn-sm " data-toggle="tooltip" data-placement="left" onclick="sortOrRefresh()" title="刷新">
                                    <i class="glyphicon glyphicon-repeat"></i> 刷新
                                </button>
                            </div>
                            <div class="pull-right">
                                <button  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search()" ><i class="fa fa-search"></i> 查询</button>
                                <button  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset()" ><i class="fa fa-refresh"></i> 重置</button>
                            </div>
                        </div>
                    </div>
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
                        </table>

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
    $(document).ready(function(){
        var table=$(".dataTables-example").dataTable({
            "bLengthChange" : false,
            //因为需要多次初始化，所以需要设置允许销毁实例
            "destroy" : true,
            "bFilter" : false,
            "orderMulti" : false, //启用多列排序
            "order" : [], //取消默认排序查询,否则复选框一列会出现小箭头
        });
        $(".dataTables_filter").css("display", "none")
        /*可编辑表格初始化*/
        var oTable=$("#editable").dataTable();
        oTable.$("td").editable("../example_ajax.php",
                {
                    "callback":function(sValue,y){
                                var aPos=oTable.fnGetPosition(this);
                                oTable.fnUpdate(sValue,aPos[0],aPos[1])
                                    },
                    "submitdata":function(value,settings){
                        return{"row_id":this.parentNode.getAttribute("id"),"column":oTable.fnGetPosition(this)[2]}
                    },
                    "width":"90%","height":"100%"
        } )

    });

    function fnClickAddRow(){$("#editable").dataTable().fnAddData(["Custom row","New row","New row","New row","New row"])};
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