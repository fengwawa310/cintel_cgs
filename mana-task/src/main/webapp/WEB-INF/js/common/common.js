var Common={}

/**
 * 日期转换二
 * */
Date.prototype.Format = function (fmt) { //author: meizz
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
/**
 * 日期格式化二，
 * fmt ：时间戳，
 *
 * pattent：格式化模式，"yyyy-MM-dd" 或者 yyyy-MM-dd hh:mm:ss 或yyyy年MM月dd hh时mm分ss秒
 *
 * 使用方式：common.dateFormat2(row,"yyyy-MM-dd hh:mm:ss ")
 * */

/*
* <fmt:formatDate value="${jzDateBaseStorage.slsj}" pattern="yyyy-MM-dd HH:mm:ss"/>
* */
Common.dateFormat2=function(fmt,patten) {
	if(fmt==null){
		return;
	}
	var   d=new Date(fmt);
	var   s=d.Format(patten);
	return s;
}

/*金额格式化*/
/**
 * <fmt:formatNumber type="currency" value="${financeCoordinationTask.outwardAmount}" pattern="#,#00.00#" maxFractionDigits="2"/>
 **/
/*金额转换js
 *
 * formatMoney(s,type)
 * 功能：金额按千位逗号分割
 * 参数：s，需要格式化的金额数值.
 * 参数：type,判断格式化后的金额是否需要小数位. 0，不要少数，1 ，要小数
 * 返回：返回格式化后的数值字符串.
 */
Common.formatMoney =function(s,type){
	if (/[^0-9\.]/.test(s))
		return "0";
	if (s == null || s == "")
		return "0";
	s = s.toString().replace(/^(\d*)$/, "$1.");
	s = (s + "00").replace(/(\d*\.\d\d)\d*/, "$1");
	s = s.replace(".", ",");
	var re = /(\d)(\d{3},)/;
	while (re.test(s))
		s = s.replace(re, "$1,$2");
	s = s.replace(/,(\d\d)$/, ".$1");
	if (type == 0) {// 不带小数位(默认是有小数位)
		var a = s.split(".");
		if (a[1] == "00") {
			s = a[0];
		}
	}
	return s;
}
/*处置进度格式化*/
Common.dateFormatRate=function(obj){
  var str=	obj.replace(/-/, "/")
 return str;
}

/**
 * 是否为空
 * @auther chensi
 * @version 2015-5-26 上午11:26:03
 */
Common.isEmpty = function(obj) {
	return obj === undefined || obj === null || obj === '';
}
/**
 * 裁剪字符串
 * @param str 待裁剪字符串
 * @param width 裁剪后的长度, 一个中文占两位
 * @return 裁剪后的字符串
 * @auther chensi
 */
Common.cutStr = function(str, width) {
	if (!str) {
		return null;
	}
	if (!width || width == 0) {
		return "";
	}
	var realLen = 0;
	var result = "";
	for ( var i = 0; i < width; i++) {
		if (str.charCodeAt(i) > 127) {
			realLen += 2;
		} else {
			realLen++;
		}
		if (realLen > width || i >= str.length) {
			break;
		}
		result += str.charAt(i);
	}
	return result;
}

/**
 * 字符串超出部分用..代替
 * @param str 待填充字符串
 * @param width 填充后的长度, 一个中文占两位
 * @return 裁剪后的字符串
 * @auther chensi
 */

/*
<c:if test="${fn:length(jzDateBaseStorage.ajmc)>12 }">
 ${fn:substring(jzDateBaseStorage.ajmc, 0, 12)}...
 </c:if>
 */
Common.fillStr = function(str, width) {
	if (!str) {
		return null;
	}
	if (!width || width == 0) {
		return "";
	}
	if (width < 2) {
		return "..";
	}
	var result = Common.cutStr(str, width);
	if (result.length < str.length) {
		result = result + "..";
	}
	return result;
}

/**
 * 扩展JS字符串功能, 字符串是否为空
 * @auther chensi
 * @version 2015-5-26 上午11:26:03
 */
String.prototype.isEmpty = function() {
	return Common.isEmpty(this);
};

/**
 * 扩展JS字符串功能, 去除字符串两边空格
 * @returns
 * @auther tom
 * @version 2015-6-27 下午4:35:22
 */
String.prototype.trim = function() {
	return this.replace(/(^\s*)|(\s*$)/g, "");
};
/**
 * 扩展JS字符串功能, 去除字符串两边空格
 * @returns
 * @auther tom
 * @version 2015-6-27 下午4:35:22
 */
Common.trim = function() {
	return this.replace(/(^\s*)|(\s*$)/g, "");
};
/**
 * 扩展JS字符串功能, 去除所有空格，第二个参数为g
 * @returns
 * @auther tom
 * @version 2015-6-27 下午4:35:22
 */
Common.trimAll=	function Trim(str)
{
	var is_global="g"
	var result;
	result = str.replace(/(^\s+)|(\s+$)/g,"");
	if(is_global.toLowerCase()=="g")
	{
		result = result.replace(/\s/g,"");
	}
	return result;
}
/*判断字符串长度*/
Common.strlen=function(str){
	var len = 0;
	for (var i=0; i<str.length; i++) {
		var c = str.charCodeAt(i);
		//单字节加1
		if ((c >= 0x0001 && c <= 0x007e) || (0xff60<=c && c<=0xff9f)) {
			len++;
		}
		else {
			len+=2;
		}
	}
	return len;
}
/*
*
* */
Common.ifPhone=function ifHave(str2){
	var str1 = "-"
	var s = str2.indexOf(str1);
	return(s);
}



/**
 * 序列化表单name=admin&sex=nan ==> {"name":"admin","sex":"nan"}输出对象为json对象
 * @auther chensi
 */
$.fn.serializeObject = function() {
	var o = {};
	var a = this.serializeArray();
	$.each(a, function() {
		if (o[this.name]) {
			if (!o[this.name].push) {
				o[this.name] = [ o[this.name] ];
			}
			o[this.name].push(this.value || '');
		} else {
			o[this.name] = this.value || '';
		}
	});
	var data=JSON.stringify(o)
	return data;
};

//时间限制
 Common.maxValue =function(){
	if($("#endTime").val()!=""){
		return $("#endTime").val();
	}
	return new Date();
 }

//金额限制
Common.regMoneyLeng12AndDot2 =function(data){
	if(/^\d{1,12}(\.\d{1,2})?$/.test(data)){
		return true;
	}
	return false;
}

Common.getPost=function(url,data,callback){
	$.post(url, data, function (res) {
		return callback(res);
	}, "json");
}

/*form表单的值回显*/
Common.formEdit=function(row,formInput){
	$.each(row, function(rowName,rowValue) {//table行传递过来的数据。
//		console.info("遍历传递的名"+rowName)
//		console.info("遍历传递值"+rowValue)
		$.each(formInput, function(index,inputCur) {//我们获得的表单的标签
//			console.info("遍历form获取当前标签名"+inputCur.name)
			if(inputCur.name==rowName){
				$(inputCur).val(rowValue)//表单元素被正确赋值，成功
			}
		})
	})
}
/**
 *	var searchForm=$("#wanganApplySearchForm");
 *	var form=Common.form(searchForm)
 *
 **/
Common.form=function(form){
	this_=form;
	var o = {};
	var a = this_.serializeArray();
	$.each(a, function(index,cur) {
		o[cur.name] = cur.value;
	});
	var data=JSON.stringify(o)
	return data;
}

/*
* 使用示例
*
*<div class="xuzhouWangAnApplyForm1">
	 <td class="sort">受害人姓名</td>
	 <td class="sort">
			 <input type="text" name="victimuser" id="victimuser">
	 </td>
	 <td  class="sort">电话</td>
	 <td class="center">
	 		<input  name="tel" type="text">
	 </td>
	 <td  class="sort">身份证号</td>
	 <td class="long" colspan="3">
	 		<input name="idcard" type="text">
	 </td>
 </div>
*
* */
/*form表单值获取*/
Common.simpleFormConvert=function(inputs){
	var obj1={};
	for(var j = 0;j<inputs.length;j++) {
		obj1[$(inputs[j]).attr('name')] = $(inputs[j]).val();
	}
	return obj1;
}
/*集合form表单值获取
*
*
* return  array数组
*
* */
Common.listFormConvert=function(list){
	var biklLen = list.length;
	var arr1 = [];
	for(var i=0;i<biklLen;i++){
		var obj2 = {};//存放input，select框的内容
		/*input的几个框框*/
		var bodyInput = list.eq(i).find('input');
		for(var j = 0;j<bodyInput.length;j++){
			obj2[$(bodyInput[j]).attr('name')] = $(bodyInput[j]).val();
		}
		/*select选择的几个框框*/
		var bodySelect = list.eq(i).find('select');
		for(var j = 0;j<bodySelect.length;j++){
			obj2[$(bodySelect[j]).attr('name')] = $(bodySelect[j]).val();
		}
		arr1.push(obj2)
	}
	return arr1;
}

/**
 *   自定义layer插件提示信息
 * @param msg  提示内容
 * @param top  距离上部位置
 * @param left 距离左侧位置
 */
Common.layerMsg=function(msg,top,left){
	var layerIndex;
	layerIndex=layer.msg(msg, {icon: 1}, function(){
		//关闭后的操作
	});
	layer.style(layerIndex, {
		width: '200px',
		height:'50px',
		top: top,
		left: left,
		marginTop: '-150px',
		marginLeft: '-200px'
	});
}

Common.getNowFormatDate=function () {
	var date = new Date();
	console.info(date)
	var seperator1 = "-";
	var seperator2 = ":";
	var month = date.getMonth() + 1;
	var strDate = date.getDate();
	if (month >= 1 && month <= 9) {
		month = "0" + month;
	}
	if (strDate >= 0 && strDate <= 9) {
		strDate = "0" + strDate;
	}
	var currentdate = date.getFullYear() + seperator1 + month + seperator1 + strDate
		+ " " + date.getHours() + seperator2 + date.getMinutes()
		+ seperator2 + date.getSeconds();
	return currentdate;
}
/*悬浮窗*/
Common.enlargeFunction=function(obj,data){
	var x=$(obj).text()
	console.info(data)
	layer.tips(data,obj, {
		tips: [2, '#0d409d'], time: 2500
	});
}
/*悬浮窗*/
Common.layOpen=function(obj,data,url){
	//官网欢迎页
	ii = top.layer.open({
		offset: 20,
		type: 1,
		title: '协同银行添加',
		fix: false,
		maxmin: true,
		shadeClose: true,
		area: ['80%', '90%'],
		content: url,
		end: function () {
//                         $('#dynamic-table-police').attr("style", "display:none");
		},
	});
}

