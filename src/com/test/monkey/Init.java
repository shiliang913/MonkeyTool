package com.test.monkey;

import java.io.File;

public class Init extends Thread {

	static File reportPath, dailyReport;
	static boolean isWindows;
	static String adb, find;

	public Init() {
		setOS();
		setReportPath();
		setAdb();
	}

	private void setAdb() {
		if(isWindows){
			adb = "cmd /c adb";
			find = "findstr";
		}
		else{
			adb = "/Users/shiliang/Downloads/sdk/platform-tools/adb";
			find = "grep";
		}
	}

	private void setReportPath() {
		if(isWindows)
			reportPath = new File("D:\\Monkey logs");
		else
			reportPath = new File("/Users/shiliang/Downloads/Monkey logs");
		if(!reportPath.exists())
			reportPath.mkdirs();
		dailyReport = new File(reportPath,Util.getTime(true));
		if(!dailyReport.exists())
			dailyReport.mkdirs();
	}

	private void setOS() {
		if(System.getProperty("os.name").contains("Windows"))
			isWindows = true;
		else
			isWindows = false;
	}

	public static File getReportPath() {
		return dailyReport;
	}
}
