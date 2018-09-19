/**
 * Created by admin on 2018/3/1.
 */
var Common={}
/**
 * 日期转换二
 * */
Date.prototype.Formats = function (fmt) { //author: meizz
    var o = {
        "M+": this.getMonth() + 1, //月份
        "d+": this.getDate(), //日
        "h+": this.getHours(), //小时
        "m+": this.getMinutes(), //分
        "s+": this.getSeconds(), //秒
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度
        "S": this.getMilliseconds() //毫秒
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
        if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
}
/*
 * <fmt:formatDate value="${jzDateBaseStorage.slsj}" pattern="yyyy-MM-dd hh:mm:ss"/>
 * */
Common.dateFormat2=function(fmt,patten) {
    if(fmt==null){
        return;
    }
    var   d=new Date(fmt);
    var   s=d.Formats(patten);
    return s;
}
/**
 * 是否为空
 * @auther chensi
 * @version 2015-5-26 上午11:26:03
 */
Common.isEmpty = function(obj) {
    return obj === undefined || obj === null || obj === '';
}
//et_tipoff表operateType
Common.operateType=function(data){
    var response="";
    if(data=="1"){
        response="未审核"
    }else if(data=="2") {
        response="已审核";//返回html开关代码
    }else if(data=="3"){
        response="已下发"
    }else if(data=="4"){
        response="已签收"
    }else if(data=="5"){
        response="研判上报"
    }else if(data=="6"){
        response="已复核"
    }else {
        response="未定义状态"
    }
    return response;
}