package com.upc.oa.mapper;

import com.upc.oa.po.Ccomment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CcommentMapper {
    List<Ccomment> findAllCcomentes(Ccomment ccomment);
    int delelteCcomente(Ccomment ccomment);
}
