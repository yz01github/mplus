package com.youngzeu.mplus.service.impl;

import java.util.List;

import com.youngzeu.mplus.entity.user.UserDO;
import com.youngzeu.mplus.entity.user.UserDTO;
import com.youngzeu.mplus.util.GeneraIdUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.youngzeu.mplus.dao.UserDao;
import com.youngzeu.mplus.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	@Override
	public List<UserDO> selectList() {
		List<UserDO> list = userDao.selectList(new QueryWrapper<UserDO>());
		return list;
	}

	@Override
	public Integer deleteById(Integer id) {
		return userDao.delete(new QueryWrapper<UserDO>().eq("id", id));
	}

	@Override
	public Boolean login(UserDO user) {
		//这部分可以看不懂，做的事情就是去数据库查询账号密码是否匹配，返回一个boolean结果
		QueryWrapper<UserDO> wrapper = new QueryWrapper<UserDO>()
				.eq("user_name", user.getUserName())
				.eq("password", user.getPassword());
		return CollectionUtils.isEmpty(userDao.selectList(wrapper));
	}

	/**
	 * 新增用户
	 * @return
	 */
	@Override
	public boolean createUser(UserDTO userDTO) {
		UserDO userDO = new UserDO();
		BeanUtils.copyProperties(userDTO, userDO);
		userDO.setUserId(GeneraIdUtil.generaUUID());
		return userDao.insert(userDO) > 0;
	}

}
