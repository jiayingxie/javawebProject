package com.xjy.javaweb.proj.service;

import com.xjy.javaweb.proj.pojo.User;

/**
 * @Author Jiaying Xie
 * @Description: some actions user could do, such as register, login and so on
 */
public interface UserService {
    /**
     * @param user
     * @return
     * @Description: register a user
     **/
    public void registUser(User user);

    /**
     * @param user
     * @return if returns null, failed to login; otherwise, success
     * @Description: login
     **/
    public User login(User user);

    /*
     * @param username
     * @return false means there is already a username; true means could
               use the username to register a new User
     * @Description: check whether a username could be used to register
     **/
    public boolean existsUsername(String username);
}
