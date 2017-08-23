package com.spring.boot.mybatis.mapper;

import com.spring.boot.mybatis.entity.Teacher;
import com.spring.boot.mybatis.entity.User;
import com.spring.boot.mybatis.enums.UserSexEnum;
import com.spring.boot.mybatis.mapper.provider.UserMapperProvider;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by shining on 2017-08-16.
 */
@Mapper
public interface UserMapper {
    @Insert("INSERT INTO user(user_name,user_password,user_sex,user_age) VALUES (#{userName}, #{userPassword},#{userSexEnum},#{userAge}) ")
    @Options(useGeneratedKeys= true, keyProperty="id")
    int insert(User user);
    @SelectProvider(type = UserMapperProvider.class,method = "findByName")
    @Results(value = {
            @Result(property = "userSexEnum",  column = "user_sex", javaType = UserSexEnum.class),
            @Result(property = "userName", column = "user_name"),
            @Result(property = "userAge", column = "user_age"),
            @Result(property = "userPassword", column = "user_password"),
            @Result(property = "teacher.id",column = "teacher.id"),
            @Result(property = "teacher.teacherName",column = "teacher.teacherName"),
    },id = "baseMap")
    List<User> findUserList(User user);

    @Select("select * from user where id=#{id}")
    @ResultMap("baseMap")
    public User findOne(Integer id);


}
