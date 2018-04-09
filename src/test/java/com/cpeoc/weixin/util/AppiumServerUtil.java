package com.cpeoc.weixin.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.tools.ant.types.CommandlineJava.SysProperties;

/**
 * AppiumServer工具类
 * 
 * @author ken
 * @date 2017-7-18
 * 
 */
public class AppiumServerUtil {

	// hubPID nodeServerPID list
	static List<Integer> serverPID = new ArrayList<Integer>();
	public static List<Integer> portList = new ArrayList<>();

	public static boolean startAppiumServer() throws InterruptedException {
		portList = PortUtil.getAvailablePortList(2);
		String serverhost = Config.serverHost;
		String deviceName = Config.deviceName;
		int serverStartUpTimeOut = 20000;// 单位毫秒
		int servertPort = portList.get(0);
		int bsPort = portList.get(1);
		String cmd = "appium -a " + serverhost + " -U \"" + deviceName
				+ "\" -p " + servertPort + " -bp " + bsPort + " > tmp"
				+ File.separator + deviceName.replaceAll(":", ".")
				+ "AppiumServer.log";
		
		Server s = new Server();
		s.cmd=cmd;
		s.start();
		
		System.out.println("运行命令：" + cmd);
		System.out.println("appium server启动中~~~~");
		long startTime = System.currentTimeMillis();
		while (true) {
			Thread.sleep(2000);
			if (PortUtil.isPortUsed(servertPort)) {
				// 获取PID
				int hub = PortUtil.getPidByPort(servertPort);
				serverPID.add(hub);
				System.out.println(deviceName + " appium server启动成功！");
				break;
			}
			long endTime = System.currentTimeMillis();
			if ((endTime - startTime) > serverStartUpTimeOut) {
				System.out.println("appium server 启动失败！设备名："
						+ deviceName.replaceAll(":", ".") + "端口号："
						+ servertPort);
				stopAppiumServer();
				// break;
				return false;
			}

		}
		return true;
	}

	/**
	 * 停止所有appium相关服务（selenium hub和appium server）
	 * 
	 * @return true 全部关闭成功 false 存在关闭异常
	 * @see 1.根据PID杀掉进程 </br> Windows:taskkill -f -pid pid </br> mac os:kill -9
	 *      pid </br>
	 */
	public static boolean stopAppiumServer() {
		String os = System.getProperty("os.name");
		for (int port : serverPID) {
			String cmd = os.contains("Windows") ? "taskkill -f -pid " + port
					: "kill -9 " + port;
			System.out.println("运行命令：" + cmd);
			
			Server s = new Server();
			s.cmd=cmd;
			s.start();
		}

		for (int port : serverPID) {
			if (PortUtil.isPortUsed(port)) {
				return false;
			}
		}
		return true;
	}

}


class Server extends Thread {
	String cmd;

	@Override
	public void run() {
		CmdUtil cu = new CmdUtil();
		cu.execCmd(cmd);
	}

}