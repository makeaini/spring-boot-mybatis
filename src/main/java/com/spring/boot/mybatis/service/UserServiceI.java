package com.spring.boot.mybatis.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.spring.boot.mybatis.entity.User;

/**
 * Created by shining on 2017-08-16.
 */
public interface UserServiceI {
    public PageInfo<User> findUserPageList(int page, int size,User user);

    public void insertUser(User user,User user2);

    public User findUser(Integer userId);
}
