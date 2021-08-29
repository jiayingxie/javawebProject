package com.xjy.javaweb.proj.service.impl;

import com.sun.org.apache.xpath.internal.operations.Or;
import com.xjy.javaweb.proj.dao.BookDao;
import com.xjy.javaweb.proj.dao.OrderDao;
import com.xjy.javaweb.proj.dao.OrderItemDao;
import com.xjy.javaweb.proj.dao.impl.BookDaoImpl;
import com.xjy.javaweb.proj.dao.impl.OrderDaoImpl;
import com.xjy.javaweb.proj.dao.impl.OrderItemDaoImpl;
import com.xjy.javaweb.proj.pojo.*;
import com.xjy.javaweb.proj.service.BookService;
import com.xjy.javaweb.proj.service.OrderService;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Author Jiaying Xie
 * @Description:
 */
public class OrderServiceImpl implements OrderService {
    private OrderDao orderDao = new OrderDaoImpl();
    private OrderItemDao orderItemDao = new OrderItemDaoImpl();
    private BookDao bookDao = new BookDaoImpl();
    BookService bookService = new BookServiceImpl();

    @Override
    public String createOrder(Cart cart, Integer userId) {
        // 1. create an order object
        // 1.1 create a unique order id
        String orderId = System.currentTimeMillis() + "" + userId;
        // status = 0，未发货；=1，已发货；=2，已签收
        Order order = new Order(orderId, new Date(), cart.getTotalPrice(), 0, userId);

        // 2. save the order
        orderDao.saveOrder(order);

        // 3. save the order item in order
        for (Map.Entry<Integer, CartItem> entry : cart.getItems().entrySet()) {
            // 3.1 get the CartItem object
            CartItem cartItem = entry.getValue();
            // 3.2 create orderItem
            OrderItem orderItem = new OrderItem(null, cartItem.getName(), cartItem.getCount(), cartItem.getPrice(), cartItem.getTotalPrice(), orderId);
            // 3.3 save
            orderItemDao.saveOrderItem(orderItem);
            // 3.4 update the sales and stock
            Book book = bookDao.queryBookById(cartItem.getId());
            book.setSales(book.getSales() + cartItem.getCount());
            book.setStock(book.getStock() - cartItem.getCount());
            bookService.updateBook(book);
        }

        // 4. clear the cart since user has already bought goods
        cart.clear();

        return orderId;
    }

    @Override
    public List<Order> showAllOrders() {
        return orderDao.queryOrders();
    }

    @Override
    public Page<Order> showAllOrdersPage(int pageNo, int pageSize) {
        Page<Order> page = new Page<>();

        // 1. get the pageTotalCount and pageTotal data
        // total number of items
        Integer pageTotalCount = orderDao.queryPageOrdersCount();

        // total page number
        Integer pageTotal = pageTotalCount / pageSize;
        if (pageTotalCount % pageSize > 0) {
            pageTotal += 1;
        }

        page.setPageSize(pageSize);
        page.setPageTotalCount(pageTotalCount);
        page.setPageTotal(pageTotal);
        page.setPageNo(pageNo);

        // 2. set current page's items' data
        // begin is the first index of current page's item
        int begin = (page.getPageNo() - 1) * pageSize;
        // items is current page's items
        List<Order> items = orderDao.queryPageOrders(begin, pageSize);
        page.setItems(items);

        return page;
    }

    @Override
    public List<Order> showMyOrders(Integer userId) {
        return orderDao.queryOrderByUserId(userId);
    }

    @Override
    public Page<Order> showMyOrdersPage(Integer userId, int pageNo, int pageSize) {
        Page<Order> page = new Page<>();

        // 1. get the pageTotalCount and pageTotal data
        // total number of items
        Integer pageTotalCount = orderDao.queryPageOrderCountByUserId(userId);

        // total page number
        Integer pageTotal = pageTotalCount / pageSize;
        if (pageTotalCount % pageSize > 0) {
            pageTotal += 1;
        }

        page.setPageSize(pageSize);
        page.setPageTotalCount(pageTotalCount);
        page.setPageTotal(pageTotal);
        page.setPageNo(pageNo);

        // 2. set current page's items' data
        // begin is the first index of current page's item
        int begin = (page.getPageNo() - 1) * pageSize;
        // items is current page's items
        List<Order> items = orderDao.queryPageOrderByUserId(userId, begin, pageSize);
        page.setItems(items);

        return page;
    }


    @Override
    public List<OrderItem> showOrderDetails(String orderId) {
        return orderItemDao.queryOrderItemsByOrderId(orderId);
    }

    @Override
    public Page<OrderItem> showOrderDetailsPage(String orderId, int pageNo, int pageSize) {
        Page<OrderItem> page = new Page<>();

        // 1. get the pageTotalCount and pageTotal data
        // total number of items
        Integer pageTotalCount = orderItemDao.queryOrderItemsByOrderIdPageCount(orderId);

        // total page number
        Integer pageTotal = pageTotalCount / pageSize;
        if (pageTotalCount % pageSize > 0) {
            pageTotal += 1;
        }

        page.setPageSize(pageSize);
        page.setPageTotalCount(pageTotalCount);
        page.setPageTotal(pageTotal);
        page.setPageNo(pageNo);

        // 2. set current page's items' data
        // begin is the first index of current page's item
        int begin = (page.getPageNo() - 1) * pageSize;
        // items is current page's items
        List<OrderItem> items = orderItemDao.queryOrderItemsByOrderIdPage(orderId, begin, pageSize);
        page.setItems(items);

        return page;
    }

    @Override
    public int deliverOrder(String orderId) {
        return orderDao.changeOrderStatus(1, orderId);
    }

    @Override
    public int receiveOrder(String orderId) {
        return orderDao.changeOrderStatus(2, orderId);
    }
}
