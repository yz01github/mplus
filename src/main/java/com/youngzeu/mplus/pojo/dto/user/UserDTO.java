package com.youngzeu.mplus.pojo.dto.user;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.youngzeu.mplus.entity.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

// DTO与VO的区别和区分开的意义:
/* 一个DTO若对应一个VO，则DTO=VO，但一个DTO对应多个VO，就必须区分出来，达到服务层和展示层解耦的目的
	例: DTO 返回性别为 1,表示男性； 有两个页面VO1,VO2;
	VO1想把性别列是“男”的展示成“帅哥”
	VO2想把性别列是“男”的展示成“小哥哥”
	这个时候DTO和VO就区分出来了，各自页面封装各自对应的VO，服务层DTO只有一个返回
 */

// service <-> web use DTO
@Data
@EqualsAndHashCode(callSuper=false)
public class UserDTO extends BaseEntity{

	private String userId;

	private String userAccount;

	private String password;

	private String userName;

	private String cardNumber;

	private String cardType;

	private String userAddr;

}
