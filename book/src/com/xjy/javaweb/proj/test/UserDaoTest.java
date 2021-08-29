package com.xjy.javaweb.proj.test;

import com.xjy.javaweb.proj.dao.UserDao;
import com.xjy.javaweb.proj.dao.impl.UserDaoImpl;
import com.xjy.javaweb.proj.pojo.User;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @Author Jiaying Xie
 * @Description:
 */
public class UserDaoTest {

    UserDao userDao = new UserDaoImpl();

    @Test
    public void queryUserByUsername() {
        if (userDao.queryUserByUsername("admin1234") == null ){
            System.out.println("用户名可用！");
        } else {
            System.out.println("用户名已存在！");
        }
    }

    @Test
    public void queryUserByUsernameAndPassword() {
        if ( userDao.queryUserByUsernameAndPassword("admin","admin1234") == null) {
            System.out.println("用户名或密码错误，登录失败");
        } else {
            System.out.println("查询成功");
        }

        if ( userDao.queryUserByUsernameAndPassword("admin","admin") == null) {
            System.out.println("用户名或密码错误，登录失败");
        } else {
            System.out.println("查询成功");
        }
    }

    @Test
    public void saveUser() {
        System.out.println( userDao.saveUser(new User(null,"xjy", "xjy", "xjy@gmail.com")) );
    }
}