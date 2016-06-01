package com.test.monkey;

import java.util.HashMap;

public class Packages {
	
	public static int pkgCount = 0;
	static int n = 0;
	
	public static HashMap<String,String> getPackages(){
		HashMap<String,String> pkgs = new HashMap<String,String>();
		pkgs.put("com.android.camera"+n,"camera");
		pkgCount = pkgs.size();
		n++;
		return pkgs;
	}
	
	public static int getPkgCount(){
		return pkgCount;
	}

}
