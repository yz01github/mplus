package com.youngzeu.mplus.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.youngzeu.mplus.entity.User;

@Transactional
public interface UserService {
	List<User> selectList();
	
	Integer deleteById(Integer id);
}
