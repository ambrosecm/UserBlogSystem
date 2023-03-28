package com.upc.oa.service;

import com.github.pagehelper.PageInfo;
import com.upc.oa.dto.BlogDto;
import com.upc.oa.dto.CcommentDto;
import com.upc.oa.dto.TagDto;
import com.upc.oa.po.Blog;
import com.upc.oa.po.Ccomment;
import com.upc.oa.po.Tag;
import com.upc.oa.po.Blog;
import com.upc.oa.po.Tag;
import org.springframework.stereotype.Service;
import java.util.List;

public interface BlogService {

    PageInfo<Blog> findBlogByParam(int pageNum, int pageSize,String searchParam);
    List<Tag> findAllTag();
    PageInfo<Blog> findBlogByTag(int pageNum, int pageSize, TagDto tagDto);

    Blog findById(BlogDto blogDto);
    List<Tag> findTagById(BlogDto blogDto);
    PageInfo<Ccomment> findCtById(int pageNum, int pageSize,BlogDto blogDto);

    int uploadBlog(BlogDto blogDto);

    int editBlog(BlogDto blogDto);

    int sendCcomment(CcommentDto ccommentDto);

    int viewBlog(BlogDto blogDto);

    int praiseBlog(BlogDto blogDto);

    int praiseCcomment(CcommentDto ccommentDto);

    PageInfo<Blog> findAllBlogs(int pageNum, int pageSize, Blog blog);
    int deleteBlog(Blog blog);
    int updateBlog(Blog blog);

}
