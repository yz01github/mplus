package com.youngzeu.mplus.volatiles;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
public class VolatileModel {

	/**
	 * volatile关键字在只修饰字段，让变量在多个线程可见（去共享内存读取，而不是从线程工作内存中读取）
	 * 
	 */
	@Getter
	@Setter
	private volatile boolean canGet = false;
	
	@Getter
	@Setter
	private volatile Integer number = 0;
	
	@Getter
	@Setter
	private String name;
	
	private List<String> list = new ArrayList<>();
	
	public void getList(){
		while (true) {
			if (canGet) {
				for (String string : list) {
					System.out.println("get " + string);
				}
				break;
			}
		}
	}
	
	public void setList() throws InterruptedException {
		for (int i = 0; i < 10; i++) {
			Thread.sleep(1000);
			list.add("value" + i);
			System.out.println(Thread.currentThread().getName() + "添加元素：" + "value" + i);
			// 第六次后允许读取
			if (i == 5) {
				canGet = true;
				System.out.println("发出读取通知");
			}
		}
	}
	
	public static void main(String[] args) {
		VolatileModel model = new VolatileModel();
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					model.setList();
				} catch (InterruptedException e) {
				}
			}
		}, "t1").start();
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				model.getList();
			}
		}, "t2").start();
		
		
	}

	public VolatileModel(Integer number, String name) {
		this.number = number;
		this.name = name;
	}

}

