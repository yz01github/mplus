package com.youngzeu.mplus.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.youngzeu.mplus.entity.User;
import com.youngzeu.mplus.response.ResponseResult;
import com.youngzeu.mplus.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "user相关接口")
@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("/all")
	public List<User> selectAllUser() {
		return userService.selectList();
	}

	@GetMapping("/{id}")
	@ApiOperation(value = "删除用户")
	public Boolean deleteUser(@PathVariable("id") Integer id) {
		System.out.println("id ------------>"+id);
		return userService.deleteById(id) > 0;
	}

	@ApiOperation(value = "参数校验")
	@PutMapping("/{name}")
	public String validParam(@PathVariable("name") String name) {
		System.out.println(name);
		return name;
	}

	@ApiOperation(value = "json参数")
	@PutMapping("/json")
	public String jsonTest(@RequestBody User user) {
		System.out.println(user);
		return user.getPassword();
	}

	@ApiOperation(value = "json参数")
	@PutMapping("/jsonTest")
	public String jsonTest1(@RequestBody String ppp) {
		System.out.println(ppp);
		return ppp;
	}
	
	@ApiOperation(value = "用户登录")
	@PostMapping("/login")
	public ResponseResult login(@Valid User user){//启用参数校验
		Boolean flag = userService.login(user);
		if (flag) {
			return ResponseResult.successAddData("登录成功");
		} else {
			return ResponseResult.failAddMessage("登录失败");
		}
	}
	
	

	@ApiOperation(value = "notNull")
	@GetMapping("/notNull")
	public User jsonParam(@Valid User id) {
		System.out.println(id);
		return id;
	}
	
	@ApiOperation("delete rest {id}")
	@DeleteMapping("/{id}")
	public String deleteTest(@PathVariable("id") String id) {
		System.out.println("id : " + id);
		return id;
	}
	
	@GetMapping("/test/{id}")
	public ResponseResult<User> getTest(@PathVariable("id") String id) {
		User user = new User();
		user.setId(Long.parseLong(id));
		user.setUserName("测试user");
		return ResponseResult.successAddData(user);
	}
}
