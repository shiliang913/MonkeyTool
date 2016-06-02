package com.test.monkey;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JScrollBar;

public class Util {
	
	public static void appendTextArea(String content){
		Frame.textArea.append(content + "\n");
		Frame.jScrollBar.setValue(Frame.jScrollBar.getMaximum());
	}

	public static String getTime(boolean isDate){
		SimpleDateFormat simpleDateFormat = null;
		if(isDate)
			simpleDateFormat = new SimpleDateFormat("yyyy_MM_dd");
		else
			simpleDateFormat = new SimpleDateFormat("MMdd_HHmmss");
		Date date = new Date(System.currentTimeMillis());
		String time = simpleDateFormat.format(date);
		return time;
	}

	public static ArrayList<String> getDeviceID(){
		ArrayList<String> ids = new ArrayList<String>();
		try {
			Process process = Runtime.getRuntime().exec(Init.adb + " devices");
			InputStream inputStream = process.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
			String line;
			while((line=bufferedReader.readLine()) != null){
				if(line.endsWith("device")){
					int end = line.indexOf("\t");
					ids.add(line.substring(0, end));
				}
			}
			bufferedReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ids;
	}

	public static void startMonkey(){
		new Thread() {
			public void run() {
				try {
					Util.StopMonkey stopMonkey = new Util.StopMonkey();
					stopMonkey.start();
					stopMonkey.join();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				ArrayList<String> pkgs = PackageList.getCheckedPkgs();
				StringBuffer stringBuffer = new StringBuffer("");
				for (int i = 0,n=pkgs.size(); i < n; i++) {
					stringBuffer.append("-p " + pkgs.get(i) + " ");
				}
				final ArrayList<String> ids = getDeviceID();
				for (int i = 0; i < ids.size(); i++) {
					String command = Init.adb + " -s " + ids.get(i) + " shell monkey --throttle 500 "
							+ stringBuffer.toString() + "--ignore-crashes --ignore-timeouts --ignore-security-exceptions -v -v -v "
							+ Frame.textField.getText();
					Util.appendTextArea(command.replace(Init.adb, "adb"));
					new Report(command).start();
				}
			}
		}.start();
	}

	static class StopMonkey extends Thread {
		public void run() {
			ArrayList<String> devices = getDeviceID();
			for (int i = 0; i < devices.size(); i++) {
				try {
					Process process = Runtime.getRuntime().exec(Init.adb + " -s " + devices.get(i) +" shell ps | " + Init.find +" monkey");
					InputStream inputStream = process.getInputStream();
					InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
					BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
					String line = bufferedReader.readLine();
					if(line != null){
						line = line.replace("shell","").trim();
						int end = line.indexOf(" ");
						String command = Init.adb + " -s " + devices.get(i) +" shell kill " + line.substring(0,end);
						Util.appendTextArea(command.replace(Init.adb, "adb"));
						Runtime.getRuntime().exec(command);
					}
					bufferedReader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}







