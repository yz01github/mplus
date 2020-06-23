package com.youngzeu.mplus.service.permission;

import java.util.List;

import com.youngzeu.mplus.entity.user.UserDTO;
import com.youngzeu.mplus.entity.user.UserEntity;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface UserService {
	List<UserEntity> selectList();
	
	Integer deleteById(String id);

    UserDTO login(UserEntity user);

    boolean createUser(UserDTO user);

	boolean validExist(String userAccount);
}
