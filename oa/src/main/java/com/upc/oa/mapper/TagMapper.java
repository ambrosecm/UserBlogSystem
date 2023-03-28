package com.upc.oa.mapper;

import com.upc.oa.po.Tag;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface TagMapper {
    List<Tag> findAllTags(Tag tag);
    int addTag(Tag tag);
    int deleteTag(Tag tag);
    int updateTag(Tag tag);

}
