package com.upc.oa.service;

import com.github.pagehelper.PageInfo;
import com.upc.oa.po.Vcomment;
import org.springframework.stereotype.Service;


public interface VcommentService {
    PageInfo<Vcomment> findAllVcomments(int pageNum, int pageSize, Vcomment vcomment);
    int deleteVcomment(Vcomment vcomment);
}
