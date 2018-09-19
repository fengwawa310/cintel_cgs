package com.common;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URLDecoder;

/**
 * 请求参数获取工具类
 *
 * @author admin
 * @create 2017-12-12 10:15
 **/
public class GetRequestParamUtil {

    /*请求数据获取*/
    public static String getRequetMethod(HttpServletRequest request) {
        StringBuffer sb = new StringBuffer();
        try {
            request.setCharacterEncoding("UTF-8");
            BufferedReader br = new BufferedReader(new InputStreamReader((ServletInputStream) request.getInputStream(),"UTF-8"));
            String line = null;
            while ((line = br.readLine()) != null) {
                sb.append(URLDecoder.decode(URLDecoder.decode(line, "UTF-8"), "UTF-8"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
}
