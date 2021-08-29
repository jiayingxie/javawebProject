package com.xjy.javaweb.proj.web;

import com.google.gson.Gson;
import com.xjy.javaweb.proj.pojo.User;
import com.xjy.javaweb.proj.service.UserService;
import com.xjy.javaweb.proj.service.impl.UserServiceImpl;
import com.xjy.javaweb.proj.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

/**
 * @Author Jiaying Xie
 * @Description: servlet about user's services
 */
public class UserServlet extends BaseServlet {

    private UserService userService = new UserServiceImpl();

    /*
     * @param req
     * @param resp
     * @return
     * @Description: login function
     **/
    protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. get post request's parameter
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        // 2. check whether the username and password could be found in database
        User loginUser = userService.login(new User(null, username, password, null));
        if (loginUser == null) {
            // if userService.login returns null, means wrong

            // 2.1 save the wrong message and the form data to the request Field
            req.setAttribute("msg", "Your username or password is wrong");
            req.setAttribute("username", username);

            // 2.2 redirect to the login page
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req, resp);
        } else {
            // login successfully

            // 1. save user's information to session scope
            req.getSession().setAttribute("user", loginUser);

            // 2. redirect to the login_success.jsp
            req.getRequestDispatcher("/pages/user/login_success.jsp").forward(req, resp);
        }
    }

    /*
     * @param req
     * @param resp
     * @return
     * @Description: user log out function
     **/
    protected void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. destroy user information in session
        req.getSession().invalidate();

        // 2. redirect to home page
        resp.sendRedirect(req.getContextPath());
    }

    /*
     * @param req
     * @param resp
     * @return
     * @Description: register a new user function
     **/
    protected void regist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. get the parameter of request
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String code = req.getParameter("code");
        // get captcha
        String token = (String) req.getSession().getAttribute(KAPTCHA_SESSION_KEY);
        // delete captcha
        req.removeAttribute(KAPTCHA_SESSION_KEY);

        // ???
        // User user = WebUtils.copyParamToBean(req.getParameterMap(), new User());

        // 2. check whether the identify code is correct
        if (token.equalsIgnoreCase(code)) {
            // 3. check whether the username is already exists
            if (userService.existsUsername(username)) {
                // if username is already exists

                // 3.1, save the data to the request scope
                req.setAttribute("msg", "Username is already exists.");
                req.setAttribute("username", username);
                req.setAttribute("email", email);

                // 3.2, redirect to the register page
                req.getRequestDispatcher("/pages/user/regist.jsp").forward(req, resp);
            } else {
                // everything is ok, register a new user
                userService.registUser(new User(null, username, password, email));
                req.getRequestDispatcher("/pages/user/regist_success.jsp").forward(req, resp);
            }
        } else {
            // if the identify code is not correct

            // 1, save the data to the request scope
            req.setAttribute("msg", "Identify code is wrong.");
            req.setAttribute("username", username);
            req.setAttribute("email", email);

            // 2, redirect to the register page
            req.getRequestDispatcher("/pages/user/regist.jsp").forward(req, resp);
        }
    }

    protected void ajaxExistsUsername(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. get parameter
        String username = req.getParameter("username");

        // 2. call userService.existsUsername()
        boolean existsUsername = userService.existsUsername(username);

        // 3. encapsulation
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("existsUsername", existsUsername);
        Gson gson = new Gson();
        String json = gson.toJson(resultMap);
        resp.getWriter().write(json);
    }
}
