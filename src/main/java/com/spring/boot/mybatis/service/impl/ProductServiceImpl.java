package com.spring.boot.mybatis.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.spring.boot.mybatis.entity.Product;
import com.spring.boot.mybatis.mapper.cluster.ProductMapper;
import com.spring.boot.mybatis.service.ProductServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by shining on 2017-08-23.
 */
@Service
@Transactional
public class ProductServiceImpl implements ProductServiceI {
    @Autowired
    private ProductMapper productMapper;
    @Override
    public PageInfo<Product> findPageList(int page, int size) {
        PageHelper.startPage(page, size);
        List<Product> products = productMapper.findPageList();
        PageInfo<Product> userPageInfo = new PageInfo<Product>(products);
        return userPageInfo;
    }
}
