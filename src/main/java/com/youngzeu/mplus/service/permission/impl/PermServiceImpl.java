package com.youngzeu.mplus.service.permission.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.youngzeu.mplus.dao.PermissionDao;
import com.youngzeu.mplus.dao.RoleDao;
import com.youngzeu.mplus.dao.RolePermDao;
import com.youngzeu.mplus.entity.PermissionEntity;
import com.youngzeu.mplus.entity.RoleEntity;
import com.youngzeu.mplus.entity.RolePermEntity;
import com.youngzeu.mplus.pojo.dto.perm.PermDTO;
import com.youngzeu.mplus.pojo.dto.role.RoleDTO;
import com.youngzeu.mplus.pojo.vo.perm.CreatePermissionVO;
import com.youngzeu.mplus.service.permission.PermService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * description: []
 * title: PermServiceImpl
 *
 * @author <a href="mailto:yangzhi@asiainfo.com">yangzhi</a>
 * created 2020/7/2
 */
@Service
public class PermServiceImpl extends ServiceImpl<PermissionDao, PermissionEntity> implements PermService {

    @Autowired
    private PermissionDao permDao;

    @Autowired
    private RolePermDao rolePermDao;

    @Autowired
    private RoleDao roleDao;

    @Override
    public int createPermission(CreatePermissionVO permVO) {
        PermissionEntity permEntity = new PermissionEntity();
        BeanUtils.copyProperties(permVO, permEntity);
        return permDao.insert(permEntity);
    }

    @Override
    public int deletePermission(String permId) {
        // 是否有角色仍在使用该权限
        if(!CollectionUtils.isEmpty(qryPerm(permId))){
            throw new RuntimeException("该权限仍有角色正在使用,无法删除");
        }
        return permDao.delete(new UpdateWrapper<PermissionEntity>().eq("PERM_ID", permId));
    }

    /**
     * description: [更新角色]
     * @param
     * @return
     * @author <a href="mailto:yangzhi@asiainfo.com">yangzhi</a>
     * created 2020/7/2
     */
    @Override
    public int updatePermission(PermDTO permDTO) {
        PermissionEntity permissionEntity = new PermissionEntity();
        BeanUtils.copyProperties(permDTO, permissionEntity);
        UpdateWrapper<PermissionEntity> wrapper = new UpdateWrapper<PermissionEntity>().eq("PERM_ID", permDTO.getPermId());
        return permDao.update(permissionEntity, wrapper);
    }

    /**
     * description: [do something]
     * @param   permId  权限id
     * @return  权限对应的角色id
     * @author <a href="mailto:yangzhi@asiainfo.com">yangzhi</a>
     * created 2020/7/2
     */
    @Override
    public List<RoleDTO> permIsUsing(String permId) {
        List<RolePermEntity> rpList = qryPerm(permId);
        if(CollectionUtils.isEmpty(rpList)){
            return Collections.emptyList();
        }
        // 查询对应的角色信息
        List<String> roleIds = rpList.stream().map(rp -> rp.getRoleId()).collect(Collectors.toList());
        List<RoleEntity> reList = roleDao.selectList(new QueryWrapper<RoleEntity>().in("ROLE_ID", roleIds));
        if(CollectionUtils.isEmpty(reList)){
            return Collections.emptyList();
        }

        // 封装返回
        List<RoleDTO> roleDTOs = reList.stream().map(re -> {
            RoleDTO roleDTO = new RoleDTO();
            BeanUtils.copyProperties(re, roleDTO);
            return roleDTO;
        }).collect(Collectors.toList());
        return roleDTOs;
    }

    public List<RolePermEntity> qryPerm(String permId) {
        // 查权限对应的角色id
        return rolePermDao.selectList(new QueryWrapper<RolePermEntity>().eq("PERM_ID", permId));
    }
}
