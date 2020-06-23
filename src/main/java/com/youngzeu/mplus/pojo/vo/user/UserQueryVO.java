package com.youngzeu.mplus.pojo.vo.user;

import com.baomidou.mybatisplus.annotation.TableField;
import com.youngzeu.mplus.entity.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

// web <-> view use VO
@Data
@EqualsAndHashCode(callSuper=false)
public class UserQueryVO extends BaseEntity{

	private String userId;

	private String userAccount;

	private String password;

	private String userName;

	private String cardNumber;

	private String cardType;

	private String userAddr;

}
