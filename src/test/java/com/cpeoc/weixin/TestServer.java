package com.cpeoc.weixin;

import com.cpeoc.weixin.util.AppiumServerUtil;

public class TestServer {
	public static void main(String[] args) {
		
		
//		try {
//			AppiumServerUtil.startAppiumServer();
//			//异步线程去执行cmd
//			
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//		
//		
//		AppiumServerUtil.stopAppiumServer();

		
		long currentTimeMillis1 = System.currentTimeMillis();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		long currentTimeMillis2 = System.currentTimeMillis();
		System.out.println(currentTimeMillis2-currentTimeMillis1);
	}
}	
