package com.youngzeu.mplus.controller;

import com.youngzeu.mplus.config.cached.SessionCached;
import com.youngzeu.mplus.entity.user.UserCreateVO;
import com.youngzeu.mplus.entity.user.UserDO;
import com.youngzeu.mplus.entity.user.UserDTO;
import com.youngzeu.mplus.response.ResponseResult;
import com.youngzeu.mplus.service.permission.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

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

	@DeleteMapping("/{id}")
	@ApiOperation(value = "删除单个用户")
	public ResponseResult<Integer> deleteUserDO(@PathVariable("id") String id) {
		Integer integer = userService.deleteById(id);
		return ResponseResult.successAddData(integer);
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

	@ApiOperation(value = "用户登录")
	@PostMapping("/login")
	public ResponseResult login(@Valid UserDO user){//启用参数校验
        UserDTO userDTO = userService.login(user);

        if (Objects.nonNull(userDTO)) {
            UserDO userDO = new UserDO();
            BeanUtils.copyProperties(userDO, userDTO);
            SessionCached.setUser(userDO);
            SessionCached.loadPerission(userDO);
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
	
	@GetMapping("/test/{id}")
	public ResponseResult<Integer> getTest(@PathVariable("id") String id) {
		UserDO user = new UserDO();
		Integer integer = userService.deleteById(id);
		return ResponseResult.successAddData(integer);
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

	// 后面应该写个AOP，把@Validated报的错处理包装返回
	@ApiOperation("是否已存在该用户账号")
	@GetMapping("/valid/{userAccount}")
	public ResponseResult<UserDO> vaildExist(@PathVariable("userAccount") String userAccount) {
		boolean isOk = userService.validExist(userAccount);
		return ResponseResult.result(isOk);
	}
}
