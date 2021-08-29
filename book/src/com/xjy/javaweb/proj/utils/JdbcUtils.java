package com.xjy.javaweb.proj.utils;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;


public class JdbcUtils {
    private static DruidDataSource dataSource;
    private static ThreadLocal<Connection> conns = new ThreadLocal<>();

    // initialization
    static {
        try {
            Properties properties = new Properties();

            // read the jdbc.properties
            InputStream inputStream = JdbcUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
            // load data from stream
            properties.load(inputStream);

            // create the sql connection pool
            dataSource = (DruidDataSource) DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* *
     * @Param []
     * @return java.sql.Connection, if return null, fails to get connection, otherwise, success.
     * @Description: to require the connection in database connection pool
     **/
    public static Connection getConnection() {
        Connection conn = conns.get();
        if (conn == null) {
            try {
                // get connection from sql connection pool
                // 从数据库连接池中获取连接
                conn = dataSource.getConnection();

                // save to the ThreadLocal, so jdbc operations could use it
                // 保存到ThreadLocal 对象中，供后面的jdbc 操作使用
                conns.set(conn);

                // set manually manage transactions
                // 设置为手动管理事务
                conn.setAutoCommit(false);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        return conn;
    }

    /* *
     * @Param [java.sql.Connection]
     * @return void
     * @Description: close the connection, put the connection back to the connection pool
     **/
    public static void close(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    /*
     * @param
     * @return
     * @Description: submit transition and close connection; 提交事务，并关闭释放连接
     **/
    public static void commitAndClose() {
        Connection conn  = conns.get();
        if (conn != null) {
            try {
                // submit transition 提交事务
                conn.commit();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } finally {
                try {
                    // close connection 释放资源
                    conn.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }

        // 一定要执行remove 操作，否则就会出错。（因为Tomcat 服务器底层使用了线程池技术）
        conns.remove();
    }

    /*
     * @param 
     * @return
     * @Description: 回滚事务，并关闭释放连接
     **/
    public static void rollbackAndClose() {
        Connection conn  = conns.get();
        if (conn != null) {
            try {
                conn.rollback(); // 回滚事务
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } finally {
                try {
                    conn.close(); // 释放资源
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }

        // 一定要执行remove 操作，否则就会出错。（因为Tomcat 服务器底层使用了线程池技术）
        conns.remove();
    }

    public static void main(String[] args) {

    }
}
