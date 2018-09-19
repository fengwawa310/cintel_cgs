/**
 * 初始化加载
 */
var projectPath = getRootPath_web(); //项目路径
//查询字典中所有信息
var dicCommon = window.parent.dicCommon;
//查询所有单位机构
var dicUnit = window.parent.dicUnit;
//查询所有地区
var dicArea = window.parent.dicArea;


// 系统时间
var getDate = function () {
    var y = new Date().getFullYear()
    var m = new Date().getMonth() + 1
    var d = new Date().getDate()
    var h = new Date().getHours()
    var min = new Date().getMinutes()
    var s = new Date().getSeconds()
    today = y + '年' + (m < 10 ? '0' + m : m) + '月' + (d < 10 ? '0' + d : d) + '日 ' + (h < 10 ? '0' + h : h) + ':' + (min < 10 ? '0' + min : min) + ':' + (s < 10 ? '0' + s : s)
    return today
}

var l = ["日", "一", "二", "三", "四", "五", "六"];
var d = new Date().getDay();
var xingqi = "星期" + l[d];
setInterval(function () {
    $(".time").text(getDate() + ' ' + xingqi)
}, 1000)

//获取主机地址
function getRootPath_web() {
    //获取当前网址，如： http://localhost:8083/uimcardprj/share/meun.jsp
    var curWwwPath = window.document.location.href;
    //获取主机地址之后的目录，如： uimcardprj/share/meun.jsp
    var pathName = window.document.location.pathname;
    var pos = curWwwPath.indexOf(pathName);
    //获取主机地址，如： http://localhost:8083
    var localhostPaht = curWwwPath.substring(0, pos);
    //获取带"/"的项目名，如：/uimcardprj
    var projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);
    return (localhostPaht + projectName + "/");
}


function initMenu() {
    $.ajax({
        type: 'POST',
        url: getRootPath_web()+"/wel/menu/getMenu02",//发送请求
        dataType : "json",
        success: function(data) {
            $("#mainMenu").empty();
            $("#mainMenu").append(data.obj);
            localStorage.setItem("menu",data.obj);

            //首页默认高亮
            $("#m162DC9915F8F00").addClass('active');
        }
    });
}

function reloadMenu() {
    var menuStr = localStorage.getItem("menu");
    if(menuStr){
        $("#mainMenu").empty();
        $("#mainMenu").append(menuStr);
    }else {
        initMenu();
    }
}
//设置一级菜单高亮
function markIcon(firstMenuId,current) {
    //设置一级菜单高亮
    // console.log("firstMenuId"+firstMenuId);
    $('#'+firstMenuId.replace("'", "")).addClass('active');
    // $('#'+firstMenuId.replace("'", "")).find("span").css({
    //     "borderTopColor":"#031957",
    //     "borderBottomColor":"#031957"
    // });
    //设置当前位置
    $("#yemianweizhi").html(decodeURI(current));

    // //隐藏综合检索搜索框
    // $("#serchDiv").attr('class','hide');
}
//页面跳转
function jumpPage(href,firstMenuId,current) {
    console.log(firstMenuId);
    //中文需要转码
    var jumpHref = encodeURI(getRootPath_web()+href+"?firstMenuId="+firstMenuId+"&current="+current);
    //console.log(jumpHref);
    window.open(jumpHref);
}

function jumpToMessage() {
    jumpPage("/view/messageList.html","m29","消息列表");
}

function jumpToNotice() {
    jumpPage("/view/notice/noticeList.html","m29","系统公告");
}

function jumpToProposal() {
    jumpPage("/view/proposal/proposalList.html","m29","系统建议");
}
//给下拉框赋值key 泛型 id为下拉框的id
function selectDicCommon(key, id) {
    var jsonObj = dicCommon[key];
    var html = [];
    html.push("<option value =\"0\">--请选择--</option>")
    for (var prop in jsonObj) {
        html.push("<option value =\"" + prop + "\">" + jsonObj[prop] + "</option>");
    }
    $("#" + id).html(html.join(''));
}

//给下拉框赋值 id为下拉框的id---单位机构
function selectDicUnit(id) {
    //dicUnit.list 是所有单位机构的信息dicUnit.html是下拉框拼接好的option HTML代码
    $("#" + id).html(dicUnit.html);
}
//给下拉框赋值 key:省id id为下拉框的id---地区
function selectDicArea(id) {
    //dicUnit.list 是所有单位机构的信息dicUnit.html是下拉框拼接好的option HTML代码
    $("#" + id).html(dicArea.html);
}

