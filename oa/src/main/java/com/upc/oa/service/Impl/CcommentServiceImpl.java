package com.upc.oa.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.upc.oa.mapper.CcommentMapper;
import com.upc.oa.po.Ccomment;
import com.upc.oa.service.CcommentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service
public class CcommentServiceImpl implements CcommentService {
    @Resource
    private CcommentMapper ccommentMapper;
    @Override
    public PageInfo<Ccomment> findAllCcomments(int pageNum, int pageSize, Ccomment ccomment) {
        PageInfo<Ccomment> pages=null;
        PageHelper.startPage(pageNum,pageSize);
        List<Ccomment> ccom=ccommentMapper.findAllCcomentes(ccomment);
        pages=new PageInfo<>(ccom);
        return pages;
    }

    @Override
    public int deleteCcomment(Ccomment ccomment) {
        return ccommentMapper.delelteCcomente(ccomment);
    }
}
