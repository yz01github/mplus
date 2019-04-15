package com.youngzeu.mplus.util;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolUtils {

	private static ExecutorService exe = Executors.newFixedThreadPool(5);

	public static ExecutorService getThreadPool() {
		return exe;
	}

}
