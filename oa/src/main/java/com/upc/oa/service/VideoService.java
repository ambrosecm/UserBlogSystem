package com.upc.oa.service;
import com.upc.oa.dto.BlogDto;
import com.upc.oa.dto.CcommentDto;
import com.upc.oa.dto.VcommentDto;
import com.upc.oa.dto.VideoDto;
import com.github.pagehelper.PageInfo;

import com.upc.oa.po.*;

import java.util.List;

public interface VideoService {
    //根据参数查找视频
    List<Video> findVideoByParam(VideoDto videoDto);
    //找所有视频（后台）
    PageInfo<Video> findAllVideos(int pageNum, int pageSize, Video video);

    PageInfo<Vcomment> findCtById(int pageNum, int pageSize, Video video);

    int praiseVcomment(VcommentDto vcommentDto);

    int sendVcomment(VcommentDto vcommentDto);
    //播放量+1操作
    int doUpdateVplayNum(Integer vid);

    int deleteVideo(Video video);

    List<Type> findAllTypes(Type type);

    int addVideo(Video video);

    int updateVideo(Video video);

    //根据id查找视频
    Video doFindVideoById(Integer vid);

    //根据id查找视频的收藏数
    int doFindColVideoNum(Integer vid);

    //点赞数+1
    int doAddPraise(Integer vid);

    //点赞数-1
    int doSubPraise(Integer vid);

    //向收藏视频表中插入一条数据
    int doSaveCol(Collectvideo collectvideo);

    //向收藏视频表中删除一条数据
    int doDelCol(Collectvideo collectvideo);

    //向视频收藏表中查询数据
    Collectvideo doFindCol(Collectvideo collectvideo);

    //向笔记表插入一条数据
    int doAddNote(Vnote vnote);

    //向笔记表查询一条数据
    Vnote doFindNote(Vnote vnote);

    //更新笔记
    int doUpdateNote(Vnote vnote);
}
