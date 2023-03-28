package com.upc.oa.service.Impl;

import com.github.pagehelper.PageInfo;
import com.upc.oa.dto.TagDto;
import com.upc.oa.mapper.BlogMapper;
import com.upc.oa.po.Blog;
import com.upc.oa.service.BlogService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BlogServiceImplTest {
    @Resource
    private BlogService blogService;

    @Test
    void findBlogByTag() {
        PageInfo<Blog> blogs=null;
        TagDto tagDto=new TagDto();
        tagDto.setTid(1001);
        blogs=blogService.findBlogByTag(1,4,tagDto);
        System.out.println(blogs.toString());

    }
}