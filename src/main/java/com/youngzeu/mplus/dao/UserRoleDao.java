package com.youngzeu.mplus.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.youngzeu.mplus.entity.UserRoleEntity;

public interface UserRoleDao  extends BaseMapper<UserRoleEntity> {
    int deleteByPrimaryKey(Long id);

    int insert(UserRoleEntity record);

    int insertSelective(UserRoleEntity record);

    UserRoleEntity selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserRoleEntity record);

    int updateByPrimaryKey(UserRoleEntity record);
}