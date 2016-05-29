package com.test.monkey;

import java.io.File;

public class Init extends Thread {
	
	static File reportPath, dailyReport;
	
	public Init() {
		reportPath = new File("D:\\Monkey Tool");
		if(!reportPath.exists())
			reportPath.mkdirs();
		dailyReport = new File(reportPath,Util.getTime(true));
		if(!dailyReport.exists())
			dailyReport.mkdirs();
	}
	
	public static File getReportPath() {
		return dailyReport;
	}
}
