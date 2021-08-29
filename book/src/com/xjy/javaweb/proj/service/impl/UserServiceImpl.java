package com.xjy.javaweb.proj.service.impl;

import com.xjy.javaweb.proj.dao.UserDao;
import com.xjy.javaweb.proj.dao.impl.UserDaoImpl;
import com.xjy.javaweb.proj.pojo.User;
import com.xjy.javaweb.proj.service.UserService;

/**
 * @Author Jiaying Xie
 * @Description: implements UserService
 */
public class UserServiceImpl implements UserService {
    private UserDao userDao = new UserDaoImpl();

    @Override
    public void registUser(User user) {
        userDao.saveUser(user);
    }

    @Override
    public User login(User user) {
        return userDao.queryUserByUsernameAndPassword(user.getUsername(), user.getPassword());
    }

    @Override
    public boolean existsUsername(String username) {
        if (userDao.queryUserByUsername(username) == null) {
            return false;
        }
        return true;
    }
}
