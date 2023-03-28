package com.upc.oa.mapper;

import com.github.pagehelper.PageInfo;
import com.upc.oa.po.Blog;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.jmx.export.annotation.ManagedOperationParameter;
import com.upc.oa.dto.BlogDto;
import com.upc.oa.dto.BtagDto;
import com.upc.oa.dto.CcommentDto;
import com.upc.oa.dto.TagDto;
import com.upc.oa.po.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BlogMapper {
    //个人中心查找所有博客
    List<Blog> findAllBlogs(Blog blog);
    //删除博客
    int deleteBlog(Blog blog);
    //更新博客
    int updateBlog(Blog blog);
    //返回所有博客
    List<Blog> findBlogByParam(String searchParam);
    //返回所有标签
    List<Tag> findAllTag();
    //按标签返回博客
    List<Blog> findBlogByTag(TagDto tagDto);
    //返回博客全部信息
    Blog findById(BlogDto blogDto);
    //返回博客对应标签
    List<Tag> findTagById(BlogDto blogDto);
    //返回博客对应评论
    List<Ccomment> findCtById(BlogDto blogDto);

    //发表博文
    int uploadBlog(BlogDto blogDto);
    //添加标签
    int uploadBlogTag(BtagDto btagDto);
    //编辑博文
    int editBlog(BlogDto blogDto);
    //修改标签
    int deleteBlogTag(BtagDto btagDto);
    //发表评论
    int sendCcomment(CcommentDto ccommentDto);
    //浏览博文
    int viewBlog(BlogDto blogDto);
    //点赞博文
    int praiseBlog(BlogDto blogDto);
    //点赞评论
    int praiseCcomment(CcommentDto ccommentDto);

}
