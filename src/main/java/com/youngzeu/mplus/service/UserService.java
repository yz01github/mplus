package com.youngzeu.mplus.service;

import java.util.List;

import com.youngzeu.mplus.entity.user.UserCreateVO;
import com.youngzeu.mplus.entity.user.UserDO;
import com.youngzeu.mplus.entity.user.UserDTO;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface UserService {
	List<UserDO> selectList();
	
	Integer deleteById(Integer id);

	Boolean login(UserDO user);

    boolean createUser(UserDTO user);
}
