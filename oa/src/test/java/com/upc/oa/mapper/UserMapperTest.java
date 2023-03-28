package com.upc.oa.mapper;

import com.upc.oa.dto.UserDto;
import com.upc.oa.po.Blog;
import com.upc.oa.po.Ccomment;
import com.upc.oa.po.User;
import com.upc.oa.po.Video;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserMapperTest {

    @Resource
    private UserMapper userMapper;

    @Test
    void isusername() {
        //UserDto dto=new UserDto();
        //dto.setUserName("test5");
        String name="dfc";
        int row=userMapper.findByName(name);
        assertEquals(0,row);

    }

    @Test
    void editSave() {
        UserDto dto=new UserDto();
        dto.setUserName("test1");
        dto.setUbirth("2021/12/01");
        dto.setPhone("18299377811");
        dto.setSex(1);
        dto.setEmail("yyd@126.com");
        int row=userMapper.editSave(dto);
        assertEquals(1,row);
    }

    @Test
    void findName() {
        String name="test1";
        User user = new User();
        user=userMapper.findName(name);
    }

    @Test
    void editPwd() {
        String name="test1";
        String opwd="123456";
        String npwd="123";
        int row=userMapper.editPwd(npwd,opwd,name);
        assertEquals(1,row);
    }

    @Test
    void save() {
        UserDto dto=new UserDto();
        dto.setUserName("admin");
        dto.setPassword("123");
        dto.setUbirth("2021/12/01");
        dto.setPhone("18299377811");
        dto.setSex(0);
        dto.setEmail("yyd1@126.com");
        int row=userMapper.save(dto);
    }

    @Test
    void delUser() {
        String userName="test6";
        String password="123";
        int row=userMapper.delUser(userName,password);
    }

    @Test
    void findById() {
        String uid="18";
        List<Video> list=userMapper.findById(uid);
        System.out.println(list);
    }

    @Test
    void findCc() {
        String id="18";
        //List<Ccomment> list=userMapper.findCc(id);
        //System.out.println(list);
    }

    @Test
    void delCvideo() {
        String uid="18";
        String vid="2001";
        int row=userMapper.delCvideo(uid,vid);
        //assertEquals(0,row);
    }

    @Test
    void findBlog() {
        String uid="18";
        List<Blog> list=userMapper.findBlog(uid);
        System.out.println(list);
    }
}