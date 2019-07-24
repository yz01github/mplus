package com.youngzeu.mplus.volatiles;

public class SetThread extends Thread {

	private VolatileModel model;
	
	private Integer time;

	public SetThread(VolatileModel model, Integer time) {
		this.model = model;
		this.time = time;
	}

	@Override
	public void run() {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(model.getName() + ": number set 1");
		model.setNumber(1);
		System.out.println(model.getName() + "start sleep 1000ms");
		System.out.println(model.getName() + "end sleep 1000ms");
	}
}