//获取form以name创建对象
var Common = {};
Common.form = function (form) {
    this_ = form;
    var o = {};
    var a = this_.serializeArray();
    $.each(a, function (index, cur) {
        if (cur.value != undefined && cur.value != null && cur.value.trim() != "" ) {
            o[cur.name] = cur.value.trim();
        }
    });
    var data = JSON.stringify(o);
    return data;
};
//验证表单，不验证hidden
Common.validateForm = function (form) {
    var flag = 0;
    this_ = form;
    var o = {};
    var a = this_.serializeArray();
    $.each(a, function (index, cur) {
        if (cur.value != undefined && cur.value != null && cur.value.trim() != "" ) {
            o[cur.name] = cur.value.trim();
        } else {
            o[cur.name] = cur.value.trim();
            var typeName = $("[name='" + cur.name + "']").attr("type");
            var className = $("[name='" + cur.name + "']").attr("class");
            if (typeName != "hidden"&&className!=undefined&&className.indexOf("verification")!=-1)
            {//隐藏域中不用验证和 加了 class="verification"要验证
				var id = $(form).find("[name='" + cur.name + "']").attr("id");
                var val = $("#" + id).val();
                if ( val != undefined &&  val!= null && val.trim() != "")
                {//出现2个或2个以上的相同name时，在没有值的name框中弹框
                    for (var i=0; i<=index;i++){
                        var id = $(form).find("[name='" + cur.name + "']:eq("+i+")").attr("id");
                        if ($("#" + id).val() != null && $("#" + id).val() != "" && $("#" + id).val() != undefined) {
                            continue;
                        }else{
                            ++flag;
                            $("#" + id).focus();
                            layer.tips('请填写内容！', "#" + id);
                            return false;
                            break;
                        }
                    }
                }else{
                    ++flag;
                    $("#" + id).focus();
                    layer.tips('请填写内容！', "#" + id);
                    return false;
                }
            } else {
                return true;
            }
        }
    });
    //console.log(JSON.stringify(o));
    var data = "";
    if (flag == 0) {
        data = JSON.stringify(o);
    }
    return data;
};
//datatable的模糊查询 传入参数：模糊条件form的ID
function findSelect(formId) {
    var searchForm = $("#" + formId);
    var form = Common.form(searchForm);
    console.info(form);
    dynamictable.search(form).draw();
}


function addzero(v) {
    if (v < 10) return '0' + v;
    return v.toString();
}
/**
 * 获取当天日期 YYYY-MM-DD
 * @returns {String}
 */
function getToday() {
    var d = new Date();
    return d.getFullYear().toString() + '-' + addzero(d.getMonth() + 1) + '-' + addzero(d.getDate());
}
function singleValidate(obj) {
    if (obj.value == null || obj.value == "" || obj.value == undefined) {
        $(obj).focus();
        var id = $(obj).attr("id");
        layer.tips('请填写内容！', "#" + id);
    }
}

//校验是否为整数
function isInt(e) {
    var g = /^-?\d+$/;
    if (!g.test(e.value)) {
        layer.tips('只能输入整数！', "#" + e.id);
    }
}

//调用定时任务查询系统消息
function MessageTimeTask() {
    $.ajax({
        type: "post",
        url: getRootPath_web() + "MessageListHandler/count.action",
        dataType: "",
        success: function (data) {
            if (data != null) {
                $("#lingdang").text(data);
            }
            //setTimeout(MessageTimeTask, 1000 * 10);
        },
        error: function (data) {
           // setTimeout(MessageTimeTask, 1000 * 10);
        }
    })
}

//系统消息定时查询
function sysNoticeTask(){
    $.ajax({
        type: "post",
        url: getRootPath_web() + "/notice/countSysUserNoticeNoRead",
        dataType: "",
        success: function (data) {
            if (data != null) {
                //$("#message").text(data.data[0]);
            }
           // setTimeout(sysNoticeTask, 1000 * 10);
        },
        error: function (data) {
           // setTimeout(sysNoticeTask, 1000 * 10);
        }
    })
}

function logEntry(czLeixing, beizhu) {
    $.ajax({
        type: "post",
        url: getRootPath_web() + "sysLog/insertSysLog",
        dataType: "json",
        data: {
            czLeixing: czLeixing,
            beizhu: beizhu
        },
        success: function (data) {
            //console.log("log success" + data)
        },
        error: function (data) {
            //console.log("log fail" + data)
        }
    })
}

//重置按钮的事件
function reset(){
     //实现hidden里的值不清除，下拉文本输入框中的hidden会清除
    $('.search-condition input').each(function(index,element){
        var className= $(element).attr("type");
        if(className!="hidden"){
            $(element).val('');
        }else{
            var id= $(element).attr("id");
            if(id!=undefined&&id!=""&&id.indexOf("cgh")!=-1){
                $(element).val('');
            }
        }
    })
    $('.search-condition select').each(function (index, element) {
        $(element).get(0).selectedIndex = 0;
    })
}

