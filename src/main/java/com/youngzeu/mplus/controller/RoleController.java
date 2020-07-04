package com.youngzeu.mplus.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.youngzeu.mplus.entity.RoleEntity;
import com.youngzeu.mplus.pojo.dto.page.PageDTO;
import com.youngzeu.mplus.pojo.dto.role.CreateRoleDTO;
import com.youngzeu.mplus.pojo.dto.role.QueryRoleDTO;
import com.youngzeu.mplus.pojo.dto.role.RoleDTO;
import com.youngzeu.mplus.pojo.dto.role.UpdateRoleDTO;
import com.youngzeu.mplus.response.ResponseResult;
import com.youngzeu.mplus.service.permission.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.ParameterScriptAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;

import static com.youngzeu.mplus.response.ResponseResult.SUCCESS_MESSAGE;
import static com.youngzeu.mplus.response.ResponseResult.FAILED_MESSAGE;

@Api(tags = "角色相关接口")
@Slf4j
@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @ApiOperation("新增角色")
    @PostMapping("/")
    public ResponseResult<String> createRole(CreateRoleDTO roleDTO){
        if(StringUtils.isBlank(roleDTO.getRoleName())){
            return ResponseResult.failAddMessage("角色名称不能为空!");
        }
        if(StringUtils.isBlank(roleDTO.getParentRoleId())){
            return ResponseResult.failAddMessage("父级角色不能为空!");
        }
        int dealNum = roleService.createRole(roleDTO);
        return ResponseResult.successAddData(String.valueOf(dealNum));
    }

    @ApiOperation("删除角色")
    @DeleteMapping("/{roleId}")
    public ResponseResult<String> removeRole(@NotBlank @PathVariable("roleId") String roleId){
        int dealNum = roleService.deleteRole(roleId);
        return ResponseResult.successAddData(String.valueOf(dealNum));
    }

    @ApiOperation("更新角色以及角色相关权限")
    @PutMapping("/")
    public ResponseResult<String> updateRole(UpdateRoleDTO roleDTO){
        int dealNum = roleService.updateRole(roleDTO);
        return ResponseResult.successAddData(String.valueOf(dealNum));
    }

    @ApiOperation("查询角色")
    @GetMapping("/")
    public ResponseResult<IPage<QueryRoleDTO>> queryRole(QueryRoleDTO roleDTO, PageDTO<RoleEntity> pageDTO){
        IPage<QueryRoleDTO> iPage = roleService.qryRoles(roleDTO, pageDTO);
        return ResponseResult.successAddData(iPage);
    }
}
