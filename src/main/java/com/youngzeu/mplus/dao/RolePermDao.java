package com.youngzeu.mplus.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.youngzeu.mplus.entity.RolePermEntity;

public interface RolePermDao  extends BaseMapper<RolePermEntity> {
    int deleteByPrimaryKey(Long id);

    int insertSelective(RolePermEntity record);

    RolePermEntity selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(RolePermEntity record);

    int updateByPrimaryKey(RolePermEntity record);
}