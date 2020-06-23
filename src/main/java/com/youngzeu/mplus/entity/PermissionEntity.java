package com.youngzeu.mplus.entity;

import com.youngzeu.mplus.entity.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = false)
public class PermissionEntity extends BaseEntity {

    private String permId;

    private String permType;

    private String permName;

    private String remark;

    private String spareStr1;

    private String spareStr2;

    private String spareStr3;

    private LocalDateTime spareDate1;

    private String spareTag1;

}