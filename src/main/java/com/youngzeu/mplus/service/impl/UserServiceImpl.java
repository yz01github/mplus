package com.youngzeu.mplus.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.youngzeu.mplus.dao.UserDao;
import com.youngzeu.mplus.entity.User;
import com.youngzeu.mplus.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	//@Autowired
	private UserDao userDao;
	
	@Override
	public List<User> selectList() {
		List<User> list = userDao.selectList(new QueryWrapper<User>());
		return list;
	}

	@Override
	public Integer deleteById(Integer id) {
		return userDao.delete(new QueryWrapper<User>().eq("id", id));
	}
	
}
