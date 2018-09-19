package com.controller.login.logincert;

import com.common.utils.DecriptUtil;
import com.entity.sys.SysUser;
import com.service.sys.SysUserService;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.XMLWriter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import sun.misc.BASE64Encoder;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: sky
 * @Description:
 * @Date: Create in 上午10:31 2018/4/18
 */

@RequestMapping("/loginCert")
@Controller
public class LoginCertController {


    @Resource
    private SysUserService sysUserService;

    /******************************* 报文公共部分 ****************************/
    /** 报文根结点 */
    private final String MSG_ROOT = "message";
    /** 报文头结点 */
    private final String MSG_HEAD = "head";
    /** 报文体结点 */
    private final String MSG_BODY = "body";
    /** 服务版本号 */
    private final String MSG_VSERSION = "version";
    /** 服务版本值 */
    private final String MSG_VSERSION_VALUE = "1.0";
    /** 服务类型 */
    private final String MSG_SERVICE_TYPE = "serviceType";
    /** 服务类型值 */
    private final String MSG_SERVICE_TYPE_VALUE = "AuthenService";
    /** 报文体 认证方式 */
    private final String MSG_AUTH_MODE = "authMode";
    /** 报文体 证书认证方式 */
    private final String MSG_AUTH_MODE_CERT_VALUE = "cert";
    /** 报文体 口令认证方式 */
    private final String MSG_AUTH_MODE_PASSWORD_VALUE = "password";
    /** 报文体 二维码认证方式 */
    private final String MSG_AUTH_MODE_QRCODE_VALUE = "qrcode";
    /** 报文体 属性集 */
    private final String MSG_ATTRIBUTES = "attributes";
    /** 报文体 自定义属性集 */
    private final String MSG_CUSTOM_ATTRIBUTES  = "customAttributes";
    /** 报文体 属性 */
    private final String MSG_ATTRIBUTE = "attr";
    /** 报文体 属性名 */
    private final String MSG_NAME = "name";
    /** 报文父级节点 */
    // --hegd
    public static final String MSG_PARENT_NAME = "parentName";
    /** 报文体 属性空间 */
    private final String MSG_NAMESPACE = "namespace";
    /*********************************************************************/

    /******************************* 请求报文 ****************************/
    /** 报文体 应用ID */
    private final String MSG_APPID = "appId";
    /** 访问控制 */
    private final String MSG_ACCESS_CONTROL = "accessControl";
    private final String MSG_ACCESS_CONTROL_TRUE = "true";
    private final String MSG_ACCESS_CONTROL_FALSE = "false";
    /** 报文体 认证结点 */
    private final String MSG_AUTH = "authen";
    /** 报文体 认证凭据 */
    private final String MSG_AUTHCREDENTIAL = "authCredential";
    /** 报文体 客户端结点 */
    private final String MSG_CLIENT_INFO = "clientInfo";
    /** 报文体 公钥证书 */
    private final String MSG_CERT_INFO = "certInfo";
    /** 报文体 客户端结点 */
    private final String MSG_CLIENT_IP = "clientIP";
    /** 报文体 detach认证请求包 */
    private final String MSG_DETACH = "detach";
    /** 报文体 证书类型，PM 证书为：PM */
    private final String MSG_CERTTYPE = "certType";
    /** 报文体 原文 */
    private final String MSG_ORIGINAL = "original";
    /** 报文体 用户名 */
    private final String MSG_USERNAME = "username";
    /** 报文体 口令 */
    private final String MSG_PASSWORD = "password";
    /** 报文体 Token */
    private final String MSG_TOKEN = "token";
    /** 报文体 属性类型 */
    private final String MSG_ATTRIBUTE_TYPE = "attributeType";
    /** 指定属性 portion */
    private final String MSG_ATTRIBUTE_TYPE_PORTION = "portion";
    /** 指定属性 all */
    private final String MSG_ATTRIBUTE_TYPE_ALL = "all";
    /** 指定属性列表控制项 attrType */
    private final String MSG_ATTR_TYPE = "attrType";
    /*********************************************************************/

