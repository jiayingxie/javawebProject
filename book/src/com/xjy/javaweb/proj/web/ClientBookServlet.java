package com.xjy.javaweb.proj.web;

import com.xjy.javaweb.proj.pojo.Book;
import com.xjy.javaweb.proj.pojo.Page;
import com.xjy.javaweb.proj.service.BookService;
import com.xjy.javaweb.proj.service.impl.BookServiceImpl;
import com.xjy.javaweb.proj.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author Jiaying Xie
 * @Description:
 */
public class ClientBookServlet extends BaseServlet{
    BookService bookService = new BookServiceImpl();

    // dealing with the paging function
    protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. get parameters
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 1);
        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);

        // 2. call
        Page<Book> page = bookService.page(pageNo, pageSize);
        page.setUrl("client/bookServlet?action=page");

        // 3. save the data to request scope
        req.setAttribute("page", page);

        // 4. getRequestDispatcher
        req.getRequestDispatcher("/pages/client/index.jsp").forward(req, resp);
    }

    // paging by price
    protected void pageByPrice(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. get parameters
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 1);
        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        double min = WebUtils.parseInt(req.getParameter("min"), 0);
        double max = (double) WebUtils.parseInt(req.getParameter("max"), Integer.MAX_VALUE);

        // 2. call
        Page<Book> page = bookService.pageByPrice(pageNo, pageSize, min, max);

        // 设置url
        StringBuilder sb = new StringBuilder("client/bookServlet?action=pageByPrice");
        // 如果有最小价格，将最小价格追加到url上
        if (req.getParameter("min") != null) {
            sb.append("&min=" + req.getParameter("min"));
        }
        if (req.getParameter("max") != null) {
            sb.append("&max=" + req.getParameter("max"));
        }
        page.setUrl(sb.toString());

        // 3. save the data to request scope
        req.setAttribute("page", page);

        // 4. getRequestDispatcher
        req.getRequestDispatcher("/pages/client/index.jsp").forward(req, resp);
    }

}
