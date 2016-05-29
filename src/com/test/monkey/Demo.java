package com.test.monkey;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Demo {

	public static void main(String[] args) throws IOException {
		File file = new File("");
		System.out.println(System.getProperty("java.class.path"));
		System.out.println(System.getProperty("path.separator"));
	}
}
