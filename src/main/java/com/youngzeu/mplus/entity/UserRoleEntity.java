package com.youngzeu.mplus.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.youngzeu.mplus.entity.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@TableName(value = "TR_USER_ROLE")
@EqualsAndHashCode(callSuper = false)
public class UserRoleEntity extends BaseEntity {

    private String userId;

    private String roleId;

    private String spareStr1;

    private Date spareStr2;

    private String spareStr3;

    private Date spareDate1;

    private Date spareTag1;

}