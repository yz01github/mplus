package com.youngzeu.mplus.entity;

import javax.validation.constraints.NotNull;

import com.youngzeu.mplus.entity.base.BaseEntity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class User extends BaseEntity{
	
	//@Excel注解不要管
//	@Excel(name = "姓名", orderNum = "0")
	
	@NotNull(message = "用户名不能为空")
	private String userName;
	
	//@NotNull 的作用就是在前端进入后台方法之前可以拦截掉不符合参数要求的请求，
	//@NotNull 即该参数传到后台时不能为null
	//这个校验是可选的，如果你不在参数前加@Valid，就代表你不启用这些校验
	@Excel(name = "年龄", orderNum = "1")
	@NotNull(message = "登录密码不能为空")
	private String password;
}
