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
 * @Description: servlet about register a new user
 */
public class RegistServlet extends HttpServlet {
    private UserService userService = new UserServiceImpl();

    @Override
    // since the password should not be seen in the browser, using post request
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // if I write the next line, error Cannot forward after response has been committed, ??? why
//        super.doPost(req, resp);

        // 1. get the parameter of request
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String code = req.getParameter("code");

        // 2. check whether the identify code is correct ??? hardcode 'abcde'
        if ("abcde".equalsIgnoreCase(code)) {
            // 3. check whether the username is already exists
            if (userService.existsUsername(username)) {
                // username is already exists

                // first, save the data to the request scope
                req.setAttribute("msg", "Username is already exists.");
                req.setAttribute("username", username);
                req.setAttribute("email", email);

               // second, redirect to the register page
                req.getRequestDispatcher("/pages/user/regist.jsp").forward(req, resp);
            } else {
                userService.registUser(new User(null, username, password, email));
                req.getRequestDispatcher("/pages/user/regist_success.jsp").forward(req, resp);
            }
        } else {
            // if the identify code is not correct
            // first, save the data to the request scope
            req.setAttribute("msg", "Identify code is wrong.");
            req.setAttribute("username", username);
            req.setAttribute("email", email);

            // second, redirect to the register page
            req.getRequestDispatcher("/pages/user/regist.jsp").forward(req, resp);
       }
    }
}
