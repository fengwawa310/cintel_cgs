package com.controller.login.logincert;

import com.common.consts.Global;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.XMLWriter;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * @Author: sky
 * @Description:生成认证原文  包含服务器和网管两种生成方式
 * @Date: Create in 下午2:45 2018/4/17
 */
public class GenerateOraginalData {

    /******************************* 报文公共部分 ****************************/
    /** 报文根结点 */
    private static final  String MSG_ROOT = "message";
    /** 报文头结点 */
    private static final  String MSG_HEAD = "head";
    /** 报文体结点 */
    private static final  String MSG_BODY = "body";
    /** 服务版本号 */
    private static final  String MSG_VSERSION = "version";
    /** 服务版本值 */
    private static final  String MSG_VSERSION_VALUE = "1.0";
    /** 服务类型 */
    private static final  String MSG_SERVICE_TYPE = "serviceType";
    /** 服务类型值 */
    private static final  String MSG_SERVICE_TYPE_VALUE = "OriginalService";
    /** 报文体 应用ID */
    private static final  String MSG_APPID = "appId";
    /******************************* 响应报文 ****************************/
    /** 报文体 认证结果集状态 */
    private static final  String MSG_MESSAGE_STATE = "messageState";
    /** 响应报文消息码 */
    private static final  String MSG_MESSAGE_CODE = "messageCode";
    /** 响应报文消息描述 */
    private static final  String MSG_MESSAGE_DESC = "messageDesc";
    /** 报文体 认证原文 */
    private static final  String MSG_ORIGINAL = "original";
    /*********************************************************************/

    /** 认证地址 */
    private static final String KEY_AUTHURL = "authURL";
    /** 应用标识 */
    private static final String KEY_APP_ID = "appId";


