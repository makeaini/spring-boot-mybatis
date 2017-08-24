package com.spring.boot.mybatis.controller;

import com.github.pagehelper.PageInfo;
import com.spring.boot.mybatis.entity.User;
import com.spring.boot.mybatis.enums.UserSexEnum;
import com.spring.boot.mybatis.service.UserServiceI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by shining on 2017-08-17.
 */
@RestController
public class UserController {
    @Autowired
    private UserServiceI userServiceI;
    private static final Logger LOG= LoggerFactory.getLogger(UserController.class);

    @GetMapping("/findUserPageList")
    public PageInfo<User> findUserPageList(@RequestParam(defaultValue = "1") Integer page,@RequestParam(defaultValue = "2")Integer size){
        LOG.debug("第一个请求findUserPageList");
        User u = new User();
        PageInfo<User> userPageInfo =  userServiceI.findUserPageList(page,size,u);
        return userPageInfo;
    }
    @GetMapping("/findById")
    public User findById(Integer userId){
        LOG.debug("第二个请求findById");
        User user =  userServiceI.findUser(userId);
        return user;
    }
    @GetMapping("/addUser")
    public String addUser(){
        User user = new User();
        user.setUserSexEnum(UserSexEnum.MAN);
        user.setUserAge(10);
        user.setUserName("adduser1");
        user.setUserPassword("122");
        User user2 = new User();
        user2.setUserSexEnum(UserSexEnum.MAN);
        user2.setUserAge(10);
        user2.setUserName("adduser2");
       userServiceI.insertUser(user,user2);
        return "success";
    }
}
