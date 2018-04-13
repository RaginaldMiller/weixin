package com.cpeoc.weixin.testcases;


import io.qameta.allure.Description;
import io.qameta.allure.Epic;

import java.util.Set;











import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.cpeoc.weixin.BaseDriver;
import com.cpeoc.weixin.util.WeixinUtil;
@Epic("微服务模块")
public class WeiFuWu extends BaseDriver {


	@Test(dataProvider="menu")
	@Description("打开菜单")
	public void testOpenMenu(String menu,String childMenu,final String expect) throws InterruptedException {
//		System.out.println(menu+":"+childMenu+":"+expect);
		WeixinUtil.openChinaPost(driver);
		//driver.findElementById(menu).click();
		String menuXpath =  "//*[contains(@text,'"+menu+"')]";
		driver.findElementByXPath(menuXpath).click();
		driver.findElementByXPath(childMenu).click();
		
		//授权弹框    com.tencent.mm:id/aln 确定  NATIVEAPP
		try {
			//driver.findElementById("com.tencent.mm:id/aln").click();
			driver.findElementByXPath("//*[contains(@text,'确定')]").click();
		} catch (Exception e) {
			// TODO: handle exception
		}
		//Thread.sleep(5000);
		driver.context("WEBVIEW_com.tencent.mm:tools");
	
		Set<String> windowHandles = driver.getWindowHandles();
		boolean flag = false;
		for (String window : windowHandles) {
			driver.switchTo().window(window);
			
			WebElement element = null;
			try {
				String xpath = "//*[contains(text(),'"+expect+"')]";
				element = driver.findElement(By.xpath(xpath));
			} catch (Exception e) {
				
			}
			if(element!= null){
				if( element.isDisplayed()){
					flag = true;
					break;
				}
			}
					
		}
		//失败截图 
		if(!flag){		
			try {
				driver.context("NATIVE_APP");
				takeScreenShot(expect);							
			} catch (Exception e) {	
				Assert.fail("截图失败！");
			}		
		}
		//返回微信首页
		WeixinUtil.goBackWeChat(driver);
		//断言
		Assert.assertEquals(flag,true);

	} 

	@DataProvider(name = "menu")
	public Object[][] menu() {
		return new Object[][] { 
				{ "微服务", "//*[@text='查邮件/资费/网点/邮编']","车主服务"},
				{ "微服务", "//*[@text='本地资讯']","广东省东莞市"},
				{ "微服务", "//*[@text='政务导航']","公共服务"},
				{ "微服务", "//*[@text='金融服务']","投资理财"},
				{ "微服务", "//*[@text='寄递服务']","寄递指南"},
				};
	}
}
