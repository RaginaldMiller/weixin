package com.cpeoc.weixin;

import java.util.Set;

import io.appium.java_client.functions.ExpectedCondition;









import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.cpeoc.weixin.BaseDriver;
import com.cpeoc.weixin.util.WeixinUtil;

public class TestDataProvider {

	@Test(dataProvider="menu")
	public void testOpenMenu(String menu,String childMenu,final String expect) throws InterruptedException {
	
	
		
		
		Assert.assertEquals("我去你大爷",expect);
		System.out.println("menu:"+ menu+","+"childMenu:"+childMenu+","+"expect:"+expect);

	} 

	@DataProvider(name = "menu")
	public Object[][] menu() {
		return new Object[][] { 
				{ "com.tencent.mm:id/aas", "//*[@text='查邮件/资费/网点/邮编']","车主服务"},
				{ "com.tencent.mm:id/aas", "//*[@text='本地资讯']","我去你大爷"},
				{ "com.tencent.mm:id/aas", "//*[@text='政务导航']","公共服务"},
				{ "com.tencent.mm:id/aas", "//*[@text='金融服务']","投资理财"},
				{ "com.tencent.mm:id/aas", "//*[@text='寄递服务']","寄递指南"},
				};
	}
	
	
	public static void main(String[] args) {
		String currentPath = System.getProperty("user.dir"); 
		System.out.println(currentPath);
	}
}
