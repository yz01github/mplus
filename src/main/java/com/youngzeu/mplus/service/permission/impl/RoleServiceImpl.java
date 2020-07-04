package com.youngzeu.mplus.service.permission.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.youngzeu.mplus.constant.RoleCons;
import com.youngzeu.mplus.dao.PermissionDao;
import com.youngzeu.mplus.dao.RoleDao;
import com.youngzeu.mplus.dao.RolePermDao;
import com.youngzeu.mplus.dao.UserDao;
import com.youngzeu.mplus.dao.UserRoleDao;
import com.youngzeu.mplus.entity.PermissionEntity;
import com.youngzeu.mplus.entity.RoleEntity;
import com.youngzeu.mplus.entity.RolePermEntity;
import com.youngzeu.mplus.entity.UserRoleEntity;
import com.youngzeu.mplus.entity.user.UserEntity;
import com.youngzeu.mplus.pojo.dto.page.PageDTO;
import com.youngzeu.mplus.pojo.dto.perm.PermDTO;
import com.youngzeu.mplus.pojo.dto.role.CreateRoleDTO;
import com.youngzeu.mplus.pojo.dto.role.QueryRoleDTO;
import com.youngzeu.mplus.pojo.dto.role.RoleDTO;
import com.youngzeu.mplus.pojo.dto.role.UpdateRoleDTO;
import com.youngzeu.mplus.pojo.dto.user.UserDTO;
import com.youngzeu.mplus.service.permission.RoleService;
import com.youngzeu.mplus.util.GeneraIdUtil;
import com.youngzeu.mplus.util.cached.RedisUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class RoleServiceImpl extends ServiceImpl<RoleDao, RoleEntity> implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private RolePermDao rolePermDao;

    @Autowired
    private UserRoleDao userRoleDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private PermissionDao permDao;

    @Autowired
    private RedisUtil redisUtil;

    /**
     * description: [新增一个Role,并新增其附带的权限(如果有)]
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

     /**
     * description: [逻辑删除某一角色,并附带逻辑删除角色对应的权限,若该角色仍有用户使用,抛错不允许删除]
     * @param   roleId  删除的角色Id
     * @return  int     删除的角色表的条数
     * @author <a href="mailto:learnsoftware@163.com"/>
     * date 2020/6/27 22:38
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public int deleteRole(String roleId) {
        // TODO 查验用户是否拥有删除角色的权限

        // check role is using
        QueryWrapper<UserRoleEntity> wrapperUR = new QueryWrapper<UserRoleEntity>()
                .eq("ROLE_ID", roleId);
        List<UserRoleEntity> urList = userRoleDao.selectList(wrapperUR);
        if(!CollectionUtils.isEmpty(urList)){
            throw new RuntimeException("该角色仍有用户正在使用,无法删除!");
        }

        // delete role_permission
        UpdateWrapper<RolePermEntity> delWrapper = new UpdateWrapper<RolePermEntity>()
                .eq("ROLE_ID", roleId);
        rolePermDao.delete(delWrapper);

        // 再查一次,不为空就抛错,不能用delete的返回条数判断,因为存在角色的关联关系为空的情况
        QueryWrapper<RolePermEntity> wrapperRP = new QueryWrapper<>();
        List<RolePermEntity> rpList = rolePermDao.selectList(wrapperRP);

        if(!CollectionUtils.isEmpty(rpList)){
            // 没删除成功,手动回滚事务,并抛错
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new RuntimeException("删除角色权限关联关系失败,角色编码:[" + roleId + "]");
        }

        UpdateWrapper<RoleEntity> wrapperR = new UpdateWrapper<RoleEntity>()
                .eq("ROLE_ID", roleId);
        return roleDao.delete(wrapperR);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int updateRole(UpdateRoleDTO roleDTO) {
        String roleId = roleDTO.getRoleId();

        if(!CollectionUtils.isEmpty(roleDTO.getNewPerms())){
            updateRolePerms(roleDTO);
        }

        // 更新角色名称等信息
        RoleEntity roleEntity = new RoleEntity();
        BeanUtils.copyProperties(roleDTO, roleEntity);
        return roleDao.update(roleEntity, new UpdateWrapper<RoleEntity>().eq("ROLE_ID",roleId));
    }

    // 更新角色的相关权限
    private void updateRolePerms(UpdateRoleDTO roleDTO) {
        String roleId = roleDTO.getRoleId();
        List<String> newPerms = roleDTO.getNewPerms();

        // Redis缓存角色拥有的权限
        String roleCacheId = GeneraIdUtil.generaRoleCacheId(roleId);
        String rolePermCache = redisUtil.get(roleCacheId); // 缓存的该角色已有所有permIds
        List<String> rolePermIds;// 该角色已有所有permIds

        if(Objects.isNull(rolePermCache)){
            // 缓存没有,数据库查已有的权限ID
            List<RolePermEntity> rolePerms = rolePermDao.selectList(new QueryWrapper<RolePermEntity>().eq("ROLE_ID", roleId));
            rolePermIds = rolePerms.stream().map(rp -> rp.getPermId()).collect(Collectors.toList());
        }else{
            rolePermIds = JSON.parseArray(rolePermCache, String.class);
        }

        // 过滤出需要添加的,没有的才加,已有的就不返回了
        List<String> newPermsFilted = newPerms.stream().filter(p -> !rolePermIds.contains(p))
                .collect(Collectors.toList());
        // 一般这种数据量不会很大,暂不考虑实现IService接口引入批量新增
        newPermsFilted.forEach(permId -> {
            RolePermEntity rolePermEntity = new RolePermEntity();
            rolePermEntity.setPermId(permId);
            rolePermEntity.setRoleId(roleId);
            rolePermDao.insert(rolePermEntity);
        });
        // 完成后新权限加入缓存
        redisUtil.set(roleCacheId, JSON.toJSONString(newPerms));
    }

    @Override
    public IPage<QueryRoleDTO> qryRoles(QueryRoleDTO roleDTO, PageDTO<RoleEntity> pageDTO) {
        // 查询指定级别目录, 第一次默认查超级管理员目录下
        String parentRoleId = roleDTO.getParentRoleId();
        String condParentRoleId = StringUtils.isBlank(parentRoleId) ? RoleCons.SPUER_ROLE : parentRoleId;
        QueryWrapper<RoleEntity> wrapperR = new QueryWrapper<RoleEntity>()
                .eq("PARENT_ROLE_ID", condParentRoleId);
        IPage<RoleEntity> convertBeforeIPageR = roleDao.selectPage(pageDTO, wrapperR);
        // TODO 判空
        IPage<QueryRoleDTO> iPageR = convertBeforeIPageR.convert(re -> {
            QueryRoleDTO queryRoleDTO = new QueryRoleDTO();
            BeanUtils.copyProperties(re, queryRoleDTO);
            return queryRoleDTO;
        });
        List<QueryRoleDTO> roleRecords = iPageR.getRecords();
        if(CollectionUtils.isEmpty(roleRecords)){
            return iPageR;
        }
        putUserByRole(roleRecords);
        putPermByRole(roleRecords);

        return null;
    }

    // 查找并put 角色下的权限用于展示
    private void putPermByRole(List<QueryRoleDTO> roleRecords) {
        List<String> roleIds = roleRecords.stream().map(r -> r.getRoleId()).collect(Collectors.toList());
        QueryWrapper<RolePermEntity> wrapperRP = new QueryWrapper<RolePermEntity>().in("ROLE_ID", roleIds);
        List<RolePermEntity> rpList = rolePermDao.selectList(wrapperRP);
        if(CollectionUtils.isEmpty(rpList)){
            return;
        }

        // 查找所有的User, 转换成<permId, PermDTO> 格式, map.get 比循环比较要快很多,这种做法是考虑效率的写法
        // TODO 使用redis获取,提高效率
        List<String> permIds = rpList.stream().map(rp -> rp.getPermId()).collect(Collectors.toList());
        List<PermissionEntity> permEntityList = permDao.selectList(new QueryWrapper<PermissionEntity>().in("PERM_ID", permIds));
        if(CollectionUtils.isEmpty(permEntityList)){
            return ;
        }
        Map<String, PermDTO> permDTOMap = permEntityList.stream().map(pe -> {
            PermDTO permDTO = new PermDTO();
            BeanUtils.copyProperties(pe, permDTO);
            return permDTO;
        }).collect(Collectors.toMap(o -> o.getPermId(), p -> p));

        // 遍历put每个角色的权限list
        roleRecords.forEach(rr -> {
            List<PermDTO> permList = rr.getPermList();
            rpList.forEach(rp -> {
                if(Objects.equals(rr.getRoleId(), rp.getRoleId())){
                    permList.add(permDTOMap.getOrDefault(rp.getPermId(), new PermDTO()));
                }
            });
        });
    }

    // 查找并put 角色下的用户用于展示
    private void putUserByRole(List<QueryRoleDTO> roleRecords) {
        List<String> roleIds = roleRecords.stream().map(r -> r.getRoleId()).collect(Collectors.toList());
        Wrapper<UserRoleEntity> wrapperUR = new QueryWrapper<UserRoleEntity>().in("ROLE_ID", roleIds);
        List<UserRoleEntity> urList = userRoleDao.selectList(wrapperUR);
        if (CollectionUtils.isEmpty(urList)){
            return;
        }

        // 查找所有的User, 转换成<userId, UserDTO> 格式, map.get 比循环比较要快很多,这种做法是考虑效率的写法
        // TODO 使用redis获取,提高效率
        List<String> userIds = urList.stream().map(ur -> ur.getUserId()).collect(Collectors.toList());
        List<UserEntity> userEntityList = userDao.selectList(new QueryWrapper<UserEntity>().in("USER_ID", userIds));
        if(CollectionUtils.isEmpty(userEntityList)){
            return;
        }
        Map<String, UserDTO> userDTOMap = userEntityList.stream().map(ue -> {
            UserDTO userDTO = new UserDTO();
            BeanUtils.copyProperties(ue, userDTO);
            return userDTO;
        }).collect(Collectors.toMap(o -> o.getUserId(), e -> e));

        // 遍历put每个角色对应的用户list
        roleRecords.forEach(rr -> {
            List<UserDTO> userList = rr.getUserList();
            urList.forEach(ur -> {
                if(Objects.equals(rr.getRoleId(), ur.getRoleId())){
                    userList.add(userDTOMap.getOrDefault(ur.getUserId(), new UserDTO()));
                }
            });
        });
    }

    private List<UserRoleEntity> qryUserRoleByRoleId(String roleId){
        QueryWrapper<UserRoleEntity> wrapperUR = new QueryWrapper<UserRoleEntity>()
                .eq("ROLE_ID", roleId);
        List<UserRoleEntity> urList = userRoleDao.selectList(wrapperUR);
        return urList;
    }
}
