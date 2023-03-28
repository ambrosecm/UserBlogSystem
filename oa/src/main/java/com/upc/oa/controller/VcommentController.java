package com.upc.oa.controller;

import com.github.pagehelper.PageInfo;
import com.upc.oa.po.Vcomment;
import com.upc.oa.service.VcommentService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.annotation.Resources;

@CrossOrigin
@RestController
@RequestMapping("/vcomment")
public class VcommentController {
    @Resource
    private VcommentService vcommentService;
    @RequestMapping("/findAllVcomments")
    public PageInfo<Vcomment> findAllVcomments(int pageNum,int pageSize,Vcomment vcomment)
    {
        PageInfo<Vcomment> pages=null;
        pages=vcommentService.findAllVcomments(pageNum, pageSize, vcomment);
        return pages;
    }
    @RequestMapping("/deleteVcomment")
    public int deleteVcomment(Vcomment vcomment)
    {
        return vcommentService.deleteVcomment(vcomment);
    }
}
