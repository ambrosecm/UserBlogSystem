package com.upc.oa.controller;

import com.github.pagehelper.PageInfo;
import com.upc.oa.dto.BlogDto;
import com.upc.oa.dto.CcommentDto;
import com.upc.oa.dto.VcommentDto;
import com.upc.oa.dto.VideoDto;
import com.upc.oa.mapper.VideoMapper;
import com.upc.oa.po.*;
import com.upc.oa.service.VideoService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/video")
public class VideoController {
    @Resource
    private VideoService videoService;
    @RequestMapping("/findAllVideos")
    public PageInfo<Video> findAllVideos(int pageNum,int pageSize,Video video)
    {
        return videoService.findAllVideos(pageNum, pageSize, video);
    }
    //根据参数查找视频
    @RequestMapping("/findVideo")
    public List<Video> findVideo(VideoDto videoDto){
        List<Video> videos = null;
        videos = videoService.findVideoByParam(videoDto);
        return videos;
    }

    @RequestMapping("/updateVplayNum")
    public int updateVplayNum(Integer vid){
        int row = 0;
        row = videoService.doUpdateVplayNum(vid);
        return row;
    }
    @RequestMapping("/deleteVideo")
    public int deleteVideo(Video video)
    {
        return videoService.deleteVideo(video);
    }
    @RequestMapping("/findAllTypes")
    public List<Type> findAllTypes(Type type)
    {
        return  videoService.findAllTypes(type);
    }

    @RequestMapping("/addVideo")
    public int addVideo(Video video)
    {
        return videoService.addVideo(video);
    }
    @RequestMapping("/updateVideo")
    public int updateVideo(Video video)
    {
        return videoService.updateVideo(video);
    }

    @RequestMapping("/findVideoById")
    public Video findVideoById(Integer vid){
        Video video = null;
        video = videoService.doFindVideoById(vid);
        return video;
    }
    //按视频id返回评论
    @RequestMapping("/findCtById")
    public PageInfo<Vcomment> findCtById(int pageNum, int pageSize, Video video){
        return videoService.findCtById(pageNum,pageSize,video);
    }
    @RequestMapping("/sendVcomment")
    public int sendCcomment(VcommentDto vcommentDto){
        return videoService.sendVcomment(vcommentDto);
    }
    @RequestMapping("/praiseVcomment")
    public int praiseVcomment(VcommentDto vcommentDto){
        return videoService.praiseVcomment(vcommentDto);
    }
}

