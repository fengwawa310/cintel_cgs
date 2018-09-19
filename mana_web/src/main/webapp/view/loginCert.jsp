<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*,com.common.consts.Global"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="jypath" value="${pageContext.request.contextPath}"/>
<script>
    var jypath = '${jypath}';
</script>
<%
	String sysName = Global.getUnicodeConfig("sys.name"); // 属性名
	String sysLogo = Global.getConfig("sys.logo"); // 属性名

	String original=null,errDesc = null;
	original = request.getAttribute("original")==null?null:request.getAttribute("original").toString();
	errDesc = request.getAttribute("errDesc")==null?null:request.getAttribute("errDesc").toString();
%>
<html>
<head>
	<title>扫黑证书认证</title>
	<link href="${jypath}/view/css/commonStyle.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="${jypath}/view/assets/js/jquery-2.0.3.min.js"></script>
	<script src="${jypath}/view/js/common.js"></script>

    <link rel="stylesheet"
          href="${jypath}/view/assets/css/bootstrap.min.css">
    <link rel="stylesheet"
          href="${jypath}/view/assets/css/bootstrapValidator.min.css">
    <link rel="stylesheet"
          href="${jypath}/view/assets/css/font-awesome.min.css" />
    <link rel="stylesheet" href="${jypath}/view/css/login.css">
	<script src="${jypath}/view/assets/js/bootstrapValidator.min.js"></script>
	<script src="${jypath}/view/assets/js/layer/layer.js"></script>
	<script type="text/javascript">
	if(!!window.ActiveXObject || "ActiveXObject" in window){
		document.write('<object id="JITDSignOcx" classid="clsid:B0EF56AD-D711-412D-BE74-A751595F3633"  codebase="JITComVCTK_S.cab#version=2,0,24,40"></object>')
	}else{
		alert("请使用IE浏览器（IE6-IE8）！");
		document.write('<embed id="JITDSignOcx" type="application/x-jit-sign-vctk-s-plugin-boc" width="0" height="0"/>');
	}
	</script>
    <style>
        .layui-layer-dialog .layui-layer-content {
            color:black;
        }
    </style>
</head>
<body>
<div class="container wrap1">
	<h1 class="mg-b20 text-center">
		<img class="img-logo"
			 src="${jypath}<%=sysLogo%>"><%=sysName%>
	</h1>
	<div class="col-sm-8 col-md-5 center-auto pd-sm-50 pd-xs-20 main_content">
		<p class="text-center font16" style="font-size: 16px">登录认证</p>
		<form id="loginForm" action="${jypath}/loginCert/loginCert.do">
			<input id="authMode" name="authMode" type="hidden" value="cert">
			<div class="form-group mg-t20">
				颁发者DN：
				<input class="form-control login_input" id="RootCADN" value="CN=DemoCA, O=JIT, C=CN"
					   style="margin: 8px 0px;padding: 0px 5px"/>
				<font color="red">(过滤证书选择框中的证书)</font>
				<input type="hidden" id="signed_data" name="signed_data" />
				<input type="hidden" id="original_jsp" name="original_jsp" />
			</div>
			<input type="submit" value="认    证" class="login_btn"  onclick="doDataProcess();"/>
		</form>
	</div>
</div>
<div class="copy-right">
	<a href="#" target="_blank">版本号：v1.3.2</a>
</div>
</body>

<script type="text/javascript">

    $(window).ready(function () {
        //获取配置文件信息
        getSysConfig();
        var errDesc = '<%=errDesc%>';
        console.log(errDesc);
        if(errDesc != null && errDesc != 'null' && errDesc != ''){
            layer.msg(errDesc, {
                icon : 2,
                time : 2000
            });
		}
    });

    //根据原文和证书产生认证数据包
    function doDataProcess(){
        var Auth_Content = '<%=original%>';
        var DSign_Subject = document.getElementById("RootCADN").value;
        // if(Auth_Content==""){
        //     alert("认证原文不能为空!");
        // }else{
        //     var InitParam = "<?xml version=\"1.0\" encoding=\"gb2312\"?><authinfo><liblist><lib type=\"CSP\" version=\"1.0\" dllname=\"\" ><algid val=\"SHA1\" sm2_hashalg=\"sm3\"/></lib><lib type=\"SKF\" version=\"1.1\" dllname=\"SERfR01DQUlTLmRsbA==\" ><algid val=\"SHA1\" sm2_hashalg=\"sm3\"/></lib><lib type=\"SKF\" version=\"1.1\" dllname=\"U2h1dHRsZUNzcDExXzMwMDBHTS5kbGw=\" ><algid val=\"SHA1\" sm2_hashalg=\"sm3\"/></lib><lib type=\"SKF\" version=\"1.1\" dllname=\"U0tGQVBJLmRsbA==\" ><algid val=\"SHA1\" sm2_hashalg=\"sm3\"/></lib></liblist></authinfo>";
        //     JITDSignOcx.Initialize(InitParam);
        //     if (JITDSignOcx.GetErrorCode() != 0) {
        //         alert("初始化失败，错误码："+JITDSignOcx.GetErrorCode()+" 错误信息："+JITDSignOcx.GetErrorMessage(JITDSignOcx.GetErrorCode()));
        //         JITDSignOcx.Finalize();
        //         return false;
        //     }
        //     //控制证书为一个时，不弹出证书选择框
        //     JITDSignOcx.SetCertChooseType(1);
        //     JITDSignOcx.SetCert("SC","","","",DSign_Subject,"");
        //     if(JITDSignOcx.GetErrorCode()!=0){
        //         alert("错误码："+JITDSignOcx.GetErrorCode()+"　错误信息："+JITDSignOcx.GetErrorMessage(JITDSignOcx.GetErrorCode()));
        //         JITDSignOcx.Finalize();
        //         return false;
        //     }else {
        //         var temp_DSign_Result = JITDSignOcx.DetachSignStr("",Auth_Content);
        //         if(JITDSignOcx.GetErrorCode()!=0){
        //             alert("错误码："+JITDSignOcx.GetErrorCode()+"　错误信息："+JITDSignOcx.GetErrorMessage(JITDSignOcx.GetErrorCode()));
        //             JITDSignOcx.Finalize();
        //             return false;
        //         }
        //
        //         JITDSignOcx.Finalize();
        //         //如果Get请求，需要放开下面注释部分
        //         //	 while(temp_DSign_Result.indexOf('+')!=-1) {
        //         //		 temp_DSign_Result=temp_DSign_Result.replace("+","%2B");
        //         //	 }
        //         document.getElementById("signed_data").value = temp_DSign_Result;
        //     }
        // }
        document.getElementById("original_jsp").value = Auth_Content;
        document.forms[0].submit();
    }
</script>
</html>