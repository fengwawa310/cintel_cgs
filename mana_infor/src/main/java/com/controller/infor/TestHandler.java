package com.controller.infor;

import com.common.GetRequestParamUtil;
import com.common.utils.HttpResponseDataVO;
import com.common.utils.PageVO;
import com.common.utils.ResponseVO;
import com.common.utils.httpclient.PageListVO;
import com.entity.sys.SysUser;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * 测试类
 *
 * @author admin
 * @create 2017-12-29 16:29
 **/

@RequestMapping("/test")
@Controller
public class TestHandler {

    /**
     *    /test
     */
    @RequestMapping(value="",produces = "application/json;charset=UTF-8" )
    public @ResponseBody PageListVO test(HttpServletRequest request, SysUser sysUser, PageVO pageVO){
        //一、类方法参数接收
//
        /*二、流方式接收--传递是json数据时*/
//        String requetMethod = GetRequestParamUtil.getRequetMethod(request);
        //数据转换请用gson
        System.out.println("ceshitong ............");

        /*构造返回*/
        SysUser sysUser1 = new SysUser();

        sysUser1.setId("fanhui");
        sysUser1.setUserName("tom");
        PageListVO<SysUser> responseVO = new PageListVO(PageListVO.CodeType.SUCCESS, Arrays.asList(sysUser1));
        return  responseVO ;
    }
}
