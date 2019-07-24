package com.youngzeu.mplus.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.youngzeu.mplus.response.ResponseResult;
import com.youngzeu.mplus.task.AsyncTask;
import com.youngzeu.mplus.volatiles.VolatileModel;

/**
 * <p>Title: VolatileController</p>  
 * <p>Description: [volatile关键字接口]</p>  
 * @author youngZeu  
 * created 2019年7月19日
 */
@RestController
@RequestMapping("/volatile")
public class VolatileController {
	
	@Autowired
	private AsyncTask task;

	@GetMapping("test")
	public ResponseResult<String> moreThreadTest() throws InterruptedException{
		// 通过打印结果可知，多个线程间可用一个拥有volatile修饰字段的对象进行通信，且多个对象间互不影响
		// 这种方法比使用static变量用来通信的优点就在于：不相关的多个线程，可以使用同一个类的不同实例，互不影响
		VolatileModel firstModel = new VolatileModel(0, "NAME 1");
		VolatileModel secoendModel = new VolatileModel(2, "NAME 2");
		System.out.println(Thread.currentThread().getName());
		task.startGet(firstModel);
		task.startSet(secoendModel,1000);
		task.startSet(firstModel, 3000);
		return ResponseResult.success();
	}
	
}
