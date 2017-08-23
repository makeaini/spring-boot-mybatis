package com.spring.boot.mybatis.entity;

import com.spring.boot.mybatis.enums.UserSexEnum;

import java.io.Serializable;
import java.util.List;

/**
 * Created by shining on 2017-08-16.
 */
public class User implements Serializable {
    private Integer id;
    private String userName;
    private Integer userAge;
    private UserSexEnum userSexEnum;
    private String userPassword;
    private Teacher teacher;

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getUserAge() {
        return userAge;
    }

    public void setUserAge(Integer userAge) {
        this.userAge = userAge;
    }

    public UserSexEnum getUserSexEnum() {
        return userSexEnum;
    }

    public void setUserSexEnum(UserSexEnum userSexEnum) {
        this.userSexEnum = userSexEnum;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public Integer getId() {

        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", userAge=" + userAge +
                ", userSexEnum=" + userSexEnum +
                ", userPassword='" + userPassword + '\'' +
                ", teacher=" + teacher +
                '}';
    }
}
