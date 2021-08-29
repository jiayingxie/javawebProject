package com.xjy.javaweb.proj.utils;

import java.util.Map;
import org.apache.commons.beanutils.BeanUtils;



/**
 * @Author Jiaying Xie
 * @Description: using BeanUtils to populate the value in Map to the bean object
 */
public class WebUtils {
    /*
     * @param value
     * @param bean
     * @return the bean object
     * @Description: populate the the value in req.getParameterMap() to bean object, using set methods in JavaBean
     *               把 req.getParameterMap()中的值注入到对应的 JavaBean属性中，是通过 JavaBean的set方法注入的
     **/
    public static <T> T copyParamToBean(Map value , T bean ){
        try {
            // populate all request's parameters to JavaBean one-off
            // 一次性的把所有请求的参数注入到JavaBean 中
            BeanUtils.populate(bean, value);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bean;
    }

    /*
     * @param s
     * @param defaultValue
     * @return
     * @Description: change the String into int
     **/
    public static int parseInt(String s, int defaultValue) {

        try {
            if (s == null) return defaultValue;
            else return Integer.parseInt(s);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return defaultValue;
    }
}
