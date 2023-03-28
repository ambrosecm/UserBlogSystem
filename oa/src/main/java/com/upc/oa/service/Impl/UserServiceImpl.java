package com.upc.oa.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.upc.oa.dto.CcommentDto;
import com.upc.oa.dto.UserDto;
import com.upc.oa.dto.UserStaDto;
import com.upc.oa.mapper.UserMapper;
import com.upc.oa.po.Blog;
import com.upc.oa.po.Ccomment;
import com.upc.oa.po.User;
import com.upc.oa.po.Video;
import com.upc.oa.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;
//    @Resource
//    private List<Video> list;

    @Override
    public List<User> findAllUsers() {
        List<User> users = null;
        users = userMapper.findAllUsers();
        return users;
    }

    @Override
    public int addUser(UserDto dto) {
        return userMapper.addUser(dto);
    }

    @Override
    public int deleteUser(UserDto dto) {
        return userMapper.deleteUser(dto);
    }

    @Override
    public int doReg(UserDto dto) {
        return userMapper.save(dto);
    }

    @Override
    public User doLogin(UserDto dto) {
        User user = null;
        user=userMapper.login(dto);
        return user;
    }

    @Override
    public int dofindByName(String name) {
        int status=0;
        status=userMapper.findByName(name);
        return status;
    }

    @Override
    public int doupdate(UserDto dto) {
        int status=0;
        status=userMapper.editSave(dto);
        return status;
    }

    @Override
    public User dofindName(String name) {
        User user=null;
        user=userMapper.findName(name);
        return user;
    }

    @Override
    public int doupdatepwd(String npwd, String opwd, String name) {
        int row=0;
        row=userMapper.editPwd(npwd, opwd, name);
        return row;
    }

    @Override
    public int dodelUser(String userName, String password) {
        int row=0;
        row=userMapper.delUser(userName, password);
        return row;
    }

    @Override
    public int updateAvatar(String userName, String avatar_url) {
        int row=0;
        row=userMapper.updateAvatar(userName, avatar_url);
        return row;
    }

    @Override
    public PageInfo<Video> findById(int pageNum, int pageSize,String uid) {
        PageInfo<Video> page=null;
        PageHelper.startPage(pageNum,pageSize);
        List<Video> videos=userMapper.findById(uid);
        page=new PageInfo<>(videos);
        return page;
    }

    @Override
    public PageInfo<Video> findByIdbuy(int pageNum, int pageSize, String uid) {
        PageInfo<Video> page=null;
        PageHelper.startPage(pageNum,pageSize);
        List<Video> videos=userMapper.findByIdbuy(uid);
        page=new PageInfo<>(videos);
        return page;
    }

    @Override
    public PageInfo<Ccomment> findCc(int pageNum, int pageSize, CcommentDto dto) {
        PageInfo<Ccomment> page=null;
        PageHelper.startPage(pageNum,pageSize);
        List<Ccomment> ccomments=userMapper.findCc(dto);
        page=new PageInfo<>(ccomments);
        return page;
    }

    @Override
    public int deleteCc(String ccid) {
        int row=0;
        row=userMapper.deleteCc(ccid);
        return row;
    }

    @Override
    public int delCvideo(String uid, String vid) {
        int row=0;
        row=userMapper.delCvideo(uid, vid);
        return  row;
    }

    @Override
    public PageInfo<Blog> findBlog(int pageNum, int pageSize,String uid) {
        PageInfo<Blog> page=null;
        PageHelper.startPage(pageNum,pageSize);
        List<Blog> blogs=userMapper.findBlog(uid);
        page=new PageInfo<>(blogs);
        return page;
    }


    public int updateUser(UserDto dto) {
        return userMapper.updateUser(dto);
    }

    @Override
    public List<UserStaDto> doCountAddress() {
        List<UserStaDto> userStaDtos = null;
        userStaDtos = userMapper.countAddress();
        return userStaDtos;
    }

    @Override
    public int updatepassword(UserDto dto) {
        return userMapper.updatepassword(dto);
    }

    @Override
    public PageInfo<User> findAllUsers2(int pageNum, int pageSize, UserDto dto) {
        PageInfo<User> pages= null;
        PageHelper.startPage(pageNum,pageSize);
        List<User> users=userMapper.findAllUsers2(dto);
        pages=new PageInfo<>(users);
        return pages;
    }
}
