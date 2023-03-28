package com.upc.oa.mapper;

import com.upc.oa.dto.BlogDto;
import com.upc.oa.po.Blog;
import com.upc.oa.po.Ccomment;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BlogMapperTest {
    @Resource
    BlogMapper blogMapper;
    @Test
    void findBlogByParam() {
        List<Blog> blogs=blogMapper.findBlogByParam("python");
        System.out.println(blogs.get(0).getBlogtags().toString());
    }

    @Test
    void findCtById() {
        BlogDto blogDto =new BlogDto();
        blogDto.setBid(121);
        List<Ccomment> ccomments=blogMapper.findCtById(blogDto);
        for (int i=0;i<ccomments.size();i++) {
            System.out.println(ccomments.get(i).getChildCcomments().toString());
        }

    }
}