package com.upc.oa.service.Impl;

import com.upc.oa.po.User;
import com.upc.oa.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class UserServiceImplTest {
    @Resource
    private UserService userService;


    @Test
    void findAllUsers() {
    }
}