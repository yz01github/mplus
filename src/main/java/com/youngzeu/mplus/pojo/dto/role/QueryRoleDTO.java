package com.youngzeu.mplus.pojo.dto.role;

import com.youngzeu.mplus.pojo.dto.perm.PermDTO;
import com.youngzeu.mplus.pojo.dto.user.UserDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class QueryRoleDTO extends RoleDTO {

    // 是否查询角色对应的User
    private boolean qryUser;

    // 是否查询角色对应的权限
    private boolean qryPerm;

    // 对应的User集合
    private List<UserDTO> userList;

    // 对应的Permission集合
    private List<PermDTO> permList;
}
