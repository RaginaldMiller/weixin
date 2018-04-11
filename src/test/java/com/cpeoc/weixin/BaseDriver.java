package com.cpeoc.weixin;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Attachment;

import org.junit.BeforeClass;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;

import com.cpeoc.weixin.util.AppiumServerUtil;
import com.cpeoc.weixin.util.Config;
import com.cpeoc.weixin.util.WeixinUtil;

/**
 * 基类
 * @author ken
 * @date 2018年4月3日
 */
public class BaseDriver {
	
	public  AppiumDriver<WebElement> driver;
	
	
	@BeforeSuite
	public void beforeSuite() throws Exception{
		
		//1.启动appium server
		if(!AppiumServerUtil.startAppiumServer()){
			Assert.fail("appium server 启动失败！");
		}
		int port = AppiumServerUtil.portList.get(0);
		
		
		//2.初始化driver
		File classpathRoot = new File(System.getProperty("user.dir"));
		File appDir = new File(classpathRoot, "app");
		File app = new File(appDir, "mm.apk");
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("deviceName", Config.deviceName); // "Android Emulator"
		capabilities.setCapability("platformVersion", Config.deviceSysVersion);
		capabilities.setCapability("app", app.getAbsolutePath());
		capabilities.setCapability("appPackage", "com.tencent.mm");

		capabilities.setCapability("noReset", true);
		// iOS配置 realDevice

		capabilities.setCapability("platformName", "Android");
		//中文输入
		capabilities.setCapability("unicodeKeyboard", true);
		capabilities.setCapability("resetKeyboard", true);
		
		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.setExperimentalOption("androidProcess", "com.tencent.mm:tools");
		capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
		capabilities.setCapability("recreateChromeDriverSessions", true); 
		
		driver = new AppiumDriver<>(new URL("http://"+Config.serverHost+":"+port+"/wd/hub"), capabilities);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	/**
	 * 退出driver
	 * 关闭server
	 */
	@AfterSuite(alwaysRun=true)
	public void afterSuite(){
		if(null != driver){
			driver.quit();
		}
		AppiumServerUtil.stopAppiumServer();
	}
	
	
	@AfterTest(alwaysRun=true)
	public void afterTest(){
		if(null != driver){
			driver.context("NATIVE_APP");
		}		
	}
	
	 @Attachment(value = "失败截图", type = "image/png")
	public byte[] takeScreenShot(String methodName) throws IOException {
	       return driver.getScreenshotAs(OutputType.BYTES);
	 }
	
	
}