function inputCheckVo(inputId, regexStr, msgStr) {
    this.inputIdStr = inputId;// input标签ID
    this.regexStr = regexStr;// 内容校验正则
    this.msgStr = msgStr;// 校验失败提示语
}

function checkInputs(checkVoArr) {
    if (!checkVoArr) {
        return false;
    }
    if (!(checkVoArr instanceof Array)) {
        return false;
    }
    for (no in checkVoArr) {
        var idStr = checkVoArr[no].inputIdStr;
        if (!idStr || idStr.length == 0) {
            continue;
        }
        var value = $("#" + idStr).val();
        if (!value || value.length == 0) {
            $("#" + idStr).focus();
            layer.tips('不能为空', "#" + idStr);
            return false;
        }
        var regexStr = checkVoArr[no].regexStr;
        if (!regexStr || regexStr.length == 0) {
            continue;
        }
        var re = new RegExp(regexStr);
        if (re.test(value)) {
            continue;
        }
        else {
            var msgStr = checkVoArr[no].msgStr;
            layer.tips('' + msgStr, "#" + idStr);
            return false;
        }
        // console.log("inputId:" + idStr + " ,inputValue:" + value + " ,regexStr:" + regexStr + " ,msgStr:" + msgStr);
    }
    return true;
}


//获得年月日  得到日期oTime
function getDateStr(str){
    var oDate = new Date(str),
        oYear = oDate.getFullYear(),
        oMonth = oDate.getMonth()+1,
        oDay = oDate.getDate(),
        oHour = oDate.getHours(),
        oMin = oDate.getMinutes(),
        oSen = oDate.getSeconds(),
        oTime = oYear +'-'+ getzf(oMonth) +'-'+ getzf(oDay) +' '+ getzf(oHour) +':'+ getzf(oMin) +':'+getzf(oSen);//最后拼接时间
    return oTime;
};
//补0操作
function getzf(num){
    if(parseInt(num) < 10){
        num = '0'+num;
    }
    return num;
}
/**
 * 下载
 * @param fileName
 * @param url
 */
function downLoadFile(fileName, url) {
    fileName=encodeURI(fileName)
    window.open(projectPath+"/fileCRUD/downLoadFile?fileName="
        + fileName + "&fileURL=" + url, "_blank");
}
/**
 * 上传
 * @param upSomeFile
 */
function upFile(upSomeFile) {
    layer.load(3, {shade: [0.3, '#393D49']});
    $.ajaxFileUpload({
        type : 'POST',
        dataType : 'json',
        url : projectPath+'/fileCRUD/upload', // 用于文件上传的服务器端请求地址
//            secureuri : false, // 是否需要安全协议，一般设置为false
        fileElementId : 'upSomeFile', // 文件上传域的ID
        success : function(data) {
            layer.closeAll('loading');
            if(data.flag){
                var imgUrl=data.url;
                $("#url").val(imgUrl);
                var fileName=imgUrl.split("$$")[1];
                $("#my_img").prop("src",projectPath+'/fileCRUD/downLoadFile?'+'fileName='+fileName+'&fileURL='+imgUrl);
                layer.alert("上传成功！");
            }else{
                layer.alert("上传失败，请重试！！");
            }
        },
        error : function(data) {
            layer.closeAll('loading');
            layer.alert("上传失败，请重试！！");
        }
    });
}



var dicCommon;
//查询字典中所有信息
$.ajax({
    type: 'POST',
    url: projectPath+"dic/findDicCommon",//发送请求
    cache: false,
    async: false,
    dataType : "json",
    success: function(data) {
        dicCommon=data;
        //console.log(data)
    }
});
var dicUnit;
//查询所有单位机构
$.ajax({
    type: 'POST',
    url: projectPath+"dic/findDicUnit",//发送请求
    cache: false,
    async: false,
    dataType : "json",
    success: function(data) {
        dicUnit=data;
    }
});
var dicArea;
//查询所有地区
$.ajax({
    type: 'POST',
    url: projectPath+"dic/findDicArea",//发送请求
    cache: false,
    async: false,
    dataType : "json",
    success: function(data) {
        //console.log(data);
        dicArea=data;
    }
});

