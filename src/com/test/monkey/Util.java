package com.test.monkey;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Util {
	
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
			Process process = Runtime.getRuntime().exec("cmd /c adb devices");
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
				final ArrayList<String> ids = getDeviceID();
				for (int i = 0; i < ids.size(); i++) {
					final String command = "adb -s " + ids.get(i) + " shell monkey -s 100 -p com.android.settings --throttle 100 "
							+ "--ignore-crashes --ignore-timeouts --ignore-security-exceptions -v -v -v "
							+ Frame.textField.getText();
					Frame.textArea.append(command + "\n");
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
					Process process = Runtime.getRuntime().exec("cmd /c adb -s " + devices.get(i) +" shell ps | findstr monkey");
					InputStream inputStream = process.getInputStream();
					InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
					BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
					String line = bufferedReader.readLine();
					if(line != null){
						line = line.replace("shell","").trim();
						int end = line.indexOf(" ");
						String command = "adb -s " + devices.get(i) +" shell kill " + line.substring(0,end);
						Frame.textArea.append(command+"\n");
						Runtime.getRuntime().exec("cmd /c " + command);
					}
					bufferedReader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}







