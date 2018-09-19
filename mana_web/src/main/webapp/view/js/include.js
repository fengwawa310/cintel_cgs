/**
 * 初始化加载
 */
/**
 * 引用JS和CSS头文件
 */
var rootPath = getRootPath_web(); //项目路径
/**
 * 动态加载CSS和JS文件
 */
var dynamicLoading = {
    meta : function(){
        document.write('<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no" />');
    },
    css: function(path){
        if(!path || path.length === 0){
            throw new Error('argument "path" is required!');
        }
        document.write('<link rel="stylesheet" type="text/css" href="' + path + '">');
    },
    js: function(path, charset){
        if(!path || path.length === 0){
            throw new Error('argument "path" is required!');
        }
        document.write('<script charset="' + (charset ? charset : "utf-8") + '" src="' + path + '"></script>');
    }
};
//动态生成meta
dynamicLoading.meta();
//动态加载项目 CSS文件 和 JS文件
<!-- basic styles -->
dynamicLoading.css(rootPath + "view/assets/css/bootstrap.min.css");
dynamicLoading.css(rootPath + "view/assets/css/font-awesome.min.css");
<!-- ace styles -->
dynamicLoading.css(rootPath + "view/assets/css/ace.min.css");
dynamicLoading.css(rootPath + "view/css/jquery.dataTables.min.css");
//动态加载项目
dynamicLoading.js(rootPath + "/view/assets/js/jquery-2.0.3.min.js", "utf-8");
dynamicLoading.js(rootPath + "/view/js/jquery.dataTables.min.js", "utf-8");
dynamicLoading.js(rootPath + "/view/assets/js/datepicker/WdatePicker.js", "utf-8");
dynamicLoading.js(rootPath + "/view/assets/js/ace-extra.min.js", "utf-8");
dynamicLoading.js(rootPath + "/view/js/common.js", "utf-8");
dynamicLoading.css(rootPath + "view/css/commonStyle.css");
<!-- basic scripts -->
// <![endif]
dynamicLoading.js(rootPath + "/view/assets/js/bootstrap.min.js", "utf-8");
dynamicLoading.js(rootPath + "/view/assets/js/typeahead-bs2.min.js", "utf-8");
<!-- ace scripts -->
dynamicLoading.js(rootPath + "/view/assets/js/ace-elements.min.js", "utf-8");
dynamicLoading.js(rootPath + "/view/assets/js/ace.min.js", "utf-8");
<!-- page specific plugin scripts -->
dynamicLoading.js(rootPath + "/view/assets/js/jquery.dataTables.min.js", "utf-8");
dynamicLoading.js(rootPath + "/view/assets/js/jquery.dataTables.bootstrap.js", "utf-8");
<!-- inline scripts related to this page -->
dynamicLoading.js(rootPath + "/view/assets/js/layer/layer.js", "utf-8");
<!--拼音搜索输入框-->
<!-- select -->
dynamicLoading.js(rootPath + "/view/js/changtopy.js", "utf-8");
dynamicLoading.js(rootPath + "/view/js/getpyszm.js", "utf-8");
dynamicLoading.js(rootPath + "/view/js/jquery.multiseselect.js", "utf-8");
dynamicLoading.js(rootPath + "/view/js/jquery.singleselect.js", "utf-8");
dynamicLoading.css(rootPath + "view/css/selectbox.css");
// dynamicLoading.css(rootPath + "view/");
// dynamicLoading.js(rootPath + "/view/", "utf-8");


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
    return (localhostPaht+projectName+"/");
}