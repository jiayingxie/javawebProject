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
import java.util.List;

/**
 * @Author Jiaying Xie
 * @Description:
 */
public class BookServlet extends BaseServlet{

    private BookService bookService = new BookServiceImpl();

    /*
     * @param req
     * @param resp
     * @return
     * @Description: query all books and RequestDispatcher so that all
     *                  books could be listed on book_manager.jsp page
     **/
    protected void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
            IOException {
        // 1. query all books
        List<Book> books = bookService.queryBooks();

        // 2. save all books to Request scope
        req.setAttribute("books", books);

        // 3. RequestDispatcher to /pages/manager/book_manager.jsp
        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req,resp);
    }

    // add a book
    protected void add(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException{
        // always let the adding book displayed on the last page
        // 添加的图书永远显示在最后一页
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 0);
        pageNo += 1;

        // 1. get the request parameter and encapsulate as a Book Object
        Book book = WebUtils.copyParamToBean(req.getParameterMap(), new Book());

        // 2. call BookService.addBook()
        bookService.addBook(book);

        // 3. go to the books list page

        // /manager/bookServlet?action=list，不能用请求转发，不然可能表单会重复提交
        // req.getRequestDispatcher("/manager/bookServlet?action=list").forward(req, resp);
        // 应该用重定向
        resp.sendRedirect(req.getContextPath() + "/manager/bookServlet?action=page&pageNo=" + pageNo);
    }

    // delete a book by id
    protected void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. get the request parameter, book id
        String id = req.getParameter("id");

        // 2. delete the book by id
        bookService.deleteBookById(WebUtils.parseInt(id, 0));

        // 3. go back to the book list page
        resp.sendRedirect(req.getContextPath() + "/manager/bookServlet?action=page&pageNo=" + req.getParameter("pageNo"));
    }


    // get the book information
    protected void getBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. get the request parameter, book id
        String id = req.getParameter("id");

        // 2. search the book by id, using BookService.queryBookById()
        Book book = bookService.queryBookById(WebUtils.parseInt(id, 0));

        // 3. save the Book to the request scope
        req.setAttribute("book", book);

        // 4. getRequestDispatcher to the book_edit.jsp page
        req.getRequestDispatcher("/pages/manager/book_edit.jsp").forward(req, resp);
    }

    // update the information of a book
    protected void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. get the request parameter and encapsulate the Book
        Book book = WebUtils.copyParamToBean(req.getParameterMap(), new Book());

        // 2. update the book by BookService.updateBook()
        bookService.updateBook(book);

        // 3. redirect to the book list page
        resp.sendRedirect(req.getContextPath() + "/manager/bookServlet?action=page&pageNo=" + req.getParameter("pageNo"));
    }

    // dealing with the paging function
    protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. get parameters
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 1);
        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);

        // 2. call
        Page<Book> page = bookService.page(pageNo, pageSize);
        page.setUrl("manager/bookServlet?action=page");

        // 3. save the data to request scope
        req.setAttribute("page", page);

        // 4. getRequestDispatcher
        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req, resp);
    }
}
