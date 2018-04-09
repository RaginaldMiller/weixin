package com.cpeoc.weixin;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.functions.ExpectedCondition;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.TestNG;

public class TestOpen {

	public static void main(String[] args) throws MalformedURLException {

		File classpathRoot = new File(System.getProperty("user.dir"));
		File appDir = new File(classpathRoot, "app");
		File app = new File(appDir, "mm.apk");
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("deviceName", "127.0.0.1:62001"); // "Android Emulator"
		capabilities.setCapability("platformVersion", "4.4");
		capabilities.setCapability("app", app.getAbsolutePath());
		capabilities.setCapability("appPackage", "com.tencent.mm");

		capabilities.setCapability("noReset", true);
		// iOS配置 realDevice

		capabilities.setCapability("platformName", "Android");
		//中文输入
		capabilities.setCapability("unicodeKeyboard", true);
		capabilities.setCapability("resetKeyboard", true);
		final AppiumDriver<WebElement> driver = new AppiumDriver<>(new URL(
				"http://0.0.0.0:4723/wd/hub"), capabilities);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		// 设置等待时间30s
//		WebDriverWait wait = new WebDriverWait(driver, 30);
//		WebElement sousuo = wait.until(new ExpectedCondition<WebElement>() {
//			@Override
//			public WebElement apply(WebDriver d) {
//				return driver.findElementByXPath("//android.widget.TextView[@text='搜索']");
//			}
//		});
		driver.findElementById("搜索").click();
//		driver.findElementByLinkText("搜索").click();
		//com.tencent.mm:id/ht
		WebElement sousuo = driver.findElementById("com.tencent.mm:id/ht");
		sousuo.click();
		sousuo.sendKeys("中国邮政");
		
		driver.findElementById("com.tencent.mm:id/kr").click();
		
		
		//text  微服务   id  com.tencent.mm:id/aas
		//查邮件/资费/网点/邮编
		
		//切换到webview
		
		
		
		
	}
}
