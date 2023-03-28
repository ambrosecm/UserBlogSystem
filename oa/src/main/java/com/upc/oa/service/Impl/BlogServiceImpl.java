package com.upc.oa.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.upc.oa.dto.BlogDto;
import com.upc.oa.dto.BtagDto;
import com.upc.oa.dto.CcommentDto;
import com.upc.oa.dto.TagDto;
import com.upc.oa.mapper.BlogMapper;
import com.upc.oa.po.Blog;
import com.upc.oa.po.Ccomment;
import com.upc.oa.po.Tag;
import com.upc.oa.service.BlogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class BlogServiceImpl implements BlogService {
    @Resource
    private BlogMapper blogMapper;


    @Override
    public PageInfo<Blog> findBlogByParam(int pageNum, int pageSize,String searchParam) {
        List<Blog> blogs=null;
        PageHelper.startPage(pageNum,pageSize);
        blogs=blogMapper.findBlogByParam(searchParam);
        PageInfo<Blog> page=null;
        page=new PageInfo<>(blogs,4);
        return page;
    }

    @Override
    public List<Tag> findAllTag() {
        return blogMapper.findAllTag();
    }

    @Override
    public PageInfo<Blog> findBlogByTag(int pageNum, int pageSize, TagDto tagDto) {
        List<Blog> blogs=null;
        PageHelper.startPage(pageNum,pageSize);
        blogs=blogMapper.findBlogByTag(tagDto);
        PageInfo<Blog> page=null;
        page=new PageInfo<>(blogs,4);
        return page;
    }

    @Override
    public Blog findById(BlogDto blogDto) {
        return blogMapper.findById(blogDto);
    }

    @Override
    public List<Tag> findTagById(BlogDto blogDto) {
        return blogMapper.findTagById(blogDto);
    }

    @Override
    public PageInfo<Ccomment> findCtById(int pageNum, int pageSize,BlogDto blogDto) {
        List<Ccomment> ccts=null;
        PageHelper.startPage(pageNum,pageSize);
        ccts=blogMapper.findCtById(blogDto);
        PageInfo<Ccomment> page=null;
        page=new PageInfo<>(ccts,4);
        return page;
    }

    @Override
    public int uploadBlog(BlogDto blogDto) {
        int ub=blogMapper.uploadBlog(blogDto);
        int ubt=0;
        Integer[] tags=blogDto.getTags();
        if(tags!=null){
            for(int i=0;i<tags.length;i++){
                BtagDto btagDto=new BtagDto();
                btagDto.setBid(blogDto.getBid());
                btagDto.setTid(tags[i]);
                ubt=blogMapper.uploadBlogTag(btagDto);
                if (ubt==0){return 0;}
            }
        }
        if(ub!=0){return ub;}else{return 0;}
    }

    @Override
    public int editBlog(BlogDto blogDto) {
        int eb=blogMapper.editBlog(blogDto);
        Integer[] tags=blogDto.getTags();
        if(tags!=null){
            BtagDto btagDto=new BtagDto();
            btagDto.setBid(blogDto.getBid());
            int dbt=blogMapper.deleteBlogTag(btagDto);
            if(dbt==0){return 0;}
            for(int i=0;i<tags.length;i++){
                btagDto.setTid(tags[i]);
                int ubt=blogMapper.uploadBlogTag(btagDto);
                if (ubt==0){return 0;}
            }
        }
        if(eb!=0){return eb;}else{return 0;}
    }

    @Override
    public int sendCcomment(CcommentDto ccommentDto) {
        return blogMapper.sendCcomment(ccommentDto);
    }



    @Override
    public int viewBlog(BlogDto blogDto) {
        return blogMapper.viewBlog(blogDto);
    }

    @Override
    public int praiseBlog(BlogDto blogDto) {
        return blogMapper.praiseBlog(blogDto);
    }

    @Override
    public int praiseCcomment(CcommentDto ccommentDto) {
        return blogMapper.praiseCcomment(ccommentDto);
    }
    //找所有
    @Override
    public PageInfo<Blog> findAllBlogs(int pageNum, int pageSize, Blog blog) {
        PageInfo<Blog> pages= null;
        PageHelper.startPage(pageNum,pageSize);
        List<Blog> users=blogMapper.findAllBlogs(blog);
        pages=new PageInfo<>(users);
        return pages;
    }
    //删
    @Override
    public int deleteBlog(Blog blog) {
        return blogMapper.deleteBlog(blog);
    }
    //改
    @Override
    public int updateBlog(Blog blog) {
        return blogMapper.updateBlog(blog);
    }

}
