package com.youngzeu.mplus.dao;

import com.youngzeu.mplus.entity.user.UserDO;
import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

@Mapper
public interface UserDao extends BaseMapper<UserDO>{

}
