package com.youngzeu.mplus.pojo.vo.perm;

import com.youngzeu.mplus.pojo.dto.perm.PermDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * description: []
 * title: CreatePermissionVO
 *
 * @author <a href="mailto:yangzhi@asiainfo.com">yangzhi</a>
 * created 2020/7/2
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class CreatePermissionVO extends PermDTO {

    // TODO 后期加上新建权限时同时指定部分角色( 只能指定自己权限内的角色)
    private String roleIds;
}
