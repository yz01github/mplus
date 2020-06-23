package com.youngzeu.mplus.pojo.dto.user;

import com.youngzeu.mplus.pojo.dto.role.RoleDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class CreateUserDTO extends UserDTO{

    private List<RoleDTO> roles;
}
