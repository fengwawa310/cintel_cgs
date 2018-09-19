$(function () {
    var table = $('#example').DataTable({
        "dom": '<"top"f >rt<"bottom"ilp><"clear">',//dom定位
        "dom": 'tiprl',//自定义显示项
        "lengthMenu": [
            [10]
        ],//每页显示条数设置
        "lengthChange": false,//是否允许用户自定义显示数量
        "bPaginate": true, //翻页功能
        "sPaginationType": "full_numbers",
        "bFilter": false, //列筛序功能
        "searching": true,//本地搜索
       // "ordering": true, //排序功能
        "Info": true,//页脚信息
        "autoWidth": true,//自动宽度
        "oLanguage": {//国际语言转化
            "sLengthMenu": "显示 _MENU_ 记录",
            "sZeroRecords": "对不起，查询不到任何相关数据",
            "sEmptyTable": "未有相关数据",
            "sLoadingRecords": "正在加载数据-请等待...",
            "sInfo": "当前显示 _START_ 到 _END_ 条，共 _TOTAL_ 条记录。",
            "sInfoEmpty": "当前显示0到0条，共0条记录",
            "sInfoFiltered": "（数据库中共为 _MAX_ 条记录）",
           // "sProcessing": "<img src='../resources/user_share/row_details/select2-spinner.gif'/> 正在加载数据...",
            "sUrl": "",
            //多语言配置文件，可将oLanguage的设置放在一个txt文件中，例：Javascript/datatable/dtCH.txt
            "oPaginate": {
                "sFirst": "首页",
                "sPrevious": " 上一页 ",
                "sNext": " 下一页 ",
                "sLast": " 尾页 "
            }
        }
    });

    //添加索引列
    table.on('order.dt search.dt',
        function () {
            table.column(0, {
                search: 'applied'
                //order: 'applied'
            }).nodes().each(function (cell, i) {
                cell.innerHTML = i + 1;
            });
        }).draw();


    //checkbox全选
    $("#checkAll").on("click", function () {
        if ($(this).prop("checked") === true) {
            $("input[name='checkList']").prop("checked", $(this).prop("checked"));
            $('#example tbody tr').addClass('selected');
        } else {
            $("input[name='checkList']").prop("checked", false);
            $('#example tbody tr').removeClass('selected');
        }
    });
})

//设置日期时间控件
$('#dateStart').datetimepicker({
    language: 'zh-CN',//显示中文
    format: 'yyyy-mm-dd',//显示格式
    minView: "month",//设置只显示到月份
    initialDate: new Date(),
    autoclose: true,//选中自动关闭
    todayBtn: true,//显示今日按钮
    locale: moment.locale('zh-cn')
});
$('#dateEnd').datetimepicker({
    language: 'zh-CN',//显示中文
    format: 'yyyy-mm-dd',//显示格式
    minView: "month",//设置只显示到月份
    initialDate: new Date(),
    autoclose: true,//选中自动关闭
    todayBtn: true,//显示今日按钮
    locale: moment.locale('zh-cn')
});
//默认获取当前日期
var today = new Date();
var nowdate = (today.getFullYear()) + "-" + (today.getMonth() + 1) + "-" + today.getDate();
//对日期格式进行处理
var date = new Date(nowdate);
var mon = date.getMonth() + 1;
var day = date.getDate();
var mydate = date.getFullYear() + "-" + (mon < 10 ? "0" + mon : mon) + "-" + (day < 10 ? "0" + day : day);
document.getElementById("dateStart").value = mydate;
document.getElementById("dateEnd").value = mydate;