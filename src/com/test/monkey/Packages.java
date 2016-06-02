package com.test.monkey;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

public class Packages {

	public static int pkgCount = 0;
	static int n = 0;

	public static HashMap<String,String> getPackages(){
		HashMap<String,String> pkgs = new HashMap<String,String>();
		ArrayList<String> devices = Util.getDeviceID();
		int ids = devices.size();
		if(ids == 0)
			return pkgs;
		String command = null;
		for (int i=0; i<ids; i++){
			command = Init.adb + " -s " + devices.get(i) + " shell pm list packages";
			try {
				Process process = Runtime.getRuntime().exec(command);
				InputStream inputStream = process.getInputStream();
				InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
				BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
				String line;
				while ((line = bufferedReader.readLine()) != null) {
					if(!line.equals("")){
						line = line.substring(8);
						pkgs.put(line, "value");
					}
				}
				bufferedReader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		pkgCount = pkgs.size();
		n++;
		return pkgs;
	}

}
