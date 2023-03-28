package com.upc.oa.mapper;

import com.upc.oa.dto.BlogDto;
import com.upc.oa.dto.CcommentDto;
import com.upc.oa.dto.UserDto;
import com.upc.oa.dto.UserStaDto;
import com.upc.oa.po.Blog;
import com.upc.oa.po.Ccomment;
import com.upc.oa.po.User;
import com.upc.oa.po.Video;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {
    List<User> findAllUsers();

    //实现注册功能
    int save(UserDto dto);

    //实现登录功能
    User login(UserDto dto);

    //判断用户名是否存在
    int findByName(String name);

    //修改个人信息
    int editSave(UserDto dto);

    //按照用户名查找
    User findName(String name);

    //修改密码
    int editPwd(@Param("npwd")String npwd, @Param("opwd")String opwd, @Param("userName")String name);

    //注销账号
    int delUser(@Param("userName")String userName,@Param("password")String password);

    //修改头像
    int updateAvatar(@Param("userName")String userName,@Param("avatar_url")String avatar_url);

    //找到用户收藏的所有视频
    List<Video> findById(String uid);

    //找到用户购买的所有视频
    List<Video> findByIdbuy(String uid);

    //查看用户自己收到的博客留言
    List<Ccomment> findCc(CcommentDto dto);

    //删除用户自己的留言
    int deleteCc(String ccid);

    //查找用户自己的博客
    List<Blog> findBlog(String uid);

    //用户取消收藏视频操作
    int delCvideo(String uid,String vid);

    //增（后台）
    int addUser(UserDto dto);
    //删（后台）
    int deleteUser(UserDto dto);
    //改（后台）
    int updateUser(UserDto dto);

    List<User> findAllUsers2(UserDto dto);

    //实现用户地域数量统计
    List<UserStaDto> countAddress();

    int updatepassword(UserDto dto);
}
