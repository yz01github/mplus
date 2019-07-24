package com.youngzeu.mplus.task;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.youngzeu.mplus.volatiles.GetThread;
import com.youngzeu.mplus.volatiles.SetThread;
import com.youngzeu.mplus.volatiles.VolatileModel;

@Component
public class AsyncTask {
	
	@Async
	public void startGet(VolatileModel firstModel) {
		System.out.println("get thread started.");
		new GetThread(firstModel).run();
	}
	
	@Async
	public void startSet(VolatileModel secoendModel, Integer time) throws InterruptedException {
		System.out.println("set secoendThread started.");
		new SetThread(secoendModel, time).run();
		Thread.sleep(1000);
	}
}
