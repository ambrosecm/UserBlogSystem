package com.upc.oa.controller;

import com.github.pagehelper.PageInfo;
import com.upc.oa.dto.CcommentDto;
import com.upc.oa.dto.UserDto;

import com.upc.oa.po.*;
import com.upc.oa.dto.UserStaDto;
import com.upc.oa.po.Ccomment;
import com.upc.oa.po.UpPhotoNameUtils;
import com.upc.oa.po.User;
import com.upc.oa.po.Video;
import com.upc.oa.service.UserService;
import com.upc.oa.tool.GenerateCode;
import org.apache.commons.mail.HtmlEmail;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageInfo;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;


    @RequestMapping("/reg")
    public int reg(UserDto dto){
        return userService.doReg(dto);
    }
    @RequestMapping("/updatepassword")
    public int updatepassword(UserDto dto){
        return userService.updatepassword(dto);
    }

    @RequestMapping("/login")
    public User login(UserDto dto){
        User user=null;
        user=userService.doLogin(dto);
        return user;
    }
    @RequestMapping("/deleteCode")
    public @ResponseBody void deleteCode(HttpSession session){
        session.removeAttribute("registerCode");
    }

    @RequestMapping("/getEmailCode")
    public @ResponseBody
    String getEmailCode(String email, HttpSession session){
        try {
            HtmlEmail htmlEmail = new HtmlEmail();//不用更改
            htmlEmail.setHostName("smtp.163.com");//需要修改，126邮箱为smtp.126.com,163邮箱为163.smtp.com，QQ为smtp.qq.com
            htmlEmail.setCharset("UTF-8");
            htmlEmail.addTo(email);// 收件地址
            String code= GenerateCode.getCode();
            htmlEmail.setFrom("cm_13969429693@163.com", "aa");//此处填邮箱地址和用户名,用户名可以任意填写
            htmlEmail.setAuthentication("cm_13969429693@163.com", "FDVTEHXGVWUNLQJK");
            htmlEmail.setSubject("邮箱验证");//此处填写邮件名，邮件名可任意填写
            htmlEmail.setMsg("尊敬的用户您好,您本次查询的验证码是:" + code);//此处填写邮件内容
            htmlEmail.send();
            return code;
        }
        catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping("/findByname")
    public int findByname(String name){
        return userService.dofindByName(name);
    }


    @RequestMapping("/doupdate")
    public int doupdata(UserDto dto)
    {
        return userService.doupdate(dto);
    }

    @RequestMapping("/dofindName")
    public User dofindName(String name){
        User user=null;
        user=userService.dofindName(name);
        return user;
    }

    @RequestMapping("/doupdatepwd")
    public int doupdatepwd(String npwd,String opwd,String name){
        int row=0;
        row=userService.doupdatepwd(npwd, opwd, name);
        return row;
    }

    @RequestMapping("/dodeluser")
    public int dodelUser(String userName,String password){
        int row=0;
        row=userService.dodelUser(userName, password);
        return row;
    }

    /**
     * 修改头像
     */
    @RequestMapping("/upload")
    public String singleFileUpload(@RequestParam("file") MultipartFile file,
                                   HttpServletRequest request, String userName) {
        //@RequestParam("file") MultipartFile file为接收图片参数
        //Long userId,String status 用户Id和状态

        try {
            byte[] bytes = file.getBytes();
            String imageFileName = file.getOriginalFilename();
            String fileName = UpPhotoNameUtils.getPhotoName("img",imageFileName);
            Path path = Paths.get("D:\\boke\\ProjectInit\\platform\\src\\assets\\" + fileName);
            //“C:\\框架\\D4\\d4_pc_ui\\src\\assets\\images\\img\\”为本地目录
            Files.write(path, bytes);//写入文件
            String avatar_url=fileName;
            userService.updateAvatar(userName,avatar_url);
            System.out.println(fileName+"\n");
            return fileName;//返回文件名字
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    //查找用户收藏的视频
    @RequestMapping("/findByUid")
    public PageInfo<Video> findById(int pageNum, int pageSize,String uid){
        PageInfo<Video> page =null;
        page=userService.findById(pageNum,pageSize,uid);
        return page;
    }

    @RequestMapping("/findByidbuy")
    public PageInfo<Video> findByIdbuy(int pageNum, int pageSize,String uid){
        PageInfo<Video> page =null;
        page=userService.findByIdbuy(pageNum,pageSize,uid);
        return page;
    }

    @RequestMapping("/findCc")
    public PageInfo<Ccomment> findByIdbuy(int pageNum, int pageSize, CcommentDto dto){
        PageInfo<Ccomment> page =null;
        page=userService.findCc(pageNum,pageSize,dto);
        return page;
    }

    @RequestMapping("/deleteCc")
    public int deleteCc(String ccid){
        int row=0;
        row=userService.deleteCc(ccid);
        return row;
    }

    @RequestMapping("/delCvideo")
    public int delCvideo(String uid, String vid) {
        int row=0;
        row=userService.delCvideo(uid, vid);
        return  row;
    }

    @RequestMapping("/findBlog")
    public PageInfo<Blog> findBlog(int pageNum, int pageSize,String uid) {
        PageInfo<Blog> page =null;
        page=userService.findBlog(pageNum,pageSize,uid);
        return page;
    }



    @RequestMapping("/findAllUsers")
    public PageInfo<User> findAllUsers(int pageNum,int pageSize,UserDto dto)
    {
        PageInfo<User> users=null;
        users=userService.findAllUsers2(pageNum,pageSize,dto);
        return users;
    }
    @RequestMapping("/addUser")
    public int addUser(UserDto dto)
    {
        return userService.addUser(dto);
    }
    @RequestMapping("/deleteUser")
    public int deleteUser(UserDto dto)
    {
        return userService.deleteUser(dto);
    }
    @RequestMapping("/updateUser")
    public int updateUser(UserDto dto)
    {
        return userService.updateUser(dto);
    }

    @RequestMapping("/countUserAddress")
    public List<Map<Object,Object>> countEmp(){
        List<UserStaDto> userStaDtos = userService.doCountAddress();
        ArrayList<Map<Object, Object>> result = new ArrayList<>();
        for(UserStaDto userStaDto:userStaDtos){
            HashMap<Object,Object> map = new HashMap<>();
            map.put("name",userStaDto.getAddress());
            map.put("value",userStaDto.getDnum());
            result.add(map);
        }
        return result;
    }

}
