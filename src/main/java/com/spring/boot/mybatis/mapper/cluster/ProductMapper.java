package com.spring.boot.mybatis.mapper.cluster;

import com.spring.boot.mybatis.entity.Product;
import com.spring.boot.mybatis.entity.User;
import com.spring.boot.mybatis.enums.UserSexEnum;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by shining on 2017-08-23.
 */
@Mapper
public interface ProductMapper {
    @Select("select * from product")
    @Results(value = {
            @Result(property = "id",  column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "price", column = "price")
    },id = "productResultMap")
    List<Product> findPageList();
}
