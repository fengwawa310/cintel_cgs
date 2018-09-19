package com.common.services.api;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import net.sf.json.JSONObject;

import com.common.consts.Const;
import com.common.utils.GsonUtils;
import com.common.utils.PageVO;


/**
 * 并发调用测试类
 * @author cintel
 *
 */
public class CountDownLatchTest implements Runnable {
	final AtomicInteger number = new AtomicInteger();
	volatile boolean bol = false;

	@Override
	public void run() {
		int tt = number.getAndIncrement();
		System.out.println(tt);
		synchronized (this) {
			try {
				if (!bol) {
					bol = true;
					Thread.sleep(10000);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			//
			PageVO pageVO = new PageVO(0, 1);
			String pageVOStr = GsonUtils.getGson().toJson(pageVO);
			JSONObject pageVOObj = JSONObject.fromObject(pageVOStr);
			
			pageVOObj.put("idcardNum","371327198211104721");
			System.out.println("并发线程为" + tt+" >>>>>>> "+APIClientRequest.servicesRequest(Const.SUB_SYSTEM_CODE_SUSPECT, "suspect/listAll", pageVOObj));
		}
	}

	public static void main(String[] args) {
		ExecutorService pool = Executors.newCachedThreadPool();
		CountDownLatchTest test = new CountDownLatchTest();
		for (int i = 0; i < 2000; i++) {
			pool.execute(test);
		}
	}
}
