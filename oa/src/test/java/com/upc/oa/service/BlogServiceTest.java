package com.upc.oa.service;

import com.github.pagehelper.PageInfo;
import com.upc.oa.po.Blog;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BlogServiceTest {
    @Resource
    BlogService blogService;
    @Test
    void findBlogByParam() {
        PageInfo<Blog> blogs=blogService.findBlogByParam(1,6,"");
        System.out.println(blogs.toString());
    }
}