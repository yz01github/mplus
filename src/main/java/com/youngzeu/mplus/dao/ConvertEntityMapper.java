package com.youngzeu.mplus.dao;

import com.youngzeu.mplus.entity.convert.ConvertEntity;
import com.youngzeu.mplus.entity.convert.ConvertEntityKey;

public interface ConvertEntityMapper {
    int deleteByPrimaryKey(ConvertEntityKey key);

    int insert(ConvertEntity record);

    int insertSelective(ConvertEntity record);

    ConvertEntity selectByPrimaryKey(ConvertEntityKey key);

    int updateByPrimaryKeySelective(ConvertEntity record);

    int updateByPrimaryKey(ConvertEntity record);
}