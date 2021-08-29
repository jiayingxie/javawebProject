package com.xjy.javaweb.proj.test;

import com.xjy.javaweb.proj.pojo.User;
import com.xjy.javaweb.proj.service.UserService;
import com.xjy.javaweb.proj.service.impl.UserServiceImpl;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @Author Jiaying Xie
 * @Description:
 */
public class UserServiceTest {
    UserService userService = new UserServiceImpl();

    @Test
    public void registUser() {
        userService.registUser(new User(null, "aaa", "123456", "aaa@qq.com"));
        userService.registUser(new User(null, "bbb", "000000", "bbb@qq.com"));
    }

    @Test
    public void login() {
        System.out.println( userService.login(new User(null, "admin", "admin", null)));
        System.out.println( userService.login(new User(null, "admin", "123456", null)));
    }

    @Test
    public void existsUsername() {
        System.out.println(userService.existsUsername("xjy"));
        System.out.println(userService.existsUsername("xxx"));
    }
}