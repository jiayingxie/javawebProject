package com.xjy.javaweb.proj.dao;

import com.sun.org.apache.xpath.internal.operations.Or;
import com.xjy.javaweb.proj.pojo.Order;

import java.util.List;

/**
 * @Author Jiaying Xie
 * @Description:
 */
public interface OrderDao {
    /*
     * @param order
     * @return
     * @Description: save the order to the database
     **/
    public int saveOrder(Order order);


    /*
     * @param
     * @return the bookstore's all orders
     * @Description: to query all orders, only admin could use
     **/
    public List<Order> queryOrders();

    /*
     * @param begin, the first Order Obejct on the page
     * @param pageSize
     * @return
     * @Description: same as queryOrders with paging
     **/
    public List<Order> queryPageOrders(int begin, int pageSize);

    /*
     * @param
     * @return
     * @Description: count how many orders the book store has
     **/
    public Integer queryPageOrdersCount();

    /*
     * @param userId
     * @return a customer's all orders
     * @Description: to query one customer's all orders
     **/
    public List<Order> queryOrderByUserId(Integer userId);

    /*
     * @param userId
     * @param begin, the first Order Obejct on the page
     * @param pageSize, how many Order Obejct should be display in one page
     * @return a customer's all orders
     * @Description: same as queryOrderByUserId, only add the paging function
     **/
    public List<Order> queryPageOrderByUserId(Integer userId, int begin, int pageSize);

    /*
     * @param userId
     * @return the number of order the given userId has
     * @Description: count how many orders the given userId has
     **/
    public Integer queryPageOrderCountByUserId(Integer userId);

    /*
     * @param orderId
     * @param userId
     * @return how many influenced rows, if -1, means failed; 1 means success
     * @Description: if the status of order is changed, call this function,
     * for instance, if admin click to deliver, the order status is delivering,
     * not changing; if customers receive the package and click the corresponding
     * button, the order status now is received, not delivering.
     **/
    public int changeOrderStatus(Integer status, String orderId);
}
