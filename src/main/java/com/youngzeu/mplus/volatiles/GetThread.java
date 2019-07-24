package com.youngzeu.mplus.volatiles;

public class GetThread extends Thread {

	private VolatileModel model;

	public GetThread(VolatileModel model) {
		super();
		this.model = model;
	}

	@Override
	public void run() {
		int i = 0;
		while(true) {
			if (6 == i++) {
				break;
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (model.getNumber() == 1) {
				System.out.println(Thread.currentThread().getName() + ": number is " + 1);
			} else {
				System.out.println(Thread.currentThread().getName() + ": number is other");
			}
		} 
	}
}
