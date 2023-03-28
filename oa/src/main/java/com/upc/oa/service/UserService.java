package com.upc.oa.service;

import com.github.pagehelper.PageInfo;
import com.upc.oa.dto.CcommentDto;
import com.upc.oa.dto.UserDto;
import com.upc.oa.po.Blog;
import com.upc.oa.dto.UserStaDto;
import com.upc.oa.po.Ccomment;
import com.upc.oa.po.User;

import com.upc.oa.po.Video;
import org.springframework.stereotype.Service;




import java.util.List;

public interface UserService {
    List<User> findAllUsers();

    //用户注册
    int doReg(UserDto dto);

    //用户登录
    User doLogin(UserDto dto);

    //查看用户名是否存在
    int dofindByName(String name);

    //编辑修改个人信息
    int doupdate(UserDto dto);

    //查找个人信息
    User dofindName(String name);

    //修改密码
    int doupdatepwd(String npwd,String opwd,String name);

    //注销账号
    int dodelUser(String userName,String password);

    //修改头像
    int updateAvatar(String userName,String avatar_url);

    //查找用户收藏的视频
    PageInfo<Video> findById(int pageNum, int pageSize,String uid);

    //查找用户购买的视频
    PageInfo<Video> findByIdbuy(int pageNum, int pageSize,String uid);

    //查询用户的博客留言
    PageInfo<Ccomment> findCc(int pageNum, int pageSize, CcommentDto dto);

    //删除用户收到的留言
    int deleteCc(String ccid);

    //取消收藏视频
    int delCvideo (String uid,String vid);

    //查找用户自己写的博客
    PageInfo<Blog> findBlog(int pageNum, int pageSize,String uid);

    //找 后台
    PageInfo<User> findAllUsers2(int pageNum, int pageSize, UserDto dto);
     //增  后台
    int addUser(UserDto dto);
    //删 后台
    int deleteUser(UserDto dto);
    //改 后台
    int updateUser(UserDto dto);

    ////实现用户地域数量统计
    List<UserStaDto> doCountAddress();

    int updatepassword(UserDto dto);
}
