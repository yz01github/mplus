package com.youngzeu.mplus.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.youngzeu.mplus.entity.PermissionEntity;
import com.youngzeu.mplus.entity.RoleEntity;
import com.youngzeu.mplus.pojo.dto.page.PageDTO;
import com.youngzeu.mplus.pojo.dto.perm.PermDTO;
import com.youngzeu.mplus.pojo.dto.role.CreateRoleDTO;
import com.youngzeu.mplus.pojo.dto.role.QueryRoleDTO;
import com.youngzeu.mplus.pojo.dto.role.UpdateRoleDTO;
import com.youngzeu.mplus.pojo.vo.perm.CreatePermissionVO;
import com.youngzeu.mplus.response.ResponseResult;
import com.youngzeu.mplus.service.permission.PermService;
import com.youngzeu.mplus.service.permission.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;

@Api(tags = "角色相关接口")
@Slf4j
@RestController
@RequestMapping("/perm")
public class PermissionController {

    @Autowired
    private PermService permService;

    @ApiOperation("新增权限")
    @PostMapping("/")
    public ResponseResult<String> createRole(@RequestBody CreatePermissionVO permVO){
        int dealNum = permService.createPermission(permVO);
        return ResponseResult.successAddData(String.valueOf(dealNum));
    }

    @ApiOperation("删除角色")
    @DeleteMapping("/{roleId}")
    public ResponseResult<String> removeRole(@NotBlank @PathVariable("roleId") String permId){
        int dealNum = permService.deletePermission(permId);
        return ResponseResult.successAddData(String.valueOf(dealNum));
    }

    @ApiOperation("更新权限")
    @PutMapping("/")
    public ResponseResult<String> updateRole(@RequestBody PermDTO permDTO){
        int dealNum = permService.updatePermission(permDTO);
        return ResponseResult.successAddData(String.valueOf(dealNum));
    }

    /*@ApiImplicitParams({
            @ApiImplicitParam(name = "roleDTO", value = "条件参数", paramType = "query", required = false),
            @ApiImplicitParam(name = "pageDTO", value = "分页参数", paramType = "query", required = false)
    })
    // 上面这个注解使用可以结合@RequestParam 用于get请求,并取消必填入参
    // @ModelAttribute 这个注解可以替代@RequestParam ,get请求用实体类接收参数时,用这个方便测试,默认非必填
    */
    @ApiOperation("查询权限")
    @GetMapping("/")
    public ResponseResult<IPage<PermDTO>> queryRole(@ModelAttribute PermDTO permDTO,
                                                    @ModelAttribute PageDTO<PermissionEntity> pageDTO){
        IPage<PermDTO> iPage = permService.qryPermList(pageDTO, permDTO);
        return ResponseResult.successAddData(iPage);
    }
}
