package com.youngzeu.mplus.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.youngzeu.mplus.entity.convert.ConvertEntity;
import com.youngzeu.mplus.entity.convert.ConvertEntityKey;

public interface ConvertDao extends BaseMapper<ConvertEntity> {
    int deleteByPrimaryKey(ConvertEntityKey key);

    int insert(ConvertEntity record);

    int insertSelective(ConvertEntity record);

    ConvertEntity selectByPrimaryKey(ConvertEntityKey key);

    int updateByPrimaryKeySelective(ConvertEntity record);

    int updateByPrimaryKey(ConvertEntity record);
}