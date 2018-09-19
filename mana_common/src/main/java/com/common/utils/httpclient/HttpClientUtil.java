package com.common.utils.httpclient;

import java.io.IOException;
import java.net.URI;
import java.nio.charset.Charset;
import java.util.*;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class HttpClientUtil {
	private static HttpClient httpClient = null;
//	private static HttpPost httpPost = null;
	private static String result = null;

	private static HttpClient getInstance(boolean isHttps) {
//		if (httpClient == null) {
//			try {
//				httpClient = HttpClients.createDefault();
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
		return httpClient;
	}

	public static void init() {
		getInstance(false);
	}

	public static String doPost(String url, Map<String, Object> map,
			String charset) {
		JSONObject resultJson = new JSONObject();
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost httpPost = null;
		CloseableHttpResponse response = null;
		try {
//			httpClient = getInstance(false);
			
			httpPost = new HttpPost(url);
			RequestConfig requestConfig = RequestConfig.custom()
					.setConnectTimeout(200000).setConnectionRequestTimeout(200000)
					.setSocketTimeout(200000).build();
			httpPost.setConfig(requestConfig);
			
			List<BasicNameValuePair> list = new ArrayList<>();
			Iterator iterator = map.entrySet().iterator();
			while (iterator.hasNext()) {
				Map.Entry elem = (Map.Entry) iterator.next();
				if(elem != null && elem.getKey() != null && elem.getValue() != null)
					list.add(new BasicNameValuePair((String) elem.getKey(), elem.getValue().toString()));
			}
			if (list.size() > 0) {
				UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list,charset);
				System.out.println("postJson>>>>>>>>>>>>>   "+JsonUtil.toJson(list));
				httpPost.setEntity(entity);
			}
			response = httpClient.execute(httpPost);
			int statusCode = response.getStatusLine().getStatusCode();
			resultJson.put("code",statusCode);
			if (statusCode != 200) {
				resultJson.put("data","{\"success\":false,\"msg\":\"服务器无回应!\"}");
			}else{
				HttpEntity resEntity = response.getEntity();
				resultJson.put("data",EntityUtils.toString(resEntity, charset));
			}
		} catch (Exception ex) {
			/**
			 * java.net.SocketTimeoutException: Read timed out
			 * 
			 * org.apache.http.conn.HttpHostConnectException: Connect to localhost:8880
			 * java.net.ConnectException: Connection refused: connect
			 */
			
			resultJson.put("code", Integer.valueOf(605));
			resultJson.put("cmd", "无效的URL请求地址 ");
			resultJson.put("data","{\"success\":false,\"msg\":\"无效的URL请求地址!\"}");
			ex.printStackTrace();
		} finally {
			try {
				if(response != null)
					response.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
//			if (httpPost != null) {
//				httpPost.releaseConnection();
//				try {
//					Thread.sleep(500L);
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
//			}
		}
		result = resultJson.toString();
		return result;
	}

	public static String postJson(String url, String json, String charset) {
		HttpClient httpClient = null;
		HttpPost httpPost = null;
		String result = null;
		JSONObject resultJson = new JSONObject();
		try {
			if ((json == null) || StringUtils.isBlank(json.trim())){
				resultJson.put("code", Integer.valueOf(602));
				resultJson.put("cmd", "请求参数param不能为空");
				resultJson.put("data",
						"{\"success\":false,\"msg\":\"请求参数param不能为空!\"}");
				return resultJson.toString();
			}
			httpClient = HttpClients.createDefault();

			httpPost = new HttpPost(url);
			RequestConfig requestConfig = RequestConfig.custom()
					.setConnectTimeout(10000).setConnectionRequestTimeout(2000)
					.setSocketTimeout(10000).build();
			httpPost.setConfig(requestConfig);

//			httpPost.addHeader("Content-type", "application/json; charset="+charset);
//			httpPost.setHeader("Accept", "application/json");
			
			List<BasicNameValuePair> list = new ArrayList<>();
			JSONObject jsonObject = JSONObject.fromObject(json);
	        Iterator iterator = jsonObject.keys();
	        String key = null,value = null;
	        while(iterator.hasNext()){
	        	key = (String) iterator.next();
	            value = jsonObject.getString(key);
	            if(key != null && value != null)
	            	list.add(new BasicNameValuePair(key, value.toString()));
	        }
			if (list.size() > 0) {
				UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list,charset);
				httpPost.setEntity(entity);
			}
			
			HttpResponse response = httpClient.execute(httpPost);
			int statusCode = response.getStatusLine().getStatusCode();
			resultJson.put("code",statusCode);
			if (statusCode != 200) {
				resultJson.put("data","{\"success\":false,\"msg\":\"返回数据为空!\"}");
			}else{
				HttpEntity resEntity = response.getEntity();
				String resStr = EntityUtils.toString(resEntity, charset);
				if(StringUtils.isBlank(resStr)){
					resultJson.put("data","{\"success\":false,\"msg\":\"服务器无回应!\"}");
				}else{
					resultJson.put("data",resStr);
				}
			}
		} catch (Exception e) {
			resultJson.put("code", Integer.valueOf(605));
			resultJson.put("cmd", "无效的URL请求地址 ");
			resultJson.put("data","{\"success\":false,\"msg\":\"无效的URL请求地址!\"}");
		} finally {
			if (httpPost != null) {
				httpPost.releaseConnection();
				try {
					Thread.sleep(500L);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}

		result = resultJson.toString();
		return result;
	}
	
	public static String doGet(String url, Map<String, String> param) {

		// 创建Httpclient对象
		CloseableHttpClient httpclient = HttpClients.createDefault();

		String resultString = "";
		CloseableHttpResponse response = null;
		try {
			// 创建uri
			URIBuilder builder = new URIBuilder(url);
			if (param != null) {
				for (String key : param.keySet()) {
					builder.addParameter(key, param.get(key));
				}
			}
			URI uri = builder.build();

			// 创建http GET请求
			HttpGet httpGet = new HttpGet(uri);

			// 执行请求
			response = httpclient.execute(httpGet);
			// 判断返回状态是否为200
			if (response.getStatusLine().getStatusCode() == 200) {
				resultString = EntityUtils.toString(response.getEntity(), "UTF-8");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (response != null) {
					response.close();
				}
				httpclient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return resultString;
	}

	public static String doLocalGet(String url) {
		return doGet(url, null);
	}


	/**
	 * 发送案件录入前置Get请求
	 * @return
	 */
	public static Map<String, Object>  sendHttpGet(String url, Map<String, Object> paramsMap, org.apache.http.client.CookieStore cookieStore) {
		Map<String, Object> map=new HashMap<>();
		CloseableHttpClient httpClient;
		HttpEntity entity;
		HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
		CloseableHttpResponse response = null;
		//响应内容
		String responseContent;
		if(cookieStore==null){
			// 创建默认的httpClient实例.
			httpClient = httpClientBuilder.build();
		}else {
			// 创建默认的httpClient实例.
			httpClient = httpClientBuilder.setDefaultCookieStore(cookieStore).build();
		}
		//封装请求参数
		List<NameValuePair> params =new ArrayList();
		for (String key : paramsMap.keySet()) {
			if(paramsMap.get(key)!=null) {
				params.add(new BasicNameValuePair(key, paramsMap.get(key)+""));
			}
		}
		String str;
		try {
			//转换为键值对
			str = EntityUtils.toString(new UrlEncodedFormEntity(params, Consts.UTF_8));
			//创建Get请求
			HttpGet httpGet = new HttpGet(url+"?"+str);
			//执行Get请求，
			response = httpClient.execute(httpGet);
			//得到响应体
			entity = response.getEntity();
			responseContent = EntityUtils.toString(entity, "UTF-8");
			map.put("code",response.getStatusLine().getStatusCode());
			map.put("responseContent",responseContent);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				// 关闭连接,释放资源
				if (response != null) {
					response.close();
				}
				if (httpClient != null) {
					httpClient.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return map;
	}

}
