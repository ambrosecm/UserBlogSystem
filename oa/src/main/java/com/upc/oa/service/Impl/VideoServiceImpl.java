package com.upc.oa.service.Impl;
import com.upc.oa.dto.VcommentDto;
import com.upc.oa.dto.VideoDto;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.upc.oa.mapper.VideoMapper;
import com.upc.oa.po.*;
import com.upc.oa.service.VideoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service
public class VideoServiceImpl implements VideoService {
    @Resource
    private VideoMapper videoMapper;
    @Override
    public List<Video> findVideoByParam(VideoDto videoDto) {
        List<Video> videos = null;
        videos= videoMapper.findVideoByParam(videoDto);
        return videos;
    }
    @Override
    public PageInfo<Video> findAllVideos(int pageNum, int pageSize, Video video) {
        PageInfo<Video> pages=null;
        PageHelper.startPage(pageNum, pageSize);
        List<Video> videos=videoMapper.findAllVideos(video);
        pages=new PageInfo<>(videos);
        return pages;
    }

    @Override
    public PageInfo<Vcomment> findCtById(int pageNum, int pageSize, Video video) {
        List<Vcomment> vcts=null;
        PageHelper.startPage(pageNum,pageSize);
        vcts=videoMapper.findCtById(video);
        PageInfo<Vcomment> page=null;
        page=new PageInfo<>(vcts,4);
        return page;
    }

    @Override
    public int praiseVcomment(VcommentDto vcommentDto) {
        return videoMapper.praiseVcomment(vcommentDto);
    }

    @Override
    public int sendVcomment(VcommentDto vcommentDto) {
        return videoMapper.sendVcomment(vcommentDto);
    }

    @Override
    public int doUpdateVplayNum(Integer vid) {
        int row = 0;
        row = videoMapper.updateVplayNum(vid);
        return row;
    }

    @Override
    public int deleteVideo(Video video) {
        return videoMapper.deleteVideo(video);
    }

    @Override
    public List<Type> findAllTypes(Type type) {
        return videoMapper.findAllType(type);
    }

    @Override
    public int addVideo(Video video) {
        return videoMapper.addVideo(video);
    }
    @Override
    public int updateVideo(Video video) {
        System.out.println(video);
        return videoMapper.updateVideo(video);
    }


    @Override
    public Video doFindVideoById(Integer vid) {
        Video video = null;
        video = videoMapper.findVideoById(vid);
        return video;
    }

    @Override
    public int doFindColVideoNum(Integer vid) {
        return videoMapper.findColVideoNum(vid);
    }

    @Override
    public int doAddPraise(Integer vid) {
        return videoMapper.addPraise(vid);
    }

    @Override
    public int doSubPraise(Integer vid) {
        return videoMapper.subPraise(vid);
    }

    @Override
    public int doSaveCol(Collectvideo collectvideo) {
        return videoMapper.saveCol(collectvideo);
    }

    @Override
    public int doDelCol(Collectvideo collectvideo) {
        return videoMapper.delCol(collectvideo);
    }

    @Override
    public Collectvideo doFindCol(Collectvideo collectvideo) {
        return videoMapper.findCOl(collectvideo);
    }

    @Override
    public int doAddNote(Vnote vnote) {
        return videoMapper.addNote(vnote);
    }

    @Override
    public Vnote doFindNote(Vnote vnote) {
        return videoMapper.findNote(vnote);
    }

    @Override
    public int doUpdateNote(Vnote vnote) {
        return videoMapper.updateNote(vnote);
    }


}
