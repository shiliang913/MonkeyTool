package com.test.monkey;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Report extends Thread {
	
	String command,deviceId;
	
	public Report(String command) {
		this.command = command;
		deviceId = command.replace(Init.adb, "adb").substring(7);
		deviceId = deviceId.substring(0, deviceId.indexOf(" "));
	}
	
	@Override
	public void run() {
		try {
			Process process = Runtime.getRuntime().exec(command);
			InputStream inputStream = process.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
			File report = new File(Init.getReportPath(), Util.getTime(false)+"_"+deviceId+".log");
			FileOutputStream fileOutputStream = new FileOutputStream(report);
			OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
			BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				if(line.contains("CRASH") || line.contains("ANR"))
					Util.appendTextArea(deviceId+":"+line);
				bufferedWriter.write(line);
				bufferedWriter.newLine();
			}
			bufferedWriter.flush();
			bufferedWriter.close();
			bufferedReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
