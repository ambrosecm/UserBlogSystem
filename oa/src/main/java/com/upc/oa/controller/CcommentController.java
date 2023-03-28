package com.upc.oa.controller;

import com.github.pagehelper.PageInfo;
import com.upc.oa.po.Ccomment;
import com.upc.oa.service.CcommentService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@CrossOrigin
@RestController
@RequestMapping("/Ccomment")
public class CcommentController {
    @Resource
    private CcommentService ccommentService;
    @RequestMapping("/findAllCcomments")
    public PageInfo<Ccomment> findAllCcomments(int pageNum, int pageSize,Ccomment ccomment)
    {
        PageInfo<Ccomment> Ccom=null;
        Ccom=ccommentService.findAllCcomments(pageNum, pageSize, ccomment);
        return Ccom;
    }
    @RequestMapping("/deleteCcomment")
    public int deleteCcomment(Ccomment ccomment)
    {
        return ccommentService.deleteCcomment(ccomment);
    }
}
