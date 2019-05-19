package com.youngzeu.mplus.response;

import lombok.Data;

@Data
public class ResponseResult {

	private Integer status;//返回状态码,200成功，500失败
	
	private Object data;//返回给前端的数据，可以是任何数据
	
	private String message;//返回给前端的提示信息

	public static ResponseResult success() {
		//返回一个只带200状态码的结果对象（请求成功对象）
		ResponseResult result = new ResponseResult();
		result.setStatus(200);
		return result;
	}
	public static ResponseResult fail() {
		//返回一个只带500状态码的结果对象（请求失败对象）
		ResponseResult result = new ResponseResult();
		result.setStatus(500);
		return result;
	}
	public static ResponseResult failAddMessage(String message) {
		//返回一个带500状态码并且附带失败信息的结果对象
		ResponseResult result = fail();
		result.setMessage(message);
		return result;
	}
	public static ResponseResult successAddData(Object obj) {
		//返回一个带200状态码并且附带所请求数据的结果对象
		ResponseResult result = success();
		result.setData(obj);
		return result;
	}
}
