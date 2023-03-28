package com.upc.oa.controller;

import com.github.pagehelper.PageInfo;
import com.upc.oa.po.Tag;
import com.upc.oa.service.TagService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@CrossOrigin
@RestController
@RequestMapping("/tag")
public class TagController {
    @Resource
    private TagService tagService;
    @RequestMapping("/findAllTags")
    public PageInfo<Tag> findAllTags(int pageNum,int pageSize,Tag tag)
    {
        PageInfo<Tag> tags=null;
        tags=tagService.findAllTags(pageNum,pageSize,tag);
        return tags;
    }
    @RequestMapping("/addTag")
    public int addTag(Tag tag)
    {
        return tagService.addTag(tag);
    }
    @RequestMapping("/deleteTag")
    public int deleteTag(Tag tag)
    {
        return tagService.deleteTag(tag);
    }
    @RequestMapping("/update")
    public int updateTag(Tag tag)
    {
        return tagService.updateTag(tag);
    }
}
