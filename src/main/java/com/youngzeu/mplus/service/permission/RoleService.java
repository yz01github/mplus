package com.youngzeu.mplus.service.permission;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.youngzeu.mplus.entity.RoleEntity;
import com.youngzeu.mplus.pojo.dto.page.PageDTO;
import com.youngzeu.mplus.pojo.dto.role.CreateRoleDTO;
import com.youngzeu.mplus.pojo.dto.role.QueryRoleDTO;
import com.youngzeu.mplus.pojo.dto.role.RoleDTO;
import com.youngzeu.mplus.pojo.dto.role.UpdateRoleDTO;

public interface RoleService extends IService<RoleEntity> {

    int createRole(CreateRoleDTO roleDTO);

    int deleteRole(RoleDTO roleDTO);

    int updateRole(UpdateRoleDTO roleDTO);

    IPage<QueryRoleDTO> qryRoles(QueryRoleDTO roleDTO, PageDTO<RoleDTO> pageDTO);

    IPage<QueryRoleDTO> qryRoles1(QueryRoleDTO roleDTO, PageDTO<RoleDTO> pageDTO);
}
