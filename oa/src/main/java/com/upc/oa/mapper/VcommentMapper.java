package com.upc.oa.mapper;

import com.upc.oa.po.Vcomment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface VcommentMapper {
    List<Vcomment> findAllVcomments(Vcomment vcomment);
    int deleteVcomment(Vcomment vcomment);
}