    /******************************* 响应报文 ****************************/
    /** 报文体 认证结果集状态 */
    private final String MSG_MESSAGE_STATE = "messageState";
    /** 响应报文消息码 */
    private final String MSG_MESSAGE_CODE = "messageCode";
    /** 响应报文消息描述 */
    private final String MSG_MESSAGE_DESC = "messageDesc";
    /** 报文体 认证结果集 */
    private final String MSG_AUTH_RESULT_SET = "authResultSet";
    /** 报文体 认证结果 */
    private final String MSG_AUTH_RESULT = "authResult";
    /** 报文体 认证结果状态 */
    private final String MSG_SUCCESS = "success";
    /** 报文体 认证错误码 */
    private final String MSG_AUTH_MESSSAGE_CODE = "authMessageCode";
    /** 报文体 认证错误描述 */
    private final String MSG_AUTH_MESSSAGE_DESC = "authMessageDesc";
    /*********************************************************************/

    /**************************** 业务处理常量 ****************************/
    /** 认证地址 */
    private final String KEY_AUTHURL = "authURL";
    /** 应用标识 */
    private final String KEY_APP_ID = "appId";
    /** 认证方式 */
    private final String KEY_CERT_AUTHEN = "certAuthen";
    /** session中原文 */
    private final String KEY_ORIGINAL_DATA = "original_data";
    /** 客户端返回的认证原文，request中原文 */
    private final String KEY_ORIGINAL_JSP = "original_jsp";
    /** 证书认证请求包 */
    private final String KEY_SIGNED_DATA = "signed_data";
    /** 证书 */
    private final String KEY_CERT_CONTENT = "certInfo";

    /*********************************************************************/


