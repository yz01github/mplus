package com.youngzeu.mplus.volatiles;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class MultiTthreadDemo {
	public static ExecutorService getExcutor() {
		return Executors.newFixedThreadPool(5);
	}
	
	public static Future<?> submit(Runnable task) {
		return getExcutor().submit(task);
	}
	
	public static Future<?> submit(Callable<?> task) {
		return getExcutor().submit(task);
	}
	
	@Test
	public void test1() {
		MultiTthreadDemo.submit(new RunThread());
		MultiTthreadDemo.submit(new CallThread());
	}
}

@Slf4j
class CallThread implements Callable<Integer> {

	@Override
	public Integer call() throws Exception {
		log.info(Thread.currentThread().getName() + " call ...");
		Thread.sleep(1111);
		return 1111;
	}
	
}

@Slf4j
class RunThread implements Runnable {

	@Override
	public void run() {
		log.info(Thread.currentThread().getName() + " run ...");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}

