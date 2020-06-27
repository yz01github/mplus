package com.youngzeu.mplus.pojo.dto.role;

import com.youngzeu.mplus.pojo.dto.perm.PermDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class UpdateRoleDTO extends RoleDTO {

    private List<String> newPerms;
}
