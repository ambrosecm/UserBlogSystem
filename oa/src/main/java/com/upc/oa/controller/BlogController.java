package com.upc.oa.controller;

import com.github.pagehelper.PageInfo;
import com.upc.oa.dto.BlogDto;
import com.upc.oa.dto.CcommentDto;
import com.upc.oa.dto.TagDto;
import com.upc.oa.po.Blog;
import com.upc.oa.po.Ccomment;
import com.upc.oa.po.Tag;
import com.upc.oa.service.BlogService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/blog")
public class BlogController {
    @Resource
    private BlogService blogService;
    //查找所有博客
    @RequestMapping("/findAllBlogs")
    public PageInfo<Blog> findAllBlogs(int pageNum, int pageSize, Blog blog)
    {
        PageInfo<Blog> blog1=null;
        blog1=blogService.findAllBlogs(pageNum,pageSize,blog);
        return blog1;
    }
    //删除博客
    @RequestMapping("/deleteBlog")
    public int deleteBlog(Blog blog)
    {
        return blogService.deleteBlog(blog);
    }
    //修改博客
    @RequestMapping("/updateBlog")
    public int updateBlog(Blog blog)
    {
        return blogService.updateBlog(blog);
    }

    //按参数返回博文
    @RequestMapping("/findBlogByParam")
    public PageInfo<Blog> findBlogByParam(int pageNum, int pageSize, String searchParam){
        return blogService.findBlogByParam(pageNum,pageSize,searchParam);
    }
    //返回所有标签
    @RequestMapping("/findAllTag")
    public List<Tag> findAllTag(){
        return blogService.findAllTag();
    }
    //按标签返回博文
    @RequestMapping("/findBlogByTag")
    public PageInfo<Blog> findBlogByTag(int pageNum, int pageSize, TagDto tagDto){
        return blogService.findBlogByTag(pageNum,pageSize,tagDto);
    }
    //按博文id返回博文
    @RequestMapping("/findById")
    public Blog findById(BlogDto blogDto){
        return blogService.findById(blogDto);
    }
    //按博文id返回标签
    @RequestMapping("/findTagById")
    public List<Tag> findTagById(BlogDto blogDto){
        return blogService.findTagById(blogDto);
    }
    //按博文id返回评论
    @RequestMapping("/findCtById")
    public PageInfo<Ccomment> findCtById(int pageNum, int pageSize,BlogDto blogDto){
        return blogService.findCtById(pageNum,pageSize,blogDto);
    }
    @RequestMapping("/sendCcomment")
    public int sendCcomment(CcommentDto ccommentDto){
        return blogService.sendCcomment(ccommentDto);
    }
    @RequestMapping("/uploadBlog")
    public int uploadBlog(BlogDto blogDto){
        return blogService.uploadBlog(blogDto);
    }
    @RequestMapping("/editBlog")
    public int editBlog(BlogDto blogDto){
        return blogService.editBlog(blogDto);
    }
    @RequestMapping("/viewBlog")
    public int viewBlog(BlogDto blogDto){
        return blogService.viewBlog(blogDto);
    }
    @RequestMapping("/praiseBlog")
    public int praiseBlog(BlogDto blogDto){
        return blogService.praiseBlog(blogDto);
    }
    @RequestMapping("/praiseCcomment")
    public int praiseCcomment(CcommentDto ccommentDto){
        return blogService.praiseCcomment(ccommentDto);
    }



}
