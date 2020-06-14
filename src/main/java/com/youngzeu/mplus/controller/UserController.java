package com.youngzeu.mplus.controller;

import com.youngzeu.mplus.entity.user.UserCreateVO;
import com.youngzeu.mplus.entity.user.UserDO;
import com.youngzeu.mplus.entity.user.UserDTO;
import com.youngzeu.mplus.response.ResponseResult;
import com.youngzeu.mplus.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(tags = "user相关接口")
@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("/all")
	public List<UserDO> selectAllUserDO() {
		return userService.selectList();
	}

	@GetMapping("/{id}")
	@ApiOperation(value = "删除用户")
	public Boolean deleteUserDO(@PathVariable("id") Integer id) {
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
	public String jsonTest(@RequestBody UserDO user) {
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
	public ResponseResult login(@Valid UserDO user){//启用参数校验
		Boolean flag = userService.login(user);
		if (flag) {
			return ResponseResult.successAddData("登录成功");
		} else {
			return ResponseResult.failAddMessage("登录失败");
		}
	}
	
	@ApiOperation(value = "notNull")
	@GetMapping("/notNull")
	public UserDO jsonParam(@Valid UserDO id) {
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
	public ResponseResult<UserDO> getTest(@PathVariable("id") String id) {
		UserDO user = new UserDO();
		user.setId(Long.parseLong(id));
		user.setUserName("测试user");
		return ResponseResult.successAddData(user);
	}

	// 后面应该写个AOP，把@Validated报的错处理包装返回
	@ApiOperation("新增用户")
	@PostMapping("/newUser")
	public ResponseResult<UserDO> getTest(@RequestBody @Validated UserCreateVO userVO) {
		UserDTO userDTO = new UserDTO();
		BeanUtils.copyProperties(userVO, userDTO);
		boolean isOk = userService.createUser(userDTO);
		return ResponseResult.result(isOk);
	}
}
