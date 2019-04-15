package com.youngzeu.mplus.util;

import java.io.File;

public class FileThreadTest implements Runnable{

	private static File file;
	
	public FileThreadTest(File file) {
		this.file = file;
	}

	@Override
	public void run() {
		System.out.println(file.getName());
	}
	
	public static void main(String[] args) {
		ThreadPoolUtils.getThreadPool().submit(new FileThreadTest(new File("D:/aaa.txt")));
		
	}
}
