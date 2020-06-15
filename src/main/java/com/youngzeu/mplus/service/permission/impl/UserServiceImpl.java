package com.youngzeu.mplus.service.permission.impl;

import java.util.ArrayList;
import java.util.List;

import com.youngzeu.mplus.entity.user.UserDO;
import com.youngzeu.mplus.entity.user.UserDTO;
import com.youngzeu.mplus.service.permission.UserService;
import com.youngzeu.mplus.util.GeneraIdUtil;
import com.youngzeu.mplus.util.permission.UserUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.youngzeu.mplus.dao.UserDao;
import org.springframework.util.CollectionUtils;

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
	public UserDTO login(UserDO user) {
		//这部分可以看不懂，做的事情就是去数据库查询账号密码是否匹配，返回一个boolean结果
		QueryWrapper<UserDO> wrapper = new QueryWrapper<UserDO>()
				.eq("userAccount", user.getUserAccount())
				.eq("password", user.getPassword());
        List<UserDO> userInfos = userDao.selectList(wrapper);
        if(CollectionUtils.isEmpty(userInfos)){
            return null;
        }
        UserDO userDO = userInfos.get(0);
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(userDO, userDTO);
        return userDTO;
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
		if(UserUtil.userIsExist(userDO)){
			throw new RuntimeException("该用户已存在，请查证账号后重新注册！");
		}
		return userDao.insert(userDO) > 0;
	}

	/**
	 * description: [用户账号是否已存在]
	 * @param 	userAccount	用户账号
	 * @return	boolean		true-已存在
	 * @author <a href="mailto:yangzhi@asiainfo.com">yangzhi</a>
	 * created 2020/6/15
	 */
	@Override
	public boolean validExist(String userAccount) {
		UserDO userDO = new UserDO();
		userDO.setUserAccount(userAccount);
		return UserUtil.userIsExist(userDO);
	}

}
