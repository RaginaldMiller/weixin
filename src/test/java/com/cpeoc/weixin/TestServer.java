package com.cpeoc.weixin;

import com.cpeoc.weixin.util.AppiumServerUtil;

public class TestServer {
	public static void main(String[] args) {
		
		
		try {
			AppiumServerUtil.startAppiumServer();
			//异步线程去执行cmd
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		
		AppiumServerUtil.stopAppiumServer();
		
	}
}	
