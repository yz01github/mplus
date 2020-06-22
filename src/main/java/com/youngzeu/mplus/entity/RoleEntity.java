package com.youngzeu.mplus.entity;

import com.youngzeu.mplus.entity.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
public class RoleEntity extends BaseEntity {

    private String roleId;

    private String roleName;

    private String parentRoleId;

    private Integer orderNo;

    private String spareStr1;

    private Date spareStr2;

    private String spareStr3;

    private Date spareDate1;

    private Date spareTag1;

}