//根据配置文件中的区域ID获取电话区号
function getAreacodeByGlobal() {
    var areacode;
    //同步获取结果返回
    $.ajax({
        type: 'POST',
        url: projectPath+"dic/getAreacodeByGlobal",//发送请求
        cache: false,
        async: false,
        dataType : "json",
        success: function(data) {
            // console.log(data.areacode);
            areacode =  data.areacode;
        }
    });
    return areacode;
}
// var fastFileUrl;
// //查询图片显示地址的IP和端口
// $.ajax({
//     type: 'POST',
//     url: projectPath+"dic/fastFileUrl",//发送请求
//     cache: false,
//     async: false,
//     dataType : "json",
//     success: function(data) {
//         //console.log(data);
//         fastFileUrl=data.fastFileUrl;
//     }
// });



function menuThing(){
    var thisURL = document.URL;
    var getval =thisURL.split('?')[1];
    if(getval!=undefined&&getval!=null&&getval!=""){
        if(getval.indexOf("firstMenuId")!=-1&&getval.indexOf("current")!=-1){
            console.log(getval)
            var getval1=getval.substring(getval.indexOf("firstMenuId"));
            var getval2=getval.substring(getval.indexOf("current"));
            var firstMenuId;
            var current;
            if(getval1.indexOf("&")!=-1){
                firstMenuId= getval1.substring(getval1.indexOf("=")+1,getval1.indexOf("&"));
            }else{
                firstMenuId= getval1.substring(getval1.indexOf("=")+1);
            }
            if(getval2.indexOf("&")!=-1){
                current = getval2.substring(getval2.indexOf("=")+1,getval2.indexOf("&"));
            }else {
                current = getval2.substring(getval2.indexOf("=")+1);
            }
            // var firstMenuId = getval.split("&")[0].split("=")[1];
            // var current = getval.split("&")[1].split("=")[1];
            if(current!="%E9%A6%96%E9%A1%B5"){
                addHeader('body');
            }
            reloadMenu();
            markIcon(firstMenuId,current);
            //定时任务
            MessageTimeTask();
            sysNoticeTask();//系统消息定时查询
        }
    }
}

function logout() {
    layer.confirm('确认要退出吗？', {
        btn: ['确认','取消'] //按钮
    }, function(){
        localStorage.removeItem("sysName");
        localStorage.removeItem("sysLogo");
        window.location.href = getRootPath_web() + "/login/logout.do";
    });
}

//弹框
var Layer={}
Layer.layOpen=function(name,url,width,high,callback){
    var flag=0;
    ii =layer.open({
        offset: 20,
        type: 2,
        title: name,
        fix: false,
//            btn:button,
        maxmin: true,
        shadeClose: true,
        area: [width, high],
        content: url,
        cancel: function(index){
            return callback();
            ++flag;
        },
        end: function () {
            if(flag==0){
                return callback();
            }
        }
    });
}

function jumpToMain() {
    window.open(getRootPath_web()+"/wel");
}

document.onkeydown =cdk;
function cdk(){
    if(event.keyCode ==13 && $('#serchInput').is(':focus')){
        serchEverywhere();
    }
}

// 综合搜索方法
function serchEverywhere() {
    //alert($('#serchInput').val());
    var key=$('#serchInput').val();
    var serchHref = encodeURI(getRootPath_web()+"/view/integrated/searching.html?key="+key);
    // //改变iframe的地址
    // $('#personIframe').attr("src", serchHref);
    //搜索弹框layer
    var name='搜索详情';
    var url=serchHref;
    var width='90%';
    var high='90%';
    var callback=function (){

    };
    //弹框
    window.parent.Layer.layOpen(name,url,width,high,callback);
//            logEntry("2","案件信息管理 查看案件ID为："+id+"详情");

}

var userName,sysLogo,sysName;


function getSysConfig() {
    $.ajax({
        type: 'POST',
        url: projectPath+"sysMenu/getSysConfig",//发送请求
        cache: false,
        async: false,
        dataType : "json",
        success: function(data) {
            console.log("sysMap"+JSON.stringify(data));
            sysLogo = data.sysLogo;
            sysName = data.sysName;
            localStorage.setItem("sysName",sysName);
            localStorage.setItem("sysLogo",sysLogo);
        }
    });
}

