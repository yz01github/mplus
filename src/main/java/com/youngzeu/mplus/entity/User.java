package com.youngzeu.mplus.entity;

import com.youngzeu.mplus.entity.base.BaseEntity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class User extends BaseEntity{
	
	@Excel(name = "姓名", orderNum = "0")
	private String userName;
	
	@Excel(name = "年龄", orderNum = "1")
	private String password;
}
