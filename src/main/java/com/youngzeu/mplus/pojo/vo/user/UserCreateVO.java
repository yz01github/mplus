package com.youngzeu.mplus.pojo.vo.user;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;

// web <-> view use VO
@Data
@EqualsAndHashCode(callSuper=false)
public class UserCreateVO {

	@NotBlank(message = "userAccount不能为空")
	private String userAccount;

	@NotBlank(message = "password不能为空")
	private String password;

	private String userName;

	private String cardNumber;

	private String cardType;

	private String userAddr;

}
