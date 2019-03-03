package com.youngzeu.mplus.entity;

import com.youngzeu.mplus.entity.base.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class User extends BaseEntity{
	
	private String userName;
	
	private String password;
}
