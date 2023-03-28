package com.upc.oa.service;

import com.github.pagehelper.PageInfo;
import com.upc.oa.po.Ccomment;
import com.upc.oa.po.Tag;

public interface CcommentService {
    PageInfo<Ccomment> findAllCcomments(int pageNum, int pageSize, Ccomment ccomment);
    int deleteCcomment(Ccomment ccomment);
}
