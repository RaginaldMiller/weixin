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

@Epic("微商城模块")
public class WeiShangCheng extends BaseDriver {

	@Test(dataProvider = "menu")
	@Description("打开菜单")
	public void testOpenMenu(String menu, String childMenu, final String expect)
			throws InterruptedException {
//		System.out.println(menu+":"+childMenu+":"+expect);
		WeixinUtil.openChinaPost(driver);
		// driver.findElementById(menu).click();
		String menuXpath = "//*[contains(@text,'" + menu + "')]";
		driver.findElementByXPath(menuXpath).click();
		driver.findElementByXPath(childMenu).click();

		// 授权弹框 com.tencent.mm:id/aln 确定 NATIVEAPP
		if (childMenu.contains("邮乐特卖")) {
			try {
				// driver.findElementById("com.tencent.mm:id/aln").click();
				driver.findElementByXPath("//*[contains(@text,'允许')]").click();
			} catch (Exception e) {
			}
		}
		if (childMenu.contains("简易保险")||childMenu.contains("报刊订阅")) {
			try {
				driver.findElementByXPath("//*[contains(@text,'确定')]").click();
			} catch (Exception e) {
			}
			try {
				driver.findElementById("确定").click();
			} catch (Exception e) {
			}
		}

		if (expect != "DIY明信片") {
			driver.context("WEBVIEW_com.tencent.mm:tools");
		}

		String xpath = "//*[contains(text(),'" + expect + "')]";
		WebElement element = null;
		boolean flag = false;
		if (expect == "DIY明信片") {
			element = driver.findElementById("btnAddPic");
			if (element != null) {
				if (element.isDisplayed()) {
					flag = true;
				}
			}
		} else {
			Set<String> windowHandles = driver.getWindowHandles();

			for (String window : windowHandles) {
				driver.switchTo().window(window);

				try {

					element = driver.findElement(By.xpath(xpath));

				} catch (Exception e) {

				}
				if (element != null) {
					if (element.isDisplayed()) {
						flag = true;
						break;
					}
				}

			}
		}
		// 失败截图
		if (!flag) {
			try {
				driver.context("NATIVE_APP");
				takeScreenShot(expect);
			} catch (Exception e) {
				Assert.fail("截图失败！");
			}
		}
		// 返回微信首页
		WeixinUtil.goBackWeChat(driver);
		// 断言
		Assert.assertEquals(flag, true);

	}

	@DataProvider(name = "menu")
	public Object[][] menu() {
		return new Object[][] { 
				{ "微商城", "//*[@text='邮乐特卖']", "热销商品" },
				{ "微商城", "//*[@text='集邮商城']", "新邮预订" },
				{ "微商城", "//*[@text='报刊订阅']", "热卖排行" },
				{ "微商城", "//*[@text='简易保险']", "意外险" },
				{ "微商城", "//*[@text='DIY明信片']", "DIY明信片" }, 
				};
	}
}
