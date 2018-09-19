function jumpPage02(pageContent) {
    var type = pageContent.split("?")[0];
    var inforNo = pageContent.split("?")[1];
    console.log(type);
    console.log(inforNo);
    localStorage.setItem("inforNo",inforNo);
    switch (type){
        //情报接收通知
        case "3101":
            jumpPage("/view/intelligence/intelligenceJieShouList.html","m18","情报研判接收");
            break;
        //情报接收审核通知
        case "3102":
            jumpPage("/view/intelligence/intelligenceJieShouList.html","m18","情报研判接收");
            break;
        //情报上报通知
        case "3103":
            jumpPage("/view/intelligence/intelligenceLowerList.html","m18","情报研判上报");
            break;
        //情报处理通知
        case "3104":
            jumpPage("/view/intelligence/intelligenceResult.html","m18","情报研判处理");
            break;
    }
}


function getPageinfoNo(){
    // var thisURL = document.URL;
    // var getval =thisURL.split('?')[1];
    // var infoTag = getval.split("=")[0];
    // var inforNo = "";
    // if ("inforNo" == infoTag){
    //     inforNo = getval.split("=")[1];
    // }
    return localStorage.getItem("inforNo");
}