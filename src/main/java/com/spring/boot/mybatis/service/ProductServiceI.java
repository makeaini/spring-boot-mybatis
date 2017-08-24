package com.spring.boot.mybatis.service;

import com.github.pagehelper.PageInfo;
import com.spring.boot.mybatis.entity.Product;

/**
 * Created by shining on 2017-08-23.
 */
public interface ProductServiceI {
    public PageInfo<Product> findPageList(int page, int size);
}
