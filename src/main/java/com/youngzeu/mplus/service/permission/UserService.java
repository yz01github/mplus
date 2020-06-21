package com.youngzeu.mplus.service.permission;

import java.util.List;

import com.youngzeu.mplus.entity.user.UserDO;
import com.youngzeu.mplus.entity.user.UserDTO;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface UserService {
	List<UserDO> selectList();
	
	Integer deleteById(String id);

    UserDTO login(UserDO user);

    boolean createUser(UserDTO user);

	boolean validExist(String userAccount);
}
