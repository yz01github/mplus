package com.youngzeu.mplus.dao;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.youngzeu.mplus.entity.User;

@Mapper
public interface UserDao extends BaseMapper<User>{
}
