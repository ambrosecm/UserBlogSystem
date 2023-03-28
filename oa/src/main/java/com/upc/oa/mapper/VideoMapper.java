package com.upc.oa.mapper;

import com.upc.oa.dto.BlogDto;
import com.upc.oa.dto.CcommentDto;
import com.upc.oa.dto.VcommentDto;
import com.upc.oa.dto.VideoDto;

import com.upc.oa.po.*;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface VideoMapper {
    //根据参数查找视频
    List<Video> findVideoByParam(VideoDto videoDto);

    //找所有视频（后台）
    List<Video> findAllVideos(Video video);

    //播放量+1操作
    int updateVplayNum(Integer vid);

    //返回视频对应评论
    List<Vcomment> findCtById(Video video);
    //发表评论
    int sendVcomment(Vcomment vcomment);
    //点赞评论
    int praiseVcomment(VcommentDto vcommentDto);

    int deleteVideo(Video video);

    List<Type> findAllType(Type type);

    int addVideo(Video video);

    int updateVideo(Video video);
    //根据id查找视频
    Video findVideoById(Integer vid);

    //根据id查找收藏视频的数量
    int findColVideoNum(Integer vid);

    //点赞数量+1
    int addPraise(Integer vid);

    //点赞量-1
    int subPraise(Integer id);

    //向收藏视频表中插入一条数据
    int saveCol(Collectvideo collectvideo);

    //向收藏视频表中删除一条数据
    int delCol(Collectvideo collectvideo);

    //查询收藏数据
    Collectvideo findCOl(Collectvideo collectvideo);

    //向视频笔记插入一条笔记
    int addNote(Vnote vnote);

    //查询一条笔记
    Vnote findNote(Vnote vnote);

    //更新笔记
    int updateNote(Vnote vnote);


}
