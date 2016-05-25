package com.test.monkey;

import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javax.swing.JFrame;

public class Util {
	
	int i, n;
	
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
	
	public static void stopMonkey(){
		new Thread() {
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
		}.start();
	}
	
}







