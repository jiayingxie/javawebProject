package com.xjy.javaweb.proj.test;

import com.xjy.javaweb.proj.utils.JdbcUtils;
import org.junit.Test;

/**
 * @Author Jiaying Xie
 * @Description:
 */
public class JdbcUtilsTest {
    @Test
    public void testJdbcUtils() {
        System.out.println(JdbcUtils.getConnection());
    }
}
