package com.xjy.javaweb.proj.web;

import com.xjy.javaweb.proj.dao.BookDao;
import com.xjy.javaweb.proj.dao.impl.BookDaoImpl;
import com.xjy.javaweb.proj.pojo.*;
import com.xjy.javaweb.proj.service.BookService;
import com.xjy.javaweb.proj.service.OrderService;
import com.xjy.javaweb.proj.service.impl.BookServiceImpl;
import com.xjy.javaweb.proj.service.impl.OrderServiceImpl;
import com.xjy.javaweb.proj.utils.JdbcUtils;
import com.xjy.javaweb.proj.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @Author Jiaying Xie
 * @Description:
 */
public class OrderServlet extends BaseServlet{

    OrderService orderService = new OrderServiceImpl();
    BookDao bookDao = new BookDaoImpl();
    BookService bookService = new BookServiceImpl();

    /*
     * @param req
     * @param resp
     * @return
     * @Description: create order
     **/
    protected void createOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. get parameters
        User user = (User) req.getSession().getAttribute("user");
        if (user == null) {
            // user is not logging in, go to the login page
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req, resp);
            return;
        }
        Integer userId = user.getId();
        Cart cart = (Cart) req.getSession().getAttribute("cart");

        // 2. create the order
        String orderId = orderService.createOrder(cart, userId);

        // 3. save the orderId to the session scope
        req.getSession().setAttribute("orderId", orderId);

        // 4. go to the checkout.jsp page
        resp.sendRedirect(req.getContextPath() + "/pages/cart/checkout.jsp");
    }

    /*
     * @param req
     * @param resp
     * @return
     * @Description: show all orders, only admin could use
     **/
    protected void showAllOrders(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. get all orders
        List<Order> allOrderList = orderService.showAllOrders();
        for (Order ele : allOrderList) {
            System.out.println("showAllOrders");
            System.out.println(ele);
        }

        // 2. save all books to Request scope
        req.setAttribute("allOrders", allOrderList);

        // 3. RequestDispatcher to /pages/manager/order_manager.jsp
        req.getRequestDispatcher("/pages/manager/order_manager.jsp").forward(req,resp);
    }


    protected void showAllOrdersPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. get parameters
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 1);
        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);

        // 2. call function
        Page<Order> page = orderService.showAllOrdersPage(pageNo, pageSize);
        page.setUrl("orderServlet?action=showAllOrdersPage");

        // 3. save the data to request scope
        req.setAttribute("page", page);

        // 4. getRequestDispatcher to /pages/manager/order_manager.jsp
        req.getRequestDispatcher("/pages/manager/order_manager.jsp").forward(req, resp);

    }

    /*
     * @param req
     * @param resp
     * @return
     * @Description: show one customer's all orders
     **/
    protected void showMyOrders(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. get parameters
        User user = (User) req.getSession().getAttribute("user");

        if (user == null) {
            // user is not logging in, go to the login page
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req, resp);
            return;
        }
        Integer userId = user.getId();

        // 2. call OrderService.showMyOrders()
        List<Order> orderList = orderService.showMyOrders(userId);
        for (Order ele : orderList) {
            System.out.println(ele);
        }

        // 3. save all books to Request scope
        req.setAttribute("orders", orderList);

        // 4. RequestDispatcher to /pages/order/order.jsp
        req.getRequestDispatcher("/pages/order/order.jsp").forward(req,resp);
    }


    protected void showMyOrdersPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. get parameters
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 1);
        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        User user = (User) req.getSession().getAttribute("user");

        if (user == null) {
            // user is not logging in, go to the login page
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req, resp);
            return;
        }
        Integer userId = user.getId();

        // 2. call
        Page<Order> page = orderService.showMyOrdersPage(userId, pageNo, pageSize);
        page.setUrl("orderServlet?action=showMyOrdersPage");

        // 3. save the data to request scope
        req.setAttribute("page", page);

        // 4. getRequestDispatcher
        req.getRequestDispatcher("/pages/order/order.jsp").forward(req, resp);
    }

    /*
     * @param req
     * @param resp
     * @return
     * @Description: show one orderId's all orderItems
     **/
    protected void showOrderDetails(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. get parameter
        String orderId = req.getParameter("orderId");

        // 2. call OrderService.showOrderDetails() to get all orderItems
        List<OrderItem> orderItemList = orderService.showOrderDetails(orderId);

        // 3. save to the Request scope
        req.setAttribute("orderDetails", orderItemList);

        // 4. RequestDispatcher to /pages/order/order_detail.jsp
        req.getRequestDispatcher("/pages/order/order_detail.jsp").forward(req,resp);
    }


    protected void showOrderDetailsPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. get parameters
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 1);
        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        String orderId = req.getParameter("orderId");

        // 2. call
        Page<OrderItem> page = orderService.showOrderDetailsPage(orderId, pageNo, pageSize);
        page.setUrl("orderServlet?action=showOrderDetailsPage");

        // 3. save the data to request scope
        req.setAttribute("page", page);

        // 4. getRequestDispatcher
        req.getRequestDispatcher("/pages/order/order_detail.jsp").forward(req, resp);
    }


    protected void deliverOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. get parameter
        String orderId = req.getParameter("orderId");
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 1);

        // 2. call method
        orderService.deliverOrder(orderId);

        // 3. sendRedirect
        resp.sendRedirect(req.getContextPath() + "/orderServlet?action=showAllOrdersPage&pageNo=" + pageNo);
    }


    protected void receiveOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. get parameter
        String orderId = req.getParameter("orderId");
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 1);

        // 2. call method
        orderService.receiveOrder(orderId);

        // 3. sendRedirect
        resp.sendRedirect(req.getContextPath() + "/orderServlet?action=showMyOrdersPage&pageNo=" + pageNo);
    }
}
