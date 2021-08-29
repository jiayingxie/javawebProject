package com.xjy.javaweb.proj.service;

import com.xjy.javaweb.proj.pojo.Cart;
import com.xjy.javaweb.proj.pojo.Order;
import com.xjy.javaweb.proj.pojo.OrderItem;
import com.xjy.javaweb.proj.pojo.Page;

import java.util.List;

/**
 * @Author Jiaying Xie
 * @Description:
 */
public interface OrderService {
    /*
     * @param cart
     * @param userId
     * @return orderId
     * @Description: to create an order
     **/
    public String createOrder(Cart cart, Integer userId);


    /*
     * @param
     * @return the bookstore's all orders
     * @Description: to show all orders, only admin could use
     **/
    public List<Order> showAllOrders();

    public Page<Order> showAllOrdersPage(int pageNo, int pageSize);

    /*
     * @param userId
     * @return a customer's all orders
     * @Description: to show the given customer's all orders
     **/
    public List<Order> showMyOrders(Integer userId);

    /*
     * @param userId
     * @param pageNo, which page will be displayed
     * @param pageSize how many order objects display in one page
     * @return the Page Object of Order, so in the front end, people could click different pages
     * @Description: same as showMyOrders, adding paging
     **/
    public Page<Order> showMyOrdersPage(Integer userId, int pageNo, int pageSize);

    /*
     * @param orderId
     * @return a List of the given orderId's all orderItems
     * @Description: to show the given orderId's all orderItems
     **/
    public List<OrderItem> showOrderDetails(String orderId);

    /*
     * @param orderId
     * @return
     * @Description: same as showOrderDetails with paging
     **/
    public Page<OrderItem> showOrderDetailsPage(String orderId, int pageNo, int pageSize);

    /*
     * @param orderId
     * @return -1 means fail; 1 means success
     * @Description: admin delivers order
     **/
    public int deliverOrder(String orderId);

    /*
     * @param orderId
     * @return -1 means fail; 1 means success
     * @Description: customer has received the order
     **/
    public int receiveOrder(String orderId);
}
