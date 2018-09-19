
//废弃
function setDpHeight() {
	//计算首页iframe的高度
	var wHeight = $(window).height() - $(".navHeader").height() - 4;
	console.log($(".navHeader").height());
	console.log(wHeight);
	$("#iframe_dp").height(wHeight);

};

//废弃
var timer;
function showMainMenu(isShow) {
	if (isShow) {

		window.clearInterval(timer)

		$('.css_notice_message').show();
		$('#li_show_main_menu_button').hide();
		$('#div_main_menu').show();
		$('#div_main_dp').hide();

		//跳转到消息页
		openMenu('1', 'menu2', 'menu0', '消息列表', '消息列表',
				'/view/messageList.html', 'null');
	} else {
		$('.css_notice_message').hide();
		$('#li_show_main_menu_button').show();
		$('#div_main_menu').hide();
		$('#div_main_dp').show();
		
		//document.getElementById("iframe_dp").contentWindow.postMessage("reflashMainShow","*");
		// timer = setInterval('document.getElementById("iframe_dp").contentWindow.postMessage("reflashMainShow","*")', 60000); //指定1分钟刷新一次
	}
}

//废弃
//打开菜单
function openMenu(type, id, parentId, menuName, fillMenuName, resUrl, target) {
	if ("index" == target) {
		showMainMenu(false);
		return;
	}

	if ('1' != type || "noset" == resUrl)
		return;
	//设置当前位置
	$("#yemianweizhi").html(fillMenuName);
	//清空
	jQuery("div.tab-control div.main").empty();

	//设置高度
	jQuery("div.tab-control div.main").css("height",
			(jQuery(window).height() - 120) + "px");

	if ('/' == resUrl.substring(0, 1)) {
		var urlpath = jypath + resUrl + "?menu=" + id;
		jQuery("div.tab-control div.main")
				.append(
						'<iframe src="'
								+ urlpath
								+ '" scrolling="auto" frameborder="0" reload=""></iframe>');
	} else {
		//TabControlAppend(id,menuName,resUrl);
		jQuery("div.tab-control div.main")
				.append(
						'<iframe src="'
								+ resUrl
								+ '" scrolling="auto" frameborder="0" reload=""></iframe>');
	}
}

//废弃
function getMenu(layer, ref) {
	$("#menu_li_id").empty();
	JY.Ajax.doRequest(null, jypath + '/wel/menu/getMenu', {
		layer : layer,
		ref : ref
	}, function(data) {
		$("#menu_li_id").html(data.obj);
	});
}

