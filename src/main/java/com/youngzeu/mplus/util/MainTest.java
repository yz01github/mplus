package com.youngzeu.mplus.util;


import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;



public class MainTest {

	public static void main(String[] args) {
		// new MainTest();
		/*
		 * HashMap<String,Object> map = new HashMap<String, Object>(4);
		 * map.put("key1","value1"); ArrayList list = new ArrayList<String>();
		 * list.add("list1");
		 * 
		 * LinkedList<Object> linkedList = new LinkedList<>(); linkedList.add(map);
		 * linkedList.add(list); String jsonString =
		 * JSONObject.toJSONString(linkedList); System.out.println(jsonString);
		 */
		
		"".replaceAll("", "");
		
	}

	public MainTest() {
		/*
		 * for (int i = 0; i < 80; i++) { A a = new A(); B.getThreadPool().submit(a); }
		 * System.out.println("end >>>>>>>>>>" );
		 */
	}
	public void Test() {
		System.out.println(UUID.randomUUID().toString().length());
		/*
		 * for (int i = 0; i < 80; i++) { A a = new A(); B.getThreadPool().submit(a); }
		 * System.out.println("end >>>>>>>>>>" );
		 */
	}
}

class A implements Runnable {

	@Override
	public void run() {
		try {
			Thread.sleep(10 * 1000);
		} catch (InterruptedException e) {
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