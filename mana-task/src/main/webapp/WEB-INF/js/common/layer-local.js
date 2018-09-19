var Layer={}

/**
 *   自定义layer插件提示信息
 * @param msg  提示内容
 * @param top  距离上部位置
 * @param left 距离左侧位置
 */
Layer.layerMsg=function(msg,top,left){
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

/*悬浮窗*/
Layer.enlargeFunction=function(obj,data){
	var x=$(obj).text()
	console.info(data)
	layer.tips(data,obj, {
		tips: [2, '#0d409d'], time: 2500
	});
}
/*悬浮窗*/
Layer.layOpen=function(obj,name,url,width,high,button){
	console.info(url)
		//官网欢迎页
		ii = top.layer.open({
			offset: 20,
			type: 2,
			title: name,
			fix: false,
			btn:button,
			maxmin: true,
			shadeClose: true,
			area: [width, high],
			content: url,
			end: function () {
//                         $('#dynamic-table-police').attr("style", "display:none");
			},
		});
	    //top.layer.title(name, ii)  //再改变当前层的标题
}

//询问框
Layer.layConfirm=function(msg,callBack1,callBack2) {
	top.layer.confirm(msg, {
		btn: ['确定', '取消'] //按钮
	}, function () {//确认按钮--重刷表单
		top.layer.closeAll()
		return callBack1();
	}, function () {//取消按钮

	});
}