function addHeader(id,username) {

    sysName = localStorage.getItem("sysName");
    sysLogo = localStorage.getItem("sysLogo");

    if(username){
        userName = username;
        localStorage.setItem("userName",username);
    }else {
        userName = localStorage.getItem("userName");
    }

    var headerHtml = " <div class=\"navHeader\">\n" +
        "    <div class=\"col-lg-12 row no-padding no-margin header\" style='float: none'>\n" +
        "        <!--头部左侧logo部分-->\n" +
        "        <div class=\"col-lg-3 logoDiv\" style='width: 20%'>\n" +
        "            <img class=\"logoImg\" src=\"http://localhost:8080/mana_web/view/assets/images/gallery/logo2.png\" onclick=\"jumpToMain();\">\n" +
        "            <label class=\"sysTitle\">"+sysName+"</label>\n" +
        "        </div>\n" +
        "        <!--头部右侧-->\n" +
        "        <div class=\"col-lg-9 row no-padding no-margin rightHeaderDiv\" style='width: 80%'>\n" +
        "            <!--上部欢迎退出-->\n" +
        "            <div class=\"col-lg-12 no-margin\" style=\"height: 65px;font-size: 11px;float: none\">\n" +
        "            <ul class=\"nav ace-nav\" id=\"welcomeTitle\">\n" +
        "                <li>\n" +
        "                    <span href=\"\" class=\"time\"></span>\n" +
        "                </li>\n" +
        "                <li>\n" +
        "                    <span href=\"\">系统管理综合研判组</span>\n" +
        "                </li>\n" +
        "                <li>\n" +
        "                    <span href=\"\">"+userName+"</span>\n" +
        "                </li>\n" +
        "                <li>\n" +
        "                    <span href=\"\">欢迎你！！</span>\n" +
        "                </li>\n" +
        "                <li class=\"\">\n" +
        "                    <a  class=\"dropdown-toggle\" onclick=\"jumpToMessage();\" href=\"#\">\n" +
        "                        <i class=\"icon-bell-alt\"></i>\n" +
        "                        <span class=\"badge badge-important\" id=\"lingdang\"></span>\n" +
        "                    </a>\n" +
        "                </li>\n" +
        "                <li>\n" +
        "                    <a style=\"margin-left: 15px;margin-right: 6px\" onclick=\"logout();\">退出</a>\n" +
        "                </li>\n" +
        "            </ul>\n" +
        "        </div>\n" +
        "        </div>\n" +
        "    </div>\n" +
        "     <!--下方菜单-->\n" +
        "     <div class=\"col-lg-12 no-margin menuDiv no-padding\" style='float: none'>\n" +
        "         <div class=\"navbar-collapse collapse no-padding breadcrumbs\">\n" +
        "            <div class=\"col-md-7\" style=\"height: 40px\">\n" +
        "                <ul class=\"nav navbar-nav navbar-left down-menu\" id=\"mainMenu\" style=\"margin-left: 10px\">\n" +
        "                    \n" +
        "                </ul>\n" +
        "            </div>\n" +
        "             <div class=\"col-md-5\" style=\"height: 40px;\">\n" +
        "                 <ul class=\"breadcrumb hide\">\n" +
        "                     <li>\n" +
        "                         <span class=\"title\">当前位置：</span>\n" +
        "                         <span id=\"yemianweizhi\">主页</span>\n" +
        "                     </li>\n" +
        "                 </ul>\n" +
        "                 <div class=\"serchDiv\" id=\"serchDiv\">\n" +
        "                     <div class=\"col-md-10 no-padding serchInputDiv\">\n" +
        "                         <input id=\"serchInput\" class=\"serchInput\" placeholder=\"请输入案件编号、身份证号、手机号、车牌号、绰号\">\n" +
        "                     </div>\n" +
        "                     <div class=\"col-md-2 serchImgDiv\">\n" +
        "                         <img class=\"serchImg\" src=\"http://localhost:8080/mana_web/view/assets/images/gallery/serch.png\" onclick=\"serchEverywhere();\">\n" +
        "                     </div>\n" +
        "                 </div>\n" +
        "             </div>\n" +
        "         </div>\n" +
        "     </div>\n" +
        " </div>";

    $('#'+id).prepend(headerHtml);

}

function getNowFormatDateBySepertor(seperator) {
    var date = new Date();
    var seperator1 = seperator || '';
    var year = date.getFullYear();
    var month = date.getMonth() + 1;
    var strDate = date.getDate();
    var hour = date.getHours();//时  
    var minute = date.getMinutes();//分  
    var second = date.getSeconds();//秒  
    if (month >= 1 && month <= 9) {
        month = "0" + month;
    }
    if (strDate >= 0 && strDate <= 9) {
        strDate = "0" + strDate;
    }
    if (hour >= 0 && hour <= 9) {
    	hour = "0" + hour;
    }
    if (minute >= 0 && minute <= 9) {
    	minute = "0" + minute;
    }
    if (second >= 0 && second <= 9) {
    	second = "0" + second;
    }
    var currentdate = year + seperator1 + month + seperator1 + strDate + seperator1 + hour + seperator1 + minute + seperator1 + second;
    return currentdate;
}