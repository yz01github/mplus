package com.youngzeu.mplus.pojo.dto.role;

import com.youngzeu.mplus.entity.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class RoleDTO extends BaseEntity {

    private String roleId;

    private String roleName;

    private String parentRoleId;

    private Integer orderNo;
}
