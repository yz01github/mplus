package com.youngzeu.mpls;

import java.util.List;

import org.apache.poi.sl.usermodel.ObjectMetaData.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.youngzeu.mplus.dao.UserDao;
import com.youngzeu.mplus.entity.User;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@Slf4j
public class MplusApplicationTests {

	@Autowired
	private UserDao userDao;
	
	@Test
	public void contextLoads() {
	}
	
	@Test
	public void userDaoTest() {
		log.info("userDaoTest start ...");
		Wrapper<User> wrapper = new QueryWrapper<>();
		List<User> list = userDao.selectList(wrapper);
		list.forEach(o -> System.out.println(o));
	}
	
}
