package com.youngzeu.mplus.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.youngzeu.mplus.entity.User;
import com.youngzeu.mplus.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("/")
	public List<User> selectAllUser() {
		return userService.selectList();
	}

	@GetMapping("/{id}")
	public Boolean deleteUser(@PathVariable("id") Integer id) {
		System.out.println("id ------------>"+id);
		return userService.deleteById(id) > 0;
	}

}
