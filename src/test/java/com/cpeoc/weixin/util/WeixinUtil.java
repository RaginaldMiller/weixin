package com.cpeoc.weixin.util;

import io.appium.java_client.AppiumDriver;

import org.openqa.selenium.WebElement;

public class WeixinUtil {

	public static void openChinaPost(AppiumDriver<WebElement> driver) {

		driver.findElementById("搜索").click();
		//WebElement sousuo = driver.findElementById("com.tencent.mm:id/ht");
		WebElement sousuo = driver.findElementByXPath("//android.widget.EditText[1]");
		sousuo.click();
		sousuo.sendKeys("中国邮政");
		driver.findElementById("com.tencent.mm:id/l7").click();
		
	}

	public static void goBackWeChat(AppiumDriver<WebElement> driver) {
		driver.context("NATIVE_APP");
		driver.findElementById("返回").click();
		driver.findElementById("返回").click();
		driver.findElementById("返回").click();

	}

}
