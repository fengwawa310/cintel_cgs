package com.controller.alarm;

import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

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
    public void test(HttpServletRequest request, HttpServletResponse response){
        try {
            response.setContentType("text/plain");
            response.setHeader("Pragma", "No-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expires", 0);
            Map<String,String> map = new HashMap<String,String>();
            map.put("result", "content");
            PrintWriter out = response.getWriter();
            JSONObject resultJSON = JSONObject.fromObject(map); //根据需要拼装json
            String jsonpCallback = request.getParameter("callback");//客户端请求参数
            out.println(jsonpCallback+"("+resultJSON.toString(1,1)+")");//返回jsonp格式数据
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
