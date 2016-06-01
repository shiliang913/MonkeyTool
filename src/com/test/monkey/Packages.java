package com.test.monkey;

import java.util.HashMap;

public class Packages {
	
	public static int pkgCount = 0;
	
	public static HashMap<String,String> getPackages(){
		HashMap<String,String> pkgs = new HashMap<String,String>();
		pkgs.put("com.android.camera","camera");
		pkgs.put("com.android.camera2","camera");
		pkgs.put("com.android.camera3","camera3");
		pkgCount = pkgs.size();
		return pkgs;
	}
	
	public static int getPkgCount(){
		return pkgCount;
	}

}
