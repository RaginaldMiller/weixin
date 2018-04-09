package com.cpeoc.weixin;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.testng.IReporter;
import org.testng.TestNG;

public class TestGetStatus {
	public static void main(String[] args) {		
		TestNG testNG = new TestNG();
		List<String> suites = new ArrayList<String>();
		suites.add(System.getProperty("user.dir")+File.separator+"tmp"+File.separator+"testng.xml");
		testNG.setTestSuites(suites);

		testNG.setOutputDirectory("tmp"+File.separator+"test-output");
		testNG.run();
				
		System.out.println(testNG.getStatus());	
		//status !=0 则有用例失败  //获取失败的用例名称？
		System.out.println(testNG.hasFailure());
		
		
	}
}
