package com.youngzeu.mplus.entity.user;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.youngzeu.mplus.entity.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
// DTO与DO的区别和区分开的意义:
/* DO对应的是表结构，一个DO可能根据业务不同会对应多个不同的DTO
   但无论如何，service层返回的，都应当是DTO而不是DO，这样可以有效他人获取数据表的表结构
   在一定程度上保护了数据结构隐私
 */

// Dao <-> database use DO
@Data
@EqualsAndHashCode(callSuper=false)
@TableName(value = "TI_USER")
public class UserDO extends BaseEntity{

	@TableField("USER_ID")
	private String userId;

	@TableField("USER_ACCOUNT")
	private String userAccount;

	@TableField("USER_PASSWORD")
	private String password;

	@TableField("USER_NAME")
	private String userName;

	@TableField("USER_CARD_NUMBER")
	private String cardNumber;

	@TableField("USER_CARD_TYPE")
	private String cardType;

	@TableField("USER_ADDR")
	private String userAddr;

}
