package com.youngzeu.mplus.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.youngzeu.mplus.entity.RoleEntity;

public interface RoleDao extends BaseMapper<RoleEntity> {
    int deleteByPrimaryKey(Long id);

    int insertSelective(RoleEntity record);

    RoleEntity selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(RoleEntity record);

    int updateByPrimaryKey(RoleEntity record);
}