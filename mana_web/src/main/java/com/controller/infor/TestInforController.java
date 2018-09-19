package com.controller.infor;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

//import com.controller.http.HttpClientUtil;
import com.common.utils.PageVO;
import com.common.utils.httpclient.ObjectToMapGen;
import com.entity.sys.SysUser;

/**
 * 情报管理controller
 *
 * @author admin
 * @create 2018-01-02 10:16
 **/

@RequestMapping("/infor")
@Controller
public class TestInforController {

    /**
     *  /infor/test
     */
    @RequestMapping("/test")
        public void test(HttpServletRequest request) throws Exception {
        String uid =(String) request.getAttribute("uid");
        SysUser user = (SysUser)request.getAttribute("user");
        /*正常路径*/
        String serverName="/test";
        PageVO pageVO = new PageVO(1,2);

        ObjectToMapGen objectToMapGen = new ObjectToMapGen(user, pageVO);
//        String s = HttpClientUtil.postPack("infor", serverName, objectToMapGen.getMap());
//        PageListVO execute = HttpClientComUtils .execute("infor", serverName, objectToMapGen.getMap(), SysUser.class);
//        System.out.println(execute);
    }


}
