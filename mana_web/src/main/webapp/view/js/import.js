var excelType = "";

/*EXCEL上传选择*/
var excelFile;

//重点人员导入点击事件
function importPerson(){
    $("#excelFile").click();
    excelType = "重点人员";
}

//关系人导入点击事件
// function importRelationship(){
//     $("#excelFile").click();
//     excelType = "关系人";
// }

//团伙案件导入点击事件
function importGang(){
    $("#excelFile").click();
    excelType = "团伙案件";
}
//个人个案导入点击事件
function importSingle(){
    $("#excelFile").click();
    excelType = "个人个案";
}
//赌档线索导入点击事件
function importGambling(){
    $("#excelFile").click();
    excelType = "赌档线索";
}
//线索导入点击事件
function importClue(){
    $("#excelFile").click();
    excelType = "线索";
}

//点击文件后处理
function handleFile(){
    console.log(excelFile);
    //获取excel名称
    var fileName = getFileName(excelFile.value);
    console.log(fileName);

    layer.open({
        title : "确认上传文件",
        content : "<span type=\"text\" class=\"fileSpan\" id=\"excelFileName\" disabled=\"disable\" value=\"\">"+fileName+"</span>",
        skin : 'layui-layer-lana',
        shadeClose : 'true',
        btn : [ '确定', '取消' ],
        yes : function(index, layero) {
            //根据自己需要变更
            if("重点人员" == excelType){
                //重点人员excel上传处理 仅供参考
                toImport("importSuspectHandler/upload");
            }else if("关系人" == excelType){
                //关系人excel上传处理 仅供参考
                toImport("RPDHan/upload");
            }else if("团伙案件" == excelType){
                //团伙案件excel上传处理
                toImport("importCase/importCase?type=5100");
            }else if("个人个案" == excelType){
                //个人个案excel上传处理
                toImport("importCase/importCase?type=5101");
            }else if("赌档线索" == excelType){
                //赌档线索excel上传处理
                 toImport("/dossierimportCase/importCase?type=0");
            }else if("线索" == excelType){
                //赌档线索excel上传处理
                toImport("/dossierimportCase/importCase?type=1");
            }
            else{
                //线索excel上传处理
            }
        },
        end:function () {
            //可以上传相同的文件
            $("#importExcel")[0].reset();
        }
    });
}

function toImport(url) {
    //加载层
    var loadIndex = layer.load(0, {shade: false});
    var data = new FormData($("#importExcel")[0]);
    $.ajax({
        type : "post",
        url : projectPath+url,
        data : data,
        dataType : "json",
        async : false,
        cache : false,
        contentType : false,
        processData : false,
        success : function(data) {
            console.log(data);

            var titleStr = data.data.flag ? "导入操作成功" : "导入操作失败";
            var msgStr = data.data.msg;
            // if (data.data.flag) {
            //     layer.msg(data.data.msg, {
            //         icon : 1,
            //         time : 2000
            //     });
            // } else {
            //     layer.msg(data.data.msg, {
            //         anim:6,
            //         icon : 2,
            //         time : 2000
            //     });
            // }
            var i = layer.open({
                title : titleStr,
                content : "<span type=\"text\" class=\"fileSpan\" id=\"excelFileName\" disabled=\"disable\" value=\"\">"+msgStr+"</span>",
                skin : 'layui-layer-lana',
                shadeClose : 'true',
                btn : [ '确定'],
                yes : function(index, layero) {
                    layer.close(i);
                }
            });
            layer.close(loadIndex);
        },
        error : function(data) {
            console.log(data);
            var i = layer.open({
                title : "导入操作失败",
                content : "<span type=\"text\" class=\"fileSpan\" id=\"excelFileName\" disabled=\"disable\" value=\"\">导入操作失败</span>",
                skin : 'layui-layer-lana',
                shadeClose : 'true',
                btn : [ '确定'],
                yes : function(index, layero) {
                    layer.close(i);
                }
            });
            layer.close(loadIndex);
            // layer.msg("上传错误!"+data, {
            //     icon : 2,
            //     time : 2000
            // });
        }
    });

}

//根据路径获取文件名称
function getFileName(filePath) {
    return filePath.substring(filePath.lastIndexOf("\\")+1,filePath.length);
}