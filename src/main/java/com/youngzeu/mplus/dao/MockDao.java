package com.youngzeu.mplus.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.youngzeu.mplus.entity.mock.MockEntity;
import com.youngzeu.mplus.entity.mock.MockEntityKey;

public interface MockDao  extends BaseMapper<MockEntity> {
    int deleteByPrimaryKey(MockEntityKey key);

    int insert(MockEntity record);

    int insertSelective(MockEntity record);

    MockEntity selectByPrimaryKey(MockEntityKey key);

    int updateByPrimaryKeySelective(MockEntity record);

    int updateByPrimaryKey(MockEntity record);
}