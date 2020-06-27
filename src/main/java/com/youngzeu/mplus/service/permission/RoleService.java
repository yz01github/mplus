package com.youngzeu.mplus.service.permission;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.youngzeu.mplus.pojo.dto.role.CreateRoleDTO;
import com.youngzeu.mplus.pojo.dto.role.RoleDTO;
import com.youngzeu.mplus.pojo.dto.role.UpdateRoleDTO;

import java.util.List;

public interface RoleService {

    int createRole(CreateRoleDTO roleDTO);

    int deleteRole(RoleDTO roleDTO);

    int updateRole(UpdateRoleDTO roleDTO);

    List<RoleDTO> qryRoles(RoleDTO roleDTO, Page<RoleDTO> page);

}
