package com.common.services.api;

import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

import org.apache.commons.lang3.StringUtils;

import com.common.consts.Global;
import com.common.utils.UuidUtil;
import com.common.utils.httpclient.HttpClientUtil;

import net.sf.json.JSONObject;

public class APIClientRequest {

	private FutureTask<String> future;
	private InterfaceCallBack callBack;
	private ExecutorService executor;

	public static String servicesRequest(String servicesName,String requestInterface,
			Map<String, Object> requestParam) {
		JSONObject resultJson = new JSONObject();
		resultJson.put("key", UuidUtil.uuid());

		String url = getSevicesFullPath(servicesName,requestInterface);
	
		System.out.println("URL++++"+url);
		if (url != null) {
			//
//			String requestParamJson = JSONObject.fromObject(requestParam).toString();
//			String httpOrgCreateTestRtn = HttpClientUtil.postJson(url,requestParamJson, "utf-8");
			
			//
			String httpOrgCreateTestRtn = HttpClientUtil.doPost(url,requestParam, "utf-8");
			
			resultJson = JSONObject.fromObject(httpOrgCreateTestRtn);
		}else{
			resultJson.put("code", Integer.valueOf(400));
			resultJson.put("cmd", "参数不能传入空值 ");
			resultJson.put("data",
					"{\"success\":false,\"msg\":\"servicesName,requestInterface,requestParam is null!\"}");
		}
		System.out.println("resultJson>>>>>>>>>>>>>   "+resultJson.toString());
		return resultJson.toString();
	}

	public String servicesRequest(String servicesName, String requestInterface,
			Map<String, Object> requestParam, InterfaceCallBack callBack) {
		String url = getSevicesFullPath(servicesName,requestInterface);
		JSONObject resultJson = new JSONObject();
		if (url != null) {
			this.callBack = callBack;

			this.executor = Executors.newCachedThreadPool();
			String key = UuidUtil.uuid();

			this.future = new FutureTask<>(new TaskWithResult(url,requestParam,key));
			this.executor.execute(this.future);

			new Thread() {
				public void run() {
					getData();
				}
			}.start();
			return key;
		}else{
			resultJson.put("code", Integer.valueOf(400));
			resultJson.put("cmd", "必须参数不能传入空值 ");
			resultJson.put("data",
					"{\"success\":false,\"msg\":\"servicesName,requestInterface,requestParam is null!\"}");
			return resultJson.toString();
		}
	}

	private class TaskWithResult implements Callable<String> {
		private Map<String, Object> requestParam;
		private String key;
		private String url;

		public TaskWithResult(String url,Map<String, Object> requestParam, String key) {
			this.requestParam = requestParam;
			this.key = key;
			this.url = url;
		}

		public String call() throws Exception {
			try {
				String httpOrgCreateTestRtn = HttpClientUtil.doPost(url,requestParam, "utf-8");
				JSONObject jsons = JSONObject.fromObject(httpOrgCreateTestRtn);
				jsons.put("key", key);
				return jsons.toString();
			} catch (Exception e) {
				e.printStackTrace();
				throw new Exception("Callable terminated with Exception!");
			}
		}
	}

	private void getData() {
		String result = "";
		try {
			result = (String) this.future.get();
			this.callBack.OnResult(result);
		} catch (InterruptedException e) {
			this.future.cancel(true);
			e.printStackTrace();
		} catch (ExecutionException e) {
			this.future.cancel(true);
			e.printStackTrace();
		} finally {
			this.executor.shutdown();
		}
	}
	
	/**
	 * 获取后台服务全地址
	 * @param servicesCode
	 * @param requestInterface
	 * @return
	 */
	public static String getSevicesFullPath(String servicesCode,String requestInterface){
		String fullPath = null;
		if (StringUtils.isNotBlank(servicesCode)&&StringUtils.isNotBlank(requestInterface)) {
			String configPath = Global.getConfig(servicesCode);
			if(configPath != null)
				fullPath = configPath + requestInterface;
		}
		return fullPath;
	}
}
