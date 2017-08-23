package com.spring.boot.mybatis;

import com.github.pagehelper.PageInfo;
import com.spring.boot.mybatis.entity.User;
import com.spring.boot.mybatis.enums.UserSexEnum;
import com.spring.boot.mybatis.mapper.UserMapper;
import com.spring.boot.mybatis.service.UserServiceI;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootMybatisApplicationTests {
	@Autowired
	private UserServiceI userServiceI;
	private static final Logger LOGGER= LoggerFactory.getLogger(SpringBootMybatisApplicationTests.class);

	@Test
	@Ignore
	public void contextLoads() {
		User user = new User();
		user.setUserAge(1);
		user.setUserName("liuwenzhong");
		user.setUserPassword("password");
		user.setUserSexEnum(UserSexEnum.MAN);
		User user2 = new User();
		user2.setUserAge(1);
		user2.setUserName("liuwenzhong");
		user2.setUserPassword("password");
		user2.setUserSexEnum(UserSexEnum.WOMAN);
		userServiceI.insertUser(user,user2);

		//int id = userMapper.insert(user);
		//System.out.println(id);
	}

	@Test
	//@Ignore
	public void queryUserLists() {
		User user = new User();
		user.setUserName("liu");
		user.setUserAge(1);
		PageInfo<User> userPageInfo = userServiceI.findUserPageList(1,10,user);
		for (User user1: userPageInfo.getList()){
			LOGGER.info("data:{}",user1);
		}
	//	LOGGER.info("data:{}",userPageInfo.getList());
	}

}