    /**
     * 应用服务器产生认证原文
     */
    public static String generateRandomNum() {
        /**************************
         * 第二步 服务端产生认证原文   *
         **************************/
        String num = "1234567890abcdefghijklmnopqrstopqrstuvwxyz";
        int size = 6;
        char[] charArray = num.toCharArray();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < size; i++) {
            sb.append(charArray[((int) (Math.random() * 10000) % charArray.length)]);
        }
        return sb.toString();
    }


    /**
     * 连接网关服务器产生认证原文
     * @return
     * @throws DocumentException
     */
    public static String generateRandomNumFromGagewayServer() throws DocumentException{
        String errCode = null, errDesc = null;
        byte[] messagexml = null;
        /*** 1 组装认证原文请求报文数据 ** 开始 **/
        Document reqDocument = DocumentHelper.createDocument();
        Element root = reqDocument.addElement(MSG_ROOT);
        Element requestHeadElement = root.addElement(MSG_HEAD);
        Element requestBodyElement = root.addElement(MSG_BODY);
        /* 组装报文头信息 */
        requestHeadElement.addElement(MSG_VSERSION).setText(
                MSG_VSERSION_VALUE);
        requestHeadElement.addElement(MSG_SERVICE_TYPE).setText(
                MSG_SERVICE_TYPE_VALUE);

        /* 组装报文体信息 */
        // 组装应用标识信息
        requestBodyElement.addElement(MSG_APPID).setText(Global.getMessage(KEY_APP_ID));
        /*** 1 组装认证原文请求报文数据 ** 完毕 **/

        StringBuffer reqMessageData = new StringBuffer();
        try {
            /*** 2 将认证原文请求报文写入输出流 ** 开始 **/
            ByteArrayOutputStream outStream = new ByteArrayOutputStream();
            XMLWriter writer = new XMLWriter(outStream);
            writer.write(reqDocument);
            messagexml = outStream.toByteArray();
            /*** 2 将认证原文请求报文写入输出流 ** 完毕 **/

            reqMessageData.append("请求内容开始！\n");
            reqMessageData.append(outStream.toString() + "\n");
            reqMessageData.append("请求内容结束！\n");
            System.out.println(reqMessageData.toString() + "\n");
        } catch (Exception e) {
            errDesc = "组装请求时出现异常";
            System.out.println("组装请求时出现异常");
        }

        /****************************************************************
         * 创建与网关的HTTP连接，发送认证原文请求报文，并接收认证原文响应报文*
         ****************************************************************/
        /*** 1 创建与网关的HTTP连接 ** 开始 **/
        String authURL = Global.getMessage(KEY_AUTHURL);
        int statusCode = 500;
        HttpClient httpClient = null;
        PostMethod postMethod = null;
        // HTTPClient对象
        httpClient = new HttpClient();
        httpClient.setConnectionTimeout(10000);
        httpClient.setTimeout(20000);
        postMethod = new PostMethod(authURL);
        postMethod.setRequestHeader("Connection","close");

        // 设置报文传送的编码格式
        postMethod.setRequestHeader("Content-Type",
                "text/xml;charset=UTF-8");
        /*** 2 设置发送认证请求内容 ** 开始 **/
        postMethod.setRequestBody(new ByteArrayInputStream(messagexml));
        /*** 2 设置发送认证请求内容 ** 结束 **/
        // 执行postMethod
        try {
            /*** 3 发送通讯报文与网关通讯 ** 开始 **/
            statusCode = httpClient.executeMethod(postMethod);
            /*** 3 发送通讯报文与网关通讯 ** 结束 **/
        } catch (Exception e) {
            errCode = String.valueOf(statusCode);
            errDesc = e.getMessage();
            System.out.println("与网关连接出现异常\n");
            postMethod.releaseConnection();
            httpClient.getHttpConnectionManager().closeIdleConnections(0);
            httpClient = null ;
        }

        /****************************************************************
         * 网关返回认证原文响应*
         ****************************************************************/
        StringBuffer respMessageData = new StringBuffer();
        String respMessageXml = null;
        // 当返回200或500状态时处理业务逻辑
        if (statusCode == HttpStatus.SC_OK
                || statusCode == HttpStatus.SC_INTERNAL_SERVER_ERROR) {
            // 从头中取出转向的地址
            try {
                /*** 4 接收通讯报文并处理 ** 开始 **/
                byte[] inputstr = postMethod.getResponseBody();

                ByteArrayInputStream ByteinputStream = new ByteArrayInputStream(
                        inputstr);
                ByteArrayOutputStream outStream = new ByteArrayOutputStream();
                int ch = 0;
                try {
                    while ((ch = ByteinputStream.read()) != -1) {
                        int upperCh = (char) ch;
                        outStream.write(upperCh);
                    }
                } catch (Exception e) {
                    errDesc = e.getMessage();
                }

                // 200 表示返回处理成功
                if (statusCode == HttpStatus.SC_OK) {
                    respMessageData.append("响应内容开始！\n");
                    respMessageData.append(new String(outStream
                            .toByteArray(), "UTF-8")
                            + "\n");
                    respMessageData.append("响应内容开始！\n");
                    respMessageXml = new String(outStream
                            .toByteArray(), "UTF-8");
                } else {
                    // 500 表示返回失败，发生异常
                    respMessageData.append("响应500内容开始！\n");
                    respMessageData.append(new String(outStream
                            .toByteArray())
                            + "\n");
                    respMessageData.append("响应500内容结束！\n");
                    errCode = String.valueOf(statusCode);
                    errDesc = new String(outStream.toByteArray());
                }
                System.out.println(respMessageData.toString()
                        + "\n");
                /*** 4 接收通讯报文并处理 ** 结束 **/
            } catch (IOException e) {
                errCode = String.valueOf(statusCode);
                errDesc = e.getMessage();
                System.out.println("读取认证响应报文出现异常！");
            } finally{
                if(httpClient != null){
                    postMethod.releaseConnection();
                    httpClient.getHttpConnectionManager().closeIdleConnections(0);
                }
            }
        }
        /*** 1 创建与网关的HTTP连接 ** 结束 **/

        /****************************************************
         *第八步：接收到网关服务器反馈的响应报文，解析认证原文  *
         ****************************************************/
        Document respDocument = null;
        Element headElement = null;
        Element bodyElement = null;
        respDocument = DocumentHelper.parseText(respMessageXml);

        headElement = respDocument.getRootElement().element(MSG_HEAD);
        bodyElement = respDocument.getRootElement().element(MSG_BODY);

        /*** 1 解析报文头 ** 开始 **/
        if (headElement != null) {
            boolean state = Boolean.valueOf(
                    headElement.elementTextTrim(MSG_MESSAGE_STATE))
                    .booleanValue();
            if (state) {
                errCode = headElement.elementTextTrim(MSG_MESSAGE_CODE);
                errDesc = headElement.elementTextTrim(MSG_MESSAGE_DESC);
                System.out.println("认证业务处理失败！\t" + errDesc + "\n");
            }
        }

        System.out.println("解析报文头成功！\n");

        // 解析认证原文
        Element originalElement = bodyElement.element(MSG_ORIGINAL);
        String original = "";
        if (originalElement != null) {
            original = originalElement.getStringValue();
        }
        return original;
    }

}
