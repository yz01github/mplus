package com.youngzeu.mplus.util;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainTest {

	public static void main(String[] args) {
		new MainTest();
	}

	public MainTest() {

		for (int i = 0; i < 80; i++) {
			A a = new A();
			B.getThreadPool().submit(a);
		}
		System.out.println("end >>>>>>>>>>" );
	}
}

class A implements Runnable {

	@Override
	public void run() {
		try {
			Thread.currentThread().sleep(10 * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName() + " : " + " hello world + " + System.currentTimeMillis());
	}

}

class B {
	private static ExecutorService exe = Executors.newFixedThreadPool(5);

	public static ExecutorService getThreadPool() {
		return exe;
	}
	
	
}