package com.youngzeu.mplus.dao;

import com.youngzeu.mplus.entity.Mock.MockEntity;
import com.youngzeu.mplus.entity.Mock.MockEntityKey;

public interface MockEntityMapper {
    int deleteByPrimaryKey(MockEntityKey key);

    int insert(MockEntity record);

    int insertSelective(MockEntity record);

    MockEntity selectByPrimaryKey(MockEntityKey key);

    int updateByPrimaryKeySelective(MockEntity record);

    int updateByPrimaryKey(MockEntity record);
}