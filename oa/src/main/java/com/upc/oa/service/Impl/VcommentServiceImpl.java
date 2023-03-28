package com.upc.oa.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.upc.oa.mapper.VcommentMapper;
import com.upc.oa.po.Vcomment;
import com.upc.oa.service.VcommentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class VcommentServiceImpl implements VcommentService {
    @Resource
    private VcommentMapper vcommentMapper;
    @Override
    public PageInfo<Vcomment> findAllVcomments(int pageNum, int pageSize, Vcomment vcomment) {
        PageInfo<Vcomment> pages=null;
        PageHelper.startPage(pageNum, pageSize);
        List<Vcomment> vcomment1=vcommentMapper.findAllVcomments(vcomment);
        pages=new PageInfo<>(vcomment1);
        return pages;
    }

    @Override
    public int deleteVcomment(Vcomment vcomment) {
        return vcommentMapper.deleteVcomment(vcomment);
    }
}
