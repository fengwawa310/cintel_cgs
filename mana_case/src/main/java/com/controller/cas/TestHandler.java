package com.controller.cas;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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
     *  /test
     */
    @RequestMapping("")
    public void test(){
        System.out.println("ceshitong ............");
    }
}
