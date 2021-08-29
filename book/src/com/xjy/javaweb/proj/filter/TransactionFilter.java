package com.xjy.javaweb.proj.filter;

import com.xjy.javaweb.proj.utils.JdbcUtils;

import javax.servlet.*;
import java.io.IOException;

/**
 * @Author Jiaying Xie
 * @Description:
 */
public class TransactionFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        try {
            filterChain.doFilter(servletRequest, servletResponse);
            JdbcUtils.commitAndClose();
        } catch (Exception e) {
            JdbcUtils.rollbackAndClose();
            e.printStackTrace();
            // throw exception to Tomcat, and provides some user-friendly info, (go to /pages/error)
            throw new RuntimeException(e);
        }
    }

    @Override
    public void destroy() {

    }
}
