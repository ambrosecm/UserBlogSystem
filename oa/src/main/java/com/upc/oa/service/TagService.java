package com.upc.oa.service;

import com.github.pagehelper.PageInfo;
import com.upc.oa.dto.UserDto;
import com.upc.oa.po.Tag;

public interface TagService {
    PageInfo<Tag> findAllTags(int pageNum, int pageSize, Tag tag);
    int addTag(Tag tag);
    int deleteTag(Tag tag);
    int updateTag(Tag tag);
}
