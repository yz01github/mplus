package com.youngzeu.mplus.service.permission.impl;

import java.util.List;

import com.youngzeu.mplus.pojo.dto.user.UserDTO;
import com.youngzeu.mplus.entity.user.UserEntity;
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
	public List<UserEntity> selectList() {
		List<UserEntity> list = userDao.selectList(new QueryWrapper<UserEntity>());
		return list;
	}

	@Override
	public Integer deleteById(String id) {
		return userDao.delete(new QueryWrapper<UserEntity>().eq("USER_ID", id));
	}

	@Override
	public UserDTO login(UserEntity user) {
		//这部分可以看不懂，做的事情就是去数据库查询账号密码是否匹配，返回一个boolean结果
		QueryWrapper<UserEntity> wrapper = new QueryWrapper<UserEntity>()
				.eq("userAccount", user.getUserAccount())
				.eq("password", user.getPassword());
        List<UserEntity> userInfos = userDao.selectList(wrapper);
        if(CollectionUtils.isEmpty(userInfos)){
            return null;
        }
		UserEntity userDO = userInfos.get(0);
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
		UserEntity userEntity = new UserEntity();
		BeanUtils.copyProperties(userDTO, userEntity);
		userEntity.setUserId(GeneraIdUtil.generaUUID());
		if(UserUtil.userIsExist(userEntity)){
			throw new RuntimeException("该用户已存在，请查证账号后重新注册！");
		}
		return userDao.insert(userEntity) > 0;
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
		UserEntity userEntity = new UserEntity();
		userEntity.setUserAccount(userAccount);
		return UserUtil.userIsExist(userEntity);
	}

}
