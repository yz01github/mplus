package com.youngzeu.mplus.service.permission.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.youngzeu.mplus.dao.RoleDao;
import com.youngzeu.mplus.dao.RolePermDao;
import com.youngzeu.mplus.entity.RoleEntity;
import com.youngzeu.mplus.entity.RolePermEntity;
import com.youngzeu.mplus.pojo.dto.perm.PermDTO;
import com.youngzeu.mplus.pojo.dto.role.CreateRoleDTO;
import com.youngzeu.mplus.pojo.dto.role.RoleDTO;
import com.youngzeu.mplus.service.permission.RoleService;
import com.youngzeu.mplus.util.GeneraIdUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private RolePermDao rolePermDao;

    /**
     * description: [新增一个Role]
     * @param roleDTO   新增数据
     * @return int      insert Role表的条数
     * @author <a href="mailto:learnsoftware@163.com"/>
     * date 2020/6/23 23:54
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public int createRole(CreateRoleDTO roleDTO) {
        // insert role
        String roleId = GeneraIdUtil.generaUUID();
        RoleEntity roleEntity = new RoleEntity();
        BeanUtils.copyProperties(roleDTO, roleEntity);
        roleEntity.setRoleId(roleId);
        int insertNum = roleDao.insert(roleEntity);

        List<PermDTO> permList = roleDTO.getPermList();
        if(CollectionUtils.isEmpty(permList)){
            return insertNum;
        }

        // insert role_permission
        // build insert data
        List<RolePermEntity> RolePermList = permList.stream()
                .map(p -> {
                    RolePermEntity rolePermEntity = new RolePermEntity();
                    rolePermEntity.setRoleId(roleId);
                    rolePermEntity.setPermId(p.getPermId());
                    return rolePermEntity;
                }).collect(Collectors.toList());
        // insert action
        RolePermList.forEach(rpEntity -> {
            rolePermDao.insert(rpEntity);
        });
        return insertNum;
    }

    @Override
    public int deleteRole(RoleDTO roleDTO) {
        return 0;
    }

    @Override
    public int updateRole(RoleDTO roleDTO) {
        return 0;
    }

    @Override
    public List<RoleDTO> qryRoles(RoleDTO roleDTO, Page<RoleDTO> page) {
        return null;
    }
}
