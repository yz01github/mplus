package com.youngzeu.mplus.service.permission;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.youngzeu.mplus.entity.PermissionEntity;
import com.youngzeu.mplus.pojo.dto.page.PageDTO;
import com.youngzeu.mplus.pojo.dto.perm.PermDTO;
import com.youngzeu.mplus.pojo.dto.role.RoleDTO;
import com.youngzeu.mplus.pojo.vo.perm.CreatePermissionVO;

import java.util.List;

/**
 * description: [权限相关service]
 * title: PermService
 *
 * @author <a href="mailto:yangzhi@asiainfo.com">yangzhi</a>
 * created 2020/7/2
 */
public interface PermService extends IService<PermissionEntity> {

    int createPermission(CreatePermissionVO permVO);

    int deletePermission(String permId);

    int updatePermission(PermDTO permDTO);

    List<RoleDTO> permIsUsing(String permId);

    IPage<PermDTO> qryPermList(PageDTO<PermissionEntity> pageDTO, PermDTO permDTO);
}
