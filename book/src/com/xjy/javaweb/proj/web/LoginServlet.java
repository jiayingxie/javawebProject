package com.xjy.javaweb.proj.web;

import com.xjy.javaweb.proj.pojo.User;
import com.xjy.javaweb.proj.service.UserService;
import com.xjy.javaweb.proj.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author Jiaying Xie
 * @Description: servlet about user login in
 */
public class LoginServlet extends HttpServlet {

    private UserService userService = new UserServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // if I write the next line, error Cannot forward after response has been committed, ??? why
//        super.doPost(req, resp);

        // 1. get post request's parameter
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        // 2. check whether the username and password could be found in database
        if (userService.login(new User(null, username, password, null)) == null) {
            // userService.login returns null means wrong

            // 2.1 save the wrong message and the form data to the request Field
            req.setAttribute("msg", "Your username or password is wrong");
            req.setAttribute("username", username);


//            System.out.println("Your username or password is wrong");
            // 2.2 redirect to the login page
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req, resp);
        } else {
            // login successfully, so redirect to the login_success.jsp
            req.getRequestDispatcher("/pages/user/login_success.jsp").forward(req, resp);
        }
    }
}
