package com.spring.boot.mybatis.mapper.master.provider;

import com.spring.boot.mybatis.entity.User;
import org.apache.ibatis.jdbc.SQL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;



/**
 * Created by shining on 2017-08-16.
 */
public class UserMapperProvider {
    private static final Logger LOG= LoggerFactory.getLogger(UserMapperProvider.class);
    // 动态生成sql
    public String findByName(User user) {
        String userName = (String)user.getUserName();
        Integer age = (Integer)user.getUserAge();
        String sql =  new SQL() {
            {
                SELECT("u.id id,t.id \"teacher.id\",u.user_name,u.user_password,u.user_age,u.user_sex,t.teacher_name \"teacher.teacherName\"");
                FROM("user u");
                INNER_JOIN("teacher t on u.teacher_id = t.id");
                if (!StringUtils.isEmpty(userName)){
                    WHERE("u.user_name LIKE '%" + userName + "%'");
                }
                if (!StringUtils.isEmpty(age)){
                    AND();
                    WHERE("u.user_age =" + age);
                }
            }
        }.toString();
        LOG.debug("sql:{}",sql);
        return sql;


    }
}
