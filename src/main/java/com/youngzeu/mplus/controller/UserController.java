package com.youngzeu.mplus.controller;

import com.youngzeu.mplus.config.cached.SessionCached;
import com.youngzeu.mplus.pojo.vo.user.UserCreateVO;
import com.youngzeu.mplus.pojo.dto.user.UserDTO;
import com.youngzeu.mplus.entity.user.UserEntity;
import com.youngzeu.mplus.response.ResponseResult;
import com.youngzeu.mplus.service.permission.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

@Api(tags = "user相关接口")
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("/all")
	public List<UserEntity> selectAllUserDO() {
		return userService.selectList();
	}

	@DeleteMapping("/{id}")
	@ApiOperation(value = "删除单个用户")
	public ResponseResult<Integer> deleteUserDO(@PathVariable("id") String id) {
		log.debug("USER_IDFO:{}", SessionCached.getUser());
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
	public String jsonTest(@RequestBody UserEntity user) {
		System.out.println(user);
		return user.getPassword();
	}

	@ApiOperation(value = "用户登录")
	@PostMapping("/login")
	public ResponseResult login(@Valid UserEntity user){//启用参数校验
        UserDTO userDTO = userService.login(user);

        if (Objects.nonNull(userDTO)) {
			UserEntity userEntity = new UserEntity();
            BeanUtils.copyProperties(userEntity, userDTO);
			SessionCached.setUser(userEntity);
			SessionCached.loadPerission(userEntity);
            return ResponseResult.successAddData("登录成功");
        } else {
            return ResponseResult.failAddMessage("登录失败");
        }

    }
	
	@ApiOperation(value = "notNull")
	@GetMapping("/notNull")
	public UserEntity jsonParam(@Valid UserEntity id) {
		System.out.println(id);
		return id;
	}
	
	@GetMapping("/test/{id}")
	public ResponseResult<Integer> getTest(@PathVariable("id") String id) {
		UserEntity user = new UserEntity();
		Integer integer = userService.deleteById(id);
		return ResponseResult.successAddData(integer);
	}

	// 后面应该写个AOP，把@Validated报的错处理包装返回
	@ApiOperation("新增用户")
	@PostMapping("/newUser")
	public ResponseResult<UserEntity> getTest(@RequestBody @Validated UserCreateVO userVO) {
		UserDTO userDTO = new UserDTO();
		BeanUtils.copyProperties(userVO, userDTO);
		boolean isOk = userService.createUser(userDTO);
		return ResponseResult.result(isOk);
	}

	// 后面应该写个AOP，把@Validated报的错处理包装返回
	@ApiOperation("是否已存在该用户账号")
	@GetMapping("/valid/{userAccount}")
	public ResponseResult<UserEntity> vaildExist(@PathVariable("userAccount") String userAccount) {
		boolean isOk = userService.validExist(userAccount);
		return ResponseResult.result(isOk);
	}
}
