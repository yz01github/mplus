package com.youngzeu.mplus.response;

import lombok.Data;

@Data
public class ResponseResult<T> {

	/**
	 * 操作成功返回提示信息
	 */
	private static final String FAILED_MESSAGE = "操作失败";
	
	/**
	 * 操作失败返回提示信息
	 */
	private static final String SUCCESS_MESSAGE = "操作成功";

	/**
	 * 返回状态码
	 */
	private Integer status;
	
	/**
	 * 返回数据
	 */
	private T data;
	
	/**
	 * 提示信息
	 */
	private String message;

	public static <T> ResponseResult<T> success() {
		//返回一个只带200状态码的结果对象（请求成功对象）
		ResponseResult<T> result = new ResponseResult<T>();
		result.setStatus(200);
		result.setMessage(SUCCESS_MESSAGE);
		return result;
	}
	public static <T> ResponseResult<T> fail() {
		//返回一个只带500状态码的结果对象（请求失败对象）
		ResponseResult<T> result = new ResponseResult<T>();
		result.setStatus(500);
		result.setMessage(FAILED_MESSAGE);
		return result;
	}
	public static <T> ResponseResult<T> failAddMessage(String message) {
		//返回一个带500状态码并且附带失败信息的结果对象
		ResponseResult<T> result = fail();
		result.setMessage(message);
		return result;
	}
	public static <T> ResponseResult<T> successAddData(T data) {
		//返回一个带200状态码并且附带所请求数据的结果对象
		ResponseResult<T> result = success();
		result.setData(data);
		return result;
	}
}
