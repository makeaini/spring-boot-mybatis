package com.spring.boot.mybatis.controller;

import com.github.pagehelper.PageInfo;
import com.spring.boot.mybatis.entity.User;
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
    @Value("${user.userName}")
    private String name;

    private static final Logger LOG= LoggerFactory.getLogger(UserController.class);
    @GetMapping("/findUserPageList")
    public PageInfo<User> findUserPageList(@RequestParam(defaultValue = "1") Integer page,@RequestParam(defaultValue = "2")Integer size){
        LOG.debug("第一个请求findUserPageList");
        User u = new User();
        u.setUserName(name);
        PageInfo<User> userPageInfo =  userServiceI.findUserPageList(page,size,u);
        return userPageInfo;
    }
    @GetMapping("/findById")
    public User findById(Integer userId){
        LOG.debug("第二个请求findById");
        User user =  userServiceI.findUser(userId);
        return user;
    }
}