    @RequestMapping("/loginCert.do")
    public String loginCert(HttpServletRequest req,HttpServletResponse resp){

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        /***************************************************************************
         * isSuccess 认证是否成功,true成功/false失败;errCode 错误码;errDesc 错误描述 * *
         ***********************************************************************/
        // 第四步：客户端认证
        // 第五步：服务端验证认证原文
        // 第六步：应用服务端认证
        // 第七步：网关返回认证响应
        // 第八步：服务端处理
        /***********************************
         * 获取应用标识及网关认证地址 *
         ***********************************/

        boolean isSuccess = true;
        String errCode = null, errDesc = null;
        String authMode = request.getParameter(MSG_AUTH_MODE);
        String token = request.getParameter(MSG_TOKEN);

        // 可以根据需求使用不同的获取方法
        String appId = this.getProperties(request.getSession(), KEY_APP_ID);
        String authURL = this.getProperties(request.getSession(), KEY_AUTHURL);
        String attrType = this.getProperties(request.getSession(),MSG_ATTR_TYPE);
        if (!isNotNull(appId) || !isNotNull(authURL)) {
            isSuccess = false;
            errDesc = "应用标识或网关认证地址不可为空";
            System.out.println("应用标识或网关认证地址不可为空\n");
        }

        String original_data = null, signed_data = null, original_jsp = null, username = null, password = null;
        /**************************
         * 获取认证数据信息 *
         **************************/
        if (isSuccess && !MSG_AUTH_MODE_QRCODE_VALUE.equalsIgnoreCase(authMode)) {
            System.out.println("应用标识及网关的认证地址读取成功！\n应用标识：" + appId + "\n认证地址："
                    + authURL + "\n");

            if (isNotNull((String) request.getSession().getAttribute(
                    KEY_ORIGINAL_DATA))
                    && isNotNull((String) request.getParameter(KEY_SIGNED_DATA))
                    && isNotNull((String) request.getParameter(KEY_ORIGINAL_JSP))) {
                // 获取session中的认证原文
                original_data = (String) request.getSession().getAttribute(
                        KEY_ORIGINAL_DATA);
                // 获取request中的认证原文
                original_jsp = (String) request.getParameter(KEY_ORIGINAL_JSP);


                /**************************
                 * 第五步：服务端验证认证原文 *
                 **************************/
                if (!original_data.equalsIgnoreCase(original_jsp)) {
                    isSuccess = false;
                    errDesc = "客户端提供的认证原文与服务端的不一致";
                    System.out.println("客户端提供的认证原文与服务端的不一致！\n");

                } else {
                    // 获取证书认证请求包
                    signed_data = (String) request.getParameter(KEY_SIGNED_DATA);

                    /* 随机密钥 */
                    original_data = new BASE64Encoder().encode(original_jsp.getBytes());
                    System.out.println("读取认证原文和认证请求包成功！\n认证原文：" + original_jsp
                            + "\n认证请求包：" + signed_data + "\n");
                }
            } else {
                isSuccess = false;
                errDesc = "证书认证数据不完整";
                System.out.println("证书认证数据不完整！\n");
            }
        }

        /**************************
         * 第六步：应用服务端认证 *
         **************************/
        // 认证处理
        try {
            byte[] messagexml = null;
            if (isSuccess) {

                /*** 1 组装认证请求报文数据 ** 开始 **/
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

                // 组装客户端信息
                Element clientInfoElement = requestBodyElement
                        .addElement(MSG_CLIENT_INFO);

                Element clientIPElement = clientInfoElement
                        .addElement(MSG_CLIENT_IP);

                clientIPElement.setText(request.getRemoteAddr());

                // 组装应用标识信息
                requestBodyElement.addElement(MSG_APPID).setText(appId);

                Element authenElement = requestBodyElement.addElement(MSG_AUTH);

                Element authCredentialElement = authenElement
                        .addElement(MSG_AUTHCREDENTIAL);

                // 组装证书认证信息
                if (MSG_AUTH_MODE_CERT_VALUE.equalsIgnoreCase(authMode)){
                    authCredentialElement.addAttribute(MSG_AUTH_MODE,
                            MSG_AUTH_MODE_CERT_VALUE);
                    authCredentialElement.addElement(MSG_DETACH).setText(
                            signed_data);
                    authCredentialElement.addElement(MSG_ORIGINAL).setText(
                            original_data);
                    // 组装证书类型信息，如果是PM证书报文认证，必须设置证书类型信息
//					authCredentialElement.addElement(MSG_CERTTYPE).setText("PM");
                }

                // 支持X509证书 认证方式
                // 获取到的证书
                // javax.security.cert.X509Certificate x509Certificate = null;
                // certInfo 为base64编码证书
                // 可以使用
                // "certInfo =new BASE64Encoder().encode(x509Certificate.getEncoded());"
                // 进行编码
                // authCredentialElement.addElement(MSG_CERT_INFO).setText(certInfo);

                requestBodyElement.addElement(MSG_ACCESS_CONTROL).setText(
                        MSG_ACCESS_CONTROL_TRUE);

                // 组装口令认证信息
                // username = request.getParameter( "" );//获取认证页面传递过来的用户名/口令
                // password = request.getParameter( "" );
                // authCredentialElement.addAttribute(MSG_AUTH_MODE,MSG_AUTH_MODE_PASSWORD_VALUE
                // );
                // authCredentialElement.addElement( MSG_USERNAME
                // ).setText(username);
                // authCredentialElement.addElement( MSG_PASSWORD
                // ).setText(password);

                // 组装属性查询列表信息
                Element attributesElement = requestBodyElement
                        .addElement(MSG_ATTRIBUTES);
                if (attrType == null || attrType.equals("")) {
                    errDesc = "属性列表控制项为空，请确认配置！！！";
                    throw new ServletException("属性列表控制项为空，请确认配置！！！");
                }
                attributesElement.addAttribute(MSG_ATTRIBUTE_TYPE, attrType);
                if (attrType.equals("portion")) {
                    String attributes = this.getProperties(
                            request.getSession(), MSG_ATTRIBUTES);
                    if (null != attributes && !"".equals(attributes)) {
                        String[] attrs = attributes.split(";");
                        int num = attrs.length;
                        for (int i = 0; i < num; i++) {
                            String att = attrs[i];
                            if (att.contains("X509") || att.contains("_saml")) {
                                // 证书属性
                                addAttribute(attributesElement, att,
                                        "http://www.jit.com.cn/cinas/ias/ns/saml/saml11/X.509");
                            } else {
                                // pms 属性 或 ums 属性
                                addAttribute(attributesElement, att,
                                        "http://www.jit.com.cn/ums/ns/user");
                                addAttribute(attributesElement, att,
                                        "http://www.jit.com.cn/pmi/pms/ns/role");
                                addAttribute(attributesElement, att,
                                        "http://www.jit.com.cn/pmi/pms/ns/privilege");
                            }
                        }
                    }

                }
                // TODO 取公共信息
                // addAttribute(attributesElement, "X509Certificate.SubjectDN",
                // "http://www.jit.com.cn/cinas/ias/ns/saml/saml11/X.509");
                // addAttribute(attributesElement, "UMS.UserID",
                // "http://www.jit.com.cn/ums/ns/user");
                // addAttribute(attributesElement, "机构字典",
                // "http://www.jit.com.cn/ums/ns/user");

                /*** 1 组装认证请求报文数据 ** 完毕 **/

                StringBuffer reqMessageData = new StringBuffer();
                try {
                    /*** 2 将认证请求报文写入输出流 ** 开始 **/
                    ByteArrayOutputStream outStream = new ByteArrayOutputStream();
                    XMLWriter writer = new XMLWriter(outStream);
                    writer.write(reqDocument);
                    messagexml = outStream.toByteArray();
                    /*** 2 将认证请求报文写入输出流 ** 完毕 **/

                    reqMessageData.append("请求内容开始！\n");
                    reqMessageData.append(outStream.toString() + "\n");
                    reqMessageData.append("请求内容结束！\n");
                    System.out.println(reqMessageData.toString() + "\n");
                } catch (Exception e) {
                    isSuccess = false;
                    errDesc = "组装请求时出现异常";
                    System.out.println("组装请求时出现异常");
                }
            }

            /****************************************************************
             * 创建与网关的HTTP连接，发送认证请求报文，并接收认证响应报文*
             ****************************************************************/
            /*** 1 创建与网关的HTTP连接 ** 开始 **/
            int statusCode = 500;
            HttpClient httpClient = null;
            PostMethod postMethod = null;
            if (isSuccess) {
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
                    isSuccess = false;
                    errCode = String.valueOf(statusCode);
                    errDesc = e.getMessage();
                    System.out.println("与网关连接出现异常\n");
                    postMethod.releaseConnection();
                    httpClient.getHttpConnectionManager().closeIdleConnections(0);
                    httpClient = null ;
                }
            }
            /****************************************************************
             * 第七步：网关返回认证响应*
             ****************************************************************/

            StringBuffer respMessageData = new StringBuffer();
            String respMessageXml = null;
            if (isSuccess) {
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
                            isSuccess = false;
                            errDesc = e.getMessage();
                        }

                        if (isSuccess) {
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
                                isSuccess = false;
                                errCode = String.valueOf(statusCode);
                                errDesc = new String(outStream.toByteArray());
                            }
                            System.out.println(respMessageData.toString()
                                    + "\n");
                        }
                        /*** 4 接收通讯报文并处理 ** 结束 **/
                    } catch (IOException e) {
                        isSuccess = false;
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
            }

            /*** 1 创建与网关的HTTP连接 ** 结束 **/

            /**************************
             *第八步：服务端处理 *
             **************************/
            Document respDocument = null;
            Element headElement = null;
            Element bodyElement = null;
            if (isSuccess) {
                respDocument = DocumentHelper.parseText(respMessageXml);

                headElement = respDocument.getRootElement().element(MSG_HEAD);
                bodyElement = respDocument.getRootElement().element(MSG_BODY);

                /*** 1 解析报文头 ** 开始 **/
                if (headElement != null) {
                    boolean state = Boolean.valueOf(
                            headElement.elementTextTrim(MSG_MESSAGE_STATE))
                            .booleanValue();
                    if (state) {
                        isSuccess = false;
                        errCode = headElement.elementTextTrim(MSG_MESSAGE_CODE);
                        errDesc = headElement.elementTextTrim(MSG_MESSAGE_DESC);
                        System.out.println("认证业务处理失败！\t" + errDesc + "\n");
                    }
                }
            }

            if (isSuccess) {
                System.out.println("解析报文头成功！\n");
                /* 解析报文体 */
                // 解析认证结果集
                Element authResult = bodyElement.element(MSG_AUTH_RESULT_SET)
                        .element(MSG_AUTH_RESULT);

                isSuccess = Boolean.valueOf(
                        authResult.attributeValue(MSG_SUCCESS)).booleanValue();
                if (!isSuccess) {
                    errCode = authResult
                            .elementTextTrim(MSG_AUTH_MESSSAGE_CODE);
                    errDesc = authResult
                            .elementTextTrim(MSG_AUTH_MESSSAGE_DESC);
                    System.out.println("身份认证失败，失败原因：" + errDesc);
                }
            }

            if (isSuccess) {
                System.out.println("身份认证成功！\n");
                String ss = bodyElement.elementTextTrim("accessControlResult");

                System.out.println(ss);

                // 解析用户属性列表
                Element attrsElement = bodyElement.element(MSG_ATTRIBUTES);
                if (attrsElement != null) {
                    List<Element> namespacesElements = attrsElement
                            .elements(MSG_ATTRIBUTE);
                    if (namespacesElements != null
                            && namespacesElements.size() > 0) {
                        System.out.println("属性个数：" + namespacesElements.size());
                        for (int i = 0; i < namespacesElements.size(); i++) {
                            String arrs = namespacesElements.get(i)
                                    .attributeValue(MSG_NAMESPACE);

                            System.out.println(arrs);
                        }
                        Map certAttributeNodeMap = new HashMap();
                        Map childAttributeNodeMap = new HashMap();
                        Map umsAttributeNodeMap = new HashMap();
                        Map pmsAttributeNodeMap = new HashMap();
                        String[] keyes = new String[2];
                        if (attrsElement != null) {
                            List attributeNodeList = attrsElement
                                    .elements(MSG_ATTRIBUTE);
                            for (int i = 0; i < attributeNodeList.size(); i++) {
                                keyes = new String[2];
                                Element userAttrNode = (Element) attributeNodeList
                                        .get(i);
                                String msgParentName = userAttrNode
                                        .attributeValue(MSG_PARENT_NAME);
                                String name = userAttrNode
                                        .attributeValue(MSG_NAME);
                                String value = userAttrNode.getTextTrim();
                                keyes[0] = name;
                                childAttributeNodeMap.clear();
                                String arrs = namespacesElements.get(i)
                                        .attributeValue(MSG_NAMESPACE);
                                if (arrs
                                        .trim()
                                        .equals(
                                                "http://www.jit.com.cn/cinas/ias/ns/saml/saml11/X.509")) {
                                    // 证书信息
                                    if (msgParentName != null
                                            && !msgParentName.equals("")) {
                                        keyes[1] = msgParentName;
                                        if (value != null && value.length() > 0)
                                            childAttributeNodeMap.put(keyes,value);
                                    } else {
                                        if (value != null && value.length() > 0)
                                            certAttributeNodeMap.put(keyes,value);
                                    }
                                    certAttributeNodeMap.putAll(childAttributeNodeMap);
                                } else if (arrs.trim().equals(
                                        "http://www.jit.com.cn/ums/ns/user")) {
                                    // UMS信息
                                    if (msgParentName != null
                                            && !msgParentName.equals("")) {
                                        keyes[1] = msgParentName;
                                        if (value != null && value.length() > 0)
                                            childAttributeNodeMap.put(keyes,value);
                                    } else {
                                        if (value != null && value.length() > 0)
                                            umsAttributeNodeMap.put(keyes,value);
                                    }
                                    umsAttributeNodeMap.putAll(childAttributeNodeMap);
                                } else if (arrs.trim().contains(
                                        "http://www.jit.com.cn/pmi/pms")) {
                                    // PMS信息
                                    if (msgParentName != null
                                            && !msgParentName.equals("")) {
                                        keyes[1] = msgParentName;
                                        if (value != null && value.length() > 0)
                                            childAttributeNodeMap.put(keyes,value);
                                    } else {
                                        if (value != null && value.length() > 0)
                                            pmsAttributeNodeMap.put(keyes,value);
                                    }
                                    pmsAttributeNodeMap.putAll(childAttributeNodeMap);
                                } else {
                                    // 如果有其他的属性信息添加到证书信息里面
                                    if (msgParentName != null
                                            && !msgParentName.equals("")) {
                                        keyes[1] = msgParentName;
                                        if (value != null && value.length() > 0)
                                            childAttributeNodeMap.put(keyes,value);
                                    } else {
                                        if (value != null && value.length() > 0)
                                            certAttributeNodeMap.put(keyes,value);
                                    }
                                    certAttributeNodeMap.putAll(childAttributeNodeMap);
                                }
                            }
                            request.setAttribute("certAttributeNodeMap",
                                    certAttributeNodeMap);
                            request.setAttribute("umsAttributeNodeMap",
                                    umsAttributeNodeMap);
                            request.setAttribute("pmsAttributeNodeMap",
                                    pmsAttributeNodeMap);

                        }
                    }
                }
                // 解析用户自定义属性列表
                Element customAttrsElement = bodyElement.element(MSG_CUSTOM_ATTRIBUTES);
                if (customAttrsElement != null) {
                    request.setAttribute("customAttributeNodeMap",
                            customAttrsElement.getTextTrim());
                }
            }
        } catch (Exception e) {
            isSuccess = false;
            e.printStackTrace();
            errDesc = e.getMessage();
        }

        if (!isSuccess) {
            if (isNotNull(errCode)) {
                request.setAttribute("errCode", errCode);
            }
            if (isNotNull(errDesc)) {
                request.setAttribute("errDesc", errDesc);
            }
            System.out.println("处理数据结束，业务处理失败，失败原因：" + errDesc + "\n");
        } else {
            System.out.println("处理数据结束，一切正常！\n");
        }

        request.setAttribute("isSuccess", new Boolean(isSuccess).toString());


        //认证成功之后的操作  需要修改
        try{
            //使用shiro提供的方式进行认证
            String name = "sj_zcy";
            String pwd = "111111";
            if(name==null||pwd==null){
                return "login";
            }
            pwd = DecriptUtil.MD5(pwd);
            //获得、得到Subject===当前用户对象===,现在subject的状态为“未认证”状态
            Subject subject = SecurityUtils.getSubject();
            //创建用户名/密码身份验证Token（即用户身份/凭证）
            AuthenticationToken authenticationToken = new UsernamePasswordToken(name, pwd);
            //4、登录，即身份验证
            subject.login(authenticationToken);//调用到SecurityManager安全管理器，安全管理器调用Realm，在Realm中进行认证
            //SecurityManager接受到token(令牌)信息后会委托内置的Authenticator的实例（通常都是ModularRealmAuthenticator类的实例）调用authenticator.authenticate(token)

            /*uid存入redis缓存*/
            SysUser sysUser = sysUserService.findUserByUsername(username);
            String id = request.getSession().getId();

//            request.setAttribute("errDesc", "ceshi");
//            return "loginCert";
            return "redirect:/wel";
        }catch (UnknownAccountException e) {//用户名不存在
            e.printStackTrace();
            //this.addActionError("用户名不存在！");
            return "loginCert";
        }catch (IncorrectCredentialsException e) {//密码错误异常
            //this.addActionError("密码错误！");
            e.printStackTrace();
            return "loginCert";
        }
    }


    /**
     * 获取文件中的属性值
     *
     * @param httpSession
     */
    private String getProperties(HttpSession httpSession, String key) {

        return httpSession.getAttribute(key) == null ? null : httpSession
                .getAttribute(key).toString();
    }

    /**
     * 判断是否是空串
     */
    private boolean isNotNull(String str) {
        if (str == null || str.trim().equals(""))
            return false;
        else
            return true;
    }

    /**
     * 向xml插入结点
     */
    private void addAttribute(Element attributesElement, String name,
                              String namespace) {
        Element attr = attributesElement.addElement(MSG_ATTRIBUTE);
        attr.addAttribute(MSG_NAME, name);
        attr.addAttribute(MSG_NAMESPACE, namespace);
    }


}
