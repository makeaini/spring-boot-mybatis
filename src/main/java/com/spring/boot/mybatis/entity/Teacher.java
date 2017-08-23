package com.spring.boot.mybatis.entity;

import org.springframework.util.StringUtils;

import java.io.Serializable;

/**
 * Created by shining on 2017-08-16.
 */
public class Teacher implements Serializable {
    private Integer id;
    private String teacherName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "id=" + id +
                ", teacherName='" + teacherName + '\'' +
                '}';
    }
}
