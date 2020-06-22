package com.youngzeu.mplus.dao;

import com.youngzeu.mplus.entity.user.UserEntity;
import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

@Mapper
public interface UserDao extends BaseMapper<UserEntity>{

}
