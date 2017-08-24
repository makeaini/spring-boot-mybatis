package com.spring.boot.mybatis.controller;

import com.github.pagehelper.PageInfo;
import com.spring.boot.mybatis.entity.Product;
import com.spring.boot.mybatis.entity.User;
import com.spring.boot.mybatis.service.ProductServiceI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by shining on 2017-08-23.
 */
@RestController
public class ProductController {

    @Autowired
    private ProductServiceI productServiceI;
    private static final Logger LOG= LoggerFactory.getLogger(ProductController.class);
    @GetMapping("productPageList")
    public PageInfo<Product> productPageInfo(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "2")Integer size){
        LOG.debug("第一个请求findUserPageList");
        PageInfo<Product> productPageInfo =  productServiceI.findPageList(page,size);
        return productPageInfo;
    }
}
