package com.xjy.javaweb.proj.web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * @Author Jiaying Xie
 * @Description: servlet using reflection to call the corresponding method,
 *              优化大量 if-else语句，以后处理user业务的时候，不用写判断这个是login业务
 *              还是register业务还是修改密码业务……
 */
public abstract class BaseServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // this is to deal with the error that something calls GET request,
        // but I only override doPost()
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // if I write the following code, error Cannot forward after response
        // has been committed, ??? why
        // super.doPost(req, resp);

        //
        // 解决post请求中文乱码问题，一定要在获取请求参数之前调用才有效
        req.setCharacterEncoding("UTF-8");

        // 解决响应的中文乱码问题
        resp.setContentType("text/html; charset=UTF-8");

        // get the action's value in <input type="hidden" name="action" value="XXX"/>
        // 这个是根据 <input type="hidden" name="action" value="XXX"/>得到的
        String action = req.getParameter("action");

        try {
            // 1. get the action's corresponding method
            // 获取action业务鉴别字符串，获取相应的业务 方法反射对象
            Method method = this.getClass().getDeclaredMethod(action, HttpServletRequest.class, HttpServletResponse.class);

            // 2. call method
            method.invoke(this, req, resp);
        } catch (Exception e) {
            e.printStackTrace();
            //
            // 把异常抛给 Filter过滤器
            throw new RuntimeException(e);
        }
    }
}
