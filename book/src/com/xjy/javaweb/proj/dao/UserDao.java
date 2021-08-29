package com.xjy.javaweb.proj.dao;

import com.xjy.javaweb.proj.pojo.User;

/**
 * @Author Jiaying Xie
 * @Description: the interface of query `t_user` table in database
 */
public interface UserDao {
    /* 
     * @param username
     * @return return null if no such user; otherwise, return the user.
     * @Description: query the user using the username
     **/
    public User queryUserByUsername(String username);

    /* 
     * @param username
     * @param password
     * @return return null means username of pwd is wrong; otherwise, return the user.
     * @Description: search the user through username and password.
     **/
    public User queryUserByUsernameAndPassword(String username,String password);
    
    /* 
     * @param user
     * @return return -1 means fails; otherwise, return the number of influenced rows
     * @Description: save the user
     **/
    public int saveUser(User user);
}
