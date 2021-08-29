package com.xjy.javaweb.proj.dao.impl;

import java.sql.Connection;
import java.util.*;
import java.sql.SQLException;

import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.xjy.javaweb.proj.utils.JdbcUtils;

/**
 * @Author Jiaying Xie
 * @Description: use DbUtils to manipulate database 使用DbUtils 操作数据库
 */
public abstract class BaseDao {
    private QueryRunner queryRunner = new QueryRunner();
    
    /* *
     * @Param [java.lang.String, java.lang.Object...]
     * @return int , if return -1 means failed; otherwise, return the number of influenced rows
     * @Description: to execute insert, delete, update sql queries
     **/
    public int update(String sql, Object... args) {
        Connection conn = JdbcUtils.getConnection();
        try {
            return queryRunner.update(conn, sql, args);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /* *
     * @Param type, the return type
     * @Param sql: the executed sql query
     * @Param args: sql's corresponding parameters type
     * @Param T: the Generic type of return value
     * @return T
     * @Description: the sql query returns one javabean
     **/
    public <T> T queryForOne(Class<T> type, String sql, Object... args) {
        Connection conn = JdbcUtils.getConnection();
        try {
            return queryRunner.query(conn, sql, new BeanHandler<T>(type), args);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /*
     * @param type: the return type
     * @param sql: the executed sql query
     * @param args: sql's corresponding parameters type; sql 对应的参数值
     * @param <T>: the Generic type of return value 返回的类型的泛型
     * @return java.util.List<T>
     * @Description: the sql query returns several javabeans 查询返回多个javaBean 的sql 语句
     **/
    public <T> List<T> queryForList(Class<T> type, String sql, Object... args) {
        Connection conn = JdbcUtils.getConnection();
        try {
            return queryRunner.query(conn, sql, new BeanListHandler<T>(type), args);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /*
     * @param sql: the executed sql query 执行的sql 语句
     * @param args: sql's corresponding parameters type; sql 对应的参数值
     * @return java.lang.Object
     * @Description: execute the sql query; 执行返回一行一列的sql 语句
     **/
    public Object queryForSingleValue(String sql, Object... args){
        Connection conn = JdbcUtils.getConnection();
        try {
            return queryRunner.query(conn, sql, new ScalarHandler(), args);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
