package com.upc.oa.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.upc.oa.mapper.TagMapper;
import com.upc.oa.po.Tag;
import com.upc.oa.po.User;
import com.upc.oa.service.TagService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TagServiceImpl implements TagService {
    @Resource
    private TagMapper tagMapper;
    @Override
    public PageInfo<Tag> findAllTags(int pageNum, int pageSize, Tag tag) {
        PageInfo<Tag> pages= null;
        PageHelper.startPage(pageNum,pageSize);
        List<Tag> users=tagMapper.findAllTags(tag);
        pages=new PageInfo<>(users);
        return pages;
    }

    @Override
    public int addTag(Tag tag) {
        return tagMapper.addTag(tag);
    }

    @Override
    public int deleteTag(Tag tag) {
        return tagMapper.deleteTag(tag);
    }

    @Override
    public int updateTag(Tag tag) {
        return tagMapper.updateTag(tag);
    }
}
