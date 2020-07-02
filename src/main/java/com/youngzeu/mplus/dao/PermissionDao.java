package com.youngzeu.mplus.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.youngzeu.mplus.entity.PermissionEntity;

public interface PermissionDao  extends BaseMapper<PermissionEntity> {
    int deleteByPrimaryKey(Long id);

    int insert(PermissionEntity record);

    int insertSelective(PermissionEntity record);

    PermissionEntity selectByPrimaryKey(Long id);

    int updateByPrimaryKeyWithBLOBs(PermissionEntity record);

    int updateByPrimaryKey(PermissionEntity record);
}