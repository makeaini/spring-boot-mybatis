package com.spring.boot.mybatis.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.spring.boot.mybatis.entity.User;
import com.spring.boot.mybatis.mapper.master.UserMapper;
import com.spring.boot.mybatis.service.UserServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by shining on 2017-08-16.
 */
@Service
public class UserServiceImpl implements UserServiceI {
    @Autowired
    private UserMapper userMapper;

    @Override
    public PageInfo<User> findUserPageList(int page, int size, User user) {
        PageHelper.startPage(page, size);
        List<User> users = userMapper.findUserList(user);
        PageInfo<User> userPageInfo = new PageInfo<User>(users);
        return userPageInfo;
    }
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void insertUser(User user, User user2) {
        userMapper.insert(user);
        userMapper.insert(user2);

    }

    @Override
    public User findUser(Integer userId) {

        return userMapper.findOne(userId);
    }
}
